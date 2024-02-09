package dominion.core.rfa;

import dominion.core.player.Entity.Player;

/**
 * Represents the request and response of the system towards a PlayerController completing an action
 *
 * @param <T> The type of response that is expected from the request
 */
public abstract class ControllerActionRequest<T> {

    private final Player player;
    private final boolean required;
    private boolean isAttack;
    private T response;

    /**
     * Overridden constructor for a request
     *
     * @param player The player the request will be sent to
     */
    protected ControllerActionRequest(Player player, boolean required) {
        this.player = player;
        this.response = null;
        this.required = required;
        this.isAttack = false;
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

    public boolean isRequired() {
        return required;
    }

    public boolean isAttack() {
        return isAttack;
    }

    /**
     * Method to signal that the action derives from an attack
     */
    public void asAttack() {
        isAttack = true;
    }
}
