package me310.hella.carinterface;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class BluetoothHandler {

    private final String MAC_ADDRESS = "00:18:E4:00:12:32"; // MAC-Adress of bluetooth module // HC-05 : 98:D3:31:F5:40:0D
    private OutputStream outputStream;
    private InputStream inStream;
    public BluetoothHandler(FunctionHandler functionHandler) throws IOException {


        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter != null) {
            if (blueAdapter.isEnabled()) {
                Set<BluetoothDevice> bondedDevices = blueAdapter.getBondedDevices();

                BluetoothDevice desiredDevice;
                if(bondedDevices.size() > 0) {
                    for(BluetoothDevice device : bondedDevices){
                        if(device.toString().equals(MAC_ADDRESS)){
                            desiredDevice = device;
                            ParcelUuid[] uuids = desiredDevice.getUuids();
                            BluetoothSocket socket = desiredDevice.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                            socket.connect();
                            outputStream = socket.getOutputStream();
                            inStream = socket.getInputStream();
                            final Thread readThread = new Thread(new BluetoothReader(functionHandler, inStream, this));
                            readThread.start();
                            break;
                        }
                    }
                }

                Log.e("error", "No appropriate paired devices.");
            } else {
                Log.e("error", "Bluetooth is disabled.");
            }
        }
    }

    public void sendControl(Controls c) throws IOException {
        outputStream.write(c.name().getBytes());
    }

}
