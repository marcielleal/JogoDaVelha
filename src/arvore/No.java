package arvore;

import java.util.ArrayList;
import java.util.Collections;

public class No implements Comparable<No>{
	private String estado[][];
	private ArrayList<No> filhos;
	private int valor;

	public No() {
		this.estado=new String[3][3];
		this.filhos=new ArrayList<No>();
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				estado[i][j]="";
		this.valor=-100;
	}
	public No(String estado[][]) {
		this.estado=new String[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				this.estado[i][j]=estado[i][j];
		
		this.filhos=new ArrayList<No>();
		this.valor=-100;
	}
	
	public ArrayList<No> getFilhos() {
		return filhos;
	}

	public String[][] getEstado() {
		return estado;
	}

	public void setEstado(String[][] estado) {
		this.estado = estado;
	}

	/**
	 * Calcula o valor do No de acordo com seus filhos
	 * @return Retorna o valor do No
	 */
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int compareTo(No outroNo){
		if(this.valor < outroNo.getValor())
			return -1;
		if(this.valor > outroNo.getValor())
			return 1;
		return 0;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof No){
			boolean igual=true;
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(!this.estado[i][j].equals((((No) o).getEstado())[i][j]))
						igual=false;
			return igual;
		}
		return false;
	}
	
	public String toString(){
		String nova="\n";
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.estado[i][j].equals(""))
					nova+="_";
				else
					nova+=this.estado[i][j];
			}
			nova+="\n";
		}
		return nova;
	}
}
