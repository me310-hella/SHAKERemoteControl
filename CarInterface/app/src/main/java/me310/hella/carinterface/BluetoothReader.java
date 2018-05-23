package me310.hella.carinterface;

import java.io.IOException;
import java.io.InputStream;

public class BluetoothReader implements Runnable {

    InputStream mmInputStream;
    private final byte DELIMITER = 10;
    private final byte[] readBuffer = new byte[1024];
    private int readBufferPosition = 0;
    private FunctionHandler functionHandler;
    private boolean useButtonInput;


    public BluetoothReader(FunctionHandler functionHandler, InputStream inputStream, boolean useButtonInput){
        this.mmInputStream = inputStream;
        this.functionHandler = functionHandler;
        this.useButtonInput = useButtonInput;
    }

    private int[] toInts (String sentData){
        String[] splitData = sentData.split(",");
        int [] intData = new int[2];
        intData[0] = Integer.parseInt(splitData[0]);
        intData[1] = Integer.parseInt(splitData[1]);
        return intData;
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
                            //System.out.println("data: " + data);
                            readBufferPosition = 0;
                            if(useButtonInput){
                                processButtonInput(data);
                            }
                            else{
                                processPositionInput(data);
                            }
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

    private void processButtonInput(String data) {
        int buttonNumber = Integer.parseInt(data);
        functionHandler.executeFunction(buttonNumber);
    }

    private void processPositionInput(String data){
        final int[] position = toInts(data);
        functionHandler.executeFunction(position);
    }

}
