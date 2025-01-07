package com.android.systemui.communal.ui.viewmodel;

import com.android.systemui.media.controls.ui.view.MediaHost;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalViewModel$ongoingContent$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaHost $mediaHost;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalViewModel$ongoingContent$1$1(MediaHost mediaHost, Continuation continuation) {
        super(2, continuation);
        this.$mediaHost = mediaHost;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalViewModel$ongoingContent$1$1(this.$mediaHost, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalViewModel$ongoingContent$1$1 communalViewModel$ongoingContent$1$1 = (CommunalViewModel$ongoingContent$1$1) create((List) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalViewModel$ongoingContent$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$mediaHost.updateViewVisibility();
        return Unit.INSTANCE;
    }
}
