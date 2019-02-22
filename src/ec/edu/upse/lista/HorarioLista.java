package ec.edu.upse.lista;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.HorarioDao;
import ec.edu.upse.dao.UsuarioperfilDao;
import ec.edu.upse.modelo.TblSgaHorario;
import ec.edu.upse.modelo.Usuarioperfil;

public class HorarioLista extends SelectorComposer<Component> {
	
	@Wire
	private Window winListaHorario;
	
	@Wire
	private Combobox cboCurso;
	
	@Wire
	private Combobox cboPeriodo;
	
	@Wire
	private Combobox cboParalelo;
	
	@Wire 
	private Listbox lstHorarios;
	
	private HorarioDao horarioDao = new HorarioDao();
	
	private TblSgaHorario horarioSeleccionado;
	private List<TblSgaHorario> horarios;
	
	
	
	
	
	

}
