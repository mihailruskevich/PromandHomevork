package com.promand.ui;

import com.promand.components.MainComponent;
import com.promand.models.TableRecord;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        MainComponent mainComponent = new MainComponent(TableRecord.class);
        setContent(mainComponent);
    }
}
