package me310.hella.carinterface;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionHandler extends Activity{

    private static final int ACCENT_COLOR = Color.parseColor("#FF4081");
    private static BluetoothHandler bluetoothHandler;
    private List<Button> buttons;
    private Map<Button, Controls> controlMap;

    public FunctionHandler(){
        try {
            this.bluetoothHandler = new BluetoothHandler(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttons = new ArrayList<Button>();
        controlMap = new HashMap<Button, Controls>();
    }

    public void registerButton(Button button, Controls control){
        buttons.add(button);
        controlMap.put(button, control);
    }

    public void executeFunction(int[] touchPosition){
        for(Button button: buttons){
            /*int[] buttonPos = new int[2];
            button.getLocationOnScreen(buttonPos);
            if(touchPosition[0] == buttonPos[0] && touchPosition[1] == buttonPos[1]){
                setButtonColorMock(button);
                break;
            }*/
            //Rect extent = button.getClipBounds();

            int[] buttonPos = new int[2];
            button.getLocationOnScreen(buttonPos);
            final int EXTENT_RADIUS = 50;
            Rect extent = new Rect(buttonPos[0] - EXTENT_RADIUS, buttonPos[1] - EXTENT_RADIUS, buttonPos[0] + EXTENT_RADIUS, buttonPos[1] + EXTENT_RADIUS);
            if(extent.contains(touchPosition[0], touchPosition[1])){
                setButtonColorMock(button);
                try {
                    bluetoothHandler.sendControl(controlMap.get(button));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void executeFunction(int buttonNumber){
        try {
            Button touchedButton = buttons.get(buttonNumber);
            setButtonColorMock(touchedButton);
            bluetoothHandler.sendControl(controlMap.get(touchedButton));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setButtonColorMock(final Button button){
        runOnUiThread(new Runnable() {
            public void run() {
                for (Button btn: buttons){
                    btn.setBackgroundResource(android.R.drawable.btn_default);
                }
                button.setBackgroundColor(ACCENT_COLOR);
            }
        });
    }
}
