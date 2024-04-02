package org.example.pro2projekt.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.Tag.H1;

@PageTitle("admin")
@Route("/admin")
public class admin extends VerticalLayout {

    public admin(){
        //componenty

        //header
        H1 text = new H1("Admin page");
        add(text);
        //nav

        //main


        //footer

        //
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
