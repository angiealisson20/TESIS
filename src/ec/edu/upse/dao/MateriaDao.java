package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaMateria;

public class MateriaDao extends ClaseDao {

	 @SuppressWarnings("unchecked")
		public List<TblSgaMateria> getMaterias() {
			List<TblSgaMateria> retorno = new ArrayList<TblSgaMateria>();  
			Query query = getEntityManager().createNamedQuery("TblSgaMateria.findAll");
			retorno = (List<TblSgaMateria>) query.getResultList();
			return retorno;
		}
		 
}
