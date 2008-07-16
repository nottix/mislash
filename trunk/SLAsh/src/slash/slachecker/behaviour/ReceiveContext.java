package slash.slachecker.behaviour;

import jade.core.behaviours.*;
import slash.slachecker.agent.*;

public class ReceiveContext extends CyclicBehaviour {
	
	private static final long serialVersionUID = 5235996069711181357L;

	private SlaCheckerAgent sc;

	public ReceiveContext(SlaCheckerAgent sc) {
		this.sc = sc;
	}
	
	public void action() {
	
		myAgent.blockingReceive();
	}

}
