package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.UserHandle;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscription;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.ControlActionWrapper;
import android.util.ArraySet;
import android.util.Log;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.List;
import java.util.Set;
import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderLifecycleManager {
    public final String TAG;
    public final IControlsActionCallback.Stub actionCallbackService;
    public final ComponentName componentName;
    public final Context context;
    public final DelayableExecutor executor;
    public final Intent intent;
    public boolean lastForPanel;
    public ExecutorImpl.ExecutionToken onLoadCanceller;
    public final PackageUpdateMonitor packageUpdateMonitor;
    public final Set queuedServiceMethods;
    public boolean requiresBound;
    public final ControlsProviderLifecycleManager$serviceConnection$1 serviceConnection;
    public final IBinder token;
    public final UserHandle user;
    public ServiceWrapper wrapper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Action extends ServiceMethod {
        public final /* synthetic */ int $r8$classId;
        public final Object action;
        public final Object id;
        public final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Action(ControlsProviderLifecycleManager controlsProviderLifecycleManager, Object obj, Object obj2, int i) {
            super();
            this.$r8$classId = i;
            this.this$0 = controlsProviderLifecycleManager;
            this.id = obj;
            this.action = obj2;
        }

        @Override // com.android.systemui.controls.controller.ControlsProviderLifecycleManager.ServiceMethod
        public final boolean callWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
            switch (this.$r8$classId) {
                case 0:
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
                    String str = controlsProviderLifecycleManager.TAG;
                    ComponentName componentName = controlsProviderLifecycleManager.componentName;
                    StringBuilder sb = new StringBuilder("onAction ");
                    sb.append(componentName);
                    sb.append(" - ");
                    String str2 = (String) this.id;
                    sb.append(str2);
                    Log.d(str, sb.toString());
                    ServiceWrapper serviceWrapper = controlsProviderLifecycleManager.wrapper;
                    if (serviceWrapper != null) {
                        try {
                            serviceWrapper.service.action(str2, new ControlActionWrapper((ControlAction) this.action), controlsProviderLifecycleManager.actionCallbackService);
                            break;
                        } catch (Exception e) {
                            Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e);
                            return false;
                        }
                    }
                    break;
                default:
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = this.this$0;
                    Log.d(controlsProviderLifecycleManager2.TAG, "subscribe " + controlsProviderLifecycleManager2.componentName + " - " + ((List) this.id));
                    ServiceWrapper serviceWrapper2 = controlsProviderLifecycleManager2.wrapper;
                    if (serviceWrapper2 != null) {
                        try {
                            serviceWrapper2.service.subscribe((List) this.id, (StatefulControlSubscriber) this.action);
                            break;
                        } catch (Exception e2) {
                            Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e2);
                            return false;
                        }
                    }
                    break;
            }
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Load extends ServiceMethod {
        public final /* synthetic */ int $r8$classId;
        public final ControlsBindingControllerImpl.LoadSubscriber subscriber;
        public final /* synthetic */ ControlsProviderLifecycleManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Load(ControlsProviderLifecycleManager controlsProviderLifecycleManager, ControlsBindingControllerImpl.LoadSubscriber loadSubscriber, int i) {
            super();
            this.$r8$classId = i;
            this.this$0 = controlsProviderLifecycleManager;
            this.subscriber = loadSubscriber;
        }

        @Override // com.android.systemui.controls.controller.ControlsProviderLifecycleManager.ServiceMethod
        public final boolean callWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
            switch (this.$r8$classId) {
                case 0:
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
                    Log.d(controlsProviderLifecycleManager.TAG, "load " + controlsProviderLifecycleManager.componentName);
                    ServiceWrapper serviceWrapper = controlsProviderLifecycleManager.wrapper;
                    if (serviceWrapper != null) {
                        try {
                            serviceWrapper.service.load(this.subscriber);
                            break;
                        } catch (Exception e) {
                            Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e);
                            return false;
                        }
                    }
                    break;
                default:
                    ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = this.this$0;
                    Log.d(controlsProviderLifecycleManager2.TAG, "suggest " + controlsProviderLifecycleManager2.componentName);
                    ServiceWrapper serviceWrapper2 = controlsProviderLifecycleManager2.wrapper;
                    if (serviceWrapper2 != null) {
                        try {
                            serviceWrapper2.service.loadSuggested(this.subscriber);
                            break;
                        } catch (Exception e2) {
                            Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e2);
                            return false;
                        }
                    }
                    break;
            }
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ServiceMethod {
        public ServiceMethod() {
        }

        public abstract boolean callWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
    }

    public ControlsProviderLifecycleManager(Context context, DelayableExecutor delayableExecutor, IControlsActionCallback.Stub stub, UserHandle userHandle, ComponentName componentName, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31) {
        this.context = context;
        this.executor = delayableExecutor;
        this.actionCallbackService = stub;
        this.user = userHandle;
        this.componentName = componentName;
        Binder binder = new Binder();
        this.token = binder;
        this.queuedServiceMethods = new ArraySet();
        this.TAG = "ControlsProviderLifecycleManager";
        Intent intent = new Intent("android.service.controls.ControlsProviderService");
        intent.setComponent(componentName);
        Bundle bundle = new Bundle();
        bundle.putBinder("CALLBACK_TOKEN", binder);
        intent.putExtra("CALLBACK_BUNDLE", bundle);
        this.intent = intent;
        String packageName = componentName.getPackageName();
        ControlsProviderLifecycleManager$ServiceMethod$run$1 controlsProviderLifecycleManager$ServiceMethod$run$1 = new ControlsProviderLifecycleManager$ServiceMethod$run$1(this, 2);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$31.this$0;
        this.packageUpdateMonitor = new PackageUpdateMonitor(userHandle, packageName, controlsProviderLifecycleManager$ServiceMethod$run$1, (Handler) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideBgHandlerProvider.get(), (Context) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get());
        this.serviceConnection = new ControlsProviderLifecycleManager$serviceConnection$1(this);
    }

    public final void cancelSubscription(IControlsSubscription iControlsSubscription) {
        Log.d(this.TAG, "cancelSubscription: " + iControlsSubscription);
        if (this.wrapper != null) {
            try {
                iControlsSubscription.cancel();
            } catch (Exception e) {
                Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e);
            }
        }
    }

    public final void invokeOrQueue(ServiceMethod serviceMethod) {
        Unit unit;
        if (this.wrapper != null) {
            if (!serviceMethod.callWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core()) {
                ControlsProviderLifecycleManager controlsProviderLifecycleManager = ControlsProviderLifecycleManager.this;
                synchronized (controlsProviderLifecycleManager.queuedServiceMethods) {
                    ((ArraySet) controlsProviderLifecycleManager.queuedServiceMethods).add(serviceMethod);
                }
                ((ExecutorImpl) controlsProviderLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$ServiceMethod$run$1(controlsProviderLifecycleManager, 0));
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            synchronized (this.queuedServiceMethods) {
                ((ArraySet) this.queuedServiceMethods).add(serviceMethod);
            }
            ((ExecutorImpl) this.executor).execute(new ControlsProviderLifecycleManager$bindService$1(this, true, false));
        }
    }

    public final void startSubscription(IControlsSubscription iControlsSubscription, long j) {
        Log.d(this.TAG, "startSubscription: " + iControlsSubscription);
        if (this.wrapper != null) {
            try {
                iControlsSubscription.request(j);
            } catch (Exception e) {
                Log.e("ServiceWrapper", "Caught exception from ControlsProviderService", e);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ControlsProviderLifecycleManager(");
        sb.append("component=" + this.componentName);
        sb.append(", user=" + this.user);
        sb.append(")");
        return sb.toString();
    }

    public final void unbindAndCleanup(String str) {
        String str2 = "Unbinding service " + this.intent + ". Reason: " + str;
        String str3 = this.TAG;
        Log.d(str3, str2);
        this.wrapper = null;
        try {
            ControlsProviderLifecycleManager$serviceConnection$1 controlsProviderLifecycleManager$serviceConnection$1 = this.serviceConnection;
            if (controlsProviderLifecycleManager$serviceConnection$1.connected.compareAndSet(true, false)) {
                this.context.unbindService(controlsProviderLifecycleManager$serviceConnection$1);
            }
        } catch (IllegalArgumentException e) {
            Log.e(str3, "Failed to unbind service", e);
        }
    }
}
