package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.example.pro2projekt.objects.Letadlo;
import org.example.pro2projekt.objects.LetadloStats;
import org.example.pro2projekt.service.LetadloService;
import org.example.pro2projekt.service.LetadloStatsService;
import org.example.pro2projekt.service.SpolecnostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("adminLetadla")
@Route("/admin/letadla")
@RolesAllowed("DISPECER")
public class AdminLetadlaView extends VerticalLayout {
    @Autowired
    private LetadloService letadloService;
    @Autowired
    private SpolecnostService spolecnostService;
    @Autowired
    private LetadloStatsService letadloStatsService;
    private Grid<Letadlo> letadloGrid = new Grid<>(Letadlo.class, false);
    private Grid<LetadloStats> statsGrid = new Grid<>(LetadloStats.class, false);
    private List<Letadlo> letadloList;
    private List<LetadloStats> customList;

    public AdminLetadlaView() {
        FlexLayout row0 = new FlexLayout();
        Div div = new Div();
        Button btnBack = new Button();
        btnBack.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(Admin.class));
        });
        btnBack.setText("Zpět na hlavní stránku");
        Icon icon1 = new Icon(VaadinIcon.ARROW_BACKWARD);
        btnBack.setIcon(icon1);
        div.add(btnBack);
        Div div2 = new Div();
        Button btnNew = new Button();
        btnNew.addClickListener(event -> {
            Dialog dialog = new Dialog();

            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text nazev = new Text("Název letadla");
            TextArea nazevField = new TextArea();
            row1div1.add(nazev, nazevField);
            row1div1.getStyle().set("padding-left", "10%");

            Div row1div2 = new Div();
            Text vyrobce = new Text("Výrobce");
            TextArea vyrobceField = new TextArea();
            row1div2.add(vyrobce, vyrobceField);
            row1div2.getStyle().set("padding-left", "10%");
            row1.add(row1div1, row1div2);
            dialog.add(row1);

            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            Text typ = new Text("Typ");
            TextArea typField = new TextArea();
            row2div1.add(typ, typField);
            row2div1.getStyle().set("padding-left", "10%");

            Div row2div2 = new Div();
            Text rokvyr = new Text("Rok výroby");
            TextArea rokVyrField = new TextArea();
            row2div2.add(rokvyr, rokVyrField);
            row2div2.getStyle().set("padding-left", "10%");
            row2.add(row2div1, row2div2);
            dialog.add(row2);

            FlexLayout row3 = new FlexLayout();
            Div row3div1 = new Div();
            Text stav = new Text("Stav");
            TextArea stavField = new TextArea();
            row3div1.add(stav, stavField);
            row3div1.getStyle().set("padding-left", "10%");
            Div row3div2 = new Div();
            Text spolecnost = new Text("Společnost");
            ComboBox<Integer> spolecnostField = new ComboBox<>();
            spolecnostField.setItems(spolecnostService.findAllIndexes());
            row3div2.add(spolecnost, spolecnostField);
            row3div2.getStyle().set("padding-left", "10%");
            row3.add(row3div1, row3div2);
            dialog.add(row3);

            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Vytvoř", event3 -> {
                letadloService.createNewLetadlo(nazevField.getValue(), vyrobceField.getValue(), typField.getValue(), rokVyrField.getValue(), stavField.getValue(), spolecnostField.getValue());
                dialog.close();
                UI.getCurrent().getPage().reload();
            });
            uploadButton.getStyle().set("margin-left", "40%");
            Icon icon10 = new Icon(VaadinIcon.CHECK);
            uploadButton.setIcon(icon10);

            Button closeButton = new Button("Zavřít", event2 -> dialog.close());
            closeButton.getStyle().set("margin-left", "10%");
            Icon icon11 = new Icon(VaadinIcon.CLOSE);
            closeButton.setIcon(icon11);
            rowLast.add(closeButton, uploadButton);
            dialog.add(rowLast);
            dialog.open();
        });
        btnNew.setText("Nové letadlo");
        btnNew.getStyle().set("margin-left", "40%");
        Icon icon2 = new Icon(VaadinIcon.PLUS_CIRCLE);
        btnNew.setIcon(icon2);
        div2.add(btnNew);
        div2.getStyle().set("margin-left", "40%");
        row0.add(div, div2);
        add(row0);

        letadloGrid.addColumn(Letadlo::getLetadloID).setHeader("ID");
        letadloGrid.addColumn(Letadlo::getNazev).setHeader("Název");
        letadloGrid.addColumn(Letadlo::getStav).setHeader("Stav");
        letadloGrid.addColumn(Letadlo::getRok_vyroby).setHeader("Rok výroby");
        letadloGrid.addColumn(Letadlo::getTyp).setHeader("Typ");
        letadloGrid.addColumn(Letadlo::getVyrobce).setHeader("Vyrobce");
        letadloGrid.addColumn(new ComponentRenderer<>(letadlo -> {
            int letadloId = letadlo.getLetadloID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text nazev = new Text("Název letadla");
                TextArea nazevField = new TextArea();
                nazevField.setValue(letadlo.getNazev());
                row1div1.add(nazev, nazevField);
                row1div1.getStyle().set("padding-left", "10%");

                Div row1div2 = new Div();
                Text vyrobce = new Text("Výrobce");
                TextArea vyrobceField = new TextArea();
                vyrobceField.setValue(letadlo.getVyrobce());
                row1div2.add(vyrobce, vyrobceField);
                row1div2.getStyle().set("padding-left", "10%");
                row1.add(row1div1, row1div2);
                dialog.add(row1);

                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text typ = new Text("Typ");
                TextArea typField = new TextArea();
                typField.setValue(letadlo.getTyp());
                row2div1.add(typ, typField);
                row2div1.getStyle().set("padding-left", "10%");

                Div row2div2 = new Div();
                Text rokvyr = new Text("Rok výroby");
                TextArea rokVyrField = new TextArea();
                rokVyrField.setValue(letadlo.getRok_vyroby());
                row2div2.add(rokvyr, rokVyrField);
                row2div2.getStyle().set("padding-left", "10%");
                row2.add(row2div1, row2div2);
                dialog.add(row2);

                FlexLayout row3 = new FlexLayout();
                Div row3div1 = new Div();
                Text stav = new Text("Stav");
                TextArea stavField = new TextArea();
                stavField.setValue(letadlo.getStav());
                row3div1.add(stav, stavField);
                row3div1.getStyle().set("padding-left", "25%");
                row3.add(row3div1);
                dialog.add(row3);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 -> {
                    letadloService.findByIdAndUpdate(letadlo.getLetadloID(), nazevField.getValue(), rokVyrField.getValue(), stavField.getValue(), typField.getValue(), vyrobceField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                Icon icon10 = new Icon(VaadinIcon.CHECK);
                uploadButton.setIcon(icon10);
                uploadButton.getStyle().set("margin-left", "40%");

                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left", "10%");
                Icon icon11 = new Icon(VaadinIcon.CLOSE);
                closeButton.setIcon(icon11);
                rowLast.add(closeButton, uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });
            Icon icon3 = new Icon(VaadinIcon.ARROW_CIRCLE_UP);
            editButton.setIcon(icon3);
            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                letadloService.findByIdAndDelete(letadloId);
                UI.getCurrent().getPage().reload();
            });
            Icon icon4 = new Icon(VaadinIcon.CLOSE_CIRCLE);
            deleteButton.setIcon(icon4);
            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        letadloGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom", "20px")
                .set("box-shadow", "5px 5px 5px grey");

        add(letadloGrid);

        statsGrid.addColumn(LetadloStats::getVyrobce).setHeader("Vyrobce");
        statsGrid.addColumn(LetadloStats::getNazev).setHeader("Název");
        statsGrid.addColumn(new ComponentRenderer<>(letadloStat -> new Span(Integer.toString(letadloStat.getPocet()))))
                .setHeader("Počet");
        statsGrid.addColumn(new ComponentRenderer<>(letadloStat -> {
            double percent = ((double) letadloStat.getPocet() / letadloStat.getAllPlanes()) * 100;
            return new Span(String.format("%.2f %%", percent));
        })).setHeader("% z celku");
        statsGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom", "20px")
                .set("box-shadow", "5px 5px 5px grey");
        add(statsGrid);
        FlexLayout footer = new FlexLayout();
        Text text1 = new Text("@2024");
        Text text2 = new Text("Jan Kubíček");
        Div div1 = new Div();
        div1.add(text1);
        div1.getStyle().set("margin-left", "10%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        Div div3 = new Div();
        div3.add(text2);
        div3.getStyle().set("margin-left", "70%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        footer.getStyle().set("border-top", "2px solid lightblue").set("width", "100%");
        footer.add(div1, div3);
        add(footer);
    }

    @PostConstruct
    private void init() {
        customList = letadloStatsService.groupByVyrobces();
        statsGrid.setItems(customList);
        letadloList = letadloService.findAll();
        letadloGrid.setItems(letadloList);
    }
}
