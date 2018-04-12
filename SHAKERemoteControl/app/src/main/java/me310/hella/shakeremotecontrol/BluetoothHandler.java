package me310.hella.shakeremotecontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class BluetoothHandler {

    private final String MAC_ADDRESS = "00:18:E4:00:12:32"; // MAC-Adress of bluetooth module // HC-05 : 98:D3:31:F5:40:0D
    private OutputStream outputStream;
    private InputStream inStream;
    private final Map<Controls, String> controlMapping= new HashMap<>();
    public BluetoothHandler(BiConsumer<Float, Float> positionConsumer) throws IOException {


        initControlMapping();
        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter != null) {
            if (blueAdapter.isEnabled()) {
                Set<BluetoothDevice> bondedDevices = blueAdapter.getBondedDevices();

                if(bondedDevices.size() > 0) {
                    BluetoothDevice device = bondedDevices.stream().filter(d -> d.toString().equals(MAC_ADDRESS)).findAny().get();
                    ParcelUuid[] uuids = device.getUuids();
                    BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                    socket.connect();
                    outputStream = socket.getOutputStream();
                    inStream = socket.getInputStream();
                    final Thread readThread = new Thread(new BluetoothReader(inStream, positionConsumer));
                    readThread.start();
                }

                Log.e("error", "No appropriate paired devices.");
            } else {
                Log.e("error", "Bluetooth is disabled.");
            }
        }
    }

    private void initControlMapping() {
        /* Add control mappings for serialization */
        controlMapping.put(Controls.LED_TOGGLE, "1");
    }

    public void write(Controls c) throws IOException {
        String s = controlMapping.get(c);
        outputStream.write(s.getBytes());
    }

}
