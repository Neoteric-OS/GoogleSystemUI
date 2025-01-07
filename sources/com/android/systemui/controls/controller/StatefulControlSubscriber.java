package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.IBinder;
import android.service.controls.Control;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.util.Log;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.controls.CustomIconCache;
import com.android.systemui.controls.ui.ControlKey;
import com.android.systemui.controls.ui.ControlViewHolder;
import com.android.systemui.controls.ui.ControlWithState;
import com.android.systemui.controls.ui.ControlsUiController;
import com.android.systemui.controls.ui.ControlsUiControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatefulControlSubscriber extends IControlsSubscriber.Stub {
    public final DelayableExecutor bgExecutor;
    public final ControlsController controller;
    public final ControlsProviderLifecycleManager provider;
    public final long requestLimit = 100000;
    public IControlsSubscription subscription;
    public boolean subscriptionOpen;

    public StatefulControlSubscriber(ControlsController controlsController, ControlsProviderLifecycleManager controlsProviderLifecycleManager, DelayableExecutor delayableExecutor) {
        this.controller = controlsController;
        this.provider = controlsProviderLifecycleManager;
        this.bgExecutor = delayableExecutor;
    }

    public final void onComplete(IBinder iBinder) {
        run(iBinder, new Function0() { // from class: com.android.systemui.controls.controller.StatefulControlSubscriber$onComplete$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StatefulControlSubscriber statefulControlSubscriber = StatefulControlSubscriber.this;
                if (statefulControlSubscriber.subscriptionOpen) {
                    statefulControlSubscriber.subscriptionOpen = false;
                    Log.i("StatefulControlSubscriber", "onComplete receive from '" + statefulControlSubscriber.provider.componentName + "'");
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final void onError(IBinder iBinder, final String str) {
        run(iBinder, new Function0() { // from class: com.android.systemui.controls.controller.StatefulControlSubscriber$onError$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StatefulControlSubscriber statefulControlSubscriber = StatefulControlSubscriber.this;
                if (statefulControlSubscriber.subscriptionOpen) {
                    statefulControlSubscriber.subscriptionOpen = false;
                    Log.e("StatefulControlSubscriber", "onError receive from '" + statefulControlSubscriber.provider.componentName + "': " + str);
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final void onNext(final IBinder iBinder, final Control control) {
        run(iBinder, new Function0() { // from class: com.android.systemui.controls.controller.StatefulControlSubscriber$onNext$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StatefulControlSubscriber statefulControlSubscriber = StatefulControlSubscriber.this;
                if (statefulControlSubscriber.subscriptionOpen) {
                    ControlsController controlsController = statefulControlSubscriber.controller;
                    ComponentName componentName = statefulControlSubscriber.provider.componentName;
                    Control control2 = control;
                    ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) controlsController;
                    if (controlsControllerImpl.confirmAvailability()) {
                        if (control2.getStatus() == 1) {
                            ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$refreshStatus$1(componentName, control2, controlsControllerImpl, 0));
                        }
                        ControlsUiController controlsUiController = controlsControllerImpl.uiController;
                        List<Control> singletonList = Collections.singletonList(control2);
                        ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) controlsUiController;
                        final boolean z = !controlsUiControllerImpl.keyguardStateController.isUnlocked();
                        for (Control control3 : singletonList) {
                            ControlWithState controlWithState = (ControlWithState) controlsUiControllerImpl.controlsById.get(new ControlKey(componentName, control3.getControlId()));
                            if (controlWithState != null) {
                                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("onRefreshState() for id: ", control3.getControlId(), "ControlsUiController");
                                CustomIconCache customIconCache = controlsUiControllerImpl.iconCache;
                                String controlId = control3.getControlId();
                                Icon customIcon = control3.getCustomIcon();
                                if (!componentName.equals(customIconCache.currentComponent)) {
                                    synchronized (customIconCache.cache) {
                                        customIconCache.cache.clear();
                                    }
                                    customIconCache.currentComponent = componentName;
                                }
                                synchronized (customIconCache.cache) {
                                    if (customIcon != null) {
                                        try {
                                        } catch (Throwable th) {
                                            throw th;
                                        }
                                    }
                                }
                                final ControlWithState controlWithState2 = new ControlWithState(componentName, controlWithState.ci, control3);
                                ControlKey controlKey = new ControlKey(componentName, control3.getControlId());
                                controlsUiControllerImpl.controlsById.put(controlKey, controlWithState2);
                                final ControlViewHolder controlViewHolder = (ControlViewHolder) controlsUiControllerImpl.controlViewsById.get(controlKey);
                                if (controlViewHolder != null) {
                                    ((ExecutorImpl) controlsUiControllerImpl.uiExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$onRefreshState$1$1$1$1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            ControlViewHolder.this.bindData(controlWithState2, z);
                                        }
                                    });
                                }
                            }
                        }
                    } else {
                        Log.d("ControlsControllerImpl", "Controls not available");
                    }
                } else {
                    Log.w("StatefulControlSubscriber", "Refresh outside of window for token:" + iBinder);
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final void onSubscribe(IBinder iBinder, final IControlsSubscription iControlsSubscription) {
        run(iBinder, new Function0() { // from class: com.android.systemui.controls.controller.StatefulControlSubscriber$onSubscribe$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                StatefulControlSubscriber statefulControlSubscriber = StatefulControlSubscriber.this;
                statefulControlSubscriber.subscriptionOpen = true;
                IControlsSubscription iControlsSubscription2 = iControlsSubscription;
                statefulControlSubscriber.subscription = iControlsSubscription2;
                statefulControlSubscriber.provider.startSubscription(iControlsSubscription2, statefulControlSubscriber.requestLimit);
                return Unit.INSTANCE;
            }
        });
    }

    public final void run(IBinder iBinder, Function0 function0) {
        if (Intrinsics.areEqual(this.provider.token, iBinder)) {
            ((ExecutorImpl) this.bgExecutor).execute(new StatefulControlSubscriber$run$1(function0));
        }
    }
}
