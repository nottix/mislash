package slash.contextmanager.agent;

import jade.core.Agent;
import slash.contextmanager.behaviour.ContextReceiveBehaviour;
import slash.contextmanager.behaviour.ContextRequestBehaviour;
import slash.df.DFUtil;
import slash.entity.Context;

public class ContextManagerAgent extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	private Context context;
	
	protected void setup() {
		System.out.println("ContextManagerAgent: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "context-manager");
		this.context = new Context();
		this.addBehaviour(new ContextReceiveBehaviour(this));
		this.addBehaviour(new ContextRequestBehaviour(this));

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
