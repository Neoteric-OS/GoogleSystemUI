package com.android.systemui.util.animation.data.repository;

import android.content.ContentResolver;
import android.os.Handler;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnimationStatusRepositoryImpl {
    public final CoroutineDispatcher backgroundDispatcher;
    public final Handler backgroundHandler;
    public final ContentResolver resolver;

    public AnimationStatusRepositoryImpl(ContentResolver contentResolver, Handler handler, CoroutineDispatcher coroutineDispatcher) {
        this.resolver = contentResolver;
        this.backgroundHandler = handler;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public final Flow areAnimationsEnabled() {
        return FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new AnimationStatusRepositoryImpl$areAnimationsEnabled$1(this, null)), this.backgroundDispatcher);
    }
}
