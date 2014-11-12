package evaluationfunctions;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

/**
 * 
 * @author Nuno e Diogo
 * Função de avaliação para identificar e ficar junto de uma presa
 */
public class FindPreyEvaluationFunction extends EvaluationFunction {
	private Vector2d nestPosition = new Vector2d(0, 0);
	private double NESTRADIUS;
	private double GOOD_DISTANCE_TO_PREY = 0.2;

	public FindPreyEvaluationFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		int numberOfRobotsCloseToPrey = 0;
		Vector2d pos = null;
		for (Robot r : simulator.getEnvironment().getRobots()) {
			pos = r.getPosition();
			for (int i = 0; i < simulator.getEnvironment().getPrey().size(); i++) {
				double distanceToPrey = pos.distanceTo(simulator
						.getEnvironment().getPrey().get(i).getPosition());
				/**
				 * verifica se há robots junto de presas
				 */
				if (distanceToPrey <= GOOD_DISTANCE_TO_PREY) {
					numberOfRobotsCloseToPrey++;
				}
			}
		}
		/**
		 * a fitness é incrementada se houver robots junto de presas
		 */
		fitness += (double) numberOfRobotsCloseToPrey * 0.1;
	}

}
