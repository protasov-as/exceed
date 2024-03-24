package com.company.exceed.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(FilterDataLoadService.NAME)
public class FilterDataLoadServiceBean implements FilterDataLoadService {
    private static final Logger log = LoggerFactory.getLogger(FilterDataLoadServiceBean.class);
    @Inject
    protected Persistence persistence;

    @Override
    public List<String> getDistinctRegNumbers() {
        List regNumbers = null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            regNumbers = em.createNativeQuery("select distinct p.reg_number from EXCEED_PLAN_IN p where p.delete_ts is null and p.reg_number is not null").getResultList();
            tx.commit();
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return regNumbers;
    }

    @Override
    public List<String> getDistinctVehicleNumber() {
        List vehicleNumbers = null;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            vehicleNumbers = em.createNativeQuery("select distinct p.vehicle_number from EXCEED_PLAN_IN p where p.delete_ts is null and p.vehicle_number is not null").getResultList();
            tx.commit();
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return vehicleNumbers;
    }
}