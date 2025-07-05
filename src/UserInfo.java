import java.util.Scanner;

public class UserInfo {
	
	private String name;
	private String userID;
	
    public UserInfo(String name) {
        this.name = name ;
        this.userID = generateUserID();
    }

    public void getNameFromUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your full name (first name and surname): ");
        this.name = input.nextLine().trim();
        generateUserID(); 
    }

  
    private String generateUserID() {
        if (this.name.contains(" ")) {
        	String[] nameParts = this.name.split(" ");
            String firstNameInitial = String.valueOf(nameParts[0].charAt(0));
            String surname = nameParts[nameParts.length - 1];
            return firstNameInitial + surname;
        } else {
            return "guest";
        }
    }


    public String getUserID() {
        return this.userID;
    }

 
    public String getName() {
        return this.name;
    }
}

