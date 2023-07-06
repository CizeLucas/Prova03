package model;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Servidor {
	
	private ServerSocket serverSocket;
	private ArrayList<ManipuladorCliente> listaClientes;
	
	protected Servidor(int porta) {
		listaClientes = new ArrayList<ManipuladorCliente>();
		
		try {
			this.serverSocket = new ServerSocket(porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread(new ReceberNovosClientes()).start();
	}
	
	protected void imprimirServerLogs(String logMsg) {
		System.out.println("SERVER LOGS: "+ logMsg);
	}
	
	protected void finalizarServerSocket() {
		try {
			if(serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected ArrayList<ManipuladorCliente> getListaClientes() {
		return listaClientes;
	}
	
	private class ReceberNovosClientes implements Runnable {
		
		public void run() {
			
			while(!serverSocket.isClosed()) {
				try {
					
					new Thread(new ManipuladorCliente(serverSocket.accept(), Servidor.this)).start();
					
				} catch (IOException e) {
					finalizarServerSocket();
					e.printStackTrace();
				}
			}
			
		}
	}

	
}
