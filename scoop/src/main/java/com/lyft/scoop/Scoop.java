package com.lyft.scoop;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class Scoop {

    private String name;
    private Scoop parent;
    private Map<String, Object> services;
    private Map<String, Scoop> children = new LinkedHashMap<>();
    private boolean destroyed;

    private Scoop(String name, Scoop parent, Map<String, Object> services) {
        this.name = name;
        this.parent = parent;
        this.services = services;

        if (parent != null) {
            parent.children.put(name, this);
        }
    }

    public void destroy() {
        final Set<Map.Entry<String, Scoop>> entries = this.children.entrySet();

        final Set<Map.Entry<String, Scoop>> entriesCopy = new HashSet<>(entries);
        for (Map.Entry<String, Scoop> entry : entriesCopy) {
            entry.getValue().destroy();
        }

        this.destroyed = true;

        if (parent != null) {
            parent.children.remove(this.getName());
        }
    }

    boolean isDestroyed() {
        return destroyed;
    }

    public <T> T findService(String serviceName) {
        return findService(this, serviceName);
    }

    private <T> T findService(Scoop scoop, String serviceName) {
        Object service = scoop.services.get(serviceName);

        if (service != null) {
            return (T) service;
        } else {
            if (scoop.parent != null) {
                return findService(scoop.parent, serviceName);
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public Scoop getParent() {
        return parent;
    }

    public Scoop findChild(String childName) {
        return children.get(childName);
    }

    public static class Builder {

        private final Scoop parent;

        private final Map<String, Object> services = new LinkedHashMap<String, Object>();
        private String name;

        public Builder(String name, Scoop scoop) {
            this.name = name;
            parent = scoop;
        }

        public Builder(String name) {
            this.name = name;
            parent = null;
        }

        public Builder service(String name, Object service) {
            services.put(name, service);

            return this;
        }

        public Scoop build() {
            return new Scoop(name, parent, services);
        }
    }

    public static Scoop fromView(View view) {
        if (view == null) {
            return null;
        }
        return fromContext(view.getContext());
    }

    public static Scoop fromContext(Context context) {

        if (!(context instanceof HaveScoop)) {
            throw new RuntimeException("View does not implement interface: HaveScoop");
        }

        HaveScoop haveScoop = (HaveScoop) context;

        return haveScoop.getScoop();
    }

    public LayoutInflater inflater(Context context) {
        return LayoutInflater.from(context).cloneInContext(createContext(context));
    }

    public Context createContext(Context context) {
        return new ScoopContextWrapper(context, this);
    }

    public View inflate(int layoutId, ViewGroup viewGroup, boolean attachToRoot) {
        return inflater(viewGroup.getContext()).inflate(layoutId, viewGroup, attachToRoot);
    }

    protected interface HaveScoop {

        Scoop getScoop();
    }

    private static class ScoopContextWrapper extends ContextWrapper implements HaveScoop {

        private final Scoop scoop;

        public ScoopContextWrapper(Context context, Scoop scoop) {
            super(context);
            this.scoop = scoop;
        }

        public Scoop getScoop() {
            return this.scoop;
        }
    }
}
