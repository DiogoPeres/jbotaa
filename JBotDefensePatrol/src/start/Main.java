package start;
import evolutionaryrobotics.EvolverMain;


public class Main {

	public static void main(String[] args) {
		try {
			new EvolverMain(new String[]{"configuration_coevo_chaserobot.conf"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
