package net.mymonopoly.engine;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import net.mymonopoly.engine.dto.Game;
import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameEstate;
import net.mymonopoly.entity.HistoryGame;
import net.mymonopoly.entity.HistoryPlayer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Main component for processing games. <br/>
 * In separate thread processes game info: players move, game statistics.
 * 
 * @author Andrey K.
 * 
 */
@Component
public final class GameThread implements Runnable, InitializingBean, DisposableBean {

	private static final Log LOG = LogFactory.getLog(GameThread.class);
	private Thread thread;

	public void run() {

		while (true && !thread.isInterrupted()) {
			long startTime = new Date().getTime();
			int removed = removeNotStartedGames();
			long gamesCount = 0;
			
			// ONE GAME PROCESS:: START
			for (Entry<String, Game> entry : Games.GAMES.entrySet()) {
				Game game = entry.getValue();
				Player currentPlayer = game.getCurrentPlayer();
				if (currentPlayer.isLeave()) {
					if (isGameFinished(game)) {
						endGame(game);
					} else {
						giveMoveToNextPlayer(game);
					}
					continue;
				}
				// time to move is over
				if (new Date().getTime() - game.getLastMoveTime() > game.getOptions().getTimeLimit()) {
					if (currentPlayer.isWasMoveSkip()) {
						currentPlayer.setLeave(true);
						Chats.systemMessage(game.getCode(), new Date().getTime(), currentPlayer.getName()
								+ " leaves the game");
						giveMoveToNextPlayer(game);
					} else {
						currentPlayer.setWasMoveSkip(true);
						Chats.systemMessage(game.getCode(), new Date().getTime(), currentPlayer.getName()
								+ " skipped his turn");
						giveMoveToNextPlayer(game);
						continue;
					}
				} else {
					// PLAYERS MOVE::: START::
					if (!currentPlayer.isWasMove() && currentPlayer.getMoney() > 0) {
						movePlayer(game);
					} else if (currentPlayer.getMoney() < 0 && currentPlayer.getMoveToKick() > 0) {
						Chats.writeToChat(game.getCode(), "'s balance is negative. Player will be kicked in "
								+ currentPlayer.getMoveToKick() + " turn.");
						currentPlayer.setMoveToKick(currentPlayer.getMoveToKick() - 1);
						currentPlayer.setWasMove(true);
					} else if (currentPlayer.getMoney() < 0 && currentPlayer.getMoveToKick() <= 0) {
						Chats.writeToChat(game.getCode(), " is a bankrupt.");
						currentPlayer.setLeave(true);
					}
					if (currentPlayer.isEndTurn()) {
						giveMoveToNextPlayer(game);
					}
					// PLAYERS MOVE::: END::
				}
				++gamesCount;
			}
			// ONE GAME PROCESS:: END
			
			Chats.cleanup();
			long endTime = new Date().getTime();
			if (gamesCount > 0 || removed > 0) {
				LOG.info("worked: " + (endTime - startTime) + "ms, processed: " + gamesCount
						+ ", removedNotStarted: " + removed);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ie) {
				LOG.error("GAME THREAD WAS INTERRUPTED! ", ie);
				thread.interrupt();
			}
		}

	}

	private void giveMoveToNextPlayer(Game game) {
		game.getCurrentPlayer().setEndTurn(false);
		game.getCurrentPlayer().setWasMove(false);
		int nextPos = game.getPlayers().indexOf(game.getCurrentPlayer()) + 1;
		if (nextPos >= game.getPlayers().size()) {
			nextPos = 0;
		}
		game.setCurrentPlayer(game.getPlayers().get(nextPos));
		game.setLastMoveTime(new Date().getTime());
	}

	private boolean isGameFinished(Game game) {
		int play = 0;
		for (Player p : game.getPlayers()) {
			if (!p.isLeave() && p.getMoney() > 0) {
				++play;
			}
		}
		if (play > 1) {
			return false;
		}
		return true;
	}

	private void endGame(Game game) {
		createGameStatistics(game);
		Games.GAMES.remove(game.getCode());
	}

	private void movePlayer(Game game) {
		Player currentPlayer = game.getCurrentPlayer();
		Random rand = new Random();
		int move = rand.nextInt(13);
		while (move <= 1) {
			move = rand.nextInt(13);
		}
		Chats.writeToChat(game.getCode(), " roll dices for " + move);
		currentPlayer.setWasMove(true);
		game.setLastMove(move);
		int position = move + currentPlayer.getPosition();
		if (position >= game.getBoard().size()) {
			int moneyGet = 200;
			// Give money to player for ending a circle
			currentPlayer.setMoney(currentPlayer.getMoney() + moneyGet);
			Chats.writeToChat(game.getCode(), currentPlayer.getName() + " gets $" + moneyGet + " gold");
			position -= game.getBoard().size();
		}
		currentPlayer.setPosition((byte) position);
		Object field = game.getBoard().get(position);
		// process field if chance, chest or ...
		if (field instanceof GameChance) {
			/*
			 * List<GameChance> chances = GameChance.findGameChancesByTheme(
			 * GameTheme.findGameThemesByNameEquals(
			 * game.getOptions().getTheme()).getSingleResult()).getResultList();
			 * GameChance chance = chances.get(rand.nextInt(chances.size()));
			 */
		} else if (field instanceof GameEstate) {
			GameEstate estate = (GameEstate) field;
			if (estate.getOwner() != null && estate.getOwner().getId() != currentPlayer.getId()
					&& !estate.isMortaged()) {
				int pay = 0;
				switch (estate.getUpgradeLevel()) {
				case 0:
					pay = estate.getRent();
					break;
				case 1:
					pay = estate.getHouse1();
					break;
				case 2:
					pay = estate.getHouse2();
					break;
				case 3:
					pay = estate.getHouse3();
					break;
				case 4:
					pay = estate.getHouse4();
					break;
				case 5:
					pay = estate.getHouse5();
					break;
				}
				currentPlayer.setMoney(currentPlayer.getMoney() - pay);
				Chats.writeToChat(game.getCode(), " paid $" + pay + " rent to " + estate.getOwner().getName());
			}
		}
	}

	/**
	 * Store game statistics in database
	 * 
	 * @param game
	 *            - ended game
	 */
	private void createGameStatistics(Game game) {
		// TODO: write game statistics
		HistoryGame history = new HistoryGame();
		history.setCode(game.getCode());
		history.setEndTime(new Date());
		history.setStartTime(game.getStartTime());
		history.setName(game.getOptions().getGameName());
		for (Player p : game.getPlayers()) {
			HistoryPlayer historyPlayer = new HistoryPlayer();
			historyPlayer.setCapital(p.getCapital());
			historyPlayer.setPlayerId(p.getId());
			historyPlayer.setKicked(p.getMoveToKick() > 0 ? false : true);
			historyPlayer.setMoney(p.getMoney());
			historyPlayer.setName(p.getName());
			history.getPlayers().add(historyPlayer);
		}
		history.persist();
	}

	/**
	 * Remove not started games with 0 players in list.
	 * 
	 * @return number of removed games
	 */
	private int removeNotStartedGames() {
		int removed = 0;
		Iterator<Entry<String, Game>> i = Games.NOT_STARTED_GAMES.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, Game> entry = i.next();
			if (entry.getValue().getPlayers().isEmpty()) {
				i.remove();
				removed++;
			}
		}
		return removed;
	}

	@Override
	public void destroy() throws Exception {
		LOG.info("Shutting down game thread");
		thread.interrupt();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("Game thread is started");
		thread = new Thread(this);
		thread.setName("Game_Thread");
		thread.start();
	}

}
