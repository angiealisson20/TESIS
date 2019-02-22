package ec.edu.upse.lista;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.UsuarioDao;
import ec.edu.upse.modelo.Usuario;

@SuppressWarnings({ "unchecked", "serial", "deprecation" })
public class UsuarioLista extends SelectorComposer<Component>  {
	
	@Wire
	private Window winListaUsuarios;
	
	@Wire
	private Textbox txtBuscar;
	
	@Wire 
	private Listbox lstUsuarios;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	private List<Usuario> usuarios;

	private Usuario usuarioSeleccionado;
	
	@SuppressWarnings("rawtypes")
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		
		System.out.println("entra buscar");
		
		if (usuarios != null) {
			usuarios = null; 
		}
		
		usuarios = usuarioDao.getListaUsuariosPorPatron(txtBuscar.getValue());
		lstUsuarios.setModel(new ListModelList(usuarios));
		
		usuarioSeleccionado = null;
		
		AnnotateDataBinder binder = new AnnotateDataBinder(winListaUsuarios);
		binder.loadComponent(lstUsuarios);

	}
	
	/**
	 * Escucha el evento "onClick" del objeto "btnNuevo"
	 * Carga el formulario sin una persona para crear uno nuevo.
	 */
	@Listen("onClick=#btnNuevo")
	public void nuevo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Usuario", null);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/usuarioEditar.zul", winListaUsuarios, params);
		ventanaCargar.doModal();
	}

	
	//Getter and Setter
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
}


