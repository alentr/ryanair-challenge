package com.alexandre.ryanair.challenge.gateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "day",
  "flights"
})
public class DayDTO {

  @JsonProperty("day")
  private Integer day;
  @JsonProperty("flights")
  private List<FlightDTO> flights;

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
    return "DayDTO{" +
      "day=" + day +
      ", flights=" + flights +
      '}';
  }
}
