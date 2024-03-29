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
    private boolean executed;

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
        this.executed = false;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    /**
     * Trigger for running the action
     */
    public ControllerActionRequest<T> execute() {
        RequestForActionRouter.getInstance().requestAction(this);
        this.executed = true;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public T getResponse() {
        if (!executed) {
            throw new RuntimeException("getResponse called before request executed");
        }
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
