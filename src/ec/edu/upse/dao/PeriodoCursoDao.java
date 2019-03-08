package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaPeriodoncurso;

public class PeriodoCursoDao extends ClaseDao{
	@SuppressWarnings("unchecked")
	public List<TblSgaPeriodoncurso> getCursoByNivel(Integer idNivel) {
		List<TblSgaPeriodoncurso> retorno = new ArrayList<TblSgaPeriodoncurso>();  
		Query query = getEntityManager().createNamedQuery("TblSgaPeriodoncurso.buscarPorNivel");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idNivel", idNivel);
		retorno = (List<TblSgaPeriodoncurso>) query.getResultList();
		return retorno;
	}
}
