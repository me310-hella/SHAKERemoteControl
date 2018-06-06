package me310.hella.carinterface.statecontrol;

import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class StateController {

    public static CustomizeView customView;
    public static FanControlView fanView;
    public static MainControlView mainView;
    public static MusicView musicView;
    public static NavigationView navView;

    public static void initializeViews(List<Button> buttons, ImageView view) {
        customView = new CustomizeView(buttons, view);
        fanView = new FanControlView(buttons, view);
        mainView = new MainControlView(buttons, view);
        musicView = new MusicView(buttons, view);
        navView = new NavigationView(buttons, view);
    }
}
