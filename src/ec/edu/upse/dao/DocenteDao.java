package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaDocente;

public class DocenteDao extends ClaseDao{
	
	@SuppressWarnings("unchecked")
	public List<TblSgaDocente> getDocente() {
		List<TblSgaDocente> retorno = new ArrayList<TblSgaDocente>();  
		Query query = getEntityManager().createNamedQuery("TblSgaDocente.findAll");
		retorno = (List<TblSgaDocente>) query.getResultList();
		return retorno;
	}
	

}
