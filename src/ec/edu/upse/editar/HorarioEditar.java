package ec.edu.upse.editar;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.CursoParaleloDao;
import ec.edu.upse.dao.DiasDao;
import ec.edu.upse.dao.DistributivoDao;
import ec.edu.upse.dao.EspaciofisicoDao;
import ec.edu.upse.dao.HoraDao;
import ec.edu.upse.dao.HorarioDao;
import ec.edu.upse.dao.MateriaDao;
import ec.edu.upse.lista.HorarioLista;
import ec.edu.upse.modelo.TblSgaCurso;
import ec.edu.upse.modelo.TblSgaCursoparalelo;
import ec.edu.upse.modelo.TblSgaDia;
import ec.edu.upse.modelo.TblSgaDistributivo;
import ec.edu.upse.modelo.TblSgaDocente;
import ec.edu.upse.modelo.TblSgaEspaciofisico;
import ec.edu.upse.modelo.TblSgaHora;
import ec.edu.upse.modelo.TblSgaHorario;
import ec.edu.upse.modelo.TblSgaMateria;
import ec.edu.upse.modelo.TblSgaParalelo;

@SuppressWarnings("serial")
public class HorarioEditar extends SelectorComposer<Component> {

	@Wire private Window winHorarioEditar;
	@Wire private Combobox cboMateria;
	@Wire private Combobox cboDocente;
	@Wire private Combobox cboPeriodo;
	@Wire private Combobox cboNivel;
	@Wire private Combobox cboCursos;
	@Wire private Combobox cboParalelo;



	private HorarioLista horarioLista;

	private HorarioDao horarioDao = new HorarioDao();
	private DiasDao diasDao = new DiasDao();
	private EspaciofisicoDao espaciofisicoDao = new EspaciofisicoDao();
	private HoraDao horaDao = new HoraDao();
	private DistributivoDao distributivoDao = new DistributivoDao();
	private MateriaDao materiaDao = new MateriaDao();
	private CursoParaleloDao cursoparaleloDao = new CursoParaleloDao();
	
	
	private TblSgaCurso curso;
	@SuppressWarnings("unused")
	private TblSgaParalelo paralelo;
	private TblSgaMateria materia;
	@SuppressWarnings("unused")
	private TblSgaDocente docente;

	private TblSgaHorario horario;

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		// Recupera la ventana padre.
		horarioLista = (HorarioLista)Executions.getCurrent().getArg().get("VentanaPadre");

		// Recupera el objeto pasado como parametro. Si no lo recibe, crea uno
		horario = null; 
		if (Executions.getCurrent().getArg().get("TblSgaHorario") != null) {
			horario = (TblSgaHorario) Executions.getCurrent().getArg().get("TblSgaHorario");
			
		}else{
			horario = new TblSgaHorario(); 
		}
		curso = (TblSgaCurso) Executions.getCurrent().getArg().get("curso");
		paralelo = (TblSgaParalelo) Executions.getCurrent().getArg().get("paralelo");
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onChange = #cboMateria")
	public void cambiaDocente(){
		cboDocente.setText("");
		TblSgaMateria materia = (TblSgaMateria)cboMateria.getSelectedItem().getValue();
		
		List<TblSgaDistributivo> distributivoLista = distributivoDao.getDocentebyMateria(materia.getMatId());
		List<TblSgaDocente> docente = new ArrayList<>();
		
		for(TblSgaDistributivo matdocente : distributivoLista)
			docente.add(matdocente.getTblSgaDocente());
		
		cboDocente.setModel(new ListModelList(docente));
	}
	
	
	
	
	@SuppressWarnings("unused")
	@Listen("onClick=#grabar")
	public void grabar(){
		System.out.println("entra grabando");
		try {
		
		
		TblSgaCursoparalelo curpar = new TblSgaCursoparalelo();	
		TblSgaDistributivo distribut = new TblSgaDistributivo();
			
			List<TblSgaCursoparalelo> cursoParalelo = cursoparaleloDao.getParalelobyCurso(curso.getCurId());
		
			System.out.println(curso.getCurId());
			
			for(TblSgaCursoparalelo par : cursoParalelo){
				if(par.getTblSgaPeriodoncurso().getTblSgaCurso().getCurId() == curso.getCurId()){
					curpar = par;
				}
			}
			
			
			//TblSgaCursoparalelo cursoPar = cursoparaleloDao.getCursoParalelos((TblSgaCurso)cboCursos.getSelectedItem().getValue(), (TblSgaParalelo)cboParalelo.getSelectedItem().getValue());		
			TblSgaDistributivo tblSgaDistributivo = distributivoDao.getDistributivo((TblSgaMateria)cboMateria.getSelectedItem().getValue(), (TblSgaDocente)cboDocente.getSelectedItem().getValue());
			
			// Inicia la transaccion
			horarioDao.getEntityManager().getTransaction().begin();
			
			if (horario.getHorIdPk() == null) {
				//Nuevo
				horario.setTblSgaCursoparalelo(curpar);
				horario.setTblSgaDistributivo(tblSgaDistributivo);
				horarioDao.getEntityManager().persist(horario);
			}else{
				//Actualizar
				horario.getTblSgaCursoparalelo();
				horario.getTblSgaDistributivo();
				horario = (TblSgaHorario) horarioDao.getEntityManager().merge(horario);
			}
			if(horario.getEstado()==null)
				horario.setEstado("A");

			// Cierra la transaccion.
			horarioDao.getEntityManager().getTransaction().commit();
			
			Clients.showNotification("Proceso Ejecutado con exito.");
			
			horarioLista.buscar();			
		salir(); 			
		} catch (Exception e) {
			e.getMessage();

			// Si hay error, reversa la transaccion.
			horarioDao.getEntityManager().getTransaction().rollback();
		}
	}
	

