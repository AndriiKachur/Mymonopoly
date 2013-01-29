package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameChest;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gamechests")
@Controller
@RooWebScaffold(path = "/backend/gamechests", formBackingObject = GameChest.class)
public class GameChestController {
}
