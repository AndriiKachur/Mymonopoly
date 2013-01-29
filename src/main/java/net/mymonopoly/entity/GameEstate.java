package net.mymonopoly.entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import net.mymonopoly.engine.dto.Player;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class GameEstate {

    @NotNull
    private String colour;

    @Min(0L)
    private short cost;

    @Min(0L)
    private short rent;

    @Min(0L)
    private short house1;

    @Min(0L)
    private short house2;

    @Min(0L)
    private short house3;

    @Min(0L)
    private short house4;

    @Min(0L)
    private short house5;

    @Min(0L)
    private short buildCost;

    @Min(0L)
    private short mortage;

    @ManyToOne
    private GameTheme theme;

    @NotNull
    private String name;

    @Min(0L)
    private short houseCost;

    private transient int upgradeLevel;
    
    private transient Player owner;
    
    private transient boolean mortaged = false;

	public boolean isMortaged() {
		return mortaged;
	}

	public void setMortaged(boolean mortaged) {
		this.mortaged = mortaged;
	}

	public int getUpgradeLevel() {
		return upgradeLevel;
	}

	public void setUpgradeLevel(int upgradeLevel) {
		this.upgradeLevel = upgradeLevel;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
