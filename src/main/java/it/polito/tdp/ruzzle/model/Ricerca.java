package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	public List<Pos> trovaParola(String parola, Board board) {
		for (Pos p : board.getPositions()) {
			// sono in una posizione, devo verificare se la lettera
			// coincide con il primo carattere di 'parola'
			if (board.getCellValueProperty(p).get().charAt(0) == 
					parola.charAt(0)) {
				// posso far partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(this.cerca(parola, parziale, 1, board)) {
					// ho trovato la parola per intero
					return parziale;
				}
			}
		}
		// non ho trovato la parola per intero
		return null;
	}
	
	private boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		if (livello == parola.length()) {
			// caso terminale
			return true;
		}
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for (Pos a : adiacenti) {
			// siamo in una pos adiacente all'ultima selezionata
			if (!percorso.contains(a) &&
					board.getCellValueProperty(a).get().charAt(0) ==
					parola.charAt(livello)) {
				// ho trovato la lettera di interesse (la prossima),
				// posso continuare il percorso facendo andare avanti
				// la ricorsione
				percorso.add(a);
				if (this.cerca(parola, percorso, livello+1, board)) {
					// uscita rapida dalla ricorsione
					return true;
				}
				percorso.remove(percorso.size()-1);
			}
		}
		return false;
	}
}
