package dao;

import java.util.List;

import javax.ejb.Stateless;

import dao.interfaces.RankingDAO;
import entities.Ranking;

@Stateless
public class RankingDAOBean extends BaseDAOBean implements RankingDAO {

	public RankingDAOBean() {

	}

	public Ranking get(int codigo) {
		return getEntity(Ranking.class, codigo);
	}

	public List<Ranking> listRanking() {
		return getAll(Ranking.class, "Ranking");
	}
}
