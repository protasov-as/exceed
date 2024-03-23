package com.company.exceed.web.screens.gate;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.Gate;

@UiController("exceed_Gate.browse")
@UiDescriptor("gate-browse.xml")
@LookupComponent("gatesTable")
@LoadDataBeforeShow
public class GateBrowse extends StandardLookup<Gate> {
}