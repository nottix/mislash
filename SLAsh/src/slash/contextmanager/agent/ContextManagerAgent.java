package slash.contextmanager.agent;

import jade.core.Agent;
import slash.contextmanager.behaviour.RmConsumerBehaviour;
import slash.contextmanager.behaviour.*;
import slash.df.DFUtil;
import slash.entity.Context;

public class ContextManagerAgent extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	private Context context;
	
	protected void setup() {
		Object[] args = this.getArguments();
		System.out.println("CpuRmAgent: "+this.getName()+", args len: "+args.length);
		if(args.length>0 && args[0].toString().equals("publisher")) {
			DFUtil.register(this, this.getLocalName(), "publisher");
			this.addBehaviour(new SLAReqReceiverBehaviour(this));
		}
		else if(args.length>0 && args[0].toString().equals("subscriber")) {
			//DFUtil.register(this, this.getLocalName(), "subscriber");
			this.addBehaviour(new SLARequesterBehaviour(this));
		}
		
		
		System.out.println("ContextManagerAgent: "+this.getName());
		//DFUtil.register(this, this.getLocalName(), "context-manager");
		this.context = new Context();
		this.context.setLocation(this.here());
		this.addBehaviour(new RmConsumerBehaviour(this));
		this.addBehaviour(new ViolationConsumerBehaviour(this));
		this.addBehaviour(new ContextProducerBehaviour(this));

	}
	
	protected void takeDown() {
		//DFUtil.deregister(this, this.getLocalName(), "context-manager");
	}
	
	public Context getContext() {
		return this.context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
}
