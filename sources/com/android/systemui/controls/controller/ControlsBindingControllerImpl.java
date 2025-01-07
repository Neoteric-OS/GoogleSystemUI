package com.android.systemui.controls.controller;

import android.R;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.UserHandle;
import android.service.controls.Control;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.util.Log;
import android.view.Window;
import com.android.systemui.controls.controller.ControlsBindingController;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl.OnActionResponseRunnable;
import com.android.systemui.controls.ui.ControlKey;
import com.android.systemui.controls.ui.ControlsUiControllerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsBindingControllerImpl implements ControlsBindingController {
    public static final ControlsBindingControllerImpl$Companion$emptyCallback$1 emptyCallback = new ControlsBindingControllerImpl$Companion$emptyCallback$1();
    public final ControlsBindingControllerImpl$actionCallbackService$1 actionCallbackService = new IControlsActionCallback.Stub() { // from class: com.android.systemui.controls.controller.ControlsBindingControllerImpl$actionCallbackService$1
        public final void accept(IBinder iBinder, String str, int i) {
            ControlsBindingControllerImpl controlsBindingControllerImpl = ControlsBindingControllerImpl.this;
            ((ExecutorImpl) controlsBindingControllerImpl.backgroundExecutor).execute(controlsBindingControllerImpl.new OnActionResponseRunnable(iBinder, str, i));
        }
    };
    public final DelayableExecutor backgroundExecutor;
    public final Context context;
    public ControlsProviderLifecycleManager currentProvider;
    public UserHandle currentUser;
    public final Lazy lazyController;
    public LoadSubscriber loadSubscriber;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31 packageUpdateMonitorFactory;
    public StatefulControlSubscriber statefulControlSubscriber;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class CallbackRunnable implements Runnable {
        public final ControlsProviderLifecycleManager provider;
        public final IBinder token;

        public CallbackRunnable(IBinder iBinder) {
            this.token = iBinder;
            this.provider = ControlsBindingControllerImpl.this.currentProvider;
        }

        public abstract void doRun();

        @Override // java.lang.Runnable
        public final void run() {
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
            if (controlsProviderLifecycleManager == null) {
                Log.e("ControlsBindingControllerImpl", "No current provider set");
                return;
            }
            if (!Intrinsics.areEqual(controlsProviderLifecycleManager.user, ControlsBindingControllerImpl.this.currentUser)) {
                Log.e("ControlsBindingControllerImpl", "User " + this.provider.user + " is not current user");
                return;
            }
            if (this.token.equals(this.provider.token)) {
                doRun();
                return;
            }
            Log.e("ControlsBindingControllerImpl", "Provider for token:" + this.token + " does not exist anymore");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LoadSubscriber extends IControlsSubscriber.Stub {
        public Lambda _loadCancelInternal;
        public ControlsBindingController.LoadCallback callback;
        public final long requestLimit;
        public IControlsSubscription subscription;
        public final ArrayList loadedControls = new ArrayList();
        public final AtomicBoolean isTerminated = new AtomicBoolean(false);

        public LoadSubscriber(ControlsBindingController.LoadCallback loadCallback, long j) {
            this.callback = loadCallback;
            this.requestLimit = j;
        }

        public final void maybeTerminateAndRun(CallbackRunnable callbackRunnable) {
            if (this.isTerminated.get()) {
                return;
            }
            this._loadCancelInternal = new Function0() { // from class: com.android.systemui.controls.controller.ControlsBindingControllerImpl$LoadSubscriber$maybeTerminateAndRun$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            this.callback = ControlsBindingControllerImpl.emptyCallback;
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = ControlsBindingControllerImpl.this.currentProvider;
            if (controlsProviderLifecycleManager != null) {
                ExecutorImpl.ExecutionToken executionToken = controlsProviderLifecycleManager.onLoadCanceller;
                if (executionToken != null) {
                    executionToken.run();
                }
                controlsProviderLifecycleManager.onLoadCanceller = null;
            }
            ((ExecutorImpl) ControlsBindingControllerImpl.this.backgroundExecutor).execute(new ControlsBindingControllerImpl$onComponentRemoved$1(1, this, callbackRunnable));
        }

        public final void onComplete(IBinder iBinder) {
            maybeTerminateAndRun(new OnLoadRunnable(ControlsBindingControllerImpl.this, iBinder, this.loadedControls, this.callback, 0));
        }

        public final void onError(IBinder iBinder, String str) {
            maybeTerminateAndRun(new OnLoadRunnable(ControlsBindingControllerImpl.this, iBinder, str, this.callback, 1));
        }

        public final void onNext(final IBinder iBinder, final Control control) {
            final ControlsBindingControllerImpl controlsBindingControllerImpl = ControlsBindingControllerImpl.this;
            ((ExecutorImpl) controlsBindingControllerImpl.backgroundExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.controller.ControlsBindingControllerImpl$LoadSubscriber$onNext$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (ControlsBindingControllerImpl.LoadSubscriber.this.isTerminated.get()) {
                        return;
                    }
                    ControlsBindingControllerImpl.LoadSubscriber.this.loadedControls.add(control);
                    long size = ControlsBindingControllerImpl.LoadSubscriber.this.loadedControls.size();
                    ControlsBindingControllerImpl.LoadSubscriber loadSubscriber = ControlsBindingControllerImpl.LoadSubscriber.this;
                    if (size >= loadSubscriber.requestLimit) {
                        ControlsBindingControllerImpl controlsBindingControllerImpl2 = controlsBindingControllerImpl;
                        IBinder iBinder2 = iBinder;
                        ArrayList arrayList = loadSubscriber.loadedControls;
                        IControlsSubscription iControlsSubscription = loadSubscriber.subscription;
                        if (iControlsSubscription == null) {
                            iControlsSubscription = null;
                        }
                        loadSubscriber.maybeTerminateAndRun(new ControlsBindingControllerImpl.OnCancelAndLoadRunnable(controlsBindingControllerImpl2, iBinder2, arrayList, iControlsSubscription, loadSubscriber.callback));
                    }
                }
            });
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.controls.controller.ControlsBindingControllerImpl$LoadSubscriber$onSubscribe$1, kotlin.jvm.internal.Lambda] */
        public final void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) {
            this.subscription = iControlsSubscription;
            final ControlsBindingControllerImpl controlsBindingControllerImpl = ControlsBindingControllerImpl.this;
            this._loadCancelInternal = new Function0() { // from class: com.android.systemui.controls.controller.ControlsBindingControllerImpl$LoadSubscriber$onSubscribe$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager = ControlsBindingControllerImpl.this.currentProvider;
                    if (controlsProviderLifecycleManager != null) {
                        IControlsSubscription iControlsSubscription2 = this.subscription;
                        if (iControlsSubscription2 == null) {
                            iControlsSubscription2 = null;
                        }
                        controlsProviderLifecycleManager.cancelSubscription(iControlsSubscription2);
                    }
                    return Unit.INSTANCE;
                }
            };
            ((ExecutorImpl) controlsBindingControllerImpl.backgroundExecutor).execute(new OnSubscribeRunnable(controlsBindingControllerImpl, iBinder, iControlsSubscription, this.requestLimit));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnActionResponseRunnable extends CallbackRunnable {
        public final String controlId;
        public final int response;

        public OnActionResponseRunnable(IBinder iBinder, String str, int i) {
            super(iBinder);
            this.controlId = str;
            this.response = i;
        }

        @Override // com.android.systemui.controls.controller.ControlsBindingControllerImpl.CallbackRunnable
        public final void doRun() {
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
            if (controlsProviderLifecycleManager != null) {
                ControlsController controlsController = (ControlsController) ControlsBindingControllerImpl.this.lazyController.get();
                ComponentName componentName = controlsProviderLifecycleManager.componentName;
                String str = this.controlId;
                final int i = this.response;
                ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) controlsController;
                if (controlsControllerImpl.confirmAvailability()) {
                    final ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) controlsControllerImpl.uiController;
                    controlsUiControllerImpl.getClass();
                    final ControlKey controlKey = new ControlKey(componentName, str);
                    ((ExecutorImpl) controlsUiControllerImpl.uiExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$onActionResponse$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ControlViewHolder controlViewHolder = (ControlViewHolder) ControlsUiControllerImpl.this.controlViewsById.get(controlKey);
                            if (controlViewHolder != null) {
                                int i2 = i;
                                ControlWithState controlWithState = controlViewHolder.cws;
                                AlertDialog alertDialog = null;
                                if (controlWithState == null) {
                                    controlWithState = null;
                                }
                                controlViewHolder.controlActionCoordinator.actionsInProgress.remove(controlWithState.ci.controlId);
                                boolean z = controlViewHolder.lastChallengeDialog != null;
                                if (i2 == 0) {
                                    controlViewHolder.lastChallengeDialog = null;
                                    controlViewHolder.setErrorStatus();
                                    return;
                                }
                                if (i2 == 1) {
                                    controlViewHolder.lastChallengeDialog = null;
                                    return;
                                }
                                if (i2 == 2) {
                                    controlViewHolder.lastChallengeDialog = null;
                                    controlViewHolder.setErrorStatus();
                                    return;
                                }
                                Function0 function0 = controlViewHolder.onDialogCancel;
                                if (i2 != 3) {
                                    if (i2 == 4) {
                                        ChallengeDialogs$createPinDialog$1 createPinDialog = ChallengeDialogs.createPinDialog(controlViewHolder, false, z, function0);
                                        controlViewHolder.lastChallengeDialog = createPinDialog;
                                        if (createPinDialog != null) {
                                            createPinDialog.show();
                                            return;
                                        }
                                        return;
                                    }
                                    if (i2 != 5) {
                                        return;
                                    }
                                    ChallengeDialogs$createPinDialog$1 createPinDialog2 = ChallengeDialogs.createPinDialog(controlViewHolder, true, z, function0);
                                    controlViewHolder.lastChallengeDialog = createPinDialog2;
                                    if (createPinDialog2 != null) {
                                        createPinDialog2.show();
                                        return;
                                    }
                                    return;
                                }
                                ControlAction controlAction = controlViewHolder.lastAction;
                                if (controlAction == null) {
                                    Log.e("ControlsUiController", "Confirmation Dialog attempted but no last action is set. Will not show");
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(controlViewHolder.context, R.style.Theme.DeviceDefault.Dialog.Alert);
                                    builder.setTitle(controlViewHolder.context.getResources().getString(com.android.wm.shell.R.string.controls_confirmation_message, controlViewHolder.title.getText()));
                                    builder.setPositiveButton(R.string.ok, new ChallengeDialogs$createPinDialog$2$1(controlViewHolder, controlAction, 1));
                                    builder.setNegativeButton(R.string.cancel, new ChallengeDialogs$createPinDialog$2$2(1, function0));
                                    alertDialog = builder.create();
                                    Window window = alertDialog.getWindow();
                                    if (window != null) {
                                        window.setType(2020);
                                    }
                                }
                                controlViewHolder.lastChallengeDialog = alertDialog;
                                if (alertDialog != null) {
                                    alertDialog.show();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnCancelAndLoadRunnable extends CallbackRunnable {
        public final ControlsBindingController.LoadCallback callback;
        public final List list;
        public final IControlsSubscription subscription;

        public OnCancelAndLoadRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder iBinder, List list, IControlsSubscription iControlsSubscription, ControlsBindingController.LoadCallback loadCallback) {
            super(iBinder);
            this.list = list;
            this.subscription = iControlsSubscription;
            this.callback = loadCallback;
        }

        @Override // com.android.systemui.controls.controller.ControlsBindingControllerImpl.CallbackRunnable
        public final void doRun() {
            Log.d("ControlsBindingControllerImpl", "LoadSubscription: Canceling and loading controls");
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
            if (controlsProviderLifecycleManager != null) {
                controlsProviderLifecycleManager.cancelSubscription(this.subscription);
            }
            this.callback.accept(this.list);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnLoadRunnable extends CallbackRunnable {
        public final /* synthetic */ int $r8$classId;
        public final ControlsBindingController.LoadCallback callback;
        public final Object list;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ OnLoadRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder iBinder, Object obj, ControlsBindingController.LoadCallback loadCallback, int i) {
            super(iBinder);
            this.$r8$classId = i;
            this.list = obj;
            this.callback = loadCallback;
        }

        @Override // com.android.systemui.controls.controller.ControlsBindingControllerImpl.CallbackRunnable
        public final void doRun() {
            switch (this.$r8$classId) {
                case 0:
                    Log.d("ControlsBindingControllerImpl", "LoadSubscription: Complete and loading controls");
                    this.callback.accept((List) this.list);
                    break;
                default:
                    this.callback.error((String) this.list);
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
                    if (controlsProviderLifecycleManager != null) {
                        Log.e("ControlsBindingControllerImpl", "onError receive from '" + controlsProviderLifecycleManager.componentName + "': " + ((String) this.list));
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnSubscribeRunnable extends CallbackRunnable {
        public final long requestLimit;
        public final IControlsSubscription subscription;

        public OnSubscribeRunnable(ControlsBindingControllerImpl controlsBindingControllerImpl, IBinder iBinder, IControlsSubscription iControlsSubscription, long j) {
            super(iBinder);
            this.subscription = iControlsSubscription;
            this.requestLimit = j;
        }

        @Override // com.android.systemui.controls.controller.ControlsBindingControllerImpl.CallbackRunnable
        public final void doRun() {
            Log.d("ControlsBindingControllerImpl", "LoadSubscription: Starting subscription");
            ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.provider;
            if (controlsProviderLifecycleManager != null) {
                controlsProviderLifecycleManager.startSubscription(this.subscription, this.requestLimit);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.controls.controller.ControlsBindingControllerImpl$actionCallbackService$1] */
    public ControlsBindingControllerImpl(Context context, DelayableExecutor delayableExecutor, Lazy lazy, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31, UserTracker userTracker) {
        this.context = context;
        this.backgroundExecutor = delayableExecutor;
        this.lazyController = lazy;
        this.packageUpdateMonitorFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31;
        this.currentUser = ((UserTrackerImpl) userTracker).getUserHandle();
    }

    public ControlsProviderLifecycleManager createProviderManager$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ComponentName componentName) {
        return new ControlsProviderLifecycleManager(this.context, this.backgroundExecutor, this.actionCallbackService, this.currentUser, componentName, this.packageUpdateMonitorFactory);
    }

    public final ControlsProviderLifecycleManager retrieveLifecycleManager(ComponentName componentName) {
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.currentProvider;
        if (controlsProviderLifecycleManager != null) {
            if (!Intrinsics.areEqual(controlsProviderLifecycleManager != null ? controlsProviderLifecycleManager.componentName : null, componentName)) {
                unbind();
            }
        }
        ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = this.currentProvider;
        if (controlsProviderLifecycleManager2 == null) {
            controlsProviderLifecycleManager2 = createProviderManager$frameworks__base__packages__SystemUI__android_common__SystemUI_core(componentName);
        }
        this.currentProvider = controlsProviderLifecycleManager2;
        return controlsProviderLifecycleManager2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("  ControlsBindingController:\n");
        sb.append("    currentUser=" + this.currentUser + "\n");
        sb.append("    StatefulControlSubscriber=" + this.statefulControlSubscriber);
        sb.append("    Providers=" + this.currentProvider + "\n");
        return sb.toString();
    }

    public final void unbind() {
        unsubscribe();
        this.loadSubscriber = null;
        ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.currentProvider;
        if (controlsProviderLifecycleManager != null) {
            ExecutorImpl.ExecutionToken executionToken = controlsProviderLifecycleManager.onLoadCanceller;
            if (executionToken != null) {
                executionToken.run();
            }
            controlsProviderLifecycleManager.onLoadCanceller = null;
            ((ExecutorImpl) controlsProviderLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$bindService$1(controlsProviderLifecycleManager, false, false));
        }
        this.currentProvider = null;
    }

    public final void unsubscribe() {
        StatefulControlSubscriber statefulControlSubscriber = this.statefulControlSubscriber;
        if (statefulControlSubscriber != null && statefulControlSubscriber.subscriptionOpen) {
            ((ExecutorImpl) statefulControlSubscriber.bgExecutor).execute(new StatefulControlSubscriber$run$1(statefulControlSubscriber));
        }
        this.statefulControlSubscriber = null;
    }
}
