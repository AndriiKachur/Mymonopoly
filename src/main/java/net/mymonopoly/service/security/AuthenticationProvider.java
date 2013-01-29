package net.mymonopoly.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import net.mymonopoly.entity.AppUser;
import net.mymonopoly.entity.AppUserRole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Authentication provider for application.
 * @author Andrey K.
 *
 */
@Service("databaseAuthenticationProvider")
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(AuthenticationProvider.class);

	private String adminUser;
	private String adminPassword;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		return;

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException("Please enter password");
		}
		String encryptedPassword = messageDigestPasswordEncoder.encodePassword(password, null);

		String expectedPassword = null;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		AppUser targetUser = new AppUser();
		if (adminUser.equals(username)) {
			expectedPassword = adminPassword;
			if (!encryptedPassword.equals(expectedPassword)) {
				throw new BadCredentialsException("Invalid password");
			}
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			try {
				TypedQuery<AppUser> query = AppUser.findAppUsersByEmailEquals(username);
				targetUser = (AppUser) query.getSingleResult();
				expectedPassword = targetUser.getPassword();

				if (targetUser.getEnabled() == null || !targetUser.getEnabled()
						|| targetUser.getLocked() == null || targetUser.getLocked()) {
					throw new BadCredentialsException("This user can't log in.");
				}
				if (!encryptedPassword.equals(expectedPassword)) {
					throw new BadCredentialsException("Invalid Password");
				}

				TypedQuery<AppUserRole> roleQuery = AppUserRole.findAppUserRolesByAppuser(targetUser);
				List<AppUserRole> userRoles = roleQuery.getResultList();
				for (AppUserRole userRole : userRoles) {
					authorities.add(new SimpleGrantedAuthority(userRole.getApprole().getName()));
				}
			} catch (EmptyResultDataAccessException e) {
				throw new BadCredentialsException("Invalid user");
			} catch (EntityNotFoundException e) {
				throw new BadCredentialsException("Invalid user");
			} catch (NonUniqueResultException e) {
				throw new BadCredentialsException("Non-unique user, contact administrator");
			}
		}
		return new User(username, password,
				targetUser.getEnabled() != null ? targetUser.getEnabled() : false, true, true,
				targetUser.getLocked() != null ? !targetUser.getLocked() : true, authorities);
	}
}
