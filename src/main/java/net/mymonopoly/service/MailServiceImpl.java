package net.mymonopoly.service;

import net.mymonopoly.entity.AppUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Mail service used to send email.
 * 
 * @author Andrey K.
 * 
 */
@Service
public class MailServiceImpl {
	private static final Log LOGGER = LogFactory.getLog(MailServiceImpl.class);

	@Value("${email.from}")
	private String EMAIL_FROM;
	@Value("${email.apphost}")
	private String APP_HOST;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Sends a new user's password to the email.
	 * @param user
	 * @param newPassword
	 */
	public void sendNewPasswordMail(AppUser user, String newPassword) {
		sendMessage(user.getEmail(), "Password Recovery", "Hi " + user.getName()
				+ ",<br/> You had requested for password recovery. Your password is: " + newPassword
				+ ".<br/> Please change it.");
	}

	/**
	 * Sends a welcome mail to a newly registered user.
	 * @param email
	 * @param user
	 */
	public void sendRegistrationMail(String email, AppUser user) {
		sendMessage(user.getEmail(), "User Activaton",
				"Hello " + user.getName() + ",<br/> You had registered with us. Please click on this link to "
						+ "activate your account - <a href=\"" + APP_HOST + "/signup/?activate&email="
						+ user.getEmail() + "&code=" + user.getActivationCode() + "\">Activate Link</a>.");
	}

	private void sendMessage(String mailTo, String subject, String message) {
		MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage());
		try {
			helper.setFrom(EMAIL_FROM);
			helper.setTo(mailTo);
			helper.setSubject(subject);
			helper.setText(message, true);
			mailSender.send(helper.getMimeMessage());
		} catch (Exception e) {
			LOGGER.warn("", e);
		}
	}

}
