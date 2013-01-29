package net.mymonopoly.web.backend;

import net.mymonopoly.entity.GameTheme;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/gamethemes")
@Controller
@RooWebScaffold(path = "/backend/gamethemes", formBackingObject = GameTheme.class)
public class GameThemeController {
}
