package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("adminClients")
@Route("/admin/clients")
public class AdminClientsView extends VerticalLayout {
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private DispecerService dispecerService;
    private Grid<Pasazer> pasazerGrid = new Grid<>(Pasazer.class,true);
    private Grid<Dispecer> dispecrGrid = new Grid<>(Dispecer.class,true);
    private List<Pasazer> pasazerList;
    private List<Dispecer> dispecerList;
    Text numbber;
    public AdminClientsView(){
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
