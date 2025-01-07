package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.view.View;
import com.android.systemui.common.ui.view.ViewExtKt$onLayoutChanged$2;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.util.kotlin.DisposableHandles;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerBinder {
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final NotificationStackScrollLayoutController controller;
    public final CoroutineDispatcher mainImmediateDispatcher;
    public final NotificationStackSizeCalculator notificationStackSizeCalculator;

    public SharedNotificationContainerBinder(NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationStackSizeCalculator notificationStackSizeCalculator, NotificationScrollViewBinder notificationScrollViewBinder, CommunalSettingsInteractor communalSettingsInteractor, CoroutineDispatcher coroutineDispatcher) {
        this.controller = notificationStackScrollLayoutController;
        this.notificationStackSizeCalculator = notificationStackSizeCalculator;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.mainImmediateDispatcher = coroutineDispatcher;
    }

    public final DisposableHandles bind(SharedNotificationContainer sharedNotificationContainer, final SharedNotificationContainerViewModel sharedNotificationContainerViewModel) {
        DisposableHandles disposableHandles = new DisposableHandles();
        SharedNotificationContainerBinder$bind$1 sharedNotificationContainerBinder$bind$1 = new SharedNotificationContainerBinder$bind$1(sharedNotificationContainerViewModel, sharedNotificationContainer, this, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(sharedNotificationContainer, EmptyCoroutineContext.INSTANCE, sharedNotificationContainerBinder$bind$1));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(sharedNotificationContainer, this.mainImmediateDispatcher, new SharedNotificationContainerBinder$bind$2(new ViewStateAccessor(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$viewState$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(SharedNotificationContainerBinder.this.controller.mView.getAlpha());
            }
        }), this, sharedNotificationContainerViewModel, null)));
        this.controller.mView.mOnHeightChangedRunnable = new SharedNotificationContainerBinder$bind$3(sharedNotificationContainerViewModel);
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$4
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                SharedNotificationContainerBinder.this.controller.mView.mOnHeightChangedRunnable = null;
            }
        });
        final Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SharedNotificationContainerViewModel.this.notificationStackChanged();
                return Unit.INSTANCE;
            }
        };
        View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener(function1) { // from class: com.android.systemui.common.ui.view.ViewExtKt$onLayoutChanged$1
            public final /* synthetic */ Lambda $onLayoutChanged;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$onLayoutChanged = (Lambda) function1;
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ?? r0 = this.$onLayoutChanged;
                Intrinsics.checkNotNull(view);
                r0.invoke(view);
            }
        };
        sharedNotificationContainer.addOnLayoutChangeListener(onLayoutChangeListener);
        disposableHandles.plusAssign(new ViewExtKt$onLayoutChanged$2(sharedNotificationContainer, onLayoutChangeListener));
        return disposableHandles;
    }
}
