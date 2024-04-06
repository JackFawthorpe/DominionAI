package dominion.core.player.controller;

import dominion.core.rfa.ControllerActionRequest;

public interface PlayerController {

    void handleAction(ControllerActionRequest<?> controllerActionRequest);

}
