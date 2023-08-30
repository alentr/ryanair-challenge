package com.alexandre.ryanair.challenge.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledFlightsByDateTimeRange {

  private LocalDateTime from;

  private LocalDateTime to;

  private String departureAirport;

  private String arrivalAirport;

  private List<Flight> flights = new ArrayList<>();


  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  public LocalDateTime getTo() {
    return to;
  }

  public void setTo(LocalDateTime to) {
    this.to = to;
  }

  public String getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  public String getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  public List<Flight> getFlights() {
    return flights;
  }

  public void setFlights(List<Flight> flights) {
    this.flights = flights;
  }

  @Override
  public String toString() {
    return "ScheduledFlightsByDateTimeRange{" +
      "from=" + from +
      ", to=" + to +
      ", departureAirport='" + departureAirport + '\'' +
      ", arrivalAirport='" + arrivalAirport + '\'' +
      ", flights=" + flights +
      '}';
  }
}
