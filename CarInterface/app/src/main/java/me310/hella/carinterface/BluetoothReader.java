package me310.hella.carinterface;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import me310.hella.carinterface.statecontrol.Triggers;

public class BluetoothReader extends Activity implements Runnable{

    InputStream mmInputStream;
    private final byte DELIMITER = 10;
    private final byte[] readBuffer = new byte[1024];
    private int readBufferPosition = 0;
    private FullscreenActivity mainActivity;


    public BluetoothReader(InputStream inputStream, FullscreenActivity mainActivity){
        this.mainActivity = mainActivity;
        this.mmInputStream = inputStream;
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
                            final String data = new String(encodedBytes, "US-ASCII").trim();
                            readBufferPosition = 0;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainActivity.processEvent(getTrigger(data));
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


    private Triggers getTrigger(String data) {
        int index;
        try {
            index = Integer.valueOf(data);
        } catch (NumberFormatException e) {
            Log.e("info", "Not a valid message " + data);
            return null;
        }
        Log.i("info", "Received trigger " + index);
        if (index >= Triggers.values().length || index < 1) {
            return null;
        }
        return Triggers.values()[index - 1];
    }
}
