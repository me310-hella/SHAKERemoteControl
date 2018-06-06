package me310.hella.carinterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import me310.hella.carinterface.statecontrol.ControlView;
import me310.hella.carinterface.statecontrol.MainControlView;
import me310.hella.carinterface.statecontrol.StateController;
import me310.hella.carinterface.statecontrol.Triggers;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private ControlView controlView;

    public static Context ctx;

    List<Button> buttons;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        getSupportActionBar().hide();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String macAddress = intent.getStringExtra(SetupActivity.MAC_ADDRESS);
        Button[] buttonsArray = {
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6)};
        this.buttons = Arrays.asList(buttonsArray);

        imageView = findViewById(R.id.imageView);
        ctx = this.getApplicationContext();
        StateController.initializeViews(imageView);
        controlView = StateController.getMainView();

        try {
            BluetoothHandler bluetoothHandler = new BluetoothHandler(macAddress, this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.topLeft();

            }
        });
        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.topRight();
            }
        });

        buttons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.middleLeft();
            }
        });
        buttons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.middleRight();
            }
        });
        buttons.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.bottomLeft();
            }
        });
        buttons.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlView = controlView.bottomRight();
            }
        });


    }

    public void processEvent(Triggers t) {
        controlView = controlView.doAction(t);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
