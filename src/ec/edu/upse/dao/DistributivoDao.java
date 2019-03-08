package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


import ec.edu.upse.modelo.TblSgaDistributivo;
import ec.edu.upse.modelo.TblSgaDocente;
import ec.edu.upse.modelo.TblSgaMateria;

public class DistributivoDao extends ClaseDao {

	@SuppressWarnings("unchecked")
	public List<TblSgaDistributivo> getDocentebyMateria(Integer idMateria) {
		List<TblSgaDistributivo> retorno = new ArrayList<TblSgaDistributivo>();  
		Query query = getEntityManager().createNamedQuery("TblSgaDistributivo.buscarPorMateria");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idMateria", idMateria);
		retorno = (List<TblSgaDistributivo>) query.getResultList();
		return retorno;
		
	}
	@SuppressWarnings("unchecked")
	public TblSgaDistributivo getDistributivo(TblSgaMateria mat, TblSgaDocente doc) {
		List<TblSgaDistributivo> resultado; 
		Query query = getEntityManager().createNamedQuery("TblSgaDistributivo.findByDocenteAndMateria");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("mat", mat);
		query.setParameter("doc", doc);
		resultado = (List<TblSgaDistributivo>) query.getResultList();
		return resultado.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<TblSgaDistributivo> getDistributivos(String value) {
		List<TblSgaDistributivo> resultado; 
		String patron = value;
		if (value == null || value.length() == 0) {
			patron = "%";
		}else{
			patron = "%" + patron.toLowerCase() + "%";
		}
		Query query = getEntityManager().createNamedQuery("TblSgaDistributivo.buscarPorPatron");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("patron", patron);
		resultado = (List<TblSgaDistributivo>) query.getResultList();
		return resultado;
	}
}
