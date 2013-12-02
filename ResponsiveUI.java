public class ResponsiveUI { //implements Runnable {

	
	public ResponsiveUI(){

	}
	
	public void sendThread(int i, int time){
		Task t = new Task(i);
		t.sendThread(time);
		//System.out.println("Finished task : " + i);
	}
	
	public static void main(String args[]) {
		ResponsiveUI rui = new ResponsiveUI();
		int[] iTime = {1000,12,1589,1356,147,1588,2,1599,10000,14};
		for (int i = 0; i < 10; i++) {
			System.out.println("Enter the duration (in ms) of task " + i + " : " + iTime[i]);
			rui.sendThread(i, iTime[i]);
		}
	}

}