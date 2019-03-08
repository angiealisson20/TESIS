package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaCurso;

public class CursoDao extends ClaseDao {
	
	@SuppressWarnings("unchecked")
	public List<TblSgaCurso> getCursos(){
		List<TblSgaCurso> retorno = new ArrayList<TblSgaCurso>();
		Query query = getEntityManager().createNamedQuery("TblSgaCurso.findAll");
		retorno = (List<TblSgaCurso>) query.getResultList();
		return retorno;
	}


}
