package me310.hella.carinterface;

import android.graphics.Rect;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionHandler {

    private List<Button> buttons;
    private Map<Button, Controls> controlMap;

    public FunctionHandler(){
        buttons = new ArrayList<Button>();
        controlMap = new HashMap<Button, Controls>();
    }

    public void registerButton(Button button, Controls control){
        buttons.add(button);
        controlMap.put(button, control);
    }

    public void executeFunction(float[] position){
        for(Button button: buttons){
            Rect extent = button.getClipBounds();
            if(position[0] > extent.left && position[0] < extent.right && position[1] > extent.top && position[1] < extent.bottom){ // TODO: check if right
                Controls control = controlMap.get(button); // TODO: send control
                break;
            }
        }
    }

    public void executeFunction(Button button){
        Controls control = controlMap.get(button); // TODO: send control
    }
}
