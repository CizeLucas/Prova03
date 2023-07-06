package model;

public class Rede {
	
	private static Rede instancia;
	protected static int porta = 5555;
	
	private Servidor servidor;
	private Cliente cliente;
	
	private Rede(String ip, String nome) {
		conectar(ip, nome);
	}
	
	private Rede(String nome) {
		iniciarServer();
		conectar("localhost", nome);
	}
	
	public static Rede getInstancia() {
		return instancia;
	}
	
	
	public static void instanciarRede(String nome) {
		if (instancia == null)
			instancia = new Rede(nome);
	}
	
	
	public static void instanciarRede(String ip, String nome) {
		if (instancia == null) 
			instancia = new Rede(ip, nome);
	}
	
	
	
	/**
	 * Método que sera executado apos o cliente receber uma mensagem da rede
	 * 
	 * @param mensagem recebida do servidor
	 */
	protected void mensagemRecebidaDaRede(String msgDaRede) {
		System.out.println("Classe REDE: 'Cliente recebeu: "+msgDaRede+"'.");
	}
	
	
	
	/**
	 * Deve mandar uma mensagem para os computadores conectados na rede
	 * 
	 * @param mensagem a string a ser enviada
	 */
	public void enviarMensagemParaRede(String msgParaRede) {
		cliente.enviarMensagem(msgParaRede);
	}
	
	
	
	/**
	 * Deve criar um cliente e conecta-lo com um host de um ip informado
	 * 
	 * @param ip ip do servidor host 
	 * @param nome  nome do cliente
	 */
	private void conectar(String ip, String nome) {
		cliente = new Cliente(ip, porta, nome);
	}
	
	
	
	/**
	 * Deve iniciar um server para que outros computadores posssam conectar
	 */
	private void iniciarServer() {
		servidor = new Servidor(porta);
	}
	
	
	
	/**
	 * Retorna a porta do servidor
	 * 
	 * @return a porta do servidor ou cliente
	 */
	public int getPorta() {
		return porta;
	}
}