	public List<TblSgaMateria> getMaterias(){
		return materiaDao.getMaterias();
	}
	
	public List<TblSgaDocente> getDocente(){
		return null;
	}

	public List<TblSgaDistributivo> getDistributivo(){
		return distributivoDao.getDocentebyMateria(materia.getMatId());
	}
	
	
	public Combobox getCboMateria() {
		return cboMateria;
	}


	public void setCboMateria(Combobox cboMateria) {
		this.cboMateria = cboMateria;
	}


	public Combobox getCboDocente() {
		return cboDocente;
	}


	public void setCboDocente(Combobox cboDocente) {
		this.cboDocente = cboDocente;
	}


	public DistributivoDao getDistributivoDao() {
		return distributivoDao;
	}


	public void setDistributivoDao(DistributivoDao distributivoDao) {
		this.distributivoDao = distributivoDao;
	}


	public MateriaDao getMateriaDao() {
		return materiaDao;
	}


	public void setMateriaDao(MateriaDao materiaDao) {
		this.materiaDao = materiaDao;
	}


	public List<TblSgaDia> getDias() {
		return diasDao.getDias();
	}

	public List<TblSgaEspaciofisico> getEspfisico() {
		return espaciofisicoDao.getEspfisico();
	}

	public List<TblSgaHora> getHoras(){
		return horaDao.getHoras();
	}

	public DiasDao getDiasDao() {
		return diasDao;
	}

	public void setDiasDao(DiasDao diasDao) {
		this.diasDao = diasDao;
	}



	public Window getWinHorarioEditar() {
		return winHorarioEditar;
	}

	public void setWinHorarioEditar(Window winHorarioEditar) {
		this.winHorarioEditar = winHorarioEditar;
	}

	public HorarioLista getHorarioLista() {
		return horarioLista;
	}

	public void setHorarioLista(HorarioLista horarioLista) {
		this.horarioLista = horarioLista;
	}

	public HorarioDao getHorarioDao() {
		return horarioDao;
	}

	public void setHorarioDao(HorarioDao horarioDao) {
		this.horarioDao = horarioDao;
	}

	public EspaciofisicoDao getEspaciofisicoDao() {
		return espaciofisicoDao;
	}

	public void setEspaciofisicoDao(EspaciofisicoDao espaciofisicoDao) {
		this.espaciofisicoDao = espaciofisicoDao;
	}

	public TblSgaHorario getHorario() {
		return horario;
	}

	public void setHorario(TblSgaHorario horario) {
		this.horario = horario;
	}


	@Listen("onClick=#salir")
	public void salir(){
		winHorarioEditar.detach();
	}


}
