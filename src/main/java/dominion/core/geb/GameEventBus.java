package dominion.core.geb;

import dominion.core.geb.event.GameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bus to handle the propagation of all events that occur within a game to the listeners that care about them
 */
public class GameEventBus {

    private static final Logger logger = LogManager.getLogger(GameEventBus.class);

    private static GameEventBus instance;

    /**
     * A mapping of a game event to the list of listeners that are listening on its firings
     */
    private final Map<Class<? extends GameEvent>, List<GameEventListener>> listenersMap;

    private GameEventBus() {
        this.listenersMap = new HashMap<>();
    }

    /**
     * Triggered when the game ends, it iterates through the different listener lists and filters out all the
     * game scoped {@link ListenScope} event listeners
     */
    public static void gameReset() {
        getInstance().listenersMap.replaceAll((event, listeners) ->
                listeners.stream().filter(listener -> listener.getScope() == ListenScope.SIMULATION)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
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
     * Adds a listener to listen in on future events only if it is not already added
     *
     * @param eventClass The class of event that the listener wants to listen to
     * @param listener   The object that will be listening
     * @param <T>        The type of event that is being listened to
     */
    public <T extends GameEvent> void addListener(Class<T> eventClass, GameEventListener<T> listener) {
        logger.info("Adding listener to {} with the identifier {}", eventClass, listener.getIdentifier());
        List<GameEventListener> listeners = listenersMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Removes a given listener from the event bus if they are actively listening
     * If they are not listening then this function silently does nothing
     *
     * @param eventClass The event type to remove the listener from
     * @param listener   The listener of the event
     * @param <T>        The type of the event
     */
    public <T extends GameEvent> void removeListener(Class<T> eventClass, GameEventListener<T> listener) {
        logger.info("Removing listener to {} with the identifier {}", eventClass, listener.getIdentifier());
        listenersMap.getOrDefault(eventClass, new ArrayList<>()).remove(listener);
    }

    /**
     * Method for notifying the listeners that a given event has occurred
     * @param event  The event to be propogated to the listeners
     * @param <T> The type of event
     */
    public <T extends GameEvent> void notifyListeners(T event) {
        logger.info("Event of type {} has hit the event bus", event.getClass());
        Class<? extends GameEvent> eventClass = event.getClass();
        List<GameEventListener> listeners = listenersMap.get(eventClass);
        if (listeners != null) {
            for (GameEventListener listener : listeners) {
                listener.handleEvent(event);
            }
        }
    }
}
