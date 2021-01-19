package com.francescocimarra.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientChat {

	public static void main(String[] args) {
		
		Socket connessione = null;
		String server = "localhost";
		int port = 2345;
		
		InputStreamReader in, input;
		BufferedReader sIN, tastiera;
		OutputStream out;
		PrintWriter sOUT;
		String msgDaInviare;
		String msgRicevuto;
		
		try {
			connessione = new Socket(server, port);
			System.out.println("Connessione eseguita.");
		}
		catch (IOException e)
		{
			System.out.println(e);
			System.exit(-1);
		}
		try {
			// flusso in ingresso
			in = new InputStreamReader(connessione.getInputStream());
			sIN = new BufferedReader(in);
			// flusso in uscita su socket
			out = connessione.getOutputStream();
			sOUT = new PrintWriter(out);
			// flusso in ingresso da tastiera
			input = new InputStreamReader(System.in);
			tastiera = new BufferedReader(input);
			System.out.println("Chat inizializzata.");
			
			while(true)
			{
				// legge il messaggio da tastiera
				msgDaInviare = tastiera.readLine();
				// interrompe la chat
				if (msgDaInviare.equals("FINE"))
					break;
				// invia il messaggio
				sOUT.println(msgDaInviare);
				sOUT.flush();
				// stampa il messaggio ricevuto
				msgRicevuto = sIN.readLine();
				System.out.println(">> " + msgRicevuto);		
			}
		}
		catch (IOException e)
		{
			System.out.println("e");
		}
		try
		{
			connessione.close();
		}
		catch (IOException e)
		{
			System.out.println("e");
		}
	}
}
