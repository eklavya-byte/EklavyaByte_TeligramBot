package org.prem.bots;

import org.prem.WebSerches.Picutre;
import org.prem.WebSerches.Weather;
import org.prem.connection.Credentials;
import org.prem.helper.PathValidator;
import org.prem.helper.PathVariable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EklavyaByteBot extends TelegramLongPollingBot {

    String BOT_NAME = Credentials.botName();
    String TOKEN  = Credentials.token();

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText() ) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            String message_text = update.getMessage().getText();
            if (message_text.equals("/start")) {
                message.setText("Welcome  " + update.getMessage().getChat().getUserName());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                message.setText("\uD83C\uDF10 Key Menu - /keyMenu \uD83C\uDF10\n" +
                        "\n" +
                        "The `/keyMenu` command allows you to access a menu with shortcuts for common tasks. Here's what you can do with it:\n" +
                        "\n" +
                        "1. \uD83C\uDF24\uFE0F Weather: Quickly check the weather by selecting this option.\n" +
                        "2. \uD83D\uDDBC\uFE0F Search Pictures: Jump straight into the picture search feature.\n" +
                        "3. ℹ\uFE0F Help: Get detailed information on how to use the bot's commands.\n" +
                        "\n" +
                        "To use this feature, simply type:\n" +
                        "`/keyMenu`\n" +
                        "\n" +
                        "Enjoy the convenience of quick navigation! \uD83E\uDD16");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            // find weather #function
            else if (PathValidator.weatherPath(message_text).equals("/weather")) {
                String location = PathVariable.pathCheck(message_text);
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setReplyToMessageId(update.getMessage().getMessageId());
                // here run weather search and pass data into msg
                String weather=null;
                try {
                    if(update.getMessage().getText().equals("/weather") || update.getMessage().getText().equals("/weather ") || update.getMessage().getText().equals("/weather   ")){
                        weather= "search like -  /weather {your location} ";
                    }else {
                        weather = Weather.simpleGetParm(location);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (weather != null) {
                    msg.setText(weather);
                }else {
                    msg.setText(location);
                }
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (PathValidator.picturePath(message_text).equals("/pic") || message_text.equals("/pic") || message_text.equals("/pic ")) {
                SendPhoto msg = new SendPhoto();
                msg.setChatId(update.getMessage().getChatId());
                SendMessage textMessage = new SendMessage();
                String search = PathVariable.picPathCheck(message_text);
                if(search.equals("error")){
                    textMessage.setText("Enter --> \"/pic {your search}\"");
                    textMessage.setChatId(update.getMessage().getChatId());
                    textMessage.setReplyToMessageId(update.getMessage().getMessageId());
                    textMessage.setReplyToMessageId(update.getMessage().getMessageId());
                    try {
                        execute(textMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }finally {
                        return;
                    }
                }
                Map<String, String> tagUrlMap = Picutre.simpleGetParm(search);
                for (Map.Entry<String, String> entry : tagUrlMap.entrySet()) {
                    String tag = entry.getKey();
                    String url = entry.getValue();
                    msg.setCaption(tag);
                    msg.setPhoto(new InputFile(url));
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else if (update.getMessage().getText().equals("/keyMenu")) {
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("Welcome  "+update.getMessage().getChat().getUserName());
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Search Weather");
                row.add("Search Picture");
                row.add("Help");
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("/close");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                msg.setReplyMarkup(keyboardMarkup);
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (message_text.equals("Search Weather")) {
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("Search like  /weather Delhi");
                //msg.setReplyToMessageId(update.getMessage().getMessageId());

                try {
                    execute(msg); // Call method to send the photo
                    msg.setText("/weather Delhi \n"+Weather.simpleGetParm("Delhi"));
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (message_text.equals("Search Picture")) {
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("Search like  /pic sunflower red");
                //msg.setReplyToMessageId(update.getMessage().getMessageId());

                try {
                    execute(msg);
                    SendPhoto ph = new SendPhoto();
                    ph.setChatId(update.getMessage().getChatId());
                    Map<String, String> tagUrlMap = Picutre.simpleGetParm("sunflower+red+wind");
                    for (Map.Entry<String, String> entry : tagUrlMap.entrySet()) {
                        String tag = entry.getKey();
                        String url = entry.getValue();
                        ph.setCaption(tag);
                        ph.setPhoto(new InputFile(url));
                        try {
                            execute(ph);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(message_text.equals("/help")|| message_text.equals("Help")){
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("\uD83C\uDF24\uFE0F Welcome to EklavyaByte! \uD83C\uDF24\uFE0F\n" +
                        "\n" +
                        "I'm here to assist you with weather information and help you find pictures. Here's how you can use my commands:\n" +
                        "\n" +
                        "1. To get weather information, use the command:\n" +
                        "   `/weather {your location}`\n" +
                        "   Example: `/weather New Delhi`\n" +
                        "\n" +
                        "2. To search for pictures, use the command:\n" +
                        "   `/pic {your pic search}`\n" +
                        "   Example: `/pic beautiful landscapes`\n" +
                        "\n" +
                        "3. For help on using the bot, simply type:\n" +
                        "   `/help`\n" +
                        "\n" +
                        "4. If you're just starting out, you can use:\n" +
                        "   `/start`\n" +
                        "   to get acquainted with me!\n" +
                        "\n");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            // instead of going back , Hide keyboard !
            else if (message_text.equals("/close")) {
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("To open again click /keyMenu");
                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                keyboardMarkup.setRemoveKeyboard(true);
                msg.setReplyMarkup(keyboardMarkup);
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                SendMessage msg = new SendMessage();
                msg.setChatId(update.getMessage().getChatId());
                msg.setText("Unknown command !!! click /keyMenu");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        // if end here
        }
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return TOKEN;
    }


}
