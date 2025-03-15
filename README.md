Cake Shop management program
This program shows CRUD operations for a cake shop, as well as filtering abilities.
This program demonstrates use of generics in java, by implementing a single repository interface called IRepository which two different class types then have implementations of in the Service class, which makes use of the UI class to
print out the menu, and operations to console directly. . This process is repeated as well,for the FileRepo abstract class which dictates the implementation for these CRUD operations to be performed to a text file, and to a binary file,
and again for the DBRepo abstract class which dictates the implementation of our two classesto write to a database. In the case of this project, sqlite was the database used. 
The filter functions are also implemented using a single generic Filter interface, which then gets implemented for 4 separate filtering methods. 
This project also has tests implemented, and user imput validation.
The choice of what type of repository is to be used gets made through a settings.properties file, which gets passed a type of Repository that it will work with. This choice then dictates what repository class is created in our Main class.
The GUI set up using javaFX offers the ability to interact with these classes in a simple intuitive way.
