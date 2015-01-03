package evaluationfunctions.coevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.robot.sensors.RobotRGBColorSensor;
import simulation.util.Arguments;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;


/**
 * 
 * @author Nuno e Diogo
 * Evaluation Function of team A, chase team B robots
 */ 
public class ChaseRobotEvaFunction extends EvaluationFunction {
	String team="teama";

	public ChaseRobotEvaFunction(Arguments args) {
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
							if(r.getSensorWithId(2).getSensorReading(2)>=0.1 && r.getSensorWithId(2).getSensorReading(2)<0.5){
								fitness = fitness + 0.5;
							}else{
								if(r.getSensorWithId(2).getSensorReading(2)>=0.5){
									fitness = fitness + 0.9;
								}
							}
							if(r.getSensorWithId(2).getSensorReading(2)<0.1){
								fitness -=0.3;
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
