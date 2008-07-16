package slash.contextmanager.agent;

import jade.core.Agent;

public class ContextManager extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	protected void setup() {
		System.out.println("ContextManager: "+this.getName());
	}
}
