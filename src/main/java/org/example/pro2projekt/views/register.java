package org.example.pro2projekt.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("register")
@Route("/register")
public class register extends VerticalLayout {
    ComboBox<String> jmenoField, prijmeniField;
    ComboBox<Integer> rodnecisloField, telefonniCisloField;
    PasswordField hesloField;
    Button btnRegister, btnBack;
    RadioButtonGroup<String> pohlavi;
    public void register(){
            //todo rest
    }
}
