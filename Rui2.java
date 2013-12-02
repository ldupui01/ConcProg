import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Rui2{
	final static int LIMITE = 10;

	public void launcher(){
		int id = 0;
		ArrayTask at = new ArrayTask();
		int counter = 0;
		int[] iTime = {1000,12,1589,1356,147,1588,5000,1599,2,14};
		
		while(counter<10){
			if(id<LIMITE){
				System.out.println("time for new thread " + id + " :"+ iTime[id]);
				//String lifeTime = System.console().readLine();
				int time = iTime[id];
				//int time = Integer.parseInt(lifeTime);
				Task t = new Task(id, time, at);
				id++;
				Thread th = new Thread(t);
				th.start();
			}
			int z = at.length();
			synchronized(this){// in order to have the wait working we must have the lock on the object or in this case on THIS 
				if (z==0){
					if(id < LIMITE){
						try{
							Thread.sleep(2000);
						}catch(InterruptedException ex){
						}	
					}
					else{
						try{
							//Thread.wait(); // Doesn't work on a non static environment
							wait();// the wait function get stuck ! 
						}catch(InterruptedException ex){
						}	
					}
				}else{
					counter = counter + z;
					Integer[] i=at.toArray();
					String message=Arrays.toString(i);
					System.out.println("finished tasks : " + message);
					at.clear();
				}
			}
		}	
	}
	public static void main(String[] args){
		Rui2 rui = new Rui2();
		rui.launcher();
	}
}

class Task implements Runnable{
	private int idtask;
	private int time;
	private ArrayTask at;
	
	public Task(int id, int time, ArrayTask at){
		this.idtask = id;
		this.time = time;
		this.at = at;
	}
	
	public void run(){
	
		try{
			Thread.sleep(time);
		}catch(InterruptedException ex){
		}
	
		at.add(idtask);
	}
}

class ArrayTask{
	private  ArrayList<Integer> al = new ArrayList<Integer>();
	
	public synchronized void add(Integer e){// must have the lock before notify All
		al.add(e);
		notifyAll();
	}
	public synchronized int length(){
		return al.size();
	}
	
	public synchronized Integer[] toArray(){
		int size = al.size();
		Object[] objList = al.toArray();
		Integer[] intList = new Integer[size];
		for(int i=0;i<size;i++){
			if(al.get(i) instanceof Integer){
				intList[i] = (Integer)al.get(i);
			}
		}
		return intList;
	}
	
	public synchronized void clear(){
		al.clear();
	}
	
}