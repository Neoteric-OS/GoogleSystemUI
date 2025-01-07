package com.android.systemui.biometrics.ui.viewmodel;

import android.graphics.Point;
import android.view.WindowManager;
import com.android.systemui.biometrics.domain.model.SideFpsSensorLocation;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewModel$overlayViewParams$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SideFpsOverlayViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideFpsOverlayViewModel$overlayViewParams$1(SideFpsOverlayViewModel sideFpsOverlayViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = sideFpsOverlayViewModel;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        SideFpsOverlayViewModel$overlayViewParams$1 sideFpsOverlayViewModel$overlayViewParams$1 = new SideFpsOverlayViewModel$overlayViewParams$1(this.this$0, (Continuation) obj4);
        sideFpsOverlayViewModel$overlayViewParams$1.L$0 = (SideFpsSensorLocation) obj2;
        return sideFpsOverlayViewModel$overlayViewParams$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SideFpsSensorLocation sideFpsSensorLocation = (SideFpsSensorLocation) this.L$0;
        Point point = new Point(sideFpsSensorLocation.left, sideFpsSensorLocation.top);
        this.this$0.getClass();
        WindowManager.LayoutParams defaultOverlayViewParams = SideFpsOverlayViewModel.getDefaultOverlayViewParams();
        defaultOverlayViewParams.x = point.x;
        defaultOverlayViewParams.y = point.y;
        return defaultOverlayViewParams;
    }
}
