package ec.edu.upse.util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.UUID;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import ec.edu.upse.dao.ClaseDao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PrintReport {
	
	public static final String FORMATO_PDF = "PDF";
	public static final String FORMATO_XLS = "XLS";
	
	private JasperReport report;
	private JasperPrint reportFilled;
	private JasperViewer viewer;
	public void crearReporte(String path, ClaseDao claseDAO,Map<String, Object> param) {
		try {
			String pathAbsoluto = Executions.getCurrent()
		              .getDesktop().getWebApp()
		              .getRealPath("/");
			String nombreReporte = pathAbsoluto + path;
			Connection cn = claseDAO.abreConexion();

			String nombreArchivo = null;
			nombreArchivo = pathAbsoluto + "temp";
			//Clients.showNotification("nombre de ruta: " + nombreArchivo);
			
			System.out.println(nombreArchivo);
			//pregunta si la carpeta existe
			File folder = new File(nombreArchivo);
			if (folder.exists()) {
			}else {
				folder.mkdir();
			}
			
			// Obtiene un nombre aleatorio para el reporte
			nombreArchivo = nombreArchivo + "/" + UUID.randomUUID().toString() + ".pdf";
			Messagebox.show("nombre de ruta y archivo: " + nombreArchivo);
			
			
			
			System.out.println(nombreArchivo);
			byte[] b = null;
			b = JasperRunManager.runReportToPdf(nombreReporte, param, cn);
			FileOutputStream fos = new FileOutputStream(nombreArchivo);
			fos.write(b);
			fos.close();
			Filedownload.save(new File(nombreArchivo), "pdf"); 
			/*
			nombreArchivo = nombreArchivo + "/" + UUID.randomUUID().toString() + ".pdf";
			Messagebox.show("nombre de ruta y archivo: " + nombreArchivo);
			*/
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void showReport(String titulo) {
		try {
			viewer = new JasperViewer(reportFilled,false);
			viewer.setTitle(titulo);
			viewer.setVisible(true);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void exportToPDF(String destino) {
		try {
			//JasperExportManager.exportReportToPdfFile(destino);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void imprimirReporte() {
		try {
			JasperPrintManager.printReport(reportFilled, false);
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
