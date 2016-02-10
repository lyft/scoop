package com.example.scoop.basics.ui.hybridsample;

import com.example.scoop.basics.R;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;

public class HybridScreens extends Screen{

    /**
     * Example: Screen.create(new HybridScreens.HybridViewControllerScreen("params"), HybridViewController.class)
     */
    @ViewController(HybridViewController.class)
    @DaggerModule(HybridModule.class)
    public static class HybridViewControllerScreen extends HybridScreens{
        private final String exampleParams;

        public HybridViewControllerScreen(final String exampleParams) {
            this.exampleParams = exampleParams;
        }

        public String getExampleParams() {
            return exampleParams;
        }
    }

    @Layout(R.layout.hybrid)
    @DaggerModule(HybridModule.class)
    public static class HybridLayoutScreen extends HybridScreens {

        private final String exampleParams;

        public HybridLayoutScreen(final String exampleParams) {
            this.exampleParams = exampleParams;
        }

        public String getExampleParams() {
            return exampleParams;
        }
    }
}
