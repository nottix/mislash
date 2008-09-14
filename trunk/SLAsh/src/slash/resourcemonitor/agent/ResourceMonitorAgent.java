package slash.resourcemonitor.agent;

import jade.core.Agent;
import slash.df.DFUtil;
import slash.resourcemonitor.behaviour.*;
import slash.entity.*;

public class ResourceMonitorAgent extends Agent {

	private static final long serialVersionUID = 2514514536861766799L;

	private Status status;
	
	protected void setup() {
		Object[] args = this.getArguments();
		System.out.println("ResourceMonitorAgent: "+this.getName()+", args len: "+args.length);
		if(args.length>0 && args[0].toString().equals("publisher")) {
			DFUtil.register(this, this.getLocalName(), "publisher");
			this.addBehaviour(new SLAReqReceiverBehaviour(this));
		}
		else if(args.length>0 && args[0].toString().equals("subscriber")) {
			//DFUtil.register(this, this.getLocalName(), "subscriber");
			this.addBehaviour(new SLARequesterBehaviour(this));
		}
		
		//this.addBehaviour(new CoreBehaviour(this));
		this.addBehaviour(new StatusProducerBehaviour(this));
		this.addBehaviour(new StatusResourceConsumerBehaviour(this));
		this.addBehaviour(new ViolationReceiverBehaviour(this));
		
		status = new Status();

	}
	
	public synchronized Status getStatus() {
		return this.status;
	}
	
	protected void takeDown() {
		Object[] args = this.getArguments();
		if(args.length>0 && args[0].toString().equals("publisher")) {
			DFUtil.deregister(this, this.getLocalName(), "publisher");
		}
		else if(args.length>0 && args[0].toString().equals("subscriber")) {
			//DFUtil.deregister(this, this.getLocalName(), "subscriber");
		}
	}
}
