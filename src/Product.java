public abstract class Product {

	private String name;
	private double price;
	private int quantity;
	private int itemNum;
	private boolean status;
	
	
	Product(String name, double price, int quantity, int itemNum, boolean status){
		
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.itemNum = itemNum;
		this.status = true;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getItemNum() {
		return itemNum;
	}
	
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	
	 public void discontinueProduct() {
         this.setStatus(false);
     }
	
	public double calcInventoryVal(Product p) {
		return p.price* p.quantity;
	}
	
	public void addStock(int qty) {
	    
        if (getStatus()) {
            this.quantity += qty;

        } else {
            System.out.println("Cannot add stock to a discontinued product.");
        }
    }
	
	public void deductStock(int qty) {
	    
        if (qty <= this.quantity) {
            this.quantity -= qty;

        } else {
            System.out.println("Insufficient stock.");
        }

    }
	
	public String toString(Product P){
		 
		 return String.format("%-30s: %s\n", "Item number", this.itemNum) +
				 String.format("%-29s: %s\n", "Product name", this.name) +
				 String.format("%-29s: %d\n", "Quantity available", this.quantity) +
				 String.format("%-33s: %.2f\n", "Price (RM)", this.price) +
				 String.format("%-26s: %.2f\n", "Inventory value (RM)", calcInventoryVal(P)) +
				 String.format("%-30s: %s", "Product status", getStatus() ? "Active" : "Discontinued");
    }

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
