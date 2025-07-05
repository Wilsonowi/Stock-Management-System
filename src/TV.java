public class TV extends Product {
    private String screenType;
    private String resolution;
    private double displaySize;
    
    public TV(String name, double price, int quantity, int itemNum, boolean status,
              String screenType, String resolution, double displaySize) {
        super(name, price, quantity, itemNum, status);
        this.screenType = screenType;
        this.resolution = resolution;
        this.displaySize = displaySize;
    }

    public String getScreenType() { 
        return screenType; 
    }

    public void setScreenType(String screenType) { 
        this.screenType = screenType; 
    }

    public String getResolution() { 
        return resolution; 
    }

    public void setResolution(String resolution) { 
        this.resolution = resolution; 
    }

    public double getDisplaySize() { 
        return displaySize; 
    }
    
    public void setDisplaySize(double displaySize) { 
        this.displaySize = displaySize; 
    }
    
    @Override
    public String toString() {
        return super.toString(this) + "\n" + 
               "Screen type       : " + screenType + "\n" +
               "Resolution        : " + resolution + "\n" +
               "Display size      : " + displaySize +"\n";
    }
}
