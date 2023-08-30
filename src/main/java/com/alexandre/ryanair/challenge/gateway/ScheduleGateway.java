package com.alexandre.ryanair.challenge.gateway;

import com.alexandre.ryanair.challenge.gateway.dto.ScheduledFlightsByMonthAndDay;
import com.alexandre.ryanair.challenge.provider.IScheduleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ScheduleGateway implements IScheduleProvider {

  @Value("${ryanair.url}")
  private String ryanairUrl;


  @Cacheable
  @Override
  public ScheduledFlightsByMonthAndDay getScheduledFlightsByYearAndMonth(String departure, String arrival,
    int year, int month) {
    var webClient = WebClient.builder().baseUrl(ryanairUrl).build();

    return webClient.get().uri(uriBuilder ->
        uriBuilder.path("/timtbl/3/schedules/{departure}/{arrival}/years/{year}/months/{month}")
          .build(departure, arrival, year, month)).retrieve().bodyToMono(ScheduledFlightsByMonthAndDay.class)
      .block();
  }
}
