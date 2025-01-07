package com.android.systemui.unfold;

import android.R;
import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.unfold.data.repository.FoldStateRepositoryImpl;
import com.android.systemui.unfold.system.DeviceStateRepositoryImpl;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTraceLogger implements CoreStartable {
    public final ContextScope bgScope;
    public final Context context;
    public final DeviceStateRepositoryImpl deviceStateRepository;
    public final FoldStateRepositoryImpl foldStateRepository;

    public UnfoldTraceLogger(Context context, FoldStateRepositoryImpl foldStateRepositoryImpl, CoroutineScope coroutineScope, CoroutineContext coroutineContext, DeviceStateRepositoryImpl deviceStateRepositoryImpl) {
        this.context = context;
        this.foldStateRepository = foldStateRepositoryImpl;
        this.deviceStateRepository = deviceStateRepositoryImpl;
        this.bgScope = new ContextScope(coroutineScope.getCoroutineContext().plus(coroutineContext));
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.context.getResources().getIntArray(R.array.config_fontManagerServiceCerts).length == 0) {
            return;
        }
        UnfoldTraceLogger$start$1 unfoldTraceLogger$start$1 = new UnfoldTraceLogger$start$1(this, null);
        ContextScope contextScope = this.bgScope;
        BuildersKt.launch$default(contextScope, null, null, unfoldTraceLogger$start$1, 3);
        BuildersKt.launch$default(contextScope, null, null, new UnfoldTraceLogger$start$2(this, null), 3);
        BuildersKt.launch$default(contextScope, null, null, new UnfoldTraceLogger$start$3(this, null), 3);
    }
}
