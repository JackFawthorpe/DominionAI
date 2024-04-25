## Table of Contents

- [Introduction](#introduction)
- [Installation](#instalation)
- [Create an agent](#create-an-agent)
- [Configuring Simulation Environment](#configuring-game-simulation)
- [Reporting a bug](#reporting-a-bug)

## Introduction

## Installation

To design an agent the following software needs to be installed.

Java: Version 18 is supported however other versions are likely to work. To install Java
follow [this guide](https://www.freecodecamp.org/news/install-openjdk-free-java-multi-os-guide/)

With Java installed you need to decide where to put your source code that is easily accessible from the terminal. My
personal suggestion is to have a dedicated development folder that is close to the root of your terminal. On a windows
computer this might look like `C:\development\mas-engine`.

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
including the ones you've previously made.

To do this you should do the following steps.

1. Copy the Agent that you want to make an iteration of or DefaultController if its the first iteration.
   Agents can be found in ```src/main/java/api/agent``` .
2. Paste the file and when prompted to change the name either by iterating the version number or writing a completely
   new name with the format {AgentName}_{VerionNumber}
3. Make sure that the class name in the file matches the file name (Otherwise the server will reject the agent)

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
