# Minesweeper
The project realized the minesweeper game on Android.

This project is based on Android Studio software development, mainly has the following three functions: user registration and login, game music playback and mine clearance function, and mainly includes six activities: MainActivity, RegisterActivity, LogActivity, SelectActivity, MusicBox, GameActivity. After opening the "Minesweeper Mini-game" App, the "User Registration and login" interface will pop up. After information registration or information login, you will jump to the "Game level Selection" interface. In this interface, you can switch, play and pause the game music, and then select the game level (primary, intermediate, advanced), you can start the game.

Since left and right clicks cannot be performed on the mobile terminal, click events are divided into "click" and "long press". When long press the screen, it indicates that the player suspects that the current game block is a "minefield", and marks the current game block with a "small red flag"; When the screen is clicked, it means that the player judges that the current block is not a "minefield", and the background verifies the current game block. If it is a "minefield", the player guesses wrong and the game fails; If it is not a "minefield", perform a minefield or an extended minefield on the current game block. When all the game blocks are clicked, or mined, or long pressed mark, judge whether the player guessed all the "minefields", and mark one by one, the game is successful; Otherwise, the game fails.

## Initial interface
![image](https://github.com/ZoeEsther/Minesweeper/assets/119051069/8e233c97-c74b-47de-8b25-14b90dd5ca26)

## Game interface
![image](https://github.com/ZoeEsther/Minesweeper/assets/119051069/5a9401d7-1237-4f0f-bcdd-1a03435b5a35)

## The implementation process of core functions
![image](https://github.com/ZoeEsther/Minesweeper/assets/119051069/3ed6fa69-c39f-491e-90df-01821e723b63)

![image](https://github.com/ZoeEsther/Minesweeper/assets/119051069/3091e728-a095-4318-8776-a9993aaad261)

## Music playback interface with water ripples
![image](https://github.com/ZoeEsther/Minesweeper/assets/119051069/23c01d8f-b16f-49a1-b01d-a9140017ec59)
