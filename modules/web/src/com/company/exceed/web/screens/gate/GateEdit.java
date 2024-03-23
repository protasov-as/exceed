package com.company.exceed.web.screens.gate;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.Gate;

import javax.inject.Inject;
import java.util.Optional;

@UiController("exceed_Gate.edit")
@UiDescriptor("gate-edit.xml")
@EditedEntityContainer("gateDc")
@LoadDataBeforeShow
public class GateEdit extends StandardEditor<Gate> {

    @Inject
    protected DataManager dataManager;

    @Override
    protected ValidationErrors validateScreen() {
        if (loadGateByNumber().isPresent()) {
            Messages messages = getBeanLocator().get(Messages.NAME);
            ValidationErrors errors = new ValidationErrors();
            errors.add(messages.getMessage(GateEdit.class, "gateEdit.numberExistsError"));
            return errors;
        }
        return super.validateScreen();
    }

    protected Optional<Gate> loadGateByNumber() {
        return dataManager.load(Gate.class)
                .query("select g from exceed_Gate g where g.number = :number")
                .parameter("number", getEditedEntity().getNumber())
                .optional();
    }
}