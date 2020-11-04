package Anthil.launcher;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import Anthil.model.*;

public class Launcher {

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);

		// Initialize number of ants (larva, male, worker, queens)
		int larva = rand.nextInt(15) % 15;
		int males = rand.nextInt(15) % 15;
		int workers = rand.nextInt(15) % 15;
		int queens = rand.nextInt(15) % 15;

		// Initialization of Anthill
		Anthill anthill = new Anthill(larva, males, workers, queens);

		int day = 0;
		boolean continuation = true;
		do {
			if (anthill.getNumberOfMales() > 0) {
				List<Ant> q = anthill.getQueens();
				for (Ant aq : q) {
					List<Ant> newLarve = ((AntQueen) aq).reproduction(day);
					anthill.addNouvellePort(newLarve);
				}
			}

			anthill.oneMoreDay(day);
			day++;
			System.out.println("Next Day ?");
			String wannaContinue = sc.nextLine();

			if (!wannaContinue.equals("")) {
				continuation = false;
			}
			System.out.println("Anthill age : " + day);
			System.out.println("Population : ");
			System.out.println("	Number of Larvas : " + anthill.getNumberOfLarvas());
			System.out.println("	Number of Males : " + anthill.getNumberOfMales());
			System.out.println("	Number of Workers : " + anthill.getNumberOfWorkers());
			System.out.println("	Number of Queens : " + anthill.getNumberOfQueens());
			System.out.println("	New birth : " + anthill.getNomberOfBirth());
			System.out.println("	Number of Death : " + anthill.getNumberOfDeathToday());
		} while (continuation);
		
		System.out.println("End of simulation day : " + day);
	}
}
