HTTPCatClone is an Remote Procedure Call (RPC) API that aims to recreate the backend of the website [http.cat](https://http.cat). 



HTTPCatClone works by populating an in-memory H2 database via a JSON file (data.json) containing HTTP status codes as strings and base64-encoded images from the http.cat website.
The user can then view the images and their codes via GET requests and the user can upload a new image via a POST request in the JSON format: 
{"status" : "statusValue",
"image" : "imageDataValue"},
which the user can then GET and view. 

The user can search the database through the HttpStatus object's id value or the object's status value.

To use the program, run the program in an IDE or preferred method to execute. The program will tell you in the console what port the server is running on then type localhost:portNumber in your browser. 

To view an image by id simply append /status/idNumber to the end of localhost:portNumber in your browser's URL bar.
To view an image by status simply append /status/statusNumber to the end of localhost:portNumber in your browser's URL bar.

To save an image to the database, send a POST request via cURL, Postman, or preferred method in the format of: 
{"status" : "statusValue",
"image" : "imageDataValue"}

