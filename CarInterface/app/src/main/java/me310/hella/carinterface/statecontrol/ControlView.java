package me310.hella.carinterface.statecontrol;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.List;

public abstract class ControlView {

    protected static int defaultColor = Color.parseColor("#FFFFFF");
    protected static int activatedColor = Color.parseColor("#FF0000");
    protected boolean activated = false;
    protected Button topLeftButton;
    protected Button topRightButton;
    protected Button bottomRightButton;
    protected Button bottomLeftButton;
    protected List<Button> buttons;


    public ControlView(List<Button> buttons) {
        this.buttons = buttons;
        this.topLeftButton = buttons.get(0);
        this.topRightButton = buttons.get(1);
        this.bottomRightButton = buttons.get(2);
        this.bottomLeftButton = buttons.get(3);
        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLeft().show();
            }
        });
        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRight().show();
            }
        });
        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomRight().show();
            }
        });
    }

    public ControlView doAction(Triggers t) {
        switch (t) {
            case TOP_LEFT:
                return topLeft();
            case TOP_RIGHT:
                return topRight();
            case BOTTOM_RIGHT:
                return bottomRight();
            default:
                return this;
        }
    }

    public abstract ControlView topLeft();
    public abstract ControlView topRight();
    public abstract ControlView bottomRight();
    public abstract void show();
}
