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

import ec.edu.upse.dao.PerfilDao;
import ec.edu.upse.modelo.Perfil;

@SuppressWarnings({"unchecked", "serial", "deprecation"})
public class PerfilLista extends SelectorComposer<Component> {
	
	@Wire
	private Window winListaPerfiles;

	@Wire
	private Textbox txtBuscar;

	@Wire 
	private Listbox lstPerfiles;

	private PerfilDao perfilDao = new PerfilDao();

	private List<Perfil> perfiles;
	private Perfil perfilSeleccionado;
	
	@SuppressWarnings({ "rawtypes" })
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		System.out.println("entra buscar");

		if (perfiles != null) {
			perfiles = null; 
		}
		perfiles = perfilDao.getPerfiles(txtBuscar.getValue());
		lstPerfiles.setModel(new ListModelList(perfiles));

		perfilSeleccionado = null;

		AnnotateDataBinder binder = new AnnotateDataBinder(winListaPerfiles);
		binder.loadComponent(lstPerfiles);

	}
	
	@Listen("onClick=#btnNuevo")
	public void nuevo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Perfil", null);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/perfilEditar.zul", winListaPerfiles, params);
		ventanaCargar.doModal();
	}

	@Listen("onClick=#btnEditar")
	public void editar(){
		if (perfilSeleccionado == null) {
			Clients.showNotification("Debe seleccionar un perfil.");
			return; 
		}

		// Actualiza la instancia antes de enviarla a editar.
		perfilDao.getEntityManager().refresh(perfilSeleccionado);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Perfil", perfilSeleccionado);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/perfilEditar.zul", winListaPerfiles, params);
		ventanaCargar.doModal();
	}

	public PerfilDao getPerfilDao() {
		return perfilDao;
	}

	public void setPerfilDao(PerfilDao perfilDao) {
		this.perfilDao = perfilDao;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Perfil getPerfilSeleccionado() {
		return perfilSeleccionado;
	}

	public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
		this.perfilSeleccionado = perfilSeleccionado;
	}	
	
	
	
	

}
