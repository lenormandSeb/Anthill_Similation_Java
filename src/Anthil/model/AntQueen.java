package Anthil.model;

import java.util.ArrayList;
import java.util.List;

public class AntQueen extends Ant  {
	
	private int cycleLifeMax = 50;

	public AntQueen(int i, int gen, int j) {
		super(i,gen, j, 50);
	}
	
	public List<Ant> reproduction(int actualGen) {
		List<Ant> nouvellePort = new ArrayList();
		for (int i = 0; i < 10; i++) {
			AntLarva antLarva = new AntLarva(this.createNewId(i, actualGen + 1), actualGen + 1, 1);
			nouvellePort.add(antLarva);
		}
		
		return nouvellePort;
	}
	
	public int createNewId(int i, int gen) {
		return this.getId() + this.getBirthRank() + this.getAge() + i + gen;
	}
}
