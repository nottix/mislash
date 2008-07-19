package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class MemoryAgent extends Agent {

	private static final long serialVersionUID = -5491334259558404278L;

	protected void setup() {
		System.out.println("Memory: "+this.getName());

		//DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = new AID("cm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new MemoryBehaviour(cmAid));

	}
	
	protected void takeDown() {
		//DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
