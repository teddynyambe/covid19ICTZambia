package zm.gov.health.dashboard.ui.views.symptoms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import zm.gov.health.dashboard.backend.entity.Symptom;
import zm.gov.health.dashboard.backend.service.SymptomService;
import zm.gov.health.dashboard.ui.MainLayout;

@Route(value = "symptoms", layout = MainLayout.class)
@PageTitle("COVID-19 Symptoms| Ministry of Health")
public class SymptomsListView  extends VerticalLayout {
    Grid<Symptom> grid = new Grid<>(Symptom.class);
    private SymptomService symptomService;
    private Button refreshButton = new Button("Refresh", click->updateList());

    public SymptomsListView(SymptomService symptomService) {
        this.symptomService = symptomService;
        addClassName("list-view");

        setSizeFull();
        configureGrid();
        updateList();
        add(refreshButton, grid);

    }

    private void updateList() {
        grid.setItems(symptomService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("symptom-list");
        grid.setSizeFull();
        grid.removeColumnByKey("id");
    }
}
