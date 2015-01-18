package evaluationfunctions.coevolution;

import mathutils.Vector2d;
import simulation.Simulator;
import simulation.robot.Robot;
import simulation.robot.sensors.RobotRGBColorSensor;
import simulation.util.Arguments;
import evolutionaryrobotics.evaluationfunctions.EvaluationFunction;

/**
 * 
 * @author Nuno e Diogo Evaluation Function of team A, chase team B robots
 */
public class PatrolNestEvaFunction extends EvaluationFunction {
	String team = "teama";
	boolean inPersuit = false;

	public PatrolNestEvaFunction(Arguments args) {
		super(args);
	}

	@Override
	public void update(Simulator simulator) {
		for (Robot r : simulator.getEnvironment().getRobots()) {
				if (r.getDescription().equals(team)) {
					/**
					 * The fitness increases when the robot is closer of team B
					 * robots The fitness decreases when the robots is far from
					 * team B robots
					 */
					try {
						if (r.getSensorWithId(3).isEnabled() && r.getSensorWithId(2).isEnabled()) {
							fitness += r.getSensorWithId(2).getSensorReading(2)-0.1;
							if(!inPersuit) fitness += r.getSensorWithId(3).getSensorReading(3)-0.3;
							if(r.getSensorWithId(2).getSensorReading(2)>0.4) inPersuit=true;
							if(r.getSensorWithId(2).getSensorReading(2)>0.6){
								fitness+=(r.getSensorWithId(2).getSensorReading(2));
								
							}
							/*if(r.getSensorWithId(2).getSensorReading(2)>0.5) inPersuit=true;
                            if(r.getSensorWithId(3).getSensorReading(3)<0.4 && !inPersuit) fitness -=1;
                            if(!inPersuit && r.getSensorWithId(3).getSensorReading(3)>=0.7)fitness +=1;
                            if(!inPersuit && r.getSensorWithId(3).getSensorReading(3)<=0.5)fitness-=0.5;
                            //if(!inPersuit) fitness+=r.getSensorWithId(3).getSensorReading(3);
                            if(r.getSensorWithId(2).getSensorReading(2)>0.1)fitness += r.getSensorWithId(2).getSensorReading(2);   
                            if(inPersuit && r.getSensorWithId(2).getSensorReading(2)>0.5) {
                            	fitness +=1;
                            }
                            if(inPersuit && r.getSensorWithId(2).getSensorReading(2)<0.1){
                            	fitness-=0.5;
                            }
                            if(inPersuit && r.getSensorWithId(2).getSensorReading(2)<=0.9){
                            	fitness+=100;
                            	simulator.stopSimulation();
                            }*/
                        
						}
					} catch (Exception e) {
						System.err.println("Nest Sensor or RGB Color Sensor error");
					}
				}
			

		}
	}

}
