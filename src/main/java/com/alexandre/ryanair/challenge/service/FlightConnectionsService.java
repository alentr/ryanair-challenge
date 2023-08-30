package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.model.Airport;
import com.alexandre.ryanair.challenge.model.Connection;
import com.alexandre.ryanair.challenge.model.FlightConnections;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FlightConnectionsService implements IFlightConnections {

  private final RouteService routeService;
  @Value("#{${interconnected.flights.max-stop}}")
  private Integer maxStop;

  public FlightConnectionsService(RouteService routeService) {
    this.routeService = routeService;
  }


  @Override
  public List<FlightConnections> getAllConnections(String departureAirportCode, String arrivalAirportCode) {
    Graph<Airport, Connection> graph = new DefaultDirectedGraph<>(Connection.class);

    var routes = routeService.getRoutes();

    routes.forEach(r -> {
      var airportFrom = new Airport(r.getAirportFrom());
      var airportTo = new Airport(r.getAirportTo());

      graph.addVertex(airportFrom);
      graph.addVertex(airportTo);
      graph.addEdge(airportFrom, airportTo);
    });

    return new AllDirectedPaths<>(graph)
      .getAllPaths(
        new Airport(departureAirportCode),
        new Airport(arrivalAirportCode),
        true,
        maxStop + 1)
      .stream()
      .map(graphPath -> new FlightConnections(graphPath.getEdgeList().size() - 1,
        graphPath.getEdgeList()))
      .collect(Collectors.toList());
  }
}
