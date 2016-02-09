package com.example.scoop.basics.ui.wizardsample;

import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class EnterLastNameController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(EnterLastNameController.class)
                .module(EnterLastNameController.Module.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = EnterLastNameController.class,
            addsTo = EnterFirstNameController.Module.class,
            library = true
    )
    public static class Module {
    }

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @Bind(R.id.last_name_edit_text)
    EditText lastNameEditText;

    @Inject
    public EnterLastNameController(AppRouter appRouter, WizardSession wizardSession) {
        this.appRouter = appRouter;
        this.wizardSession = wizardSession;
    }

    @Override
    protected int layoutId() {
        return R.layout.wizard_enter_last_name;
    }

    @Override
    public void attach(View view) {
        super.attach(view);

        lastNameEditText.setText(wizardSession.lastName);
        Keyboard.showKeyboard(lastNameEditText);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.lastName = lastNameEditText.getText().toString();
        appRouter.goTo(ConfirmationController.createScreen());
    }
}
