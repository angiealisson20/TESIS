package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaNivel;
import ec.edu.upse.modelo.TblSgaPeriodolectivo;

public class NivelDao extends ClaseDao{
	@SuppressWarnings("unchecked")
	public List<TblSgaNivel> getNivelByPeriodo(Integer idPeriodo) {
		List<TblSgaNivel> retorno = new ArrayList<TblSgaNivel>();  
		Query query = getEntityManager().createNamedQuery("TblSgaNivel.buscarPorPeriodo");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idPeriodo", idPeriodo);
		retorno = (List<TblSgaNivel>) query.getResultList();
		return retorno;
	}
}
