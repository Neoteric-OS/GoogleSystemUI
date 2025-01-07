package com.android.wm.shell.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.R;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultiInstanceHelper {
    public final PackageManager packageManager;
    public final String[] staticAppsSupportingMultiInstance;
    public final boolean supportsMultiInstanceProperty;

    public MultiInstanceHelper(Context context, PackageManager packageManager) {
        String[] stringArray = context.getResources().getStringArray(R.array.config_appsSupportMultiInstancesSplit);
        this.packageManager = packageManager;
        this.staticAppsSupportingMultiInstance = stringArray;
        this.supportsMultiInstanceProperty = true;
    }

    public static final boolean samePackage(String str, int i, int i2, String str2) {
        return str != null && str.equals(str2) && i == i2;
    }

    public final boolean supportsMultiInstanceSplit(ComponentName componentName) {
        PackageManager.Property property;
        if (componentName != null && componentName.getPackageName() != null) {
            String packageName = componentName.getPackageName();
            for (String str : this.staticAppsSupportingMultiInstance) {
                if (Intrinsics.areEqual(str, packageName)) {
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL, "application=%s in allowlist supports multi-instance", new Object[]{packageName});
                    return true;
                }
            }
            if (!this.supportsMultiInstanceProperty) {
                return false;
            }
            try {
                property = this.packageManager.getProperty("android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI", componentName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (property.isBoolean()) {
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL, "activity=%s supports multi-instance", new Object[]{componentName});
                return property.getBoolean();
            }
            ProtoLog.w(ShellProtoLogGroup.WM_SHELL, "Warning: property=%s for activity=%s has non-bool type=%d", new Object[]{"android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI", packageName, Integer.valueOf(property.getType())});
            try {
                PackageManager.Property property2 = this.packageManager.getProperty("android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI", packageName);
                if (property2.isBoolean()) {
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL, "application=%s supports multi-instance", new Object[]{packageName});
                    return property2.getBoolean();
                }
                ProtoLog.w(ShellProtoLogGroup.WM_SHELL, "Warning: property=%s for application=%s has non-bool type=%d", new Object[]{"android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI", packageName, Integer.valueOf(property2.getType())});
            } catch (PackageManager.NameNotFoundException unused2) {
            }
        }
        return false;
    }
}
