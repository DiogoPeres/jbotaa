package evaluationfunctions.coevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import environmentpatrol.CoEvoPatrolEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
/**
 * 
 * @author Diogo e Nuno Evaluation Function of team B, invade nest without being detected and / or being catched
 *
 */
public class InvadeNestEvaFunction extends EvaluationFunction {
	private Vector2d nestPosition;
	private double NEST = 0.9;
	private double GOOD_DISTANCE_TO_NEST = 0.7;
	String team = "teamb";
	private boolean win = false;
	private boolean completed=false;
	private double CATCHED_DISTANCE_TO_ROBOT = 0.2;

	public InvadeNestEvaFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {

		/**
		 * The farther of team A robots, the more the fitness increases The
		 * closer of team A robots, the more the fitness decreases Fitness also
		 * increases when the robot is close to the nest
		 */
		for (Robot r : simulator.getEnvironment().getRobots()) {
				if (r.getDescription().equals(team)) {
					
					try {
						if (r.getSensorWithId(2).isEnabled()
								&& r.getSensorWithId(3).isEnabled()) {

							if (r.getSensorWithId(3).getSensorReading(3) > NEST
									&& r.getSensorWithId(2).getSensorReading(2) <= CATCHED_DISTANCE_TO_ROBOT && !completed) {
									//remove ->  && !completed
								fitness = 1+r.getSensorWithId(3).getSensorReading(3);
								completed=true;
								//simulator.stopSimulation();
							}
							
								if(!completed && r.getSensorWithId(2).getSensorReading(2)>0.3)fitness -= 0.1;
								if(completed)fitness += 0.2-r.getSensorWithId(2).getSensorReading(2);
								//if(completed)fitness += 0.5-r.getSensorWithId(2).getSensorReading(2);
								//if(completed)fitness += 1-r.getSensorWithId(3).getSensorReading(3);
								if(!completed)fitness += r.getSensorWithId(3).getSensorReading(3)-0.6;
								if(r.getSensorWithId(3).getSensorReading(3)>=0.9)completed=true;
							

						}
					} catch (Exception e) {
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
