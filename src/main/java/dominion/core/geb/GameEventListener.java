package dominion.core.geb;

/**
 * Representation of any object that is listening in on the actions of the player
 */
public interface GameEventListener {

    String getIdentifier();

    /**
     * Function that will be triggered whenever an event is fired
     *
     * @param event The event to process
     */
    void handleEvent(GameEvent event);

}
