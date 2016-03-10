package com.example.scoop.basics.ui.wizardsample.controller;

import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.Keyboard;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.example.scoop.basics.ui.wizardsample.screen.ConfirmationScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class EnterLastNameController extends ViewController {

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
    public void onAttach() {
        super.onAttach();

        lastNameEditText.setText(wizardSession.lastName);
        Keyboard.showKeyboard(lastNameEditText);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.lastName = lastNameEditText.getText().toString();
        appRouter.goTo(new ConfirmationScreen());
    }
}
