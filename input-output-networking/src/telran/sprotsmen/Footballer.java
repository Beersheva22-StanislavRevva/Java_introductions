package telran.sprotsmen;

public class Footballer implements Sportsman {
String team;

	@Override
	public void action() {
		System.out.printf("plays footbal %s\n", team != null ? "for team" + team : "");
	}
	public Footballer() {
		
	}
	public Footballer(String team) {
		this.team = team;
	}

}
