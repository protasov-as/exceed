package com.company.exceed.web.screens.planin;

import com.company.exceed.entity.*;
import com.company.exceed.web.screens.RegistrationFragment;
import com.company.exceed.web.screens.gate.GateFragment;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.Facets;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.List;

@UiController("exceed_PlanIn.browse")
@UiDescriptor("plan-in-browse.xml")
@LookupComponent("planInsTable")
@LoadDataBeforeShow
public class PlanInBrowse extends StandardLookup<PlanIn> {

    private final String planTabName = "planTab";
    private final String gatesTabName = "gatesTab";

    @Inject
    private Messages messages;
    @Inject
    private Facets facets;
    @Inject
    private TabSheet tabSheet;
    @Inject
    private TimeSource timeSource;
    @Inject
    private DataManager dataManager;
    @Inject
    private Notifications notifications;
    @Inject
    private Screens screens;

    @Inject
    private CollectionContainer<PlanIn> planInsGateDc;
    @Inject
    private CollectionLoader<PlanIn> planInsPlanDl;
    @Inject
    private CollectionContainer<PlanIn> planInsPlanDc;
    @Inject
    private CollectionLoader<PlanIn> planInsGateDl;
    @Inject
    private Table<PlanIn> planInsTableGate;
    @Inject
    private Table<PlanIn> planInsTablePlan;
    @Inject
    private Button loadedUpBtnGates;
    @Inject
    private Button assignGateBtnPlan;
    @Inject
    private Button regUpBtnPlan;

