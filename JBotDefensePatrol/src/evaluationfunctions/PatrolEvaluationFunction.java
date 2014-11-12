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
 * Função de avaliação para patrulhar junto ao ninho
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
			 * verifica se há robots na area de patrulha desejada
			 */
			if (distanceToNest < NESTRADIUS + MAX_PATROL_AREA) {
				numberOfRobotsCloseToNest++;
			} 
			/**
			 * fitness é incrementada se houver robots perto do ninho
			 */
			fitness += (double) numberOfRobotsCloseToNest * 0.1;
			if(distanceToNest > MIN_PATROL_AREA && distanceToNest < MAX_PATROL_AREA){
				boolean haspass = false;
				for (int i = 0; i < pontos.size(); i++) {
					/**
					 * verifica se já passou naquele ponto
					 */
					if(pontos.get(i).distanceTo(r.getPosition()) == 0){
						haspass=true;
					}
				}
				/**
				 * a não passagem naquele ponto da area de patrulha aumenta ainda mais o desempenho
				 */
				if(!haspass) {
					pontos.add(r.getPosition());
					fitness+=1;
				}
				/**
				 * limpa a lista para que o robot possa percorrer pontos já percorrido há algum tempo
				 */
				if(pontos.size()>100){
					for (int i = 0; i < pontos.size()-50; i++) {
						pontos.remove(i);
					}
				}
			}
		}
	}
}
