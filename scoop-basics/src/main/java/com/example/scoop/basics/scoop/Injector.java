package com.example.scoop.basics.scoop;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.ViewController;

public interface Injector {

    void configureScope(Scoop.Builder builder, Scoop parentScoop);

    ViewController getController();
}
