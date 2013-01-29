package net.mymonopoly.engine.utils;

import flexjson.JSONSerializer;

/**
 * Static utility class for JSON responses.
 * 
 * @author Andrey K.
 * 
 */
public final class JSON {

	private JSON() {
	}

	/**
	 * JSON. "status": false.
	 */
	public static final String ERROR = "{\"status\":false}";

	/**
	 * JSON. "status": false, "ended":true.
	 */
	public static final String GAME_ENDED = "{\"status\":false, \"ended\": true}";

	/**
	 * JSON. "status": true.
	 */
	public static final String OK = "{\"status\":true}";

	/**
	 * JSON status - true and "code" - game code.
	 * 
	 * @param code
	 * @return
	 */
	public static String OKandCODE(String code) {
		return "{\"status\":true,\"code\":\"" + code + "\"}";
	}

	/**
	 * JSON. status - true, messages - HTML formatted chat.
	 * 
	 * @param messages
	 * @return
	 */
	public static String chatOK(String messages) {
		return "{\"status\":true,\"messages\":\"" + messages + "\"}";
	}

	/**
	 * Serializes object to JSON excluding class field.
	 * 
	 * @param o
	 * @return
	 */
	public static String serialize(Object o) {
		return new JSONSerializer().exclude("*.class").serialize(o);
	}

	/**
	 * Deep serialization of object excluding class field.
	 * 
	 * @param o
	 * @return
	 */
	public static String deepSerialize(Object o) {
		return new JSONSerializer().exclude("*.class").deepSerialize(o);
	}
}
