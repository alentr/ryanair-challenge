package com.alexandre.ryanair.challenge.model;

import com.alexandre.ryanair.challenge.gateway.dto.FlightDTO;
import java.util.ArrayList;
import java.util.List;


public class ScheduledFlightsByDay {

  private Integer day;
  private List<FlightDTO> flights = new ArrayList<>();

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public List<FlightDTO> getFlights() {
    return flights;
  }

  public void setFlights(List<FlightDTO> flights) {
    this.flights = flights;
  }

  @Override
  public String toString() {
    return "SchedulesDayDTO{" + "day=" + day + ", flights=" + flights + '}';
  }
}
