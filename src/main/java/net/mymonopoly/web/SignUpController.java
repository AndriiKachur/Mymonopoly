package net.mymonopoly.web;

import java.util.Date;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.mymonopoly.entity.AppUser;
import net.mymonopoly.service.AppUserService;
import net.mymonopoly.web.dto.UserRegistrationForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sign up controller for registering user.
 */
@Controller
@RequestMapping("/signup/**")
public class SignUpController {

	private static final Log LOGGER = LogFactory.getLog(SignUpController.class);

	@Autowired
	AppUserService appUserService;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid UserRegistrationForm userRegistration, BindingResult result, Model model,
			HttpServletRequest request) {

		validate(userRegistration, result);
		boolean exists = false;
		try {
			exists = AppUser.findAppUsersByEmailEquals(userRegistration.getEmailAddress()).getSingleResult() == null ? false
					: true;
		} catch (Exception ignored) {
			LOGGER.warn("ignored", ignored);
		}

		if (result.hasErrors() || exists) {
			model.addAttribute("user", userRegistration);
			model.addAttribute("errors", result);
			model.addAttribute("exists", exists);
			return "signup/index";

		} else {
			try {
				appUserService.processSignup(userRegistration.getEmailAddress(),
						userRegistration.getNickname(), userRegistration.getFirstName(),
						userRegistration.getLastName(), userRegistration.getPassword());
			} catch (RuntimeException e) {
				LOGGER.error("Can't register: ", e);
				return "signup/error";
			}
			return "signup/thanks";
		}
	}

	@RequestMapping(params = "activate", method = RequestMethod.GET)
	public String activateUser(@RequestParam(value = "code", required = true) String activationCode,
			@RequestParam(value = "email", required = true) String email, Model model, HttpSession session) {

		TypedQuery<AppUser> query = AppUser.findAppUsersByActivationCodeAndEmail(activationCode, email);
		AppUser user = query.getSingleResult();
		if (user != null) {
			user.setActivationDate(new Date());
			user.setEnabled(true);
			user.merge();
			return "signup/completed";
		} else {
			return "signup/error";
		}
	}

	private void validate(UserRegistrationForm form, Errors errors) {
		String newPassword = form.getPassword();
		String newPasswordAgain = form.getRepeatPassword();
		if (!newPassword.equals(newPasswordAgain)) {
			errors.reject("changepassword.passwordsnotmatch");
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		UserRegistrationForm form = new UserRegistrationForm();
		model.addAttribute("user", form);
		return "signup/index";
	}

	@RequestMapping(value = "signup/thanks")
	public String thanks() {
		return "signup/thanks";
	}

	@RequestMapping(value = "signup/error")
	public String error() {
		return "signup/error";
	}
}
