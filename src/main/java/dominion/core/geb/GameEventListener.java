package dominion.core.geb;

import dominion.core.geb.event.GameEvent;

/**
 * Representation of any object that is listening in on the actions of the player
 */
public interface GameEventListener<T extends GameEvent> {

    /**
     * Function that will be triggered whenever an event is fired
     *
     * @param event The event to process
     */
    void handleEvent(T event);

    /**
     * @return Returns a string representation of the listener that is listening in
     */
    String getIdentifier();

    /**
     * The level of persistence for the listener
     *
     * @return The scope
     */
    ListenScope getScope();

}
