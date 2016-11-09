package com.lyft.scoop;

import android.view.View;
import android.view.ViewGroup;

public class ViewControllerInflater {

    protected ViewController createViewController(Scoop scoop, Class<? extends ViewController> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("View controller " + clazz.getSimpleName() + " don't have default constructor");
        }
    }

    public ViewController inflateViewController(
            Scoop scoop,
            Class<? extends ViewController> viewControllerClazz,
            ViewGroup viewGroup) {

        ViewController viewController = createViewController(scoop, viewControllerClazz);
        viewController.setScoop(scoop);
        return viewController;
    }



    static void bindViewControllerToView(final View view, final ViewController viewController) {

        final View.OnAttachStateChangeListener viewAttachListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                viewController.attach(view);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                viewController.detach(view);
            }
        };

        view.addOnAttachStateChangeListener(viewAttachListener);
    }
}
