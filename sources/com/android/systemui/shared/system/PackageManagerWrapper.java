package com.android.systemui.shared.system;

import android.app.AppGlobals;
import android.content.pm.IPackageManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PackageManagerWrapper {
    public static final PackageManagerWrapper sInstance = new PackageManagerWrapper();
    public static final IPackageManager mIPackageManager = AppGlobals.getPackageManager();
}
