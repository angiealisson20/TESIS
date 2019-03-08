package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaCurso;
import ec.edu.upse.modelo.TblSgaCursoparalelo;
import ec.edu.upse.modelo.TblSgaDistributivo;
import ec.edu.upse.modelo.TblSgaDocente;
import ec.edu.upse.modelo.TblSgaMateria;
import ec.edu.upse.modelo.TblSgaParalelo;

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
	
	@SuppressWarnings("unchecked")
	public List<TblSgaCursoparalelo> getParalelo(Integer idParalelo) {
		List<TblSgaCursoparalelo> retorno = new ArrayList<TblSgaCursoparalelo>();  
		Query query = getEntityManager().createNamedQuery("TblSgaCursoparalelo.buscarPorParalelo");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idParalelo", idParalelo);
		retorno = (List<TblSgaCursoparalelo>) query.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public TblSgaCursoparalelo getCursoParalelos(TblSgaCurso cur, TblSgaParalelo par) {
		List<TblSgaCursoparalelo> resultado; 
		Query query = getEntityManager().createNamedQuery("TblSgaCursoparalelo.findByParaleloCurso");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("cur", cur);
		query.setParameter("par", par);
		resultado = (List<TblSgaCursoparalelo>) query.getResultList();
		return resultado.get(0);
	}
}
