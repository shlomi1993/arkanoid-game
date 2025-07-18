# Arkanoid Game

My project as part of the Intro to Object Oriented Programming course at Bar-Ilan University (1st year).

## Motivation

Through this project I've leraned about the basics of Object-Oriented-Programming. This project is an implementation of a well known Arkanoid Game, also known as Block Breaker Game.

## Project Milestones

#### Objects, Geometry, Abstract Art and Bouncing Balls

- Program Point, Line and Rectangle classes.
- Use a GUI package and Create AbstractArtDrawing class for Abstract Art.
- Create animation of Bouncing Balls by program Velocity and Ball classes.
- Create frame for the animations and later multi-framed anomations.

#### Block Removal, Lives and Scores

- Program Block and Paddle class that inherit Rectangle class.
- Code Organization Into Packages.
- Removing Blocks by BlockRemover class.
- "Killing" the player when all the balls fall from the screen using BallRemover class.
- Keeping track of scores with ScoreTrackingListener class. 
- Displaying the score with HighScoresAnimation and ScoreIndicator classes.

#### New Screens and Multiple Levels

- Reorganization and a "pause" feature with PauseScreen class.
- Add 3... 2... 1... (GO) bonus feature with CountdownAnimation class.
- Add 4 basic Levels with DirectHit, FinalFour, GoodNight and WideEasy classes.
- Multiple Levels with GenericLevel class.
- Add new game management classes such as GameEnvironment, GameFlow and GameLevel.
- Add WinScreen and LooseScreen classes.

#### Java I/O: Reading and Writing Files
- Level-specification files - Allow creating levels using .txt files.
- Keeping highest score by saving it into .txt file.
- Add an Opening Menu.
- Allow imaged backgrounds.

###### Menu flow:

![image](https://user-images.githubusercontent.com/72878018/120080131-257af100-c0c0-11eb-91b0-f4ddeaa264d9.png)


## Running the game

1. Install ant on your Linux-based or WSL2 system
2. Download this repo and extract files.
3. Open a terminal in the project directory.
4. Run "ant compile" command.
5. Then run "ant -Dargs=<path_to_level_file> run" while "<path_to_level_file>" replaced with the file to the level path. You can also run the program withput arguments and the game will be run with default 4 levels.

## Notes

- To use level-files or to define new kind of blocks, use the files in "ArkanoidGame\resources\definitions" path. You can also edit those files as you wish.

- The game has different difficulty levels that are expressed by ball speed, number of balls at the beginning, and paddle length.

## Language

100% Java.

## IDEs, Writers and Tools

- JetBrains IntelliJ IDEA
- Notepad++
- BIUOOP-1.4.jar - a graphic package.
- CheckStyle - a tool to maintain Java coding conventions.

## Screenshots

![image](https://user-images.githubusercontent.com/72878018/120080205-7ab70280-c0c0-11eb-9a2a-35dee90a0753.png)
![image](https://user-images.githubusercontent.com/72878018/120080212-81de1080-c0c0-11eb-80fa-0e0e9a90ccc0.png)
![image](https://user-images.githubusercontent.com/72878018/120080234-93bfb380-c0c0-11eb-897e-e1493cd6974f.png)
![image](https://user-images.githubusercontent.com/72878018/120080248-a0440c00-c0c0-11eb-81b9-b23b0a6da9eb.png)
