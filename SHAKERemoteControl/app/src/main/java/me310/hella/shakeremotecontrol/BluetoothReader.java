package me310.hella.shakeremotecontrol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.BiConsumer;

import android.os.Handler;

public class BluetoothReader implements Runnable {

    InputStream mmInputStream;
    private final byte DELIMITER = 10;
    private final Handler HANDLER = new Handler();
    private final byte[] readBuffer = new byte[1024];
    private int readBufferPosition = 0;

    private BiConsumer positionConsumer;

    public BluetoothReader(InputStream inputStream, BiConsumer consumer){
        this.positionConsumer = consumer;
        this.mmInputStream = inputStream;
    }

    private Float[] toFloats (String sentData){
        String[] splitData = sentData.split(",");
        return Arrays.stream(splitData).map(Float::valueOf).toArray(Float[]::new);
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

                            Float[] position = toFloats(data);

                            HANDLER.post(new Runnable()
                            {
                                public void run()
                                {
                                    positionConsumer.accept(position[0], position[1]);
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
