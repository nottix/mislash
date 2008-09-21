package slash.dsm.agent;

import jade.core.Agent;
import slash.dsm.behaviour.DsmServerBehaviour;

public class DsmServerAgent extends Agent {

	private static final long serialVersionUID = 4497729865124407532L;

	protected void setup() {
		this.addBehaviour(new DsmServerBehaviour(this));
	}
}
