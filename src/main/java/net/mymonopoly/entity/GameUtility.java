package net.mymonopoly.entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import net.mymonopoly.engine.dto.Player;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class GameUtility {

    @Min(0L)
    private short cost;

    @Min(0L)
    private byte rentMul1;

    @Min(0L)
    private byte rentMul2;

    @Min(0L)
    private short mortage;

    @ManyToOne
    private GameTheme theme;

    private String name;
    
    private transient Player owner;
    
    private transient boolean mortaged;

	public boolean isMortaged() {
		return mortaged;
	}

	public void setMortaged(boolean mortaged) {
		this.mortaged = mortaged;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
