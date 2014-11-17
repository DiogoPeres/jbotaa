package evaluationfunctions.coevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;


/**
 * 
 * @author Nuno e Diogo
 * Evaluation Function of team A, chase robots of team B
 */ 
public class ChaseRobotEvaluationFunction extends EvaluationFunction {
	private double OK_DISTANCE_TO_ROBOT = 0.6;
	private double GOOD_DISTANCE_TO_ROBOT = 0.4;
	private double PERFECT_DISTANCE_TO_ROBOT = 0.1;
	String team="teama";

	public ChaseRobotEvaluationFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		double distanceToRobot = 0;
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					/**
					 * The closer of one robot of team B, the greater the fitness
					 * The farther of one robot of team B, more decreases the fitness
					 */
					distanceToRobot = r.getPosition().distanceTo(r2.getPosition());
					fitness += 1-distanceToRobot;
					/*if(distanceToRobot<= GOOD_DISTANCE_TO_ROBOT){
						fitness++;
					}else{
						if(distanceToRobot< OK_DISTANCE_TO_ROBOT)
							fitness+=0.5;
						else fitness--;
					}
					if(distanceToRobot<=PERFECT_DISTANCE_TO_ROBOT) fitness+=4;
					*/
				}
			}

		}
		/**
		 * Fitness is increased if there is any robots near the preys
		 */

	}

}
