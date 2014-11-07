package fitnessfunctions;

import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.environment.RoundForageEnvironment;
import simulation.robot.Robot;
import simulation.util.Arguments;

public class SimpleEvaluationFunction extends EvaluationFunction {
	private Vector2d    nestPosition = new Vector2d(0, 0);
	private double      NESTRADIUS;

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
			coord.set(r.getPosition());
			pos=r.getPosition();
			double distanceToNest = coord.distanceTo(nestPosition);
			if (distanceToNest < NESTRADIUS+1) {
				numberOfRobotsCloseToNest++;
			} 
		}
		fitness += (double) numberOfRobotsCloseToNest * 0.1;
		if(pos.distanceTo(nestPosition) <=0.01){
			fitness+=1;
			//simulator.getEnvironment().getRobots().get(0).stop();
		}
	}

}
