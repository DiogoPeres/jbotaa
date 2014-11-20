package start;
import evolutionaryrobotics.EvolverMain;

/**
 * 
 * @author Nuno e Diogo
 * Defense Patrol Main (using JBotEvolver)
 */
public class Main {

	public static void main(String[] args) {
		try {
			new EvolverMain(new String[]{"configuration_coevo_invadenest.conf"});
		} catch (Exception e) {
			System.out.println("JBotEvolver Main error! Check the configuration file!");
		}
	}
}
