package org.example.pro2projekt.controller;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.example.pro2projekt.views.login;

public class ConfigureUIServiceListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiInitEvent -> {
            final UI ui = uiInitEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter);
        });
    }
    private void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        if(!login.class.equals(beforeEnterEvent.getNavigationTarget())&& !SecurityUtils.isUserLoggedIn()){
            beforeEnterEvent.rerouteTo(login.class);
        }
    }
}
