package com.example.scoop.basics.ui.resultsample.controller;

import android.widget.EditText;
import butterknife.Bind;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.ScreenResult;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class ResultViewController extends ViewController {

    @Bind(R.id.result_edit_text)
    EditText resultEditText;

    private final AppRouter appRouter;

    @Inject
    public ResultViewController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.result_sample;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        Keyboard.showKeyboard(resultEditText);
    }

    @OnClick(R.id.finish_button)
    public void onFinishButtonClick() {
        final String input = resultEditText.getText().toString();
        InputResult inputResult = new InputResult(input);
        setResult(inputResult);

        appRouter.goBack();
    }

    public static class InputResult extends ScreenResult {

        private final String input;

        private InputResult(String input) {
            this.input = input;
        }

        public String getResult() {
            return input;
        }
    }
}
