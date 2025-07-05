import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

//Add these imports at the top of your file:
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StockManagement extends Application {

	private static ArrayList<Product> products = new ArrayList<>();
	private UserInfo currentUser;
	private Stage window;
	private static TextArea outputTextArea = new TextArea();
	
	public static void main(String[] args) {
     launch(args);
     }

	@Override
public void start(Stage primaryStage) {
     window = primaryStage;
     window.setTitle("Stock Management System");
     WelcomeScene();
     }

private void WelcomeScene() {
     VBox layout = new VBox(15);
     layout.setStyle("-fx-background-color: #e0f7fa;");
     layout.setPadding(new Insets(20));
     layout.setAlignment(Pos.CENTER);

     Label welcomeLabel = new Label("Welcome To Stock Management System");
     welcomeLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px; -fx-font-weight: bold;");

     Label dateTimeLabel = new Label();
     LocalDateTime now = LocalDateTime.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     dateTimeLabel.setText("Current Date and Time: " + now.format(formatter));

     String[] members = {"Owi Wilson", "Tan Cheng Lock", "Ng Wei Jie", "Yee Hao Zhe"};
     Arrays.sort(members);
     StringBuilder sb = new StringBuilder("PROJECT BY GROUP 86\nGroup Members:\n");
     for (int i = 0; i < members.length; i++) {
         sb.append(i + 1).append(". ").append(members[i]).append("\n");
     }
     Label groupMembersLabel = new Label(sb.toString());
     groupMembersLabel.setStyle("-fx-font-family: 'Courier'; -fx-font-size: 14px;");
     groupMembersLabel.setWrapText(true);
     
     HBox hbGroup = new HBox(groupMembersLabel);
     hbGroup.setAlignment(Pos.CENTER_LEFT);
     
     Label nameInsert = new Label("Enter your full name:");
     nameInsert.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15px; -fx-font-weight: bold;");
     
     TextField nameField = new TextField();

     Button continueButton = new Button("Continue");
     
     continueButton.setOnAction(e -> {
         String name = nameField.getText().trim();
         if (!name.isEmpty()) {
             currentUser = new UserInfo(name);
             askAddProduct();
         } else {
             Alert alert = new Alert(Alert.AlertType.WARNING, "Name cannot be empty!");
             alert.showAndWait();
         }
         
     });

     layout.getChildren().addAll(welcomeLabel, dateTimeLabel, hbGroup, nameInsert, nameField, continueButton);
     Scene scene = new Scene(layout, 600, 450);
     scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
     scene.setFill(Color.LIGHTBLUE);
     window.setScene(scene);
     window.show();
 }
 
 
private void askAddProduct() {
	 
	 Alert confirmAdd = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add products?", ButtonType.YES, ButtonType.NO);
     confirmAdd.setTitle("Entry");
     confirmAdd.getDialogPane().setMinSize(400, 200);
     confirmAdd.getDialogPane().setStyle("-fx-font-size: 16px;");
     confirmAdd.setHeaderText(null);

     Optional<ButtonType> result = confirmAdd.showAndWait();
     if (result.isPresent() && result.get() == ButtonType.YES) {
         addProduct();
    	 showMainMenu(); // Go to main menu
     } else {
         // Ask user to type 0 to exit
         while (true) {
             TextInputDialog inputExit = new TextInputDialog();
             inputExit.setTitle("Exit Confirmation");
             inputExit.setHeaderText("You chose not to add products. Please type 0 to exit the program:");
             Optional<String> exitInput = inputExit.showAndWait();

             if (exitInput.isPresent() && exitInput.get().trim().equals("0")) {
                 Alert exitting = new Alert(Alert.AlertType.INFORMATION,
                         "Thank you. See You Again \n UserID: " + currentUser.getUserID() +
                         "\n Name: " + currentUser.getName());
                 exitting.getDialogPane().setMinSize(400, 200);
                 exitting.getDialogPane().setStyle("-fx-font-size: 16px;");
                 exitting.showAndWait();
                 window.close();
                 break;
             }}}}
 
private static void viewProducts() {
	    outputTextArea.clear();
	    if (products.isEmpty()) {
	        outputTextArea.appendText("No products available.\n");
	    } else {
	        int count = 1;
	        for (Product p : products) {
	            outputTextArea.appendText("════════ Product " + (count++) + " ════════\n");
	            outputTextArea.appendText("Type: " + getDetailedProductType(p) + "\n");
	            outputTextArea.appendText(p.toString() + "\n\n");
	        }
	    }
	}

private static String getDetailedProductType(Product product) {
	   if (product instanceof Refrigerator) {
	        return "Refrigerator (" + ((Refrigerator) product).getDoorDesign() + " door)";
	    } else if (product instanceof TV) {
	        return "TV (" + ((TV) product).getScreenType() + ", " + ((TV) product).getResolution() + ")";
	    } else if (product instanceof Oven) {
	        return "Oven (" + ((Oven) product).getOvenType() + ")";
	    } else if (product instanceof Fan) {
	        return "Fan (" + ((Fan) product).getFanType() + ")";
	    } else if (product instanceof CoffeeMachine) {
	        return "Coffee Machine (" + ((CoffeeMachine) product).getMachineType() + ")";
	    } else {
	        return "Generic Product";
	    }
	}
	

private static void addStockPhase() {
	
	if (products.isEmpty()) {
        outputTextArea.setText("No products to update.\n");
        return;
    }

    viewProducts();
    TextInputDialog indexDialog = new TextInputDialog();
    indexDialog.setHeaderText("Enter product number (e.g. 1, 2...):");
    Optional<String> indexInput = indexDialog.showAndWait();

    if (indexInput.isPresent()) {
        try {
            int index = Integer.parseInt(indexInput.get()) - 1;
            if (index < 0 || index >= products.size()) {
            	outputTextArea.clear();
                outputTextArea.appendText("Invalid product number.\n");
                return;
            }

            Product selected = products.get(index);
            if (!selected.getStatus()) {
            	outputTextArea.clear();
                outputTextArea.appendText("Cannot modify a discontinued product.\n");
                return;
            }

            TextInputDialog qtyDialog = new TextInputDialog();
            qtyDialog.setHeaderText("Enter quantity for restocking " );
            Optional<String> qtyInput = qtyDialog.showAndWait();
            if (qtyInput.isPresent()) {
                int qty = Integer.parseInt(qtyInput.get());
                if (qty <= 0) {
                	outputTextArea.clear();
                    outputTextArea.appendText("Quantity must be a positive number.\n");
                    return;
                }
                selected.addStock(qty);
                outputTextArea.clear();
                outputTextArea.appendText("Stock added successfully.\n");
            
            }}catch (NumberFormatException e) {
            	outputTextArea.clear();
                outputTextArea.appendText("Invalid input format.\n");}}}
 
private static void deductStockPhase() {
	 if (products.isEmpty()) {
		 outputTextArea.clear();
         outputTextArea.setText("No products to update.\n");
         return;
     }

     viewProducts();

     TextInputDialog indexDialog = new TextInputDialog();
     indexDialog.setHeaderText("Enter product number (e.g. 1, 2...):");
     Optional<String> indexInput = indexDialog.showAndWait();

     if (indexInput.isPresent()) {
         try {
             int index = Integer.parseInt(indexInput.get()) - 1;
             if (index < 0 || index >= products.size()) {
            	 outputTextArea.clear();
                 outputTextArea.appendText("Invalid product number.\n");
                 return;
             }

             Product selected = products.get(index);
             if (!selected.getStatus()) {
            	 outputTextArea.clear();
                 outputTextArea.appendText("Cannot modify a discontinued product.\n");
                 return;
             }

             TextInputDialog qtyDialog = new TextInputDialog();
             qtyDialog.setHeaderText("Enter quantity to deduct:");
             Optional<String> qtyInput = qtyDialog.showAndWait();

             if (qtyInput.isPresent()) {
                 int qty = Integer.parseInt(qtyInput.get());
                 if (qty <= 0) {
                	 outputTextArea.clear();
                     outputTextArea.appendText("Quantity must be a positive number.\n");
                     return;
                 }
                     if (selected.getQuantity() >= qty) {
                         selected.deductStock(qty);
                         outputTextArea.clear();
                         outputTextArea.appendText("Stock deducted successfully.\n");
                     } else {
                    	 outputTextArea.clear();
                         outputTextArea.appendText("Error: Not enough stock to deduct.\n");
                     }
                 }
         } catch (NumberFormatException e) {
        	 outputTextArea.clear();
             outputTextArea.appendText("Invalid input format.\n");}}}
 
private static void discontinueProduct() {
     TextInputDialog dialog = new TextInputDialog();	
     dialog.setHeaderText("Enter product number to discontinue:");
     dialog.showAndWait().ifPresent(indexStr -> {
         try {
             int index = Integer.parseInt(indexStr) - 1;
             if (index >= 0 && index < products.size()) {
                 products.get(index).setStatus(false);
                 outputTextArea.clear();
                 outputTextArea.appendText("Product discontinued.\n");
             } else {
            	 outputTextArea.clear();
                 outputTextArea.appendText("Invalid index.\n");
             }
         } catch (Exception e) {
        	 outputTextArea.clear();
             outputTextArea.appendText("Invalid input.\n");
         }
     });
 }

private void showMainMenu() {
	 VBox layout = new VBox(20);
	 layout.setPadding(new Insets(25));
	 layout.setAlignment(Pos.TOP_CENTER);

   outputTextArea.setEditable(false);
   outputTextArea.setPrefHeight(300);

   ButtonBar buttonBar = new ButtonBar();
   buttonBar.setPadding(new Insets(15,0,0,0));
   Button viewBtn = new Button("View Products");
   Button addStockBtn = new Button("Add Stock");
   Button deductStockBtn = new Button("Deduct Stock");
   Button discontinueBtn = new Button("Discontinue");
   Button addProductBtn = new Button("Add Product");
   Button exitBtn = new Button("Exit");

   viewBtn.setOnAction(e -> viewProducts());
   addStockBtn.setOnAction(e -> addStockPhase());
   deductStockBtn.setOnAction(e -> deductStockPhase());
   discontinueBtn.setOnAction(e -> discontinueProduct());
   addProductBtn.setOnAction(e -> addProduct());
   exitBtn.setOnAction(e -> {
       Alert ThankyouMsg = new Alert(Alert.AlertType.INFORMATION, "Thank you for using SMS, " + currentUser.getUserID());
       ThankyouMsg.getDialogPane().setMinSize(400, 200);
       ThankyouMsg.getDialogPane().setStyle("-fx-font-size: 16px;");
       ThankyouMsg.showAndWait();
       window.close();
   });

   buttonBar.getButtons().addAll(viewBtn, addStockBtn, deductStockBtn, discontinueBtn, addProductBtn, exitBtn);
   layout.getChildren().addAll(new Label("Product Operations:"), outputTextArea, buttonBar);
   
   TitledPane titledBorder = new TitledPane("Stock Management System", layout);
   titledBorder.setCollapsible(false);
   
   Scene scene = new Scene(layout, 700, 500);
   window.setScene(scene);
}



private void addProduct() {
     ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Refrigerator","Refrigerator", "TV","Oven","Fan","Coffee Machine");
     choiceDialog.setHeaderText("Select product type:");
     choiceDialog.getDialogPane().setMinSize(400, 200);
     choiceDialog.getDialogPane().setStyle("-fx-font-size: 16px;");
     choiceDialog.showAndWait().ifPresent(choice -> {
    	 switch (choice) {
         case "Refrigerator":
             addRefrigerator();
             break;
         case "TV":
             addTV();
             break;
         case "Oven":
             addOven();
             break;
         case "Fan":
             addFan();
             break;
         case "Coffee Machine":
             addCoffeeMachine();
             break;
     }
     });
 }

private void addRefrigerator() {
    
    Dialog<Refrigerator> dialog = new Dialog<>();
    dialog.setTitle("Add New Refrigerator");
    dialog.setHeaderText("Please enter refrigerator details:");

    // Set the button types
    ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField itemIdField = new TextField();
    itemIdField.setPromptText("Item ID");
    TextField nameField = new TextField();
    nameField.setPromptText("Name");
    
    ComboBox<String> doorTypeCombo = new ComboBox<>();
    doorTypeCombo.getItems().addAll("Top", "Bottom", "Side By Side", "French Door");
    doorTypeCombo.setValue("Top");
    
    ComboBox<String> colorCombo = new ComboBox<>();
    colorCombo.getItems().addAll("White", "Black", "Stainless Steel");
    colorCombo.setValue("White");
    
    TextField capacityField = new TextField();
    capacityField.setPromptText("Capacity in liters");
    TextField quantityField = new TextField();
    quantityField.setPromptText("Quantity");
    TextField priceField = new TextField();
    priceField.setPromptText("Price (RM)");

    grid.add(new Label("Item ID:"), 0, 0);
    grid.add(itemIdField, 1, 0);
    grid.add(new Label("Name:"), 0, 1);
    grid.add(nameField, 1, 1);
    grid.add(new Label("Door Type:"), 0, 2);
    grid.add(doorTypeCombo, 1, 2);
    grid.add(new Label("Color:"), 0, 3);
    grid.add(colorCombo, 1, 3);
    grid.add(new Label("Capacity (L):"), 0, 4);
    grid.add(capacityField, 1, 4);
    grid.add(new Label("Quantity:"), 0, 5);
    grid.add(quantityField, 1, 5);
    grid.add(new Label("Price (RM):"), 0, 6);
    grid.add(priceField, 1, 6);

    Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
    addButton.setDisable(true);

    ChangeListener<String> fieldListener = (observable, oldValue, newValue) -> {
        boolean allFieldsValid = !itemIdField.getText().trim().isEmpty()
                && !nameField.getText().trim().isEmpty()
                && !capacityField.getText().trim().isEmpty()
                && !quantityField.getText().trim().isEmpty()
                && !priceField.getText().trim().isEmpty();
        addButton.setDisable(!allFieldsValid);
    };

    itemIdField.textProperty().addListener(fieldListener);
    nameField.textProperty().addListener(fieldListener);
    capacityField.textProperty().addListener(fieldListener);
    quantityField.textProperty().addListener(fieldListener);
    priceField.textProperty().addListener(fieldListener);

    itemIdField.textProperty().addListener((obs, oldVal, newVal) -> {
        if (!newVal.matches("\\d*")) {
            itemIdField.setText(newVal.replaceAll("[^\\d]", ""));
        }
    });

    capacityField.textProperty().addListener((obs, oldVal, newVal) -> {
        if (!newVal.matches("\\d*\\.?\\d*")) {
            capacityField.setText(newVal.replaceAll("[^\\d.]", ""));
        }
    });

    quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
        if (!newVal.matches("\\d*")) {
            quantityField.setText(newVal.replaceAll("[^\\d]", ""));
        }
    });

    priceField.textProperty().addListener((obs, oldVal, newVal) -> {
        if (!newVal.matches("\\d*\\.?\\d*")) {
            priceField.setText(newVal.replaceAll("[^\\d.]", ""));
        }
    });

    dialog.getDialogPane().setContent(grid);

    Platform.runLater(() -> itemIdField.requestFocus());

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButtonType) {
            try {
                int itemID = Integer.parseInt(itemIdField.getText());
                String name = nameField.getText();
                String door = doorTypeCombo.getValue();
                String color = colorCombo.getValue();
                double capacity = Double.parseDouble(capacityField.getText());
                int qty = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                return new Refrigerator(name, price, qty, itemID, true, door, color, capacity);
            } catch (NumberFormatException e) {
                outputTextArea.appendText("Invalid number format in one of the fields.\n");
                return null;
            }
        }
        return null;
    });

    Optional<Refrigerator> result = dialog.showAndWait();

    result.ifPresent(refrigerator -> {
        products.add(refrigerator);
        outputTextArea.clear();
        outputTextArea.appendText("Refrigerator added successfully:\n" + refrigerator.toString() + "\n\n");
    });
}

