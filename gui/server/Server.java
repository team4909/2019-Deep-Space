package com.stackoverflow.q3732109;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server
{
  
  public static void main(String[] args) throws Exception
  {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

    server.createContext("/rocketHatch1", new RocketHatch1());
    server.createContext("/rocketHatch2", new RocketHatch2());
    server.createContext("/rocketHatch3", new RocketHatch3());

    server.createContext("/rocketCargo1", new RocketCargo1());
    server.createContext("/rocketCargo2", new RocketCargo2());
    server.createContext("/rocketCargo3", new RocketCargo3());

    server.createContext("/shipHatch", new ShipHatch());
    server.createContext("/shipCargo", new ShipCargo());

    server.createContext("/loadStationHatch", new LoadStationHatch());
    server.createContext("/loadStationCargo", new LoadStationCargo());

    server.setExecutor(null); // creates a default executor
    server.start();
  }
  
  static void respond(HttpExchange t) throws IOException
  {
    // Create the response and send it to the requester
    String response = "Ok";
    t.sendResponseHeaders(200, response.length());
    OutputStream os = t.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
  
  
  static class RocketHatch1 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      // Allow cross-origin requests
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateHatch1_DepositHatch_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class RocketHatch2 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateHatch2_DepositHatch_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class RocketHatch3 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateHatch3_DepositHatch_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class RocketCargo1 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateCargo1_DepositCargo_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class RocketCargo2 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateCargo2_DepositCargo_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class RocketCargo3 implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateCargo3_DepositCargo_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class ShipHatch implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateShipHatch_DepositHatch_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class ShipCargo implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateShipCargo_DepositCargo_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class LoadStationHatch implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateLSHatch_DepositHatch_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
  
  static class LoadStationCargo implements HttpHandler
  {
    @Override
    public void handle(HttpExchange t) throws IOException
    {
      t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

      // Issue command group
//      CommandGroup commandGroup =
//        new LineFollow_ElevateLSCargo_DepositCargo_ElevateGround_Backup();
//      commandGroup.start();

      // Send a response to this request
      respond(t);
    }
  }
}
