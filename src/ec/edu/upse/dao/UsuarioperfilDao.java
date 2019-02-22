package ec.edu.upse.dao;

import java.util.List;

import javax.persistence.Query;

import ec.edu.upse.modelo.Usuario;
import ec.edu.upse.modelo.Usuarioperfil;

public class UsuarioperfilDao extends ClaseDao {
	
	/**
	 * Retorna el id del rol dependiendo del usuario.
	 * @param nombreUsuario
	 * @return
	 */
	public Integer getUsuarioPorRol(String nombreUsuario) {
		Integer resultado = 0; 
		Query consulta = null;
		try {
			consulta = getEntityManager().createNamedQuery("UsuarioPerfil.buscaUsuarioRol");
			consulta.setParameter("nombreUsuario", nombreUsuario);
			resultado = (Integer) consulta.getSingleResult();		
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public Usuario getUsuario(String nombreUsuario) {
		Usuario resultado = null; 
		Query consulta = null;
		try {
			consulta = getEntityManager().createNamedQuery("UsuarioPerfil.UsuarioRolClave");
			consulta.setParameter("nombreUsuario", nombreUsuario);
			resultado = (Usuario) consulta.getSingleResult();		
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuarioperfil> getUsuarioPerfiles(String value) {
		List<Usuarioperfil> resultado; 
		String patron = value;
		if (value == null || value.length() == 0) {
			patron = "%";
		}else{
			patron = "%" + patron.toLowerCase() + "%";
		}
		Query query = getEntityManager().createNamedQuery("UsuarioPerfil.buscarPorPatron");
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		query.setParameter("patron", patron);
		resultado = (List<Usuarioperfil>) query.getResultList();
		return resultado;
	}
	

}
