package net.mymonopoly.engine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Fully focused on chat rooms class. Thread safe.
 * 
 * @author Andrey K.
 * 
 */
public class Chats {

	private static final Log LOG = LogFactory.getLog(Chats.class);

	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss ");

	private static final String BEGIN_TAG = "<p>";
	private static final String END_TAG = "</p>";
	private static final String SYSTEM_NAME = "<b>SYSTEM</b>: ";

	private static final ConcurrentMap<String, ConcurrentNavigableMap<Long, String>> CHATS = new ConcurrentHashMap<String, ConcurrentNavigableMap<Long, String>>();

	/**
	 * Post a message to chat.
	 * 
	 * @param gameCode
	 *            - code of the game
	 * @param timeMillis
	 *            - time of message in milliseconds from 1970
	 * @param message
	 *            - Message. Best arg is: player name + message
	 * @return true if message was sent
	 */
	public static final boolean send(String gameCode, long timeMillis, String message) {
		if (!CHATS.containsKey(gameCode)) {
			CHATS.put(gameCode, new ConcurrentSkipListMap<Long, String>());
		}
		CHATS.get(gameCode).put(Long.valueOf(timeMillis), message);
		return true;
	}

	/**
	 * Post a system message to chat.
	 * 
	 * @param gameCode
	 *            - code of the game
	 * @param timeMillis
	 *            - time of message in milliseconds from 1970
	 * @param message
	 *            - message
	 * @return true if message sent succesfully
	 */
	public static final boolean systemMessage(String gameCode, long timeMillis, String message) {
		if (!CHATS.containsKey(gameCode)) {
			CHATS.put(gameCode, new ConcurrentSkipListMap<Long, String>());
		}
		CHATS.get(gameCode).put(Long.valueOf(timeMillis), SYSTEM_NAME + message);
		return true;
	}

	/**
	 * Gets HTML of chat from date equals and higher.
	 * 
	 * @param gameCode
	 * @param timeMillis
	 * @return HTML with chat records since date
	 */
	public static final String get(String gameCode, long timeMillis) {
		if (!CHATS.containsKey(gameCode)) {
			return null;
		}
		ConcurrentNavigableMap<Long, String> chat = CHATS.get(gameCode);
		Entry<Long, String> entry = chat.ceilingEntry(Long.valueOf(timeMillis));
		StringBuilder sb = new StringBuilder();
		while (entry != null) {
			sb.append(BEGIN_TAG).append(timeFormat.format(new Date(entry.getKey()))).append(entry.getValue())
					.append(END_TAG);
			entry = chat.higherEntry(entry.getKey());
		}
		return sb.toString();
	}

	/**
	 * Performs clean up of inactive chats: - if chat of the ended game - if
	 * chat is not assigned to started and not started games.
	 */
	public static final void cleanup() {
		int cleaned = 0;
		Iterator<Entry<String, ConcurrentNavigableMap<Long, String>>> it = CHATS.entrySet().iterator();
		while (it.hasNext()) {
			String gameCode = it.next().getKey();
			if (!Games.GAMES.containsKey(gameCode) && !Games.NOT_STARTED_GAMES.containsKey(gameCode)) {
				it.remove();
				++cleaned;
			}
		}
		if (cleaned > 0) {
			LOG.info("Chats cleaned up: " + cleaned);
		}
	}

	/**
	 * Write system message to chat.
	 * 
	 * @param gameCode
	 * @param message
	 */
	public static final void writeToChat(String gameCode, String message) {
		Chats.systemMessage(gameCode, new Date().getTime(), message);
	}

}
