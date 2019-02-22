package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_sga_estudiante database table.
 * 
 */
@Entity
@Table(name="tbl_sga_estudiante")
@NamedQuery(name="TblSgaEstudiante.findAll", query="SELECT t FROM TblSgaEstudiante t")
public class TblSgaEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="est_id")
	private Integer estId;

	@Column(name="est_apell")
	private String estApell;

	@Column(name="est_ced")
	private Integer estCed;

	@Column(name="est_cod")
	private String estCod;

	@Column(name="est_estado")
	private String estEstado;

	@Column(name="est_matric")
	private String estMatric;

	@Column(name="est_nomb")
	private String estNomb;

	//bi-directional many-to-one association to TblSgaMatricula
	@OneToMany(mappedBy="tblSgaEstudiante")
	private List<TblSgaMatricula> tblSgaMatriculas;

	public TblSgaEstudiante() {
	}

	public Integer getEstId() {
		return this.estId;
	}

	public void setEstId(Integer estId) {
		this.estId = estId;
	}

	public String getEstApell() {
		return this.estApell;
	}

	public void setEstApell(String estApell) {
		this.estApell = estApell;
	}

	public Integer getEstCed() {
		return this.estCed;
	}

	public void setEstCed(Integer estCed) {
		this.estCed = estCed;
	}

	public String getEstCod() {
		return this.estCod;
	}

	public void setEstCod(String estCod) {
		this.estCod = estCod;
	}

	public String getEstEstado() {
		return this.estEstado;
	}

	public void setEstEstado(String estEstado) {
		this.estEstado = estEstado;
	}

	public String getEstMatric() {
		return this.estMatric;
	}

	public void setEstMatric(String estMatric) {
		this.estMatric = estMatric;
	}

	public String getEstNomb() {
		return this.estNomb;
	}

	public void setEstNomb(String estNomb) {
		this.estNomb = estNomb;
	}

	public List<TblSgaMatricula> getTblSgaMatriculas() {
		return this.tblSgaMatriculas;
	}

	public void setTblSgaMatriculas(List<TblSgaMatricula> tblSgaMatriculas) {
		this.tblSgaMatriculas = tblSgaMatriculas;
	}

	public TblSgaMatricula addTblSgaMatricula(TblSgaMatricula tblSgaMatricula) {
		getTblSgaMatriculas().add(tblSgaMatricula);
		tblSgaMatricula.setTblSgaEstudiante(this);

		return tblSgaMatricula;
	}

	public TblSgaMatricula removeTblSgaMatricula(TblSgaMatricula tblSgaMatricula) {
		getTblSgaMatriculas().remove(tblSgaMatricula);
		tblSgaMatricula.setTblSgaEstudiante(null);

		return tblSgaMatricula;
	}

}