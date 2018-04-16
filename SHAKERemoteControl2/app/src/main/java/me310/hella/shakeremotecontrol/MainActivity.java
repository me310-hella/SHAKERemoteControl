package me310.hella.shakeremotecontrol;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private BluetoothHandler bluetoothHandler;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView helloWorldTextView = findViewById(R.id.helloWorldTextView);


        final Button button = findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("Button pressed");
                int [] buttonLocation = new int[2];
                button.getLocationOnScreen(buttonLocation);
                helloWorldTextView.setText("Button location\nx = "+buttonLocation[0]+"\ny = "+ buttonLocation[1]);
                System.out.println("Button position: " + buttonLocation[0] + "," + buttonLocation[1] );
            }
        });

        try {
            bluetoothHandler = new BluetoothHandler(findViewById(R.id.content_layout));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    bluetoothHandler.write(Controls.LED_TOGGLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


