package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.dto.InterconnectionDTO;
import com.alexandre.ryanair.challenge.model.FlightConnections;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InterconnectionService {

  private final LegService legService;

  private final IFlightConnections flightConnectionsService;

  public InterconnectionService(LegService legService, FlightConnectionsService flightConnectionsService) {
    this.legService = legService;
    this.flightConnectionsService = flightConnectionsService;
  }

  public List<InterconnectionDTO> getInterconnections(String departure, LocalDateTime departureDateTime,
    String arrival, LocalDateTime arrivalDateTime) {

    var flightConnections = flightConnectionsService.getAllConnections(departure, arrival);

    return flightConnections.stream()
      .map(flightConnection ->
        buildInterconnection(departure, departureDateTime, arrival, arrivalDateTime, flightConnection))
      .filter(interconnection -> !interconnection.getLegs().isEmpty())
      .collect(Collectors.toList());
  }

  private InterconnectionDTO buildInterconnection(String departure, LocalDateTime departureDateTime,
    String arrival, LocalDateTime arrivalDateTime, FlightConnections flightConnections) {

    var legs = legService.getFlightLegsForConnections(flightConnections,
      departure, departureDateTime, arrival, arrivalDateTime);

    return new InterconnectionDTO().stops(flightConnections.getStops()).legs(legs);
  }
}
