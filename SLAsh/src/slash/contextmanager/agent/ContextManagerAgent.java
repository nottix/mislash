package slash.contextmanager.agent;

import jade.core.Agent;
import slash.contextmanager.behaviour.ResourceReceiverBehaviour;
import slash.contextmanager.behaviour.ResourceRequesterBehaviour;
import slash.contextmanager.behaviour.ContextReqReceiverBehaviour;
import slash.df.DFUtil;
import slash.entity.Context;

public class ContextManagerAgent extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	private Context context;
	
	protected void setup() {
		System.out.println("ContextManagerAgent: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "context-manager");
		this.context = new Context();
		this.addBehaviour(new ResourceReceiverBehaviour(this));
		this.addBehaviour(new ResourceRequesterBehaviour(this));
		this.addBehaviour(new ContextReqReceiverBehaviour(this));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "context-manager");
	}
	
	public Context getContext() {
		return this.context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
}
