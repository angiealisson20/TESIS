package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaCursoparalelo;
import ec.edu.upse.modelo.TblSgaHorario;
import ec.edu.upse.modelo.Usuario;

public class HorarioDao extends ClaseDao {
	
	@SuppressWarnings("unchecked")
	public List<TblSgaHorario> getHorarios() {
		List<TblSgaHorario> horarios = new ArrayList<TblSgaHorario>();

		Query query = getEntityManager().createNamedQuery("TblSgaHorario.findAll");
		horarios = (List<TblSgaHorario>) query.getResultList();

		return horarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblSgaHorario> getHorarios(String value) {
		List<TblSgaHorario> resultado; 
		String patron = value;

		if (value == null || value.length() == 0) {
			patron = "%";
		}else{
			patron = "%" + patron.toLowerCase() + "%";
		}
		Query query = getEntityManager().createNamedQuery("TblSgaHorario.buscarPorPatron");

		query.setHint("javax.persistence.cache.storeMode", "REFRESH");

		query.setParameter("patron", patron);

		resultado = (List<TblSgaHorario>) query.getResultList();
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblSgaHorario> getCursosRegistrados(TblSgaCursoparalelo cursoparalelo){
		List<TblSgaHorario> resultado;
		
		try{
			Query query = getEntityManager().createNamedQuery("TblSgaHorario.cursosRegistrados");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("cursoparalelo", cursoparalelo);
			resultado = (List<TblSgaHorario>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*
	 * 
	 * public List<Opcion> getOpcionesDisponibles(Perfil perfil) {
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
	 * 
	 */
	
	

}
