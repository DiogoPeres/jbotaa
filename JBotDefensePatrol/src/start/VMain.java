package start;
import evolutionaryrobotics.ViewerMain;

/**
 * 
 * @author Nuno e Diogo
 * Simulation window
 */
public class VMain {
	//ResultViewerGui - for simple evolutions
	//ResultCoEvolutionViewerGui - for co-evolutions
	public static void main(String[] args) {
		try {
			new ViewerMain(new String[]{"--gui","classname=ResultCoEvolutionViewerGui,renderer=(classname=TwoDRenderer)"});
		} catch (Exception e) {
			System.out.println("JbotSim error! Check the Gui options on VMain");
		}
	}
}
