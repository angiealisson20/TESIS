package ec.edu.upse.editar;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.CursoDao;
import ec.edu.upse.dao.DistributivoDao;
import ec.edu.upse.dao.DocenteDao;
import ec.edu.upse.dao.MateriaDao;
import ec.edu.upse.lista.DistributivoLista;
import ec.edu.upse.modelo.TblSgaCurso;
import ec.edu.upse.modelo.TblSgaDistributivo;
import ec.edu.upse.modelo.TblSgaDocente;
import ec.edu.upse.modelo.TblSgaMateria;

@SuppressWarnings({ "serial", "rawtypes" })
public class DistributivoEditar extends SelectorComposer {
	
	@Wire
	private Window winDistributivoEditar;
	
	private DistributivoLista distributivoLista;
	
	private DistributivoDao distributivoDao = new DistributivoDao();
	private DocenteDao docenteDao= new DocenteDao();
	private MateriaDao materiaDao = new MateriaDao();
	private CursoDao cursoDao = new CursoDao();
	private TblSgaDistributivo distributivo;
	
	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);

		// Recupera la ventana padre.
		distributivoLista = (DistributivoLista)Executions.getCurrent().getArg().get("VentanaPadre");

		// Recupera el objeto pasado como parametro. Si no lo recibe, crea uno
		distributivo = null; 
		if (Executions.getCurrent().getArg().get("TblSgaDistributivo") != null) {
			System.out.println("entra grabando");
			distributivo = (TblSgaDistributivo) Executions.getCurrent().getArg().get("TblSgaDistributivo");
		}else{
			distributivo = new TblSgaDistributivo(); 
		}
	}
	
	@Listen("onClick=#grabar")
	public void grabar(){
		System.out.println("entra grabando");
		try {
			// Inicia la transaccion
			distributivoDao.getEntityManager().getTransaction().begin();
			
			if (distributivo.getDistId() == null) {
				distributivoDao.getEntityManager().persist(distributivo);
			}else{
				distributivo = (TblSgaDistributivo) distributivoDao.getEntityManager().merge(distributivo);
			}

			// Cierra la transaccion.
			distributivoDao.getEntityManager().getTransaction().commit();
			
			Clients.showNotification("Proceso Ejecutado con exito.");
			
			distributivoLista.buscar();			
			salir(); 			
		} catch (Exception e) {
			e.printStackTrace();

			// Si hay error, reversa la transaccion.
			distributivoDao.getEntityManager().getTransaction().rollback();
		}
	}
	
	@Listen("onClick=#salir")
	public void salir(){
		winDistributivoEditar.detach();
	}

	public Window getWinDistributivoEditar() {
		return winDistributivoEditar;
	}

	public void setWinDistributivoEditar(Window winDistributivoEditar) {
		this.winDistributivoEditar = winDistributivoEditar;
	}

	public DistributivoLista getDistributivoLista() {
		return distributivoLista;
	}

	public void setDistributivoLista(DistributivoLista distributivoLista) {
		this.distributivoLista = distributivoLista;
	}

	public DistributivoDao getDistributivoDao() {
		return distributivoDao;
	}

	public void setDistributivoDao(DistributivoDao distributivoDao) {
		this.distributivoDao = distributivoDao;
	}

	public DocenteDao getDocenteDao() {
		return docenteDao;
	}

	public void setDocenteDao(DocenteDao docenteDao) {
		this.docenteDao = docenteDao;
	}

	public MateriaDao getMateriaDao() {
		return materiaDao;
	}

	public void setMateriaDao(MateriaDao materiaDao) {
		this.materiaDao = materiaDao;
	}

	public TblSgaDistributivo getDistributivo() {
		return distributivo;
	}

	public void setDistributivo(TblSgaDistributivo distributivo) {
		this.distributivo = distributivo;
	}

	public List<TblSgaDocente> getDocente() {
		return docenteDao.getDocente();
	}
	
	public List<TblSgaMateria> getMaterias(){
		return materiaDao.getMaterias();
	}

	public List<TblSgaCurso> getCursos(){
		return cursoDao.getCursos();
	}

	public CursoDao getCursoDao() {
		return cursoDao;
	}

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}
}
