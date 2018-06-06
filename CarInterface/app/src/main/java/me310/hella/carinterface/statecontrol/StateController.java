package me310.hella.carinterface.statecontrol;

import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class StateController {

    private static CustomizeView customView;
    private static FanControlView fanView;
    private static MainControlView mainView;
    private static MusicView musicView;
    private static NavigationView navView;

    public static void initializeViews(List<Button> buttons, ImageView view) {
        customView = new CustomizeView(buttons, view);
        fanView = new FanControlView(buttons, view);
        mainView = new MainControlView(buttons, view);
        musicView = new MusicView(buttons, view);
        navView = new NavigationView(buttons, view);
    }

    public static CustomizeView getCustomView() {
        customView.show();
        return customView;
    }

    public static FanControlView getFanView() {
        fanView.show();
        return fanView;
    }

    public static MainControlView getMainView() {
        mainView.show();
        return mainView;
    }

    public static MusicView getMusicView() {
        musicView.show();
        return musicView;
    }

    public static NavigationView getNavView() {
        navView.show();
        return navView;
    }
}
