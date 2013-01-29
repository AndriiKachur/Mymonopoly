package net.mymonopoly.engine.dto;

import java.util.List;

/**
 * Represents player for game processing.
 * 
 * @author Andrey K.
 * 
 */
public class Player {

	private long id; // user id
	private String name; // player name
	private byte position; // field position
	private int money; // money on the hands
	private int capital; // money in estate
	private boolean wasMoveSkip = false;
	private List<Estate> estate;
	private boolean isLeave = false;
	private boolean wasMove = false;
	private boolean endTurn = false;
	private int moveToKick = 1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getPosition() {
		return position;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public boolean isWasMoveSkip() {
		return wasMoveSkip;
	}

	public void setWasMoveSkip(boolean wasMoveSkip) {
		this.wasMoveSkip = wasMoveSkip;
	}

	public List<Estate> getEstate() {
		return estate;
	}

	public void setEstate(List<Estate> estate) {
		this.estate = estate;
	}

	public boolean isLeave() {
		return isLeave;
	}

	public void setLeave(boolean isLeave) {
		this.isLeave = isLeave;
	}

	public boolean isWasMove() {
		return wasMove;
	}

	public void setWasMove(boolean wasMove) {
		this.wasMove = wasMove;
	}

	public boolean isEndTurn() {
		return endTurn;
	}

	public void setEndTurn(boolean endTurn) {
		this.endTurn = endTurn;
	}

	public int getMoveToKick() {
		return moveToKick;
	}

	public void setMoveToKick(int moveToKick) {
		this.moveToKick = moveToKick;
	}

}
