package resultadoOperacionDTOs;

import java.io.Serializable;
import java.util.List;

import dto.RankingDTO;

public class ResultadoOperacionListadoRankingDTO extends ResultadoOperacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RankingDTO> ranking;

	public ResultadoOperacionListadoRankingDTO(boolean resultado, String message, List<RankingDTO> ranking) {
		super(resultado, message);
		this.ranking = ranking;
		// TODO Auto-generated constructor stub
	}

	public List<RankingDTO> getRanking() {
		// TODO Auto-generated method stub
		return this.ranking;
	}

}
