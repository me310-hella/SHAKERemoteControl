package me310.hella.carinterface.statecontrol;

import android.content.Context;
import android.widget.ImageView;

import me310.hella.carinterface.FullscreenActivity;

public abstract class ControlView {

    protected Context ctx;

    protected ImageView imageView;

    public ControlView(final ImageView imageView) {
        this.imageView = imageView;
        this.ctx = FullscreenActivity.ctx;
    }

    public ControlView doAction(Triggers t) {
        switch (t) {
            case TOP_LEFT:
                return topLeft();
            case TOP_RIGHT:
                return topRight();
            case MIDDLE_LEFT:
                return middleLeft();
            case MIDDLE_RIGHT:
                return middleRight();
            case BOTTOM_LEFT:
                return bottomLeft();
            case BOTTOM_RIGHT:
                return bottomRight();
            case RADIO:
                return radio();
            case AC_CAR:
                return acCar();
            default:
                return this;
        }
    }

    public abstract ControlView topLeft();
    public ControlView topRight(){
        return StateController.getMainView();
    }
    public abstract ControlView middleLeft();
    public abstract ControlView middleRight();
    public abstract ControlView bottomLeft();
    public abstract ControlView bottomRight();

    public ControlView radio(){
        StateController.getMusicView().toggleMusic();
        return StateController.getMusicView();
    }

    public ControlView acCar(){
        return StateController.getFanView();
    }

    public abstract void show();
}
