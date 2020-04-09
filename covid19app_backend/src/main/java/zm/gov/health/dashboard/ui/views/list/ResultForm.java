package zm.gov.health.dashboard.ui.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;
import zm.gov.health.dashboard.backend.entity.Result;

import java.util.List;

public class ResultForm extends FormLayout {
    DatePicker reportDate = new DatePicker("Reporting date");
    TextField confirmedZambia = new TextField("Confirmed cases [Zambia]");

    TextField confirmedWorld = new TextField("Confirmed cases [World]");
    TextField deathsZambia = new TextField("Deaths [Zambia]");
    TextField deathsWorld = new TextField("Deaths [World]");
    TextField recoveredZambia = new TextField("Recovered [Zambia]");
    TextField recoveredWorld = new TextField("Recovered [World]");
    TextField testedPositive = new TextField("Tested [Positives]");
    TextField testedNegative = new TextField("Tested [Negatives]");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Result> binder = new BeanValidationBinder<>(Result.class);

    public ResultForm(List<Result> results) {
        addClassName("result-form");
        confirmedZambia.setRequired(true);
        confirmedWorld.setRequired(true);
        deathsZambia.setRequired(true);
        deathsWorld.setRequired(true);
        recoveredWorld.setRequired(true);
        recoveredZambia.setRequired(true);
        testedNegative.setRequired(true);
        testedPositive.setRequired(true);
        binder.forField(confirmedZambia)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getConfirmedZambia, Result::setConfirmedZambia);
        binder.forField(confirmedWorld)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getConfirmedWorld, Result::setConfirmedWorld);
        binder.forField(deathsZambia)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getDeathsZambia, Result::setDeathsZambia);
        binder.forField(deathsWorld)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getDeathsWorld, Result::setDeathsWorld);
        binder.forField(recoveredZambia)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getRecoveredZambia, Result::setRecoveredZambia);
        binder.forField(recoveredWorld)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getRecoveredWorld, Result::setRecoveredWorld);
        binder.forField(testedPositive)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getTestedPositive, Result::setTestedPositive);
        binder.forField(testedNegative)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must be a number"))
                .withValidator(value -> value >= 0, "Enter number")
                .bind(Result::getTestedNegative, Result::setTestedNegative);

        binder.bindInstanceFields(this);

        add(
                reportDate,
                confirmedZambia,
                confirmedWorld,
                deathsZambia,
                deathsWorld,
                recoveredZambia,
                recoveredWorld,
                testedPositive,
                testedNegative,
                createButtonsLayout()
        );
    }

    public void setResult(Result result) {
        binder.setBean(result);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

//        Result result = binder.getBean();
//        System.out.println("Bean is: \n" +
//                "\nReport date: " + result.getReportDate() + " " +
//                "\nconfirmedZambia: " + result.getConfirmedZambia() + " " +
//                "\nConfirmed_world: " + result.getConfirmedWorld() + " " +
//                "\nDeaths_zm: " + result.getDeathsZambia() + " " +
//                "\nDeaths_world: " + result.getDeathsWorld() + " " +
//                "\nRecovered_zm: " + result.getRecoveredZambia() + " " +
//                "\nRecovered_world: " + result.getRecoveredWorld() + " " +
//                "\nTested_pos: " + result.getTestedPositive() + " " +
//                "\nTested_neg: " + result.getTestedNegative()
//
//        );

        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ResultForm> {
        private Result result;

        protected ContactFormEvent(ResultForm source, Result result) {
            super(source, false);
            this.result = result;
        }

        public Result getResult() {
            return result;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ResultForm source, Result result) {
            super(source, result);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ResultForm source, Result result) {
            super(source, result);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ResultForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
