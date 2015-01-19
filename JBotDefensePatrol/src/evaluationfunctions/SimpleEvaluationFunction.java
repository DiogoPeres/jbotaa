package evaluationfunctions;

import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.environment.RoundForageEnvironment;
import simulation.robot.Robot;
import simulation.util.Arguments;

/**
 * @author Diogo & Nuno
 * Evaluation Function to move the robot to the nest
 */

public class SimpleEvaluationFunction extends EvaluationFunction {
	private Vector2d    nestPosition = new Vector2d(0, 0);
	private double      NESTRADIUS;
	private double lastDistanceToNest = Double.MAX_VALUE;

	public SimpleEvaluationFunction(Arguments args) {
		super(args);
	}
	/**
	 * The closest the nest, the greater the fitness
	 */
	@Override
	public void update(Simulator simulator) {			
		for(Robot r : simulator.getEnvironment().getRobots()){
			fitness+=1-r.getPosition().distanceTo(nestPosition);
		}
		
		
	}

}
