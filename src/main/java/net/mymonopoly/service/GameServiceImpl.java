package net.mymonopoly.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import net.mymonopoly.engine.ChatContext;
import net.mymonopoly.engine.GameContext;
import net.mymonopoly.engine.GamesFactory;
import net.mymonopoly.engine.dto.FieldInfo;
import net.mymonopoly.engine.dto.Game;
import net.mymonopoly.engine.dto.GameOptions;
import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.engine.utils.JSON;
import net.mymonopoly.engine.utils.SNM;
import net.mymonopoly.entity.AppUser;
import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameChest;
import net.mymonopoly.entity.GameEstate;
import net.mymonopoly.entity.GameRailroad;
import net.mymonopoly.entity.GameUtility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Game service. Used for all player <--> game interaction.
 * 
 * @author Andrey K.
 * 
 */
@Service
public class GameServiceImpl {
	@Autowired
	private ChatContext chatContext;
	@Autowired
	private GameContext games;
	@Autowired
	private GamesFactory gamesFactory;

	/**
	 * Get STARTED game object.
	 * 
	 * @param code
	 * @return game or null
	 */
	public Game getGame(String code) {
		if (code == null) {
			return null;
		}
		Game game = games.getGames().get(code);
		return game;
	}

	/**
	 * Get NOT STARTED (waiting for start) game object.
	 * 
	 * @param code
	 * @return game or null
	 */
	public Game getNotStartedGame(String code) {
		if (code == null) {
			return null;
		}
		Game game = games.getNotStartedGames().get(code);
		return game;
	}

	/**
	 * Checks if game is full or can accept more players.
	 * 
	 * @param code
	 * @return true if game is not full
	 */
	public boolean canIJoinGame(String code) {
		Game game = getNotStartedGame(code);
		return game != null && game.getPlayers().size() <= game.getOptions().getPlayersAmount();
	}

	/**
	 * Checks if user is creator of the game and game is not started and all
	 * required options are ok.
	 * 
	 * @param session
	 * @return
	 */
	public boolean canIStartGame(HttpSession session) {
		Game game = getNotStartedGame((String) session.getAttribute(SNM.GAME));
		Boolean creator = (Boolean) session.getAttribute(SNM.CREATOR);
		// TODO: for testing MIN PLAYERS IS 2
		return creator != null && game != null && game.getPlayers().size() >= 2
				&& game.getPlayers().size() < 7 && !game.isStarted();
	}

	/**
	 * End user's turn.
	 * 
	 * @param session
	 * @return
	 */
	public boolean endTurn(HttpSession session) {
		if (!checkPlayer(session)) {
			return false;
		}
		Game game = getGame((String) session.getAttribute(SNM.GAME));
		game.getCurrentPlayer().setEndTurn(true);
		game.getCurrentPlayer().setWasMoveSkip(false);
		return true;
	}

	/**
	 * Create not started game.
	 * 
	 * @param session
	 * @param options
	 * @return
	 */
	public String createGame(HttpSession session, GameOptions options) {
		Player player = new Player();
		AppUser user = (AppUser) session.getAttribute(SNM.USER);
		player.setName(user.getNickname());
		player.setId(user.getId());
		player.setMoney(1500);

		if (session.getAttribute(SNM.GAME) != null) {
			session.removeAttribute(SNM.GAME);
			session.removeAttribute(SNM.PLAYER);
			session.removeAttribute(SNM.CREATOR);
		}
		Game game = gamesFactory.getGame(options);
		game.getPlayers().add(player);
		session.setAttribute(SNM.GAME, game.getCode());
		session.setAttribute(SNM.PLAYER, player);
		session.setAttribute(SNM.CREATOR, Boolean.TRUE);
		return game.getCode();
	}

	/**
	 * Returns JSON of deep serialized game object or JSON.GAME_ENDED.
	 * 
	 * @param code
	 * @return
	 */
	public String getGameState(String code) {
		Game game = getGame(code);
		if (game == null) {
			return JSON.GAME_ENDED;
		}
		return JSON.deepSerialize(game);
	}

