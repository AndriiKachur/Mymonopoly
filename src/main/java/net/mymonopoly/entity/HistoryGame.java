package net.mymonopoly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findHistoryGamesByPlayers" })
public class HistoryGame {

	@NotNull
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date endTime;

	@NotNull
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<HistoryPlayer> players = new HashSet<HistoryPlayer>();

	public static List<HistoryGame> findByPlayerId(Long id) {
		if (id == null) {
			return null;
		}
		Query query = HistoryGame
				.entityManager()
				.createQuery(
						"SELECT hg FROM HistoryGame hg JOIN hg.players p WHERE p.playerId = :param"
								+ " ORDER BY hg.startTime DESC").setParameter("param", id);

		return query.getResultList();
	}
}
