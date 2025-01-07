package com.android.systemui.communal.data.repository;

import android.content.IntentFilter;
import android.content.pm.UserInfo;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalPrefsRepositoryImpl {
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 backupRestorationEvents;
    public final CoroutineDispatcher bgDispatcher;
    public final Lazy logger$delegate;
    public final UserFileManager userFileManager;

    public CommunalPrefsRepositoryImpl(CoroutineDispatcher coroutineDispatcher, UserFileManager userFileManager, BroadcastDispatcher broadcastDispatcher, final LogBuffer logBuffer) {
        this.bgDispatcher = coroutineDispatcher;
        this.userFileManager = userFileManager;
        this.logger$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.communal.data.repository.CommunalPrefsRepositoryImpl$logger$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new Logger(LogBuffer.this, "CommunalPrefsRepository");
            }
        });
        this.backupRestorationEvents = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("com.android.systemui.backup.RESTORE_FINISHED"), null, 2), new CommunalPrefsRepositoryImpl$backupRestorationEvents$1(this, null), 0));
    }

    public final Object setCtaDismissed(UserInfo userInfo, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.bgDispatcher, new CommunalPrefsRepositoryImpl$setCtaDismissed$2(this, userInfo, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
