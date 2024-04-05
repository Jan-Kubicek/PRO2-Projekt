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
import org.example.pro2projekt.objects.Spolecnost;
import org.example.pro2projekt.service.SpolecnostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("adminSpolecnost")
@Route("/admin/spolecnost")
public class AdminSpolecnostView extends VerticalLayout {
    @Autowired
    private SpolecnostService spolecnostService;
    private Grid<Spolecnost> spolecnostGrid = new Grid<>(Spolecnost.class,false);
    private List<Spolecnost> spolecnostList;
    public AdminSpolecnostView(){
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
            Text nazev = new Text("Název");
            TextArea nazevField = new TextArea();
            row1div1.add(nazev,nazevField);
            row1div1.getStyle().set("padding-left","10%");
            Div row1div2 = new Div();
            Text sidlo = new Text("Sídlo");
            TextArea sidloField = new TextArea();
            row1div2.add(sidlo,sidloField);
            row1div2.getStyle().set("padding-left","10%");
            row1.add(row1div1,row1div2);
            dialog.add(row1);


            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Vytvoř", event3 ->{
                spolecnostService.createSpolecnost(nazevField.getValue(),sidloField.getValue());
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
        btnNew.setText("Nové společnost");
        btnNew.getStyle().set("margin-left","40%");
        div2.add(btnNew);
        div2.getStyle().set("margin-left","40%");
        row0.add(div,div2);
        add(row0);

        spolecnostGrid.addColumn(Spolecnost::getSpolecnostID).setHeader("ID");
        spolecnostGrid.addColumn(Spolecnost::getNazev).setHeader("Název");
        spolecnostGrid.addColumn(Spolecnost::getSidlo).setHeader("Sídlo");
        spolecnostGrid.addColumn(new ComponentRenderer<>(spolecnost -> {
            int spolecnostId = spolecnost.getSpolecnostID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text nazev = new Text("Název");
                TextArea nazevField = new TextArea();
                nazevField.setValue(spolecnost.getNazev());
                row1div1.add(nazev,nazevField);
                row1div1.getStyle().set("padding-left","10%");
                Div row1div2 = new Div();
                Text sidlo = new Text("Sídlo");
                TextArea sidloField = new TextArea();
                sidloField.setValue(spolecnost.getSidlo());
                row1div2.add(sidlo,sidloField);
                row1div2.getStyle().set("padding-left","10%");
                row1.add(row1div1,row1div2);
                dialog.add(row1);


                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 ->{
                    spolecnostService.findByIdAndUpdate(spolecnostId,nazevField.getValue(),sidloField.getValue());
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
                spolecnostService.finByIdAndDelete(spolecnostId);
                UI.getCurrent().getPage().reload();
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        spolecnostGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom","20px")
                .set("box-shadow","5px 5px 5px grey");
        add(spolecnostGrid);
    }

    @PostConstruct
    private void init(){
        spolecnostList = spolecnostService.findAll();
        spolecnostGrid.setItems(spolecnostList);
    }
}
