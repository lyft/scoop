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
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(EnterLastNameController.Module.class)
@Layout(R.layout.wizard_enter_last_name)
public class EnterLastNameController extends FrameLayout {

    public static Screen createScreen() {
        return Screen.create(EnterLastNameController.class)
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

    @Inject
    AppRouter appRouter;

    @Inject
    WizardSession wizardSession;

    @Bind(R.id.last_name_edit_text)
    EditText lastNameEditText;

    public EnterLastNameController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        lastNameEditText.setText(wizardSession.lastName);
        Keyboard.showKeyboard(lastNameEditText);
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        wizardSession.lastName = lastNameEditText.getText().toString();
        appRouter.goTo(ConfirmationController.createScreen());
    }
}
