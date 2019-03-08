package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaDia;

public class DiasDao extends ClaseDao {
	
		
	@SuppressWarnings("unchecked")
	public List<TblSgaDia> getDias(){
		List<TblSgaDia> retorno = new ArrayList<TblSgaDia>();
		Query query = getEntityManager().createNamedQuery("TblSgaDia.findAll");
		retorno = (List<TblSgaDia>) query.getResultList();
		return retorno;
	}

}
