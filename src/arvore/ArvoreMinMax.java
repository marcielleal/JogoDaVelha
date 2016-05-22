package arvore;

import java.util.Collections;

import application.Validacao;
/**
 * O primeiro nível dessa árvore vai ser criada com o jogador inverso
 * ao jogadorInicial
 * @author Leal
 *
 */
public class ArvoreMinMax {
	private No raiz;
	private No ultimoAcesso;
	private String jogadorInicial;	/** Que é o PC */
	
	public ArvoreMinMax(String jogador) {
		this.raiz=new No();
		this.ultimoAcesso=raiz;
		this.jogadorInicial=jogador;
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public No getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(No ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	/**
	 * Constroi filhos a partir de um pai, sabendo a string do jogador
	 * @param pai
	 * @param inicial
	 * @throws Exception 
	 */
	private void construirFilhosMinMax(No pai, String inicial){
		Validacao validacao=new Validacao();
		String estado[][]=new String[3][3];
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				estado[i][j]=pai.getEstado()[i][j];
		
		if(inicial.equals("X"))
			inicial= "O";
		else if(inicial.equals("O"))
			inicial="X";
		else
			assert(false);
		
		No novoNo;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(estado[i][j].equals("")){
					estado[i][j]=inicial;
					novoNo=new No(estado);
					pai.getFilhos().add(novoNo);
					
					//Se acabou
					if(!validacao.validarEstagio(null, estado)){
						if(validacao.isEmpate()){
							novoNo.setValor(0);
						}else{
							if(inicial.equals(this.jogadorInicial))
								novoNo.setValor(10);
							else
								novoNo.setValor(-10);
						}
					}else{
						construirFilhosMinMax(novoNo,inicial);
					}
					estado[i][j]="";
				}
			}
		}
	}
	public void construirMinMax(){
		String jogada=this.jogadorInicial;
		construirFilhosMinMax(this.raiz,jogada);
	}
	
	private void valorarArvoreMin(No pai){
		//Se meu valor é diferente de -100, não preciso valorar
		if(pai.getValor()!=-100)
			return;
		
		Collections.sort(pai.getFilhos());
		if(pai.getFilhos().get(0).getValor()==-100){
			for(No filho: pai.getFilhos())
				valorarArvoreMax(filho);
			Collections.sort(pai.getFilhos());
			pai.setValor(pai.getFilhos().get(0).getValor());
		}else{
			pai.setValor(pai.getFilhos().get(0).getValor());
		}
	}
	/**
	 * Valora o elemento com o máximo
	 * @param pai
	 */
	private void valorarArvoreMax(No pai){
		//Se meu valor é diferente de -100, não preciso valorar
		if(pai.getValor()!=-100)
			return;
		
		Collections.sort(pai.getFilhos());
		if(pai.getFilhos().get(0).getValor()==-100){
			for(No filho: pai.getFilhos())
				valorarArvoreMin(filho);
			Collections.sort(pai.getFilhos());
			pai.setValor(pai.getFilhos().get(pai.getFilhos().size()-1).getValor());
		}else{
			pai.setValor(pai.getFilhos().get(pai.getFilhos().size()-1).getValor());
		}
	}
	
	/**
	 * Dá valor aos nós abaixo de ultimoAcesso (atributo de ArvoreMinMax
	 * @param min booleano que indica se a valoração é de Mínimo ou não
	 */
	public void valorarArvore(boolean pccomeca){
		if(pccomeca)
			valorarArvoreMax(this.raiz);
		else
			valorarArvoreMin(this.raiz);
	}
}
