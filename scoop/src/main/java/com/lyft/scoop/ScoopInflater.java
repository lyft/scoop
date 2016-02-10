package com.lyft.scoop;

import android.view.View;
import android.view.ViewGroup;

public class ScoopInflater {

    static final int VIEW_CONTROLLER_TAG = 0x80000001;

    protected ViewController createViewController(Scoop scoop, Class<? extends ViewController> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("View controller " + clazz.getSimpleName() + " don't have default constructor");
        }
    }

    public View inflateViewController(
            Scoop scoop,
            Screen nextScreen,
            ViewGroup viewGroup) {

        if (nextScreen.getLayoutId() != null) {
            return scoop.inflate(nextScreen.getLayoutId(), viewGroup, false);
        }

        ViewController viewController = createViewController(scoop, nextScreen.getController());
        viewController.setScoop(scoop);
        View view = scoop.inflate(viewController.layoutId(), viewGroup, false);
        bindViewControllerToView(view, viewController);
        return view;
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
