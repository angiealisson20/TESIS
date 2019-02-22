package ec.edu.upse.lista;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.UsuarioperfilDao;
import ec.edu.upse.modelo.Usuarioperfil;


@SuppressWarnings({ "unchecked", "serial", "deprecation" })
public class UsuarioPerfilLista extends SelectorComposer<Component> {
	
	@Wire
	private Window winListaUsuarioPerfil;
	
	@Wire
	private Textbox txtBuscar;
	
	@Wire 
	private Listbox lstUsuarioperfil;
	
		
	private UsuarioperfilDao usuarioPerfilDao = new UsuarioperfilDao();
	
	private Usuarioperfil usuarioperfilSeleccionada;
	private List<Usuarioperfil> usuarioperfiles;
	
	
	@SuppressWarnings("rawtypes")
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		
		System.out.println("entra buscar");
		
		if (usuarioperfiles != null) {
			usuarioperfiles = null; 
		}
		
		usuarioperfiles = usuarioPerfilDao.getUsuarioPerfiles(txtBuscar.getValue());
		lstUsuarioperfil.setModel(new ListModelList(usuarioperfiles));
		
		usuarioperfilSeleccionada = null;
		
		AnnotateDataBinder binder = new AnnotateDataBinder(winListaUsuarioPerfil);
		binder.loadComponent(lstUsuarioperfil);

	}
	
	/*
	
	@AfterCompose
	public void aferCompose(@ContextParam(ContextType.VIEW) Component view) throws IOException{
		Selectors.wireComponents(view, this, false);
		textoBuscar="";
	}
	
	@GlobalCommand("UsuarioPerfilLista.buscar")
	@Command
	@NotifyChange({"usuarioperfiles", "usuarioperfilSeleccionada"})
	public void buscar(){

		if (usuarioperfiles != null) {
			usuarioperfiles = null; 
		}
		usuarioperfiles = usuarioPerfilDao.getUsuarioPerfiles(textoBuscar);

		// Limpia os objetos de trabajo
		usuarioperfilSeleccionada = null; 
	}*/
	
	
	@Listen("onClick=#btnNuevo")
	public void nuevo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usuarioperfil", null);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/usuarioPerfilEditar.zul", winListaUsuarioPerfil, params);
		ventanaCargar.doModal();
	}
	
	/**
	 * Crea nuevo
	 */
	/*@Command
	public void nuevo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usuarioperfil", new Usuarioperfil());
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/usuarioPerfilEditar.zul", null, params);
		ventanaCargar.doModal();
	}*/
	
	@Listen("onClick=#btnEditar")
	public void editar(){
		if (usuarioperfilSeleccionada == null) {
			Clients.showNotification("Debe seleccionar un usuario.");
			return; 
		}

		// Actualiza la instancia antes de enviarla a editar.
		usuarioPerfilDao.getEntityManager().refresh(usuarioperfilSeleccionada);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usuario", usuarioperfilSeleccionada);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/usuarioPerfilEditar.zul", winListaUsuarioPerfil, params);
		ventanaCargar.doModal();
	}
	
	
	/**
	 * Edita
	 */
/*	@Command
	public void editar(){
		if (usuarioperfilSeleccionada == null) {
			Clients.showNotification("Debe seleccionar una opción de la lista.");
			return; 
		}

		// Actualiza la instancia antes de enviarla a editar.
		usuarioPerfilDao.getEntityManager().refresh(usuarioperfilSeleccionada);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usuarioperfil", usuarioperfilSeleccionada);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/usuarioPerfilEditar.zul", null, params);
		ventanaCargar.doModal();
	}*/
	
	
	public Usuarioperfil getUsuarioperfilSeleccionada() {
		return usuarioperfilSeleccionada;
	}
	public void setUsuarioperfilSeleccionada(Usuarioperfil usuarioperfilSeleccionada) {
		this.usuarioperfilSeleccionada = usuarioperfilSeleccionada;
	}
	public List<Usuarioperfil> getUsuarioperfiles() {
		return usuarioperfiles;
	}
	public void setUsuarioperfiles(List<Usuarioperfil> usuarioperfiles) {
		this.usuarioperfiles = usuarioperfiles;
	}

}
