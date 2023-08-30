package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.model.FlightConnections;
import java.util.List;

public interface IFlightConnections {

  /**
   * Return a list with all connections between two airports containing the number of connections
   *
   * @param departureAirportCode origin airport
   * @param arrivalAirportCode   destination airport
   * @return List containing all connections
   */
  List<FlightConnections> getAllConnections(String departureAirportCode, String arrivalAirportCode);
}
