package org.example.pro2projekt.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Client")
@Route("client")
public class mainClient extends VerticalLayout {
    public mainClient(){
        //componenty

        //header
        H1 text = new H1("Client page");
        add(text);
        //nav

        //main


        //footer

        //
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
