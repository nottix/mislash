package slash.boot;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class AgentLauncher {

	private static int counter = 1;
	private static boolean scEnabled = false;
	private static boolean dsmEnabled = false;
	
	public static void launchAgent(String name, String path, Object[] args, ContainerController cc) {
		try {
			AgentController agent = cc.createNewAgent(name, path, args);
			agent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
	
	public static ContainerController createContainer() {
		Runtime rt = Runtime.instance();
		Profile p = new ProfileImpl();
		ContainerController cc = rt.createAgentContainer(p);
		return cc;
		
	}
	
	public static void launchNode(String network, int bandwidth, String type) {
		
		ContainerController cc = createContainer();
		
		Object[] arg = new Object[2];
		arg[0] = network;
		arg[1] = Integer.valueOf(bandwidth);
		
		if(!dsmEnabled) {
			launchAgent("dsm", "slash.dsm.agent.DsmServerAgent", null, cc);
			dsmEnabled = true;
		}
		
		if(!scEnabled) {
			launchAgent("sc", "slash.slachecker.agent.SLACheckerAgent", null, cc);
			scEnabled = true;
		}
		launchAgent("cm"+counter, "slash.contextmanager.agent.ContextManagerAgent", new Object[]{type}, cc);
		
		launchAgent("cpu"+counter, "slash.resource.agent.CpuAgent", arg, cc);
		launchAgent("energy"+counter, "slash.resource.agent.EnergyAgent", arg, cc);
		launchAgent("memory"+counter, "slash.resource.agent.MemoryAgent", arg, cc);
		launchAgent("ram"+counter, "slash.resource.agent.RamAgent", arg, cc);
		
		launchAgent("latency"+counter, "slash.resource.agent.LatencyAgent", arg, cc);
		launchAgent("reliability"+counter, "slash.resource.agent.ReliabilityAgent", arg, cc);
		launchAgent("reqInterval"+counter, "slash.resource.agent.ReqIntervalAgent", arg, cc);
		
		launchAgent("rm-cpu"+counter, "slash.resourcemonitor.agent.CpuRmAgent", arg, cc);
		launchAgent("rm-energy"+counter, "slash.resourcemonitor.agent.EnergyRmAgent", arg, cc);
		launchAgent("rm-memory"+counter, "slash.resourcemonitor.agent.MemoryRmAgent", arg, cc);
		launchAgent("rm-ram"+counter, "slash.resourcemonitor.agent.RamRmAgent", arg, cc);
		
		launchAgent("rm-latency"+counter, "slash.resourcemonitor.agent.LatencyRmAgent", arg, cc);
		launchAgent("rm-reliability"+counter, "slash.resourcemonitor.agent.ReliabilityRmAgent", arg, cc);
		launchAgent("rm-reqInterval"+counter, "slash.resourcemonitor.agent.ReqIntervalRmAgent", arg, cc);
		
		counter++;
	}
	
	public static void main(String[] args) {
		
		launchNode("wired", 512, "publisher");
		launchNode("wireless", 256, "subscriber");
		launchNode("wireless", 256, "subscriber");
	}

}
