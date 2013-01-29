package net.mymonopoly.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.mymonopoly.engine.dto.Player;
import net.mymonopoly.entity.GameChance;
import net.mymonopoly.entity.GameChest;
import net.mymonopoly.entity.GameEstate;
import net.mymonopoly.entity.GameRailroad;
import net.mymonopoly.entity.GameUtility;

/**
 * JSP tag for rendering separate board cell.
 * 
 * @author Andrey K.
 * 
 */
public class GameTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	static final private Log LOG = LogFactory.getLog(GameTag.class);

	private Object cell;

	private List<Player> players;

	private int position;

	@Override
	public int doEndTag() throws JspException {
		StringBuilder cellHTML = new StringBuilder();
		StringBuilder playersHTML = new StringBuilder();
		StringBuilder actionsHTML = new StringBuilder();

		try {
			if (players == null) {
				return SKIP_BODY;
			}
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				if (position == p.getPosition()) {
					playersHTML.append(" playertoken" + (i + 1));
				}
			}

			cellHTML.append("<div id='c").append(position).append("' data-toggle=\"dropdown\" class='cell ")
					.append(playersHTML.toString());
			if (cell instanceof GameEstate) {
				cellHTML.append(" estate ");
			}
			cellHTML.append("' ");
			if (position == 0) {
				cellHTML.append("><p>Start</p>");
			} else if (position == 10) {
				cellHTML.append("><p>Jail/Just Visiting</p>");
			} else if (position == 20) {
				cellHTML.append("><p>Free Parking</p>");
			} else if (position == 30) {
				cellHTML.append("><p>Go To Jail</p>");
			} else {
				if (cell instanceof GameChance) {
					cellHTML.append("><p>Chance</p>");
				} else if (cell instanceof GameChest) {
					cellHTML.append("><p>Chest</p>");
				} else if (cell instanceof GameEstate) {
					cellHTML.append(" style='border-color: " + ((GameEstate) cell).getColour() + ";'><p>"
							+ ((GameEstate) cell).getName() + "</p>" + "<p>$" + ((GameEstate) cell).getCost()
							+ "</p>");
					actionsHTML.append("<ul class='dropdown-menu'>" + "<li><a onclick='buy(" + position
							+ ")'>Buy</a></li>" + "<li><a onclick='sell(" + position + ")'>Sell</a></li>"
							+ "<li><a onclick='upgrade(" + position + ")'>Upgrade</a></li>"
							+ "<li><a onclick='downgrade(" + position + ")'>Downgrade</a></li>"
							+ "<li><a onclick='mortage(" + position + ")'>Mortage</a></li>" + "</ul>");
				} else if (cell instanceof GameRailroad) {
					cellHTML.append("><p>" + ((GameRailroad) cell).getName() + "</p>" + "<p>$"
							+ ((GameRailroad) cell).getCost() + "</p>");
					actionsHTML.append("<ul class='dropdown-menu'>" + "<li><a onclick='buy(" + position
							+ ")'>Buy</a></li>" + "<li><a onclick='sell(" + position + ")'>Sell</a></li>"
							+ "<li><a onclick='mortage(" + position + ")'>Mortage</a></li>" + "</ul>");
				} else if (cell instanceof GameUtility) {
					cellHTML.append("><p>" + ((GameUtility) cell).getName() + "</p>" + "<p>$"
							+ ((GameUtility) cell).getCost() + "</p>");
					actionsHTML.append("<ul class='dropdown-menu'>" + "<li><a onclick='buy(" + position
							+ ")'>Buy</a></li>" + "<li><a onclick='sell(" + position + ")'>Sell</a></li>"
							+ "<li><a onclick='mortage(" + position + ")'>Mortage</a></li>" + "</ul>");
				}
			}
			// TODO: normal dropdown
			cellHTML.append("</div>").append(actionsHTML.toString());
		} catch (Exception e) {
			LOG.warn("", e);
		}

		JspWriter r;
		try {
			r = pageContext.getOut();
			r.print(cellHTML.toString());
			r.flush();
		} catch (IOException e1) {
			LOG.warn("", e1);
		}

		return SKIP_BODY;
	}

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
