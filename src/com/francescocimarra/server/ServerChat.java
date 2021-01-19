package com.francescocimarra.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {

	public static void main(String[] args) {
		ServerSocket sSocket;
		Socket connessione = null;
		int port = 2345;
		
		InputStreamReader in, input = null;
		BufferedReader sIN, tastiera = null;
		OutputStream out;
		PrintWriter sOUT;
		String msgDaInviare;
		String msgRicevuto;
		
		try {
			sSocket = new ServerSocket(port);
			System.out.println("In attesa di connessioni...");
			// ciclo infinito
			while (true)
			{
				connessione = sSocket.accept();
				// flusso in uscita su socket
				out = connessione.getOutputStream();
				sOUT = new PrintWriter(out);
				// flusso in ingresso da socket
				in = new InputStreamReader(connessione.getInputStream());
				sIN = new BufferedReader(input);
				System.out.println("Chat inizializzata.");
				while (true)
				{
					// stampa il messaggio ricevuto
					msgRicevuto = sIN.readLine();
					if (msgRicevuto == null)
					{
						System.out.println("Il client ha chiuso la chat.");
						break;
					}
					System.out.println(">> " + msgRicevuto);
					// legge il messaggio da tastiera
					msgDaInviare = tastiera.readLine();
					// invia il messaggio
					sOUT.println(msgDaInviare);
					sOUT.flush();
				}	
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		try {
			connessione.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

	}

}
