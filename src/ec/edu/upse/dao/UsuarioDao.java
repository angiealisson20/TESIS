package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.Usuario;

public class UsuarioDao extends ClaseDao {
	
	/**
	 * Retorna un usuario en base a su nombre de usuario.
	 * @param nombreUsuario
	 * @return
	 */
	public Usuario getUsuario(String nombreUsuario) {
		Usuario usuario; 
		Query consulta;
		
		consulta = getEntityManager().createNamedQuery("Usuario.buscaUsuario");
		consulta.setParameter("nombreUsuario", nombreUsuario);
		
		usuario = (Usuario) consulta.getSingleResult();
		
		return usuario;
	}
	
	/**
	 * RETORNA LA LISTA DE USUARIO DE ACUERDO A LA BUSQUEDA
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaUsuariosPorPatron(String value) {
		List<Usuario> resultado; 
		String patron = value;
		try {
			if (value == null || value.length() == 0) {
				patron = "%";
			}else{
				patron = "%" + patron.toLowerCase() + "%";
			}
			Query query = getEntityManager().createNamedQuery("Usuario.buscaPatron");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			query.setParameter("patron", patron);
			resultado = (List<Usuario>) query.getResultList();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuario() {
		List<Usuario> retorno = new ArrayList<Usuario>();  
		Query query = getEntityManager().createNamedQuery("Usuario.findAll");
		retorno = (List<Usuario>) query.getResultList();
		return retorno;
	}
	
	

}