private void addTV() {
    try {
        // Create and setup dialog
        Dialog<TV> dialog = new Dialog<>();
        dialog.setTitle("Add TV");
        dialog.setHeaderText("Enter TV details");
        
        // Setup buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        // Create form grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        // Form fields
        TextField itemIdField = new TextField();
        TextField nameField = new TextField();
        
        ComboBox<String> screenCombo = new ComboBox<>();
        screenCombo.getItems().addAll("LCD", "LED", "QLED", "OLED");
        screenCombo.setValue("LCD");
        
        ComboBox<String> resolutionCombo = new ComboBox<>();
        resolutionCombo.getItems().addAll("1080p", "2K", "4K", "8K");
        resolutionCombo.setValue("1080p");
        
        TextField displaySizeField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();
        
        // Add to grid
        grid.add(new Label("Item ID:"), 0, 0);
        grid.add(itemIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Screen Type:"), 0, 2);
        grid.add(screenCombo, 1, 2);
        grid.add(new Label("Resolution:"), 0, 3);
        grid.add(resolutionCombo, 1, 3);
        grid.add(new Label("Display Size (inches):"), 0, 4);
        grid.add(displaySizeField, 1, 4);
        grid.add(new Label("Quantity:"), 0, 5);
        grid.add(quantityField, 1, 5);
        grid.add(new Label("Price (RM):"), 0, 6);
        grid.add(priceField, 1, 6);
        
        // Enable/disable add button based on validation
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);
        
        // Validation listener
        ChangeListener<String> listener = (obs, oldVal, newVal) -> {
            addButton.setDisable(
                itemIdField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                displaySizeField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty()
            );
        };
        
        itemIdField.textProperty().addListener(listener);
        nameField.textProperty().addListener(listener);
        displaySizeField.textProperty().addListener(listener);
        quantityField.textProperty().addListener(listener);
        priceField.textProperty().addListener(listener);
        
        // Numeric validation
        itemIdField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                itemIdField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
        
        displaySizeField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                displaySizeField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });
        
        quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantityField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
        
        priceField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                priceField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });
        
        
        dialog.getDialogPane().setContent(grid);
        
        Platform.runLater(() -> itemIdField.requestFocus());
        
        dialog.setResultConverter(button -> {
            if (button == addButtonType) {
                try {
                    return new TV(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(quantityField.getText()),
                        Integer.parseInt(itemIdField.getText()),
                        true,
                        screenCombo.getValue(),
                        resolutionCombo.getValue(),
                        Double.parseDouble(displaySizeField.getText())
                    );
                } catch (NumberFormatException e) {
                    if (outputTextArea != null) {
                    	outputTextArea.clear();
                        outputTextArea.appendText("Invalid number format\n");
                    }
                    return null;
                }
            }
            return null;
        });
        
        
        Optional<TV> result = dialog.showAndWait();
        result.ifPresent(tv -> {
            products.add(tv);
            if (outputTextArea != null) {
            	outputTextArea.clear();
                outputTextArea.appendText("TV added successfully:\n" + tv.toString() + "\n\n");
            }
        });
        
    } catch (Exception e) {
        if (outputTextArea != null) {
            outputTextArea.appendText("Error adding TV: " + e.getMessage() + "\n");
        }
    }
}

