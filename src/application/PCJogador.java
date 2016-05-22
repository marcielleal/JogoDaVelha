package application;

import arvore.ArvoreMinMax;
import arvore.No;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PCJogador {
	private ArvoreMinMax arvore;
	private boolean pccomeca;
	public PCJogador(String jogador, boolean pccomeca) {
		this.arvore=new ArvoreMinMax(jogador);
		arvore.construirMinMax();
		arvore.valorarArvore(pccomeca);
		this.pccomeca=pccomeca;
	}
	
	private int getJogada(String estado[][]){
		//Calcular o índice da jogada do player
		if(this.pccomeca){
			arvore.setUltimoAcesso(arvore.getRaiz().getFilhos().get(8));
			this.pccomeca=false;
			return diferencaEntreEstados(estado,arvore.getRaiz().getFilhos().get(8).getEstado());
		}
		System.out.println("-------------------------------------");
		System.out.println(arvore.getUltimoAcesso().getFilhos());
		int i=arvore.getUltimoAcesso().getFilhos().indexOf(new No(estado));
		System.out.println("----------------------------");
		No noEstadoAtual=arvore.getUltimoAcesso().getFilhos().get(i);

		//Indice do filho escolhido
		arvore.setUltimoAcesso(noEstadoAtual.getFilhos().get(noEstadoAtual.getFilhos().size()-1));

		return diferencaEntreEstados(estado,noEstadoAtual.getFilhos().get(noEstadoAtual.getFilhos().size()-1).getEstado());
	}
	
	private int diferencaEntreEstados(String[][] estado1, String[][] estado2){
		int k=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++,k++){
				if(!estado1[i][j].equals(estado2[i][j])){
					System.out.println("K é: "+k);
					return k;
				}
			}
		}
		return k;
	}
	public void jogue(GridPane grid, String jogada){
		assert(grid!=null);
		assert(jogada.equals("O"));
		String estado[][]=new String[3][3];
			
		int k=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++,k++)
				estado[i][j]=((Button)grid.getChildren().get(k)).getText();
		
		Button bt= (Button) grid.getChildren().get(this.getJogada(estado));
		bt.setText(jogada);
	}
	
	public void reset(){
		this.arvore.setUltimoAcesso(this.arvore.getRaiz());
	}
}
