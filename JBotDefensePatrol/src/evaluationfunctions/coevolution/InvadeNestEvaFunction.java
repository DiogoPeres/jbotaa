package evaluationfunctions.coevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import environmentpatrol.CoEvoPatrolEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

public class InvadeNestEvaFunction extends EvaluationFunction{
	private Vector2d nestPosition;
	private double NEST = 1;
	private double GOOD_DISTANCE_TO_NEST = 0.7;
	String team="teamb";
	private boolean win = false;
	private double CATCHED_DISTANCE_TO_ROBOT=0.2;
	
	public InvadeNestEvaFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		
		/**
		 * The farther of team A robots, the more the fitness increases
		 * The closer of team A robots, the more the fitness decreases 
		 * Fitness also increases when the robot is close to the nest
		 */
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					try{
						if(r.getSensorWithId(2).isEnabled() && r.getSensorWithId(3).isEnabled()){
							
							if(r.getSensorWithId(3).getSensorReading(3) >= NEST && r.getSensorWithId(2).getSensorReading(2)<=CATCHED_DISTANCE_TO_ROBOT){
								win = true;
								fitness=fitness+100;
								simulator.stopSimulation();
							}
							if(!win){
								fitness+=0.3-r.getSensorWithId(2).getSensorReading(2);
								fitness+=r.getSensorWithId(3).getSensorReading(3)-0.1;
								if(r.getSensorWithId(3).getSensorReading(3)>=GOOD_DISTANCE_TO_NEST){
									fitness++;
								}
							}
							
							
						}
					}catch(Exception e){
						System.err.println("RGB Color Sensor error");
					}
					
					
					/**
					 * objective completed
					 */
					
					
					/**
					 * objective NOT completed
					 */
					
				}
			}

		}
	}
}

