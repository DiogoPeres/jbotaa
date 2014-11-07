package fitnessfunctions;

import java.util.ArrayList;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

public class PatrolEvaluationFunction extends EvaluationFunction{
	private Vector2d    nestPosition = new Vector2d(0, 0);
	private double      NESTRADIUS;
	private ArrayList<Vector2d> pontos = new ArrayList<Vector2d>();

	public PatrolEvaluationFunction(Arguments args) {
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
			if (distanceToNest < NESTRADIUS + 0.6) {
				numberOfRobotsCloseToNest++;
			} 
			fitness += (double) numberOfRobotsCloseToNest * 0.1;
			if(distanceToNest > 0.5 && distanceToNest < 0.6){
				boolean haspass = false;
				for (int i = 0; i < pontos.size(); i++) {
					if(pontos.get(i).distanceTo(r.getPosition()) == 0){
						haspass=true;
					}
				}
				if(!haspass) {
					pontos.add(r.getPosition());
					fitness+=1;
				}
				if(pontos.size()>50){
					for (int i = 0; i < pontos.size()-20; i++) {
							pontos.remove(i);
					}
				}
			}
		}
		
		/*fitness += (double) numberOfRobotsCloseToNest * 0.1;
		if(pos.distanceTo(nestPosition) <=0.01){
			fitness+=10;
			//simulator.getEnvironment().getRobots().get(0).stop();
		}*/
	}
}
