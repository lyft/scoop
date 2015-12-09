package com.example.scoop.basics.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.security.InvalidParameterException;

public final class Keyboard {
    private static InputMethodManager getInputManager(Context paramContext) {
        return (InputMethodManager) paramContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static void hideKeyboard(Context paramContext, IBinder paramIBinder) {
        getInputManager(paramContext).hideSoftInputFromWindow(paramIBinder, 0);
    }

    public static void hideKeyboard(View paramView) {
        hideKeyboard(paramView.getContext(), paramView.getWindowToken());
    }

    public static void showKeyboard(final View paramView) {
        paramView.requestFocus();
        paramView.post(new Runnable() {
            @Override
            public void run() {
                getInputManager(paramView.getContext()).showSoftInput(paramView, 0);
            }
        });
    }

    public static void showOnStart(View view) {
        setSoftInputMode(view, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
    }

    public static void hideOnStart(View view) {
        setSoftInputMode(view, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private static void setSoftInputMode(View view, int inputMode) {
        Context context = view.getContext();
        getWindow(context).setSoftInputMode(inputMode);
    }

    private static Window getWindow(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return activity.getWindow();
        } else if (context instanceof ContextWrapper) {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            return getWindow(contextWrapper.getBaseContext());
        } else {
            throw new InvalidParameterException("Cannot find activity context");
        }
    }
}