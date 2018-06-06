package me310.hella.carinterface.statecontrol;

import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import me310.hella.carinterface.R;

public class MusicView extends ControlView {

    private boolean playing;
    private final MediaPlayer mp = MediaPlayer.create(this.ctx, R.raw.soho);

    public MusicView(List<Button> buttons, ImageView imageView) {
        super(buttons, imageView);
    }

    @Override
    public ControlView topLeft() {
        return null;
    }

    @Override
    public ControlView middleLeft() {
        toggleMusic();
        return this;
    }

    @Override
    public ControlView middleRight() {
        return this.middleLeft();
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
        //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_pause, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        imageView.setImageResource(R.drawable.music_pause);
    }

    private void toggleMusic(){
        if(!playing){
            mp.start();
            //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_play, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
            imageView.setImageResource(R.drawable.music_play);
        }
        else{
            mp.pause();
            //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.music_pause, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
            imageView.setImageResource(R.drawable.music_pause);
        }
        playing = !playing;

    }
}
