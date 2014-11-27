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
public class EscapeFromRobotEvaFunction extends EvaluationFunction{
	String team="teamb";

	public EscapeFromRobotEvaFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					/**
					 * The fitness increases when the robot is closer of  team B robots
					 * The fitness decreases when the robots is far from team B robots
					 */
					try{
						if(r.getSensorWithId(2).isEnabled()){
							if(r.getSensorWithId(2).getSensorReading(2)>=0.1){
								fitness = fitness - 0.5;
							}
							if(r.getSensorWithId(2).getSensorReading(2)<=0){
								fitness +=0.3;
							}
						}
					}catch(Exception e){
						System.err.println("RGB Color Sensor error");
					}
				}
			}

		}
	}
}