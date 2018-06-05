package me310.hella.carinterface;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import me310.hella.carinterface.statecontrol.ControlView;
import me310.hella.carinterface.statecontrol.MainControlView;
import me310.hella.carinterface.statecontrol.Triggers;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ExpeActivity extends AppCompatActivity {
    private ImageView imageView;
    private ControlView controlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.imageView);

        // Set up the user interaction to manually show or hide the system UI.
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void processEvent(Triggers t) {
        if (t.equals(Triggers.TOP_RIGHT) && !controlView.getClass().equals(MainControlView.class)) { // TODO: Anything else on the top right?
            controlView = new MainControlView();

        } else {
            controlView = controlView.doAction(t);
        }
        controlView.show();
    }

}
