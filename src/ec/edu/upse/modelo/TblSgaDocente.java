package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_docente database table.
 * 
 */
@Entity
@Table(name="tbl_sga_docente")
@NamedQuery(name="TblSgaDocente.findAll", query="SELECT t FROM TblSgaDocente t")
public class TblSgaDocente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="docente_id")
	private Integer docenteId;

	@Column(name="docente_ap")
	private String docenteAp;

	@Column(name="docente_ced")
	private String docenteCed;

	@Column(name="docente_estado")
	private String docenteEstado;

	@Column(name="docente_nom")
	private String docenteNom;

	@Column(name="usuariod_fk")
	private Integer usuariodFk;

	//bi-directional many-to-one association to TblSgaDistributivo
	@OneToMany(mappedBy="tblSgaDocente")
	private List<TblSgaDistributivo> tblSgaDistributivos;

	public TblSgaDocente() {
	}

	public Integer getDocenteId() {
		return this.docenteId;
	}

	public void setDocenteId(Integer docenteId) {
		this.docenteId = docenteId;
	}

	public String getDocenteAp() {
		return this.docenteAp;
	}

	public void setDocenteAp(String docenteAp) {
		this.docenteAp = docenteAp;
	}

	public String getDocenteCed() {
		return this.docenteCed;
	}

	public void setDocenteCed(String docenteCed) {
		this.docenteCed = docenteCed;
	}

	public String getDocenteEstado() {
		return this.docenteEstado;
	}

	public void setDocenteEstado(String docenteEstado) {
		this.docenteEstado = docenteEstado;
	}

	public String getDocenteNom() {
		return this.docenteNom;
	}

	public void setDocenteNom(String docenteNom) {
		this.docenteNom = docenteNom;
	}

	public Integer getUsuariodFk() {
		return this.usuariodFk;
	}

	public void setUsuariodFk(Integer usuariodFk) {
		this.usuariodFk = usuariodFk;
	}

	public List<TblSgaDistributivo> getTblSgaDistributivos() {
		return this.tblSgaDistributivos;
	}

	public void setTblSgaDistributivos(List<TblSgaDistributivo> tblSgaDistributivos) {
		this.tblSgaDistributivos = tblSgaDistributivos;
	}

	public TblSgaDistributivo addTblSgaDistributivo(TblSgaDistributivo tblSgaDistributivo) {
		getTblSgaDistributivos().add(tblSgaDistributivo);
		tblSgaDistributivo.setTblSgaDocente(this);

		return tblSgaDistributivo;
	}

	public TblSgaDistributivo removeTblSgaDistributivo(TblSgaDistributivo tblSgaDistributivo) {
		getTblSgaDistributivos().remove(tblSgaDistributivo);
		tblSgaDistributivo.setTblSgaDocente(null);

		return tblSgaDistributivo;
	}

}