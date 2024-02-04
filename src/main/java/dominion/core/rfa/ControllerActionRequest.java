package dominion.core.rfa;

import dominion.core.player.Player;

/**
 * Represents the request and response of the system towards a PlayerController completing an action
 *
 * @param <T> The type of response that is expected from the request
 */
public abstract class ControllerActionRequest<T> {

    Player player;

    T response;

    boolean optional;

    /**
     * Overridden constructor for a request
     *
     * @param player The player the request will be sent to
     */
    protected ControllerActionRequest(Player player, boolean optional) {
        this.player = player;
        this.response = null;
        this.optional = optional;
    }

    /**
     * Trigger for running the action
     */
    public ControllerActionRequest<T> execute() {
        RequestForActionRouter.getInstance().requestAction(this);
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
