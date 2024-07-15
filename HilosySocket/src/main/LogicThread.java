package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LogicThread extends Thread {

	private Socket socket;

	LogicThread(Socket socket) {

		this.socket = socket;

	}

	public void run() {

		System.out.println("Debug: creado thread");

		boolean running = true;

		BufferedReader input = null;

		PrintWriter out = null;

		try {

			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);

		} catch (IOException e) {

			e.printStackTrace();

			return;
		}

		List<String> options = new ArrayList<String>();

		options.add("Estas son las opciones:\n");

		options.add("1º ¿Como te llamas?\n");

		options.add("2º ¿Cuantos años tienes?\n");

		options.add("3º ¿Donde vives?\n");

		options.add("4º ¿Que deporte me recomendarias?\n");

		options.add("5º ¿Que nota se merece esta práctica?\n");
		
		options.add("6º Hasta luego.\n");

		while (running) {

			try {

				String prompt = input.readLine();

				if (prompt.equals("¿Como te llamas?")) {

					out.println("Mi nombre es Alonso Motta.");

				}

				else if (prompt.equals("¿Cuantos años tienes?")) {

					out.println("Tengo 25 años recien cumplidos.");

				}

				else if (prompt.equals("¿Donde vives?")) {

					out.println("Actualmente resido en Madrid.");

				}

				else if (prompt.equals("¿Que deporte me recomendarias?")) {

					out.println("Cualquier deporte de equipo/pareja como el padel o el futbol.");

				} else if (prompt.equals("¿Que nota se merece esta práctica?")) {

					out.println("Un 10 por supuesto.");

				}
				else if (prompt.equals("Hasta luego")) {

					out.println("Adios.");
					
					running = false;

				}

				else {

					for (String option : options) {

						out.print(option);

					}

					out.flush();
				}

			}

			catch (Exception e) {

				e.printStackTrace();

				running = false;
			}

		}

		if (socket != null) {

			try {

				socket.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		System.out.println("Se ha acabado el thread");
	}

}
