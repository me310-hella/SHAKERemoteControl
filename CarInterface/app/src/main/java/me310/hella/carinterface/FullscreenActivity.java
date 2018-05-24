package me310.hella.carinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String macAddress = intent.getStringExtra(SetupActivity.MAC_ADDRESS);
        boolean useButtonInput = intent.getBooleanExtra(SetupActivity.USE_BUTTON_INPUT, true);

        functionHandler = new FunctionHandler(macAddress, useButtonInput);
        functionHandler.registerButton((Button)findViewById(R.id.button0), Controls.LED_TOGGLE);        // x: 53, y: 634
        functionHandler.registerButton((Button)findViewById(R.id.button1), Controls.FUNCTION_TWO);      // x: 593 y: 634
        functionHandler.registerButton((Button)findViewById(R.id.button2), Controls.FUNCTION_THREE);    // x: 53 y: 1304
        functionHandler.registerButton((Button)findViewById(R.id.button3), Controls.FUNCTION_FOUR);     // x: 593 y: 1304
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
