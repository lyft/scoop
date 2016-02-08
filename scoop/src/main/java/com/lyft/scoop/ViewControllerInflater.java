package com.lyft.scoop;

import android.view.View;
import android.view.ViewGroup;

public class ViewControllerInflater {

    static final int VIEW_CONTROLLER_TAG = 0x80000001;

    protected ViewController createViewController(Scoop scoop, Class<? extends ViewController> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("View controller " + clazz.getSimpleName() + " don't have default constructor");
        }
    }

    public View inflateView(
            Scoop scoop,
            Screen screen,
            ViewGroup viewGroup) {

        ViewController viewController = createViewController(scoop, screen.getController());
        viewController.setScoop(scoop);
        View view = scoop.inflate(getLayoutId(screen, viewController), viewGroup, false);
        bindViewControllerToView(view, viewController);
        return view;
    }

    private int getLayoutId(Screen screen, ViewController viewController) {
        Class localClass = getClass(screen);
        Layout layout = (Layout) localClass.getAnnotation(Layout.class);
        if (layout != null) {
            return layout.value();
        }
        return viewController.layoutId();
    }

    public <T> Class<T> getClass(Object object) {
        return (Class<T>) object.getClass();
    }

    private static void bindViewControllerToView(final View view, final ViewController viewController) {

        final View.OnAttachStateChangeListener viewAttachListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Scoop.viewBinder.bind(viewController, v);
                viewController.attach(view);
                viewController.setAttached(true);
                view.setTag(VIEW_CONTROLLER_TAG, viewController);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                viewController.detach(view);
                viewController.setAttached(false);
                view.setTag(VIEW_CONTROLLER_TAG, null);
                Scoop.viewBinder.unbind(viewController);
            }
        };

        view.addOnAttachStateChangeListener(viewAttachListener);
    }
}
