package sva.tbot.juego;

import sva.tbot.exception.JuegoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Menu {
	private static int columnaIdioma = 1; //por defecto sera español
	public static final String SALTO_LINEA = "\n";
	public static final String CSV_SEPARATOR = ";";
	public static final String CSV_RPATH = "src/main/resources/a.csv";
	public static final String LOGO_DEV = """
				         /\\
				      _-/- \\
				     - /   -\\-_
				     -/__    \\_-
				     /     _--\\
				     ----------

				     SHI-GAMES
				    2021 - SPAIN
				""";

	// MENUS ----------------------------------------------

	public static String menuPrincipal(int vecesJugado) {
		StringBuilder sb = new StringBuilder(LOGO_DEV + SALTO_LINEA);
		if(vecesJugado >= 1){
			sb.append("0.- ").append(buscaTexto("option0")).append(SALTO_LINEA);
			sb.append("1.- ").append(buscaTexto("option1"));
			if (vecesJugado >= 5) {
				sb.append("+").append(vecesJugado);
			} else {
				sb.append("+".repeat(vecesJugado));
			}
			sb.append(SALTO_LINEA);
		}else{
			sb.append("1.- ").append(buscaTexto("option1")).append(SALTO_LINEA);
		}
		sb.append("2.- ").append(buscaTexto("option2")).append(SALTO_LINEA);
		sb.append("3.- ").append(buscaTexto("option3")).append(SALTO_LINEA);
		sb.append("4.- ").append(buscaTexto("option4")).append(SALTO_LINEA);
		sb.append("5.- ").append(buscaTexto("option15")).append(SALTO_LINEA);
		return sb.toString();
	}

	public static String menuChangeLog() {
		StringBuilder sb = new StringBuilder();
		sb.append("========================= ").append(buscaTexto("changelogmenu0")).append(" =========================");
		sb.append(SALTO_LINEA);
		for(int i=1; i<= 12; i++){
			sb.append("+ ").append(buscaTexto("changelogmenu" + i)).append(SALTO_LINEA);
		}
		sb.append(SALTO_LINEA);
		sb.append("========================= ").append(buscaTexto("changelogmenu13")).append(" =================");
		sb.append(SALTO_LINEA);
		for(int i=14; i<= 15; i++){
			sb.append("+ ").append(buscaTexto("changelogmenu" + i)).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public static String menuExtras() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= 5; i++){
			sb.append(buscaTexto("extras" + i)).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public static String menuClases() {
		return  "0.- " + buscaTexto("option11") + SALTO_LINEA +
				"1.- " + String.format("%-12s", buscaTexto("class0")) + "(HP: 100, AD: 1, AP: 1, AR: 2, MR: 2, AGL: 0)"
				+ SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("class1")) + "(HP: 90,  AD: 2, AP: 1, AR: 1, MR: 0, AGL: 3)"
				+ SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("class2")) + "(HP: 90,  AD: 2, AP: 0, AR: 2, MR: 2, AGL: 1)"
				+ SALTO_LINEA +
				"4.- " + String.format("%-12s", buscaTexto("class3")) + "(HP: 90,  AD: 1, AP: 3, AR: 0, MR: 0, AGL: 2)"
				+ SALTO_LINEA +
				"5.- " + String.format("%-12s", buscaTexto("class4")) + "(HP: 90,  AD: 2, AP: 0, AR: 1, MR: 2, AGL: 2)"
				+ SALTO_LINEA;
	}

	public static String menuCambiaIdioma() {
		StringBuilder sb = new StringBuilder();
		sb.append(buscaTexto("langmenu0")).append(SALTO_LINEA);
		String[] idiomas = getIdiomasDisponibles();
		for (int i = 0; i < idiomas.length; i++) {
			sb.append(i+1).append(".- ").append(idiomas[i]).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public static String menuAcciones() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(i+1).append(".- ").append(buscaTexto("option" + (i +5))).append(SALTO_LINEA);
		}
		return sb.toString();
	}

	public static String menuAccionesFisico() {
		return  "1.- " + String.format("%-12s", buscaTexto("attack0")) + "(Min: 2 AD)" + SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("attack1")) + "(Min: 10 AD)" + SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("attack2")) + "(Min: 6 AD)" + SALTO_LINEA +
				"4.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	public static String menuAccionesMagico() {
		return  "1.- " + String.format("%-12s", buscaTexto("attack3")) + "(Min: 2 AD)" + SALTO_LINEA +
				"2.- " + String.format("%-12s", buscaTexto("attack4")) + "(Min: 10 AD)" + SALTO_LINEA +
				"3.- " + String.format("%-12s", buscaTexto("attack5")) + "(Min: 6 AD)" + SALTO_LINEA +
				"4.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	public static String menuAccionesOtros() {
		return "1.- "  + buscaTexto("option12") + SALTO_LINEA +
				"2.- " + buscaTexto("option13") + SALTO_LINEA +
				"3.- " + buscaTexto("option14") + SALTO_LINEA +
				"4.- " + buscaTexto("option15") + SALTO_LINEA +
				"5.- " + buscaTexto("option10") + SALTO_LINEA;
	}

	// MUESTRA COSAS -----------------------------------

	public static String muestraAyudaClases() {
		return "============ " + buscaTexto("classInfo0") + " ============" +
				SALTO_LINEA + SALTO_LINEA +
				"1.- " + buscaTexto("classInfo1") + SALTO_LINEA +
				buscaTexto("classInfo2") + SALTO_LINEA +
				buscaTexto("classInfo3") + SALTO_LINEA +
				SALTO_LINEA +
				"2.- " + buscaTexto("classInfo4") + SALTO_LINEA +
				buscaTexto("classInfo5") + SALTO_LINEA +
				buscaTexto("classInfo6") + SALTO_LINEA +
				SALTO_LINEA +
				"3.- " + buscaTexto("classInfo7") + SALTO_LINEA +
				buscaTexto("classInfo8") + SALTO_LINEA +
				buscaTexto("classInfo9") + SALTO_LINEA +
				SALTO_LINEA +
				"4.- " + buscaTexto("classInfo10") + SALTO_LINEA +
				buscaTexto("classInfo11") + SALTO_LINEA +
				buscaTexto("classInfo12") + SALTO_LINEA +
				SALTO_LINEA +
				"5.- " + buscaTexto("classInfo13") + SALTO_LINEA +
				buscaTexto("classInfo14") + SALTO_LINEA +
				buscaTexto("classInfo15") + SALTO_LINEA;
	}

	public static String muestraAyudaJuego() {
		return buscaTexto("gameinfo0");
	}

	public static String muestraHistoriaJuego() {
		return buscaTexto("gameStory0");
	}

	// MENSAJES --------------------------------------------

	public static String pideAccion() {
		return buscaTexto("message1");
	}

	public static String pideNombre() {
		return buscaTexto("message2");
	}

	public static String msgBienvenida() {
		return buscaTexto("message3");
	}

	public static String msgTurno() {
		return "===== " + buscaTexto("message4") + " %s =====";
	}

	public static String msgEmpiezaJuego() {
		return "========= " + buscaTexto("message5") +" =========";
	}

	public static String msgPersonajeCreado() {
		return "%s " + buscaTexto("message6") + " (%02d/%02d)";
	}

	public static String msgSubidaNivel() {
		return "%s " + buscaTexto("message7") + " %d!";
	}

	public static String msgSalirJuego() {
		return buscaTexto("message8");
	}

	public static String pideClases() {
		return buscaTexto("message9");
	}

	public static String pideConfirmacion() {
		return buscaTexto("message10");
	}

	public static String msgPausa() {
		return SALTO_LINEA + buscaTexto("message11");
	}

	public static String msgEnemigoDerrotado() {
		return buscaTexto("message12");
	}

	public static String msgJuegoPerdido() {
		return buscaTexto("message13");
	}

	public static String pideEnemigo() {
		return buscaTexto("message14");
	}

	public static String enemigoElegidoNoDisponible() {
		return buscaTexto("message15");
	}

	public static String msgBloqueo() {
		return buscaTexto("message16");
	}

	public static String msgConfirmaBloqueo() {
		return buscaTexto("message17");
	}

	public static String msgPosVacia(){
		return buscaTexto("message18");
	}

	public static String msgCreacionClase() {
		return buscaTexto("message19");
	}

	public static String msgEnemigoAtaqueEsquivado() {
		return buscaTexto("message23");
	}

	public static String msgPersonajeAtaqueEsquivado() {
		return buscaTexto("message29");
	}

	public static String msgDespedida(){
		return buscaTexto("message22");
	}

	public static String confimaBloqueo() {
		return buscaTexto("message20") + " %s " + buscaTexto("message21");
	}

	// ERRORES -----------------------------------------------------------

	public static String errorEnemigoNoEncontrado() {
		return buscaTexto("errormessage0");
	}

	public static String errorPersonajeNoEncontrado() {
		return buscaTexto("errormessage1");
	}

	public static String errorNumeroNoValido() {
		return buscaTexto("errormessage2");
	}

	public static String errorAtaqueNoDisponible() {
		return buscaTexto("errormessage3");
	}

	public static String errorInesperado() {
		return buscaTexto("errormessage4");
	}

	public static String errorBloqueoYaActivo() {
		return buscaTexto("errormessage5");
	}

	public static String errorNombreNoValido() {
		return buscaTexto("errormessage6");
	}

	// metodos configuradores del lector. -----------------------------------------------

	public static boolean cambiarIdioma(String nuevoIdioma){
		String[] idiomas = getIdiomasDisponibles();
		for (int i = 0; i < idiomas.length; i++) {
			if(idiomas[i].equalsIgnoreCase(nuevoIdioma)){
				columnaIdioma = i;
				return true;
			}
		}
		return false;
	}

	private static String[] getIdiomasDisponibles(){
		try (Scanner sc = new Scanner(new File(CSV_RPATH))){
			String header = sc.nextLine();
			String[] headerSplitted = header.split(CSV_SEPARATOR);
			String[] idiomas = new String[headerSplitted.length-1];
			System.arraycopy(headerSplitted, 1, idiomas, 0, idiomas.length);
			return idiomas;
		}catch(FileNotFoundException e){
			throw new JuegoException("Archivo de idioma no encontrado.");
		}
	}

	private static String buscaTexto(String id){
		try (Scanner sc = new Scanner(new File(CSV_RPATH))){
			sc.nextLine(); //quitamos el header
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] splitted = line.split(CSV_SEPARATOR);
				if(splitted[0].equalsIgnoreCase(id)){
					String text = splitted[columnaIdioma].trim();
					if(text.isEmpty()){
						throw new JuegoException("No se ha encontrado el texto requerido.");
					}
					return text;
				}
			}
			throw new JuegoException("Dialogo inexiste en archivo.");
		}catch(FileNotFoundException e){
			throw new JuegoException("Archivo de idioma no encontrado.");
		}
	}
}