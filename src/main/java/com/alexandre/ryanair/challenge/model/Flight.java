package com.alexandre.ryanair.challenge.model;

import java.time.LocalDateTime;

public class Flight {

  private String carrierCode;

  private String number;

  private LocalDateTime departureDatetime;

  private LocalDateTime arrivalDateTime;


  public Flight(String carrierCode, String number, LocalDateTime departureDatetime,
    LocalDateTime arrivalDateTime) {
    this.carrierCode = carrierCode;
    this.number = number;
    this.departureDatetime = departureDatetime;
    this.arrivalDateTime = arrivalDateTime;
  }

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

  public LocalDateTime getDepartureDatetime() {
    return departureDatetime;
  }

  public void setDepartureDatetime(LocalDateTime departureDatetime) {
    this.departureDatetime = departureDatetime;
  }

  public LocalDateTime getArrivalDateTime() {
    return arrivalDateTime;
  }

  public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
    this.arrivalDateTime = arrivalDateTime;
  }

  @Override
  public String toString() {
    return "Flight{" +
      "carrierCode='" + carrierCode + '\'' +
      ", number='" + number + '\'' +
      ", departureDatetime=" + departureDatetime +
      ", arrivalDateTime=" + arrivalDateTime +
      '}';
  }
}
