package model;

import java.sql.*;

public class gameDAO {
	
	private static gameDAO instancia;
	
	private static final String nomeTabela = "Rodadas";
	
	//Dados de Conexao Com BancoDeDados(DB)
	private String host;
	private String banco;
	private String usuario;
	private String senha;
	
	private gameDAO() {
		host = "sql10.freesqldatabase.com";
		banco = "sql10630472";
		usuario = "sql10630472";
		senha = "lBIZXIPLzj";
	}
	
	public static gameDAO getInstancia() {
		if(instancia == null) {
			instancia = new gameDAO();
		}
		return instancia;
	}
	
	public boolean inserir() {
		try {
			
			Connection conexao = new ConexaoDB().getConexao();
			
			PreparedStatement inserir = conexao.prepareStatement("INSERT INTO `"+nomeTabela+"` "
				+ "(`id`, `dica`, `jogadorDaVez`, `cartaDaVez`, `pontosJ1`, `jogador2`, `cartaJ2`, `pontosJ2`, `jogador3`, `cartaJ3`, `pontosJ3`, `jogador4`, `cartaJ4`, `pontosJ4`) "
				+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	
			inserir.setString(1, "DICA");
			
			inserir.setString(2, "jogadorDaVez");
			inserir.setString(3, "cartaDaVez");
			inserir.setInt(4, 10);
			
			inserir.setString(5, "jogador2");
			inserir.setString(6, "cartaJ2");
			inserir.setInt(7, 10);
			
			inserir.setString(8, "jogador3");
			inserir.setString(9, "cartaJ3");
			inserir.setInt(10, 10);
			
			inserir.setString(11, "jogador4");
			inserir.setString(12, "cartaJ4");
			inserir.setInt(13, 10);
			
			return (inserir.executeUpdate()!=0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String[][] getMatrizDeDados() {
		try {
			
			Connection conexao = new ConexaoDB().getConexao();
			
			ResultSet rs = conexao.prepareStatement("select count(*) from `"+ nomeTabela +"`").executeQuery();
			int qtdDeLinhas=0;
			while(rs.next()) {
				qtdDeLinhas = rs.getInt(1);
			}
			
			ResultSet resultado = conexao.prepareStatement("SELECT * FROM `"+ nomeTabela +"`").executeQuery();
			
			int qtdDeColunas = resultado.getMetaData().getColumnCount();
			
			String[][] dados = new String[qtdDeLinhas][qtdDeColunas];
			
			String[] nomeColunasDoBanco = {"id", "dica", "jogadorDaVez", "cartaDaVez", "pontosJ1", "jogador2", "cartaJ2", "pontosJ2", 
					"jogador3", "cartaJ3", "pontosJ3", "jogador4", "cartaJ4", "pontosJ4"};
			
			for(int j=0; resultado.next(); j++) {
				for(int i=0; i<qtdDeColunas; i++) {
					dados[j][i] =  resultado.getString(nomeColunasDoBanco[i]);
				}
			}
			
//						resultado.getInt("id")+" "+
//						resultado.getString("dica")+" "+
//						resultado.getString("jogadorDaVez")+" "+
//						resultado.getString("cartaDaVez")+" "+
//						resultado.getInt("pontosJ1")+" "+
//						resultado.getString("jogador2")+" "+
//						resultado.getString("cartaJ2")+" "+
//						resultado.getInt("pontosJ2")+" "+
//						resultado.getString("jogador3")+" "+
//						resultado.getString("cartaJ3")+" "+
//						resultado.getInt("pontosJ3")+" "+
//						resultado.getString("jogador4")+" "+
//						resultado.getString("cartaJ4")+" "+
//						resultado.getInt("pontosJ4");
				
			conexao.close();
			return dados;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private class ConexaoDB {
		
		public Connection getConexao() {
			String URL = "jdbc:mysql://" + host + "/" + banco+"?verifyServerCertificate=false&useSSL=true";
			try {
				return DriverManager.getConnection(URL, usuario, senha);
			} catch (SQLException ex) {
				System.out.println("Conexao com MYSQL nao realizada");
				ex.printStackTrace();
			}
			return null;
		}
		
	}
	
}
