package com.company.exceed.web.screens.planin;

import com.haulmont.cuba.gui.screen.*;
import com.company.exceed.entity.PlanIn;

@UiController("exceed_PlanIn.edit")
@UiDescriptor("plan-in-edit.xml")
@EditedEntityContainer("planInDc")
@LoadDataBeforeShow
public class PlanInEdit extends StandardEditor<PlanIn> {
}