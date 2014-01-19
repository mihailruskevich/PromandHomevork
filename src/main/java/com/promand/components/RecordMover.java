package com.promand.components;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class RecordMover extends VerticalLayout {

    private Button moveToLeftButton = new Button("<<");
    private Button moveToRightButton = new Button(">>");

    public RecordMover(final SimpleTable leftTable, final SimpleTable rightTable) {
        addComponent(moveToRightButton);
        addComponent(moveToLeftButton);

        moveToLeftButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                moveRecord(rightTable, leftTable);
            }
        });
        moveToRightButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                moveRecord(leftTable, rightTable);
            }
        });
    }

    private void moveRecord(SimpleTable fromTable, SimpleTable toTable){
        Object itemId = fromTable.getValue();
        Container fromContainer = fromTable.getContainerDataSource();
        Item item = fromContainer.getItem(itemId);
        if(item != null){
            toTable.addRecord(item);
            fromContainer.removeItem(itemId);
        } else {
            Notification.show("Select item appropriate", Notification.Type.HUMANIZED_MESSAGE);
        }
    }

}
