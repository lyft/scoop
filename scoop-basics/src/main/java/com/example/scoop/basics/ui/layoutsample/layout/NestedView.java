package com.example.scoop.basics.ui.layoutsample.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.scoop.basics.R;
import com.example.scoop.basics.ui.layoutsample.LayoutInjectData;
import com.lyft.scoop.dagger.DaggerInjector;
import javax.inject.Inject;

public class NestedView extends LinearLayout {

    @BindView(R.id.nested_text_view)
    TextView nestedTextView;

    @Inject
    LayoutInjectData layoutInjectData;

    public NestedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerInjector.fromView(this).inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        nestedTextView.setText(layoutInjectData.getData());
    }
}
