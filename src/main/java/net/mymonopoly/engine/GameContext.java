package net.mymonopoly.engine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import net.mymonopoly.engine.dto.Game;

/**
 * Component that holds games in memory.
 * 
 * @author Andrey K.
 * 
 */
@Component
public class GameContext {

	private ConcurrentMap<String, Game> games = new ConcurrentHashMap<String, Game>();

	private ConcurrentMap<String, Game> notStartedGames = new ConcurrentHashMap<String, Game>();

	/**
	 * Started games.
	 */
	public ConcurrentMap<String, Game> getGames() {
		return games;
	}

	/**
	 * Not started games (e.g. waiting for other players).
	 */
	public ConcurrentMap<String, Game> getNotStartedGames() {
		return notStartedGames;
	}

}
