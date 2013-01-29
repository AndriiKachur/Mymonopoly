package net.mymonopoly.engine.dto;

import flexjson.JSONSerializer;

/**
 * Represents game field on the board.
 * 
 * @author Andrey K.
 * 
 */
public class FieldInfo {

	private String name;
	private int cost;
	private int rent;
	private int upgradeCost;
	private int upgradeRent;
	private int mortage;
	private String owner;

	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public int getUpgradeRent() {
		return upgradeRent;
	}

	public void setUpgradeRent(int upgradeRent) {
		this.upgradeRent = upgradeRent;
	}

	public int getMortage() {
		return mortage;
	}

	public void setMortage(int mortage) {
		this.mortage = mortage;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
