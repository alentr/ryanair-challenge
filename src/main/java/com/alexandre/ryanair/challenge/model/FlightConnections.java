package com.alexandre.ryanair.challenge.model;

import java.util.List;

public class FlightConnections {

  private int stops;

  private List<Connection> connections;

  public FlightConnections(int stops, List<Connection> connections) {
    this.stops = stops;
    this.connections = connections;
  }

  public int getStops() {
    return stops;
  }

  public void setStops(int stops) {
    this.stops = stops;
  }

  public List<Connection> getConnections() {
    return connections;
  }

  public void setConnections(List<Connection> connections) {
    this.connections = connections;
  }
}
