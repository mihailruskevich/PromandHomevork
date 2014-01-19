package com.promand.containers;

import com.google.gwt.thirdparty.guava.common.base.Defaults;
import com.vaadin.data.util.IndexedContainer;

import java.lang.reflect.Field;

public class DataContainer extends IndexedContainer {

    public DataContainer(Class recordType) {
        for(Field field : recordType.getDeclaredFields()){
            addContainerProperty(field.getName(), field.getType(), Defaults.defaultValue(field.getClass())); // DEFAULT
        }
    }
}
