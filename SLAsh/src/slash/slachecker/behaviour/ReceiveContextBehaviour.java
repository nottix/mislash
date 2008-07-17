package slash.slachecker.behaviour;

import jade.core.behaviours.*;
import slash.slachecker.agent.*;

public class ReceiveContextBehaviour extends CyclicBehaviour {
	
	private static final long serialVersionUID = 5235996069711181357L;

	private SLACheckerAgent sc;

	public ReceiveContextBehaviour(SLACheckerAgent sc) {
		this.sc = sc;
	}
	
	public void action() {
	
		myAgent.blockingReceive();
	}

}
