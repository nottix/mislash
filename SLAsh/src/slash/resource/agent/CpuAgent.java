package slash.resource.agent;

import jade.core.Agent;

public class CpuAgent extends Agent {

	private static final long serialVersionUID = -5570077702241336240L;
	
	protected void setup() {
		System.out.println("Cpu: "+this.getName());
	}

}
