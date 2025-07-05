public class Fan extends Product{
	private String fanType;
	private int numberOfBlades;
	
	public Fan(String name, double price , int quantity , int itemNum , boolean status,
	String fanType , int numberOfBlades){ 
		super(name, price, quantity, itemNum, status);
		this.fanType = fanType;
		this.numberOfBlades = numberOfBlades;

	}

	public String getFanType(){
		return fanType;
	}

	public void setFanType(String fanType){
		this.fanType = fanType;
	}

	public int getNumberOfBlades() {
      	return numberOfBlades;
  	}

  	public void setNumberOfBlades(int numberOfBlades) {
     		this.numberOfBlades = numberOfBlades;
 	}

	@Override
	public String toString() {
		
		return super.toString(this) + "\n" +
				  String.format("%-34s: %s\n", "Fan type", fanType) +
				  String.format("%-27s: %d\n", "Number of blades", numberOfBlades);

	}
}