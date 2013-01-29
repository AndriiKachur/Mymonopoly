package net.mymonopoly.web;

import javax.servlet.http.HttpSession;

import net.mymonopoly.engine.utils.JSON;
import net.mymonopoly.engine.utils.SNM;
import net.mymonopoly.entity.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Root controller for main actions.
 * 
 * @author Andrey K.
 * 
 */
@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@RequestMapping()
	public String rootPage(Model model) {
		return "frontend/index";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "frontend/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "n", required = true) String email,
			@RequestParam(value = "p", required = true) String password, Model model, HttpSession session) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		AppUser details = new AppUser();
		details.setEmail(email);
		token.setDetails(details);

		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);

			// TODO: remember me
			AppUser user = AppUser.findAppUsersByEmailEquals(email).getSingleResult();
			session.setAttribute(SNM.USER, user);
			session.setAttribute(SNM.NICKNAME, user.getNickname());
			return "redirect:/";
		} catch (BadCredentialsException e) {
			model.addAttribute("errors", e.getMessage());
			return "frontend/login";
		}
	}

	@RequestMapping(value = "isauthenticated", method = RequestMethod.GET)
	@ResponseBody
	public String getStatus() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !auth.getName().equals("anonymousUser") && auth.isAuthenticated()) {
			return JSON.OK;
		} else {
			return JSON.ERROR;
		}
	}

}
