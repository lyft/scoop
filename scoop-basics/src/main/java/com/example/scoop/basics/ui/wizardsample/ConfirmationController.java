package com.example.scoop.basics.ui.wizardsample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(ConfirmationController.Module.class)
@Layout(R.layout.wizard_confirmation)
public class ConfirmationController extends FrameLayout {

    public ConfirmationController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(ConfirmationController.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = ConfirmationController.class,
            addsTo = EnterLastNameController.Module.class,
            library = true
    )
    public static class Module {}

    @Inject
    AppRouter appRouter;

    @Inject
    WizardSession wizardSession;

    @Bind(R.id.first_name_text)
    TextView firstNameTextView;

    @Bind(R.id.last_name_text)
    TextView lastNameTextView;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        firstNameTextView.setText(wizardSession.firstName);
        lastNameTextView.setText(wizardSession.lastName);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.next_button)
    public void onClick() {
        appRouter.resetTo(DemosController.createScreen());
    }
}
