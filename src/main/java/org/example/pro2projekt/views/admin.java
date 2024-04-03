package org.example.pro2projekt.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.example.pro2projekt.objects.Pasazer;
//import com.vaadin.flow.component.crud.Crud;


@PageTitle("admin")
@Route("/admin")
public class admin extends VerticalLayout {
   // Crud<Pasazer> pasazers;
    public admin(){
        //componenty

        //header
        H1 text = new H1("Admin page");
        add(text);
        //nav

        //todo show stats


        //todo crud pasazer
     //   pasazers = new Crud<>(Pasazer.class,createEditor());

        //todo crud letadla


        //main

        //footer

        //
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
