package slash.resource.agent;

import jade.core.AID;
import slash.entity.Context;
import slash.resource.behaviour.NotifyReceiverBehaviour;
import slash.resource.behaviour.ReqIntervalBehaviour;

public class ReqIntervalAgent extends ResourceAgent {

	private static final long serialVersionUID = -2452255667787453718L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("ReqInterval: "+this.getName());
		
		Object[] args = this.getArguments();
		if(args.length==2) {
			if(args[0].toString().equals("wired"))
				network = Context.WIRED;
			else
				network = Context.WIRELESS;
			
			bandwidth = (Integer)args[1];
		}
		
		this.rm = new AID("rm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new ReqIntervalBehaviour(rm, this));
		this.addBehaviour(new NotifyReceiverBehaviour(this));

	}
	
	public int getNetwork() {
		return this.network;
	}
	
	public int getBandwidth() {
		return this.bandwidth;
	}
}
