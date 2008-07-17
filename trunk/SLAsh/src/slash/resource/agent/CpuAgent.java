package slash.resource.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import slash.resource.behaviour.CpuBehaviour;

public class CpuAgent extends Agent {

	private static final long serialVersionUID = -5570077702241336240L;
	
	protected void setup() {
		System.out.println("Cpu: "+this.getName());
		
		try {
			
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription tsd = new ServiceDescription();
			tsd.setType("weather-forecast");
			template.addServices(tsd);
			SearchConstraints sc = new SearchConstraints();
			DFAgentDescription[] res = DFService.search(this, template, sc);
			AID cmAid = res[0].getName();
			this.addBehaviour(new CpuBehaviour(cmAid));
			
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}


}
