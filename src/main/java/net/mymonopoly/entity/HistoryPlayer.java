package net.mymonopoly.entity;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class HistoryPlayer {

    private String name;

    private Long playerId;

    private int money;

    private int capital;

    private Boolean kicked;
}
