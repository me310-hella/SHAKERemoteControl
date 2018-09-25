package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class CustomizeView extends ControlView {

    public CustomizeView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        StateController.getMainView().toggleLight();
        return StateController.getMainView();
    }

    @Override
    public ControlView topRight(){
        return StateController.getMainView();
    }

    @Override
    public ControlView middleLeft() {
        return StateController.getMainView();
    }

    @Override
    public ControlView middleRight() {
        return StateController.getMainView();
    }

    @Override
    public ControlView bottomLeft() {
        return StateController.getMainView();
    }

    @Override
    public ControlView bottomRight() {
        return StateController.getMainView();
    }

    @Override
    public void show() {
        int imageId = StateController.getMainView().isLightOn() ? R.drawable.main_customize_lighton : R.drawable.main_customize;
        imageView.setImageResource(imageId);
    }
}
