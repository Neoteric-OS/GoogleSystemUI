package com.android.systemui.dreams.homecontrols;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.dreams.homecontrols.domain.interactor.HomeControlsComponentInteractor;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsDreamStartable implements CoreStartable {
    public final CoroutineScope bgScope;
    public final ComponentName componentName;
    public final HomeControlsComponentInteractor homeControlsComponentInteractor;
    public final PackageManager packageManager;

    public HomeControlsDreamStartable(Context context, PackageManager packageManager, HomeControlsComponentInteractor homeControlsComponentInteractor, CoroutineScope coroutineScope) {
        this.packageManager = packageManager;
        this.homeControlsComponentInteractor = homeControlsComponentInteractor;
        this.bgScope = coroutineScope;
        this.componentName = new ComponentName(context, (Class<?>) HomeControlsDreamService.class);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.bgScope, null, null, new HomeControlsDreamStartable$start$1(this, null), 3);
    }
}
