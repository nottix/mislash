package slash.resource.agent;

import jade.core.AID;
import jade.core.Agent;
import slash.df.DFUtil;
import slash.resource.behaviour.CpuBehaviour;

public class CpuAgent extends Agent {

	private static final long serialVersionUID = -5570077702241336240L;
	
	protected void setup() {
		System.out.println("Cpu: "+this.getName());

		DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = DFUtil.search(this, "cm"+this.getName().charAt(2), "context-manager");
		this.addBehaviour(new CpuBehaviour(cmAid));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "resource");
	}


}
