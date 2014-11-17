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
/**
 * 
 * @author Diogo & Nuno
 * 
 * Bring the prey to the nest and keep protect it
 */
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
			/**
			 * 	check for the prey that is closest
			 */
			for(Prey p : environment.getPrey()) {
				double distance = p.getPosition().distanceTo(r.getPosition());
				if(distance < closestPreyDistance) {
					closestPreyDistance = distance;
				}
			}
			
			/**
			 * earn fitness as robot approach the prey
			 */
			fitness+= 2-closestPreyDistance;
			/**
			 * if the robot is carrying prey earns more 1 of fitness
			 */
			if(actuator.isCarryingPrey())
				fitness+=1;
			/**
			 * 	if the robot is close to the prey and close to the nest earns 10 more points
			 */
			if(closestPreyDistance < 0.2 && r.getPosition().distanceTo(nestPosition)< NESTRADIUS){
				fitness+=10;
			}
		}
		
		
		
	}

}
