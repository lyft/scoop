package com.lyft.scoop;

import android.view.View;
import android.view.ViewGroup;

public class LayoutInflator {

    static final int VIEW_CONTROLLER_TAG = 0x80000001;

    protected int getLayoutId(Class<? extends View> clazz) {
        try {
            Layout layout = clazz.getAnnotation(Layout.class);
            return layout.value();
        } catch (Throwable e) {
            throw new RuntimeException("View controller " + clazz.getSimpleName() + " don't have default constructor");
        }
    }

    public View inflateLayout(
            Scoop scoop,
            Class<? extends View> viewClazz,
            ViewGroup viewGroup) {

        int layoutId = getLayoutId(viewClazz);
        View view = scoop.inflate(layoutId, viewGroup, false);
        bindView(view);
        return view;
    }

    private static void bindView(final View view) {

        final View.OnAttachStateChangeListener viewAttachListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Scoop.viewBinder.bind(view, v);
                view.setTag(VIEW_CONTROLLER_TAG, v);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                view.setTag(VIEW_CONTROLLER_TAG, null);
                Scoop.viewBinder.unbind(v);
            }
        };

        view.addOnAttachStateChangeListener(viewAttachListener);
    }
}
