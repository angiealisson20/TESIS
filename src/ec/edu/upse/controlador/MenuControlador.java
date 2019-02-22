package ec.edu.upse.controlador;

import java.util.List;


import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import ec.edu.upse.dao.OpcionDao;
import ec.edu.upse.modelo.Opcion;
import ec.edu.upse.modelo.Opcionperfil;
import ec.edu.upse.util.SecurityUtil;

public class MenuControlador {
	
	/**
	 * Provee los datos para las opciones
	 * @return
	 */
	public List<Opcion> getOpciones() {
		OpcionDao opcionDao = new OpcionDao();
		return opcionDao.getOpciones();
		//return opcionDao.getOpcionesFiltradas();
	}
	

	/**
	 * Se ejecuta al dar click en la fila del grid
	 * @param url
	 */
	@Command
	@NotifyChange("formularioActual")
	public void cargaUrl(@BindingParam("opcion") Opcion opcion) {
		boolean tienePrivilegio = false; 

		for (Opcionperfil opcionPerfil : opcion.getOpcionperfils()) {
			
			// Utilidad que permite verificar los privilegios del usuario actual.
			if (SecurityUtil.isAnyGranted(opcionPerfil.getPerfil().getNombre())) {
				tienePrivilegio = true;
				break; 
			}
		}
		
		if (tienePrivilegio) {
			// Si comienza con "http" se entiende que es una URL
			// de lo contrario es un formulario.
			if (opcion.getUrl().substring(0, 4).toLowerCase().equals("http")) {
				
				// Limpia el atributo del formulario actual de la variable 
				// de sesion
				Sessions.getCurrent().setAttribute("FormularioActual", null);
				Executions.getCurrent().sendRedirect(opcion.getUrl(), "_blank");	
				
			}else{
				// Coloca el atributo del formulario actual en una variable 
				// de sesion
				Sessions.getCurrent().setAttribute("FormularioActual", opcion);
			}
		}else{
			Clients.showNotification("No tiene privilegios para acceder a esta opcion.");
		}
		

	}
	
	public String getFormularioActual() {
		String url = null; 
		// Recupera el formulario actual desde la variable de sesion
		if (Sessions.getCurrent().getAttribute("FormularioActual") != null) {
			url = ((Opcion)Sessions.getCurrent().getAttribute("FormularioActual")).getUrl();
		}
		return url; 
		
	}
	
	/**
	 * Retorna el nombre del usuario logoneado.
	 * @return
	 */
	public String getNombreUsuario() {
		String retorno = null; 
		if (SecurityUtil.getUser() != null) {
			retorno = SecurityUtil.getUser().getUsername();
		}
		return retorno;
	}


}
