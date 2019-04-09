package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;





public class SpellCheckerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> menuTendina;		//si deve indicare il tipo che contiene il menu a tendina

    @FXML
    private Button btnSpell;

    @FXML
    private Text txtError;

    @FXML
    private Button btnClear;

    @FXML
    private Text txtTempo;
    
    @FXML
    private TextArea txtAreaTesto;

    @FXML
    private TextArea txtAreaErrori;
    
    
    private Dictionary model;
    private String lingua=null;		//devo inizializzare a null
    private List<String> listaTesto=new LinkedList<String>();
	private List<RichWord> listaParoleErrate= new LinkedList<RichWord>();
    

    @FXML
    void doScelta(ActionEvent event) {
    	if(menuTendina.getValue()!=null) {
    		txtAreaTesto.setEditable(true);
    	}else {
    		txtAreaTesto.setEditable(false);
    	}
    }
    
    

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	txtAreaErrori.clear();
    	
    	lingua=menuTendina.getValue();
    	model.loaddictionary(lingua);
    	
    	String testoInserito= txtAreaTesto.getText().toLowerCase();
    	testoInserito=testoInserito.replaceAll("[.,?=\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]]", "");		//restituisce una stringa
    	        	
    	String parole[]=testoInserito.split(" ");
    	
    	for(String p: parole) {		//il for each si può fare anche con un array
    		if(p!=null) {
    			listaTesto.add(p);
    		}
    	}
    	
    	listaParoleErrate=model.spellCheckText(listaTesto);
    	
    	int num=0;
    	if(listaParoleErrate==null) {
        	txtAreaErrori.setText("Non ci sono errori");
        	
    	}else {
    		for(RichWord r: listaParoleErrate) {
    			String parola=r.getParola();
    			txtAreaErrori.appendText(parola+"\n");		//nell'append non posso mettere direttamente r.getParola()
    			num=num+1;
    		}
    	}
    	
    	txtError.setText("The text conteins "+num+" errors");
    	
    	long tempoDiProcesso=System.nanoTime();
    	String tempo=String.valueOf(tempoDiProcesso);
    	txtTempo.setText("Spell check completed in "+tempo+"seconds");
    	
    	listaParoleErrate.clear();
   	}
    	
 
    
    
    @FXML
    void doClearText(ActionEvent event) {
    	txtAreaErrori.clear();
    	txtAreaTesto.clear();
    }

   

    @FXML
    void initialize() {
        assert menuTendina != null : "fx:id=\"menuTendina\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    
    
    public void setModel(Dictionary m) {
    	this.model=m;
    	menuTendina.getItems().addAll("English", "Italian");		//per inizializzare il menu tendina    	
    }
    



	@Override
	public String toString() {
		return String.format("SpellCheckerController [txtAreaTesto=%s]", txtAreaTesto);
	}
    
    
    
    
    
    
    
    
    
}