private void addOven() {
    try {
        Dialog<Oven> dialog = new Dialog<>();
        dialog.setTitle("Add Oven");
        dialog.setHeaderText("Enter oven details");

        ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField itemIdField = new TextField();
        TextField nameField = new TextField();
        
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Convection ovens", "Microwave ovens", "Gas ovens", "Steam ovens", "Conventional ovens");
        typeCombo.setValue("Convection ovens");
        
        TextField timerField = new TextField();
        TextField capacityField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();

        grid.add(new Label("Item ID:"), 0, 0);
        grid.add(itemIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Oven Type:"), 0, 2);
        grid.add(typeCombo, 1, 2);
        grid.add(new Label("Timer Type:"), 0, 3);
        grid.add(timerField, 1, 3);
        grid.add(new Label("Capacity:"), 0, 4);
        grid.add(capacityField, 1, 4);
        grid.add(new Label("Quantity:"), 0, 5);
        grid.add(quantityField, 1, 5);
        grid.add(new Label("Price (RM):"), 0, 6);
        grid.add(priceField, 1, 6);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        ChangeListener<String> listener = (obs, oldVal, newVal) -> {
            addButton.setDisable(
                itemIdField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                timerField.getText().trim().isEmpty() ||
                capacityField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty()
            );
        };

        itemIdField.textProperty().addListener(listener);
        nameField.textProperty().addListener(listener);
        timerField.textProperty().addListener(listener);
        capacityField.textProperty().addListener(listener);
        quantityField.textProperty().addListener(listener);
        priceField.textProperty().addListener(listener);

    
        itemIdField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                itemIdField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        capacityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                capacityField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });

        quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantityField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        priceField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                priceField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> itemIdField.requestFocus());

        dialog.setResultConverter(button -> {
            if (button == addButtonType) {
                try {
                    return new Oven(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(itemIdField.getText()),
                        Integer.parseInt(quantityField.getText()),
                        true,
                        typeCombo.getValue(),
                        timerField.getText(),
                        Double.parseDouble(capacityField.getText())
                    );
                } catch (NumberFormatException e) {
                	outputTextArea.clear();
                    outputTextArea.appendText("Invalid number format\n");
                    return null;
                }
            }
            return null;
        });

        Optional<Oven> result = dialog.showAndWait();
        result.ifPresent(oven -> {
            products.add(oven);
            outputTextArea.clear();
            outputTextArea.appendText("Oven added successfully:\n" + oven.toString() + "\n\n");
        });

    } catch (Exception e) {
    	outputTextArea.clear();
        outputTextArea.appendText("Error adding oven: " + e.getMessage() + "\n");
    }
}