    @Subscribe("tabSheet")
    public void onTabSheetSelectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        if (planTabName.equals(event.getSelectedTab().getName())) {
            planInsPlanDl.load();
            setPlannedTabCaption();
        } else if (gatesTabName.equals(event.getSelectedTab().getName())) {
            planInsGateDl.load();
            setGatesTabCaption();
        }
    }

    @Subscribe
    public void onInit(InitEvent event) {
        addDcListeners();
        addRefreshTimer();
    }

    protected void addDcListeners() {
        planInsPlanDc.addCollectionChangeListener(e -> {
            setPlannedTabCaption();
        });

        planInsPlanDc.addItemPropertyChangeListener(e -> {
            setPlannedTabCaption();
        });

        planInsGateDc.addCollectionChangeListener(e -> {
            setGatesTabCaption();
        });

        planInsGateDc.addItemPropertyChangeListener(e -> {
            setGatesTabCaption();
        });

        planInsGateDc.addItemChangeListener (e -> {
            if (planInsTableGate != null && planInsTableGate.getSingleSelected() != null) {
                loadedUpBtnGates.setEnabled(true);
            } else {
                loadedUpBtnGates.setEnabled(false);
            }
        });

        planInsPlanDc.addItemChangeListener (e -> {
            if (planInsTablePlan != null && planInsTablePlan.getSingleSelected() != null) {
                assignGateBtnPlan.setEnabled(true);
                regUpBtnPlan.setEnabled(true);
            } else {
                assignGateBtnPlan.setEnabled(false);
                regUpBtnPlan.setEnabled(false);
            }
        });
    }

    protected void addRefreshTimer() {
        Timer refreshTimer = facets.create(Timer.class);
        getWindow().addFacet(refreshTimer);
        refreshTimer.setId("refreshTimer");
        refreshTimer.setDelay(10000);
        refreshTimer.setRepeating(true);

        refreshTimer.addTimerActionListener(e -> {
            planInsPlanDl.load();
            setPlannedTabCaption();
            planInsGateDl.load();
            setGatesTabCaption();
        });

        refreshTimer.start();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        planInsPlanDl.load();
        setPlannedTabCaption();

        planInsGateDl.load();
        setGatesTabCaption();
    }

    protected void setPlannedTabCaption() {
        String plannedTabName =  messages.getMessage(PlanInBrowse.class, "plannedTab");
        if (CollectionUtils.isNotEmpty(planInsPlanDc.getItems())) {
            plannedTabName += " (" + planInsPlanDc.getItems().size() + ")";
        }
        tabSheet.getTab("planTab").setCaption(plannedTabName);
    }

    protected void setGatesTabCaption() {
        String gatesTabName =  messages.getMessage(PlanInBrowse.class, "gatesTab");
        if (CollectionUtils.isNotEmpty(planInsGateDc.getItems())) {
            gatesTabName += " (" + planInsGateDc.getItems().size() + ")";
        }
        tabSheet.getTab("gatesTab").setCaption(gatesTabName);
    }

    public void loadedUp() {
        PlanIn selected = planInsTableGate.getSingleSelected();
        if (selected != null) {
            selected.setStatus(VehicleStatus.DEPART_ACCEPTED);
            selected.setState(VehicleState.DEPART_ACCEPTED);
            selected.setDepartAcceptedDate(timeSource.currentTimestamp());
            selected.setGate("pexit");

            dataManager.commit(selected);

            planInsPlanDl.load();
            setPlannedTabCaption();
            planInsGateDl.load();
            setGatesTabCaption();
        }
    }

    public void regUp() {
        PlanIn selected = planInsTablePlan.getSingleSelected();
        if (selected != null) {
            Screen gateReg = screens.create("exceed_RegistrationFragment", OpenMode.DIALOG);
            gateReg.addAfterCloseListener(afterCloseEvent -> {
                CloseAction closeAction = afterCloseEvent.getCloseAction();
                if (closeAction instanceof RegistrationFragment.RegistrationCloseAction) {
                    selected.setRegNumber(String.valueOf(System.currentTimeMillis()).substring(1,10));

                    RegistrationFragment.RegistrationCloseAction action = (RegistrationFragment.RegistrationCloseAction) closeAction;
                    selected.setVehicleNumber(action.getVehicleNumber());
                    selected.setDriverName(action.getDriverName());
                    selected.setDriverPhone(action.getDriverPhone());
                    selected.setWeightCapacity(action.getWeightCapacity());
                    selected.setStatus(VehicleStatus.REGISTERED);
                    selected.setState(VehicleState.WAITING_GATES);
                    selected.setRegDate(timeSource.currentTimestamp());

                    dataManager.commit(selected);

                    planInsPlanDl.load();
                    setPlannedTabCaption();
                    planInsGateDl.load();
                    setGatesTabCaption();
                }
            });
            gateReg.show();
        }
    }

    public void assignGate() {
        PlanIn selected = planInsTablePlan.getSingleSelected();
        if (selected != null) {
            if (!VehicleStatus.REGISTERED.equals(selected.getStatus())) {
                notifications.create(Notifications.NotificationType.ERROR)
                        .withCaption(messages.getMessage(PlanInBrowse.class, "errorTitle"))
                        .withDescription(messages.getMessage(PlanInBrowse.class, "notRegisteredError"))
                        .show();

            } else {
                List<Gate> gates = loadGateByNumber();
                if (CollectionUtils.isNotEmpty(gates)) {
                    Screen gateScreen = screens.create("exceed_GateFragment", OpenMode.DIALOG);
                    gateScreen.addAfterCloseListener(afterCloseEvent -> {
                        CloseAction closeAction = afterCloseEvent.getCloseAction();
                        if (closeAction instanceof GateFragment.GateCloseAction) {
                            Gate result = ((GateFragment.GateCloseAction) closeAction).getResult();
                            selected.setGate(result.getNumber());
                            selected.setStatus(VehicleStatus.GATES);
                            selected.setState(VehicleState.AT_GATES);
                            selected.setAtGatesDate(timeSource.currentTimestamp());

                            dataManager.commit(selected);

                            planInsPlanDl.load();
                            setPlannedTabCaption();
                            planInsGateDl.load();
                            setGatesTabCaption();
                        }
                    });
                    gateScreen.show();
                }
            }
        }
    }

    protected List<Gate> loadGateByNumber() {
        return dataManager.load(Gate.class)
                .query("select g from exceed_Gate g " +
                        " where g.availableFrom <= :currentTime and g.availableTo >= :currentTime " +
                        " and not exists " +
                        " (select p.id from exceed_PlanIn p where p.gate = g.number) ")
                .parameter("currentTime", timeSource.currentTimestamp())
                .list();
    }
}