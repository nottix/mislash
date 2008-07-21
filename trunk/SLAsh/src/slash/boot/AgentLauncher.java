package slash.boot;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class AgentLauncher {

	private static int counter = 1;
	private static boolean scEnabled = false;
	
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
		
		if(!scEnabled) {
			launchAgent("sc", "slash.slachecker.agent.SLACheckerAgent", null, cc);
			scEnabled = true;
		}
		launchAgent("cm"+counter, "slash.contextmanager.agent.ContextManagerAgent", null, cc);
		
		launchAgent("cpu"+counter, "slash.resource.agent.CpuAgent", arg, cc);
		launchAgent("energy"+counter, "slash.resource.agent.EnergyAgent", arg, cc);
		launchAgent("memory"+counter, "slash.resource.agent.MemoryAgent", arg, cc);
		launchAgent("ram"+counter, "slash.resource.agent.RamAgent", arg, cc);
		
		launchAgent("latency"+counter, "slash.resource.agent.LatencyAgent", arg, cc);
		launchAgent("reliability"+counter, "slash.resource.agent.ReliabilityAgent", arg, cc);
		launchAgent("reqInterval"+counter, "slash.resource.agent.ReqIntervalAgent", arg, cc);
			
		launchAgent("rm"+counter, "slash.resourcemonitor.agent.ResourceMonitorAgent", new Object[]{type}, cc);
		
		counter++;
	}
	
	public static void main(String[] args) {
		
		launchNode("wired", 512, "publisher");
		launchNode("wireless", 256, "subscriber");
		launchNode("wireless", 256, "subscriber");
	}

}
