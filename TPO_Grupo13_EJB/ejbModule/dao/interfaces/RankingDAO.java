package dao.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Ranking;

@Local
public interface RankingDAO extends BaseDAO {

	public Ranking get(int codigo);

	public List<Ranking> listRanking();
}