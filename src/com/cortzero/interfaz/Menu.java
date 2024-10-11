/**
 *Clase Menu
 *Esta es la clase que se encargara del menu del juego y la interfaz.
 *@author Johan Camilo Cortes Ocampo
 *@version 1.0 - 29/04/2018
*/

package com.cortzero.interfaz;
import java.util.Scanner;
import com.cortzero.modelo.Matriz;

public class Menu{
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//ATRIBUTOS
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private String matrizInterfaz[][];
	private int filas;
	private int columnas;
	private Scanner numeros;
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//ASOCIACIONES
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Matriz juego;
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//MÉTODOS
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 *Metodo constructor
	 *
	 *<b>Post:</b> Se ha creado el objeto de tipo Menu.<br>
	 */
	public Menu(){
		numeros = new Scanner(System.in);
	}
	
	/**
	 *Muestra el titulo del juego.<br>
	*/
	public void titulo(){
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|======================================================================================================================|");
		System.out.println("|                                                  BUSCAMINAS                                                          |");
		System.out.println("|======================================================================================================================|");
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");
		eleccionDificultad();
	}
	
	/**
	 *Muestra un mensaje indicando que el jugador ha ganado la partida.<br>
	*/
	public void mensajeVictoria(){
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                                 FELICIDADES!!                                                       |");
		System.out.println("|                                             HAS GANADO LA PARTIDA                                                   |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	/**
	 *Muestra un mensaje indicando que el jugador ha perdido la partida.<br>
	*/
	public void mensajeDerrota(){
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                               PISASTE UNA MINA!!                                                    |");
		System.out.println("|                                             HAS PERDIDO LA PARTIDA                                                  |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	/**
	 *Muestra un mensaje indicando que la matriz esta solucionada.<br>
	*/
	public void mensajeSolucion(){
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                           CAMPO DE MINAS DESCUBIERTO.                                               |");
		System.out.println("|                                          ESTA ES LA SOLUCION DEL JUEGO.                                             |");
		System.out.println("|                                              GRACIAS POR JUGAR!                                                     |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	/**
	 *Muestra un mensaje de error cuando se ingresa un valor invalido.<br>
	*/
	public void mensajeErrorOpcion(){
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                               OPCION INVALIDA!                                                      |");
		System.out.println("|                                       POR FAVOR, INGRESE UN VALOR VALIDO.                                           |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	/**
	 *Muestra un mensaje de error en caso de que se haya ingresado una coordenada invalida.<br>
	*/
	public void mensajeErrorCoordenadas(){
		System.out.println("\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("|                               LA CASILLA SELECCIONADA NO EXISTE O YA FUE DESCUBIERTA                                |");
		System.out.println("|                                     POR FAVOR, INGRESE UNA COORDENADA VALIDA.                                       |");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	/**
	 *Inicializa la matriz de la interfaz con el tamanio dado de acuerdo a la dificultad del juego elegida y se le asigna el valor a cada elemento.<br>
	 *
	 *<b>Pre:</b> La matriz esta declarada.<br>
	 *
	 *@param filas - Cantidad de filas de la matriz. Es un numero entero positivo.
	 *@param columnas - Cantidad de columnas de la matriz. Es un numero entero positivo.
	 *@param cantidadMinas - Cantidad de minas que tendra la matriz. Es un entero positivo.
	*/
	public void construirMatriz(int filas, int columnas, int cantidadMinas){
		this.filas = filas;
		this.columnas = columnas;
		matrizInterfaz = new String[filas][columnas];
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				matrizInterfaz[i][j] = " ";
			}
		}
		juego = new Matriz(filas, columnas, cantidadMinas);
		juego.llenarMatriz();
		juego.instalarMinas();
		juego.contarMinas();
	}
	
	/**
	 *Muestra y permite elegir las opciones de dificultad del juego.<br>
	 *
	 *<b>Pre:</b> El objeto de tipo Scanner esta declarado e inicializado.<br>
	 *<b>Pre:</b> El metodo construirMatriz esta creado.<br>
	*/
	public void eleccionDificultad(){
		int opcion = 0;
		do{
			System.out.println("\n--------------------------------------SELECCIONA LA DIFICULTAD DEL JUEGO----------------------------------------\n");
			System.out.println("\t1. Principiante.");
			System.out.println("\t2. Intermedio.");
			System.out.println("\t3. Experto.\n");
			System.out.print("\nOpcion: ");
			opcion = numeros.nextInt();
			switch(opcion){
				case 1:
					construirMatriz(8, 8, 10);
					break;
				case 2:
					construirMatriz(16, 16, 40);
					break;
				case 3:
					construirMatriz(16, 30, 99);
					break;
				default:
					mensajeErrorOpcion();
			}
		}while(opcion < 1 || opcion > 3);
		opcionesDeJuego();
	}
	 
	/**
	 *Muestra la interfaz de la matriz para que el jugador interactue con ella.<br>
	 *
	 *<b>Pre:</b> La matriz de la interfaz esta declarada e inicializada.<br> 
	*/
	public void mostrarMatriz() {
		System.out.print("       ");
		for(int k = 0; k < columnas; k++) {
			System.out.print((k+1)+"       ");
		}
		for(int i = 0; i < filas; i++) {
			System.out.println("\n");
			if(i < 9) {
				System.out.print((i+1)+"     ");
			}
			else {
				System.out.print((i+1)+"    ");
			}
			for(int j = 0; j < columnas; j++) {
				if(j < 10) {
					System.out.print("[ "+matrizInterfaz[i][j]+" ]"+"   ");
				}
				else {
					System.out.print("[ "+matrizInterfaz[i][j]+" ]"+"    ");
				}
			}
		}
	}
	
	/**
	 *Asigna el numero de minas alrededor de la casilla en la posicion ij de la matriz del modelo a la matriz de la interfaz en la misma posicion.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz y la del modelo estan declaradas e inicializadas.<br>
	 *<b>Pre:</b> La coordenada ingresada no corresponde a una mina.<br>
	 *<b>Pre:</b> El objeto juego de tipo Matriz esta instanciado.<br>
	 *
	 *<b>Post:</b> El valor en la posicion ij en la matriz interfaz ha cambiado.<br>
	 *
	 *@param i - i-esima fila de la matriz. Es un entero positivo menor al numero de filas ya establecido.
	 *@param j - j-esima columna de la matriz. Es un entero positivo menor al numero de columnas ya establecido.
	*/
	public void copiarContenido(int i, int j){
		matrizInterfaz[i][j] = String.valueOf(juego.darCasilla(i, j).darCantidadMinasAlrededor());
		juego.darCasilla(i, j).establecerEstado(true);
	}
	
	/**
	 *Llama al metodo copiarContenido con las coordenadas que ingreso el jugador.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz y la del modelo estan declaradas e inicializadas.<br>
	 *<b>Pre:</b> El metodo copiarContenido ha sido creado.<br>
	 *<b>Pre:</b> El metodo mostrarMatriz ha sido creado.<br>
	 *<b>Pre:</b> El metodo mensajeDerrota ha sido creado.<br>
	 *<b>Pre:</b> Las coordenadas ingresada estan dentro de la coordenada minima y la coordenada maxima de la matriz.<br>
	 *<b>Pre:</b> La casilla que se va a abrir esta cubierta (estado = false).<br>
	 *
	 *@param fila - i-esima fila de la matriz. Es un numero entero positivo.
	 *@param columna - j-esima columna de la matriz. Es un numero entero positivo.
	*/
	public void abrirCasilla(int fila, int columna){
		int contador = 0;
		if(juego.darCasilla(fila, columna).hayMina()){
			matrizInterfaz[fila][columna] = "*";
			mostrarMatriz();
			System.out.println("");
			mensajeDerrota();
			System.exit(0);
		}
		else{
			copiarContenido(fila, columna);
			for(int i = 0; i < filas; i++){
				for(int j = 0; j < columnas; j++){
					if(!matrizInterfaz[i][j].equals("*")){
						contador++;
					}
				}
			}
			if(contador == (filas*columnas) - juego.cantidadMinas){
				mensajeVictoria();
				System.exit(0);
			}
		}
	}
	
	/**
	 *Marca con un simbolo la casilla en la que el jugador considere que hay una mina.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz esta declarada e inicializada.<br>
	 *<b>Pre:</b> El metodo mostrarMatriz ha sido creado.<br>
	 *<b>Pre:</b> Las coordenadas ingresada estan dentro de la coordenada minima y la coordenada maxima de la matriz.<br>
	 *<b>Pre:</b> La casilla a la que se le va a poner la marca esta cubierta (estado = false).<br>
	 *
	 *@param fila - i-esima fila de la matriz. Es un numero entero positivo.
	 *@param columna - j-esima columna de la matriz. Es un numero entero positivo.
	*/
	public void ponerMarca(int fila, int columna){
		matrizInterfaz[fila][columna] = "?";
	}
	
	/**
	 *Le pide al jugador la coordenada de una casilla para despues llamar los metodos correspondientes a la opcion elegida por el jugador.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz y la del modelo estan declaradas e inicializadas.<br>
	 *<b>Pre:</b> El metodo abrirCasilla ha sido creado.<br>
	 *<b>Pre:</b> El metodo ponerMarca ha sido creado.<br>
	 *<b>Pre:</b> El metodo mensajeErrorCoordenadas ha sido creado.<br>
	 *<b>Pre:</b> El objeto de tipo Scanner esta instanciado.<br>
	 *
	 *@param opcion - Opcion del menu de juego ingresada por el jugador.
	*/
	public void ingresarCoordenadas(int opcion){
		int fila = 0;
		int columna = 0;
		boolean fueraDeRango = false;
		boolean casillaDescubierta = false;
		do{
			System.out.print("\nFila: ");
			fila = numeros.nextInt();
			System.out.print("\nColumna: ");
			columna = numeros.nextInt();
			if(fila < 0 || fila > filas || columna < 0 || columna > columnas){
				mensajeErrorCoordenadas();
				fueraDeRango = true;
			}
			else if(juego.darCasilla(fila-1, columna-1).darEstado()){
				mensajeErrorCoordenadas();
				casillaDescubierta = true;
			}
			else{
				switch(opcion){
					case 1: 
						abrirCasilla(fila-1, columna-1);
						break;
					case 2:
						ponerMarca(fila-1, columna-1);
						break;
				}
				fueraDeRango = false;
				casillaDescubierta = false;
			}
		}while(fueraDeRango || casillaDescubierta);
	}
	
	/**
	 *Muestra la matriz con todas sus casillas descubiertas asignandole los valores de cada objeto casilla de la matriz del modelo a la matriz interfaz 
	 *y si es una mina se le asigna un aasterisco.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz y la del modelo estan declaradas e inicializadas.<br>
	 *<b>Pre:</b> El objeto juego de tipo Matriz esta instanciado.<br>
	 *<b>Pre:</b> El metodo mostrarMatriz ha sido creado.<br>
	 *
	 *<b>Post:</b> Los valores de la matriz interfaz han sido modificados.<br>
	*/
	public void solucionarMatriz(){
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				if(juego.darCasilla(i, j).hayMina()){
					matrizInterfaz[i][j] = "*";
				}
				else{
					matrizInterfaz[i][j] = String.valueOf(juego.darCasilla(i, j).darCantidadMinasAlrededor());
				}
			}
		}
	}
	
	/**
	 *Busca en la matriz del modelo la primera casilla que no tenga una mina, que no se haya descubierto antes y que tenga minas alrededor y despues 
	 *se le asigna ese valor a la matriz de la interfaz.<br>
	 *
	 *<b>Pre:</b> La matriz interfaz y la del modelo estan declaradas e inicializadas.<br>
	 *<b>Pre:</b> Las minas están colocadas aleatoriamente en la matriz del modelo.<br>
	 *<b>Pre:</b> Se ha calculado la cantidad de minas que hay alrededor de cada casilla de la matriz del modelo.<br>
	 *<b>Pre:</b> El objeto juego de tipo Matriz esta instanciado.<br>
	 *
	 *<b>Post:</b> Se ha descubierto la primera casilla que cumpla las condiciones.<br>
	*/
	public void pista(){
		int fila = 0;
		int columna = 0;
		boolean casillaEncontrada = false;
		for(int i = 0; i < filas && !casillaEncontrada; i++){
			for(int j = 0; j < columnas && !casillaEncontrada; j++){
				if(!juego.darCasilla(i, j).hayMina() && juego.darCasilla(i, j).darCantidadMinasAlrededor() != 0 && !juego.darCasilla(i, j).darEstado()){
					matrizInterfaz[i][j] = String.valueOf(juego.darCasilla(i, j).darCantidadMinasAlrededor());
					juego.darCasilla(i, j).establecerEstado(true);
					fila = i;
					columna = j;
					casillaEncontrada = true;
				}
			}
		}
		System.out.println("\n-----------------------Se ha abierto la casilla en la fila " + (fila + 1) + " y columna " + (columna + 1) + ".-------------------------\n");
	}
	
	/**
	 *Muestra y permite elegir las opciones disponibles para que el jugador interactue con la matriz de la interfaz.<br>
	 *
	 *<b>Pre:</b> Todos los metodos necesarios estan creados.<br>
	 *<b>Pre:</b> El objeto de tipo Scanner esta instanciado.<br>
	*/
	public void opcionesDeJuego(){
		int opcion = 0;
		int opcionSolucion = 0;
		do{
			System.out.println("");
			mostrarMatriz();
			System.out.println("");
			System.out.println("\nOpciones:\n");
			System.out.println("\t1. Ingresar coordenadas.");
			System.out.println("\t2. Marcar casilla.");
			System.out.println("\t3. Pista.");
			System.out.println("\t4. Solucion.");
			System.out.println("\t5. Salir.\n");
			System.out.print("Opcion: ");
			opcion = numeros.nextInt();
			switch(opcion){
				case 1: 
					ingresarCoordenadas(1);
					break;
				case 2:
					ingresarCoordenadas(2);
					break;
				case 3:
					pista();
					break;
				case 4:
					do{
						System.out.println("Seguro que quieres ver la solucion y terminar el juego?\n");
						System.out.println("\t1. Si.");
						System.out.println("\t2. No.\n");
						System.out.print("Opcion: ");
						opcionSolucion = numeros.nextInt();
						switch(opcionSolucion){
							case 1:
								solucionarMatriz();
								mensajeSolucion();
								System.out.println("\n");
								mostrarMatriz();
								System.out.println("\n");
								System.exit(0);
							case 2:
								break;
							default: 
								mensajeErrorOpcion();
						}
					}while(opcionSolucion != 1 && opcionSolucion != 2);
					break;
				case 5:
					System.exit(0);
				default:
					mensajeErrorOpcion();
			}
		}while(opcion != 5);
	}
}