package com.android.systemui.qs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.provider.DeviceConfig;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.FgsManagerControllerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.DeviceConfigProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsController {
    public final FgsManagerController fgsManagerController;

    public FooterActionsController(FgsManagerController fgsManagerController) {
        this.fgsManagerController = fgsManagerController;
    }

    public final void init() {
        final FgsManagerControllerImpl fgsManagerControllerImpl = (FgsManagerControllerImpl) this.fgsManagerController;
        synchronized (fgsManagerControllerImpl.lock) {
            if (fgsManagerControllerImpl.initialized) {
                return;
            }
            fgsManagerControllerImpl.deviceConfigProxy.getClass();
            fgsManagerControllerImpl.showUserVisibleJobs = DeviceConfig.getBoolean("systemui", "task_manager_show_user_visible_jobs", true);
            fgsManagerControllerImpl.deviceConfigProxy.getClass();
            fgsManagerControllerImpl.informJobSchedulerOfPendingAppStop = DeviceConfig.getBoolean("systemui", "task_manager_inform_job_scheduler_of_pending_app_stop", true);
            try {
                fgsManagerControllerImpl.activityManager.registerForegroundServiceObserver(fgsManagerControllerImpl.foregroundServiceObserver);
                if (fgsManagerControllerImpl.showUserVisibleJobs) {
                    fgsManagerControllerImpl.jobScheduler.registerUserVisibleJobObserver(fgsManagerControllerImpl.userVisibleJobObserver);
                }
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
            ((UserTrackerImpl) fgsManagerControllerImpl.userTracker).addCallback(fgsManagerControllerImpl.userTrackerCallback, fgsManagerControllerImpl.backgroundExecutor);
            Set set = fgsManagerControllerImpl.currentProfileIds;
            List userProfiles = ((UserTrackerImpl) fgsManagerControllerImpl.userTracker).getUserProfiles();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(userProfiles, 10));
            Iterator it = userProfiles.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((UserInfo) it.next()).id));
            }
            set.addAll(arrayList);
            DeviceConfigProxy deviceConfigProxy = fgsManagerControllerImpl.deviceConfigProxy;
            Executor executor = fgsManagerControllerImpl.backgroundExecutor;
            DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$init$1$2
                public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                    StateFlowImpl stateFlowImpl = FgsManagerControllerImpl.this._showFooterDot;
                    Boolean valueOf = Boolean.valueOf(properties.getBoolean("task_manager_show_footer_dot", ((Boolean) stateFlowImpl.getValue()).booleanValue()));
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, valueOf);
                    FgsManagerControllerImpl fgsManagerControllerImpl2 = FgsManagerControllerImpl.this;
                    fgsManagerControllerImpl2.showStopBtnForUserAllowlistedApps = properties.getBoolean("show_stop_button_for_user_allowlisted_apps", fgsManagerControllerImpl2.showStopBtnForUserAllowlistedApps);
                    FgsManagerControllerImpl fgsManagerControllerImpl3 = FgsManagerControllerImpl.this;
                    boolean z = fgsManagerControllerImpl3.showUserVisibleJobs;
                    fgsManagerControllerImpl3.showUserVisibleJobs = properties.getBoolean("task_manager_show_user_visible_jobs", z);
                    FgsManagerControllerImpl fgsManagerControllerImpl4 = FgsManagerControllerImpl.this;
                    boolean z2 = fgsManagerControllerImpl4.showUserVisibleJobs;
                    if (z2 != z) {
                        if (z2) {
                            fgsManagerControllerImpl4.jobScheduler.registerUserVisibleJobObserver(fgsManagerControllerImpl4.userVisibleJobObserver);
                        } else {
                            fgsManagerControllerImpl4.jobScheduler.unregisterUserVisibleJobObserver(fgsManagerControllerImpl4.userVisibleJobObserver);
                            synchronized (fgsManagerControllerImpl4.lock) {
                                try {
                                    for (Map.Entry entry : fgsManagerControllerImpl4.runningTaskIdentifiers.entrySet()) {
                                        FgsManagerControllerImpl.UserPackage userPackage = (FgsManagerControllerImpl.UserPackage) entry.getKey();
                                        FgsManagerControllerImpl.StartTimeAndIdentifiers startTimeAndIdentifiers = (FgsManagerControllerImpl.StartTimeAndIdentifiers) entry.getValue();
                                        if (startTimeAndIdentifiers.fgsTokens.isEmpty()) {
                                            fgsManagerControllerImpl4.runningTaskIdentifiers.remove(userPackage);
                                        } else {
                                            startTimeAndIdentifiers.jobSummaries.clear();
                                        }
                                    }
                                    fgsManagerControllerImpl4.updateNumberOfVisibleRunningPackagesLocked();
                                    fgsManagerControllerImpl4.updateAppItemsLocked(false);
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    }
                    FgsManagerControllerImpl fgsManagerControllerImpl5 = FgsManagerControllerImpl.this;
                    fgsManagerControllerImpl5.informJobSchedulerOfPendingAppStop = properties.getBoolean("show_stop_button_for_user_allowlisted_apps", fgsManagerControllerImpl5.informJobSchedulerOfPendingAppStop);
                }
            };
            deviceConfigProxy.getClass();
            DeviceConfig.addOnPropertiesChangedListener("systemui", executor, onPropertiesChangedListener);
            StateFlowImpl stateFlowImpl = fgsManagerControllerImpl._showFooterDot;
            fgsManagerControllerImpl.deviceConfigProxy.getClass();
            Boolean valueOf = Boolean.valueOf(DeviceConfig.getBoolean("systemui", "task_manager_show_footer_dot", false));
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
            fgsManagerControllerImpl.deviceConfigProxy.getClass();
            fgsManagerControllerImpl.showStopBtnForUserAllowlistedApps = DeviceConfig.getBoolean("systemui", "show_stop_button_for_user_allowlisted_apps", true);
            fgsManagerControllerImpl.dumpManager.registerDumpable(fgsManagerControllerImpl);
            BroadcastDispatcher.registerReceiver$default(fgsManagerControllerImpl.broadcastDispatcher, new BroadcastReceiver() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$init$1$3
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.SHOW_FOREGROUND_SERVICE_MANAGER")) {
                        FgsManagerControllerImpl.this.showDialog$1(null);
                    }
                }
            }, new IntentFilter("android.intent.action.SHOW_FOREGROUND_SERVICE_MANAGER"), fgsManagerControllerImpl.mainExecutor, null, 4, 40);
            fgsManagerControllerImpl.initialized = true;
        }
    }
}
