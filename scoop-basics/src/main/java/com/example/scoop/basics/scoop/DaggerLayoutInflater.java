package com.example.scoop.basics.scoop;

import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.LayoutInflater;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.Screen;

public class DaggerLayoutInflater extends LayoutInflater {

    @Override
    public View inflateView(
            Scoop scoop,
            Screen screen,
            ViewGroup viewGroup) {

        View view = super.inflateView(scoop, screen, viewGroup);
        DaggerInjector injector = DaggerInjector.fromScoop(scoop);
        injector.inject(view);
        return view;
    }
}
