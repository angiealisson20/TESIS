package ec.edu.upse.editar;


import org.zkoss.zk.ui.select.SelectorComposer;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.PerfilDao;
import ec.edu.upse.dao.UsuarioDao;
import ec.edu.upse.dao.UsuarioperfilDao;
import ec.edu.upse.lista.UsuarioPerfilLista;
import ec.edu.upse.modelo.Usuarioperfil;
import ec.edu.upse.modelo.Perfil;
import ec.edu.upse.modelo.Usuario;

@SuppressWarnings({ "serial", "rawtypes" })
public class UsuarioPerfilEditar extends SelectorComposer {
	
	@Wire
	private Window winUPE;
	
	private UsuarioPerfilLista usuarioperfilLista;
	
	private UsuarioperfilDao usuarioperfilDao = new UsuarioperfilDao();
	private UsuarioDao usuarioDao = new UsuarioDao();
	private PerfilDao perfilDao = new PerfilDao();
	private Usuarioperfil usuarioperfil;
	
	
	
	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		// Recupera la ventana padre.
		usuarioperfilLista = (UsuarioPerfilLista)Executions.getCurrent().getArg().get("VentanaPadre");

		// Recupera el objeto pasado como parametro. Si no lo recibe, crea uno
		usuarioperfil = null; 
		if (Executions.getCurrent().getArg().get("Usuarioperfil") != null) {
			System.out.println("entra grabando");
			usuarioperfil = (Usuarioperfil) Executions.getCurrent().getArg().get("Usuarioperfil");
		}else{
			usuarioperfil = new Usuarioperfil(); 
		}
	}
	
	
	@Listen("onClick=#grabar")
	public void grabar(){
		System.out.println("entra grabando");
		try {
			// Inicia la transaccion
			usuarioperfilDao.getEntityManager().getTransaction().begin();
			
			if (usuarioperfil.getIdUsuPerfil() == null) {
				usuarioperfilDao.getEntityManager().persist(usuarioperfil);
			}else{
				usuarioperfil = (Usuarioperfil) usuarioperfilDao.getEntityManager().merge(usuarioperfil);
			}

			// Cierra la transaccion.
			usuarioperfilDao.getEntityManager().getTransaction().commit();
			
			Clients.showNotification("Proceso Ejecutado con exito.");
			
			usuarioperfilLista.buscar();			
			salir(); 			
		} catch (Exception e) {
			e.printStackTrace();

			// Si hay error, reversa la transaccion.
			usuarioperfilDao.getEntityManager().getTransaction().rollback();
		}
	}

	@Listen("onClick=#salir")
	public void salir(){
		winUPE.detach();
	}


	public UsuarioPerfilLista getUsuarioperfilLista() {
		return usuarioperfilLista;
	}


	public void setUsuarioperfilLista(UsuarioPerfilLista usuarioperfilLista) {
		this.usuarioperfilLista = usuarioperfilLista;
	}


	public UsuarioperfilDao getUsuarioperfilDao() {
		return usuarioperfilDao;
	}


	public void setUsuarioperfilDao(UsuarioperfilDao usuarioperfilDao) {
		this.usuarioperfilDao = usuarioperfilDao;
	}


	public Usuarioperfil getUsuarioperfil() {
		return usuarioperfil;
	}


	public void setUsuarioperfil(Usuarioperfil usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}

	public List<Perfil> getPerfil() {
		return perfilDao.getPerfil();
	}
	
	public List<Usuario> getUsuario(){
		return usuarioDao.getUsuario();
	}
	
}
