package com.alexandre.ryanair.challenge.gateway;

import com.alexandre.ryanair.challenge.gateway.dto.RouteDTO;
import com.alexandre.ryanair.challenge.provider.IRouteProvider;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RouteGateway implements IRouteProvider {

  @Value("${ryanair.url}")
  private String ryanairUrl;


  @Cacheable
  @Override
  public List<RouteDTO> getAllRoutes() {
    var webClient = WebClient.builder().baseUrl(ryanairUrl).build();

    var routes = webClient.get()
      .uri("/views/locate/3/routes")
      .retrieve()
      .bodyToFlux(RouteDTO.class)
      .collect(Collectors.toList())
      .block();

    return routes != null ? routes : Collections.emptyList();
  }
}
