package com.example.scoop.basics.ui.navigationsample.controller;

import android.widget.Toast;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.navigationsample.screen.BScreen;
import javax.inject.Inject;

public class AController extends BaseViewController {
    private static final String TOAST_TEXT = "Successfully called \"getView()\"";

    private AppRouter appRouter;

    @Inject
    public AController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.a;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.go_to_b_button)
    public void goToB() {
        appRouter.goTo(new BScreen());
    }

    @OnClick(R.id.toast_button)
    public void toast() {
        Toast.makeText(getView().getContext(), TOAST_TEXT,
                Toast.LENGTH_SHORT).show();
    }
}
