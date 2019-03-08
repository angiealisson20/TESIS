package ec.edu.upse.lista;

import java.util.ArrayList;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.CursoParaleloDao;
import ec.edu.upse.dao.DiasDao;
import ec.edu.upse.dao.EspaciofisicoDao;
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
import ec.edu.upse.util.PrintReport;

@SuppressWarnings({ "serial", "deprecation", "unused" })
public class HorarioLista extends SelectorComposer<Component> {

	@Wire private Window winHorario;
	@Wire private Combobox cboPeriodo;
	@Wire private Combobox cboNivel;
	@Wire private Combobox cboCursos;
	@Wire private Combobox cboParalelo;
	@Wire private Listbox lstHorario1;
	@Wire private Listbox lstHorario2;
	@Wire private Listbox lstHorario3;
	@Wire private Listbox lstHorario4;
	@Wire private Listbox lstHorario5;

	private HorarioDao horarioDao = new HorarioDao();
	private PeriodoLectivoDao periodoDao = new PeriodoLectivoDao();
	private PeriodoNivelDao periodoNivelDao = new PeriodoNivelDao();
	private PeriodoCursoDao periodoCursoDao = new PeriodoCursoDao();
	private CursoParaleloDao cursoParaleloDao = new CursoParaleloDao();
	
	private DiasDao diasDao = new DiasDao();
	private EspaciofisicoDao espFisicoDao = new EspaciofisicoDao();

	private TblSgaHorario horarioSeleccionado;
	
	private List<TblSgaHorario> horarios;
	
	private List<TblSgaHorario> horariosLunes;
	private List<TblSgaHorario> horariosMartes;
	private List<TblSgaHorario> horariosMiercoles;
	private List<TblSgaHorario> horariosJueves;
	private List<TblSgaHorario> horariosViernes;
	
	private TblSgaCurso curso;
	private TblSgaParalelo paralelo;
	private TblSgaNivel nivel;
	private TblSgaPeriodolectivo periodo;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		
		System.out.println("entra buscar");
				
		TblSgaPeriodolectivo periodo = (TblSgaPeriodolectivo)cboPeriodo.getSelectedItem().getValue();
		List<TblSgaPeriodonivel> periodoNiveles = periodoNivelDao.getNivelByPeriodo(periodo.getPerId());
		
		TblSgaNivel nivel = (TblSgaNivel)cboNivel.getSelectedItem().getValue();
		List<TblSgaPeriodoncurso> periodoCursoLista = periodoCursoDao.getCursoByNivel(nivel.getNivelId());
		
		TblSgaCurso curso = (TblSgaCurso)cboCursos.getSelectedItem().getValue();
		List<TblSgaCursoparalelo> cursoParaleloLista = cursoParaleloDao.getParalelobyCurso(curso.getCurId());
		
		TblSgaParalelo paralelo = (TblSgaParalelo)cboParalelo.getSelectedItem().getValue();
		List<TblSgaCursoparalelo> cursoParaleloList= cursoParaleloDao.getParalelobyCurso(paralelo.getParalId());
		
