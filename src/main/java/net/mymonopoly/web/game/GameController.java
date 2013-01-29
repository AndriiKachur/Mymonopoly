package net.mymonopoly.web.game;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.mymonopoly.engine.GamesFactory;
import net.mymonopoly.engine.dto.Game;
import net.mymonopoly.engine.dto.GameOptions;
import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.engine.utils.JSON;
import net.mymonopoly.engine.utils.SNM;
import net.mymonopoly.service.GameServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for game functionality.
 * 
 * @author Andrey K.
 * 
 */
@RequestMapping(value = "/game/")
@Controller
public class GameController {

	@Autowired
	private GameServiceImpl service;

	@RequestMapping(value = "mock")
	public String mockPage(Model model, HttpSession session) {
		// FIXME this is a test page
		Game game = GamesFactory.getTestGame(new GameOptions());
		game.getOptions().setGameName("TEST MOCK");
		Player p = new Player();
		p.setName("John Coul");
		p.setCapital(1500);
		p.setMoney(340);
		p.setPosition((byte) 1);

		Player p2 = new Player();
		p2.setName("Andy Smith");
		p2.setCapital(400);
		p2.setMoney(130);
		p2.setPosition((byte) 6);

		Player p3 = new Player();
		p3.setName("John Karther");
		p3.setCapital(4000);
		p3.setMoney(2356);
		p3.setPosition((byte) 7);

		Player p4 = new Player();
		p4.setName("Jimmy Nickel");
		p4.setCapital(3200);
		p4.setMoney(100);
		p4.setPosition((byte) 20);

		Player p5 = new Player();
		p5.setName("Sandy Lane");
		p5.setCapital(456);
		p5.setMoney(4545);
		p5.setPosition((byte) 23);

		Player p6 = new Player();
		p6.setName("Andy Manek");
		p6.setCapital(780);
		p6.setMoney(112);
		p6.setPosition((byte) 24);

		game.getPlayers().add(p);
		game.getPlayers().add(p2);
		game.getPlayers().add(p3);
		game.getPlayers().add(p4);
		game.getPlayers().add(p5);
		game.getPlayers().add(p6);

		model.addAttribute("game", game);
		return "game/mock";
	}

	@RequestMapping(value = "{gameid}")
	public String gamePage(@PathVariable String gameid, Model model, HttpSession session) {
		model.addAttribute("game", service.getGame(gameid));
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (player != null) {
			model.addAttribute("currentPlayer", player.getId());
		}
		return "game/mock";
	}

	@RequestMapping(method = RequestMethod.GET, value = "getplayers")
	@ResponseBody
	public String getPlayersList(Model model, HttpSession session) {
		String code = (String) session.getAttribute(SNM.GAME);
		if (service.checkPlayer(session)) {
			Game game = service.getGame(code);
			if (game != null) {
				return JSON.OKandCODE(code);
			}
			return JSON.serialize(service.getGamePlayers(code));
		}
		return JSON.GAME_ENDED;
	}

	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String createGameRoom(Model model, HttpSession session) {
		Game game = service.getNotStartedGame((String) session.getAttribute(SNM.GAME));
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (game != null && player != null) {
			model.addAttribute("gameName", game.getOptions().getGameName());
			model.addAttribute("currentPlayer", player.getId());
		}
		return "frontend/creategame";
	}

	@RequestMapping(method = RequestMethod.GET, value = "return")
	public String returnToGame(Model model, HttpSession session) {
		Game game = service.getGame((String) session.getAttribute(SNM.GAME));
		if (game != null) {
			return "redirect:/game/" + game.getCode();
		} else {
			return "redirect:/game/create";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "join")
	public String joinGameRoom(Model model, HttpSession session) {
		model.addAttribute("games", service.getNotStartedGames());
		return "frontend/joingame";
	}

	@RequestMapping(method = RequestMethod.GET, value = "fieldPopover")
	@ResponseBody
	public String fieldPopover(@RequestParam(value = "cell", required = true) Integer cell, Model model,
			HttpSession session) {
		return service.getFieldInfo(session, cell).toJson();
	}

	@RequestMapping(method = RequestMethod.POST, value = "joingame")
	@ResponseBody
	public String joinGame(@RequestParam(value = "code", required = true) String code, Model model,
			HttpSession session) {
		if (service.canIJoinGame(code)) {
			if (service.joinGame(session, code)) {
				return JSON.OK;
			} else {
				return JSON.ERROR;
			}
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.POST, value = "create")
	@ResponseBody
	public String createGame(@Valid GameOptions options, Model model, HttpSession session) {
		String code = service.createGame(session, options);
		if (!StringUtils.isBlank(code)) {
			return JSON.OKandCODE(code);
		} else {
			return JSON.ERROR;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "getstate")
	@ResponseBody
	public String getState(@RequestParam(value = "code", required = true) String code, HttpSession session) {
		return service.getGameState(code);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "startgame")
	@ResponseBody
	public String startGame(HttpSession session) {
		if (service.checkPlayer(session)) {
			String code = (String) session.getAttribute(SNM.GAME);
			if (service.canIStartGame(session)) {
				if (service.startGame(session)) {
					return JSON.OKandCODE(code);
				}
			}
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.POST, value = "endturn")
	@ResponseBody
	public String endTurn(HttpSession session) {
		if (service.endTurn(session)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.GET, value = "leavegame")
	public String leaveGame(HttpSession session) {
		service.leaveGame(session);
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "buy/{cell}")
	@ResponseBody
	public String buyField(@PathVariable("cell") Integer cell, HttpSession session) {
		if (service.buyCell(session, cell)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "sell/{cell}")
	@ResponseBody
	public String sellField(@PathVariable("cell") Integer cell, HttpSession session) {
		if (service.sellCell(session, cell)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "upgrade/{cell}")
	@ResponseBody
	public String upgradeField(@PathVariable("cell") Integer cell, HttpSession session) {
		if (service.upgradeCell(session, cell)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "downgrade/{cell}")
	@ResponseBody
	public String downgradeField(@PathVariable("cell") Integer cell, HttpSession session) {
		if (service.downgradeCell(session, cell)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "mortage/{cell}")
	@ResponseBody
	public String mortageField(@PathVariable("cell") Integer cell, HttpSession session) {
		if (service.mortageCell(session, cell)) {
			return JSON.OK;
		}
		return JSON.ERROR;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "abandon")
	@ResponseBody
	public String abandonPlayer(@RequestParam("id") Long id, HttpSession session) {
		service.abandonPlayer(id, session);
		return JSON.OK;
	}

}
