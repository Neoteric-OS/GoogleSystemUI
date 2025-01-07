package com.android.systemui.mediaprojection.appselector.data;

import android.content.pm.PackageManager;
import com.android.systemui.shared.system.PackageManagerWrapper;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BasicPackageManagerAppIconLoader {
    public final CoroutineDispatcher backgroundDispatcher;
    public final PackageManager packageManager;
    public final PackageManagerWrapper packageManagerWrapper;

    public BasicPackageManagerAppIconLoader(CoroutineDispatcher coroutineDispatcher, PackageManagerWrapper packageManagerWrapper, PackageManager packageManager) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.packageManagerWrapper = packageManagerWrapper;
        this.packageManager = packageManager;
    }
}