	/**
	 * Player leaves the game.
	 * 
	 * @param session
	 */
	public void leaveGame(HttpSession session) {
		String code = (String) session.getAttribute(SNM.GAME);
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = null;
		if (!StringUtils.isBlank(code) && player != null) {
			game = getGame(code);
			if (game != null) {
				for (Player p1 : game.getPlayers()) {
					if (p1.getId() == player.getId()) {
						p1.setLeave(true);
						break;
					}
				}
			} else {
				game = getNotStartedGame(code);
				if (game != null) {
					for (Iterator<Player> it = game.getPlayers().iterator(); it.hasNext();) {
						if (player.getId() == it.next().getId()) {
							it.remove();
							break;
						}
					}
				}
			}
			chatContext.systemMessage(code, new Date().getTime(), player.getName() + " leaves the game");
		}
		session.removeAttribute(SNM.GAME);
		session.removeAttribute(SNM.PLAYER);
		session.removeAttribute(SNM.CREATOR);
	}

	/**
	 * Player joins not started game.
	 * 
	 * @param session
	 * @param code
	 * @return
	 */
	public boolean joinGame(HttpSession session, String code) {
		session.setAttribute(SNM.GAME, code);
		Player player = new Player();
		AppUser user = (AppUser) session.getAttribute(SNM.USER);
		player.setName(user.getNickname());
		player.setId(user.getId());
		player.setMoney(1500);

		getGamePlayers(code).add(player);
		session.setAttribute(SNM.PLAYER, player);
		return true;
	}

	/**
	 * Player starts his game.
	 * 
	 * @param session
	 * @return
	 */
	public boolean startGame(HttpSession session) {
		String gameCode = (String) session.getAttribute(SNM.GAME);
		Game game = getNotStartedGame(gameCode);
		games.getNotStartedGames().remove(gameCode);
		game.setStarted(true);
		int n = new Random().nextInt(game.getPlayers().size());
		game.setCurrentPlayer(game.getPlayers().get(n));
		game.setStartTime(new Date());
		games.getGames().put(game.getCode(), game);
		return true;
	}

	/**
	 * Returns list of not started games.
	 */
	public List<Game> getNotStartedGames() {
		List<Game> result = new ArrayList<Game>(games.getNotStartedGames().values());
		return result;
	}

	/**
	 * Returns list of players in specified game.
	 * 
	 * @param code
	 * @return empty or filled with Players list.
	 */
	public List<Player> getGamePlayers(String code) {
		if (games.getGames().containsKey(code)) {
			return getGame(code).getPlayers();
		} else if (games.getGames().containsKey(code)) {
			return games.getNotStartedGames().get(code).getPlayers();
		}
		return Collections.emptyList();
	}

	/**
	 * Returns field info for player's game and cell.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public FieldInfo getFieldInfo(HttpSession session, Integer cell) {
		FieldInfo fi = new FieldInfo();
		String code = (String) session.getAttribute(SNM.GAME);
		if (code != null) {
			Game game = getGame(code);
			if (game != null) {
				Object f = game.getBoard().get(cell);
				if (f instanceof GameEstate) {
					GameEstate ge = (GameEstate) f;
					fi.setName(ge.getName());
					if (ge.getOwner() != null) {
						fi.setOwner(ge.getOwner().getName());
					}
					fi.setCost(ge.getCost());
					fi.setMortage(ge.getMortage());
					fi.setUpgradeCost(ge.getBuildCost());
					int rent = 0;
					int upgradeRent = 0;
					switch (ge.getUpgradeLevel()) {
					case 0:
						rent = ge.getRent();
						upgradeRent = ge.getHouse1();
						break;
					case 1:
						rent = ge.getHouse1();
						upgradeRent = ge.getHouse2();
						break;
					case 2:
						rent = ge.getHouse2();
						upgradeRent = ge.getHouse3();
						break;
					case 3:
						rent = ge.getHouse3();
						upgradeRent = ge.getHouse4();
						break;
					case 4:
						rent = ge.getHouse4();
						upgradeRent = ge.getHouse5();
						break;
					case 5:
						rent = ge.getHouse5();
						break;
					}
					fi.setRent(rent);
					fi.setUpgradeRent(upgradeRent);
				} else if (f instanceof GameRailroad) {
					GameRailroad gr = (GameRailroad) f;
					fi.setName(gr.getName());
					fi.setMortage(gr.getMortage());
					fi.setCost(gr.getCost());
					if (gr.getOwner() != null) {
						fi.setOwner(gr.getOwner().getName());
					}
				} else if (f instanceof GameUtility) {
					GameUtility gu = (GameUtility) f;
					fi.setName(gu.getName());
					fi.setMortage(gu.getMortage());
					if (gu.getOwner() != null) {
						fi.setOwner(gu.getOwner().getName());
					}
					fi.setCost(fi.getCost());
				} else if (f instanceof GameChance) {
					// TODO implement chances
					// GameChance gc = (GameChance) f;
					fi.setName("Chance");
				} else if (f instanceof GameChest) {
					// TODO implement game chests
					// GameChest gc = (GameChest) f;
					fi.setName("Chest");
				}
			}

		}

		return fi;
	}

	/**
	 * Checks if game exists and this is a player of this game or removes
	 * session attributes.
	 * 
	 * @param session
	 * @return
	 */
	public boolean checkPlayer(HttpSession session) {
		String code = (String) session.getAttribute(SNM.GAME);
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (StringUtils.isBlank(code) || player == null) {
			session.removeAttribute(SNM.GAME);
			session.removeAttribute(SNM.PLAYER);
			session.removeAttribute(SNM.CREATOR);
			return false;
		}
		Game game = (Game) games.getGames().get(code);
		if (game == null) {
			game = games.getNotStartedGames().get(code);
		}
		if (game == null) {
			return false;
		} else {
			boolean isPlayer = false;
			for (Player p : game.getPlayers()) {
				if (p.getId() == player.getId() && !p.isLeave()) {
					isPlayer = true;
					break;
				}
			}
			return isPlayer;
		}
	}

