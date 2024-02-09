package dominion.core.geb;

import dominion.core.geb.event.GameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bus to handle the propagation of all events that occur within a game to the listeners that care about them
 */
public class GameEventBus {

    private static final Logger logger = LogManager.getLogger(GameEventBus.class);

    private static GameEventBus instance;

    private final Map<Class<? extends GameEvent>, List<GameEventListener>> listenersMap;

    private GameEventBus() {
        this.listenersMap = new HashMap<>();
    }

    /**
     * Singleton implementation for GameEventBus
     *
     * @return The singleton of the GameEventBus
     */
    public static GameEventBus getInstance() {
        if (instance == null) {
            logger.info("Instantiating game event bus");
            instance = new GameEventBus();
        }
        return instance;
    }

    /**
     * Adds a listener to listen in on future events
     *
     * @param eventClass The class of event that the listener wants to listen to
     * @param listener   The object that will be listening
     * @param <T>        The type of event that is being listened to
     */
    public <T extends GameEvent> void addListener(Class<T> eventClass, GameEventListener<T> listener) {
        List<GameEventListener> listeners = listenersMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
        listeners.add(listener);
    }

    /**
     * Removes a given listener from the event bus if they are actively listening
     *
     * @param eventClass The event type to remove the listener from
     * @param listener   The listener of the event
     * @param <T>        The type of the event
     */
    public <T extends GameEvent> void removeListener(Class<T> eventClass, GameEventListener<T> listener) {
        listenersMap.getOrDefault(eventClass, new ArrayList<>()).remove(listener);
    }

    public <T extends GameEvent> void notifyListeners(T event) {
        Class<? extends GameEvent> eventClass = event.getClass();
        List<GameEventListener> listeners = listenersMap.get(eventClass);
        if (listeners != null) {
            for (GameEventListener listener : listeners) {
                listener.handleEvent(event);
            }
        }
    }
}
