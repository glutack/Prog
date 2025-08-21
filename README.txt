Projects done for my Programming subject

### [Proyecto 1 - Introducción a la programación] ()
**Ejercicio01:**Performs three arithmetic calculations with user-provided numbers and prints the results.
**Ejercicio02:**Demonstrates variable and constant declarations, type casting (implicit and explicit), and basic arithmetic and assignment operations in Java.
**Ejercicio03:** Validates a password by checking its length, whether it starts with a vowel, ends with a consonant, and contains at least one special character.

### [Proyecto 2 - Uso de estructuras de control de flujo] ()
Calculates employee salaries based on years of service, provides annual and monthly salary calculations for new employees, and interacts with the user through a menu-driven console interface.

### [Proyecto 3 - Utilizando cadenas de caracteres y arrays] ()
Creates instances of classes Aeronave and Aeropuerto and simulates aircraft operations, including takeoffs, landings, and status updates at different airports.

### [Proyecto 4 - Utilizando cadenas de caracteres y arrays] ()
**Ejercicio01:** Compresses a string by replacing consecutive repeated letters with the letter followed by its count (e.g., aaabb → a3b2) and prints the compressed version if shorter than the original.
**Ejercicio02:** Reads a square matrix from user input, validates its elements, rotates it 90 degrees to the right, and prints both the original and rotated matrix.
<details><summary>Tech used:</summary>
```
**Scanner** (java.util.Scanner) → Class. Reads user input from the console.
**Arrays** → Core feature. Used to represent 2D matrices (String[][] matriz).
**Regular Expressions** (regex) → Validates that input only contains numbers/letters.
**String methods:**
	**trim()** → Removes spaces around values.
	**split(",")** → Splits the user’s input into array elements.
	**matches(regex)** → Validates each input element.
**Control structures** → for, while, if used for looping and conditions.
```
</details>

### [Proyecto 4 - Utilizando cadenas de caracteres y arrays] ()
**Ejercicio01:** Compresses a string by replacing consecutive repeated letters with the letter followed by its count (e.g., aaabb → a3b2) and prints the compressed version if shorter than the original.
**Ejercicio02:** Reads a square matrix from user input, validates its elements, rotates it 90 degrees to the right, and prints both the original and rotated matrix.
<details><summary>Tech used:</summary>
```
**Scanner** (java.util.Scanner) → Class. Reads user input from the console.
**Arrays** → Core feature. Used to represent 2D matrices (String[][] matriz).
**Regular Expressions** (regex) → Validates that input only contains numbers/letters.
**String methods:**
	**trim()** → Removes spaces around values.
	**split(",")** → Splits the user’s input into array elements.
	**matches(regex)** → Validates each input element.
**Control structures** → for, while, if used for looping and conditions.
```
</details>

### [Proyecto 5 - Desarrollo de clases] ()
Object modeling with attributes and methods, encapsulation, data validation through exceptions, and the use of Javadoc for proper documentation.

### [Proyecto 6 - Advanced use of classes] ()
Implementation of a class hierarchy based on boat types.
<details><summary>Java's core OOP:</summary>
```
**Class** → Blueprint. Defines the structure and behavior (fields and methods) of objects. It acts as a template from which objects (instances) are created.
	**Abstract Class** → Partial blueprint. A class that cannot be instantiated directly and may contain abstract methods (without body) as well as regular methods. It is used when classes share common behavior but still need specialization.
**Object** → Instance. A concrete entity created from a class, holding its own state (fields/attributes) and capable of invoking its methods.
**Constructor** → Special method. A block of code that initializes a new object when it is created. It has the same name as the class and does not have a return type.
	**Default constructor** → Provided by the compiler if no constructor is defined.
	**Parameterized constructor** → Allows setting initial values when creating an object.
**Method** → Function inside a class. Defines a behavior that an object can perform. Can return a value or be void.
	**Instance methods** → Operate on the object’s state (fields).
	**Static methods** → Belong to the class itself, can be called without creating an object.
	**Overriding → Redefining a method in a subclass with the same signature.**
**Attribute** → Variable inside a class. Represents the state or properties of an object.
**Interface** → Contract. A collection of abstract methods (and constants) that a class can implement. It defines what a class must do, but not how. A class can implement multiple interfaces.
-------
**Inheritance** → Allows a class (subclass) to inherit fields and methods from another class (superclass). Promotes code reuse and hierarchy.
**Polymorphism** → The ability of different classes to be treated as instances of the same type, usually via method overriding or interface implementation.
**Encapsulation** → Hiding the internal details of a class (using private fields) and providing controlled access through getters and setters
```
</details>


