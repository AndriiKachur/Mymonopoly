package net.mymonopoly.entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findGameChancesByTheme" })
public class GameChance {

    @NotNull
    private String message;

    @Min(0L)
    @Max(3L)
    private short stopTurn;

    private short moneyDiff;

    private short move;

    private String moveToClass;

    @ManyToOne
    private GameTheme theme;
}
