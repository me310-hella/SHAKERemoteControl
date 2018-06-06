package me310.hella.carinterface.statecontrol;

import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import me310.hella.carinterface.R;

public class CustomizeView extends ControlView {

    public CustomizeView(List<Button> buttons, ImageView imageView) {
        super(buttons, imageView);
    }

    @Override
    public ControlView topLeft() {
        return null;
    }

    @Override
    public ControlView topRight() {
        return null;
    }

    @Override
    public ControlView middleLeft() {
        return null;
    }

    @Override
    public ControlView middleRight() {
        return null;
    }

    @Override
    public ControlView bottomLeft() {
        return null;
    }

    @Override
    public ControlView bottomRight() {
        return null;
    }

    @Override
    public void show() {
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.main_customize, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        imageView.setImageResource(R.drawable.main_customize);
    }
}
