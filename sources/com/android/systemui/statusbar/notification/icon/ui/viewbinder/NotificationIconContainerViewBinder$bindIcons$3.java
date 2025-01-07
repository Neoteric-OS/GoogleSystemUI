package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.R;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.ConfigurationStateImpl;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bindIcons$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function3 $bindIcon;
    final /* synthetic */ ConfigurationState $configuration;
    final /* synthetic */ String $logTag;
    final /* synthetic */ Function1 $notifyBindingFailures;
    final /* synthetic */ SystemBarUtilsState $systemBarUtilsState;
    final /* synthetic */ Flow $this_bindIcons;
    final /* synthetic */ NotificationIconContainer $view;
    final /* synthetic */ NotificationIconContainerViewBinder.IconViewStore $viewStore;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconContainerViewBinder$bindIcons$3(ConfigurationState configurationState, SystemBarUtilsState systemBarUtilsState, Flow flow, String str, NotificationIconContainer notificationIconContainer, Function1 function1, NotificationIconContainerViewBinder.IconViewStore iconViewStore, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$configuration = configurationState;
        this.$systemBarUtilsState = systemBarUtilsState;
        this.$this_bindIcons = flow;
        this.$logTag = str;
        this.$view = notificationIconContainer;
        this.$notifyBindingFailures = function1;
        this.$viewStore = iconViewStore;
        this.$bindIcon = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationIconContainerViewBinder$bindIcons$3 notificationIconContainerViewBinder$bindIcons$3 = new NotificationIconContainerViewBinder$bindIcons$3(this.$configuration, this.$systemBarUtilsState, this.$this_bindIcons, this.$logTag, this.$view, this.$notifyBindingFailures, this.$viewStore, this.$bindIcon, continuation);
        notificationIconContainerViewBinder$bindIcons$3.L$0 = obj;
        return notificationIconContainerViewBinder$bindIcons$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationIconContainerViewBinder$bindIcons$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder$bindIcons$3, kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.ContinuationImpl] */
    /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object stateIn;
        NotificationIconContainerViewBinder$bindIcons$3 notificationIconContainerViewBinder$bindIcons$3 = this;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = notificationIconContainerViewBinder$bindIcons$3.label;
        Unit unit = Unit.INSTANCE;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) notificationIconContainerViewBinder$bindIcons$3.L$0;
                FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(((ConfigurationStateImpl) notificationIconContainerViewBinder$bindIcons$3.$configuration).getDimensionPixelSize(R.dimen.text_edit_floating_toolbar_elevation), ((ConfigurationStateImpl) notificationIconContainerViewBinder$bindIcons$3.$configuration).getDimensionPixelSize(com.android.wm.shell.R.dimen.status_bar_icon_horizontal_margin), notificationIconContainerViewBinder$bindIcons$3.$systemBarUtilsState.statusBarHeight, new NotificationIconContainerViewBinder$bindIcons$3$layoutParams$1(4, null));
                notificationIconContainerViewBinder$bindIcons$3.label = 1;
                stateIn = FlowKt.stateIn(combine, coroutineScope, notificationIconContainerViewBinder$bindIcons$3);
                if (stateIn == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return unit;
                }
                ResultKt.throwOnFailure(obj);
                stateIn = obj;
            }
            StateFlow stateFlow = (StateFlow) stateIn;
            Flow flow = notificationIconContainerViewBinder$bindIcons$3.$this_bindIcons;
            String str = notificationIconContainerViewBinder$bindIcons$3.$logTag;
            NotificationIconContainer notificationIconContainer = notificationIconContainerViewBinder$bindIcons$3.$view;
            Function1 function1 = notificationIconContainerViewBinder$bindIcons$3.$notifyBindingFailures;
            NotificationIconContainerViewBinder.IconViewStore iconViewStore = notificationIconContainerViewBinder$bindIcons$3.$viewStore;
            Function3 function3 = notificationIconContainerViewBinder$bindIcons$3.$bindIcon;
            notificationIconContainerViewBinder$bindIcons$3.label = 2;
            Object coroutineScope2 = CoroutineScopeKt.coroutineScope(notificationIconContainerViewBinder$bindIcons$3, new NotificationIconContainerViewBinder$bindIcons$5(flow, str, notificationIconContainer, iconViewStore, function1, stateFlow, function3, null));
            if (coroutineScope2 != coroutineSingletons) {
                coroutineScope2 = unit;
            }
            if (coroutineScope2 == coroutineSingletons) {
                return coroutineSingletons;
            }
            return unit;
        } finally {
            NotificationIconContainer notificationIconContainer2 = notificationIconContainerViewBinder$bindIcons$3.$view;
            boolean z = notificationIconContainer2.mAnimationsEnabled;
            boolean z2 = notificationIconContainer2.mChangingViewPositions;
            notificationIconContainer2.mAnimationsEnabled = false;
            notificationIconContainer2.mChangingViewPositions = true;
            notificationIconContainer2.removeAllViews();
            notificationIconContainer2.mChangingViewPositions = z2;
            notificationIconContainer2.mAnimationsEnabled = z;
        }
    }
}
