package slash.contextmanager.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ContextReceiveBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);;
	ACLMessage recvMsg;
	
	public void action() {
		recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			
		}
		else
			block();
	}

}
