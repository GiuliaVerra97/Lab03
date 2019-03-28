package it.polito.tdp.spellchecker.model;

//RichWord è una classe del modello

public class RichWord {

	String parola;
	
	
	public RichWord(String parola){
		this.parola=parola;
	}


	public String getParola() {
		return parola;
	}


	public void setParola(String parola) {
		this.parola = parola;
	}


	@Override
	public String toString() {
		return String.format("RichWord [parola=%s]", parola);
	}
	
	
	
	
	
	
	
}
