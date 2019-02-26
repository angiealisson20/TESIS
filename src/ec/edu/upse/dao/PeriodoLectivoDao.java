package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.TblSgaPeriodolectivo;


public class PeriodoLectivoDao extends ClaseDao{
	@SuppressWarnings("unchecked")
	public List<TblSgaPeriodolectivo> getPeriodos() {
		List<TblSgaPeriodolectivo> retorno = new ArrayList<TblSgaPeriodolectivo>();  
		Query query = getEntityManager().createNamedQuery("TblSgaPeriodolectivo.findAll");
		retorno = (List<TblSgaPeriodolectivo>) query.getResultList();
		return retorno;
	}
}
