## Table of Contents

- [Introduction](#introduction)
- [Setting up an AI](#set-up-a-new-ai)
- [Configuring Simulation Environment](#configuring-game-simulation)
- [Reporting a bug](#reporting-a-bug)
- [Script Troubleshooting](#troubleshooting-scripts)

## Introduction

Discord Link: [Redacted]

## Set up a new AI

During the challenge you are likely to want to experiment with new strategies and improve upon old ones.
To measure progress of how your AI's are developing it's important to have a wide range of AI to test against
including the ones you've previously made. To support this a script is supplied for generating AI classes.

```./scripts/GenerateAI.sh <AI Name>```

Running the above command will do one of two things. Firstly, if it is a new AI name, it will make a copy
of the default AI Controller and give it the name you provided. For instance JacksCoolAI would be created as
JacksCoolAI_1.java within the ```src/main/java/api/ai``` package. Secondly, once you have your AI in a state you
are happy with, you can run the same command with the same AI to version it. This would create a copy of the AI and
increment the version for you test your new improvements as they are made.

## Configuring Game Simulation

### Configuration Options

Below outlines each of the fields that are required within a configuration file

| Field             | Explanation                                                     | Example                   |
|-------------------|-----------------------------------------------------------------|---------------------------|
| statisticsEnabled | Disables the end of game statistics screen (Debugging purposes) | ```true``` or ```false``` |
| games             | The amount of games to simulate                                 | ```100```                 |
| playerCount       | The amount of players to play against                           | ```2``` to ```4```        |

### Loading Configuration file

Whenever you make changes to configuration you should run the following command at the
root directory of the project.

```./scripts/LoadConfiguration.sh <ConfigFileName>```

Alternatively, you can manually load a configuration by copying the contents of your configuration file into
```loadedConfiguration.json```. It is still recommended to have multiple configuration files as you develop
new versions of your AI strategies.

## Reporting a Bug

It is possible that within the AI development Challenge you will encounter bugs within the logic of the game. This can
occur in one of two ways.

1. A flaw within the AI you have made. Although the API is designed to minimise the amount of errors that a player can
   make when deciding what to do, it is not impossible to play an illegal move. An example of this would be refusing to
   discard when it is a required action. This will display as an IllegalMoveException and the game will exit. If you see
   an ```IllegalMoveException``` then the error is likely caused by the logic within your AI controller.
2. Anything else. This could present as any other error, or, an unexpected behaviour that differs from the rules
   of dominion. If an error is present, please send a copy of the dominion.log for that game to me on Discord. If not,
   provide a description of the expected and actual behaviour of the game also through discord. This can be done within
   the bugs channel of the Discord Server

## Troubleshooting Scripts

- Windows Systems usually aren't able to run shell scripts out of the box. You will see something similar
  to the following image in this case. My recommendation is to install bash, this gives you access to linux commands
  on a Windows computer. Here's an example for Windows 10 (https://www.lifewire.com/install-bash-on-windows-10-4101773)
  ![WindowsScriptFailure.PNG](Documentation%2FWindowsScriptFailure.PNG)
