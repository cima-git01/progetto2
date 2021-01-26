package com.francescocimarra.server;
// chat con una sola connessione

import java.net.*;
import java.io.*;
public class ServerChat {

	public static void main(String[] args) {
		ServerSocket sSocket;
		Socket connessione = null;
		int port = 2345;
		InputStreamReader in, input;
		BufferedReader sIN, tastiera;
		OutputStream out;
		PrintWriter sOUT;
		String msgDaInviare;
		String msgRicevuto;
		try
		{
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
				sIN = new BufferedReader(in);
				// flusso in ingresso da tastiera
				input = new InputStreamReader(System.in);
				tastiera = new BufferedReader(input);
				System.out.println("Chat inizzializzata con successo.");
				while(true)
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
		try
		{
			connessione.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}

}

