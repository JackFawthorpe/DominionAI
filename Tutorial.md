# Getting Started with MAS-Competition

## Contents

- [Introduction](#introduction)
- [Dominion](#dominion)
- [Playing Dominion Online](#playing-dominion-online)
- [Setting up your Development Environment](#setting-up-your-development-environment)
- [Creating your first agent](#creating-your-first-agent)
- [Creating a good strategy](#creating-a-good-strategy)

## Introduction

Welcome to the MAS-Competition. The goal of the competition is for you and your team to work together to create an agent
that plays the board-game [Dominion](https://boardgamegeek.com/boardgame/36218/dominion). You'll do this by writing some
code to answer a set of prompts the agent has to act upon. As Dominion is a card game these prompts will be of the form
"Which of these cards should I buy" or "Which card should I play". This tutorial will outline the steps you should take
to learn how to play dominion and set up an environment that you can use to develop the agents.

## Dominion

[Dominion](https://boardgamegeek.com/boardgame/36218/dominion) is a card game where you are collecting cards into your
deck that will earn you victory points. Some cards are money cards, which you can use to buy more cards.
Some are actions which let you play more cards on your turn, buy more cards or draw more cards. Finally, some cards are
victory point cards, these cards don't do anything but are worth points at the end of the game.

![CopperCard.PNG](Images%2FCopperCard.PNG)
![CellarCard.PNG](Images%2FCellarCard.PNG)
![EstateCard.PNG](Images%2FEstateCard.PNG)

## Playing Dominion Online

As boring as it can be the best place to start would be reading the rules of dominion (or skimming for those more
daring). A pdf of the rules of the game can be
found [here](https://cdn.1j1ju.com/medias/59/e6/c2-dominion-rulebook.pdf). If you would prefer a
video, [here](https://www.youtube.com/watch?v=5jNGpgdMums) is a quick 3-minute video explaining the rules and some
simple strategy for what you should do.

Now that you are familiar with the rules you can play the game for free online with the following steps.

1. Go to the website https://dominion.games/
   ![DominionGamesLogin.PNG](Images%2FDominionGamesLogin.PNG)
2. You can create an account for free here
3. Once you've logged in and verified your account you will be on the home screen. Within your group someone will need
   to make a lobby. If you are not making the lobby skip to step 7.
4. On the left hand side of the screen click "New Table"  
   ![NewTable.PNG](Images%2FNewTable.PNG)
5. In the following screen you'll have a few things to do, firstly, lets change the player count. This can be done with
   the + and - in the players area. If you are playing by yourself you can add some AI using the little robot
   button.
   ![PlayersArea.PNG](Images%2FPlayersArea.PNG)
6. The next part is selecting the cards to play with. Usually this is something that changes from game to game but for
   this competition we will stick with the beginner set. This can be
   found [here](https://wiki.dominionstrategy.com/index.php/First_Game). To select a card for the game, click on one of
   the non-selected cards and then click one of the cards that you want to play
   with.  
   ![SelectCards.PNG](Images%2FSelectCards.PNG)  
   Once this is done the game should look like this:  
   ![ExpectedGame.PNG](Images%2FExpectedGame.PNG).
7. To join the game, click on the Tables option in the menus.  
   ![Tables.PNG](Images%2FTables.PNG).
   You'll then be able to search for your team-mates name to find the lobby that they have created. Click play to join
   their lobby.  
   ![TablesScreen.PNG](Images%2FTablesScreen.PNG)
8. When you are ready to play click the ready button at the bottom of the screen.

### Playing

To start the game simply click the start button  
![StartGame.PNG](Images%2FStartGame.PNG)  
The good news is once the game is started it will instruct what you need to do as shown below.   
![Instruction.PNG](Images%2FInstruction.PNG)  
On your first two turns all you can do is buy cards. This is done by playing your coppers and then clicking a card that
you want to buy. Cards that can be bought will be indicated with a blue plus icon, their costs shown in the bottom left
of the card. To see what a card does, you can right-click the card to inspect it.  
![BuyCardExample.PNG](Images%2FBuyCardExample.PNG)  
Other actions are completed by clicking the card that you want to fulfill the action and then clicking confirm. An
example of this is shown in the image below. Someone played the militia meaning I have to discard two cards.  
![DiscardExample.PNG](Images%2FDiscardExample.PNG)

## Setting up Your Development Environment

After familiarising yourself with dominion the next step is to set up your development environment to create some
agents! Although you can use whatever IDE you would like to work on these agents the recommended IDE
is [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows).

From the project menu, click "open" and select the unzipped project that you have downloaded, titled "mas-engine". This
project contains all the code required to develop and test your agents locally.  
![OpenProject.PNG](Images%2FOpenProject.PNG)

The project builds and runs with a build system known as [Gradle](https://gradle.org/). To check that the project is
working you can run the engine with the default agents. You can do this with the following steps.

1. Click the elephant icon on the right hand side of IntelliJ. This will open the gradle run configurations
2. Run the following task ```Tasks/application/run```  
   ![Gradle.PNG](Images%2FGradle.PNG)
3. This will run 5 simulations of the dominion game with 4 of the default agents. A console will appear at the bottom of
   intelliJ with an output similar to the below code snippet. Each of the 5 arrays represents the points at the end of
   one of the games. ```[Player 1, Player 2, Player 3, Player 4]```

```
3:26:06 PM: Executing 'run'...

> Task :generateEffectiveLombokConfig UP-TO-DATE
> Task :compileJava UP-TO-DATE
> Task :processResources
> Task :classes

> Task :run
[0, 2, -1, 2]
[1, 4, -3, 1]
[2, -2, 0, 5]
[0, -1, 5, 1]
[7, 4, -1, 1]

BUILD SUCCESSFUL in 1s
4 actionable tasks: 2 executed, 2 up-to-date
3:26:08 PM: Execution finished 'run'.
```

## Creating Your First Agent

With everything set up it's time to code an agent. Agents are stored in ```src/main/java/api/agent```. When you first
start your engine this will contain an interface ```ActionController``` and the default agent ```DefaultController```.
To create a new agent simply copy paste the DefaultController, giving it a new name. For the study, please use the
[agent naming scheme](#agent-naming-scheme). The code you will write will contain 5 decision-making functions to help
the agent pick cards for different actions. The use of these functions is explained within the comments above the
functions but a summary is also shown below.

| Function            | Action Explanation                                                                                                                                                                                                                                  |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| BuyCardHook         | This is for when the agent has the option to buy a card. It will provide all the cards that the agent can buy with their money. You simply need to return the card you want to buy or null if you are happy to not buy anything                     |
| DiscardFromHandHook | This is when the agent is asked to discard a card. Note that sometimes you are forced to and sometimes it is optional. You must return a card if ```isRequired``` is ```true``` otherwise you can discard if you want to, or return null if not     |
| GainCardHook        | This is the same as buying a card however in this case you don't have to pay for the card.                                                                                                                                                          |
| TrashCardHook       | This is the same as discarding except you will never get this card back (Its destroyed)                                                                                                                                                             |
| PlayActionCardHook  | This, alongside buying a card is the most important function. It decides which card to play on your turn. The order you play your cards on a turn changes how many cards you can play, how much money you will have and how many cards you can buy. |

The default agent simply picks a random card for each of these. You can imagine that this isn't a very good strategy. To
make decisions you will need to read the state of the game and write simple rules to pick the right cards in the right
situation. To read the state of the game, you have been provided an API that lets you see information like, what cards
are still available to buy? What cards do I own? How many points does everyone have?
This API is within ```src/main/java/api```.

Below is an example of how you can use the API. I encourage you to explore the different data you have. After that is
the set of rules that your agent must follow. Please make sure you read through this as for the security of the server
there are some tight restrictions on what is allowed in the agents.

### When buying a card, I want to prioritise buying gold until I have 5 gold in my deck

```
/**
     * Buying a card is spending the cost of the card (card.getCost()) to buy the card
     * <p>
     * Return the card that you want your agent to buy or null if you don't want a new card
     */
    public CardData buyCardHook(List<CardData> buyOptions) {

        // This retrieves my deck of cards
        PlayerData myData = PlayerAPI.getMe(this);
        DeckData myDeck = myData.getDeckData();

        // This uses a for loop to iterate over my cards and count how much gold I have
        int currentGoldCount = 0;
        for (CardData card : myDeck.getCardList()) {
            if (card.getName().equals(CardName.GOLD)) {
                currentGoldCount++;
            }
        }

        // This determines if I should buy gold
        // I can then iterate through my buying options and if it is gold then I return the card to buy it
        boolean shouldBuyGold = currentGoldCount < 5;
        if (shouldBuyGold) {
            for (CardData cardData : buyOptions) {
                if (cardData.getName().equals(CardName.GOLD)) {
                    return cardData;
                }
            }
        }

        // If I couldn't find any gold to buy, or I already have enough then I Just select a random card
        return getRandomCard(buyOptions);
    }
```

### Agent Naming Scheme

Agents should have a title followed by an underscore and a version number. For instance ```ExampleAgent_1``` would
represent the first iteration of the ExampleAgent agent. This is a good way to keep your agents organised and keep track
of the evolution of different strategies you have tried.

## Agent Rules

Below are a set of rules that you must follow when creating your agents. Failing to follow these rules will disqualify
your agent. This can be seen below.  
![IllegalImports.PNG](Images%2FIllegalImports.PNG)

Note that even though your agent works locally it is not guaranteed to work on the server. This is because there are
extra security measures put in place, only on the server.

Rules:

- You can only make imports from the ``src/main/java/api/agent`` package and the following list of imports.
    - ```java.util.Collection```
    - ```java.util.Comparator```
    - ```java.util.Iterator```
    - ```java.util.List```
    - ```java.util.ListIterator```
    - ```java.util.Map```
    - ```java.util.MapEntry```
    - ```java.util.Set```
    - ```java.util.Spliterator```
    - ```java.util.ArrayList```
    - ```java.util.Collections```
    - ```java.util.HashMap```
    - ```java.util.HashSet```
    - ```java.util.Optional```
    - ```java.util.Random```
- None of your functions must exceed 0.1s in run time. This likely wont be an issue but please look out for endless
  loops as these will disqualify the agent.
- You must complete the entire interface. If your code is working locally this will be okay.
- You must play the game without cheating! This will be described as an Illegal Move and this might happen accidentally.
  It is recommended that you run your agent locally a few times to make sure this never happens. When this does happen
  it will present as an ```IllegalMoveException```. Examples of this include:
    - Returning a card that wasn't an option
    - Returning null (picking no card) when it is a required action, such as trashing and discarding.
- Although it is okay to have print statements in your agent as you are developing it (infact its encouraged to
  understand its behaviour), please remove all print statements from your agent before submitting. In the case that you
  have left print statements in, it will count as an illegal import.

Speaking of testing your agent, below outlines how you can actually run your agent.

### Running Your Agent

To run your agent you must load it in through the ```PlayerLoader```. Open the
file ```src/main/java/dominion/core/player/loader/PlayerLoaderImpl```. At the top of this class you will see a list
called, agentsToLoad. Intially, this will be 4 DefaultControllers. To load in your agent you simply need to remove one
of these and instantiate your agent. An example of this is shown below.  
![PlayerLoader.PNG](Images%2FPlayerLoader.PNG)
By default, when you run the gradle run task it will only play 5 games but you can
modify ```src/main/resources/confgiration/LoadedConfiguration.json``` to change this to be as many games as you'd like.

### Submitting Your Agent

Once you have an agent that you are happy to submit, you can go to
the [website](https://mas-competition.csse.canterbury.ac.nz/test) to submit.

First you will be prompted to log in, you can log in with your email and the password ```Password1!```. I advise that
you change your password as soon as you log in. This can be done by opening the settings and clicking "Change Password".
![ChangePassword.PNG](Images%2FChangePassword.PNG)

To submit an agent you can navigate to agent submission and simply fill out the details as requested.
![AgentSubmission.PNG](Images%2FAgentSubmission.PNG)

You are also provided with two leaderboards. The first is the agent leaderboard. This will show you the currnet ratings
of each agent. If you are curious on how these are determined see [Agent Ranking](#agent-ranking). More importantly
though is the team leaderboard. This is where you can see how you stack up against your classmates. May the best team
win!

That's all for the tutorial, you can now work with your team to devise strategies to try out and experiment with. If you
are looking for some ideas to get started read through the [strategy section](#creating-a-good-strategy). If you have
any ideas about how the experience could be improved whilst you play, please keep note of them and supply them at the
end of the study.

## Creating a Good Strategy

If you are struggling to think of some strategies to try out you could consider trying to implement some of the
strategies the best players in the world use in real life. As you can imagine, this could be quite challenging to start
with. I suggest that you try and extract some guiding principles from the strategies provided here

The other piece of advice is to create metrics of your agents to extract information out of the simulated games. For
instance, maybe create some functions that allow you to print out what cards you have at the end of the game, do they
match what you expect? What about understanding how much money you are creating per turn? There are many small bits of
data that can help you to infer where your agent is struggling.

[//]: # (Online Strategies)


[//]: # (Getting metrics)

## Agent Ranking

The server is always playing games behind the scenes with your agents. It will match four agents up randomly and agents
ratings will move up and down based on which agents they are able to do better than. As an example, in a game, my agent
might place 2nd beating a 1500 and 1300 agent but losing to a 2100, this would mean I should expect its rating to be
around 1800.