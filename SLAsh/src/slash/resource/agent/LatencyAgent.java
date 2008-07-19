package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class LatencyAgent extends Agent {

	private static final long serialVersionUID = -5626212945073184408L;

	protected void setup() {
		System.out.println("Latency: "+this.getName());

		//DFUtil.register(this, this.getLocalName(), "resource");
		
		AID rmAid = new AID("rm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new LatencyBehaviour(rmAid));

	}
	
	protected void takeDown() {
		//DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
