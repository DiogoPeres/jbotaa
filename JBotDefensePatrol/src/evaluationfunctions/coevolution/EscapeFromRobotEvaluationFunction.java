package evaluationfunctions.coevolution;

import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;

/**
 * 
 * @author Nuno e Diogo
 * Evaluation Function of team B, run away from team A robots
 */
public class EscapeFromRobotEvaluationFunction extends EvaluationFunction{
	private double GREAT_DISTANCE_TO_ROBOT = 0.4;
	private double BAD_DISTANCE_TO_ROBOT = 0.2;
	String team="teamb";

	public EscapeFromRobotEvaluationFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		double distanceToRobot = 0;
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					/**
					 * The farther of one robot of team A, more increases the fitness
					 * The closer of one robot of team A, more decreases the fitness
					 */
					distanceToRobot = r.getPosition().distanceTo(r2.getPosition());
					fitness += distanceToRobot -1;
					/*if(distanceToRobot>= GREAT_DISTANCE_TO_ROBOT){
						fitness++;
					}else{
						fitness--;
						if(distanceToRobot<= BAD_DISTANCE_TO_ROBOT){
							fitness=fitness-2;
						}
					}
					*/
				}
			}

		}
		/**
		 * Fitness is increased if there is any robots near the preys
		 */

	}
}
