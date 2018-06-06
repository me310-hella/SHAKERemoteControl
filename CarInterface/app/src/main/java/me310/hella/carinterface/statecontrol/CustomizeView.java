package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class CustomizeView extends ControlView {

    public CustomizeView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        return StateController.getMainView();
    }

    @Override
    public ControlView topRight(){
        return StateController.getFanView();
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
        imageView.setImageResource(R.drawable.main_customize);
    }
}
