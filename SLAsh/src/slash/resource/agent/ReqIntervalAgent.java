package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class ReqIntervalAgent extends Agent {

	private static final long serialVersionUID = -2452255667787453718L;

	protected void setup() {
		System.out.println("ReqInterval: "+this.getName());

		DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = DFUtil.search(this, "cm"+this.getName().charAt(2), "context-manager");
		this.addBehaviour(new ReqIntervalBehaviour(cmAid));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
