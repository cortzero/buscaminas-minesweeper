/**
 *Clase Matriz
 *Aqui esta la matriz del modelo en la que se almacenan los objetos de tipo Casilla.
 *@author Johan Camilo Cortes Ocampo
 *@version 1.0 - 21/04/2018
*/

package com.cortzero.modelo;
import java.util.ArrayList;

public class Matriz {
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//ATRIBUTOS
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private int filas;
	private int columnas;
	public int cantidadMinas;
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//ASOCIACIONES
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Casilla matriz[][];
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
//MÉTODOS
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 *Metodo contructor que crea un objeto de tipo Matriz.<br>
	 *
	 *<b>Post:</b> Se ha creado un objeto de tipo Matriz.<br>
	 *
	 *@param filas - Cantidad de filas de la matriz.<br>
	 *@param columnas - Cantidad de columnas de la matriz.<br>
	 *@param cantidadMinas - Numero de minas que va a tener la matriz.<br>
	*/
	public Matriz(int filas, int columnas, int cantidadMinas){
		this.filas = filas;
		this.columnas = columnas;
		this.cantidadMinas = cantidadMinas;
		matriz = new Casilla[filas][columnas];
	}
	
	/**
	 *Almacena objetos de tipo Casilla en cada uno de los espacios de la matriz.<br>
	 *
	 *<b>Pre:</b> La matriz esta declarada e inicializada.<br>
	*/
	public void llenarMatriz(){
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				matriz[i][j] = new Casilla();
			}
		}
	}
	
	/**
	 *Selecciona aleatoriamente las casillas que deben tener mina.<br>
	 *
	 *<b>Pre:</b> La matriz esta declarada e inicializada.<br>
	*/
	public void instalarMinas(){
		ArrayList<String> listaCoordenadas = new ArrayList<>();
		String coordenada = null;
		int i = 0;
		int j = 0;
		int contador = 0;
		boolean mismaCoordenada = false;
		while(contador < cantidadMinas) {
			i = (int) (Math.random()*(filas));
			j = (int) (Math.random()*(columnas));
			coordenada = i + String.valueOf(j);
			for(int k = 0; k < listaCoordenadas.size() && !mismaCoordenada; k++) {
				if(coordenada.equals(listaCoordenadas.get(k))) {
					mismaCoordenada = true;
				}
			}
			if(mismaCoordenada) {
				mismaCoordenada = false;
			}
			else {
				matriz[i][j].establecerHayMina(true);
				listaCoordenadas.add(coordenada);
				contador++;
			}
		}
		
	}
	
	/**
	 *Cuenta la cantidad de minas que hay alrededor de una casilla sin mina.<br>
	 *
	 *<b>Pre:</b> La matriz esta declarada e inicializada.<br>
	 *<b>Pre:</b> Las minas están colocadas aleatoriamente en la matriz.<br>
	*/
	public void contarMinas(){
		int minas = 0;
		for(int i = 0; i < filas; i++){
			for(int j = 0; j < columnas; j++){
				if(!matriz[i][j].hayMina()){
					if(i - 1 >= 0 && j - 1 >= 0){
						if(matriz[i-1][j-1].hayMina()){
							minas++;
						}
					}
					if(i - 1 >= 0){
						if(matriz[i-1][j].hayMina()){
							minas++;
						}
					}
					if(i - 1 >= 0 && j + 1 < columnas){
						if(matriz[i-1][j+1].hayMina()){
							minas++;
						}
					}
					if(j - 1 >= 0){
						if(matriz[i][j-1].hayMina()){
							minas++;
						}
					}
					if(j + 1 < columnas){
						if(matriz[i][j+1].hayMina()){
							minas++;
						}
					}
					if(i + 1 < filas && j - 1 >= 0){
						if(matriz[i+1][j-1].hayMina()){
							minas++;
						}
					}
					if(i + 1 < filas){
						if(matriz[i+1][j].hayMina()){
							minas++;
						}
					}
					if(i + 1 < filas && j + 1 < columnas){
						if(matriz[i+1][j+1].hayMina()){
							minas++;
						}
					}
					matriz[i][j].establecerCantidadMinas(minas);
					minas = 0;
				}
			}
		}
	}
	
	/**
	 *Devuelve el objeto Casilla de la matriz en la posicion ij.<br>
	 *
	 *<b>Pre:</b> La matriz esta declarada e inicializada.<br>
	 *
	 *@param i - i-esima fila de la matriz. Es un entero positivo.
	 *@param j - j-esima columna de la matriz. Es un entero positivo.
	 *
	 *@return matriz[i][j] - Objeto Casilla que esta en la posicion ij en la matriz.
	*/
	public Casilla darCasilla(int i, int j){
		return matriz[i][j];
	}
}