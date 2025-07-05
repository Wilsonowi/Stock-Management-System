public class Refrigerator extends Product{ 
	  private String doorDesign;
	  private String color;
	  private double capacity;
	  
	  public Refrigerator(String name,double price, int quantity,int itemNumber,  boolean status,
	          String doorDesign , String color , double capacity){ 
	    super(name, price, quantity, itemNumber, status);
	    this.doorDesign = doorDesign;
	    this.color = color;
	    this.capacity = capacity;

	  }

	  public String getDoorDesign(){
	    return doorDesign;
	  
	  }
	  
	  public void setDoorDesign(String doorDesign) {
	    this.doorDesign = doorDesign;
	  }

	  public String getColor() {
	    return color;
	  }

	  public void setColor(String color) {
	    this.color = color;
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
	    	String.format("%-31s: %s\n", "Door design", doorDesign) +
	    	String.format("%-36s: %s\n", "Color", color) +
	    	String.format("%-34s: %.1f L\n", "Capacity", capacity);

	  }
	  
	}

