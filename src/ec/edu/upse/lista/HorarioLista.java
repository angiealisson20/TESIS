package ec.edu.upse.lista;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.CursoParaleloDao;
import ec.edu.upse.dao.DiasDao;
import ec.edu.upse.dao.HorarioDao;
import ec.edu.upse.dao.PeriodoCursoDao;
import ec.edu.upse.dao.PeriodoLectivoDao;
import ec.edu.upse.dao.PeriodoNivelDao;
import ec.edu.upse.modelo.TblSgaCurso;
import ec.edu.upse.modelo.TblSgaCursoparalelo;
import ec.edu.upse.modelo.TblSgaDia;
import ec.edu.upse.modelo.TblSgaHorario;
import ec.edu.upse.modelo.TblSgaNivel;
import ec.edu.upse.modelo.TblSgaParalelo;
import ec.edu.upse.modelo.TblSgaPeriodolectivo;
import ec.edu.upse.modelo.TblSgaPeriodoncurso;
import ec.edu.upse.modelo.TblSgaPeriodonivel;

@SuppressWarnings({ "serial", "deprecation", "unused" })
public class HorarioLista extends SelectorComposer<Component> {

	@Wire private Window winHorario;
	@Wire private Combobox cboPeriodo;
	@Wire private Combobox cboNivel;
	@Wire private Combobox cboCursos;
	@Wire private Combobox cboParalelo;
	@Wire private Listbox lstHorario;



	private HorarioDao horarioDao = new HorarioDao();
	private PeriodoLectivoDao periodoDao = new PeriodoLectivoDao();
	private PeriodoNivelDao periodoNivelDao = new PeriodoNivelDao();
	private PeriodoCursoDao periodoCursoDao = new PeriodoCursoDao();
	private CursoParaleloDao cursoParaleloDao = new CursoParaleloDao();
	
	private DiasDao diasDao = new DiasDao();

	private TblSgaHorario horarioSeleccionado;
	
	private List<TblSgaHorario> horarios;
	
	private TblSgaCurso curso;
	private TblSgaParalelo paralelo;
	private TblSgaNivel nivel;
	private TblSgaPeriodolectivo periodo;
	
	private List<TblSgaDia> dias;


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		
		System.out.println("entra buscar");
		
		if (horarios != null) {
			horarios = null; 
		}
		
		TblSgaPeriodolectivo periodo = (TblSgaPeriodolectivo)cboPeriodo.getSelectedItem().getValue();
		List<TblSgaPeriodonivel> periodoNiveles = periodoNivelDao.getNivelByPeriodo(periodo.getPerId());
		
		TblSgaNivel nivel = (TblSgaNivel)cboNivel.getSelectedItem().getValue();
		List<TblSgaPeriodoncurso> periodoCursoLista = periodoCursoDao.getCursoByNivel(nivel.getNivelId());
		
		TblSgaCurso curso = (TblSgaCurso)cboCursos.getSelectedItem().getValue();
		List<TblSgaCursoparalelo> cursoParaleloLista = cursoParaleloDao.getParalelobyCurso(curso.getCurId());
		
		TblSgaParalelo paralelo = (TblSgaParalelo)cboParalelo.getSelectedItem().getValue();
		List<TblSgaCursoparalelo> cursoParaleloList= cursoParaleloDao.getParalelobyCurso(paralelo.getParalId());
		
		horarios = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId());
		lstHorario.setModel(new ListModelList(horarios));		
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onChange = #cboPeriodo")
	public void cambiaNiveles() {
		cboNivel.setText("");
		TblSgaPeriodolectivo p = (TblSgaPeriodolectivo)cboPeriodo.getSelectedItem().getValue();
		List<TblSgaPeriodonivel> periodoNiveles = periodoNivelDao.getNivelByPeriodo(p.getPerId());
		List<TblSgaNivel> niveles = new ArrayList<>();

		for(TblSgaPeriodonivel perNivel : periodoNiveles)
			niveles.add(perNivel.getTblSgaNivel());
		///List<Parametrica> ciudades = parametricaDAO.getCiudades(p.getIdParametrica()).get(0).getParametricas();
		cboNivel.setModel(new ListModelList(niveles));
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onChange = #cboNivel")
	public void cambiaCursos() {
		cboCursos.setText("");
		TblSgaNivel p = (TblSgaNivel)cboNivel.getSelectedItem().getValue();

		List<TblSgaPeriodoncurso> periodoCursoLista = periodoCursoDao.getCursoByNivel(p.getNivelId());
		List<TblSgaCurso> cursos = new ArrayList<>();

		for(TblSgaPeriodoncurso perCurso : periodoCursoLista)
			cursos.add(perCurso.getTblSgaCurso());
		///List<Parametrica> ciudades = parametricaDAO.getCiudades(p.getIdParametrica()).get(0).getParametricas();
		cboCursos.setModel(new ListModelList(cursos));
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onChange = #cboCursos")
	public void cambiaParalelos() {
		cboParalelo.setText("");
		TblSgaCurso cp = (TblSgaCurso)cboCursos.getSelectedItem().getValue();

		List<TblSgaCursoparalelo> cursoParaleloLista = cursoParaleloDao.getParalelobyCurso(cp.getCurId());
		List<TblSgaParalelo> paralelos = new ArrayList<>();

		for(TblSgaCursoparalelo parCurso : cursoParaleloLista)
			paralelos.add(parCurso.getTblSgaParalelo());
		///List<Parametrica> ciudades = parametricaDAO.getCiudades(p.getIdParametrica()).get(0).getParametricas();
		cboParalelo.setModel(new ListModelList(paralelos));
		
	
		
	}
	
	


	public List<TblSgaPeriodolectivo> getPeriodos(){
		return periodoDao.getPeriodos();
	}
	public List<TblSgaNivel> getNiveles(){
		return null;
	}
	public List<TblSgaCurso> getCursos(){
		return null;
	}
	
	public List<TblSgaParalelo> getParalelos(){
		return null;
	}

	

}
