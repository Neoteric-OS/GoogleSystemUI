package com.google.android.settingslib.dcservice;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DcServiceClientImpl {
    public static final Uri PROXY_AUTHORITY = new Uri.Builder().scheme("content").authority("com.google.android.settings.intelligence.provider.dcservice").build();
    public final ContentResolver contentResolver;
    public final LocalBluetoothProfileManager profileManager;

    public DcServiceClientImpl(Context context, LocalBluetoothManager localBluetoothManager) {
        this.contentResolver = context.getContentResolver();
        this.profileManager = localBluetoothManager.mProfileManager;
    }
}
