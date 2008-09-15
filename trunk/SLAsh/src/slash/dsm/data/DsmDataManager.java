package slash.dsm.data;

import java.util.*;
import slash.dsm.tuple.*;
import slash.entity.*;
import java.math.*;

public class DsmDataManager {

	Hashtable<String, Hashtable<String, Queue<Object>>> tupleSpace;
	
	public DsmDataManager() {
		this.tupleSpace = new Hashtable<String, Hashtable<String, Queue<Object>>>();
	}
	
	public synchronized int out(String index, String type, Object value) {
		if(type.equals("context"))
			System.out.println("Context -> cpu: "+((Context)value).getCpu());
		//System.out.println("OUT: index "+index+", type "+type+", value "+value);
		//Se hashtable per index già esistente
		
		if(index==null)
			System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		else if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				queue.offer(value);
				//System.out.println("OUT -> type: "+type);
			}
			else {
				Hashtable<String, Queue<Object>> oldType = this.tupleSpace.get(index);
				Queue<Object> queue = new LinkedList<Object>();
				queue.offer(value);
				oldType.put(type, queue);
//				if(type.equals("slacontract"))
//					System.out.println("CONTRATTO AGGIUNTO");
				//System.out.println("OUT -> type: "+type);
			}
		} //Se hashtable per index non esistente
		else {
			Hashtable<String, Queue<Object>> newType = new Hashtable<String, Queue<Object>>();
			Queue<Object> queue = new LinkedList<Object>();
			queue.offer(value);
			newType.put(type, queue);
			
			this.tupleSpace.put(index, newType);
			//System.out.println("OUT -> type: "+type);
		}
		
		return 0;
	}
	
	public synchronized Tuple in(String index, String type) {
//		if(type.equals("slacontract"))
//			System.out.println("CONTRATTO Ricerca");
		if(index==null) {
//			if(type.equals("slacontract"))
//				System.out.println("CONTRATTO Errore");
			Enumeration<Hashtable<String, Queue<Object>>> enumeration = this.tupleSpace.elements();
			Enumeration<String> keys = this.tupleSpace.keys();
			while(enumeration.hasMoreElements() && keys.hasMoreElements()) {
				String key = keys.nextElement();
				//System.out.println("TYPE: "+key);
				Hashtable<String, Queue<Object>> typeTable = enumeration.nextElement();
				//if((Math.random()%2)==1) {
				//	System.out.println("continue");
				//	continue;
				//}
				Queue<Object> queue = typeTable.get(type);
				//System.out.println("IN -> type: "+type);
				if(queue!=null) {
					//System.out.println("QUEUE OK");
					Object obj = queue.poll();
					return new Tuple(Tuple.IN, type, key, obj);
				}
			}
		}
		else if(this.tupleSpace.containsKey(index)) {
			if(type.equals("slacontract"))
				System.out.println("CONTRATTO Trovato? in "+index);
			//System.out.println("in contanier: "+index);
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				Object obj = queue.poll();
				//System.out.println("IN: "+obj);
//				if(type.equals("slacontract"))
//					System.out.println("CONTRATTO TROVATO");
				return new Tuple(Tuple.IN, type, index, obj);
			}
		}
		
		return null;
	}
	
	public synchronized Object read(String index, String type) {
		if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Object obj = this.tupleSpace.get(index).get(type);
				return obj;
			}
		}
		
		return null;
	}
}
