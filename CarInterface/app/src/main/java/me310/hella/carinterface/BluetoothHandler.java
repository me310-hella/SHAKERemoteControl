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

    private OutputStream outputStream;
    private InputStream inStream;
    public BluetoothHandler(String macAddress, FullscreenActivity act) throws IOException {


        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (blueAdapter != null) {
            if (blueAdapter.isEnabled()) {
                Set<BluetoothDevice> bondedDevices = blueAdapter.getBondedDevices();

                BluetoothDevice desiredDevice;
                if(bondedDevices.size() > 0) {
                    for(BluetoothDevice device : bondedDevices){
                        if(device.toString().equals(macAddress)){
                            Log.i("info", "found device");
                            desiredDevice = device;
                            ParcelUuid[] uuids = desiredDevice.getUuids();
                            BluetoothSocket socket = desiredDevice.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                            socket.connect();
                            outputStream = socket.getOutputStream();
                            inStream = socket.getInputStream();
                            final Thread readThread = new Thread(new BluetoothReader(inStream, act));
                            readThread.start();
                            break;
                        }
                    }
                } else {
                    Log.e("error", "No appropriate paired devices.");
                }
            } else {
                Log.e("error", "Bluetooth is disabled.");
            }
        }
    }

    public void sendControl(Controls c) throws IOException {
        outputStream.write(c.name().getBytes());
    }

}
