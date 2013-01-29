package net.mymonopoly.engine.utils;

/**
 * Session Name Resolver class. <br/>
 * Holds constants for object in HttpSession.
 * 
 * @author Andrey K.
 * 
 */
public class SNM {
	/**
	 * Game object.
	 */
	public static final String GAME = "GAME";
	/**
	 * Player object.
	 */
	public static final String PLAYER = "PLAYER";
	/**
	 * AppUser object.
	 */
	public static final String USER = "USER";
	/**
	 * Nickname string.
	 */
	public static final String NICKNAME = "NICKNAME";
	/**
	 * Boolean object - user is creator of the game.
	 */
	public static final String CREATOR = "CREATOR";
	/**
	 * Long object (date in milliseconds) since last chat was read.
	 */
	public static final String LAST_CHAT_GET = "CHAT_FROM";

}
