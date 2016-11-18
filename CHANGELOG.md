Change Log
==========

Version 0.4.2 *(2016-11-17)*
----------------------------

Fix release artifacts.

Version 0.4.1 *(2016-11-17)*
----------------------------

Fix bug where incorrect exit transition was used.

Version 0.4.0 *(2016-11-9)*
----------------------------

Add support for setting transitions in ViewController.
Remove annotation support for transitions.

Version 0.3.18 *(2016-7-28)*
----------------------------

Added improvements to view binder.

Version 0.3.17 *(2016-5-3)*
----------------------------

Moving ScreenScooper into the UIContainer to ensure active screen is always used.

Version 0.3.16 *(2016-5-3)*
----------------------------

Ensure getView is available during onAttach in ViewController.

Version 0.3.15 *(2016-5-2)*
----------------------------

Adding butterknife binding to viewcontroller.

Version 0.3.14 *(2016-5-2)*
----------------------------

Only call detach in ViewControllers when attach has been called first.

Version 0.3.13 *(2016-4-29)*
----------------------------

Adding scoop peek to router.


Version 0.3.12 *(2016-4-4)*
----------------------------

Removing ConcurrentModificationException bug in Scoop

Version 0.3.11 *(2016-3-31)*
----------------------------

Migrated scoop creation from Router to UI layer

Version 0.3.10 *(2016-3-22)*
----------------------------

Eliminating memory leak in scoop.destroy()

Version 0.3.9 *(2016-3-16)*
----------------------------

Fixing replaceAllWith() in router

Version 0.3.8 *(2016-3-16)*
----------------------------

Adding support for transitions to null views


Version 0.3.7 *(2016-3-11)*
----------------------------

Adding getView() to ViewController

Removing default screen from Router's onCreate()

Version 0.3.6 *(2016-3-9)*
----------------------------

Updating fromController() to type cast screen.

Version 0.3.5 *(2016-3-2)*
----------------------------

Updating tag to match release.

Version 0.3.4 *(2016-3-2)*
----------------------------

Fixing bug where Dagger Compiler was being compiled instead of provided.

Version 0.3.3 *(2016-2-29)*
----------------------------

Adding support for using a Router without a default screen.

Version 0.3.2 *(2016-2-29)*
----------------------------

Lowering compile sdk to Android 22 to support applications with sdk's below 23.

Version 0.3.1 *(2016-2-25)*
----------------------------

Adding support for module extension in nested layouts.

Version 0.3.0 *(2016-2-18)*
----------------------------

Adding layout annotation support.

Screens are now extendable.

Controller, layout, and animation annotations are now located on top of the screen.

Parameters are passed through a screen's constructor.

Version 0.2.2 *(2016-2-11)*
----------------------------

Enhancement to disable all input events while the screens are transitioning.

Version 0.2.0 *(2016-2-05)*
----------------------------

Removed parent navigation.

Queued transitions.

Switched to Android library.

Disable click events on screens that are transitioning.

Version 0.1.0 *(2015-12-09)*
----------------------------

Initial release.
