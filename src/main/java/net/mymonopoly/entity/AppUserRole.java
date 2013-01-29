package net.mymonopoly.entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAppUserRolesByAppuser" })
public class AppUserRole {

    @ManyToOne
    @NotNull
    private AppUser appuser;

    @ManyToOne
    @NotNull
    private AppRole approle;
}
