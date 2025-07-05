//Oven Class
public class Oven extends Product{
	private String ovenType;
	private String timerType;
	private double capacity; 
	 
	
	public Oven(String name, double price , int quantity , int itemNum , boolean status,
	String ovenType ,  String timerType , double capacity){ 
		super(name, price, quantity, itemNum, status);
		this.ovenType = ovenType;
		this.timerType = timerType;
		this.capacity = capacity;
		
	}

	public String getOvenType(){
		return ovenType;
	}

	public void setOvenType(String ovenType){
		this.ovenType = ovenType;

	}

	public String getTimerType() {
      	return timerType;
  	}

  	public void setTimerType(String timerType) {
     		this.timerType = timerType;
 	}

  	public double getCapacity() {
      	return capacity;
  	}

  	public void setCapacity(double capacity) {
     		this.capacity = capacity;
 	}


	@Override
	public String toString() {
		return super.toString(this) + "\n" +
				String.format("%-32s: %s\n", "Oven type", ovenType) +
			    String.format("%-33s: %s\n", "Timer type", timerType) +
			    String.format("%-34s: %.1f L\n", "Capacity", capacity);

	}
}
