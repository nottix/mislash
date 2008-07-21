package slash.resourcemonitor.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.resourcemonitor.agent.*;

public class StatusResourceReceiverBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 6020734463253999461L;
	
	private ResourceMonitorAgent rm;
	
	public StatusResourceReceiverBehaviour(ResourceMonitorAgent agent) {
		this.rm = agent;
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchConversationId("status resource response");
		ACLMessage recvMsg = myAgent.receive(mt);
		if(recvMsg!=null) {
			AID sender = recvMsg.getSender();
			//System.out.println("RECEIVED from "+sender.getLocalName()+" status: "+recvMsg.getContent());
			if(sender.getLocalName().indexOf("latency")>=0) {
				rm.getStatus().addLatencyValue(Float.parseFloat(recvMsg.getContent()));
				//System.out.println("LAT: "+Float.parseFloat(recvMsg.getContent()));
			}
			else if(sender.getLocalName().indexOf("reliability")>=0) {
				rm.getStatus().addReliabilityValue(Float.parseFloat(recvMsg.getContent()));
				//System.out.println("REL: "+Float.parseFloat(recvMsg.getContent()));
			}
			else if(sender.getLocalName().indexOf("reqInterval")>=0) {
				rm.getStatus().addReqIntervalValue(Float.parseFloat(recvMsg.getContent()));
				//System.out.println("REQ: "+Float.parseFloat(recvMsg.getContent()));
			}
		}
		else
			block();
	}
}
