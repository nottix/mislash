package slash.dsm.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;

import slash.dsm.agent.DsmServerAgent;
import slash.dsm.data.DsmDataManager;
import slash.dsm.tuple.Tuple;
import slash.entity.Context;
import slash.entity.Notify;

public class DsmServerBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 6270841980860732385L;
	
	private DsmDataManager dsmDataManager;
	private int associatedID;
	
	public DsmServerBehaviour(DsmServerAgent agent) {
		this.dsmDataManager = new DsmDataManager();
		this.associatedID = 0;
	}
	
	public void action() {
		try {
			MessageTemplate mt = MessageTemplate.MatchConversationId("dsm");
			ACLMessage recvMsg = myAgent.receive(mt);
			Tuple tuple = null;
			if(recvMsg!=null) {
				tuple = (Tuple)recvMsg.getContentObject();
				doOperation(tuple, recvMsg.getSender());
				tuple = dsmDataManager.read("notify", "notify");
				if(tuple!=null && tuple.getValue()!=null) {
					Notify notify = (Notify)tuple.getValue();
					int dest = notify.getDest();
					if(dest!=this.associatedID) {
						tuple = dsmDataManager.read(String.valueOf(dest), "context");
						if(tuple!=null && tuple.getValue()!=null) {
							Context context = (Context)tuple.getValue();
							myAgent.doMove(context.getLocation());
							System.out.println("Moving DSM to "+dest);
							this.associatedID = dest;
						}
					}
				}

			}
			else
				block();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int doOperation(Tuple tuple, AID sender) {
		try {
			if(tuple!=null) {
				if(tuple.getOperation().equals(Tuple.IN)) {
					Tuple tupleRes = dsmDataManager.in(tuple.getIndex(), tuple.getType());
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(sender);
					msg.setLanguage("English");
					msg.setConversationId("dsm");
					msg.setContentObject(tupleRes);
					myAgent.send(msg);
				}
				else if(tuple.getOperation().equals(Tuple.READ)) {
					Tuple tupleRes = dsmDataManager.read(tuple.getIndex(), tuple.getType());
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(sender);
					msg.setLanguage("English");
					msg.setConversationId("dsm");
					msg.setContentObject(tupleRes);
					myAgent.send(msg);
				}
				else if(tuple.getOperation().equals(Tuple.OUT)) {
					dsmDataManager.out(tuple.getIndex(), tuple.getType(), tuple.getValue());
				}
				else if(tuple.getOperation().equals(Tuple.UPDATE)) {
					dsmDataManager.update(tuple.getIndex(), tuple.getType(), tuple.getValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
