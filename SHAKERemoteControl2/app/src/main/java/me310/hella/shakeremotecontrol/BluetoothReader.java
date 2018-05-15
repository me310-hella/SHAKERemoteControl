package me310.hella.shakeremotecontrol;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class BluetoothReader implements Runnable {

    InputStream mmInputStream;
    private final byte DELIMITER = 10;
    private final Handler HANDLER = new Handler();
    private final byte[] readBuffer = new byte[1024];
    private int readBufferPosition = 0;
    private TouchEventHandler touchEventHandler;
    private BluetoothHandler bluetoothHandler;

    public BluetoothReader(InputStream inputStream, View view, BluetoothHandler bluetoothHandler){
        this.mmInputStream = inputStream;
        touchEventHandler = new TouchEventHandler(view);
        this.bluetoothHandler = bluetoothHandler;
    }

    private float[] toFloats (String sentData){
        String[] splitData = sentData.split(",");
        float [] floatData = new float[2];
        floatData[0] = Float.parseFloat(splitData[0]);
        floatData[1] = Float.parseFloat(splitData[1]);
        return floatData;
        //return Arrays.stream(splitData).map(Float::valueOf).toArray(Float[]::new);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted())
        {
            try
            {
                int bytesAvailable = mmInputStream.available();
                if(bytesAvailable > 0)
                {

                    byte[] packetBytes = new byte[bytesAvailable];
                    mmInputStream.read(packetBytes);

                    for(int i=0;i<bytesAvailable;i++)
                    {
                        byte b = packetBytes[i];
                        if(b == DELIMITER)
                        {

                            byte[] encodedBytes = new byte[readBufferPosition];
                            System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                            String data = new String(encodedBytes, "US-ASCII");
                            System.out.println("data: " + data);
                            readBufferPosition = 0;

                            if (data.equals("1,1")){
                                //bluetoothHandler.write(Controls.LED_TOGGLE);
                                TextView tv = this.touchEventHandler.getView().findViewById(R.id.helloWorldTextView);
                                tv.setText("received data from arduino");
                            }

                            final float[] position = toFloats(data);

                            HANDLER.post(new Runnable()
                            {
                                public void run()
                                {
                                    touchEventHandler.triggerTouch(position[0], position[1]);
                                }
                            });
                        }
                        else
                        {
                            readBuffer[readBufferPosition++] = b;
                        }
                    }
                }
            }
            catch (IOException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

}
