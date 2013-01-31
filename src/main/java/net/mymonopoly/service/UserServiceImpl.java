package net.mymonopoly.service;

import java.util.Random;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.mymonopoly.entity.AppUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service.
 * 
 * @author Andrey K.
 * 
 */
@Service
public class UserServiceImpl {
	private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
	@Autowired
	private MailServiceImpl mailService;

	/**
	 * Sends email with a new password.
	 * 
	 * @param email
	 */
	public void processForgotPassword(String email) {
		TypedQuery<AppUser> userQuery = AppUser.findAppUsersByEmailEquals(email);
		if (null != userQuery && userQuery.getMaxResults() > 0) {
			AppUser user = null;
			try {
				user = userQuery.getSingleResult();
			} catch (NoResultException ignored) {
			} catch (RuntimeException re) {
				LOGGER.warn("", re);
				return;
			}
			Random random = new Random(System.currentTimeMillis());
			String newPassword = "pass" + random.nextLong() + "word";
			user.setPassword(messageDigestPasswordEncoder.encodePassword(newPassword, null));
			try {
				user.merge();
			} catch (RuntimeException re) {
				LOGGER.warn("", re);
			}
			mailService.sendNewPasswordMail(user, newPassword);
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
		try {
			user.persist();
		} catch (RuntimeException re) {
			LOGGER.warn("", re);
		}
		mailService.sendRegistrationMail(email, user);
	}

}
