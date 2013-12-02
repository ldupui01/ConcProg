public class Task implements Runnable{
	private int t;
	
	public Task(int i) {
		this.t = i;
	}
	
	public int getIntTask() {
		return t;
	}
	
	public void sendThread(int time){
		Thread th = new Thread(this);
		th.start();
		try{
			th.sleep(time);
		}catch (InterruptedException ex){
			//just sleeping
		}
	}
	
	public void run() {
		System.out.println("Finished task : " + this.getIntTask());
		//return this.getIntTask(); //Can't return anything
	}
	
}