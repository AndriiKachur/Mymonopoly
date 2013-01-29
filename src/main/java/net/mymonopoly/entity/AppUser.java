package net.mymonopoly.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAppUsersByEmailEquals", "findAppUsersByActivationCodeAndEmail" })
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

	@NotNull
    private String name;

    @NotNull
    private String surname;
    
    @NotNull
    private String nickname;

    @NotNull
    @Column(unique = true)
    @Size(min = 3)
    private String email;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date activationDate;

    private Boolean locked;

    private Boolean enabled;

    private String activationCode;
}
