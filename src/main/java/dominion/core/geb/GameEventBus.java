package dominion.core.geb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Bus to handle the propagation of all events that occur within a game to the listeners that care about them
 */
public class GameEventBus {

    private static final Logger logger = LogManager.getLogger(GameEventBus.class);

    private static GameEventBus instance;

    private final HashMap<GameEventCode, ArrayList<GameEventListener>> listeners;
    public static GameEventBus getInstance() {
        if (instance == null) {
            logger.info("Instantiating game event bus");
            instance = new GameEventBus();
        }
        return instance;
    }

    /**
     * Registers the listener to the event code that is being listened to,
     * If there is no listeners for the given event yet, then it will instantiate the list for the event
     * @param listener The listener to append to the event
     * @param eventCode The code of the event to listen to
     */
    public void addListener(GameEventListener listener, GameEventCode eventCode) {
        ArrayList<GameEventListener> eventCodeListeners = listeners.get(eventCode);
        eventCodeListeners.add(listener);
        logger.info("Adding listener {} to event code {}", listener.getIdentifier(), eventCode);
        listeners.put(eventCode, eventCodeListeners);
    }

    /**
     * Propagates the event to each of the listeners for the given event code
     * @param event The event to propagate
     */
    public void fireEvent(GameEvent event) {
        ArrayList<GameEventListener> codedEventListeners = this.listeners.get(event.getCode());

        logger.info("Firing event {} at {} listeners", event.getCode(), codedEventListeners.size());

        for (GameEventListener listener: codedEventListeners) {
            listener.handleEvent(event);
        }
    }

    private GameEventBus() {
        this.listeners = new HashMap<>();
        for (GameEventCode code : GameEventCode.values()) {
            this.listeners.put(code, new ArrayList<>());
        }
    }
}
