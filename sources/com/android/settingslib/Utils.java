package com.android.settingslib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.UserInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbPort;
import android.hardware.usb.UsbPortStatus;
import android.icu.text.NumberFormat;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import android.util.Log;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.launcher3.icons.IconFactory;
import com.android.launcher3.util.UserIconInfo;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Utils {
    static final String STORAGE_MANAGER_ENABLED_PROPERTY = "ro.storage_manager.enabled";

    public static boolean containsIncompatibleChargers(Context context, String str) {
        List<UsbPort> ports;
        UsbPortStatus status;
        int[] complianceWarnings;
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "incompatible_charger_warning_disabled", 0) == 1) {
                Log.d(str, "containsIncompatibleChargers: disabled");
                return false;
            }
            UsbManager usbManager = (UsbManager) context.getSystemService(UsbManager.class);
            if (usbManager != null && (ports = usbManager.getPorts()) != null && !ports.isEmpty()) {
                for (UsbPort usbPort : ports) {
                    Log.d(str, "usbPort: " + usbPort);
                    if (usbPort.supportsComplianceWarnings() && (status = usbPort.getStatus()) != null && status.isConnected() && (complianceWarnings = status.getComplianceWarnings()) != null && complianceWarnings.length != 0) {
                        for (int i : complianceWarnings) {
                            if (i == 2 || i == 5) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(str, "containsIncompatibleChargers()", e);
            return false;
        }
    }

    public static String formatPercentage(int i) {
        return NumberFormat.getPercentInstance().format(i / 100.0d);
    }

    public static FastBitmapDrawable getBadgedIcon(Context context, ApplicationInfo applicationInfo) {
        int i;
        IconFactory obtain;
        UserInfo userInfo;
        Drawable loadUnbadgedIcon = applicationInfo.loadUnbadgedIcon(context.getPackageManager());
        UserHandle userHandleForUid = UserHandle.getUserHandleForUid(applicationInfo.uid);
        try {
            userInfo = ((UserManager) context.getSystemService(UserManager.class)).getUserInfo(userHandleForUid.getIdentifier());
        } catch (Exception unused) {
        }
        try {
            if (userInfo != null) {
                if (userInfo.isCloneProfile()) {
                    i = 2;
                } else if (userInfo.isManagedProfile()) {
                    i = 1;
                } else if (userInfo.isPrivateProfile()) {
                    i = 3;
                }
                obtain = IconFactory.obtain(context);
                BaseIconFactory.IconOptions iconOptions = new BaseIconFactory.IconOptions();
                iconOptions.mUserIconInfo = new UserIconInfo(userHandleForUid, i);
                FastBitmapDrawable newIcon = obtain.createBadgedIconBitmap(loadUnbadgedIcon, iconOptions).newIcon(0, context);
                obtain.close();
                return newIcon;
            }
            BaseIconFactory.IconOptions iconOptions2 = new BaseIconFactory.IconOptions();
            iconOptions2.mUserIconInfo = new UserIconInfo(userHandleForUid, i);
            FastBitmapDrawable newIcon2 = obtain.createBadgedIconBitmap(loadUnbadgedIcon, iconOptions2).newIcon(0, context);
            obtain.close();
            return newIcon2;
        } catch (Throwable th) {
            try {
                obtain.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
        i = 0;
        obtain = IconFactory.obtain(context);
    }

    public static ColorStateList getColorAttr(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getColorStateList(0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int getColorAttrDefaultColor(int i, int i2, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, i2);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static int getColorStateListDefaultColor(int i, Context context) {
        return context.getResources().getColorStateList(i, context.getTheme()).getDefaultColor();
    }

    public static int getThemeAttr(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static boolean isInService(ServiceState serviceState) {
        if (serviceState == null) {
            return false;
        }
        int voiceRegState = serviceState.getVoiceRegState();
        if (voiceRegState == 1 || voiceRegState == 2) {
            NetworkRegistrationInfo networkRegistrationInfo = serviceState.getNetworkRegistrationInfo(2, 1);
            if (networkRegistrationInfo == null ? false : networkRegistrationInfo.isInService()) {
                voiceRegState = 0;
            }
        }
        return (voiceRegState == 3 || voiceRegState == 1 || voiceRegState == 2) ? false : true;
    }
}
