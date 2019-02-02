package com.stackoverflow.q3732109;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Test {
  
  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    
    server.createContext("/rocketHatch1", new RocketHatch1());
    server.createContext("/rocketHatch2", new RocketHatch2());
    server.createContext("/rocketHatch3", new RocketHatch3());
    
    server.createContext("/rocketCargo1", new RocketCargo1());
    server.createContext("/rocketCargo2", new RocketCargo2());
    server.createContext("/rocketCargo3", new RocketCargo3());
    
    server.createContext("/shipHatch", new ShipHatch());
    server.createContext("/shipCargo", new ShipCargo());
    
    server.createContext("/LoadStationHatch", new LoadStationHatch());
    server.createContext("/LoadStationCargo", new LoadStationCargo());
    
    server.setExecutor(null); // creates a default executor
    server.start();
  }
  
  static class RocketHatch1 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket hatch 1";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class RocketHatch2 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket hatch 2";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class RocketHatch3 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket hatch 3";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class RocketCargo1 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket cargo 1";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class RocketCargo2 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket cargo 2";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class RocketCargo3 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "rocket cargo 3";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class ShipHatch implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "ship hatch";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class ShipCargo implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "ship cargo";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class LoadStationHatch implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "loading station cargo";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
  
  static class LoadStationCargo implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      //
      // Issue command here
      //
//      doSomething;

      //
      // Send HTTP response
      //
      String response = "loading station cargo";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
}
}
