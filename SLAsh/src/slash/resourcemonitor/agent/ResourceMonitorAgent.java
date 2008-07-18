package slash.resourcemonitor.agent;

import jade.core.Agent;
import slash.df.DFUtil;
import slash.resourcemonitor.behaviour.*;

public class ResourceMonitorAgent extends Agent {

	private static final long serialVersionUID = 2514514536861766799L;

	protected void setup() {
		Object[] args = this.getArguments();
		System.out.println("ResourceMonitorAgent: "+this.getName()+", args len: "+args.length);
		if(args.length>0 && args[0].toString().equals("publisher")) {
			DFUtil.register(this, this.getLocalName(), "publisher");
			this.addBehaviour(new SLAReqReceiverBehaviour());
		}
		else if(args.length>0 && args[0].toString().equals("subscriber")) {
			this.addBehaviour(new SLARequesterBehaviour());
		}
			


	}
	
	protected void takeDown() {
		Object[] args = this.getArguments();
		if(args.length>0 && args[0].toString().equals("publisher")) {
			DFUtil.deregister(this, this.getLocalName(), "publisher");
		}
		else if(args.length>0 && args[0].toString().equals("subscriber")) {
			DFUtil.deregister(this, this.getLocalName(), "subscriber");
		}
	}
}
