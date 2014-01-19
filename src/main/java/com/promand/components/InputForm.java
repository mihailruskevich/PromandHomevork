package com.promand.components;

import com.promand.models.Record;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InputForm extends VerticalLayout{

    private List<TextField> textFieldList = new ArrayList<TextField>();
    Class recordType;

    public InputForm(Class recordType) {
        this.recordType = recordType;
        for(Field field : recordType.getDeclaredFields()){
            TextField textField = new TextField(field.getName());
            textFieldList.add(textField);
            addComponent(textField);
        }
    }

    public Record getRecord(){
        Object record = null;
        try {
            record = recordType.newInstance();
            int textIndex = 0;
            for(Field field : recordType.getDeclaredFields()){
                field.setAccessible(true);
                field.set(record, textFieldList.get(textIndex).getValue());
                textIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Record)record;
    }
    public void clearFields(){
        for(TextField textField: textFieldList){
            textField.setValue("");
        }
    }
}
