STARTING

The Application can be started with the gradle-task:

	gradle StartApplication
	
This task will build the application as well as start a tomcat-server to make the web-page available.

CONFIGURATION

The application can be run in two ways:
1. A standard way that takes exactly the data that was enclosed in the zip-file of your e-mail.
2. A modified version that uses a user-defined json-file as data base. For this you have to modify the
   file Configuration.properties in the project directory and add the Entry JsonPath with the path of the file.
   The following example sets the path to a file under C:/books.json:
   
		JsonPath=C:/books.json
		
ACCESSING

I tried to solve the back-end as well as the frond end side.
The website you can access under: 

	http://localhost:8080/BookLibraryHomework/index
	
The REST-Server, that provides the data for this web-page can be accessed under:

	http://localhost:7777/api/XYZ
	
So for example to get a book by it's ISBN the following link has to be opened:

	http://localhost:7777/api/book/9780080568782
	
to get by ID you'd call:

	http://localhost:7777/api/book/N1IiAQAAIAAJ
	
to get the books by their category you'd call for example:

	http://localhost:7777/api/category/Computers/books
	
and to get the ratings of the authors you'd call:

	http://localhost:7777/api/rating