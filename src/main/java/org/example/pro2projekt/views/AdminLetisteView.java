package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Letiste;
import org.example.pro2projekt.service.LetisteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("adminLetiste")
@Route("/admin/letiste")
public class AdminLetisteView  extends VerticalLayout {
    @Autowired
    private LetisteService letisteService;
    private Grid<Letiste> letisteGrid = new Grid<>(Letiste.class,false);
    private List<Letiste> letisteList;
    public AdminLetisteView(){
        FlexLayout row0 = new FlexLayout();
        Div div = new Div();
        Button btnBack = new Button();
        btnBack.addClickListener(event ->{ getUI().ifPresent(ui -> ui.navigate(admin.class));});
        btnBack.setText("Zpět na hlavní stránku");
        div.add(btnBack);
        Div div2 = new Div();
        Button btnNew = new Button();
        btnNew.addClickListener(event -> {
            Dialog dialog = new Dialog();

            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text kapacita = new Text("Kapacita");
            NumberField kapacitaField = new NumberField();
            row1div1.add(kapacita,kapacitaField);
            row1div1.getStyle().set("padding-left","10%");
            Div row1div2 = new Div();
            Text mesto = new Text("Město");
            TextArea mestoField = new TextArea();
            row1div2.add(mesto,mestoField);
            row1div2.getStyle().set("padding-left","10%");
            row1.add(row1div1,row1div2);
            dialog.add(row1);


            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            Text nazev = new Text("Název");
            TextArea nazevField = new TextArea();
            row2div1.add(nazev,nazevField);
            row2div1.getStyle().set("padding-left","10%");
            Div row2div2 = new Div();
            Text stat = new Text("Stát");
            TextArea statField = new TextArea();
            row2div2.add(stat,statField);
            row2div2.getStyle().set("padding-left","10%");
            row2.add(row2div1,row2div2);
            dialog.add(row2);


            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Vytvoř", event3 ->{
                int kapacitaFina = kapacitaField.getValue().intValue();
                letisteService.createLetiste(kapacitaFina,mestoField.getValue(),nazevField.getValue(),statField.getValue());
                dialog.close();
                UI.getCurrent().getPage().reload();
            });
            uploadButton.getStyle().set("margin-left","40%");

            Button closeButton = new Button("Zavřít", event2 -> dialog.close());
            closeButton.getStyle().set("margin-left","10%");
            rowLast.add(closeButton,uploadButton);
            dialog.add(rowLast);
            dialog.open();
        });
        btnNew.setText("Nové letiště");
        btnNew.getStyle().set("margin-left","40%");
        div2.add(btnNew);
        div2.getStyle().set("margin-left","40%");
        row0.add(div,div2);
        add(row0);

        letisteGrid.addColumn(Letiste::getLetisteID).setHeader("ID");
        letisteGrid.addColumn(Letiste::getKapacita).setHeader("Kapacita");
        letisteGrid.addColumn(Letiste::getMesto).setHeader("Město");
        letisteGrid.addColumn(Letiste::getNazev).setHeader("Název");
        letisteGrid.addColumn(Letiste::getStat).setHeader("Stát");
        letisteGrid.addColumn(new ComponentRenderer<>(letiste -> {
            int letisteID = letiste.getLetisteID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text kapacita = new Text("Kapacita");
                NumberField kapacitaField = new NumberField();
                kapacitaField.setValue(Double.valueOf(String.valueOf(letiste.getKapacita())));
                row1div1.add(kapacita,kapacitaField);
                row1div1.getStyle().set("padding-left","10%");
                Div row1div2 = new Div();
                Text mesto = new Text("Město");
                TextArea mestoField = new TextArea();
                mestoField.setValue(letiste.getMesto());
                row1div2.add(mesto,mestoField);
                row1div2.getStyle().set("padding-left","10%");
                row1.add(row1div1,row1div2);
                dialog.add(row1);

                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text nazev = new Text("Název");
                TextArea nazevField = new TextArea();
                nazevField.setValue(letiste.getNazev());
                row2div1.add(nazev,nazevField);
                row2div1.getStyle().set("padding-left","10%");
                Div row2div2 = new Div();
                Text stat = new Text("Stát");
                TextArea statField = new TextArea();
                statField.setValue(letiste.getStat());
                row2div2.add(stat,statField);
                row2div2.getStyle().set("padding-left","10%");
                row2.add(row2div1,row2div2);
                dialog.add(row2);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 ->{
                    int kapacitaFina = kapacitaField.getValue().intValue();
                    letisteService.findByIdAndUpdate(letisteID,kapacitaFina,mestoField.getValue(),nazevField.getValue(),statField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                uploadButton.getStyle().set("margin-left","40%");
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left","10%");
                rowLast.add(closeButton,uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });

            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                letisteService.findByIdAndDelete(letisteID);
                UI.getCurrent().getPage().reload();
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        letisteGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom","20px")
                .set("box-shadow","5px 5px 5px grey");
        add(letisteGrid);
    }

    @PostConstruct
    private void init(){
        letisteList = letisteService.findAll();
        letisteGrid.setItems(letisteList);
    }
}
