package com.android.systemui.mediarouter.data.repository;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.policy.CastDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaRouterRepositoryImpl$castDevices$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaRouterRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRouterRepositoryImpl$castDevices$2(MediaRouterRepositoryImpl mediaRouterRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaRouterRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MediaRouterRepositoryImpl$castDevices$2 mediaRouterRepositoryImpl$castDevices$2 = new MediaRouterRepositoryImpl$castDevices$2(this.this$0, continuation);
        mediaRouterRepositoryImpl$castDevices$2.L$0 = obj;
        return mediaRouterRepositoryImpl$castDevices$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MediaRouterRepositoryImpl$castDevices$2 mediaRouterRepositoryImpl$castDevices$2 = (MediaRouterRepositoryImpl$castDevices$2) create((List) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        mediaRouterRepositoryImpl$castDevices$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        Intrinsics.checkNotNull(list);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((CastDevice) it.next()).shortLogString);
        }
        String obj2 = arrayList.toString();
        LogBuffer logBuffer = this.this$0.logger;
        LogMessage obtain = logBuffer.obtain("MediaRouterRepo", LogLevel.INFO, new Function1() { // from class: com.android.systemui.mediarouter.data.repository.MediaRouterRepositoryImpl$castDevices$2.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("All cast devices: ", ((LogMessage) obj3).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = obj2;
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
