package com.company.exceed.web.screens.planinevent;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.PlaninEvent;

@UiController("exceed_PlaninEvent.browse")
@UiDescriptor("planin-event-browse.xml")
@LookupComponent("planinEventsTable")
@LoadDataBeforeShow
public class PlaninEventBrowse extends StandardLookup<PlaninEvent> {
}