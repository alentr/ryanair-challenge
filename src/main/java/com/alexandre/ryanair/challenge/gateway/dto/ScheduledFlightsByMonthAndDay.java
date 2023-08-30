package com.alexandre.ryanair.challenge.gateway.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "month",
  "days"
})
@Generated("jsonschema2pojo")
public class ScheduledFlightsByMonthAndDay {

  @JsonProperty("month")
  private Integer month;
  @JsonProperty("days")
  private List<DayDTO> days;

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public List<DayDTO> getDays() {
    return days;
  }

  public void setDays(List<DayDTO> days) {
    this.days = days;
  }

  @Override
  public String toString() {
    return "SchedulesDTO{" +
      "month=" + month +
      ", dayDTOS=" + days +
      '}';
  }
}
