package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


import ec.edu.upse.modelo.TblSgaHorario;

public class HorarioDao extends ClaseDao {

	@SuppressWarnings("unchecked")
	public List<TblSgaHorario> getHorarios() {
		List<TblSgaHorario> horarios = new ArrayList<TblSgaHorario>();

		Query query = getEntityManager().createNamedQuery("TblSgaHorario.findAll");
		horarios = (List<TblSgaHorario>) query.getResultList();

		return horarios;
	}


	@SuppressWarnings("unchecked")
	public List<TblSgaHorario> getObtenerConsulta(Integer idPeriodo,  Integer idNivel, Integer idCurso, Integer idParalelo, Integer idDia){
		List<TblSgaHorario> resultado;
		Query query = getEntityManager().createNamedQuery("TblSgaHorario.BuscaPorConsulta");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("idParalelo", idParalelo);
		query.setParameter("idPeriodo", idPeriodo);
		query.setParameter("idCurso", idCurso);
		query.setParameter("idNivel", idNivel);
		query.setParameter("idDia", idDia);
		resultado = (List<TblSgaHorario>) query.getResultList();

		return resultado;
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





}
