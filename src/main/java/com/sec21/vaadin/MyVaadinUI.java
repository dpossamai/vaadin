package com.sec21.vaadin;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.sec21.vaadin.model.Client;
import com.sec21.vaadin.model.DocumentType;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

	private TextField nameField;
	
	private DateField dateField;
	
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.sec21.vaadin.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        BeanFieldGroup<Client> beanFieldGroup = new BeanFieldGroup<Client>(Client.class);
        
        this.nameField = new TextField("Nome:");
        this.nameField.addValidator(new StringLengthValidator(
        	    "The name must be 1-10 letters (was {0})",
        	    1, 10, true));
        nameField.setImmediate(true);
        nameField.setNullRepresentation("");
        nameField.setNullSettingAllowed(true);
        beanFieldGroup.bind(nameField, "name");
        
        this.dateField = new DateField("Data Nascimento:");
        beanFieldGroup.bind(dateField, "birthDay");
        beanFieldGroup.setBuffered(false);
        
        ComboBox comboBox = new ComboBox("TIPOS");
        comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        comboBox.setItemCaptionPropertyId("type");
        
        ArrayList<DocumentType> documents = new ArrayList<DocumentType>();
		documents.add(new DocumentType(1,"TIPO1"));
        BeanItemContainer<DocumentType> container = new BeanItemContainer<DocumentType>(DocumentType.class);
        
        container.addAll(documents);
        comboBox.setContainerDataSource(container);
        comboBox.setValue(documents.get(0));
        
        beanFieldGroup.bind(comboBox, "type");
        Client bean = new Client();
        bean.setType(new DocumentType(1, "TIPO1"));
		beanFieldGroup.setItemDataSource(bean);
        
        layout.addComponent(nameField);
        layout.addComponent(dateField);
        layout.addComponent(comboBox);
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            }
        });
        layout.addComponent(button);
    }

}
