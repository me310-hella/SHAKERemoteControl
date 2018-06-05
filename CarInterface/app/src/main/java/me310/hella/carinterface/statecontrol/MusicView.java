package me310.hella.carinterface.statecontrol;

import android.media.MediaPlayer;

import me310.hella.carinterface.R;

public class MusicView extends ControlView {

    private boolean playing;
    private static final MediaPlayer mp = MediaPlayer.create(this, R.raw.soho);

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
        toggleMusic();
        return this;
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
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_pause, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
    }

    private void toggleMusic(){
        if(!playing){
            mp.start();
            imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_play, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        }
        else{
            mp.pause();
            imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_pause, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        }
        playing = !playing;

    }
}
