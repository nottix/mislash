package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

import slash.df.DFUtil;
import slash.entity.SLAContract;

public class SLAReqReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 1897715821469100876L;

	public float genLatency() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReliability() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReqInterval() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("SLAContract request");
			ACLMessage recvMsg = myAgent.receive(mt);
			
			if(recvMsg!=null) {
				AID sc = DFUtil.search(myAgent, "sc", "sla-checker");
				SLAContract contract = new SLAContract(myAgent.getAID(), new AID("cm"+myAgent.getLocalName().charAt(2), AID.ISLOCALNAME), recvMsg.getSender(),  new AID("cm"+recvMsg.getSender().getLocalName().charAt(2), AID.ISLOCALNAME), this.genLatency(), this.genReliability(), this.genReqInterval());
				
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(sc);
				msg.setLanguage("English");
				msg.setContentObject(contract);
				msg.setConversationId("SLAContract send");
				myAgent.send(msg);
			}
			else
				block();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
