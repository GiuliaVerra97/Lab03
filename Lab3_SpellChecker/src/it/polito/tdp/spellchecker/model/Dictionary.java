package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.spellchecker.controller.SpellCheckerController;

//Dictionary � una classe del modello



public class Dictionary {

	
	SpellCheckerController controller=new SpellCheckerController();
	List<String> listaDizionario=new LinkedList<String>();
	List<RichWord> listaParoleErrate=new LinkedList<RichWord>();
	
	
	public Dictionary() {
		super();
		this.controller = controller;
		this.listaDizionario = listaDizionario;
		this.listaParoleErrate = listaParoleErrate;
	}


	/**
	 * Permette di caricare in memoria il dizionario della ligua desiderata nel menuTendina
	 * @param language indica la lingua selezionata nel menu tendina
	 */
	public void loaddictionary(String language) {
		
		listaDizionario.clear();
		
		if(language.equals("English")) {
			try {
	
				FileReader fr = new FileReader( "rsc/English.txt" );		//src indica la cartella dalla quale si prende il file
				BufferedReader br = new BufferedReader(fr);
				String word ;
				while (( word = br .readLine()) != null ) {
					listaDizionario.add(word);
				}
				br .close();
				} catch (IOException e ){
					System.out .println( "Errore nella lettura del file" );
				}
		}
		
		
		if(language.equals("Italian")) {
			try {
	
				FileReader fr = new FileReader( "rsc/Italian.txt" );
				BufferedReader br = new BufferedReader(fr);
				String word ;
				while (( word = br .readLine()) != null ) {
					listaDizionario.add(word);
				}
				br .close();
				} catch (IOException e ){
					System.out .println( "Errore nella lettura del file" );
				}
			}
		}
		
	
	
	/**
	 * esegue il controllo ortografico sul testo di input (rappresentato da una lista di parole)
	 * @param inputTextList indica il testo inserito nella txtArea e da correggere
	 * @return lista di {@link RichWord} errate 
	 */
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		listaParoleErrate.clear();
		boolean presente=false;
		
		if(!inputTextList.isEmpty()) {
			for(String parola: inputTextList) {
				if(listaDizionario.contains(parola)==false) {
					RichWord parolaErrata=new RichWord(parola);
					listaParoleErrate.add(parolaErrata);
					presente=true;
				}
			}
		}
		
		if(presente==false) {
			return null;
		}else {
			return listaParoleErrate;
		}
		
	}
	
	
	
	/*
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		listaParoleErrate.clear();
		boolean presente=false;
		
		if(!inputTextList.isEmpty()) {
			for(String parola: inputTextList) {
				presente=false;
				for(int i=0; i<listaDizionario.size();i++) {
					if(parola.equals(listaDizionario.get(i))) {
						presente=true;
						break;
						}
					}
				if(presente==false) {
					RichWord parolaErrata=new RichWord(parola);
					listaParoleErrate.add(parolaErrata);
				}
			}
		}
		
		return listaParoleErrate;
		
	}
	
	
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		listaParoleErrate.clear();
		boolean presente=false;
		
		if(!inputTextList.isEmpty()) {
			for(String parola: inputTextList) {
				presente=false;
				int dim=listaDizionario.size();
				String parolaMeta=listaDizionario.get((int)dim/2);
				if(parola.compareTo(parolaMeta)<0) {
					for(int i=0;i<dim/2;i++) {
						if(listaDizionario.get(i).equals(parola)) {
							presente=true;
							break;
						}
					}
				}else if(parola.compareTo(parolaMeta)==0) {
					presente=true;
					break;
				}else {
					for(int i=dim/2;i<listaDizionario.size();i++) {
						if(listaDizionario.get(i).equals(parola)) {
							presente=true;
							break;
						}
				}
			}
				
			if(presente==false) {
				RichWord parolaErrata=new RichWord(parola);
				listaParoleErrate.add(parolaErrata);
				}
			}
				
		}
		
		return listaParoleErrate;
		
	}
	
	*/
	
	
}
