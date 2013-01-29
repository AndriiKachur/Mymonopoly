package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameChance;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gamechances")
@Controller
@RooWebScaffold(path = "/backend/gamechances", formBackingObject = GameChance.class)
public class GameChanceController {
}
