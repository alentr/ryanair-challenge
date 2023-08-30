package com.alexandre.ryanair.challenge.controller;

import com.alexandre.ryanair.challenge.dto.InterconnectionDTO;
import com.alexandre.ryanair.challenge.service.InterconnectionService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterconnectionsController implements InterconnectionsApi {

  private final InterconnectionService interconnectionService;

  public InterconnectionsController(InterconnectionService interconnectionService) {
    this.interconnectionService = interconnectionService;
  }

  @Override
  public ResponseEntity<List<InterconnectionDTO>> getInterconnections(String departure,
    LocalDateTime departureDateTime, String arrival, LocalDateTime arrivalDateTime) {

    var interconnections = interconnectionService.getInterconnections(departure, departureDateTime, arrival,
      arrivalDateTime);

    return ResponseEntity.ok(interconnections);
  }
}
