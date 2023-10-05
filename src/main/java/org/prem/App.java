package org.prem;

import org.prem.bots.EklavyaByteBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 *
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot( new EklavyaByteBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
