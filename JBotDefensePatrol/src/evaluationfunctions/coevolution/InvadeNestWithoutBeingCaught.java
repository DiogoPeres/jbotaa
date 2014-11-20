package evaluationfunctions.coevolution;


import environmentpatrol.*;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;

/**
 * @author Diogo & Nuno
 */
public class InvadeNestWithoutBeingCaught extends EvaluationFunction{
	private Vector2d nestPosition = new Vector2d(0, 0);
	private double NESTRADIUS;
	private double CATCHED_DISTANCE_TO_ROBOT = 0.4;
	String team="teamb";
	private boolean wasCatched = false;
	private boolean gotToNest = false;
	private boolean win = false;
	
	public InvadeNestWithoutBeingCaught(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		NESTRADIUS         = ((CoEvoPatrolEnvironment)(simulator.getEnvironment())).getNestRadius();
		
		double distanceToEnemyRobot = 0;
		double distanceToNest = 0;
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					/**
					 * The farther of one robot of team A, more increases the fitness
					 * The closer of one robot of team A, more decreases the fitness
					 * The closer of the nest, more increases the fitness
					 */
					
					
					distanceToEnemyRobot = r.getPosition().distanceTo(r2.getPosition());
					distanceToNest = r.getPosition().distanceTo(nestPosition);
					if(distanceToEnemyRobot <= CATCHED_DISTANCE_TO_ROBOT){
						wasCatched = true;
					}
					
					if(distanceToNest <= NESTRADIUS && !wasCatched){
						win = true;
						//fitness++;
					}
					
					if(!win){
						fitness += distanceToEnemyRobot -1;
						fitness += 1 - distanceToNest;
					}
					
					
					
					/*if(distanceToRobot>= GREAT_DISTANCE_TO_ROBOT){
						fitness++;
					}else{
						fitness--;
						if(distanceToRobot<= BAD_DISTANCE_TO_ROBOT){
							fitness=fitness-2;
						}
					}
					*/
				}
			}

		}
		/**
		 * a fitness é incrementada se houver robots junto de presas
		 */

	}
}
