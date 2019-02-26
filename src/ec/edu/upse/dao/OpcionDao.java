package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;



import ec.edu.upse.modelo.Opcion;
import ec.edu.upse.modelo.Perfil;

@SuppressWarnings("unchecked")
public class OpcionDao extends ClaseDao {
	
	
	/**
	 * RETORNA LA LISTA DE OPCIONES PADRES  DE ACUERDO AL ROL DEL USUARIO
	 * @param value
	 * @return
	 */
	public List<Opcion> getOpcionPadrePorPerfil(Integer perfil) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcionPorPerfilPadre");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("perfil", perfil);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Opcion> getOpcionPadrePorHijo(Integer idOpcionPadre) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcionPadrePorHijo");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("idOpcionPadre", idOpcionPadre);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Opcion> getOpcionPadreHijo(Integer idOpcionPadre) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcionPadreHijo");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("idOpcionPadre", idOpcionPadre);
			//query.setParameter("perfil", perfil);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * RETORNA LA LISTA DE OPCIONES HIJOS DE ACUERDO AL ROL DEL USUARIO
	 * @param value
	 * @return
	 */
	public List<Opcion> getOpcionHijoPorPerfil(Integer opcionPadre,Integer perfil) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcionPorPerfilHijo");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("opcionPadre", opcionPadre);
			query.setParameter("perfil", perfil);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	public List<Opcion> getOpcion() {
		List<Opcion> retorno = new ArrayList<Opcion>();  
		Query query = getEntityManager().createNamedQuery("Opcion.findAll");
		retorno = (List<Opcion>) query.getResultList();
		return retorno;
	}
	
	public List<Opcion> getOpcionPadre() {
		List<Opcion> retorno = new ArrayList<Opcion>();  
		Query query = getEntityManager().createNamedQuery("Opcion.opcionPadre");
		retorno = (List<Opcion>) query.getResultList();
		return retorno;
	}
	
	public List<Opcion> getOpcionesDisponibles(Perfil perfil) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcionesDisponibles");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("perfil", perfil);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Opcion> getOpcDisp(Opcion opc) {
		List<Opcion> resultado; 
		try {			
			Query query = getEntityManager().createNamedQuery("Opcion.opcDisp");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("opc", opc);
			resultado = (List<Opcion>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