		horariosLunes = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId(),1);
		horariosMartes = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId(),2);
		horariosMiercoles = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId(),3);
		horariosJueves = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId(),4);
		horariosViernes = horarioDao.getObtenerConsulta(periodo.getPerId(), nivel.getNivelId(), curso.getCurId(), paralelo.getParalId(),5);
		lstHorario1.setModel(new ListModelList(horariosLunes));
		lstHorario2.setModel(new ListModelList(horariosMartes));
		lstHorario3.setModel(new ListModelList(horariosMiercoles));
		lstHorario4.setModel(new ListModelList(horariosJueves));
		lstHorario5.setModel(new ListModelList(horariosViernes));
		
		horarioSeleccionado=null;
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
	
	@Listen("onClick=#btnNuevo")
	public void nuevo(){
		if(cboParalelo.getSelectedItem().getValue() == null){
			Messagebox.show("Information is pressed", "Information", Messagebox.OK, Messagebox.INFORMATION);
		}
		else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TblSgaHorario", null);
			params.put("VentanaPadre", this);
			TblSgaCurso curso = (TblSgaCurso)cboCursos.getSelectedItem().getValue();
			TblSgaParalelo paralelo = (TblSgaParalelo)cboParalelo.getSelectedItem().getValue();
			params.put("curso", curso);
			params.put("paralelo", paralelo);
			Window ventanaCargar = (Window) Executions.createComponents("/Estructura/horarioEditar.zul", winHorario, params);
			ventanaCargar.doModal();	
		}
		
	}
	@SuppressWarnings("unlikely-arg-type")
	@Listen("onClick=#btnImprimir")
	public void imprimir() {
		try {
			PrintReport pr = new PrintReport();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("curso", cboCursos.getSelectedItem().getValue());
			/*param.put("nivel", cboNivel.getSelectedItem().getValue());
			param.put("paralelo", cboParalelo.getSelectedItem().getValue());
			param.put("periodo", cboPeriodo.getSelectedItem().getValue());
			param.put("dia", diasDao.getDias().equals("LUNES"));
			param.put("esp_fisico", espFisicoDao.getEspfisico().equals("LABORATORIO DE TIC"));*/
			
			pr.crearReporte("/reporte/prueba.jasper", diasDao, param);
			pr.showReport("Deuda de Clientes");
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}
		
	@Listen("onClick=#btnEditar")
	public void editar(){
		if (horarioSeleccionado == null) {
			Clients.showNotification("Debe seleccionar un horario.");
			return; 
		}

		// Actualiza la instancia antes de enviarla a editar.
		horarioDao.getEntityManager().refresh(horarioSeleccionado);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TblSgaHorario", horarioSeleccionado);
		params.put("VentanaPadre", this);
		TblSgaCurso curso = (TblSgaCurso)cboCursos.getSelectedItem().getValue();
		TblSgaParalelo paralelo = (TblSgaParalelo)cboParalelo.getSelectedItem().getValue();
		params.put("curso", curso);
		params.put("paralelo", paralelo);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/horarioEditar.zul", winHorario, params);
		ventanaCargar.doModal();
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

	public Window getWinHorario() {
		return winHorario;
	}

	public void setWinHorario(Window winHorario) {
		this.winHorario = winHorario;
	}

	public Combobox getCboPeriodo() {
		return cboPeriodo;
	}

	public void setCboPeriodo(Combobox cboPeriodo) {
		this.cboPeriodo = cboPeriodo;
	}

	public Combobox getCboNivel() {
		return cboNivel;
	}

	public void setCboNivel(Combobox cboNivel) {
		this.cboNivel = cboNivel;
	}

	public Combobox getCboCursos() {
		return cboCursos;
	}

	public void setCboCursos(Combobox cboCursos) {
		this.cboCursos = cboCursos;
	}

	public Combobox getCboParalelo() {
		return cboParalelo;
	}

	public void setCboParalelo(Combobox cboParalelo) {
		this.cboParalelo = cboParalelo;
	}

	public Listbox getLstHorario1() {
		return lstHorario1;
	}

	public void setLstHorario1(Listbox lstHorario1) {
		this.lstHorario1 = lstHorario1;
	}

	public Listbox getLstHorario2() {
		return lstHorario2;
	}

	public void setLstHorario2(Listbox lstHorario2) {
		this.lstHorario2 = lstHorario2;
	}

	public Listbox getLstHorario3() {
		return lstHorario3;
	}

	public void setLstHorario3(Listbox lstHorario3) {
		this.lstHorario3 = lstHorario3;
	}

	public Listbox getLstHorario4() {
		return lstHorario4;
	}

	public void setLstHorario4(Listbox lstHorario4) {
		this.lstHorario4 = lstHorario4;
	}

	public Listbox getLstHorario5() {
		return lstHorario5;
	}

	public void setLstHorario5(Listbox lstHorario5) {
		this.lstHorario5 = lstHorario5;
	}

	public List<TblSgaHorario> getHorariosLunes() {
		return horariosLunes;
	}

	public void setHorariosLunes(List<TblSgaHorario> horariosLunes) {
		this.horariosLunes = horariosLunes;
	}

	public List<TblSgaHorario> getHorariosMartes() {
		return horariosMartes;
	}

	public void setHorariosMartes(List<TblSgaHorario> horariosMartes) {
		this.horariosMartes = horariosMartes;
	}

	public List<TblSgaHorario> getHorariosMiercoles() {
		return horariosMiercoles;
	}

	public void setHorariosMiercoles(List<TblSgaHorario> horariosMiercoles) {
		this.horariosMiercoles = horariosMiercoles;
	}

	public List<TblSgaHorario> getHorariosJueves() {
		return horariosJueves;
	}

	public void setHorariosJueves(List<TblSgaHorario> horariosJueves) {
		this.horariosJueves = horariosJueves;
	}

	public List<TblSgaHorario> getHorariosViernes() {
		return horariosViernes;
	}

	public void setHorariosViernes(List<TblSgaHorario> horariosViernes) {
		this.horariosViernes = horariosViernes;
	}

	public HorarioDao getHorarioDao() {
		return horarioDao;
	}

	public void setHorarioDao(HorarioDao horarioDao) {
		this.horarioDao = horarioDao;
	}

	public PeriodoLectivoDao getPeriodoDao() {
		return periodoDao;
	}

	public void setPeriodoDao(PeriodoLectivoDao periodoDao) {
		this.periodoDao = periodoDao;
	}

	public PeriodoNivelDao getPeriodoNivelDao() {
		return periodoNivelDao;
	}

	public void setPeriodoNivelDao(PeriodoNivelDao periodoNivelDao) {
		this.periodoNivelDao = periodoNivelDao;
	}

	public PeriodoCursoDao getPeriodoCursoDao() {
		return periodoCursoDao;
	}

	public void setPeriodoCursoDao(PeriodoCursoDao periodoCursoDao) {
		this.periodoCursoDao = periodoCursoDao;
	}

	public CursoParaleloDao getCursoParaleloDao() {
		return cursoParaleloDao;
	}

	public void setCursoParaleloDao(CursoParaleloDao cursoParaleloDao) {
		this.cursoParaleloDao = cursoParaleloDao;
	}

	public DiasDao getDiasDao() {
		return diasDao;
	}

	public void setDiasDao(DiasDao diasDao) {
		this.diasDao = diasDao;
	}

	public TblSgaHorario getHorarioSeleccionado() {
		return horarioSeleccionado;
	}

	public void setHorarioSeleccionado(TblSgaHorario horarioSeleccionado) {
		this.horarioSeleccionado = horarioSeleccionado;
	}

	public List<TblSgaHorario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<TblSgaHorario> horarios) {
		this.horarios = horarios;
	}

	public TblSgaCurso getCurso() {
		return curso;
	}

	public void setCurso(TblSgaCurso curso) {
		this.curso = curso;
	}

	public TblSgaParalelo getParalelo() {
		return paralelo;
	}

	public void setParalelo(TblSgaParalelo paralelo) {
		this.paralelo = paralelo;
	}

	public TblSgaNivel getNivel() {
		return nivel;
	}

	public void setNivel(TblSgaNivel nivel) {
		this.nivel = nivel;
	}

	public TblSgaPeriodolectivo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(TblSgaPeriodolectivo periodo) {
		this.periodo = periodo;
	}
}