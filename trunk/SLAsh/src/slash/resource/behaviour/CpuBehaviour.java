package slash.resource.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.*;
import jade.core.*;
import jade.domain.FIPAAgentManagement.*;

public class CpuBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -3231027298580344751L;

	private AID cmAid;
	
	public CpuBehaviour(AID cmAid) {
		this.cmAid = cmAid;
	}
	
	public void action() {
		

	}
}
