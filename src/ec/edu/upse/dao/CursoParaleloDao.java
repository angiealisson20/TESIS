package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaCursoparalelo;

public class CursoParaleloDao extends ClaseDao {
	
	@SuppressWarnings("unchecked")
	public List<TblSgaCursoparalelo> getParalelobyCurso(Integer idCurso) {
		List<TblSgaCursoparalelo> retorno = new ArrayList<TblSgaCursoparalelo>();  
		Query query = getEntityManager().createNamedQuery("TblSgaCursoparalelo.buscarPorCurso");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idCurso", idCurso);
		retorno = (List<TblSgaCursoparalelo>) query.getResultList();
		return retorno;
	}

}
