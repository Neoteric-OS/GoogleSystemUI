package com.google.android.systemui.volume.panel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentProviderClient;
import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import android.media.Spatializer;
import android.os.Bundle;
import android.util.Log;
import com.android.settingslib.bluetooth.LeAudioProfile;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.google.android.settingslib.dcservice.DcServiceClientImpl;
import java.nio.charset.StandardCharsets;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatializerWrapper extends Spatializer {
    public final DcServiceClientImpl dcServiceClient;
    public final LocalBluetoothManager localBluetoothManager;
    public final Spatializer spatializer;

    public SpatializerWrapper(AudioManager audioManager, DcServiceClientImpl dcServiceClientImpl, LocalBluetoothManager localBluetoothManager) {
        super(audioManager);
        this.dcServiceClient = dcServiceClientImpl;
        this.localBluetoothManager = localBluetoothManager;
        this.spatializer = audioManager.getSpatializer();
    }

    public final boolean hasHeadTracker(AudioDeviceAttributes audioDeviceAttributes) {
        LocalBluetoothManager localBluetoothManager;
        LocalBluetoothAdapter localBluetoothAdapter;
        if (!this.spatializer.hasHeadTracker(audioDeviceAttributes)) {
            return false;
        }
        if (!BluetoothAdapter.checkBluetoothAddress(audioDeviceAttributes.getAddress()) || (localBluetoothManager = this.localBluetoothManager) == null || (localBluetoothAdapter = localBluetoothManager.mLocalAdapter) == null) {
            return true;
        }
        BluetoothDevice remoteDevice = localBluetoothAdapter.mAdapter.getRemoteDevice(audioDeviceAttributes.getAddress());
        if (remoteDevice == null) {
            return true;
        }
        DcServiceClientImpl dcServiceClientImpl = this.dcServiceClient;
        dcServiceClientImpl.getClass();
        Log.i("DcServiceClientImpl", "Query head tracking state through DC-Service for " + remoteDevice.getAddress());
        Bundle bundle = new Bundle();
        bundle.putString("bluetooth_address", remoteDevice.getAddress());
        byte[] metadata = remoteDevice.getMetadata(3);
        if (metadata == null) {
            metadata = new byte[0];
        }
        bundle.putString("hardware_version", new String(metadata, StandardCharsets.UTF_8));
        LeAudioProfile leAudioProfile = dcServiceClientImpl.profileManager.mLeAudioProfile;
        bundle.putBoolean("is_le_audio", leAudioProfile != null ? leAudioProfile.isEnabled(remoteDevice) : false);
        try {
            ContentProviderClient acquireUnstableContentProviderClient = dcServiceClientImpl.contentResolver.acquireUnstableContentProviderClient(DcServiceClientImpl.PROXY_AUTHORITY);
            Intrinsics.checkNotNull(acquireUnstableContentProviderClient);
            try {
                Bundle call = acquireUnstableContentProviderClient.call("method_is_head_tracking_available", null, bundle);
                Intrinsics.checkNotNull(call);
                boolean z = call.getBoolean("head_tracking_available");
                AutoCloseableKt.closeFinally(acquireUnstableContentProviderClient, null);
                return z;
            } finally {
            }
        } catch (Exception unused) {
            Log.w("DcServiceClientImpl", "isHeadTrackingAvailable: error happens when calling DcService.");
            return true;
        }
    }
}
