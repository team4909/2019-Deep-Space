***Directories***

server -
  Example of what will go in robot code, to provide the HTTP server that the
  GUI communicates with to request that Commands or CommandGroups be executed.

operator -
  Operator user interface

***Usage***
* Server
  * Change directory into `server`
  * Compile the server. On Linux you can use the command-line javac utility:
    `javac Server.java` or you should be able to compile it with whatever Java
    environment you have.
  * Run the server. Again, with Linux, you can use the command-line Java
    runtime: `java Server.java` or use whatever Java run-time environment you
    have.
  * Test the server with your browser, with URL
    http://localhost:8000/rocketHatch1. You will receive back a response that
  * says, `Ok`.

* Client (GUI)
  * Change directory into `operator`.
  * Install the prerequisites: `npm install`
  * If you are on Linux, or probably on a Mac, the symbolic link `qx` should
    now point to `node_modules/qxcompiler/qx`.
  * For development or testing, you can build the Operator console GUI
    application by typing `qx serve` which builds it and starts an HTTP server
    that will serve it.
  * Connect to the Operator console app from your browser, with the URL
    http://localhost:8080/
  * All of the Rocket Ship buttons will issue a request to the server. You can
  * open the Chrome "Developer Tools" console to see the `Ok` responses being
  * returned by the server.
