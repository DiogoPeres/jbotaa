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
					 * The farther of team A robots, the more the fitness increases
					 * The closer of team A robots, the more the fitness decreases 
					 */
					distanceToRobot = r.getPosition().distanceTo(r2.getPosition());
					fitness += distanceToRobot -1;
				}
			}
		}
	}
}
