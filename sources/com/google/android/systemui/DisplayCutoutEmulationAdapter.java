package com.google.android.systemui;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayCutoutEmulationAdapter {
    public final Context mContext;
    public final AnonymousClass1 mObserver;
    public final IOverlayManager mOverlayManager;

    public DisplayCutoutEmulationAdapter(Context context) {
        ContentObserver contentObserver = new ContentObserver(Handler.getMain()) { // from class: com.google.android.systemui.DisplayCutoutEmulationAdapter.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                DisplayCutoutEmulationAdapter.this.update();
            }
        };
        this.mContext = context;
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("com.google.android.systemui.display_cutout_emulation"), false, contentObserver, 0);
        update();
    }

    public final void update() {
        String stringForUser = Settings.Global.getStringForUser(this.mContext.getContentResolver(), "com.google.android.systemui.display_cutout_emulation", 0);
        if (stringForUser == null) {
            return;
        }
        String[] split = stringForUser.split(":", 2);
        try {
            int parseInt = Integer.parseInt(split[0]);
            String str = split[1];
            if (parseInt <= PreferenceManager.getDefaultSharedPreferences(this.mContext).getInt("com.google.android.systemui.display_cutout_emulation.VERSION", -1)) {
                return;
            }
            if (!str.isEmpty() && !str.startsWith("com.android.internal.display.cutout.emulation")) {
                Log.e("CutoutEmulationAdapter", "Invalid overlay prefix: ".concat(stringForUser));
                return;
            }
            try {
                List overlayInfosForTarget = this.mOverlayManager.getOverlayInfosForTarget("android", 0);
                for (int size = overlayInfosForTarget.size() - 1; size >= 0; size--) {
                    if (!((OverlayInfo) overlayInfosForTarget.get(size)).packageName.startsWith("com.android.internal.display.cutout.emulation")) {
                        overlayInfosForTarget.remove(size);
                    }
                }
                OverlayInfo[] overlayInfoArr = (OverlayInfo[]) overlayInfosForTarget.toArray(new OverlayInfo[overlayInfosForTarget.size()]);
                String str2 = null;
                for (OverlayInfo overlayInfo : overlayInfoArr) {
                    if (overlayInfo.isEnabled()) {
                        str2 = overlayInfo.packageName;
                    }
                }
                if ((!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) && !TextUtils.equals(str, str2)) {
                    for (OverlayInfo overlayInfo2 : overlayInfoArr) {
                        boolean isEnabled = overlayInfo2.isEnabled();
                        boolean equals = TextUtils.equals(overlayInfo2.packageName, str);
                        if (isEnabled != equals) {
                            try {
                                this.mOverlayManager.setEnabled(overlayInfo2.packageName, equals, 0);
                            } catch (RemoteException e) {
                                throw e.rethrowFromSystemServer();
                            }
                        }
                    }
                }
                PreferenceManager.getDefaultSharedPreferences(this.mContext).edit().putInt("com.google.android.systemui.display_cutout_emulation.VERSION", parseInt).apply();
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e3) {
            Log.e("CutoutEmulationAdapter", "Invalid configuration: ".concat(stringForUser), e3);
        }
    }
}
