package com.company.exceed.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultInt;

@Source(type = SourceType.APP)
public interface SchedulerConfig extends Config {
    @Property("exceed.vehicleDepartureInMinutes")
    @DefaultInt(-1)
    int getVehicleDepartureInMinutes();
}
