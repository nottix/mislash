package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class ReliabilityAgent extends Agent {

	private static final long serialVersionUID = -4576966086955385673L;

	protected void setup() {
		System.out.println("Reliability: "+this.getName());

		DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = DFUtil.search(this, "cm"+this.getName().charAt(2), "context-manager");
		this.addBehaviour(new ReliabilityBehaviour(cmAid));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
