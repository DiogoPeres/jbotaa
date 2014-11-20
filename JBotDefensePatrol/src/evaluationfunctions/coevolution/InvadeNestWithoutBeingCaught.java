package evaluationfunctions.coevolution;


import environmentpatrol.*;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;

/**
 * @author Diogo & Nuno
 * Evaluation function of team B, robots must go to Team A nest without being caught
 */
public class InvadeNestWithoutBeingCaught extends EvaluationFunction{
	private Vector2d nestPosition;
	private double NESTRADIUS;
	private double CATCHED_DISTANCE_TO_ROBOT = 0.2;
	String team="teamb";
	private boolean win = false;
	
	public InvadeNestWithoutBeingCaught(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		NESTRADIUS         = ((CoEvoPatrolEnvironment)(simulator.getEnvironment())).getNestRadius();
		nestPosition = ((CoEvoPatrolEnvironment)(simulator.getEnvironment())).getNests().getFirst().getPosition();
		double distanceToEnemyRobot = 0;
		double distanceToNest = 0;
		/**
		 * The farther of team A robots, the more the fitness increases
		 * The closer of team A robots, the more the fitness decreases 
		 * Fitness also increases when the robot is close to the nest
		 */
		for (Robot r : simulator.getEnvironment().getRobots()) {
			for (Robot r2 : simulator.getEnvironment().getRobots()) {
				if(r.getDescription().equals(team) && !r2.getDescription().equals(team)){
					distanceToEnemyRobot = r.getPosition().distanceTo(r2.getPosition());
					distanceToNest = r.getPosition().distanceTo(nestPosition);
					
					/**
					 * objective failed
					 */
					if(distanceToEnemyRobot <= CATCHED_DISTANCE_TO_ROBOT && !win){
						fitness=fitness-100;
						simulator.stopSimulation();
					}
					
					/**
					 * objective completed
					 */
					if(distanceToNest < NESTRADIUS-0.05){
						win = true;
						fitness=fitness+1000;
						simulator.stopSimulation();
					}
					
					/**
					 * objective NOT completed
					 */
					if(!win){
						fitness += distanceToEnemyRobot -1;
						fitness += 1 - distanceToNest;
					}
				}
			}

		}
	}
}
