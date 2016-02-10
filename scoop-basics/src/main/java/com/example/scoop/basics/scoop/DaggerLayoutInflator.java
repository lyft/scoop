package com.example.scoop.basics.scoop;

import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.LayoutInflator;

public class DaggerLayoutInflator extends LayoutInflator {

    @Override
    public View inflateLayout(
            Scoop scoop,
            Class<? extends View> viewClazz,
            ViewGroup viewGroup) {
        final View view = super.inflateLayout(scoop, viewClazz, viewGroup);
        DaggerInjector injector = DaggerInjector.fromScoop(scoop);
        injector.inject(view);
        return view;
    }
}
