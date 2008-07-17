package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.contextmanager.agent.ContextManagerAgent;

public class ResourceReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ContextManagerAgent agent;
	
	public ResourceReceiverBehaviour(ContextManagerAgent agent) {
		this.agent = agent;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("resource response");
		ACLMessage recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			AID sender = recvMsg.getSender();
			System.out.println("recv from "+sender.getLocalName()+" content: "+recvMsg.getContent());
			if(sender.getLocalName().indexOf("cpu")>=0) {
				agent.getContext().setCpu(Float.parseFloat(recvMsg.getContent()));
				System.out.println("cpu: "+agent.getContext().getCpu());
			}
			else if(sender.getLocalName().indexOf("energy")>=0) {
				agent.getContext().setEnergy(Float.parseFloat(recvMsg.getContent()));
				System.out.println("energy: "+agent.getContext().getEnergy());
			}
			else if(sender.getLocalName().indexOf("memory")>=0) {
				agent.getContext().setMemory(Float.parseFloat(recvMsg.getContent()));
				System.out.println("memory: "+agent.getContext().getMemory());
			}
			else if(sender.getLocalName().indexOf("ram")>=0) {
				agent.getContext().setRam(Float.parseFloat(recvMsg.getContent()));
				System.out.println("ram: "+agent.getContext().getRam());
			}
		}
		else
			block();
	}

}
