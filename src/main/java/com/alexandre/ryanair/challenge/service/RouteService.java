package com.alexandre.ryanair.challenge.service;

import com.alexandre.ryanair.challenge.gateway.RouteGateway;
import com.alexandre.ryanair.challenge.gateway.dto.RouteDTO;
import com.alexandre.ryanair.challenge.provider.IRouteProvider;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

  private final IRouteProvider routeProvider;

  public RouteService(RouteGateway routeProvider) {
    this.routeProvider = routeProvider;
  }

  public List<RouteDTO> getRoutes() {
    return routeProvider.getAllRoutes().parallelStream().filter(
      this::isRyanairRoute).toList();
  }

  private boolean isRyanairRoute(RouteDTO route) {
    return route.getConnectingAirport() == null && route.getOperator().equals("RYANAIR");
  }
}
