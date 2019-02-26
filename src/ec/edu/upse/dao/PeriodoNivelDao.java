package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaNivel;
import ec.edu.upse.modelo.TblSgaPeriodonivel;

public class PeriodoNivelDao extends ClaseDao{
	@SuppressWarnings("unchecked")
	public List<TblSgaPeriodonivel> getNivelByPeriodo(Integer idPeriodo) {
		List<TblSgaPeriodonivel> retorno = new ArrayList<TblSgaPeriodonivel>();  
		Query query = getEntityManager().createNamedQuery("TblSgaPeriodonivel.buscarPorPeriodo");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idPeriodo", idPeriodo);
		retorno = (List<TblSgaPeriodonivel>) query.getResultList();
		return retorno;
	}
}
