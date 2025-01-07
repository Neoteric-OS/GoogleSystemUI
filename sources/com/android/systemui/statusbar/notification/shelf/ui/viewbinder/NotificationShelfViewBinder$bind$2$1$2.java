package com.android.systemui.statusbar.notification.shelf.ui.viewbinder;

import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel$special$$inlined$map$1$2;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationShelfViewBinder$bind$2$1$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationShelf $this_apply;
    final /* synthetic */ NotificationShelfViewModel $viewModel;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.shelf.ui.viewbinder.NotificationShelfViewBinder$bind$2$1$2$1, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass1 implements FlowCollector, FunctionAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ NotificationShelf $tmp0;

        public /* synthetic */ AnonymousClass1(NotificationShelf notificationShelf, int i) {
            this.$r8$classId = i;
            this.$tmp0 = notificationShelf;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Unit unit = Unit.INSTANCE;
            NotificationShelf notificationShelf = this.$tmp0;
            switch (this.$r8$classId) {
                case 0:
                    notificationShelf.mCanModifyColorOfNotifications = ((Boolean) obj).booleanValue();
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    break;
                default:
                    notificationShelf.mCanInteract = ((Boolean) obj).booleanValue();
                    notificationShelf.updateInteractiveness();
                    CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                    break;
            }
            return unit;
        }

        public final boolean equals(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        break;
                    }
                    break;
                default:
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        break;
                    }
                    break;
            }
            return getFunctionDelegate().equals(((FunctionAdapter) obj).getFunctionDelegate());
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function getFunctionDelegate() {
            switch (this.$r8$classId) {
                case 0:
                    return new AdaptedFunctionReference(2, this.$tmp0, NotificationShelf.class, "setCanModifyColorOfNotifications", "setCanModifyColorOfNotifications(Z)V", 4);
                default:
                    return new AdaptedFunctionReference(2, this.$tmp0, NotificationShelf.class, "setCanInteract", "setCanInteract(Z)V", 4);
            }
        }

        public final int hashCode() {
            switch (this.$r8$classId) {
            }
            return getFunctionDelegate().hashCode();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationShelfViewBinder$bind$2$1$2(NotificationShelfViewModel notificationShelfViewModel, NotificationShelf notificationShelf, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = notificationShelfViewModel;
        this.$this_apply = notificationShelf;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationShelfViewBinder$bind$2$1$2(this.$viewModel, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationShelfViewBinder$bind$2$1$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isShelfStatic = this.$viewModel.interactor.isShelfStatic();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_apply, 0);
            this.label = 1;
            Object collect = isShelfStatic.collect(new NotificationShelfViewModel$special$$inlined$map$1$2(anonymousClass1), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
