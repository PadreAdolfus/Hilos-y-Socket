package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serverloop {

	private static final int N_THREADS = 10;

	public static void main(String[] args) throws IOException {

		ServerSocket listener = new ServerSocket(9999);

		System.out.println("Servidor iniciado");

		Socket socket = null;

		boolean running = true;

		List<Thread> threadpool = new ArrayList<Thread>();

		while (running) {

			try {

				while (threadpool.size() > N_THREADS) {

					for (Thread tr : threadpool) {

						if (!tr.isAlive()) {

							threadpool.remove(tr);

						}

					}

				}

				socket = listener.accept();

				LogicThread thread = new LogicThread(socket);

				thread.start();

				threadpool.add(thread);

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		if (listener != null) {

			listener.close();

		}

	}

}
