package me310.hella.shakeremotecontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //private BluetoothHandler bluetoothHandler;

    public MainActivity() throws IOException {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*findViewById(R.id.content_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast toast = Toast.makeText(
                        getApplicationContext(),
                        "View touched",
                        Toast.LENGTH_SHORT
                );
                toast.show();
                System.out.println("recieved Touch: " + motionEvent.getX()+ " " + motionEvent.getY());
                return false;
            }
        });*/

        final TextView helloWorldTextView = findViewById(R.id.helloWorldTextView);

        final Button button = findViewById(R.id.button1);
        button.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //System.out.println("Button pressed");
                int [] buttonLocation = new int[2];
                button.getLocationOnScreen(buttonLocation);
                //helloWorldTextView.setText("Button location\nx = "+buttonLocation[0]+"\ny = "+ buttonLocation[1]);
                helloWorldTextView.setText("Button location\nx = "+buttonLocation[0]+"\ny = "+ buttonLocation[1]);
                //System.out.println("Button position: " + buttonLocation[0] + "," + buttonLocation[1] );
                return true;
            }
        });

        /*try {
            bluetoothHandler = new BluetoothHandler(findViewById(R.id.content_layout));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*try {
                    bluetoothHandler.write(Controls.LED_TOGGLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //new TouchEventHandler(findViewById(R.id.content_layout)).triggerTouch(100,100); //TODO: remove later
            }

        });

        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);

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


