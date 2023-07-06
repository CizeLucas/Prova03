

import model.NomeImagens;
import model.gameDAO;

public class Main {

	public static void main(String[] args) {
		
		NomeImagens.getInstancia();
		
		
		//(gameDAO.getInstancia()).inserir();

		String[][] dadosDB = (gameDAO.getInstancia()).getMatrizDeDados();

		for (String[] strings : dadosDB) {
			for (String str : strings) {
				System.out.print(str+"; ");
			}
			System.out.println();
		}
	}

}
