package slash.resourcemonitor.agent;

import jade.core.Agent;
import slash.resourcemonitor.behaviour.LatencyResourceBehaviour;

public class LatencyRmAgent extends Agent {

	private static final long serialVersionUID = 2514514536861766799L;
	
	protected void setup() {
		this.addBehaviour(new LatencyResourceBehaviour(this));
	}
}
