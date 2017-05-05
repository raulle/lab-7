package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {

	public List<String> createGraph(int numeroLettere) {
		WordDAO dao = new WordDAO();
		return dao.getAllWordsFixedLength(numeroLettere);
	}

	public List<String> displayNeighbours(String parolaInserita) {

		System.out.println("Model -- TODO");
		return new ArrayList<String>();
	}

	public String findMaxDegree() {
		System.out.println("Model -- TODO");
		return "";
	}
}
