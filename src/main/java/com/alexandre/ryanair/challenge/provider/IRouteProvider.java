package com.alexandre.ryanair.challenge.provider;

import com.alexandre.ryanair.challenge.gateway.dto.RouteDTO;
import java.util.List;

public interface IRouteProvider {

  List<RouteDTO> getAllRoutes();
}
