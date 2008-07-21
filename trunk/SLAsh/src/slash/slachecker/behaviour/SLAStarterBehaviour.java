package slash.slachecker.behaviour;

import java.io.IOException;

import slash.entity.Notify;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import slash.slachecker.agent.*;

public class SLAStarterBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -1382758463374415598L;

	private SLACheckerAgent sc;
	
//	public SLAStarterBehaviour(SLACheckerAgent agent) {
//		super(agent, 1000);
//		this.sc = agent;
//	}
	
	
	public void action() {
		notifyMigration(0, 1); //Avvertire il primo nodo della presenza dello sc
	}

	public void notifyMigration(int src, int dest) {

		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		
		if(src!=0) {
			msg.addReceiver(new AID("cpu"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("energy"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("ram"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("memory"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("latency"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("reliability"+src, AID.ISLOCALNAME));
			msg.addReceiver(new AID("reqInterval"+src, AID.ISLOCALNAME));
		}
		
		msg.addReceiver(new AID("cpu"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("energy"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("ram"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("memory"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("latency"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("reliability"+dest, AID.ISLOCALNAME));
		msg.addReceiver(new AID("reqInterval"+dest, AID.ISLOCALNAME));
		msg.setLanguage("English");
		Notify notify = new Notify(src, dest);
		try {
			msg.setContentObject(notify);
		} catch (IOException e) {
			e.printStackTrace();
		}
		msg.setConversationId("notify migration");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myAgent.send(msg);
	}
}
