package net.mymonopoly.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.mymonopoly.engine.utils.SNM;
import net.mymonopoly.entity.AppUser;
import net.mymonopoly.entity.HistoryGame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for user profile actions.
 * 
 * @author Andrey K.
 * 
 */
@Controller
@RequestMapping("/profile/")
public class ProfileController {

	@RequestMapping()
	public String rootPage(HttpSession session, Model model) {
		AppUser user = (AppUser) session.getAttribute(SNM.USER);
		if (user != null) {
			List<HistoryGame> list = HistoryGame.findByPlayerId(user.getId());
			model.addAttribute("games", list);
		}
		return "frontend/profile";
	}

}
