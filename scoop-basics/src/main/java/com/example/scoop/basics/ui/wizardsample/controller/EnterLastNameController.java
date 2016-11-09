package com.example.scoop.basics.ui.wizardsample.controller;

import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.Keyboard;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.example.scoop.basics.ui.wizardsample.screen.ConfirmationScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class EnterLastNameController extends BaseViewController {

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @BindView(R.id.last_name_edit_text)
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

    @Override
    protected ScreenTransition enterTransition() {
        return new ForwardSlideTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new BackwardSlideTransition();
    }
}
