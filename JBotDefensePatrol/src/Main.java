import evolutionaryrobotics.EvolverMain;


public class Main {

	public static void main(String[] args) {
		try {
			new EvolverMain(new String[]{"CoEvolution.conf"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
