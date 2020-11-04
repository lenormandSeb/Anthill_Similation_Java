package Anthil.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;

public class Anthill {

	private int larva;
	private int males;
	private int workers;
	private int queens;
	private List<Ant> population;
	private int nbOfDeath;

	public Anthill() {
	}

	public Anthill(int larva, int males, int workers, int queens) {
		this.population = new ArrayList();
		this.larva = larva;
		this.males = males;
		this.workers = workers;
		this.queens = queens;
		this.inializeFirstGen();
	}

	private void inializeFirstGen() {
		int gen = 0;
		for (int i = 1; i < this.larva; i++) {
			AntLarva antLarva = new AntLarva(i, gen, 1);
			this.population.add(antLarva);
		}
		for (int i = 1; i < this.males; i++) {
			AntMale antMale = new AntMale(i, gen, 1);
			this.population.add(antMale);
		}
		for (int i = 1; i < this.workers; i++) {
			AntWorker antWorker = new AntWorker(i, gen, 1);
			this.population.add(antWorker);
		}
		for (int i = 1; i < this.queens; i++) {
			AntQueen antQueen = new AntQueen(i, gen, 1);
			this.population.add(antQueen);
		}
	}

	public void addNouvellePort(List<Ant> np) {
		for (Ant npa : np) {
			this.population.add(npa);
		}
	}

	public void oneMoreDay(int generation) {
		ListIterator<Ant> it = this.population.listIterator();
		this.setNumberOfDeathToday(0);
		while (it.hasNext()) {
			Ant actualAnt = it.next();
			if (actualAnt instanceof AntLarva) {
				if (actualAnt.canBeAnAdult()) {
					it.remove();
					Random rand = new Random();
					int whatIBecome = rand.nextInt(20) % 20;
					switch (whatIBecome) {
					case 0:
					case 1:
						actualAnt = new AntMale(actualAnt.getId(), generation, actualAnt.getAge());
						break;
					case 2:
						actualAnt = new AntQueen(actualAnt.getId(), generation, actualAnt.getAge());
						break;
					default:
						actualAnt = new AntWorker(actualAnt.getId(), generation, actualAnt.getAge());
						break;
					}
					it.add(actualAnt);
				}
			}
			actualAnt.older();
			if (actualAnt.isDead()) {
				this.setNumberOfDeathToday(this.getNumberOfDeathToday() + 1);
				it.remove();
			}
		}
	}

	public int getPopulationTotal() {
		return this.population.size();
	}
	
	public int getNumberOfDeathToday() {
		return this.nbOfDeath;
	}
	
	public void setNumberOfDeathToday(int nbOfDeath) {
		this.nbOfDeath = nbOfDeath;
	}
	
	public int getNomberOfBirth() {
		if (this.getNumberOfMales() > 0) {
			return (int) this.getNumberOfQueens() * 10;			
		}
		return 0;
	}

	public int getNumberOfQueens() {
		return (int) this.population.stream().filter(x -> {
			if (x instanceof AntQueen) {
				return true;
			}
			return false;
		}).count();
	}
	
	public int getNumberOfWorkers() {
		return (int) this.population.stream().filter(x -> {
			if (x instanceof AntWorker) {
				return true;
			}
			return false;
		}).count();
	}
	
	public int getNumberOfLarvas() {
		return (int) this.population.stream().filter(x -> {
			if (x instanceof AntLarva) {
				return true;
			}
			return false;
		}).count();
	}

	public int getNumberOfMales() {
		return (int) this.population.stream().filter(x -> {
			if (x instanceof AntMale) {
				return true;
			}
			return false;
		}).count();
	}

	public List<Ant> getQueens() {
		return this.population.stream().filter(x -> {
			if (x instanceof AntQueen) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
	}
}