private void addFan() {
    try {
        Dialog<Fan> dialog = new Dialog<>();
        dialog.setTitle("Add Fan");
        dialog.setHeaderText("Enter fan details");

        ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField itemIdField = new TextField();
        TextField nameField = new TextField();
        
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Ceiling Fan", "Table Fan", "Pedestal Fan", "Tower Fan", "Wall Fan");
        typeCombo.setValue("Ceiling Fan");
        
        TextField bladesField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();

        grid.add(new Label("Item ID:"), 0, 0);
        grid.add(itemIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Fan Type:"), 0, 2);
        grid.add(typeCombo, 1, 2);
        grid.add(new Label("Number of Blades:"), 0, 3);
        grid.add(bladesField, 1, 3);
        grid.add(new Label("Quantity:"), 0, 4);
        grid.add(quantityField, 1, 4);
        grid.add(new Label("Price (RM):"), 0, 5);
        grid.add(priceField, 1, 5);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        ChangeListener<String> listener = (obs, oldVal, newVal) -> {
            addButton.setDisable(
                itemIdField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                bladesField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty()
            );
        };

        itemIdField.textProperty().addListener(listener);
        nameField.textProperty().addListener(listener);
        bladesField.textProperty().addListener(listener);
        quantityField.textProperty().addListener(listener);
        priceField.textProperty().addListener(listener);

   
        itemIdField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                itemIdField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        bladesField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                bladesField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantityField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        priceField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                priceField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> itemIdField.requestFocus());

        dialog.setResultConverter(button -> {
            if (button == addButtonType) {
                try {
                    return new Fan(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(quantityField.getText()),
                        Integer.parseInt(itemIdField.getText()),
                        true,
                        typeCombo.getValue(),
                        Integer.parseInt(bladesField.getText())
                    );
                } catch (NumberFormatException e) {
                	outputTextArea.clear();
                    outputTextArea.appendText("Invalid number format\n");
                    return null;
                }
            }
            return null;
        });

        Optional<Fan> result = dialog.showAndWait();
        result.ifPresent(fan -> {
            products.add(fan);
            outputTextArea.clear();
            outputTextArea.appendText("Fan added successfully:\n" + fan.toString() + "\n\n");
        });

    } catch (Exception e) {
    	outputTextArea.clear();
        outputTextArea.appendText("Error adding fan: " + e.getMessage() + "\n");
    }
}

