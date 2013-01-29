package net.mymonopoly.web.backend;

import net.mymonopoly.entity.AppUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/backend/appusers")
@Controller
@RooWebScaffold(path = "/backend/appusers", formBackingObject = AppUser.class)
public class AppUserController {
}
