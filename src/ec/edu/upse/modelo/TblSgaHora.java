package ec.edu.upse.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tbl_sga_hora database table.
 * 
 */
@Entity
@Table(name="tbl_sga_hora")
@NamedQuery(name="TblSgaHora.findAll", query="SELECT t FROM TblSgaHora t")
public class TblSgaHora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hrs_id_pk")
	private Integer hrsIdPk;

	private String estado;

	@Column(name="hrs_descripcion")
	private String hrsDescripcion;

	@Column(name="hrs_fin")
	private Time hrsFin;

	@Column(name="hrs_inicio")
	private Time hrsInicio;

	//bi-directional many-to-one association to TblSgaHorario
	@OneToMany(mappedBy="tblSgaHora")
	private List<TblSgaHorario> tblSgaHorarios;

	public TblSgaHora() {
	}

	public Integer getHrsIdPk() {
		return this.hrsIdPk;
	}

	public void setHrsIdPk(Integer hrsIdPk) {
		this.hrsIdPk = hrsIdPk;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHrsDescripcion() {
		return this.hrsDescripcion;
	}

	public void setHrsDescripcion(String hrsDescripcion) {
		this.hrsDescripcion = hrsDescripcion;
	}

	public String getHrsFin() {
		System.out.println("Hola: " + this.hrsFin.toString());
		return this.hrsFin.toString();
	}

	public void setHrsFin(String hrsFin) {
		Time time = Time.valueOf(hrsFin);
		System.out.println(time);
		this.hrsFin = time;
	}

	public String getHrsInicio() {
		System.out.println("Hola: " + this.hrsInicio.toString());
		return this.hrsInicio.toString();
	}

	public void setHrsInicio(String hrsInicio) {
		Time time = Time.valueOf(hrsInicio);
		System.out.println(time);
		this.hrsInicio = time;
	}

	public List<TblSgaHorario> getTblSgaHorarios() {
		return this.tblSgaHorarios;
	}

	public void setTblSgaHorarios(List<TblSgaHorario> tblSgaHorarios) {
		this.tblSgaHorarios = tblSgaHorarios;
	}

	public TblSgaHorario addTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().add(tblSgaHorario);
		tblSgaHorario.setTblSgaHora(this);

		return tblSgaHorario;
	}

	public TblSgaHorario removeTblSgaHorario(TblSgaHorario tblSgaHorario) {
		getTblSgaHorarios().remove(tblSgaHorario);
		tblSgaHorario.setTblSgaHora(null);

		return tblSgaHorario;
	}

}