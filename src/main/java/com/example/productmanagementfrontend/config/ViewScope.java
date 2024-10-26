package com.example.productmanagementfrontend.config;

import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Map;

public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            return objectFactory.getObject();
        }
        
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();
        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = objectFactory.getObject();
            viewMap.put(name, object);
            return object;
        }
    }

    @Override
    public Object remove(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            return context.getViewRoot().getViewMap().remove(name);
        }
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // Not supported
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}