package com.example.scoop.basics.scoop;

import android.view.View;
import com.lyft.scoop.Scoop;
//import dagger.ObjectGraph;

public class Dagger {

    public static final String SERVICE_NAME = "dagger";

    public static <T> T fromScoop(Scoop scoop) {
        return scoop.findService(SERVICE_NAME);
    }

    public static Dagger fromView(View view) {
        return Scoop.fromView(view).findService(SERVICE_NAME);
    }
}
