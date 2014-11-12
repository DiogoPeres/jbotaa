package evaluationfunctions;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.physicalobjects.Nest;
import simulation.physicalobjects.Prey;
import simulation.robot.Robot;
import simulation.robot.actuators.PreyPickerActuator;
import simulation.util.Arguments;
import environmentpatrol.CoEvoPatrolEnvironment;
import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

public class BringPreyEvaluationFunction extends EvaluationFunction {
	private Vector2d nestPosition = new Vector2d(0, 0);
	private double NESTRADIUS;
	private double GOOD_DISTANCE_TO_PREY = 0.2;

	public BringPreyEvaluationFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		NESTRADIUS         = ((SimpleEnvironment)(simulator.getEnvironment())).getNestRadius();
		SimpleEnvironment environment = (SimpleEnvironment) simulator.getEnvironment();
		for(Robot r : environment.getRobots()) {
			PreyPickerActuator actuator = (PreyPickerActuator)r.getActuatorByType(PreyPickerActuator.class);
			double closestPreyDistance = Double.MAX_VALUE;
			for(Prey p : environment.getPrey()) {
				double distance = p.getPosition().distanceTo(r.getPosition());
				if(distance < closestPreyDistance) {
					closestPreyDistance = distance;
				}
			}
			/*if(closestPreyDistance < 0.2) {
				fitness+= 0.2;
				//fitness += (environment.getForageRadius() - closestPreyDistance) / environment.getForageRadius() * .0001;
			}*/
			fitness+= 2-closestPreyDistance;
			if(closestPreyDistance < 0.2 && r.getPosition().distanceTo(nestPosition)< NESTRADIUS){
				fitness+=10;
				/*double robotDistanceToNest = r.getPosition().distanceTo(nestPosition);
				if(robotDistanceToNest < 0.05)
					fitness += 10;
					
			}*/
			}
		}
		
		
		
	}

}
