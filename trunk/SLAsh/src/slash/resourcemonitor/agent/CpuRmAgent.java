package slash.resourcemonitor.agent;

import jade.core.Agent;
import slash.resourcemonitor.behaviour.CpuResourceBehaviour;

public class CpuRmAgent extends Agent {

	private static final long serialVersionUID = 2514514536861766799L;
	
	protected void setup() {
		this.addBehaviour(new CpuResourceBehaviour(this));
	}
}
