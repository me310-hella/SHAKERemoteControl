package me310.hella.carinterface.statecontrol;

import android.widget.Button;

import java.util.List;

import me310.hella.carinterface.R;

public class MainControlView extends ControlView {

    private boolean lightOn = false;


    public MainControlView(List<Button> buttons) {
        super(buttons);
    }
    public MainControlView(){}

    @Override
    public ControlView topLeft() {
        toggleLight();
        return this;
    }

    @Override
    public ControlView topRight() {
        return new FanControlView();

    }

    @Override
    public ControlView middleLeft() {
        return new CustomizeView();
    }

    @Override
    public ControlView middleRight() {
        return new NavigationView();
    }

    @Override
    public ControlView bottomLeft() {
        return new MusicView();
    }

    @Override
    public ControlView bottomRight() {
        return this;

    }

    @Override
    public void show() {
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.main_lightoff_yellow, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
    }

    private void toggleLight(){
        int imageId = lightOn ? R.drawable.main_lightoff_yellow : R.drawable.main_lighton_yellow;
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, imageId, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        lightOn = !lightOn;
    }

}
