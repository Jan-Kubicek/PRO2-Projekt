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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

@PageTitle("adminClients")
@Route("/admin/clients")
public class AdminClientsView extends VerticalLayout {
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private DispecerService dispecerService;
    private Grid<Pasazer> pasazerGrid = new Grid<>(Pasazer.class,false);
    private Grid<Dispecer> dispecrGrid = new Grid<>(Dispecer.class,true);
    private List<Pasazer> pasazerList;
    private List<Dispecer> dispecerList;
    Text numbber;
    public AdminClientsView(){
        pasazerGrid.addColumn(Pasazer::getPasazerID).setHeader("ID");
        pasazerGrid.addColumn(Pasazer::getJmeno).setHeader("Jmeno");
        pasazerGrid.addColumn(Pasazer::getPrijmeni).setHeader("Prijmeni");
        pasazerGrid.addColumn(Pasazer::getEmail).setHeader("Email");
        pasazerGrid.addColumn(Pasazer::getRodne_cislo).setHeader("Rodné číslo");
        pasazerGrid.addColumn(Pasazer::getTelefoni_cislo).setHeader("Telefonní číslo");
        pasazerGrid.addColumn(new ComponentRenderer<>(pasazer -> {
            int pasazerId = pasazer.getPasazerID();
            Button editButton = new Button("Edit");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();
                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text jmeno = new Text("Jméno");
                TextArea jmenoField = new TextArea();
                jmenoField.setValue(pasazer.getJmeno());
                row1div1.add(jmeno,jmenoField);
                row1div1.getStyle().set("padding-left","10%");

                Div row1div2 = new Div();
                Text prijmeni = new Text("Přijmení");
                TextArea prijmeniField = new TextArea();
                prijmeniField.setValue(pasazer.getPrijmeni());
                row1div2.add(prijmeni,prijmeniField);
                row1div2.getStyle().set("padding-left","10%");

                row1.add(row1div1,row1div2);
                dialog.add(row1);


                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 ->{

                });
                uploadButton.getStyle().set("padding-left","20%");
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                rowLast.add(closeButton,uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });

            Button deleteButton = new Button("Delete");
            deleteButton.addClickListener(event -> {
                pasazerService.findByIdAndDelete(pasazerId);
                UI.getCurrent().getPage().reload();
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Actions");




        add(pasazerGrid);
        add(dispecrGrid);
    }

    @PostConstruct
    private void init(){
        pasazerList = pasazerService.findAll();
        pasazerGrid.setItems(pasazerList);
        dispecerList = dispecerService.findAll();
        dispecrGrid.setItems(dispecerList);
    }
}
