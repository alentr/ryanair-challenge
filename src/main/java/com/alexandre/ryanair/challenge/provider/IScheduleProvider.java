package com.alexandre.ryanair.challenge.provider;

import com.alexandre.ryanair.challenge.gateway.dto.ScheduledFlightsByMonthAndDay;

public interface IScheduleProvider {

  ScheduledFlightsByMonthAndDay getScheduledFlightsByYearAndMonth(String departure, String arrival, int year,
    int month);
}
