package com.promand.components;

import com.promand.models.Record;
import com.vaadin.ui.*;

public class MainComponent extends HorizontalLayout{

    private Class recordType;
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();

    private SimpleTable leftTable;
    private SimpleTable rightTable;
    private SimpleTable checkoutTable;
    private InputForm inputForm;

    private RecordMover recordMover;
    private Button addButton = new Button("Add");
    private Button removeButton = new Button("Remove");
    private Button checkoutButton = new Button("Checkout");
    private Window checkoutWindow = new Window();

    public MainComponent(Class recordType) {
        this.recordType = recordType;
        initLeftLayout();
        initRightLayout();
        recordMover = new RecordMover(leftTable, rightTable);
        addComponent(leftLayout);
        addComponent(recordMover);
        addComponent(rightLayout);


    }

    private void initLeftLayout(){
        leftTable = new SimpleTable(recordType);
        inputForm = new InputForm(recordType);
        leftLayout.addComponent(leftTable);
        leftLayout.addComponent(inputForm);
        leftLayout.addComponent(addButton);
        leftLayout.addComponent(removeButton);
        addButton.setWidth("6em");
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Record record = inputForm.getRecord();
                if(!record.isEmpty()){
                    leftTable.addRecord(record);
                    inputForm.clearFields();
                } else {
                    Notification.show("Input some text", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });
        removeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Object itemId = leftTable.getValue();
                if(!leftTable.getContainerDataSource().removeItem(itemId)) {
                    Notification.show("Select item in left table", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });

    }

    private void initRightLayout(){
        rightTable = new SimpleTable(recordType);
        rightLayout.addComponent(rightTable);
        rightLayout.addComponent(checkoutButton);

        checkoutTable = new SimpleTable(recordType);
        checkoutTable.setContainerDataSource(rightTable.getContainerDataSource());
        checkoutWindow.setModal(true);
        checkoutWindow.setContent(checkoutTable);
        checkoutButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ((UI)getParent()).addWindow(checkoutWindow);
            }
        });
    }

}
