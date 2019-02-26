package ec.edu.upse.util;



public class Context {
	
private final static Context instance = new Context();
	
	
	private Integer idUsuarioLogeado;
	public static Context getInstance() {
		return instance;
	}


	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

}
