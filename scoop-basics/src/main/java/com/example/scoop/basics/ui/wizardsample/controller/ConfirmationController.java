package com.example.scoop.basics.ui.wizardsample.controller;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.DemoScreen;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class ConfirmationController extends BaseViewController {

    private AppRouter appRouter;
    private WizardSession wizardSession;

    @BindView(R.id.first_name_text)
    TextView firstNameTextView;

    @BindView(R.id.last_name_text)
    TextView lastNameTextView;

    @Inject
    public ConfirmationController(AppRouter appRouter, WizardSession wizardSession) {
        this.appRouter = appRouter;
        this.wizardSession = wizardSession;
    }

    @Override
    protected int layoutId() {
        return R.layout.wizard_confirmation;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        firstNameTextView.setText(wizardSession.firstName);
        lastNameTextView.setText(wizardSession.lastName);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.next_button)
    public void onClick() {
        appRouter.resetTo(new DemoScreen());
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
