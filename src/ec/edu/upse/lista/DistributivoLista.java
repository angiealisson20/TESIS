package ec.edu.upse.lista;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ec.edu.upse.dao.DistributivoDao;
import ec.edu.upse.modelo.TblSgaDistributivo;

@SuppressWarnings("serial")
public class DistributivoLista extends SelectorComposer<Component> {
	
	@Wire
	private Window winListaDistributivo;
	
	@Wire
	private Textbox txtBuscar;
	
	@Wire 
	private Listbox lstDistributivo;
	
	private DistributivoDao distributivoDao = new DistributivoDao();
	
	private TblSgaDistributivo distributivoSeleccionado;
	private List<TblSgaDistributivo> distributivo;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onClick=#btnBuscar;onOK=#txtBuscar")
	public void buscar(){
		
		System.out.println("entra buscar");
		
		if (distributivo != null) {
			distributivo = null; 
		}
		
		distributivo = distributivoDao.getDistributivos(txtBuscar.getValue());
		lstDistributivo.setModel(new ListModelList(distributivo));
		
		distributivoSeleccionado = null;

	}

	@Listen("onClick=#btnNuevo")
	public void nuevo(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TblSgaDistributivo", null);
		params.put("VentanaPadre", this);
		Window ventanaCargar = (Window) Executions.createComponents("/Estructura/distributivoEditar.zul", winListaDistributivo, params);
		ventanaCargar.doModal();
	}
	
	
	public Window getWinListaDistributivo() {
		return winListaDistributivo;
	}

	public void setWinListaDistributivo(Window winListaDistributivo) {
		this.winListaDistributivo = winListaDistributivo;
	}

	public Textbox getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(Textbox txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public Listbox getLstDistributivo() {
		return lstDistributivo;
	}

	public void setLstDistributivo(Listbox lstDistributivo) {
		this.lstDistributivo = lstDistributivo;
	}

	public DistributivoDao getDistributivoDao() {
		return distributivoDao;
	}

	public void setDistributivoDao(DistributivoDao distributivoDao) {
		this.distributivoDao = distributivoDao;
	}

	public TblSgaDistributivo getDistributivoSeleccionado() {
		return distributivoSeleccionado;
	}

	public void setDistributivoSeleccionado(TblSgaDistributivo distributivoSeleccionado) {
		this.distributivoSeleccionado = distributivoSeleccionado;
	}

	public List<TblSgaDistributivo> getDistributivo() {
		return distributivo;
	}

	public void setDistributivo(List<TblSgaDistributivo> distributivo) {
		this.distributivo = distributivo;
	}

	
	

}
