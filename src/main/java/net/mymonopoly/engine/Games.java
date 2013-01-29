package net.mymonopoly.engine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.mymonopoly.engine.dto.Game;

/**
 * Static class that holds games in memory.
 * 
 * @author Andrey K.
 * 
 */
public final class Games {

	/**
	 * Started games.
	 */
	public static final ConcurrentMap<String, Game> GAMES = new ConcurrentHashMap<String, Game>();

	/**
	 * Not started games (e.g. waiting for other players).
	 */
	public static final ConcurrentMap<String, Game> NOT_STARTED_GAMES = new ConcurrentHashMap<String, Game>();

	private Games() {
	}
}
