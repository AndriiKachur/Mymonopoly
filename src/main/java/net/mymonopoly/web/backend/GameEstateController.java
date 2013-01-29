package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameEstate;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gameestates")
@Controller
@RooWebScaffold(path = "/backend/gameestates", formBackingObject = GameEstate.class)
public class GameEstateController {
}
