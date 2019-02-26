package ec.edu.upse.controlador;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;

import ec.edu.upse.dao.UsuarioperfilDao;
import ec.edu.upse.modelo.Usuario;
import ec.edu.upse.util.SecurityUtil;



public class Index {
private UsuarioperfilDao usuarioPerfilDao = new UsuarioperfilDao();
	
	
	@AfterCompose
	public void aferCompose(@ContextParam(ContextType.VIEW) Component view) throws IOException{
		Selectors.wireComponents(view, this, false);
		principal();
	}
	
	private void principal() {
		Usuario objeto  = usuarioPerfilDao.getUsuario(SecurityUtil.getUser().getUsername().trim()); 
		System.out.println("JAJAJ");
		Executions.getCurrent().sendRedirect("/menuPrincipal.zul");
					
	}

}
