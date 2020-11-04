package Anthil.model;

public abstract class Ant {
	
	private int id;
	private int birthRank;
	private int age;
	private int cycleOfLifeMax;
	
	public Ant(int id, int birthRank, int age, int cycleOfLifeMax) {
		this.setId(id);
		this.setBirthRank(birthRank);
		this.setAge(age);
		this.cycleOfLifeMax = cycleOfLifeMax;
	}
	
	public void older() {
		this.setAge(this.getAge() + 1);
	}
	
	public boolean canBeAnAdult() {
		if (this.getAge() == this.cycleOfLifeMax) {
			return true;
		}
		return false;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBirthRank() {
		return birthRank;
	}

	public void setBirthRank(int birthRank) {
		this.birthRank = birthRank;
	}

	public boolean isDead() {
		if (this.getAge() > this.cycleOfLifeMax) {
			return true;
		}
		return false;
	}
}
