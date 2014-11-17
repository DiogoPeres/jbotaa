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
	
	@Override
	public void update(Simulator simulator) {			
		int numberOfRobotsCloseToNest = 0;
		
		NESTRADIUS         = ((SimpleEnvironment)(simulator.getEnvironment())).getNestRadius();
		Vector2d pos=null;
		Vector2d coord = new Vector2d();
		for(Robot r : simulator.getEnvironment().getRobots()){
			pos=r.getPosition();
			if(lastDistanceToNest<Double.MAX_VALUE){
				lastDistanceToNest=pos.distanceTo(nestPosition);
			}
			/**
			 * Increases Fitness for each time the robot approaches the nest
			 * Decreases Fitness any time the robots move away from the nest
			 */
			if(lastDistanceToNest>pos.distanceTo(nestPosition)){
				fitness+=(lastDistanceToNest-pos.distanceTo(nestPosition))*100;
			}else{
				fitness-=(pos.distanceTo(nestPosition)-lastDistanceToNest)*100;
			}
			lastDistanceToNest = pos.distanceTo(nestPosition);
			/**
			 * Increases Fitness if the robot is in the nest
			 */
			if(pos.distanceTo(nestPosition) <=0.01){
				fitness+=2;
			}
		}
		
	}

}
