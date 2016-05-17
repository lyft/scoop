package com.example.scoop.basics.ui.wizardsample.controller;

import android.widget.TextView;

import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemoScreen;
import com.example.scoop.basics.ui.wizardsample.WizardSession;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmationController extends ViewController {

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
}
