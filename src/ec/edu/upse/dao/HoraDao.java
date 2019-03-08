package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaHora;

public class HoraDao extends ClaseDao {

		
	@SuppressWarnings("unchecked")
	public List<TblSgaHora> getHoras(){
		List<TblSgaHora> retorno = new ArrayList<TblSgaHora>();
		Query query = getEntityManager().createNamedQuery("TblSgaHora.findAll");
		retorno = (List<TblSgaHora>) query.getResultList();
		return retorno;
		
	}
}
