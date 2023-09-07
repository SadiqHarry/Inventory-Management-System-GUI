# Inventory Management System GUI

This project is a Graphical User Interface (GUI) for an Inventory Management System. It provides a user-friendly interface for managing inventory additions, deletions, and other data. The system was designed to meet the specific business requirements of a small manufacturing organization that has outgrown its current inventory system.

![Screenshot from 2023-04-28 12-50-10](https://user-images.githubusercontent.com/116308353/235231832-7d2d3fcc-6162-4a79-80a0-64abe0248f5e.png)

## Technologies Leveraged
- Java/JavaFX
- Scenebuilder
- Intellij 

## Features

The Inventory Management System GUI provides the following functionalities:

1. **Main Screen**: The main screen displays the application title and provides buttons for adding, modifying, deleting, and searching parts and products. It also includes lists for displaying parts and products and text boxes for searching.

2. **Add Part Screen**: This screen allows users to add a new part to the inventory. Users can choose between "In-House" and "Outsourced" parts, enter various details such as ID, name, inventory level, price, max and min values, and company name or machine ID. It includes buttons to save the part or cancel the operation.

3. **Modify Part Screen**: The modify part screen allows users to edit existing parts. It pre-populates the fields with the saved data and allows users to make changes to the part's details. Users can save the modifications or cancel the operation.

4. **Add Product Screen**: Users can add a new product to the inventory using this screen. They can enter details such as ID, name, inventory level, price, max and min values, and associate one or more parts with the product. The screen includes buttons to save the product, add parts, delete parts, and cancel the operation.

5. **Modify Product Screen**: This screen enables users to modify existing products. It displays the product's current details and associated parts. Users can make changes to the product's information, associate or disassociate parts, and save the modifications or cancel the operation.

6. **Functionality on Main Screen**: The main screen includes additional functionality to redirect users to the various screens, delete selected parts or products, search for parts or products, and exit the application.

7. **Exception Controls**: The application implements exception controls to handle errors and display custom error messages. These controls ensure data integrity and validate user inputs. For example, the system prevents entering inventory values that exceed the minimum or maximum values for a part or product.




## Screenshots

Here are some screenshots of the Inventory Management System GUI:

### Modify Part Screen
![Screenshot from 2023-04-28 12-50-21](https://user-images.githubusercontent.com/116308353/235231826-683783be-e111-4a06-a94c-b4ceb0f1f811.png)

### Modify Product Screen
![Screenshot from 2023-04-28 12-50-34](https://user-images.githubusercontent.com/116308353/235231814-3b875af3-c0f0-4c01-b8f5-fd739b524403.png)





## Setup Guide

To run this project locally, please follow the steps below:

1. Clone this repository to your local machine.

2. Open the project in IntelliJ IDE (version 17.0.6 or later).

3. Ensure that Java JDK 19.0.2 is installed on your machine.

4. Install the JavaFX framework libraries if you haven't already.

5. Install Scene Builder 19.0.0 to display FXML files in the project.

6. Build and run the project from the IDE.




## How to Contribute

If you would like to contribute to this project, please follow these guidelines:

1. Fork the repository on GitHub.

2. Make your desired changes in your local fork.

3. Test your changes thoroughly.

4. Submit a pull request to the original repository, explaining the changes you made.

5. Ensure that your code follows the project's coding standards and practices.




## License

This project is licensed under the [MIT License](LICENSE).

## Contact

If you have any questions or suggestions regarding this project, please feel free to contact me at [sadiqharry@gmail.com](mailto:sadiqharry@gmail.com).

Thank you for your interest in this Inventory Management System GUI! I hope you find it helpful and informative.


