package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.collection.ArrayMap;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bindIcons$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function3 $bindIcon;
    final /* synthetic */ StateFlow $layoutParams;
    final /* synthetic */ String $logTag;
    final /* synthetic */ Function1 $notifyBindingFailures;
    final /* synthetic */ Flow $this_bindIcons;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconContainerViewBinder$bindIcons$5(Flow flow, String str, NotificationIconContainer notificationIconContainer, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Function1 function1, StateFlow stateFlow, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_bindIcons = flow;
        this.$logTag = str;
        this.$view = notificationIconContainer;
        this.$viewStore = iconViewStore;
        this.$notifyBindingFailures = function1;
        this.$layoutParams = stateFlow;
        this.$bindIcon = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainerViewBinder$bindIcons$5 notificationIconContainerViewBinder$bindIcons$5 = new NotificationIconContainerViewBinder$bindIcons$5(this.$this_bindIcons, this.$logTag, this.$view, this.$viewStore, this.$notifyBindingFailures, this.$layoutParams, this.$bindIcon, continuation);
        notificationIconContainerViewBinder$bindIcons$5.L$0 = obj;
        return notificationIconContainerViewBinder$bindIcons$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationIconContainerViewBinder$bindIcons$5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            final ArrayMap arrayMap = new ArrayMap(0);
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ref$ObjectRef.element = new NotificationIconsViewData(0, 7, (List) null);
            Flow flow = this.$this_bindIcons;
            final String str = this.$logTag;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIcons$5.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("NIC(", str, ")#bindIcons");
                }
            };
            final NotificationIconContainer notificationIconContainer = this.$view;
            final NotificationIconContainerViewBinder.IconViewStore iconViewStore = this.$viewStore;
            final Function1 function1 = this.$notifyBindingFailures;
            final StateFlow stateFlow = this.$layoutParams;
            final Function3 function3 = this.$bindIcon;
            final Lazy lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, function0);
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIcons$5$invokeSuspend$$inlined$collectTracingEach$1
                /* JADX WARN: Finally extract failed */
                /* JADX WARN: Removed duplicated region for block: B:146:0x025e  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r21, kotlin.coroutines.Continuation r22) {
                    /*
                        Method dump skipped, instructions count: 610
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIcons$5$invokeSuspend$$inlined$collectTracingEach$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            };
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
