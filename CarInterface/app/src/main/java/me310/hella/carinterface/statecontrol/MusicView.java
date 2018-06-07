package me310.hella.carinterface.statecontrol;

import android.media.MediaPlayer;
import android.widget.ImageView;

import me310.hella.carinterface.FullscreenActivity;
import me310.hella.carinterface.R;

public class MusicView extends ControlView {

    private boolean playing;
    private final MediaPlayer mp = MediaPlayer.create(this.ctx, R.raw.soho);

    public MusicView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        return this;
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
        return this;
    }

    @Override
    public ControlView bottomRight() {
        return this;
    }

    @Override
    public void show() {
        int imageId = playing ? R.drawable.music_play : R.drawable.music_pause;
        imageView.setImageResource(imageId);
        //FullscreenActivity.ImageViewAnimatedChange(imageView, imageId);
    }

    private void toggleMusic(){
        if(!playing){
            mp.start();
        }
        else{
            mp.pause();
        }
        playing = !playing;
        show();

    }
}
