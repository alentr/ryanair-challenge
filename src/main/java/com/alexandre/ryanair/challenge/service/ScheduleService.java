package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.gateway.ScheduleGateway;
import com.alexandre.ryanair.challenge.gateway.dto.DayDTO;
import com.alexandre.ryanair.challenge.gateway.dto.FlightDTO;
import com.alexandre.ryanair.challenge.model.Flight;
import com.alexandre.ryanair.challenge.model.ScheduledFlightsByDateTimeRange;
import com.alexandre.ryanair.challenge.provider.IScheduleProvider;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

  private final IScheduleProvider scheduleProvider;

  public ScheduleService(ScheduleGateway scheduleProvider) {
    this.scheduleProvider = scheduleProvider;
  }

  /**
   * @param departure         departure airport
   * @param departureDateTime date-time from the origin airport
   * @param arrival           arrival airport
   * @param arrivalDateTime   date-time to the destination airport
   * @return ScheduledFlightsByDateTimeRange with all scheduled flights for the given time range
   */
  public ScheduledFlightsByDateTimeRange getScheduledFlightsByDateTimeRange(String departure,
    LocalDateTime departureDateTime, String arrival, LocalDateTime arrivalDateTime) {

    var scheduledFlightsByDateTimeRange = new ScheduledFlightsByDateTimeRange();

    // faz um for olhando apenas para o ano e mes
    for (LocalDateTime dateTime = departureDateTime;
      dateTime.getYear() <= arrivalDateTime.getYear()
        && dateTime.getMonthValue() <= arrivalDateTime.getMonthValue();
      dateTime = dateTime.plusMonths(1)) {

      var loopDateTime = dateTime;

      var result = scheduleProvider.getScheduledFlightsByYearAndMonth(
        departure, arrival, loopDateTime.getYear(), loopDateTime.getMonthValue());

      result.getDays().stream()
        // only days inside the time range
        .filter(dayDTO -> isValidDay(departureDateTime, arrivalDateTime, dayDTO, loopDateTime))
        .forEach(day -> {
          day.getFlights().forEach(flight -> {
            // only flights inside the time range
            if (loopDateTime.with(flight.getDepartureTime()).isAfter(departureDateTime) &&
              loopDateTime.with(flight.getArrivalTime()).isBefore(arrivalDateTime)) {
              scheduledFlightsByDateTimeRange.getFlights()
                .add(buildFlightFromFlightDTO(flight, loopDateTime, day));
            }
          });
        });
    }

    scheduledFlightsByDateTimeRange.setFrom(departureDateTime);
    scheduledFlightsByDateTimeRange.setTo(arrivalDateTime);
    scheduledFlightsByDateTimeRange.setDepartureAirport(departure);
    scheduledFlightsByDateTimeRange.setArrivalAirport(arrival);

    return scheduledFlightsByDateTimeRange;
  }

  private boolean isValidDay(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
    DayDTO dayDTO, LocalDateTime loopDateTime) {

    var departureMonth = departureDateTime.getMonthValue();
    var arrivalMonth = arrivalDateTime.getMonthValue();
    var departureDay = departureDateTime.getDayOfMonth();
    var arrivalDay = arrivalDateTime.getDayOfMonth();
    var loopMonth = loopDateTime.getMonthValue();
    var day = dayDTO.getDay();

    if (departureMonth == arrivalMonth) {
      return day >= departureDay && day <= arrivalDay;
    } else if (loopMonth > departureMonth && loopMonth < arrivalMonth) {
      return day >= departureDay;
    } else if (loopMonth == departureMonth && day >= departureDay) {
      return true;
    } else {
      return loopMonth == arrivalMonth && day <= arrivalDay;
    }
  }

  private Flight buildFlightFromFlightDTO(FlightDTO flight, LocalDateTime loopDateTime, DayDTO day) {
    return new Flight(
      flight.getCarrierCode(),
      flight.getNumber(),
      loopDateTime.withDayOfMonth(day.getDay()).with(flight.getDepartureTime()),
      calculateFlightArrivalTime(loopDateTime.withDayOfMonth(day.getDay()), flight.getDepartureTime(),
        flight.getArrivalTime()));
  }

  private LocalDateTime calculateFlightArrivalTime(LocalDateTime localDateTime, LocalTime departureTime,
    LocalTime arrivalTime) {
    if (arrivalTime.isBefore(departureTime)) {
      return localDateTime.plusDays(1).with(arrivalTime);
    } else {
      return localDateTime.with(arrivalTime);
    }
  }
}
