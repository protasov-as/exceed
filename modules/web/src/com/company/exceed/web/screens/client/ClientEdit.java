package com.company.exceed.web.screens.client;

import com.company.exceed.entity.Client;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Optional;

@UiController("exceed_Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
@LoadDataBeforeShow
public class ClientEdit extends StandardEditor<Client> {

    @Inject
    protected DataManager dataManager;

    @Override
    protected ValidationErrors validateScreen() {
        if (loadClientByCode().isPresent()) {
            Messages messages = getBeanLocator().get(Messages.NAME);
            ValidationErrors errors = new ValidationErrors();
            errors.add(messages.getMessage(ClientEdit.class, "clientEdit.codeExistsError"));
            return errors;
        }
        return super.validateScreen();
    }

    protected Optional<Client> loadClientByCode() {
        return dataManager.load(Client.class)
                .query("select c from exceed_Client c where c.code = :code")
                .parameter("code", getEditedEntity().getCode())
                .optional();
    }
}