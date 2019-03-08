package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaEspaciofisico;

public class EspaciofisicoDao extends ClaseDao {
	
	@SuppressWarnings("unchecked")
	public List<TblSgaEspaciofisico> getEspfisico(){
		List<TblSgaEspaciofisico> retorno = new ArrayList<TblSgaEspaciofisico>();
		Query query = getEntityManager().createNamedQuery("TblSgaEspaciofisico.findAll");
		retorno = (List<TblSgaEspaciofisico>) query.getResultList();
		return retorno;
	}
	

}
