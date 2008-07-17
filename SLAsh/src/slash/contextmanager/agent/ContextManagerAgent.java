package slash.contextmanager.agent;

import jade.core.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import slash.contextmanager.behaviour.*;
import slash.df.DFUtil;

public class ContextManagerAgent extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	protected void setup() {
		System.out.println("ContextManagerAgent: "+this.getName());
		DFUtil.register(this, this.getLocalName(), "context-manager");
		this.addBehaviour(new ContextRequest(this));

	}
	
	protected void takeDown() {
		DFUtil.deregister(this, this.getLocalName(), "context-manager");
	}
}
