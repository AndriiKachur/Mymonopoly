package net.mymonopoly.web.game;

import java.util.Date;

import javax.servlet.http.HttpSession;

import net.mymonopoly.engine.Chats;
import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.engine.utils.JSON;
import net.mymonopoly.engine.utils.SNM;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for chat functionality.
 * 
 * @author Andrey K.
 * 
 */
@RequestMapping(value = "/game/chat")
@Controller
public class ChatController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String send(@RequestParam(value = "message", required = true) String message, HttpSession session) {
		String gameCode = (String) session.getAttribute(SNM.GAME);
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (player != null && gameCode != null) {

			message = message.replaceAll("\\<.*?\\>", "");
			Chats.send(gameCode, new Date().getTime(), player.getName() + ": " + message);
			return JSON.OK;
		}
		return JSON.ERROR;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> get(HttpSession session) {
		String gameCode = (String) session.getAttribute(SNM.GAME);
		Player player = (Player) session.getAttribute(SNM.PLAYER);
		if (gameCode == null || player == null) {
			return new ResponseEntity<String>(JSON.GAME_ENDED, HttpStatus.OK);
		}
		Long time = (Long) session.getAttribute(SNM.LAST_CHAT_GET);
		if (time == null) {
			time = new Date().getTime();
		}
		session.setAttribute(SNM.LAST_CHAT_GET, new Date().getTime());
		String messages = Chats.get(gameCode, time);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		ResponseEntity<String> response = new ResponseEntity<String>(JSON.chatOK(messages), headers,
				HttpStatus.OK);
		return response;
	}

}
