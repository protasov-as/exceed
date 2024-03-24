package com.company.exceed.core;

import com.company.exceed.config.SchedulerConfig;
import com.company.exceed.entity.PlanIn;
import com.company.exceed.entity.VehicleStatus;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.security.app.Authenticated;
import org.apache.commons.lang3.time.DateUtils;

import javax.inject.Inject;
import java.util.List;

public class RegisterVehicleDepartureBean {
    public static final String NAME = "exceed_RegisterVehicleDepartureBean";
    @Inject
    protected Configuration configuration;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected TimeSource timeSource;

    @Authenticated
    public void registerVehicleDeparture() {
        SchedulerConfig schedulerConfig = configuration.getConfig(SchedulerConfig.class);
        int mins = schedulerConfig.getVehicleDepartureInMinutes();

        if (mins != -1) {
            List<PlanIn> planIns = getPlanInsForDeparture(mins);
            for (PlanIn planIn : planIns) {
                planIn.setStatus(VehicleStatus.DEPARTED);
                planIn.setState(null);
                planIn.setDepartDate(timeSource.currentTimestamp());
                planIn.setGate("Exit");
                dataManager.commit(planIn);
            }
        }
    }

    protected List<PlanIn> getPlanInsForDeparture(int departureTime) {
        return dataManager.load(PlanIn.class)
                .query("select p from exceed_PlanIn p " +
                        " where p.departAcceptedDate >= :currentTimePlusDepart " +
                        " and p.status = 50 ")
                .parameter("currentTimePlusDepart", DateUtils.addMinutes(timeSource.currentTimestamp(), departureTime * -1))
                .list();
    }
}