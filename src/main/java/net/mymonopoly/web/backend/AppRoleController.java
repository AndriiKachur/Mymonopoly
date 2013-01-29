package net.mymonopoly.web.backend;

import net.mymonopoly.entity.AppRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/approles")
@Controller
@RooWebScaffold(path = "/backend/approles", formBackingObject = AppRole.class)
public class AppRoleController {
}
