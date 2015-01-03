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
			new EvolverMain(new String[]{"configurationFiles/coevolution/configuration_invadeNest.conf"});
		} catch (Exception e) {
			System.out.println("JBotEvolver Main error! Check the configuration file!");
		}
	}
}
