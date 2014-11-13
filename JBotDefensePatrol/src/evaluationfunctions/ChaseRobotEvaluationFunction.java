package evaluationfunctions;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;


/**
 * 
 * @author Nuno e Diogo
 * Função de avaliação da equipa A, perseguição dos robots da equipa B
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
					 * Quanto mais perto de um robot B, mais aumenta a fitness
					 * Quanto mais longe de um robot B, mais diminui a fitness
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
		 * a fitness é incrementada se houver robots junto de presas
		 */

	}

}
