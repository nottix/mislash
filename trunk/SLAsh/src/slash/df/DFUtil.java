package slash.df;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.core.*;

public class DFUtil {

	public static void register(Agent agent, String name, String type) {
		try {
			System.out.println("Registering name: "+name+", type: "+type);
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(agent.getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setName(name);
			sd.setType(type);
			dfd.addServices(sd);
			DFService.register(agent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
	
	public static DFAgentDescription[] search(Agent agent, String type) {
		try {
			System.out.println("Searching type: "+type);
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription tsd = new ServiceDescription();
			tsd.setType(type);
			template.addServices(tsd);
			SearchConstraints sc = new SearchConstraints();
			DFAgentDescription[] res = DFService.search(agent, template, sc);
			return res;
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static AID search(Agent agent, String name, String type) {
		try {
			DFAgentDescription[] res;
			do {
				System.out.println("Searching name: "+name+", type: "+type);
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription tsd = new ServiceDescription();
				tsd.setName(name);
				tsd.setType(type);
				template.addServices(tsd);
				SearchConstraints sc = new SearchConstraints();
				res = DFService.search(agent, template, sc);
			}
			while(res.length==0);
			return res[0].getName();
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deregister(Agent agent, String name, String type) {
		try {
			System.out.println("Deregistering name: "+name+", type: "+type);
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(agent.getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setName(name);
			sd.setType(type);
			dfd.addServices(sd);
			DFService.deregister(agent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
}
