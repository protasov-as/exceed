package com.company.exceed.web.screens;

import com.company.exceed.entity.WeightCapacity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UiController("exceed_RegistrationFragment")
@UiDescriptor("registration-fragment.xml")
public class RegistrationFragment extends Screen {
    @Inject
    private Messages messages;
    @Inject
    private ScreenValidation screenValidation;
    @Inject
    private Button okBtn;
    @Inject
    private Button cancelBtn;
    @Inject
    private TextField<String> vehicleNumber;
    @Inject
    private TextField<String> driverPhone;
    @Inject
    private TextField<String> driverName;
    @Inject
    private LookupField<WeightCapacity> weightCapacity;

    @Subscribe
    public void onInit(InitEvent event) {
        addButtonListeners();
        setWeightCapacityOptions();
    }

    protected void addButtonListeners() {
        cancelBtn.addClickListener(e -> close(StandardOutcome.CLOSE));
        okBtn.addClickListener(e -> {
            ValidationErrors errors = screenValidation.validateUiComponents(this.getWindow());
            if (!errors.isEmpty()) {
                screenValidation.showValidationErrors(this, errors);
                return;
            }
            close(new RegistrationCloseAction(vehicleNumber.getValue(), driverName.getValue(), driverPhone.getValue(), weightCapacity.getValue()));
        });
    }

    protected void setWeightCapacityOptions() {
        weightCapacity.setOptionsEnum(WeightCapacity.class);
    }

    @Install(to = "vehicleNumber", subject = "validator")
    private void vehicleNumberValidator(String string) {
        String regexp = "^[А-Я]{1}[0-9]{3}[А-Я]{2}$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            throw new ValidationException(messages.getMessage(RegistrationFragment.class, "vehicleNumberValidationError"));
        }
    }

    @Install(to = "driverPhone", subject = "validator")
    private void driverPhoneValidator(String string) {
        String regexp = "^[+]{1}[7]{1}[(]{1}[0-9]{3}[)]{1}[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            throw new ValidationException(messages.getMessage(RegistrationFragment.class, "driverPhoneValidationError"));
        }
    }

    public class RegistrationCloseAction extends StandardCloseAction {

        private String vehicleNumber;
        private String driverName;
        private String driverPhone;
        private WeightCapacity weightCapacity;

        public RegistrationCloseAction(String vehicleNumber, String driverName, String driverPhone, WeightCapacity weightCapacity) {
            super("myCloseAction");
            this.vehicleNumber = vehicleNumber;
            this.driverName = driverName;
            this.driverPhone = driverPhone;
            this.weightCapacity = weightCapacity;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public String getDriverName() {
            return driverName;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public WeightCapacity getWeightCapacity() {
            return weightCapacity;
        }
    }
}