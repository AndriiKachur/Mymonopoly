package net.mymonopoly.engine.dto;

/**
 * Represents player's estate.
 * 
 * @author Andrey K.
 * 
 */
public class Estate {

	private long id;
	private int level;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
