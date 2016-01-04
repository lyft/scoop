package com.example.scoop.basics.ui.wizardsample;

import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.FadeTransition;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Singleton;

//@InjectThat(EnterFirstNameController.Module.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class EnterFirstNameController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(EnterFirstNameController.class);
    }

    @dagger.Module(
    )
    public static class Module {

        @Provides
        @Singleton
        WizardSession provideWizardSession() {
            return new WizardSession();
        }
    }

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @Bind(R.id.first_name_edit_text)
    EditText firstNameEditText;

    @Inject
    public EnterFirstNameController(AppRouter appRouter, WizardSession wizardSession) {
        this.appRouter = appRouter;
        this.wizardSession = wizardSession;
    }

    @Override
    protected int layoutId() {
        return R.layout.wizard_enter_first_name;
    }

    @Override
    public void attach(View view) {
        super.attach(view);

        firstNameEditText.setText(wizardSession.firstName);
        Keyboard.showKeyboard(firstNameEditText);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.firstName = firstNameEditText.getText().toString();
        appRouter.goTo(EnterLastNameController.createScreen());
    }
}
