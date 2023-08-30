package com.alexandre.ryanair.challenge.gateway.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "airportFrom",
  "airportTo",
  "connectingAirport",
  "newRoute",
  "seasonalRoute",
  "operator",
  "carrierCode",
  "group",
  "similarArrivalAirportCodes",
  "tags"
})
public class RouteDTO {

  @JsonProperty("airportFrom")
  private String airportFrom;
  @JsonProperty("airportTo")
  private String airportTo;
  @JsonProperty("connectingAirport")
  private Object connectingAirport;
  @JsonProperty("newRoute")
  private Boolean newRoute;
  @JsonProperty("seasonalRoute")
  private Boolean seasonalRoute;
  @JsonProperty("operator")
  private String operator;
  @JsonProperty("carrierCode")
  private String carrierCode;
  @JsonProperty("group")
  private String group;
  @JsonProperty("similarArrivalAirportCodes")
  private List<String> similarArrivalAirportCodes;
  @JsonProperty("tags")
  private List<Object> tags;

  public String getAirportFrom() {
    return airportFrom;
  }

  public void setAirportFrom(String airportFrom) {
    this.airportFrom = airportFrom;
  }

  public String getAirportTo() {
    return airportTo;
  }

  public void setAirportTo(String airportTo) {
    this.airportTo = airportTo;
  }

  public Object getConnectingAirport() {
    return connectingAirport;
  }

  public void setConnectingAirport(Object connectingAirport) {
    this.connectingAirport = connectingAirport;
  }

  public Boolean getNewRoute() {
    return newRoute;
  }

  public void setNewRoute(Boolean newRoute) {
    this.newRoute = newRoute;
  }

  public Boolean getSeasonalRoute() {
    return seasonalRoute;
  }

  public void setSeasonalRoute(Boolean seasonalRoute) {
    this.seasonalRoute = seasonalRoute;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getCarrierCode() {
    return carrierCode;
  }

  public void setCarrierCode(String carrierCode) {
    this.carrierCode = carrierCode;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public List<String> getSimilarArrivalAirportCodes() {
    return similarArrivalAirportCodes;
  }

  public void setSimilarArrivalAirportCodes(List<String> similarArrivalAirportCodes) {
    this.similarArrivalAirportCodes = similarArrivalAirportCodes;
  }

  public List<Object> getTags() {
    return tags;
  }

  public void setTags(List<Object> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "RoutesDTO{" +
      "airportFrom='" + airportFrom + '\'' +
      ", airportTo='" + airportTo + '\'' +
      ", connectingAirport=" + connectingAirport +
      ", newRoute=" + newRoute +
      ", seasonalRoute=" + seasonalRoute +
      ", operator='" + operator + '\'' +
      ", carrierCode='" + carrierCode + '\'' +
      ", group='" + group + '\'' +
      ", similarArrivalAirportCodes=" + similarArrivalAirportCodes +
      ", tags=" + tags +
      '}';
  }
}