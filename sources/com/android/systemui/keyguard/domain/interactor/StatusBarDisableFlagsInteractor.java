package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import kotlin.Pair;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatusBarDisableFlagsInteractor implements CoreStartable {
    public final Context applicationContext;
    public final CoroutineDispatcher backgroundDispatcher;
    public final DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 disableFlagsForUserId;
    public final IBinder disableToken;
    public final IStatusBarService statusBarService;

    public StatusBarDisableFlagsInteractor(CoroutineScope coroutineScope, Context context, CoroutineDispatcher coroutineDispatcher, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, IStatusBarService iStatusBarService) {
        new Binder();
        new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new Pair(0, 0));
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
