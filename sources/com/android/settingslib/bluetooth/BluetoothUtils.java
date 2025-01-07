package com.android.settingslib.bluetooth;

import android.R;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeBroadcastAssistant;
import android.bluetooth.BluetoothLeBroadcastReceiveState;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.keyboard.KeyboardUI;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BluetoothUtils {
    public static KeyboardUI.BluetoothErrorListener sErrorListener;

    static {
        ImmutableSet.construct(3, 2, 22, 21);
    }

    public static boolean getBooleanMetaData(BluetoothDevice bluetoothDevice) {
        byte[] metadata;
        if (bluetoothDevice == null || (metadata = bluetoothDevice.getMetadata(6)) == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(metadata));
    }

    public static Pair getBtClassDrawableWithDescription(Context context, CachedBluetoothDevice cachedBluetoothDevice) {
        int i;
        BluetoothClass bluetoothClass = cachedBluetoothDevice.mDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
            if (majorDeviceClass == 256) {
                return new Pair(context.getDrawable(R.drawable.ic_audio_vol_mute), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_computer));
            }
            if (majorDeviceClass == 512) {
                return new Pair(context.getDrawable(R.drawable.ic_perm_group_status_bar), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_phone));
            }
            if (majorDeviceClass == 1280) {
                int deviceClass = bluetoothClass.getDeviceClass();
                if (deviceClass != 1344) {
                    if (deviceClass == 1408) {
                        i = R.drawable.ic_bluetooth_transient_animation;
                    } else if (deviceClass != 1472) {
                        i = R.drawable.ic_battery;
                    }
                    return new Pair(context.getDrawable(i), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_input_peripheral));
                }
                i = R.drawable.ic_lockscreen_forgotpassword_normal;
                return new Pair(context.getDrawable(i), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_input_peripheral));
            }
            if (majorDeviceClass == 1536) {
                return new Pair(context.getDrawable(R.drawable.ic_search_api_holo_dark), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_imaging));
            }
        }
        if (cachedBluetoothDevice.isHearingAidDevice()) {
            return new Pair(context.getDrawable(R.drawable.ic_audio_vol), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_hearing_aids));
        }
        int i2 = 0;
        for (LocalBluetoothProfile localBluetoothProfile : cachedBluetoothDevice.getProfiles()) {
            int drawableResource = localBluetoothProfile.getDrawableResource(bluetoothClass);
            if (drawableResource != 0) {
                if ((localBluetoothProfile instanceof HearingAidProfile) || (localBluetoothProfile instanceof HapClientProfile)) {
                    return new Pair(context.getDrawable(drawableResource), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_hearing_aids));
                }
                if (i2 == 0) {
                    i2 = drawableResource;
                }
            }
        }
        if (i2 != 0) {
            return new Pair(context.getDrawable(i2), null);
        }
        if (bluetoothClass != null) {
            if (bluetoothClass.doesClassMatch(0)) {
                return new Pair(context.getDrawable(R.drawable.ic_audio_ring_notif_vibrate), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_headset));
            }
            if (bluetoothClass.doesClassMatch(1)) {
                return new Pair(context.getDrawable(R.drawable.ic_audio_ring_notif_mute), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_headphone));
            }
        }
        return new Pair(context.getDrawable(R.drawable.ic_sd_card_48dp).mutate(), context.getString(com.android.wm.shell.R.string.bluetooth_talkback_bluetooth));
    }

    public static String getControlUriMetaData(BluetoothDevice bluetoothDevice) {
        byte[] metadata;
        String str = (bluetoothDevice == null || (metadata = bluetoothDevice.getMetadata(25)) == null) ? null : new String(metadata);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Locale locale = Locale.ENGLISH;
        Matcher matcher = Pattern.compile("<HEARABLE_CONTROL_SLICE_WITH_WIDTH>(.*?)</HEARABLE_CONTROL_SLICE_WITH_WIDTH>").matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static int getIntMetaData(BluetoothDevice bluetoothDevice, int i) {
        byte[] metadata;
        if (bluetoothDevice == null || (metadata = bluetoothDevice.getMetadata(i)) == null) {
            return -1;
        }
        try {
            return Integer.parseInt(new String(metadata));
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static boolean hasConnectedBroadcastSourceForBtDevice(BluetoothDevice bluetoothDevice, LocalBluetoothManager localBluetoothManager) {
        List allSources;
        LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant = localBluetoothManager == null ? null : localBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
        if (bluetoothDevice == null || localBluetoothLeBroadcastAssistant == null) {
            Log.d("BluetoothUtils", "Skip check hasConnectedBroadcastSourceForBtDevice due to arg is null");
            return false;
        }
        Log.d("LocalBluetoothLeBroadcastAssistant", "getAllSources()");
        BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = localBluetoothLeBroadcastAssistant.mService;
        if (bluetoothLeBroadcastAssistant == null) {
            Log.d("LocalBluetoothLeBroadcastAssistant", "The BluetoothLeBroadcastAssistant is null");
            allSources = new ArrayList();
        } else {
            allSources = bluetoothLeBroadcastAssistant.getAllSources(bluetoothDevice);
        }
        return !allSources.isEmpty() && allSources.stream().anyMatch(new BluetoothUtils$$ExternalSyntheticLambda0(0));
    }

    public static boolean isActiveMediaDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        return cachedBluetoothDevice.isActiveDevice(2) || cachedBluetoothDevice.isActiveDevice(1) || cachedBluetoothDevice.isActiveDevice(21) || cachedBluetoothDevice.isActiveDevice(22);
    }

    public static boolean isAvailableHearingDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        if (!isDeviceConnected(cachedBluetoothDevice)) {
            return false;
        }
        if (!cachedBluetoothDevice.isConnectedAshaHearingAidDevice() && (!cachedBluetoothDevice.isConnectedHapClientDevice() || !cachedBluetoothDevice.isConnectedLeAudioDevice())) {
            return false;
        }
        Log.d("BluetoothUtils", "isFilterMatched() device : " + cachedBluetoothDevice.getName() + ", the profile is connected.");
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0036, code lost:
    
        if (r5.getConnectionStatus(r4.mDevice) == 2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0048, code lost:
    
        if (r5.getConnectionStatus(r4.mDevice) == 2) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isAvailableMediaBluetoothDevice(com.android.settingslib.bluetooth.CachedBluetoothDevice r4, android.media.AudioManager r5) {
        /*
            int r5 = r5.getMode()
            r0 = 2
            r1 = 1
            if (r5 == r1) goto L10
            if (r5 == r0) goto L10
            r2 = 3
            if (r5 != r2) goto Le
            goto L10
        Le:
            r5 = r0
            goto L11
        L10:
            r5 = r1
        L11:
            boolean r2 = isDeviceConnected(r4)
            r3 = 0
            if (r2 == 0) goto L68
            boolean r2 = r4.isConnectedAshaHearingAidDevice()
            if (r2 != 0) goto L4b
            boolean r2 = r4.isConnectedLeAudioDevice()
            if (r2 == 0) goto L25
            goto L4b
        L25:
            if (r5 == r1) goto L3c
            if (r5 == r0) goto L2a
            goto L68
        L2a:
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r5 = r4.mProfileManager
            com.android.settingslib.bluetooth.A2dpProfile r5 = r5.mA2dpProfile
            if (r5 == 0) goto L39
            android.bluetooth.BluetoothDevice r4 = r4.mDevice
            int r4 = r5.getConnectionStatus(r4)
            if (r4 != r0) goto L39
            goto L3a
        L39:
            r1 = r3
        L3a:
            r3 = r1
            goto L68
        L3c:
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r5 = r4.mProfileManager
            com.android.settingslib.bluetooth.HeadsetProfile r5 = r5.mHeadsetProfile
            if (r5 == 0) goto L39
            android.bluetooth.BluetoothDevice r4 = r4.mDevice
            int r4 = r5.getConnectionStatus(r4)
            if (r4 != r0) goto L39
            goto L3a
        L4b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "isFilterMatched() device : "
            r5.<init>(r0)
            java.lang.String r4 = r4.getName()
            r5.append(r4)
            java.lang.String r4 = ", the profile is connected."
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "BluetoothUtils"
            android.util.Log.d(r5, r4)
            return r1
        L68:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.BluetoothUtils.isAvailableMediaBluetoothDevice(com.android.settingslib.bluetooth.CachedBluetoothDevice, android.media.AudioManager):boolean");
    }

    public static boolean isConnected(BluetoothLeBroadcastReceiveState bluetoothLeBroadcastReceiveState) {
        return bluetoothLeBroadcastReceiveState.getBisSyncState().stream().anyMatch(new BluetoothUtils$$ExternalSyntheticLambda0(1));
    }

    public static boolean isDeviceConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice == null) {
            return false;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        return bluetoothDevice.getBondState() == 12 && bluetoothDevice.isConnected();
    }

    public static boolean isExclusivelyManagedBluetoothDevice(Context context, BluetoothDevice bluetoothDevice) {
        String str;
        boolean z;
        byte[] metadata = bluetoothDevice.getMetadata(29);
        if (metadata == null) {
            Log.d("BluetoothUtils", "Bluetooth device " + bluetoothDevice.getName() + " doesn't have exclusive manager");
            str = null;
        } else {
            str = new String(metadata);
        }
        if (str == null) {
            return false;
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        if (unflattenFromString != null) {
            str = unflattenFromString.getPackageName();
        }
        try {
            z = context.getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d("BluetoothUtils", "Package " + str + " is not installed/enabled");
            z = false;
        }
        if (!z) {
            return false;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Found exclusively managed app ", str, "BluetoothUtils");
        return true;
    }
}
