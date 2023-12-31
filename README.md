﻿# EklavyaByteTelegramBot

 # Telegram Bot with TeligramBot Library

This Telegram bot provides two main functionalities:

1. **Weather**: Quickly check the weather by selecting this option.
2. **Search Pictures**: Jump straight into the picture search feature.

This bot is built using the TeligramBot library, which is free to use.

## Getting Started

To use this bot, follow these steps:

1. Obtain the required API key/token.
2. Put the API key/token into the `config.property` file located in the `resource` package.
3. Bot token and botname can be obtain from Teligram Bot father ! 
4. Api key can be obtain from https://openweathermap.org and https://pixabay.com/.

## How to Use

### Start Command
When you give the `/start` command, the bot responds with:

Bot Name
Welcome @UserAddress
Bot Name
🌐 Key Menu - /keyMenu 🌐
The /keyMenu command allows you to access a menu with shortcuts for common tasks. Here's what you can do with it:

🌤️ Weather: Quickly check the weather by selecting this option.

🖼️ Search Pictures: Jump straight into the picture search feature.

ℹ️ Help: Get detailed information on how to use the bot's commands.

To use this feature, simply type:
/keyMenu
Enjoy the convenience of quick navigation! 🤖


### Weather Command
The `/weather {your location }` command displays:
let say Delhi `/weather Delhi` output will be like : 

/weather Delhi 
In Delhi, located at a lat. of 28.6667 and lon. of 77.2167, the weather conditions are as follows:
The sun rises at 05-10-202* 06:15:45 and sets at 05-10-202* 18:03:30.
The temperature is currently 26.05°C with a minimum of 26.05°C and maximum of 26.05°C.
The atmospheric pressure is 1010 hPa, and the humidity level stands at 47%.
Visibility extends up to 2800 meters, and weather Description : haze.

### Picture Command
The `/pic {your search}` command displays:
let say Sunflower `/pic sunflower red` output will be like : 

![sunflower red](https://cdn.pixabay.com/photo/2021/09/17/17/01/red-sunflower-6633324_1280.jpg)
picture and tag name ! 

### Key Menu Command
Upon using the `/keyMenu` command, a menu will appear on your screen for selecting commands. It provides a clear idea of how to use the bot.

### Help Command
The `/help` command displays:

Bot Name
Welcome @userAddress
Bot Name
🌐 Key Menu - /keyMenu 🌐
The /keyMenu command allows you to access a menu with shortcuts for common tasks. Here's what you can do with it:

🌤️ Weather: Quickly check the weather by selecting this option.
🖼️ Search Pictures: Jump straight into the picture search feature.
ℹ️ Help: Get detailed information on how to use the bot's commands.
To use this feature, simply type:
/keyMenu
Enjoy the convenience of quick navigation! 🤖


### Close Command
The `/close` command will close the keyboard.

## Enjoy using the bot!







