package com.example.scoop.basics.ui.transitions.standardtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.transitions.standardtransitions.screen.HorizontalSlideScreen;
import com.lyft.scoop.ScreenResult;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class FadeController extends ViewController {
    private AppRouter appRouter;

    @Inject
    public FadeController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.fade;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        FadeResult fadeResult = new FadeResult();
        fadeResult.setMessage("hello");
        setResult(fadeResult);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(new HorizontalSlideScreen());
    }

    public static class FadeResult extends ScreenResult {
        String message;

        void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
