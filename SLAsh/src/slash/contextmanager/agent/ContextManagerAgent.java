package slash.contextmanager.agent;

import jade.core.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;

public class ContextManagerAgent extends Agent {

	private static final long serialVersionUID = 5985223370274396652L;

	protected void setup() {
		System.out.println("ContextManagerAgent: "+this.getName());
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("context-manager");
		sd.setType("context-manager");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}

	}
}
