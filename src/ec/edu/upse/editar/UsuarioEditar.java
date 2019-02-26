package ec.edu.upse.editar;

import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.UsuarioDao;
import ec.edu.upse.lista.UsuarioLista;
import ec.edu.upse.modelo.Usuario;

@SuppressWarnings({ "serial", "rawtypes","unused" })
public class UsuarioEditar extends SelectorComposer {
	
	@Wire
	private Window winUsuarioEditar;

	@Wire
	private Textbox clave;	
	
	@Wire
	private Textbox claveNueva;	
	
	@Wire
	private Textbox confirmaClave;	

	@Wire
	private Textbox correo;
	
	@Wire
	private Textbox nombUsuario;
	
	@Wire
	private Textbox nombres;
	
	@Wire
	private Textbox apellidos;
	
	
	private UsuarioLista usuarioLista; 
	private UsuarioDao usuarioDao = new UsuarioDao();
	private Usuario usuario;
	//private Context context;
	private Integer usu; //***
	
	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		//usu = Context.getInstance().getIdUsuarioLogeado(); //**
		
		//Recupera la ventana padre.
		usuarioLista = (UsuarioLista)Executions.getCurrent().getArg().get("VentanaPadre");

		// Recupera el objeto pasado como parametro. Si no lo recibe, crea uno
		usuario = null; 
		if (Executions.getCurrent().getArg().get("Usuario") != null) {
			usuario = (Usuario)Executions.getCurrent().getArg().get("Usuario");
		}else{
			usuario = new Usuario(); 
		}
	}
	
	/**Validar que claves sean iguales */
	@Listen("onClick=#validar")
	public void validarClavesIguales () {
		try {
			String a = claveNueva.getText();
			String b = confirmaClave.getText();
			if (a.equals(b)==false) {
				Clients.showNotification("Los datos ingresados no coinciden, por favor confirme nuevamente la clave");
			}else {				
				Clients.showNotification("Los datos ingresados coinciden");
			}
		} catch (Exception e) {
		
		}
	}
	
	/**Funcion encriptar*/
	public String encriptar(String clave) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clave.getBytes());

		int size = b.length;
		StringBuilder h = new StringBuilder(size);
		for (int i = 0; i < size; i++) {

			int u = b[i] & 255;

			if (u < 16)
			{
				h.append("0").append(Integer.toHexString(u));
			}
			else
			{
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}
	
	/** Método para validar email*/
	boolean ValidarEmail (String correo) {
		Pattern pat = null;
		Matcher mat = null;        
		pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		}else{
			return false;
		}        
	}
	
	/** Validar Datos */
	public boolean isValidarDatos() {
		 try {
			 Boolean resultado = true;
			
			if(ValidarEmail(correo.getText()) == false) {
				Clients.showNotification("El correo ingresado no es válido!!");
				correo.focus();
				resultado = false;
				return resultado;
			}
			
			if(correo.getText() == null) {
				Clients.showNotification("Por favor ingrese un correo electrónico.!!");
				correo.focus();
				return resultado;
			}
			if(nombUsuario.getText() == null) {
				Clients.showNotification("Por favor ingrese nombre de usuario.!!");
				nombUsuario.focus();
				return resultado;
			}
			if(clave.getText() == null) {
				Clients.showNotification("Por favor ingrese una clave de usuario.!!");
				clave.focus();
				return resultado;
			}
			if(nombres.getText() == null) {
				Clients.showNotification("Por favor ingrese nombres.!!");
				nombres.focus();
				return resultado;
			}
			if(apellidos.getText() == null) {
				Clients.showNotification("Por favor ingrese apellidos.!!");
				apellidos.focus();
				return resultado;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	 }

	
	@Listen("onClick=#grabar")
	public void grabar(){
		System.out.println("entra grabando");
		try {

			if (!isValidarDatos()){
				//Clients.showNotification("Por favor verifique que los campos con * esten llenos.!");
				return;
			}else {
				// Inicia la transaccion
				usuarioDao.getEntityManager().getTransaction().begin();	

				if (usuario.getIdUsuario() == null) {
					usuario.setClave(encriptar(clave.getText()));					
					
					usuarioDao.getEntityManager().persist(usuario);

				}else{ 
					usuario = (Usuario) usuarioDao.getEntityManager().merge(usuario);
					usuario.setClave(encriptar(clave.getText()));				
				}
				if(usuario.getEstado()==null)
					usuario.setEstado("A");
					

				// Cierra la transaccion.
				usuarioDao.getEntityManager().getTransaction().commit();

				Clients.showNotification("Proceso Ejecutado con exito.");

				// Actualiza la lista
				usuarioLista.buscar();

				// Cierra la ventana
				salir();			
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			// Si hay error, reversa la transaccion.
			usuarioDao.getEntityManager().getTransaction().rollback();
		}		
	}
	
	
	
	/**
	 * Escucha el evento "onClick" del objeto "salir"
	 */
	@Listen("onClick=#salir")
	public void salir(){
		winUsuarioEditar.detach();
	}

	/**
	 * Retorna una lista de usuarios, se deberia recuperar de una tabla.
	 * @return
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
