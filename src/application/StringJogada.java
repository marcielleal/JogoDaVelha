package application;

public class StringJogada {
	private String jogada;
	
	public StringJogada(String jogada) {
		if(jogada.equals("X"))
			this.jogada = jogada;
		else
			this.jogada="O";
	}
	public StringJogada(){
		this.jogada="O";
	}
	/**
	 * Muda a jogada atual e retorna essa jogada
	 * @return Retorna uma string que irá ser mostrada quando o jogador jogar
	 */
	public String getProximaJogada(){
		if(this.jogada.equals("X"))
			this.jogada = "O";
		else
			this.jogada="X";
		return this.jogada;
	}
	/**
	 * @return Valor da jogada atual
	 */
	public String getUltimaJogada(){
		return this.jogada;
	}
}
