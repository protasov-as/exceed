package com.company.exceed.service;

import com.google.common.collect.Lists;

import java.util.List;

public interface FilterDataLoadService {
    String NAME = "exceed_FilterDataLoadService";

    List<String> getDistinctRegNumbers();
    List<String> getDistinctVehicleNumber();
}