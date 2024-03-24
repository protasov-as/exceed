package com.company.exceed.listeners;

import com.company.exceed.entity.PlanIn;

import java.util.UUID;

import com.company.exceed.entity.PlaninEvent;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.TimeSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("exceed_PlanInChangedListener")
public class PlanInChangedListener {
    @Inject
    Metadata metadata;
    @Inject
    private TimeSource timeSource;
    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<PlanIn, UUID> event) {
        AttributeChanges changes = event.getChanges();
        if (changes.isChanged("status") || changes.isChanged("state")) {
            PlanIn planIn = dataManager.load(event.getEntityId())
                    .view("_local")
                    .one();
            PlaninEvent changeEvent = metadata.create(PlaninEvent.class);
            changeEvent.setRegNumber(planIn.getRegNumber());
            changeEvent.setVehicleNumber(planIn.getVehicleNumber());
            changeEvent.setStatus(planIn.getStatus());
            changeEvent.setState(planIn.getState());
            changeEvent.setEventDate(timeSource.currentTimestamp());
            dataManager.commit(changeEvent);
        }
    }
}