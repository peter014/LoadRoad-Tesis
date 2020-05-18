package app.configurations;

public class Constantes {

	//estados transportador
	private static final String DISPONIBLE = "Disponible";
	private static final String OCUPADO = "Ocupado";
	
	//estados pedido
	private static final String PENDIENTE = "Pendiente";
	private static final String ACEPTADO = "Aceptado";
	private static final String ENMARCHA = "EnMarcha";
	private static final String DESCANSANDO = "Descanzando";
	private static final String PROBLEMASVIA = "ProblemasVia";
	private static final String RECHAZADO = "Rechazado";
	private static final String FINALIZADO = "Finalizado";
	private static final String[] CIUDADES = {"Bogotá", "Medellin", "Cali", "Barranquilla", "Cartagena", "Cucuta", "Ibague", "Bucaramanga", "Santa Marta", "Villavicencio", "Valledupar", "Pereira", "Buenaventura", "Pasto", " Manizales", "Neiva", "Tunja"};
	
	public static String getDisponible() {
		return DISPONIBLE;
	}

	public static String[] getCiudades() {
		return CIUDADES;
	}

	public static String getRechazado() {
		return RECHAZADO;
	}

	public static String getPendiente() {
		return PENDIENTE;
	}

	public static String getAceptado() {
		return ACEPTADO;
	}

	public static String getFinalizado() {
		return FINALIZADO;
	}

	public static String getEnmarcha() {
		return ENMARCHA;
	}

	public static String getOcupado() {
		return OCUPADO;
	}

	public static String getDescansando() {
		return DESCANSANDO;
	}

	public static String getProblemasvia() {
		return PROBLEMASVIA;
	}

	
	
}
