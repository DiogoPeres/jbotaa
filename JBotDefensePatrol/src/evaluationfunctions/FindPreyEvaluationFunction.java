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
 * Evaluation function to identify and stay near a prey
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
				 * Verify if there is any robots near preys
				 */
				if (distanceToPrey <= GOOD_DISTANCE_TO_PREY) {
					numberOfRobotsCloseToPrey++;
				}
			}
		}
		/**
		 * The fitness is increased if there is any robots near a prey
		 */
		fitness += (double) numberOfRobotsCloseToPrey * 0.1;
	}

}
