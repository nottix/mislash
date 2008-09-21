package slash.contextmanager.behaviour;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import slash.contextmanager.agent.ContextManagerAgent;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.entity.SLAContract;
import slash.util.PropertiesReader;

public class SLAReqReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 1897715821469100876L;

	private DsmClient dsmClient;
	private ContextManagerAgent cm;
	
	public SLAReqReceiverBehaviour(ContextManagerAgent agent) {
		super(agent, Integer.parseInt(PropertiesReader.getProperty("slareqreceiver.tick")));
		this.cm = agent;
		this.dsmClient = new DsmClient(agent);
	}
	
	public float genLatency() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReliability() {
		return (float)(((Math.random()*100))%30)+70;
	}
	
	public float genReqInterval() {
		return (float)(((Math.random()*100))%30)+70;
	}
		
	protected void onTick() {
		Tuple req;
		if((req=(Tuple)dsmClient.in("slacontract", "slacontract-request"))!=null) {
			if(req.getValue()!=null) {
				System.out.println("SLAContract-request received from "+req.getValue());
				AID requester = new AID((String)req.getValue(), AID.ISLOCALNAME);
				SLAContract contract = new SLAContract(myAgent.getAID(), requester, this.genLatency(), this.genReliability(), this.genReqInterval());
				dsmClient.out("slacontract", "slacontract", contract);
			}
		}
	}
}
