package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class LatencyAgent extends Agent {

	private static final long serialVersionUID = -5626212945073184408L;

	protected void setup() {
		System.out.println("Latency: "+this.getName());

		DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = DFUtil.search(this, "cm"+this.getLocalName().charAt(this.getLocalName().length()-1), "context-manager");
		this.addBehaviour(new LatencyBehaviour(cmAid));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
