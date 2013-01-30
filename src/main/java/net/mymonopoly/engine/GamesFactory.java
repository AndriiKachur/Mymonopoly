package net.mymonopoly.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import net.mymonopoly.engine.dto.Game;
import net.mymonopoly.engine.dto.GameOptions;
import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameChest;
import net.mymonopoly.entity.GameEstate;
import net.mymonopoly.entity.GameRailroad;
import net.mymonopoly.entity.GameUtility;

/**
 * Game factory component. <br/>
 * Use this bean to create game objects.
 * 
 * @author Andrey K.
 * 
 */
@Component
public class GamesFactory {
	
	@Autowired
	private GameContext games;

	/**
	 * Create game based on options.
	 * 
	 * @param o
	 *            - game options
	 * @return - game object
	 */
	public Game getGame(GameOptions o) {
		Game game = new Game();
		game.setPlayers(new ArrayList<Player>());
		game.setStarted(false);
		game.setOptions(o);
		if (o.getTimeLimit() > 120) {
			o.setTimeLimit(120 * 1000);
		} else if (o.getTimeLimit() < 30) {
			o.setTimeLimit(30 * 1000);
		} else {
			o.setTimeLimit(o.getTimeLimit() * 1000);
		}
		Random random = new Random();
		game.setCode(new Md5PasswordEncoder().encodePassword(String.valueOf(random.nextLong()),
				random.nextFloat()));

		List<Object> board = new ArrayList<Object>(40);
		board.add(GameChance.findGameChance(1l)); // fixed start point
		board.add(GameEstate.findGameEstate(1l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(2l));
		board.add(GameChance.findGameChance(2l)); // fixed tax
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(3l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(4l));
		board.add(GameEstate.findGameEstate(5l));
		board.add(GameChance.findGameChance(3l)); // fixed jail
		board.add(GameEstate.findGameEstate(6l));
		board.add(GameUtility.findGameUtility(1l));
		board.add(GameEstate.findGameEstate(7l));
		board.add(GameEstate.findGameEstate(8l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(9l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(10l));
		board.add(GameEstate.findGameEstate(11l));
		board.add(GameChance.findGameChance(4l)); // parking fixed
		board.add(GameEstate.findGameEstate(12l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(13l));
		board.add(GameEstate.findGameEstate(14l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(15l));
		board.add(GameEstate.findGameEstate(16l));
		board.add(GameUtility.findGameUtility(2l));
		board.add(GameEstate.findGameEstate(17l));
		board.add(GameChance.findGameChance(5l)); // fixed go to jail
		board.add(GameEstate.findGameEstate(18l));
		board.add(GameEstate.findGameEstate(19l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(20l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(21l));
		board.add(GameChance.findGameChance(6l));// fixed pay 75$
		board.add(GameEstate.findGameEstate(22l));

		game.setBoard(board);
		
		games.getNotStartedGames().put(game.getCode(), game);
		return game;
	}

	/**
	 * Get mock game for testing purpose.
	 * 
	 * @param o
	 *            - game options.
	 * @return game object
	 */
	public Game getTestGame(GameOptions o) {
		Game game = new Game();
		game.setPlayers(new ArrayList<Player>());
		game.setStarted(false);
		game.setOptions(o);
		if (o.getTimeLimit() > 120) {
			o.setTimeLimit(120 * 1000);
		} else if (o.getTimeLimit() < 30) {
			o.setTimeLimit(30 * 1000);
		} else {
			o.setTimeLimit(o.getTimeLimit() * 1000);
		}
		Random random = new Random();
		game.setCode(new Md5PasswordEncoder().encodePassword(String.valueOf(random.nextLong()),
				random.nextFloat()));

		List<Object> board = new ArrayList<Object>(40);
		board.add(GameChance.findGameChance(1l)); // fixed start point
		board.add(GameEstate.findGameEstate(1l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(2l));
		board.add(GameChance.findGameChance(2l)); // fixed tax
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(3l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(4l));
		board.add(GameEstate.findGameEstate(5l));
		board.add(GameChance.findGameChance(3l)); // fixed jail
		board.add(GameEstate.findGameEstate(6l));
		board.add(GameUtility.findGameUtility(1l));
		board.add(GameEstate.findGameEstate(7l));
		board.add(GameEstate.findGameEstate(8l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(9l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(10l));
		board.add(GameEstate.findGameEstate(11l));
		board.add(GameChance.findGameChance(4l)); // parking fixed
		board.add(GameEstate.findGameEstate(12l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(13l));
		board.add(GameEstate.findGameEstate(14l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameEstate.findGameEstate(15l));
		board.add(GameEstate.findGameEstate(16l));
		board.add(GameUtility.findGameUtility(2l));
		board.add(GameEstate.findGameEstate(17l));
		board.add(GameChance.findGameChance(5l)); // fixed go to jail
		board.add(GameEstate.findGameEstate(18l));
		board.add(GameEstate.findGameEstate(19l));
		board.add(GameChest.findGameChest(1l));
		board.add(GameEstate.findGameEstate(20l));
		board.add(GameRailroad.findGameRailroad(1l));
		board.add(GameChance.findGameChance(7l));
		board.add(GameEstate.findGameEstate(21l));
		board.add(GameChance.findGameChance(6l));// fixed pay 75$
		board.add(GameEstate.findGameEstate(22l));

		game.setBoard(board);

		return game;
	}

}
