package ec.edu.upse.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ec.edu.upse.dao.UsuarioDao;
import ec.edu.upse.modelo.Usuario;
import ec.edu.upse.modelo.Usuarioperfil;


public class ServicioAutenticacion  implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

		UsuarioDao usuarioDAO = new UsuarioDao();
		Usuario usuario; 
		User usuarioSpring;
		List<GrantedAuthority> privilegios; 
		
		usuario = usuarioDAO.getUsuario(nombreUsuario);
		
		Context.getInstance().setIdUsuarioLogeado(usuario.getIdUsuario()); //*
		
		privilegios = obtienePrivilegios(usuario);
		
		// Construye un objeto de Spring en base a los datos del usuario de la base de datos.
		usuarioSpring = new User(usuario.getUsuario(), usuario.getClave(), privilegios);

		return usuarioSpring;
	}
	

	/**
	 * Elabora una lista de objetos del tipo GrantedAuthority en base a los privilegios
	 * del usuario.
	 * 
	 * @param usuario
	 * @return
	 */
	private List<GrantedAuthority> obtienePrivilegios(Usuario usuario) {
		List<GrantedAuthority> listaPrivilegios = new ArrayList<GrantedAuthority>(); 
		
	    for(Usuarioperfil usuarioPerfil  : usuario.getUsuarioperfils()){
	    	listaPrivilegios.add(new SimpleGrantedAuthority(usuarioPerfil.getPerfil().getNombre()));
	    }

		return listaPrivilegios;
	}

}
