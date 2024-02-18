package api.ai;

import dominion.card.Card;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface that has to be implemented by an AI
 */
public interface ActionController {
    /**
     * Hook to allow for the player to choose which card they want to buy
     *
     * @param buyOptions The cards that the player can currently buy
     * @return The card the player wants to buy
     */
    Card buyCardHook(@NotNull List<Card> buyOptions);

    /**
     * Hook to allow for the player to choose which card they want to discard from their hand
     *
     * @param discardOptions The cards in their hand
     * @return The card the player wants to discard
     */
    Card discardFromHandHook(@NotNull List<Card> discardOptions, boolean isRequired);

    /**
     * Hook to allow for the player to choose which card they want to gain
     *
     * @param gainOptions The cards the play has to choose from
     * @return The card that the player chooses to gain
     */
    Card gainCardHook(@NotNull List<Card> gainOptions);

    /**
     * Hook to allow for the player to choose which card they want to trash
     *
     * @param trashOptions The cards the player has to choose from
     * @param isRequired   Tells the player if they have to trash or if its optional
     * @return The card that the player chooses to gain
     */
    Card trashCardHook(@NotNull List<Card> trashOptions, boolean isRequired);

    /**
     * Hook to allow for the player to choose which card they want to top-deck
     *
     * @param topDeckOptions The card that the player has to choose from
     * @param required       Whether the top decking action is optional
     * @return The card that the player chooses to top deck
     */
    Card chooseTopDeckHook(@NotNull List<Card> topDeckOptions, boolean required);


    /**
     * Hook to allow the player to choose which action they want to do
     *
     * @param actionCardsInHand The cards that are actionable. (The actions currently in their hand)
     * @return The card the player wants to play
     */
    Card playActionCardHook(@NotNull List<Card> actionCardsInHand);
}
