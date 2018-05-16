package me310.hella.carinterface;

import java.io.IOException;
import java.io.InputStream;

public class BluetoothReader implements Runnable {

    InputStream mmInputStream;
    private final byte DELIMITER = 10;
    private final byte[] readBuffer = new byte[1024];
    private int readBufferPosition = 0;
    private FunctionHandler functionHandler;


    public BluetoothReader(FunctionHandler functionHandler, InputStream inputStream, BluetoothHandler bluetoothHandler){
        this.mmInputStream = inputStream;
        this.functionHandler = functionHandler;
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

                            }
                            final float[] position = toFloats(data);
                            functionHandler.executeFunction(position);
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
