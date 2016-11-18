<img src="art/scoop_logo.png" width="190" align="right" hspace="20" />

Scoop [![Build Status](https://travis-ci.org/lyft/scoop.svg)](https://travis-ci.org/lyft/scoop)
=====

Scoop is a micro framework for building view based modular Android applications.

What do I get?
==============

1. [Router](#navigation). Object that allows you to navigate between screens and maintain backstack.
2. [View controllers](#viewcontroller). A new building block that you will use instead of Activity and Fragments.
3. [Layouts](#layout). A new building block that you will use instead of Activity and Fragments.
4. [Scoops ("Scopes")](#scoops). Hierarchical scopes that allows you organize your application dependencies and their lifespan.
5. [Transitions](#transitions). Animations played between moving from one view to another. We provide a set of basic transitions such as sliding right, left, etc. Additionally, you are able to create your own custom transitions.

Navigation
==========

Our navigation is based on lightweight objects called `Screen`.

`Screen` is a meta data object that specifies which view controller or layout you want to show and optional data you want to pass to your view controller or layout. You can think of them as Android Intents with a much simpler API.

```java
Screen screen = new MyScreen(new MyData("foo"));

router.goTo(screen);
```

The `Screen` class is extendable, and will provide you with the foundation for your navigation.
```java

@ViewController(MyController.class)
public class MyScreen extends Screen {
}
```

We provide 5 primary navigation methods:

1. `goTo` - Go to specified `Screen` and add it to the backstack.
2. `replaceWith` - Go to specified `Screen` and replace the top of the backstack with it.
3. `replaceAllWith` - Replace the backstack with a List of specified `Screen`s, and navigate to the last `Screen` in the list.
4. `resetTo` - Go to specified `Screen` and remove all `Screen`s after it from the backstack. If the specified screen is not in the backstack, remove all and make this `Screen` the top of the backstack.
5. `goBack` - Navigate to previous `Screen`.

`Router` does not render views. `Router` just emits an event that you can listen to in order to render the specified `Screen`. Within Scoop we provide the extensible view `UIContainer` that you can use to render view controllers and transitions.

ViewController
===============

This class manages a portion of the user interface as well as the interactions between that interface and the underlying data. Similar to an activity or a fragment, `ViewController` requires you to specify the layout id and has lifecycle methods. However, a `ViewController` lifecycle only has two states: "attached" and "detached".

You can also [use view binders like Butterknife](https://github.com/lyft/scoop/blob/master/scoop-basics/src/main/java/com/example/scoop/basics/ui/BaseViewController.java). So you don't need to explicitly call `ButterKnife.bind/unbind`.

```java

@ViewController(MyController.class)
public class MyScreen extends Screen {
}
```

```java
public class MyController extends ViewController {

    @Override
    protected int layoutId() {
        return R.layout.my;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnClick(R.id.do_smth)
    public void doSomething() {

    }
}
```

The big difference from Android fragments and activities is that in Scoop we don't keep the `ViewController` in memory after it was detached. Whenever you move to a new `Screen` the `ViewController` detaches and is disposed together with the view.

Layout
===============

A `Layout` annotation can be used similarly to `ViewController` annotation, and can accomplish the same goals. However, there is a higher degree of coupling between the controller and the view in this approach, so this implementation is generally not recommended.

```java
@Layout(R.layout.my)
public class MyScreen extends Screen {
}
```

```java
public class MyView extends FrameLayout {

    public LayoutView(Context context, AttributeSet attrs) {
      super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
      super.onAttachedToWindow();

      if (isInEditMode()) {
        return;
      }
    }

    @OnClick(R.id.do_smth)
    public void doSomething() {

    }
}
```

Scoops
=======

Scoop's namesake is the word "scope". You can think of app scopes as ice cream scoops: going deeper in the navigation stack is an extra scoop on the top with another flavor.

Primary purpose of scoops is providing access to named services. When you create a scoop you have to specify its parent (except root) and services.

```java
Scoop rootScoop = new Scoop.Builder("root")
        .service(MyService.SERVICE_NAME, myService)
        .build();

Scoop childScoop = new Scoop.Builder("child", rootScoop)
        .service(MyService.SERVICE_NAME, myService2)
        .service(OtherService.SERVICE_NAME, otherService)
        .build();
```

Internally, when trying to find a service within scoop, scoop will first try to find the service within itself. If the service is not within itself, scoop will iteratively go up in its scoop heirarchy to try to find the service.

```java
MyService service = childScoop.findService(MyService.SERVICE_NAME);
```

When a scoop is no longer needed you can destroy it, which will remove references to all its services and invoke destroy for all children.

```java
childScoop.destroy();
```

You are only required to create the root scoop manually. All child scoops will be created by `Router` whenever you advance in navigation. Created child scoops will be destroyed whenever you navigate to a previous item in the backstack.

To control child scoop creation you should extend `ScreenScooper` class. By default `ScreenScooper` only adds `Screen` to each child scoop.

Instead of adding individual services to your scoops, we recommend implementing dagger integration. In this case the only added service will be the dagger injector.

```java
public class DaggerScreenScooper extends ScreenScooper {

    @Override
    protected Scoop addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        DaggerInjector parentDagger = DaggerInjector.fromScoop(parentScoop);

        DaggerModule daggerModule = screen.getClass().getAnnotation(DaggerModule.class);

        if(daggerModule == null) {
            return scoopBuilder.service(DaggerInjector.SERVICE_NAME, parentDagger).build();
        }

        DaggerInjector screenInjector;

        try {
            Object module = daggerModule.value().newInstance();
            screenInjector = parentDagger.extend(module);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to instantiate module for screen: " + screen.getClass().getSimpleName(), e);
        }

        return scoopBuilder
                .service(DaggerInjector.SERVICE_NAME, screenInjector).build();
    }
}
```

Transitions
==========

Transitions are animations played between moving from one `ViewController` to another. Within `Scoop` we provide the following built in transitions:

1. Backward slide
2. Forward slide
3. Downward slide
4. Upward slide
5. Fade

To apply a transition you have to specify it for your `ViewController` by overriding `enterTransition()`/`exitTransition()` methods.

```java
public class MyController extends ViewController {

    @Override
    protected ScreenTransition enterTransition() {
        return new ForwardSlideTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new BackwardSlideTransition();
    }

    ...
}
```

If a transition is not specified, views will be swapped instantly.

You can also implement custom transitions by implementing the `ScreenTransition` interface.

```java
public class AutoTransition implements ScreenTransition {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void translate(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {

        Scene toScene = new Scene(root, to);

        android.transition.AutoTransition transition = new android.transition.AutoTransition();

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                transitionListener.onTransitionCompleted();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                transitionListener.onTransitionCompleted();
            }
            ...
        });

        TransitionManager.go(toScene, transition);
    }
}
```

Samples
=======

- [Basics](https://github.com/lyft/scoop/tree/master/scoop-basics) - App that showcases the basics of Scoop (navigation, parameter passing, dependency injection)
- Micro Lyft - advanced sample based on Lyft's public api to showcase real world usage [COMING SOON].


Questions
----------
For questions please use GitHub issues. Mark the issue with the "question" label.

Download
--------

```groovy
compile 'com.lyft:scoop:0.4.2'
```

Snapshots of development version are available in [Sonatype's `snapshots` repository](https://oss.sonatype.org/content/repositories/snapshots/com/lyft/scoop/).

License
-------

    Copyright (C) 2015 Lyft, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Special Thanks
==============

- [Logan Johnson](https://github.com/loganj)
- [Ray Ryan](https://github.com/rjrjr)
- [Pierre-Yves Ricau](https://github.com/pyricau)
- [Jake Wharton](https://github.com/JakeWharton)
