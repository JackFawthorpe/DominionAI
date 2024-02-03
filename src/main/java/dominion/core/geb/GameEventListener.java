package dominion.core.geb;

/**
 * Representation of any object that is listening in on the actions of the player
 */
public interface GameEventListener {

    String getIdentifier();

    void handleEvent(GameEvent event);

}