	/**
	 * Checks if player can has turn.
	 * 
	 * @param session
	 * @return
	 */
	public boolean isMyTurn(HttpSession session) {
		String code = (String) session.getAttribute(SNM.GAME);
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (code == null || player == null) {
			return false;
		}
		Game game = (Game) games.getGames().get(code);
		if (game != null && game.getCurrentPlayer() != null
				&& game.getCurrentPlayer().getId() == player.getId()) {
			return true;
		}
		return false;
	}

	/**
	 * Try to buy something at the state.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public boolean buyCell(HttpSession session, int cell) {
		if (!isMyTurn(session)) {
			return false;
		}
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = (Game) games.getGames().get(session.getAttribute(SNM.GAME).toString());
		Object field = game.getBoard().get(cell);
		if (field instanceof GameChance || field instanceof GameChest || player.getPosition() != cell) {
			return false;
		} else if (field instanceof GameEstate) {
			GameEstate ge = (GameEstate) field;
			if (ge.getOwner() == null && ge.getCost() <= player.getMoney()) {
				player.setMoney(player.getMoney() - ge.getCost());
				player.setCapital(player.getCapital() + ge.getCost());
				ge.setOwner(player);
				chatContext.writeToChat(game.getCode(), player.getName() + " bought " + ge.getName());
				return true;
			}
		} else if (field instanceof GameRailroad) {
			GameRailroad gr = (GameRailroad) field;
			if (gr.getOwner() == null && gr.getCost() <= player.getMoney()) {
				player.setMoney(player.getMoney() - gr.getCost());
				player.setCapital(player.getCapital() + gr.getCost());
				gr.setOwner(player);
				chatContext.writeToChat(game.getCode(), player.getName() + " bought " + gr.getName());
				return true;
			}
		} else if (field instanceof GameUtility) {
			GameUtility gu = (GameUtility) field;
			if (gu.getOwner() == null && gu.getCost() <= player.getMoney()) {
				player.setMoney(player.getMoney() - gu.getCost());
				player.setCapital(player.getCapital() + gu.getCost());
				gu.setOwner(player);
				chatContext.writeToChat(game.getCode(), player.getName() + " bought " + gu.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Try to sell your estate.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public boolean sellCell(HttpSession session, int cell) {
		// TODO: AUCTION
		if (!isMyTurn(session)) {
			return false;
		}
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = (Game) games.getGames().get(session.getAttribute(SNM.GAME).toString());
		Object field = game.getBoard().get(cell);
		if (field instanceof GameChance || field instanceof GameChest) {
			return false;
		} else if (field instanceof GameEstate) {
			GameEstate ge = (GameEstate) field;
			if (ge.getOwner() != null && ge.getOwner().getId() == player.getId() && ge.getUpgradeLevel() == 0) {
				player.setMoney(player.getMoney() + ge.getCost());
				player.setCapital(player.getCapital() - ge.getCost());
				ge.setOwner(null);
				chatContext.writeToChat(game.getCode(), player.getName() + " sold " + ge.getName());
				return true;
			}
		} else if (field instanceof GameRailroad) {
			GameRailroad gr = (GameRailroad) field;
			if (gr.getOwner() != null && gr.getOwner().getId() == player.getId()) {
				player.setMoney(player.getMoney() + gr.getCost());
				player.setCapital(player.getCapital() - gr.getCost());
				gr.setOwner(null);
				chatContext.writeToChat(game.getCode(), player.getName() + " sold " + gr.getName());
				return true;
			}
		} else if (field instanceof GameUtility) {
			GameUtility gu = (GameUtility) field;
			if (gu.getOwner() != null && gu.getOwner().getId() == player.getId()) {
				player.setMoney(player.getMoney() + gu.getCost());
				player.setCapital(player.getCapital() - gu.getCost());
				gu.setOwner(null);
				chatContext.writeToChat(game.getCode(), player.getName() + " sold " + gu.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Try to upgrade a cell.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public boolean upgradeCell(HttpSession session, int cell) {
		if (!isMyTurn(session)) {
			return false;
		}
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = (Game) games.getGames().get(session.getAttribute(SNM.GAME).toString());
		Object field = game.getBoard().get(cell);
		if (field instanceof GameEstate) {
			GameEstate ge = (GameEstate) field;
			if (ge.getOwner() != null && ge.getOwner().getId() == player.getId() && ge.getUpgradeLevel() < 4
					&& ge.getBuildCost() <= player.getMoney()) {
				ge.setUpgradeLevel(ge.getUpgradeLevel() + 1);
				player.setMoney(player.getMoney() - ge.getBuildCost());
				player.setCapital(player.getCapital() + ge.getBuildCost());
				chatContext.writeToChat(game.getCode(), player.getName() + " upgraded " + ge.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Try to decrease an upgrade level of a cell.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public boolean downgradeCell(HttpSession session, int cell) {
		if (!isMyTurn(session)) {
			return false;
		}
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = (Game) games.getGames().get(session.getAttribute(SNM.GAME).toString());
		Object field = game.getBoard().get(cell);
		if (field instanceof GameEstate) {
			GameEstate ge = (GameEstate) field;
			if (ge.getOwner() != null && ge.getOwner().getId() == player.getId() && ge.getUpgradeLevel() > 0) {
				ge.setUpgradeLevel(ge.getUpgradeLevel() - 1);
				player.setMoney(player.getMoney() + ge.getBuildCost() / 2);
				player.setCapital(player.getCapital() - ge.getBuildCost());
				chatContext.writeToChat(game.getCode(), player.getName() + " downgraded " + ge.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Try to mortgage a cell estate.
	 * 
	 * @param session
	 * @param cell
	 * @return
	 */
	public boolean mortageCell(HttpSession session, int cell) {
		if (!isMyTurn(session)) {
			return false;
		}
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		Game game = (Game) games.getGames().get(session.getAttribute(SNM.GAME).toString());
		Object field = game.getBoard().get(cell);
		if (field instanceof GameEstate) {
			GameEstate ge = (GameEstate) field;
			if (ge.getOwner() != null && ge.getOwner().getId() == player.getId() && ge.getUpgradeLevel() == 0
					&& !ge.isMortaged()) {
				ge.setMortaged(true);
				player.setMoney(player.getMoney() + ge.getMortage());
				player.setCapital(player.getCapital() - ge.getCost());
				chatContext.writeToChat(game.getCode(), player.getName() + " laid the " + ge.getName());
				return true;
			}
		} else if (field instanceof GameRailroad) {
			GameRailroad gr = (GameRailroad) field;
			if (gr.getOwner() != null && gr.getOwner().getId() == player.getId() && !gr.isMortaged()) {
				gr.setMortaged(true);
				player.setMoney(player.getMoney() + gr.getMortage());
				player.setCapital(player.getCapital() - gr.getCost());
				chatContext.writeToChat(game.getCode(), player.getName() + " laid the " + gr.getName());
				return true;
			}
		} else if (field instanceof GameUtility) {
			GameUtility gu = (GameUtility) field;
			if (gu.getOwner() != null && gu.getOwner().getId() == player.getId()) {
				gu.setMortaged(true);
				player.setMoney(player.getMoney() + gu.getMortage());
				player.setCapital(player.getCapital() - gu.getCost());
				chatContext.writeToChat(game.getCode(), player.getName() + " laid the " + gu.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Kick player from game if player is owner and not owner's id.
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	public boolean abandonPlayer(Long id, HttpSession session) {
		Game game = getNotStartedGame((String) session.getAttribute(SNM.GAME));
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (game != null && player != null && session.getAttribute((String) SNM.CREATOR) != null
				&& player.getId() != id) {
			Iterator<Player> it = game.getPlayers().iterator();
			while (it.hasNext()) {
				Player p = it.next();
				if (p.getId() == id) {
					it.remove();
					break;
				}
			}
		}
		return true;
	}
}
