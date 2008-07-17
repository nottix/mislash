package slash.contextmanager.behaviour;

import java.io.IOException;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import slash.contextmanager.agent.*;
import slash.df.DFUtil;

public class ContextReqReceiverBehaviour extends CyclicBehaviour{

	private static final long serialVersionUID = -884539926477308910L;
	
	private AID scAid;
	private ContextManagerAgent cm;
	
	public ContextReqReceiverBehaviour(ContextManagerAgent agent) {
		this.cm = agent;
		scAid = DFUtil.search(agent, "sc", "sla-checker");
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("context request");
			ACLMessage recvMsg = myAgent.receive(mt);
			if(recvMsg!=null) {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(scAid);
				msg.setLanguage("English");
				msg.setContentObject(cm.getContext());
				msg.setConversationId("context response");
				myAgent.send(msg);
			}
			else
				block();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
