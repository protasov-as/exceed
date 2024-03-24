package com.company.exceed.web.screens.gate;

import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.Gate;

import java.util.List;
import java.util.function.Consumer;

@UiController("exceed_Gate.browse")
@UiDescriptor("gate-browse.xml")
@LookupComponent("gatesTable")
@LoadDataBeforeShow
public class GateBrowse extends StandardLookup<Gate> {
}