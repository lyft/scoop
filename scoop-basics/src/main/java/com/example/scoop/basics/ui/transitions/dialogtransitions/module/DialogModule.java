package com.example.scoop.basics.ui.transitions.dialogtransitions.module;

import com.example.scoop.basics.MainActivityModule;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogDisableOnBackController;
import dagger.Provides;

@dagger.Module(
        injects = {
                DialogController.class,
                DialogDisableOnBackController.class,
        },
        addsTo = MainActivityModule.class,
        library = true
)
public class DialogModule {

    @Provides
    public DialogController provideDialogController(AppRouter appRouter) {
        return new DialogController(appRouter);
    }
}
