package me310.hella.carinterface.statecontrol;

import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import me310.hella.carinterface.R;

public class MainControlView extends ControlView {

    private boolean lightOn = false;


    public MainControlView(List<Button> buttons, ImageView imageView) {
        super(buttons, imageView);
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
        imageView.setImageResource(R.drawable.main_lightoff_yellow);
        //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.main_lightoff_yellow, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
    }

    private void toggleLight(){
        int imageId = lightOn ? R.drawable.main_lightoff_yellow : R.drawable.main_lighton_yellow;
        //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, imageId, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        imageView.setImageResource(imageId);
        lightOn = !lightOn;
    }

}
