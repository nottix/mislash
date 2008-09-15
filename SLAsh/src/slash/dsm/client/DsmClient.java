package slash.dsm.client;

import java.io.IOException;

import slash.df.DFUtil;
import slash.dsm.tuple.*;
import slash.entity.*;
import jade.core.*;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

public class DsmClient {

	private Agent agent;
	private AID dsm;
	
	public DsmClient(Agent agent) {
		this.agent = agent;
		
//		DFAgentDescription[] res = DFUtil.search(agent, "dsm");
//		dsm = res[0].getName();
		
		dsm = new AID("dsm", AID.ISLOCALNAME);
	}
	
	public int out(String index, String type, Object value) {
		try {
			if(!index.matches("\\D*"))
				index = index.replaceAll("\\D*", "");
			Tuple tuple = new Tuple(Tuple.OUT, type, index, value);
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(dsm);
			msg.setLanguage("English");
			msg.setConversationId("dsm");
			msg.setContentObject(tuple);
			//System.out.println("Sending msg to dsm");
			agent.send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Tuple in(String index, String type) {
		try {
			if(!index.matches("\\D*"))
				index = index.replaceAll("\\D*", "");
			//System.out.println("INDEX: "+index);
			Tuple tuple = new Tuple(Tuple.IN, type, index, null);
			
//			if(type.equals("slacontract"))
//				System.out.println("CONTRATTO Richiesto");
			//System.out.println("Selected dsm: "+dsm.getLocalName()+", sending: index "+index+", type "+type);
			
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(dsm);
			msg.setLanguage("English");
			msg.setConversationId("dsm");
			msg.setContentObject(tuple);
			agent.send(msg);
			//System.out.println(agent.getLocalName()+" -> SEND");
			
			ACLMessage recvMsg = agent.blockingReceive();
			//System.out.println(agent.getLocalName()+" -> RESPONSE");
//			if(type.equals("slacontract"))
//				System.out.println("CONTRATTO Ricevuto?");
			if(recvMsg!=null) {
				Object obj = recvMsg.getContentObject();
				//System.out.println("RECV!!! "+obj);
				if(obj!=null) {
//					if(type.equals("slacontract"))
//						System.out.println("CONTRATTO Ricevuto");
					return ((Tuple)obj);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * OBSOLETA
	 * @param type
	 * @return
	 */
	public Tuple in(String type) {
		try {
			
			Tuple tuple = new Tuple(Tuple.IN, type, null, null);			
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(dsm);
			msg.setLanguage("English");
			msg.setConversationId("dsm");
			msg.setContentObject(tuple);
			agent.send(msg);
			//System.out.println(agent.getLocalName()+" -> SEND");
			ACLMessage recvMsg = agent.blockingReceive();
			//System.out.println(agent.getLocalName()+" -> RESPONSE");
			if(recvMsg!=null) {
				Object obj = recvMsg.getContentObject();
				//System.out.println("RECV!!! "+obj);
				if(obj!=null) {
					Tuple tuple2 = (Tuple)obj;
					if(tuple2.getType().equals("context") && tuple2.getValue()!=null)
						System.out.println("RECV!!! cpu: "+((Context)tuple2.getValue()).getCpu());
					return ((Tuple)obj);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
