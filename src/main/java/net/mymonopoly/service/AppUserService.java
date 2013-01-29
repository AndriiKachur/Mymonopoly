package net.mymonopoly.service;

import java.util.Random;

import javax.persistence.TypedQuery;

import net.mymonopoly.entity.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service.
 * 
 * @author Andrey K.
 * 
 */
@Service
public class AppUserService {

	@Value("${email.from}")
	private String EMAIL_FROM;
	@Value("${email.apphost}")
	private String APP_HOST;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	/**
	 * Sends email with new password.
	 * 
	 * @param email
	 */
	public void processForgotPassword(String email) {
		TypedQuery<AppUser> userQuery = AppUser.findAppUsersByEmailEquals(email);
		if (null != userQuery && userQuery.getMaxResults() > 0) {
			AppUser user = userQuery.getSingleResult();
			Random random = new Random(System.currentTimeMillis());
			String newPassword = "pass" + random.nextLong() + "word";
			user.setPassword(messageDigestPasswordEncoder.encodePassword(newPassword, null));
			user.merge();
			sendMessage(email, "Password Recovery", "Hi " + user.getName()
					+ ",\n. You had requested for password recovery. Your password is " + newPassword
					+ ".\n Please change it.");
		}
	}

	/**
	 * Sends user activation account email for finishing registration.
	 * 
	 * @param email
	 * @param nickname
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public void processSignup(String email, String nickname, String firstname, String lastname,
			String password) {
		Random random = new Random(System.currentTimeMillis());
		String activationCode = String.valueOf(random.nextInt());

		AppUser user = new AppUser();
		user.setActivationDate(null);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setName(firstname);
		user.setSurname(lastname);
		user.setPassword(messageDigestPasswordEncoder.encodePassword(password, null));
		user.setActivationCode(activationCode);
		user.setEnabled(false);
		user.setLocked(false);
		user.persist();

		sendMessage(
				user.getEmail(),
				"User Activaton",
				"Hello " + user.getName() + ",\n. You had registered with us. Please click on this link to "
						+ "activate your account - <a href=\"" + APP_HOST + "/signup/?activate&email="
						+ user.getEmail() + "&code=" + activationCode + "\">Activate Link</a>.");
	}

	private void sendMessage(String mailTo, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(mailTo);
		simpleMailMessage.setFrom(EMAIL_FROM);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		mailSender.send(simpleMailMessage);
	}

}
