package slash.resource.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import slash.resource.agent.*;
import slash.entity.*;

public class NotifyReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 4984068838158410640L;

	private ResourceAgent ra;
	
	public NotifyReceiverBehaviour(ResourceAgent ra) {
		this.ra = ra;
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("notify migration");
			ACLMessage recvMsg = myAgent.receive(mt);
			//System.out.println("In attesa di notifica!!!");
			if(recvMsg!=null) {
				System.out.println("Notifica ricevuta!!!");
				Notify notify = (Notify)recvMsg.getContentObject();
				int num = Integer.valueOf(String.valueOf(myAgent.getLocalName().charAt(myAgent.getLocalName().length()-1)));
				
				if(notify.getSrc() == num) {
					ra.setLocalSC(false);
				}
				else if(notify.getDest() == num) {
					ra.setLocalSC(true);
				}
			}
			else
				block();
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}

}
