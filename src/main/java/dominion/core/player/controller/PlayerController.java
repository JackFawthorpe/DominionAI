package dominion.core.player.controller;

import api.agent.ActionController;
import dominion.core.rfa.ControllerActionRequest;

public interface PlayerController {

    void handleAction(ControllerActionRequest<?> controllerActionRequest);

    ActionController getActionController();

}
