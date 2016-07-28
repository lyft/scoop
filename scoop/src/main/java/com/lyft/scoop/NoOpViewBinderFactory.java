package com.lyft.scoop;

class NoOpViewBinderFactory implements ViewBinderFactory {

    @Override
    public ViewBinder create(Object object) {
        return new NoOpViewBinder();
    }
}
