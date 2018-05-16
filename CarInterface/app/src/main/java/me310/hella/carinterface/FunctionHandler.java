package me310.hella.carinterface;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FunctionHandler {

    private List<Button> buttons;

    public FunctionHandler(){
        buttons = new ArrayList<Button>();
    }

    public void registerButton(Button button){
        buttons.add(button);
    }

    public void executeFunction(float[] position){
        for(Button button: buttons){

        }
    }
}
