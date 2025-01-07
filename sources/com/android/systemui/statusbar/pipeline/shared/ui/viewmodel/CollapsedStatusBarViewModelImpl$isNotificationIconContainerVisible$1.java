package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.shared.domain.model.StatusBarDisableFlagsVisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CollapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ CollapsedStatusBarViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1(CollapsedStatusBarViewModelImpl collapsedStatusBarViewModelImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = collapsedStatusBarViewModelImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        CollapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1 collapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1 = new CollapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1(this.this$0, (Continuation) obj3);
        collapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1.Z$0 = booleanValue;
        collapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1.L$0 = (StatusBarDisableFlagsVisibilityModel) obj2;
        return collapsedStatusBarViewModelImpl$isNotificationIconContainerVisible$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        StatusBarDisableFlagsVisibilityModel statusBarDisableFlagsVisibilityModel = (StatusBarDisableFlagsVisibilityModel) this.L$0;
        boolean z2 = z && statusBarDisableFlagsVisibilityModel.areNotificationIconsAllowed;
        this.this$0.getClass();
        return new CollapsedStatusBarViewModel.VisibilityModel(z2 ? 0 : 8, statusBarDisableFlagsVisibilityModel.animate);
    }
}
