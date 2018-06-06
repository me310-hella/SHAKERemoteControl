package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

public class StateController {

    private static CustomizeView customView;
    private static FanControlView fanView;
    private static MainControlView mainView;
    private static MusicView musicView;
    private static NavigationView navView;

    public static void initializeViews(ImageView view) {
        customView = new CustomizeView(view);
        fanView = new FanControlView(view);
        mainView = new MainControlView(view);
        musicView = new MusicView(view);
        navView = new NavigationView(view);
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
