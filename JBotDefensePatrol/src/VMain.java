import evolutionaryrobotics.ViewerMain;


public class VMain {

	public static void main(String[] args) {
		try {
			new ViewerMain(new String[]{"--gui","classname=ResultCoEvolutionViewerGui,renderer=(classname=TwoDRenderer)"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
