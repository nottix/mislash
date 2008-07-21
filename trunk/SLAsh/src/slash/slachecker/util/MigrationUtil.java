package slash.slachecker.util;

import jade.core.*;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

import slash.entity.Notify;

public class MigrationUtil {
	
	public static void notifyMigration(Agent agent, int src, int dest) {

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
		agent.send(msg);
	}
}
