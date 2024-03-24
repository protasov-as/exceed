package com.company.exceed.web.screens.gate;

import com.company.exceed.entity.Gate;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.List;

@UiController("exceed_GateFragment")
@UiDescriptor("gate-fragment.xml")
public class GateFragment extends Screen {

    @Inject
    protected LookupField<Gate> lookup;
    @Inject
    private Button okBtn;
    @Inject
    private Button cancelBtn;
    @Inject
    private CollectionLoader<Gate> gatesDl;

    @Subscribe
    public void onInit(InitEvent event) {
        gatesDl.load();
        addButtonListeners();
    }

    protected void addButtonListeners() {
        cancelBtn.addClickListener(e -> close(StandardOutcome.CLOSE));
        okBtn.addClickListener(e -> close(new GateCloseAction(lookup.getValue())));
    }

    public class GateCloseAction extends StandardCloseAction {

        private Gate gate;

        public GateCloseAction(Gate gate) {
            super("myCloseAction");
            this.gate = gate;
        }

        public Gate getResult() {
            return gate;
        }
    }
}