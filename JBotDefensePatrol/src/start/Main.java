package start;
import evolutionaryrobotics.EvolverMain;


public class Main {

	public static void main(String[] args) {
		try {
			new EvolverMain(new String[]{"configuration_bring_prey_to_nest.conf"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
