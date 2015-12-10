<img src="art/scoop_logo.png" width="190" align="right" hspace="20" />

Scoop
=====

Scoop is a micro framework for building view based modular Android applications.

What do I get?
==============

1. [Router](#router). Object that allows you to navigate between screens and maintain backstack.
2. [View controllers](#view-controller). A new building block that you will use instead of Activity and Fragments.
3. [Scoops ("Scopes")](#scoops). Hierarchical scopes that allows you organize your application dependencies and their lifetime.
4. [Transitions](#transitions). Animations played between moving from one view to another. We provide a set of build in transitions like slide left and right and allow you to create your own.

Navigation
==========

Our navigation is based on lightweight objects called Screens.

Screen is a meta data that specifies which view controller you want to show and optional data you want to pass. You can think of them as Android Intents with a much simpler API.

```java
Screen screen = Screen.create(MyController.class)
  .data(new MyData("foo"));

router.goTo(screen);
```

We provide 5 primary navigation methods:

1. `goTo` - Go to specified screen and add it to the backstack.
2. `replaceWith` - Go to specified screen and replace the top of the backstack with it.
3. `resetTo` - Go to specified screen and remove all screens after it from the backstack. If the specified screen is not in the backstack, remove all and make this screen the top of the backstack.
4. `goBack` - navigate to previous screen.
5. `goUp` - Go to parent view controller specified in the ParentController attribute of the current view controller.

Router does not render views. Router just emits an event that you can listen to in order to render the specified screen. Within Scoop we provide the extensible view UIContainer that you can use to render view controllers and transitions.

View controller
===============

Class that manages a portion of the user interface as well as the interactions between that interface and the underlying data. Similar to activity and fragment, ViewController requires you to specify the layout and has a lifecycle. However its lifecycle consists of two states: "attached" and "detached".

It also automatically applies view binders like [Butterknife](https://github.com/JakeWharton/butterknife). So you don't need to explicitly call ButterKnife.bind.

```java
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class MyController extends ViewController {

    @Override
    protected int layoutId() {
        return R.layout.my;
    }

    @Override
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);

    }

    @OnClick(R.id.do_smth)
    public void doSomething() {

    }
}
```

The big difference from Android fragments and activities is that in Scoop we don't keep the view controller alive after it was detached. Whenever you move to a new screen the view controller detaches and disposes together with the view.

Scoops
=======

Scoop's namesake is the word "scope". You can think of app scopes as icecream scoops: going deeper in the navigation stack is an extra scoop on the top with another flavour.

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

When you try to find a service within scoop it first tries to find it within itself and if nothing was found it goes up in the scoop heirarhy.

```java
MyService service = childScoop.findService(MyService.SERVICE_NAME);
```

When a scoop is no longer needed you can destroy it, which will remove references to all its services and invoke destroy for all children.

```java
childScoop.destroy();
```

Typcally you only need to create the root scoop manually. All child scoops will be created by Router whenever you advance in navigation. Created child scoops will be destroyed whenever you navigate to a previous item in the backstack.

To control child scoop creation you should extend ScreenScooper class. By default screen scooper only adds Screen to each child scoop.

Instead of adding individual services to your scoops, we recommend implementing dagger integration. In this case the only added service will be the dagger injector.

```java
public class DaggerScreenScooper extends ScreenScooper {

    @Override
    protected Scoop.Builder addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        DaggerInjector parentDagger = DaggerInjector.fromScoop(parentScoop);

        // each controller have an attribute that points to dagger module
        ControllerModule controllerModule = screen.getController().getAnnotation(ControllerModule.class);

        DaggerInjector screenInjector;

        try {
            Object module = controllerModule.value().newInstance();
            screenInjector = parentDagger.extend(module);
        } catch (Throwable e) {
            throw new RuntimeException("Module not specified for " + screen.getController().getSimpleName(), e);
        }

        return scoopBuilder
                .service(DaggerInjector.SERVICE_NAME, screenInjector);
    }
}
```

Transitions
==========

Transitions are animations played between moving from one view controller to another. Within Scoop we provide the following built in transitions:

1. Backward slide
2. Forward slide
3. Downward slide
4. Upward slide
5. Fade

To apply a transition you have to specify it for your view controller using EnterTranstion/ExitTransition attributes.

```java
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class MyController extends ViewController {
  ...
}
```

If a transition is not specified, views will be swapped instantly.

You can also implement custom transitions by implementing the ScreenTransition interface.

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

- [Basics](https://github.com/lyft/scoop/tree/master/scoop-basics) - app that showcase basics of scoop (navigation, parameter passing, dependency injection)
- Micro Lyft - advanced sample based on Lyft's public api to showcase real world usage [COMING SOON].


Questions
----------
For questions please use GitHub issues. Mark the issue with the "question" label.

Download
--------

```groovy
compile 'com.lyft:scoop:0.1.0'
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
