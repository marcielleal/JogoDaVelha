package application;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Validacao {
	private String estado[][];
	private boolean isfimDeJogo;
	private boolean isEmpate;
	/**
	 * Construtor de Validacao
	 */
	public Validacao() {
		this.estado=new String[3][3];
		this.isfimDeJogo=false;
		this.isEmpate=false;
	}
	/**
	 * Cria uma matriz 3x3 baseado no grid dado
	 * @param grid É o grid do jogo da velha em questão
	 */
	private void geraEstado(GridPane grid){
		if(grid==null)
			return;
		int k=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++,k++)
				this.estado[i][j]=((Button)grid.getChildren().get(k)).getText();
	}
	/**
	 * Valida se ainda há jogadas possíveis a serem feitas pelos jogadores 
	 * @param grid O grid do jogo da velha em questão
	 * @return true se ainda é possível jogar, false se não 
	 */
	public boolean validarEstagio(GridPane grid){
		this.geraEstado(grid);
		String tmp;
		//Debug
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.estado[i][j].equals(""))
					System.out.print("_");
				else
					System.out.print(this.estado[i][j]);
			}
			System.out.println();
		}
		
		//linhas
		for(int i=0;i<3;i++){
			if(estado[i][0].equals(""))
				continue;
			tmp=estado[i][0];
			if(estado[i][1].equals(tmp) &&estado[i][2].equals(tmp)){
				isfimDeJogo=true;
				return false;
			}
		}
		
		//colunas
		for(int i=0;i<3;i++){
			if(estado[0][i].equals(""))
				continue;
			tmp=estado[0][i];
			if(estado[1][i].equals(tmp) &&estado[2][i].equals(tmp)){
				isfimDeJogo=true;
				return false;
			}
		}
		
		//diagonais
		if(!estado[1][1].equals("")){
			tmp=estado[1][1];
			if(estado[0][0].equals(tmp)&&estado[2][2].equals(tmp)){
				isfimDeJogo=true;
				return false;
			}
			if(estado[0][2].equals(tmp)&&estado[2][0].equals(tmp)){
				isfimDeJogo=true;
				return false;
			}
		}
		/* Existem possibilidades de jogadas ainda?*/
		boolean semJogadas=true;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(estado[i][j].equals(""))
					semJogadas=false;
		
		if(semJogadas){
			isfimDeJogo=true;
			isEmpate=true;
			return false;
		}
		/*-------------------------------------------*/
		return true;
	}
	
	public boolean validarEstagio(GridPane grid,String estado[][]){
		this.estado=estado;
		return this.validarEstagio(null);
	}
	

	public boolean isIsfimDeJogo() {
		return this.isfimDeJogo;
	}
	
	public boolean isEmpate(){
		return this.isEmpate;
	}
	
	public void reset(){
		this.estado=new String[3][3];
		this.isfimDeJogo=false;
		this.isEmpate=false;
	}
}
