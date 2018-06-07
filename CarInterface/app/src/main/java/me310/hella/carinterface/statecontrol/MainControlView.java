package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class MainControlView extends ControlView {

    private boolean lightOn = false;


    public MainControlView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        toggleLight();
        return this;
    }

    @Override
    public ControlView topRight() {
        return StateController.getFanView();
    }

    @Override
    public ControlView middleLeft() {
        return StateController.getCustomView();
    }

    @Override
    public ControlView middleRight() {
        return StateController.getNavView();
    }

    @Override
    public ControlView bottomLeft() {
        return StateController.getMusicView();
    }

    @Override
    public ControlView bottomRight() {
        return this;

    }

    @Override
    public void show() {
        int imageId = lightOn ? R.drawable.main_lighton_yellow : R.drawable.main_lightoff_yellow;
        imageView.setImageResource(imageId);
    }

    private void toggleLight(){
        lightOn = !lightOn;
        show();
    }

}
