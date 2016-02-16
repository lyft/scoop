package com.example.scoop.basics.ui.wizardsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.example.scoop.basics.ui.wizardsample.controller.EnterFirstNameController;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
        injects = EnterFirstNameController.class,
        addsTo = DemosModule.class,
        library = true
)
public class EnterFirstNameModule {

    @Provides
    @Singleton
    WizardSession provideWizardSession() {
        return new WizardSession();
    }
}
