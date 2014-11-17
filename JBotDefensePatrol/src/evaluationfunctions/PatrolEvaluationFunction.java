package evaluationfunctions;

import java.util.ArrayList;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.util.Arguments;
import environmentpatrol.SimpleEnvironment;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

/**
 * 
 * @author Nuno e Diogo
 * Evaluation function to patrol near the nest
 */
public class PatrolEvaluationFunction extends EvaluationFunction{
	private Vector2d    nestPosition = new Vector2d(0, 0);
	private double      NESTRADIUS;
	private ArrayList<Vector2d> pontos = new ArrayList<Vector2d>();
	private double      MAX_PATROL_AREA=0.6;
	private double      MIN_PATROL_AREA=0.5;

	public PatrolEvaluationFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {			
		int numberOfRobotsCloseToNest = 0;
		NESTRADIUS         = ((SimpleEnvironment)(simulator.getEnvironment())).getNestRadius();
		Vector2d coord = new Vector2d();
		for(Robot r : simulator.getEnvironment().getRobots()){
			coord.set(r.getPosition());
			double distanceToNest = coord.distanceTo(nestPosition);
			/**
			 * Verify is there is any robots in the designated patrol area
			 */
			if (distanceToNest < NESTRADIUS + MAX_PATROL_AREA) {
				numberOfRobotsCloseToNest++;
			} 
			/**
			 * Fitness is increased if there is any robots near the nest
			 */
			fitness += (double) numberOfRobotsCloseToNest * 0.1;
			if(distanceToNest > MIN_PATROL_AREA && distanceToNest < MAX_PATROL_AREA){
				fitness+=1;
// Comentada a sec��o que verificava se o robot andava para tr�s por n�o ser consistente, para descomentar seleccionar linhas e "ctr + shift + /"				
//				boolean haspass = false;
//				for (int i = 0; i < pontos.size(); i++) {
//					/**
//					 * verifica se j� passou naquele ponto
//					 */
//					if(pontos.get(i).distanceTo(r.getPosition()) == 0){
//						haspass=true;
//					}
//				}
//				/**
//				 * a n�o passagem naquele ponto da area de patrulha aumenta ainda mais o desempenho
//				 */
//				
//				if(!haspass) {
//					pontos.add(r.getPosition());
//					fitness+=1;
//				}
//				/**
//				 * limpa a lista para que o robot possa percorrer pontos j� percorrido h� algum tempo
//				 */
//				if(pontos.size()>100){
//					for (int i = 0; i < pontos.size()-50; i++) {
//						pontos.remove(i);
//					}
//				}
			}
		}
	}
}
