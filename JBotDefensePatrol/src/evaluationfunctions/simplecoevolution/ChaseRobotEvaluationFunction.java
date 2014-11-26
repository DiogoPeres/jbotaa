package evaluationfunctions.simplecoevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;


/**
 * 
 * @author Nuno e Diogo
 * Evaluation Function of team A, chase team B robots
 */ 
public class ChaseRobotEvaluationFunction extends EvaluationFunction {
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
					 * The fitness increases when the robot is closer of  team B robots
					 * The fitness decreases when the robots is far from team B robots
					 */
					distanceToRobot = r.getPosition().distanceTo(r2.getPosition());
					fitness += 1-distanceToRobot;
				}
			}

		}
	}

}
