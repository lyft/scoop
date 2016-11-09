package com.example.scoop.basics.ui.wizardsample.controller;

import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.Keyboard;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.example.scoop.basics.ui.wizardsample.screen.EnterLastNameScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class EnterFirstNameController extends BaseViewController {

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @BindView(R.id.first_name_edit_text)
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
    public void onAttach() {
        super.onAttach();

        firstNameEditText.setText(wizardSession.firstName);
        Keyboard.showKeyboard(firstNameEditText);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.firstName = firstNameEditText.getText().toString();
        appRouter.goTo(new EnterLastNameScreen());
    }

    @Override
    protected ScreenTransition enterTransition() {
        return new FadeTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new FadeTransition();
    }
}