### [Proyecto 7 - Manejos de estructuras de datos internas] ()
Simulates a very basic academic management system.
<details><summary>Tech used:</summary>
```
**Java Collections Framework** → Package. A set of interfaces and classes for storing and manipulating groups of objects such as lists, sets, and maps.
List → Interface. Represents an ordered collection that can contain duplicate elements.
ArrayList → Class. A List implementation backed by a dynamic array, allowing fast random access by index.
Set → Interface. A collection that does not allow duplicate elements.
HashSet → Class. A Set implementation based on a hash table; provides fast lookups without guaranteeing order.
Map → Interface. A collection of key-value pairs where each key is unique.
HashMap → Class. A Map implementation that uses hash tables for quick access to values by their keys.
TreeMap → Class. A Map implementation that keeps its keys sorted according to their natural ordering or a custom comparator.
**Java IO** → Package. Provides for system input and output through data streams, serialization and the file system.
	**File** → Class. Contains several methods for working with the pathname, deleting and renaming files, creating new directories...
	**BufferedReader** / **BufferedWriter** → Class. Reads and writes text efficiently in files, using buffers to improve performance.
	**Serializable** → Interfaz. Allows to turn objects into a byte sequence for storage in binary files and later retrieval.
**XStream** → Library. Allows to serialize objects to XML format and vice versa.

```
</details>

### [Proyecto 8 - Estructuras de datos externas (ficheros)] ()
Reads/writes a book library in a text file.
<details><summary>Tech used:</summary>
```
[**Java IO**, **File**, **BufferedReader** / **BufferedWriter**, **Serializable**](#Proyecto 8 - Estructuras de datos externas (ficheros))
**XStream** → Library. Allows to serialize objects to XML format and vice versa.
```
</details>

### [Proyecto 9 - Interfaces gráficas de usuario] ()
Creates a calculator application with a graphical user interface.
<details><summary>Tech used:</summary>
```
**Swing** → Java library for building GUI. It provides components like windows, buttons, and text fields, allowing to create interactive desktop applications.
**JavaFX** → client application platform for desktop, mobile and embedded systems built on Java.
    **JavaFX FXML** → XML format that enables you to compose JavaFX GUIs in a fashion similar to how you compose web GUIs in HTML.
```
</details>

### [Proyecto 10 - Gestión de bases de datos relacionales y orientadas a objetos] (https://github.com/glutack/Prog/tree/master/10.%20Gesti%C3%B3n%20de%20bases%20de%20datos%20relacionales%20y%20orientadas%20a%20objetos)
Manages a supermarket using a H2 database.
<details><summary>Tech used:</summary>
```
**JDBC** (Java Database Connectivity) → API Java. It allows Java apps to connect and communicate to relational databases. It provides a setof classes and interfaces that allow you to execute sQL statements, manipulate data and manage information in different databases from your Java applications.
**JPA** (Java Persistance API)→ API Java. It dacilitates the mangement of data persistence and object-relational mapping in Java applications. It allows you to work with JAva objects as if they were persitent data, without having to write SWQL code directly to interact with relational databases.
**H2** → Relational database management system written in Java. It can be used as an embedded database in Java applications or run in client-server mode. It is open source and offers high speed and a small file size, making it suitable for development and testing.
```
</details>