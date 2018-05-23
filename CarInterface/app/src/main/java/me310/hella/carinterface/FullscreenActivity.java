package me310.hella.carinterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private View mContentView;
    private View mControlsView;

    private FunctionHandler functionHandler;
    private SharedPreferences sharedPref;

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        getSupportActionBar().hide();

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        //BluetoothHandler bluetoothHandler = new BluetoothHandler(functionHandler);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String macAddress = intent.getStringExtra(SetupActivity.MAC_ADDRESS);

        functionHandler = new FunctionHandler(macAddress);
        //Button b = findViewById(R.id.button1);
        //functionHandler.registerButton(b, Controls.LED_TOGGLE);
        functionHandler.registerButton((Button)findViewById(R.id.button1), Controls.LED_TOGGLE);        // x: 53, y: 634
        functionHandler.registerButton((Button)findViewById(R.id.button2), Controls.FUNCTION_TWO);      // x: 593 y: 634
        functionHandler.registerButton((Button)findViewById(R.id.button3), Controls.FUNCTION_THREE);    // x: 53 y: 1304
        functionHandler.registerButton((Button)findViewById(R.id.button4), Controls.FUNCTION_FOUR);     // x: 593 y: 1304

        /*final Button b1 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] position = new int[2];
                b1.getLocationOnScreen(position);
                b1.setText("x: " + position[0] + " y: " + position[1]);
            }
        });*/


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }
}
