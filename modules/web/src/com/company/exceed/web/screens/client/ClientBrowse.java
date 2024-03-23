package com.company.exceed.web.screens.client;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.Client;

@UiController("exceed_Client.browse")
@UiDescriptor("client-browse.xml")
@LookupComponent("clientsTable")
@LoadDataBeforeShow
public class ClientBrowse extends StandardLookup<Client> {
}