package ec.edu.upse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import ec.edu.upse.modelo.Opcion;
import ec.edu.upse.modelo.Opcionperfil;
import ec.edu.upse.modelo.Usuario;
import ec.edu.upse.modelo.Usuarioperfil;

public class OpcionDao extends ClaseDao {
	
	/**
	 * Retorna la lista de opciones disponibles.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> getOpciones() {
		List<Opcion> opciones = new ArrayList<Opcion>();

		Query query = getEntityManager().createNamedQuery("Opcion.findAll");
		opciones = (List<Opcion>) query.getResultList();

		return opciones;
	}
	
	/**
	 * Retorna la lista de opciones disponibles filtradas por 
	 * los privilegios del usuario
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> getOpcionesFiltradas() {
		UsuarioDao usuarioDAO = new UsuarioDao();
		List<Opcion> opciones = new ArrayList<Opcion>();
		List<Opcion> retorno = new ArrayList<Opcion>();

		Query query = getEntityManager().createNamedQuery("Opcion.findAll");
		opciones = (List<Opcion>) query.getResultList();

		// Obtiene el usuario logoneado
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Busco el usuario en la base
		Usuario usuario = usuarioDAO.getUsuario(user.getUsername()); 

		// Filtra solo las opciones a las que tiene acceso el usuario.
		for (Opcion opcion : opciones) {
			// Por cada opcion, verifica si esta asignada a 
			// algun privilegio que tenga el usuario.
			boolean opcionInsertada = false; 
			for (Opcionperfil opcionPerfil : opcion.getOpcionperfils()) {
				for (Usuarioperfil usuarioPerfil : usuario.getUsuarioperfils()) {
					if (opcionPerfil.getPerfil().getIdPerfil() == usuarioPerfil.getPerfil().getIdPerfil()) {
						retorno.add(opcion);
						opcionInsertada = true; 
						break;
					}
				}
				if (opcionInsertada) {
					break;
				}
			}
		}

		return retorno;
	}


}
