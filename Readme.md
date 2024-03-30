## Table of Contents

- [Introduction](#introduction)
- [Installation](#instalation)
- [Create an agent](#create-an-agent)
- [Configuring Simulation Environment](#configuring-game-simulation)
- [Reporting a bug](#reporting-a-bug)
- [Script Troubleshooting](#troubleshooting-scripts)

## Introduction

## Installation

To design an agent the following software needs to be installed.

Java: Version 18 is supported however other versions are likely to work. To install Java
follow [this guide](https://www.freecodecamp.org/news/install-openjdk-free-java-multi-os-guide/)

With Java installed you need to decide where to put your source code that is easily accessible from the terminal. My
personal suggestion is to have a dedicated development folder that is close to the root of your terminal. On a windows
computer this might look like `C:\development\mas-engine`. All commands within this readme should be ran from this
directory. If you aren't confident in the terminal yet read through the [Terminal Usage](#terminal-usage) section.

To verify that the engine is working correctly run the following command in the projects root directory

```
./gradlew test
```

This runs the tests to ensure the software is working as expected. It may take a while on the first run as it
initialises gradle. The expected outcome of this will be a `BUILD SUCCESSFUL` message.

Now that you have verified everything is functional you can start creating agents. Read
through [Create an agent](#create-an-agent) to start.

## Create an Agent

During the challenge you are likely to want to experiment with new strategies and improve upon old ones.
To measure progress of how your agent is are developing it's important to have a wide range of agent to test against
including the ones you've previously made. To support this a script is supplied for generating agent classes.

```./scripts/GenerateAgent.sh <Agent Name>```

Running the above command will do one of two things. Firstly, if it is a new agent name, it will make a copy
of the default agent controller and give it the name you provided. For instance JacksCoolAgent would be created as
JacksCoolAgent_1.java within the ```src/main/java/api/agent``` package. Secondly, once you have your agent in a state
you
are happy with, you can run the same command with the same agent to version it. This would create a copy of the agent
and
increment the version for you test your new improvements as they are made.

## Configuring Game Simulation

### Configuration Options

Below outlines each of the fields that are required within a configuration file

| Field             | Explanation                                | Example                   |
|-------------------|--------------------------------------------|---------------------------|
| statisticsEnabled | Disables the end of game statistics screen | ```true``` or ```false``` |
| games             | The amount of games to simulate            | ```100```                 |

### Loading Configuration file

Whenever you make changes to configuration you should run the following command at the
root directory of the project.

```./scripts/LoadConfiguration.sh <ConfigFileName>```

Alternatively, you can manually load a configuration by copying the contents of your configuration file into
```loadedConfiguration.json```. It is still recommended to have multiple configuration files as you develop
new versions of your agent strategies.

### Loading Agent into the game

Once you have created a controller you need to set the player controller within the code. This can be done with the
following steps.

1. Open `src/main/java/dominion/core/player/loader/PlayerLoaderImpl.java`
2. Within `controllersToLoad` you can replace any of these with your controller.

As an example, If I wanted to load CoolAgent_1.java into the first and third slot it will look like

```
 private final List<ActionController> controllersToLoad = List.of(
         new CoolAgent_1(),
         new DefaultController(),
         new CoolAgent_1(),
         new DefaultController()
 );
```

Make sure is imported as well and it will be good to go!

## Reporting a Bug

It is possible that within the agent development Challenge you will encounter bugs within the logic of the game. This
can
occur in one of two ways.

1. A flaw within the agent you have made. Although the API is designed to minimise the amount of errors that a player
   can
   make when deciding what to do, it is not impossible to play an illegal move. An example of this would be refusing to
   discard when it is a required action. This will display as an IllegalMoveException and the game will exit. If you see
   an ```IllegalMoveException``` then the error is likely caused by the logic within your agent controller.
2. Anything else. This could present as any other error, or, an unexpected behaviour that differs from the rules
   of dominion. If an error is present, please send a copy of the dominion.log for that game to me on Discord. If not,
   provide a description of the expected and actual behaviour of the game also through discord. This can be done within
   the bugs channel of the Discord Server

### Terminal Usage

Throughout this competition you are likely to use the terminal to run commands to generate Agent templates and to
interact with the game engine. Linux and Mac machines will be able to run all of these commands immediately however
Windows computers may have a little trouble when configured incorrectly. A commonly used tool to simplify this is
the [gitbash ternminal](https://git-scm.com/downloads). This comes packaged with [git](https://git-scm.com/downloads),
a [version control system](https://about.gitlab.com/topics/version-control/) that is widely used in the software
industry. Once installed open gitbash and use this as your terminal.