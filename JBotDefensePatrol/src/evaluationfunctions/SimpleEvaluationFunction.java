package evaluationfunctions;

import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;
import mathutils.Vector2d;
import simulation.Simulator;
import simulation.environment.RoundForageEnvironment;
import simulation.robot.Robot;
import simulation.util.Arguments;

/* 
 * Fun��o para mover robot at� ao ninho
 */

public class SimpleEvaluationFunction extends EvaluationFunction {
	private Vector2d    nestPosition = new Vector2d(0, 0);
	private double      NESTRADIUS;
	private double lastDistanceToNest = 99999999;

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
			if(lastDistanceToNest==99999999){
				lastDistanceToNest=pos.distanceTo(nestPosition);
			}
			/* D� recompensa de cada vez que se aproxima do ninho e d� castigo de cada vez que se afasta */
			if(lastDistanceToNest>pos.distanceTo(nestPosition)){
				fitness+=(lastDistanceToNest-pos.distanceTo(nestPosition))*100;
			}else{
				fitness-=(pos.distanceTo(nestPosition)-lastDistanceToNest)*100;
			}
			lastDistanceToNest = pos.distanceTo(nestPosition);
			// D� recompensa quando o robot est� no meio
			if(pos.distanceTo(nestPosition) <=0.01){
				fitness+=2;
			}
		}
		
	}

}
