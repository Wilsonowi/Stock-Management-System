//Coffee Machine
public class CoffeeMachine extends Product{
	private String machineType;  
	private double waterTankCapacity;  
	private boolean hasGrinder; 
	
	public CoffeeMachine(String name, double price , int quantity , int itemNum , boolean status,
	String machineType , double waterTankCapacity , boolean hasGrinder){ 
		super(name, price, quantity, itemNum, status);
		this.machineType = machineType;
		this.waterTankCapacity = waterTankCapacity;
		this.hasGrinder = hasGrinder;

	}

	public String getMachineType(){
		return machineType;
	}

	public void setMachineType(String machineType){
		this.machineType = machineType;

	}

	public double getWaterTankCapacity() {
      	return waterTankCapacity;
  	}

  	public void setWaterTankCapacity(double waterTankCapacity) {
     		this.waterTankCapacity = waterTankCapacity;
 	}

  	public boolean isHasGrinder() {
      	return hasGrinder;
  	}

  	public void setHasGrinder(boolean hasGrinder) {
      	this.hasGrinder = hasGrinder;
 	}

	@Override
	public String toString() {
		
		String s;
		if(hasGrinder) s = ("Yes");
		else s = ("No");
		
		return super.toString(this) + "\n" +
		"Machine type		  	: " + machineType + "\n" +
		"Water tank capacity (in mL) 	: " + waterTankCapacity + " mL\n" +
		"Has grinder 		  	: " + s + "\n";

	}
}







