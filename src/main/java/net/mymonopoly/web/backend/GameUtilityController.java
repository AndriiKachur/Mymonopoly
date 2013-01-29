package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameUtility;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gameutilitys")
@Controller
@RooWebScaffold(path = "/backend/gameutilitys", formBackingObject = GameUtility.class)
public class GameUtilityController {
}
