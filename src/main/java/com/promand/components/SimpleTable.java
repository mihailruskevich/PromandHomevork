package com.promand.components;

import com.promand.containers.DataContainer;
import com.promand.models.Record;
import com.vaadin.data.Item;
import com.vaadin.ui.Table;

import java.lang.reflect.Field;
import java.util.Collection;

public class SimpleTable extends Table {

    private static final int DEFAULT_PAGE_LENGTH = 10;
    private DataContainer dataContainer;
    private Class recordType;

    public SimpleTable(Class recordType) {
        this.recordType = recordType;
        dataContainer = new DataContainer(recordType);
        setPageLength(DEFAULT_PAGE_LENGTH);
        setSelectable(true);
        setContainerDataSource(dataContainer);
    }

    public void addRecord(Record record) {
        if (recordType.equals(record.getClass())) {
            Object itemId = dataContainer.addItem();
            try {
                for (Field field : recordType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(record);
                    dataContainer.getContainerProperty(itemId, field.getName()).setValue(fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Incorrect record type");
        }
    }

    public void addRecord(Item item){
        Record record = createRecord(item);
        addRecord(record);
    }

    private Record createRecord(Item item){
        Record record = null;
        try {
            record = (Record)recordType.newInstance();
            for(Field field : recordType.getDeclaredFields()){
                field.setAccessible(true);
                Object itemToAdd = item.getItemProperty(field.getName()).getValue();
                field.set(record, itemToAdd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

}
