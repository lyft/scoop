package com.example.scoop.basics.ui.wizardsample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.FadeTransition;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Singleton;

@ControllerModule(EnterFirstNameController.Module.class)
@Layout(R.layout.wizard_enter_first_name)
public class EnterFirstNameController extends FrameLayout {

    public EnterFirstNameController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(EnterFirstNameController.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = EnterFirstNameController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module {

        @Provides
        @Singleton
        WizardSession provideWizardSession() {
            return new WizardSession();
        }
    }

    @Inject
    AppRouter appRouter;

    @Inject
    WizardSession wizardSession;

    @Bind(R.id.first_name_edit_text)
    EditText firstNameEditText;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        firstNameEditText.setText(wizardSession.firstName);
        Keyboard.showKeyboard(firstNameEditText);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.firstName = firstNameEditText.getText().toString();
        appRouter.goTo(EnterLastNameController.createScreen());
    }
}
