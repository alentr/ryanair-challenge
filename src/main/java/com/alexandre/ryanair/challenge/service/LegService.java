package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.dto.LegDTO;
import com.alexandre.ryanair.challenge.model.ConnectionCategory;
import com.alexandre.ryanair.challenge.model.Flight;
import com.alexandre.ryanair.challenge.model.FlightConnections;
import com.alexandre.ryanair.challenge.model.ScheduledFlightsByDateTimeRange;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LegService {

  private final ScheduleService scheduleService;
  @Value("#{${interconnected.flights.minimum-time-between-connections-minutes}}")
  private long timeBetweenConnections;

  public LegService(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  /**
   * @param flightConnections all connections to get flight legs
   * @param departure         origin airport
   * @param departureDateTime date-time from the origin airport
   * @param arrival           destination airport
   * @param arrivalDateTime   date-time to the destination airport
   * @return list of flight legs
   */
  public List<LegDTO> getFlightLegsForConnections(FlightConnections flightConnections, String departure,
    LocalDateTime departureDateTime, String arrival, LocalDateTime arrivalDateTime) {

    List<LegDTO> legs = new ArrayList<>();

    flightConnections.getConnections().forEach(connection -> {
      var scheduledFlightsByDateTimeRange = scheduleService.getScheduledFlightsByDateTimeRange(
        connection.getFrom().getIata(), departureDateTime,
        connection.getTo().getIata(), arrivalDateTime);

      legs.addAll(scheduledFlightsByDateTimeRange.getFlights()
        .parallelStream()
        .filter(Objects::nonNull)
        .map(flight ->
          buildLeg(scheduledFlightsByDateTimeRange, flight))
        .toList());
    });

    return filterInterconnectionLegs(legs, departure, arrival);
  }

  private List<LegDTO> filterInterconnectionLegs(List<LegDTO> legs, String departure, String arrival) {

    var connections = filterLegsWithAirport(legs, departure, arrival);

    Set<LegDTO> legsFiltered = new HashSet<>();

    for (LegDTO first : connections.get(ConnectionCategory.FIRST_FLIGHT)) {
      for (LegDTO second : connections.get(ConnectionCategory.SECOND_FLIGHT)) {
        if (first.getArrivalDateTime().until(second.getDepartureDateTime(), ChronoUnit.MINUTES)
          > timeBetweenConnections) {
          legsFiltered.add(first);
          legsFiltered.add(second);
        }
      }
    }

    return Stream.concat(connections.get(ConnectionCategory.NO_CONNECTION).stream(), legsFiltered.stream())
      .collect(Collectors.toList());
  }

  private LegDTO buildLeg(ScheduledFlightsByDateTimeRange scheduledFlightsByDateTimeRange, Flight flight) {
    return new LegDTO()
      .departureAirport(scheduledFlightsByDateTimeRange.getDepartureAirport())
      .departureDateTime(flight.getDepartureDatetime())
      .arrivalAirport(scheduledFlightsByDateTimeRange.getArrivalAirport())
      .arrivalDateTime(flight.getArrivalDateTime());
  }

  private HashMap<ConnectionCategory, List<LegDTO>> filterLegsWithAirport(List<LegDTO> legs, String departure,
    String arrival) {

    var legsFiltered = new HashMap<ConnectionCategory, List<LegDTO>>();

    legsFiltered.put(ConnectionCategory.FIRST_FLIGHT, new ArrayList<>());
    legsFiltered.put(ConnectionCategory.SECOND_FLIGHT, new ArrayList<>());
    legsFiltered.put(ConnectionCategory.NO_CONNECTION, new ArrayList<>());

    legs.stream().filter(Objects::nonNull)
      .forEach(leg -> {
        if (!leg.getDepartureAirport().equals(departure) || !leg.getArrivalAirport().equals(arrival)) {
          if (leg.getDepartureAirport().equals(departure)) {
            legsFiltered.get(ConnectionCategory.FIRST_FLIGHT).add(leg);
          } else if (leg.getArrivalAirport().equals(arrival)) {
            legsFiltered.get(ConnectionCategory.SECOND_FLIGHT).add(leg);
          }
        } else {
          legsFiltered.get(ConnectionCategory.NO_CONNECTION).add(leg);
        }
      });

    return legsFiltered;
  }
}
