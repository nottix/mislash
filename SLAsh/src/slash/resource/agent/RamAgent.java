package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class RamAgent extends Agent {

	private static final long serialVersionUID = -630447104765764031L;

	protected void setup() {
		System.out.println("Ram: "+this.getName());

		DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = DFUtil.search(this, "cm"+this.getLocalName().charAt(this.getLocalName().length()-1), "context-manager");
		this.addBehaviour(new RamBehaviour(cmAid));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
