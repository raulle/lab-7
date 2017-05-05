package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	private Model model;
	UndirectedGraph<String, DefaultEdge> grafo= new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	
	public void setModel(Model model){
		this.model=model;
	}

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("Reset!");
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		List<String> parole = model.createGraph(Integer.parseInt(inputNumeroLettere.getText()));
		for(String s : parole)
			grafo.addVertex(s);
		for(String s : parole){
			char[] c=s.toCharArray();
			for(String ss : parole){
				char[] cc= ss.toCharArray();
				int t=0;
				for(int i=0;i<s.length();i++){
					if(c[i]!=cc[i])
						t++;
					
				}
				if(t==1)
					grafo.addEdge(s, ss);
			}
		}
			
		txtResult.setText(grafo.toString());
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		int max=0;
		for(String v : grafo.vertexSet()){
			if(grafo.edgesOf(v).size()>max){
				max=grafo.edgesOf(v).size();
				txtResult.setText("Grado massimo: "+max+"\nVertice: "+v+"\nVicini: "+Graphs.neighborListOf(grafo, v).toString());
			}
		}
		
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		String s= inputParola.getText();
		txtResult.setText(Graphs.neighborListOf(grafo, s).toString());
	}
	
	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}