package com.google.android.systemui.ailabs.ui.data.repository;

import android.content.ComponentName;
import android.content.Intent;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WarpLockscreenRepository {
    public final ActivityStarter activityStarter;
    public final BroadcastDispatcher broadcastDispatcher;
    public final Function0 launchWarpActivity;
    public final ReadonlyStateFlow warps;

    public WarpLockscreenRepository(CoroutineScope coroutineScope, BroadcastDispatcher broadcastDispatcher, ActivityStarter activityStarter) {
        this.broadcastDispatcher = broadcastDispatcher;
        this.activityStarter = activityStarter;
        this.warps = FlowKt.stateIn(FlowKt.callbackFlow(new WarpLockscreenRepository$warps$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        new Function0() { // from class: com.google.android.systemui.ailabs.ui.data.repository.WarpLockscreenRepository$launchWarpActivity$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Intent intent = new Intent("android.intent.action.MAIN");
                WarpLockscreenRepository warpLockscreenRepository = WarpLockscreenRepository.this;
                intent.setComponent(new ComponentName("com.google.android.apps.warp", "com.google.android.apps.warp.MainActivity"));
                intent.addFlags(335544320);
                warpLockscreenRepository.activityStarter.startActivity(intent, true);
                return Unit.INSTANCE;
            }
        };
    }
}
