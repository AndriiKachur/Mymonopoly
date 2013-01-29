package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameRailroad;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gamerailroads")
@Controller
@RooWebScaffold(path = "/backend/gamerailroads", formBackingObject = GameRailroad.class)
public class GameRailroadController {
}
