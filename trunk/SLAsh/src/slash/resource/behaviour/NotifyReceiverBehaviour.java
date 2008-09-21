package slash.resource.behaviour;

import jade.core.behaviours.TickerBehaviour;
import slash.dsm.client.DsmClient;
import slash.dsm.tuple.Tuple;
import slash.entity.Notify;
import slash.resource.agent.ResourceAgent;
import slash.util.PropertiesReader;

public class NotifyReceiverBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 4984068838158410640L;

	private ResourceAgent ra;
	private DsmClient dsmClient;
	
	public NotifyReceiverBehaviour(ResourceAgent ra) {
		super(ra, Integer.parseInt(PropertiesReader.getProperty("notify.tick")));
		this.ra = ra;
		this.dsmClient = new DsmClient(ra);
	}

	protected void onTick() {
		Tuple tuple = dsmClient.read("notify", "notify");
		Notify notify = null;
		if(tuple!=null) {
			notify = (Notify)tuple.getValue();
		}
		if(notify!=null) {
			int num = Integer.valueOf(String.valueOf(myAgent.getLocalName().charAt(myAgent.getLocalName().length()-1)));
			
			if(notify.getSrc() == num) {
				ra.setLocalSC(false);
			}
			else if(notify.getDest() == num) {
				ra.setLocalSC(true);
			}
			else
				ra.setLocalSC(false);
		}
	}

}
