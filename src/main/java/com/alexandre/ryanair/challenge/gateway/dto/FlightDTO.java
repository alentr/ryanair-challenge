package com.alexandre.ryanair.challenge.gateway.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "carrierCode",
  "number",
  "departureTime",
  "arrivalTime"
})
public class FlightDTO {

  @JsonProperty("carrierCode")
  private String carrierCode;
  @JsonProperty("number")
  private String number;
  @JsonProperty("departureTime")
  @JsonFormat(pattern = "HH:mm")
  private LocalTime departureTime;
  @JsonProperty("arrivalTime")
  @JsonFormat(pattern = "HH:mm")
  private LocalTime arrivalTime;

  public String getCarrierCode() {
    return carrierCode;
  }

  public void setCarrierCode(String carrierCode) {
    this.carrierCode = carrierCode;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(LocalTime departureTime) {
    this.departureTime = departureTime;
  }

  public LocalTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(LocalTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  @Override
  public String toString() {
    return "FlightDTO{" +
      "carrierCode='" + carrierCode + '\'' +
      ", number='" + number + '\'' +
      ", departureTime=" + departureTime +
      ", arrivalTime=" + arrivalTime +
      '}';
  }
}
