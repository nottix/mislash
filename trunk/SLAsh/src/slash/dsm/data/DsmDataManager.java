package slash.dsm.data;

import java.util.*;
import slash.dsm.tuple.*;

public class DsmDataManager {

	Hashtable<String, Hashtable<String, Queue<Object>>> tupleSpace;
	
	public DsmDataManager() {
		this.tupleSpace = new Hashtable<String, Hashtable<String, Queue<Object>>>();
	}
	
	public synchronized int out(String index, String type, Object value) {
		System.out.println("OUT: index "+index+", type "+type+", value "+value);
		//Se hashtable per index già esistente
		if(this.tupleSpace.containsKey(index)) {
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				queue.offer(value);
			}
			else {
				Hashtable<String, Queue<Object>> newType = new Hashtable<String, Queue<Object>>();
				Queue<Object> queue = new LinkedList<Object>();
				queue.offer(value);
				newType.put(type, queue);
			}
		} //Se hashtable per index non esistente
		else {
			Hashtable<String, Queue<Object>> newType = new Hashtable<String, Queue<Object>>();
			Queue<Object> queue = new LinkedList<Object>();
			queue.offer(value);
			newType.put(type, queue);
			
			this.tupleSpace.put(index, newType);
		}
		
		return 0;
	}
	
	public synchronized Tuple in(String index, String type) {
		System.out.println("IN: index "+index+", type "+type);
		if(this.tupleSpace.containsKey(index)) {
			System.out.println("in contanier: "+index);
			if(this.tupleSpace.get(index).containsKey(type)) {
				Queue<Object> queue = this.tupleSpace.get(index).get(type);
				Object obj = queue.poll();
				System.out.println("IN: "+obj);
				return new Tuple(Tuple.IN, index, type, obj);
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
