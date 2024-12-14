# *City Simulator Game*

# *I. Project Overview*
The City Simulator Game is a console-based simulation where players manage a virtual city by balancing its key attributes: healthcare, education, innovation, environment, fund, and morale. Players make decisions based on real-world scenarios that impact these attributes, with the aim of maintaining an ideal balance and ensuring the city's sustainability. The game features three difficulty levels and incorporates core Object-Oriented Programming (OOP) principles.

# *II. Explanation of OOP Principles Applied*
The project leverages Object-Oriented Programming principles as follows

Encapsulation: Each city attribute (healthcare, education, innovation, environment, fund, morale) is encapsulated with private fields and public getter and setter methods to control access.

Inheritance: The game has a base class, City, that contains the general attributes of the city, and specialized classes, such as CityUI, inherit from it, extending functionality.

Polymorphism: The game utilizes polymorphism by allowing different scenarios that impact the city’s attributes to be handled through a common interface.

Abstraction: Complex details of scenario handling are abstracted into methods that players interact with, without needing to understand the underlying processes. The city management system hides the complexity of calculations behind simplified user interfaces.

# *III. Details of the Chosen SDG and Its Integration into the Project*
The game aligns with UN Sustainable Development Goal (SDG) 11: Sustainable Cities and Communities. This SDG focuses on making cities inclusive, safe, resilient, and sustainable. The game integrates this by simulating decision-making that impacts the sustainability of the city. Players must balance various resources and make ethical choices to improve the city’s attributes, contributing to long-term prosperity and quality of life for its inhabitants.

# *IV. Instructions for Running the Program*

*Prerequisites:*
*Java 8 or higher*
IDE (e.g., Visual Studio Code) for development or the terminal for command-line usage

*Steps to run the program:*
1. Clone or download the repository to your local machine.

2. Navigate to the project directory using the terminal or open the project in your IDE.

3. Run the db/innit.sql script in your database client (e.g., MySQL Workbench, pgAdmin, or SQLite CLI) to create the database and its tables.

4. Navigate to JDBC/DatabaseConnection.java file to change the corresponding variables:

    USER = change to your DB username

    PASSWORD = change to your DB password.

5. Run the main class.

6. Follow the on-screen prompts to interact with the game.