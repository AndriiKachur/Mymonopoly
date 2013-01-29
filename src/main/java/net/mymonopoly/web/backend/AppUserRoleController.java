package net.mymonopoly.web.backend;

import net.mymonopoly.entity.AppUserRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/appuserroles")
@Controller
@RooWebScaffold(path = "/backend/appuserroles", formBackingObject = AppUserRole.class)
public class AppUserRoleController {
}
