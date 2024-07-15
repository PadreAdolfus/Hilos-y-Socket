package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) throws IOException {

		boolean running = true;

		String serverAddress = "127.0.0.1";

		Socket socket = null;

		socket = new Socket(serverAddress, 9999);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		Scanner sc = new Scanner(System.in);

		String welcome = sc.nextLine();

		while (running) {

			String answer = null;

			out.println(welcome);

			do {

				answer = input.readLine();

				System.out.println(answer);

			}

			while (!answer.contains("."));

			if (answer.equals("Adios.")) {

				running = false;
			} else {
				
				welcome = sc.nextLine();
			}

		}

		if (socket != null) {

			try {

				socket.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		sc.close();
	}
}
