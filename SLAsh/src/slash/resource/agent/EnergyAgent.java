package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class EnergyAgent extends Agent {

	private static final long serialVersionUID = 7577668356120531127L;

	protected void setup() {
		System.out.println("Energy: "+this.getName());

		//DFUtil.register(this, this.getLocalName(), "resource");
		
		AID cmAid = new AID("cm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new EnergyBehaviour(cmAid));

	}
	
	protected void takeDown() {
		//DFUtil.deregister(this, this.getLocalName(), "resource");
	}
}
