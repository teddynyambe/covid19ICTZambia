package zm.gov.health.dashboard.ui.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.format.datetime.DateFormatter;
import zm.gov.health.dashboard.backend.entity.Result;
import zm.gov.health.dashboard.backend.service.ResultService;
import zm.gov.health.dashboard.ui.MainLayout;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

@Route(value = "", layout = MainLayout.class)
@PageTitle("COVID-19 Results | Ministry of Health Dashboard")
public class ResultsListView extends VerticalLayout {

    private final ResultForm form;
    Grid<Result> grid = new Grid<>(Result.class);
    TextField filterText = new TextField();

    private ResultService resultService;

    public ResultsListView(ResultService resultService) {
        this.resultService = resultService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();


        form = new ResultForm(resultService.findAll());
        form.addListener(ResultForm.SaveEvent.class, this::saveResult);
        form.addListener(ResultForm.DeleteEvent.class, this::deleteResult);
        form.addListener(ResultForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteResult(ResultForm.DeleteEvent evt) {
        resultService.delete(evt.getResult());
        updateList();
        closeEditor();
    }

    private void saveResult(ResultForm.SaveEvent evt) {
        resultService.save(evt.getResult());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addNewReportButton = new Button("New Report", click -> addResult());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addNewReportButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addResult() {
        grid.asSingleSelect().clear();
        editResult(new Result());
    }

    private void configureGrid() {
        grid.addClassName("result-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("reportDate");
        grid.setColumns("confirmedZambia", "confirmedWorld","deathsZambia", "deathsWorld");
        grid.addColumn(new LocalDateRenderer<>(Result::getReportDate, "dd/MM/yyyy")).setSortable(true).setHeader("Reported date");



        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editResult(evt.getValue()));
    }

    private void editResult(Result result) {
        if (result == null) {
            closeEditor();
        } else {
            form.setResult(result);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setResult(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        //grid.setItems(resultService.findAll(filterText.getValue()));
        grid.setItems(resultService.findAll());
    }

}
