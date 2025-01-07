package com.google.android.systemui.keyguard;

import android.content.res.Resources;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RefreshRateRequesterBinder implements CoreStartable {
    public final Lazy interactor;
    public final Resources resources;
    public final CoroutineScope scope;

    public RefreshRateRequesterBinder(Resources resources, Lazy lazy, CoroutineScope coroutineScope) {
        this.resources = resources;
        this.interactor = lazy;
        this.scope = coroutineScope;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("enabled: ", this.resources.getBoolean(R.bool.config_request_pre_auth_refresh_rate), printWriter);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.resources.getBoolean(R.bool.config_request_pre_auth_refresh_rate)) {
            BuildersKt.launch$default(this.scope, null, null, new RefreshRateRequesterBinder$start$1(this, null), 3);
        }
    }
}