private void addCoffeeMachine() {
    try {
        Dialog<CoffeeMachine> dialog = new Dialog<>();
        dialog.setTitle("Add Coffee Machine");
        dialog.setHeaderText("Enter coffee machine details");

        ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField itemIdField = new TextField();
        TextField nameField = new TextField();
        
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Espresso", "Drip", "Pod", "French Press", "Percolator");
        typeCombo.setValue("Espresso");
        
        TextField capacityField = new TextField();
        CheckBox grinderCheckbox = new CheckBox("Has Grinder");
        TextField quantityField = new TextField();
        TextField priceField = new TextField();

        grid.add(new Label("Item ID:"), 0, 0);
        grid.add(itemIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Machine Type:"), 0, 2);
        grid.add(typeCombo, 1, 2);
        grid.add(new Label("Water Tank Capacity (L):"), 0, 3);
        grid.add(capacityField, 1, 3);
        grid.add(new Label("Features:"), 0, 4);
        grid.add(grinderCheckbox, 1, 4);
        grid.add(new Label("Quantity:"), 0, 5);
        grid.add(quantityField, 1, 5);
        grid.add(new Label("Price (RM):"), 0, 6);
        grid.add(priceField, 1, 6);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        ChangeListener<String> listener = (obs, oldVal, newVal) -> {
            addButton.setDisable(
                itemIdField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                capacityField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty()
            );
        };

        itemIdField.textProperty().addListener(listener);
        nameField.textProperty().addListener(listener);
        capacityField.textProperty().addListener(listener);
        quantityField.textProperty().addListener(listener);
        priceField.textProperty().addListener(listener);

        // Numeric validation
        itemIdField.textProperty().addListener((obs, oldVal, newVal) -> {
        	if (!newVal.matches("\\d*")) {
                itemIdField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        capacityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                capacityField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });

        quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantityField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        priceField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) {
                priceField.setText(newVal.replaceAll("[^\\d.]", ""));
            }
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> itemIdField.requestFocus());

        dialog.setResultConverter(button -> {
            if (button == addButtonType) {
                try {
                    return new CoffeeMachine(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(quantityField.getText()),
                        Integer.parseInt(itemIdField.getText()),
                        true,
                        typeCombo.getValue(),
                        Double.parseDouble(capacityField.getText()),
                        grinderCheckbox.isSelected()
                    );
                } catch (NumberFormatException e) {
                	outputTextArea.clear();
                    outputTextArea.appendText("Invalid number format\n");
                    return null;
                }
            }
            return null;
        });

        Optional<CoffeeMachine> result = dialog.showAndWait();
        result.ifPresent(coffeeMachine -> {
            products.add(coffeeMachine);
            outputTextArea.clear();
            outputTextArea.appendText("Coffee machine added successfully:\n" + coffeeMachine.toString() + "\n\n");
        });

    } catch (Exception e) {
    	outputTextArea.clear();
        outputTextArea.appendText("Error adding coffee machine: " + e.getMessage() + "\n");
    }
}

}

