package com.example.scoop.basics.ui.transitions.dialogtransitions.screen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideDownTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideUpTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.module.DialogModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import java.io.ByteArrayOutputStream;

@Controller(DialogController.class)
@DaggerModule(DialogModule.class)
@EnterTransition(SlideUpTransition.class)
@ExitTransition(SlideDownTransition.class)
public class Dialog extends Screen {
    private byte[] background;

    @Override
    public void saveViewState(View view) {
        super.saveViewState(view);
        BitmapDrawable backgroundDrawable = (BitmapDrawable) view.getBackground();

        if (backgroundDrawable != null) {
            Bitmap bitmap = backgroundDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            background = stream.toByteArray();
        } else {
            background = null;
        }
    }

    public void restoreViewState(View view) {
        super.restoreViewState(view);

        if (background != null) {
            Bitmap backgroundBitmap = BitmapFactory.decodeByteArray(background, 0, background.length);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(view.getResources(), backgroundBitmap);
            view.setBackground(bitmapDrawable);
        }
    }
}
