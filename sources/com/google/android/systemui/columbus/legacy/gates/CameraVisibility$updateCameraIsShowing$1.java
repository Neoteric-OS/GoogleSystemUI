package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CameraVisibility$updateCameraIsShowing$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ CameraVisibility this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraVisibility$updateCameraIsShowing$1(CameraVisibility cameraVisibility, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraVisibility;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CameraVisibility$updateCameraIsShowing$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CameraVisibility$updateCameraIsShowing$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CameraVisibility cameraVisibility;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CameraVisibility cameraVisibility2 = this.this$0;
            this.L$0 = cameraVisibility2;
            this.label = 1;
            Object access$isCameraShowing = CameraVisibility.access$isCameraShowing(cameraVisibility2, this);
            if (access$isCameraShowing == coroutineSingletons) {
                return coroutineSingletons;
            }
            cameraVisibility = cameraVisibility2;
            obj = access$isCameraShowing;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            cameraVisibility = (CameraVisibility) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        cameraVisibility.cameraShowing = ((Boolean) obj).booleanValue();
        CameraVisibility cameraVisibility3 = this.this$0;
        BuildersKt.launch$default(cameraVisibility3.coroutineScope, null, null, new CameraVisibility$updateBlocking$1(cameraVisibility3, null), 3);
        return Unit.INSTANCE;
    }
}
