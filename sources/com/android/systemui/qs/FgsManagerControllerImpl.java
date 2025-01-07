package com.android.systemui.qs;

import android.app.IActivityManager;
import android.app.IForegroundServiceObserver;
import android.app.job.IUserVisibleJobObserver;
import android.app.job.JobScheduler;
import android.app.job.UserVisibleJobSummary;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.UserHandle;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import android.util.IndentingPrintWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.qs.FgsManagerControllerImpl;
import com.android.systemui.qs.FgsManagerControllerImpl.UserPackage;
import com.android.systemui.qs.footer.data.repository.ForegroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1;
import com.android.systemui.qs.footer.data.repository.ForegroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FgsManagerControllerImpl implements Dumpable, FgsManagerController {
    public final StateFlowImpl _showFooterDot;
    public final IActivityManager activityManager;
    public final AppListAdapter appListAdapter;
    public final Executor backgroundExecutor;
    public final BroadcastDispatcher broadcastDispatcher;
    public final Set currentProfileIds;
    public final DeviceConfigProxy deviceConfigProxy;
    public SystemUIDialog dialog;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final DumpManager dumpManager;
    public final ForegroundServiceObserver foregroundServiceObserver;
    public boolean informJobSchedulerOfPendingAppStop;
    public boolean initialized;
    public final JobScheduler jobScheduler;
    public int lastNumberOfVisiblePackages;
    public final Object lock;
    public final Executor mainExecutor;
    public boolean newChangesSinceDialogWasDismissed;
    public final Set onDialogDismissedListeners;
    public final Set onNumberOfPackagesChangedListeners;
    public final PackageManager packageManager;
    public final ArrayMap runningApps;
    public final Map runningTaskIdentifiers;
    public final ReadonlyStateFlow showFooterDot;
    public boolean showStopBtnForUserAllowlistedApps;
    public boolean showUserVisibleJobs;
    public final SystemClock systemClock;
    public final SystemUIDialog.Factory systemUIDialogFactory;
    public final UserTracker userTracker;
    public final FgsManagerControllerImpl$userTrackerCallback$1 userTrackerCallback;
    public final UserVisibleJobObserver userVisibleJobObserver;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView appLabelView;
        public final TextView durationView;
        public final ImageView iconView;
        public final Button stopButton;

        public AppItemViewHolder(View view) {
            super(view);
            this.appLabelView = (TextView) view.requireViewById(R.id.fgs_manager_app_item_label);
            this.durationView = (TextView) view.requireViewById(R.id.fgs_manager_app_item_duration);
            this.iconView = (ImageView) view.requireViewById(R.id.fgs_manager_app_item_icon);
            this.stopButton = (Button) view.requireViewById(R.id.fgs_manager_app_item_stop_button);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppListAdapter extends RecyclerView.Adapter {
        public final Object lock = new Object();
        public List data = EmptyList.INSTANCE;

        public AppListAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return this.data.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            Object obj;
            final AppItemViewHolder appItemViewHolder = (AppItemViewHolder) viewHolder;
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            synchronized (this.lock) {
                obj = this.data.get(i);
                ref$ObjectRef.element = obj;
            }
            final FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
            appItemViewHolder.iconView.setImageDrawable(((RunningApp) obj).icon);
            appItemViewHolder.appLabelView.setText(((RunningApp) ref$ObjectRef.element).appLabel);
            TextView textView = appItemViewHolder.durationView;
            ((SystemClockImpl) fgsManagerControllerImpl.systemClock).getClass();
            textView.setText(DateUtils.formatDuration(Math.max(android.os.SystemClock.elapsedRealtime() - ((RunningApp) ref$ObjectRef.element).timeStarted, 60000L), 20));
            appItemViewHolder.stopButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$AppListAdapter$onBindViewHolder$2$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FgsManagerControllerImpl.AppItemViewHolder.this.stopButton.setText(R.string.fgs_manager_app_item_stop_button_stopped_label);
                    FgsManagerControllerImpl fgsManagerControllerImpl2 = fgsManagerControllerImpl;
                    FgsManagerControllerImpl.RunningApp runningApp = (FgsManagerControllerImpl.RunningApp) ref$ObjectRef.element;
                    int i2 = runningApp.userId;
                    String str = runningApp.packageName;
                    long j = runningApp.timeStarted;
                    ((SystemClockImpl) fgsManagerControllerImpl2.systemClock).getClass();
                    fgsManagerControllerImpl2.backgroundExecutor.execute(new FgsManagerControllerImpl$logEvent$1(fgsManagerControllerImpl2, str, i2, 2, android.os.SystemClock.elapsedRealtime(), j));
                    fgsManagerControllerImpl2.new UserPackage(i2, str);
                    if (fgsManagerControllerImpl2.showUserVisibleJobs || fgsManagerControllerImpl2.informJobSchedulerOfPendingAppStop) {
                        fgsManagerControllerImpl2.jobScheduler.notePendingUserRequestedAppStop(str, i2, "task manager");
                    }
                    fgsManagerControllerImpl2.activityManager.stopAppForUser(str, i2);
                }
            });
            if (((RunningApp) ref$ObjectRef.element).uiControl == UIControl.HIDE_BUTTON) {
                appItemViewHolder.stopButton.setVisibility(4);
            }
            if (((RunningApp) ref$ObjectRef.element).stopped) {
                appItemViewHolder.stopButton.setEnabled(false);
                appItemViewHolder.stopButton.setText(R.string.fgs_manager_app_item_stop_button_stopped_label);
                appItemViewHolder.durationView.setVisibility(8);
            } else {
                appItemViewHolder.stopButton.setEnabled(true);
                appItemViewHolder.stopButton.setText(R.string.fgs_manager_app_item_stop_button_label);
                appItemViewHolder.durationView.setVisibility(0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
            return new AppItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fgs_manager_app_item, viewGroup, false));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ForegroundServiceObserver extends IForegroundServiceObserver.Stub {
        public ForegroundServiceObserver() {
        }

        public final void onForegroundStateChanged(IBinder iBinder, String str, int i, boolean z) {
            FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
            synchronized (fgsManagerControllerImpl.lock) {
                try {
                    UserPackage userPackage = fgsManagerControllerImpl.new UserPackage(i, str);
                    if (z) {
                        Map map = fgsManagerControllerImpl.runningTaskIdentifiers;
                        Object obj = map.get(userPackage);
                        if (obj == null) {
                            obj = new StartTimeAndIdentifiers(fgsManagerControllerImpl.systemClock);
                            map.put(userPackage, obj);
                        }
                        ((StartTimeAndIdentifiers) obj).fgsTokens.add(iBinder);
                    } else {
                        StartTimeAndIdentifiers startTimeAndIdentifiers = (StartTimeAndIdentifiers) fgsManagerControllerImpl.runningTaskIdentifiers.get(userPackage);
                        if (startTimeAndIdentifiers != null) {
                            startTimeAndIdentifiers.fgsTokens.remove(iBinder);
                            if (startTimeAndIdentifiers.fgsTokens.isEmpty() && startTimeAndIdentifiers.jobSummaries.isEmpty()) {
                                fgsManagerControllerImpl.runningTaskIdentifiers.remove(userPackage);
                            }
                        }
                    }
                    fgsManagerControllerImpl.updateNumberOfVisibleRunningPackagesLocked();
                    fgsManagerControllerImpl.updateAppItemsLocked(false);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RunningApp {
        public CharSequence appLabel = "";
        public Drawable icon;
        public final String packageName;
        public boolean stopped;
        public final long timeStarted;
        public final UIControl uiControl;
        public final int userId;

        public RunningApp(int i, String str, long j, UIControl uIControl) {
            this.userId = i;
            this.packageName = str;
            this.timeStarted = j;
            this.uiControl = uIControl;
        }

        public final void dump(PrintWriter printWriter) {
            printWriter.println("RunningApp: [");
            ((IndentingPrintWriter) printWriter).increaseIndent();
            StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("userId="), this.userId, printWriter, "packageName=");
            m.append(this.packageName);
            printWriter.println(m.toString());
            long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
            long j = this.timeStarted;
            printWriter.println("timeStarted=" + j + " (time since start = " + (elapsedRealtime - j) + "ms)");
            StringBuilder sb = new StringBuilder("uiControl=");
            sb.append(this.uiControl);
            printWriter.println(sb.toString());
            printWriter.println("appLabel=" + ((Object) this.appLabel));
            printWriter.println("icon=" + this.icon);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("stopped=", this.stopped, printWriter);
            ((IndentingPrintWriter) printWriter).decreaseIndent();
            printWriter.println("]");
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RunningApp)) {
                return false;
            }
            RunningApp runningApp = (RunningApp) obj;
            return this.userId == runningApp.userId && Intrinsics.areEqual(this.packageName, runningApp.packageName) && this.timeStarted == runningApp.timeStarted && this.uiControl == runningApp.uiControl;
        }

        public final int hashCode() {
            return this.uiControl.hashCode() + Scale$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, Integer.hashCode(this.userId) * 31, 31), 31, this.timeStarted);
        }

        public final String toString() {
            return "RunningApp(userId=" + this.userId + ", packageName=" + this.packageName + ", timeStarted=" + this.timeStarted + ", uiControl=" + this.uiControl + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StartTimeAndIdentifiers {
        public final SystemClock systemClock;
        public final long startTime = android.os.SystemClock.elapsedRealtime();
        public final Set fgsTokens = new LinkedHashSet();
        public final Set jobSummaries = new LinkedHashSet();

        public StartTimeAndIdentifiers(SystemClock systemClock) {
            this.systemClock = systemClock;
        }

        public final void dump(PrintWriter printWriter) {
            printWriter.println("StartTimeAndIdentifiers: [");
            ((IndentingPrintWriter) printWriter).increaseIndent();
            ((SystemClockImpl) this.systemClock).getClass();
            long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
            long j = this.startTime;
            printWriter.println("startTime=" + j + " (time running = " + (elapsedRealtime - j) + "ms)");
            printWriter.println("fgs tokens: [");
            ((IndentingPrintWriter) printWriter).increaseIndent();
            Iterator it = this.fgsTokens.iterator();
            while (it.hasNext()) {
                printWriter.println(String.valueOf((IBinder) it.next()));
            }
            ((IndentingPrintWriter) printWriter).decreaseIndent();
            printWriter.println("job summaries: [");
            ((IndentingPrintWriter) printWriter).increaseIndent();
            Iterator it2 = this.jobSummaries.iterator();
            while (it2.hasNext()) {
                printWriter.println(String.valueOf((UserVisibleJobSummary) it2.next()));
            }
            ((IndentingPrintWriter) printWriter).decreaseIndent();
            printWriter.println("]");
            ((IndentingPrintWriter) printWriter).decreaseIndent();
            printWriter.println("]");
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof StartTimeAndIdentifiers) && Intrinsics.areEqual(this.systemClock, ((StartTimeAndIdentifiers) obj).systemClock);
        }

        public final int hashCode() {
            return this.systemClock.hashCode();
        }

        public final String toString() {
            return "StartTimeAndIdentifiers(systemClock=" + this.systemClock + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class UIControl {
        public static final /* synthetic */ UIControl[] $VALUES;
        public static final UIControl HIDE_BUTTON;
        public static final UIControl HIDE_ENTRY;
        public static final UIControl NORMAL;

        static {
            UIControl uIControl = new UIControl("NORMAL", 0);
            NORMAL = uIControl;
            UIControl uIControl2 = new UIControl("HIDE_BUTTON", 1);
            HIDE_BUTTON = uIControl2;
            UIControl uIControl3 = new UIControl("HIDE_ENTRY", 2);
            HIDE_ENTRY = uIControl3;
            UIControl[] uIControlArr = {uIControl, uIControl2, uIControl3};
            $VALUES = uIControlArr;
            EnumEntriesKt.enumEntries(uIControlArr);
        }

        public static UIControl valueOf(String str) {
            return (UIControl) Enum.valueOf(UIControl.class, str);
        }

        public static UIControl[] values() {
            return (UIControl[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserPackage {
        public final String packageName;
        public boolean uiControlInitialized;
        public final Lazy uid$delegate;
        public final int userId;
        public int backgroundRestrictionExemptionReason = -1;
        public UIControl uiControl = UIControl.NORMAL;

        public UserPackage(int i, String str) {
            this.userId = i;
            this.packageName = str;
            this.uid$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$UserPackage$uid$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    PackageManager packageManager = FgsManagerControllerImpl.this.packageManager;
                    FgsManagerControllerImpl.UserPackage userPackage = this;
                    return Integer.valueOf(packageManager.getPackageUidAsUser(userPackage.packageName, userPackage.userId));
                }
            });
        }

        public final void dump(PrintWriter printWriter) {
            printWriter.println("UserPackage: [");
            ((IndentingPrintWriter) printWriter).increaseIndent();
            StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("userId="), this.userId, printWriter, "packageName=");
            m.append(this.packageName);
            printWriter.println(m.toString());
            printWriter.println("uiControl=" + getUiControl() + " (reason=" + this.backgroundRestrictionExemptionReason + ")");
            ((IndentingPrintWriter) printWriter).decreaseIndent();
            printWriter.println("]");
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof UserPackage)) {
                return false;
            }
            UserPackage userPackage = (UserPackage) obj;
            return Intrinsics.areEqual(userPackage.packageName, this.packageName) && userPackage.userId == this.userId;
        }

        public final UIControl getUiControl() {
            if (!this.uiControlInitialized) {
                updateUiControl();
            }
            return this.uiControl;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.userId), this.packageName);
        }

        public final void updateUiControl() {
            UIControl uIControl;
            FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
            int backgroundRestrictionExemptionReason = fgsManagerControllerImpl.activityManager.getBackgroundRestrictionExemptionReason(((Number) this.uid$delegate.getValue()).intValue());
            this.backgroundRestrictionExemptionReason = backgroundRestrictionExemptionReason;
            if (backgroundRestrictionExemptionReason != 10 && backgroundRestrictionExemptionReason != 11) {
                if (backgroundRestrictionExemptionReason == 51 || backgroundRestrictionExemptionReason == 63) {
                    uIControl = UIControl.HIDE_ENTRY;
                } else if (backgroundRestrictionExemptionReason == 65) {
                    uIControl = fgsManagerControllerImpl.showStopBtnForUserAllowlistedApps ? UIControl.NORMAL : UIControl.HIDE_BUTTON;
                } else if (backgroundRestrictionExemptionReason != 300 && backgroundRestrictionExemptionReason != 318 && backgroundRestrictionExemptionReason != 320 && backgroundRestrictionExemptionReason != 327 && backgroundRestrictionExemptionReason != 55 && backgroundRestrictionExemptionReason != 56) {
                    switch (backgroundRestrictionExemptionReason) {
                        case 322:
                        case 323:
                        case 324:
                            break;
                        default:
                            uIControl = UIControl.NORMAL;
                            break;
                    }
                }
                this.uiControl = uIControl;
                this.uiControlInitialized = true;
            }
            uIControl = UIControl.HIDE_BUTTON;
            this.uiControl = uIControl;
            this.uiControlInitialized = true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserVisibleJobObserver extends IUserVisibleJobObserver.Stub {
        public UserVisibleJobObserver() {
        }

        public final void onUserVisibleJobStateChanged(UserVisibleJobSummary userVisibleJobSummary, boolean z) {
            FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
            synchronized (fgsManagerControllerImpl.lock) {
                try {
                    UserPackage userPackage = fgsManagerControllerImpl.new UserPackage(UserHandle.getUserId(userVisibleJobSummary.getCallingUid()), userVisibleJobSummary.getCallingPackageName());
                    if (z) {
                        Map map = fgsManagerControllerImpl.runningTaskIdentifiers;
                        Object obj = map.get(userPackage);
                        if (obj == null) {
                            obj = new StartTimeAndIdentifiers(fgsManagerControllerImpl.systemClock);
                            map.put(userPackage, obj);
                        }
                        ((StartTimeAndIdentifiers) obj).jobSummaries.add(userVisibleJobSummary);
                    } else {
                        StartTimeAndIdentifiers startTimeAndIdentifiers = (StartTimeAndIdentifiers) fgsManagerControllerImpl.runningTaskIdentifiers.get(userPackage);
                        if (startTimeAndIdentifiers != null) {
                            startTimeAndIdentifiers.jobSummaries.remove(userVisibleJobSummary);
                            if (startTimeAndIdentifiers.fgsTokens.isEmpty() && startTimeAndIdentifiers.jobSummaries.isEmpty()) {
                                fgsManagerControllerImpl.runningTaskIdentifiers.remove(userPackage);
                            }
                        }
                    }
                    fgsManagerControllerImpl.updateNumberOfVisibleRunningPackagesLocked();
                    fgsManagerControllerImpl.updateAppItemsLocked(false);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.qs.FgsManagerControllerImpl$userTrackerCallback$1] */
    public FgsManagerControllerImpl(Executor executor, Executor executor2, SystemClock systemClock, IActivityManager iActivityManager, JobScheduler jobScheduler, PackageManager packageManager, UserTracker userTracker, DeviceConfigProxy deviceConfigProxy, DialogTransitionAnimator dialogTransitionAnimator, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, SystemUIDialog.Factory factory) {
        this.mainExecutor = executor;
        this.backgroundExecutor = executor2;
        this.systemClock = systemClock;
        this.activityManager = iActivityManager;
        this.jobScheduler = jobScheduler;
        this.packageManager = packageManager;
        this.userTracker = userTracker;
        this.deviceConfigProxy = deviceConfigProxy;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.broadcastDispatcher = broadcastDispatcher;
        this.dumpManager = dumpManager;
        this.systemUIDialogFactory = factory;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._showFooterDot = MutableStateFlow;
        this.showFooterDot = new ReadonlyStateFlow(MutableStateFlow);
        this.showUserVisibleJobs = true;
        this.informJobSchedulerOfPendingAppStop = true;
        this.lock = new Object();
        this.currentProfileIds = new LinkedHashSet();
        this.runningTaskIdentifiers = new LinkedHashMap();
        this.appListAdapter = new AppListAdapter();
        this.runningApps = new ArrayMap();
        this.userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$userTrackerCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onProfilesChanged(List list) {
                FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
                synchronized (fgsManagerControllerImpl.lock) {
                    try {
                        fgsManagerControllerImpl.currentProfileIds.clear();
                        Set set = fgsManagerControllerImpl.currentProfileIds;
                        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Integer.valueOf(((UserInfo) it.next()).id));
                        }
                        set.addAll(arrayList);
                        fgsManagerControllerImpl.lastNumberOfVisiblePackages = 0;
                        fgsManagerControllerImpl.updateNumberOfVisibleRunningPackagesLocked();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context) {
            }
        };
        this.foregroundServiceObserver = new ForegroundServiceObserver();
        this.userVisibleJobObserver = new UserVisibleJobObserver();
        this.onNumberOfPackagesChangedListeners = new LinkedHashSet();
        this.onDialogDismissedListeners = new LinkedHashSet();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        PrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        synchronized (this.lock) {
            try {
                indentingPrintWriter.println("current user profiles = " + this.currentProfileIds);
                indentingPrintWriter.println("newChangesSinceDialogWasShown=" + this.newChangesSinceDialogWasDismissed);
                indentingPrintWriter.println("Running task identifiers: [");
                indentingPrintWriter.increaseIndent();
                for (Map.Entry entry : this.runningTaskIdentifiers.entrySet()) {
                    UserPackage userPackage = (UserPackage) entry.getKey();
                    StartTimeAndIdentifiers startTimeAndIdentifiers = (StartTimeAndIdentifiers) entry.getValue();
                    indentingPrintWriter.println("{");
                    indentingPrintWriter.increaseIndent();
                    userPackage.dump(indentingPrintWriter);
                    startTimeAndIdentifiers.dump(indentingPrintWriter);
                    indentingPrintWriter.decreaseIndent();
                    indentingPrintWriter.println("}");
                }
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("]");
                indentingPrintWriter.println("Loaded package UI info: [");
                indentingPrintWriter.increaseIndent();
                for (Map.Entry entry2 : this.runningApps.entrySet()) {
                    UserPackage userPackage2 = (UserPackage) entry2.getKey();
                    RunningApp runningApp = (RunningApp) entry2.getValue();
                    indentingPrintWriter.println("{");
                    indentingPrintWriter.increaseIndent();
                    userPackage2.dump(indentingPrintWriter);
                    runningApp.dump(indentingPrintWriter);
                    indentingPrintWriter.decreaseIndent();
                    indentingPrintWriter.println("}");
                }
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("]");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int getNumVisibleButtonsLocked() {
        Set<UserPackage> keySet = this.runningTaskIdentifiers.keySet();
        int i = 0;
        if (!(keySet instanceof Collection) || !keySet.isEmpty()) {
            for (UserPackage userPackage : keySet) {
                if (userPackage.getUiControl() != UIControl.HIDE_BUTTON && this.currentProfileIds.contains(Integer.valueOf(userPackage.userId)) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
            }
        }
        return i;
    }

    public final int getNumVisiblePackagesLocked() {
        Set<UserPackage> keySet = this.runningTaskIdentifiers.keySet();
        int i = 0;
        if (!(keySet instanceof Collection) || !keySet.isEmpty()) {
            for (UserPackage userPackage : keySet) {
                if (userPackage.getUiControl() != UIControl.HIDE_ENTRY && this.currentProfileIds.contains(Integer.valueOf(userPackage.userId)) && (i = i + 1) < 0) {
                    CollectionsKt__CollectionsKt.throwCountOverflow();
                    throw null;
                }
            }
        }
        return i;
    }

    public final void showDialog$1(final Expandable expandable) {
        synchronized (this.lock) {
            if (this.dialog == null) {
                final SystemUIDialog create = this.systemUIDialogFactory.create();
                create.setTitle(R.string.fgs_manager_dialog_title);
                create.setMessage(R.string.fgs_manager_dialog_message);
                Context context = create.getContext();
                RecyclerView recyclerView = new RecyclerView(context);
                recyclerView.setLayoutManager(new LinearLayoutManager(1));
                recyclerView.setAdapter(this.appListAdapter);
                create.setView(recyclerView, 0, context.getResources().getDimensionPixelSize(R.dimen.fgs_manager_list_top_spacing), 0, 0);
                this.dialog = create;
                create.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$showDialog$1$1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
                        fgsManagerControllerImpl.newChangesSinceDialogWasDismissed = false;
                        synchronized (fgsManagerControllerImpl.lock) {
                            fgsManagerControllerImpl.dialog = null;
                            fgsManagerControllerImpl.updateAppItemsLocked(false);
                        }
                        FgsManagerControllerImpl fgsManagerControllerImpl2 = FgsManagerControllerImpl.this;
                        Iterator it = fgsManagerControllerImpl2.onDialogDismissedListeners.iterator();
                        while (it.hasNext()) {
                            fgsManagerControllerImpl2.mainExecutor.execute(new FgsManagerControllerImpl$updateAppItems$4(2, (ForegroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1) it.next()));
                        }
                    }
                });
                this.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$showDialog$1$2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Expandable expandable2 = Expandable.this;
                        DialogTransitionAnimator.Controller m = expandable2 != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "active_background_apps", expandable2) : null;
                        if (m == null) {
                            create.show();
                            return;
                        }
                        FgsManagerControllerImpl fgsManagerControllerImpl = this;
                        SystemUIDialog systemUIDialog = create;
                        TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                        fgsManagerControllerImpl.dialogTransitionAnimator.show(systemUIDialog, m, false);
                    }
                });
                updateAppItemsLocked(true);
            }
        }
    }

    public final void updateAppItemsLocked(final boolean z) {
        if (this.dialog == null) {
            this.backgroundExecutor.execute(new FgsManagerControllerImpl$updateAppItems$4(1, this));
            return;
        }
        Map map = this.runningTaskIdentifiers;
        final LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), Long.valueOf(((StartTimeAndIdentifiers) entry.getValue()).startTime));
        }
        final Set set = CollectionsKt.toSet(this.currentProfileIds);
        this.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$updateAppItemsLocked$2
            @Override // java.lang.Runnable
            public final void run() {
                FgsManagerControllerImpl.RunningApp runningApp;
                FgsManagerControllerImpl fgsManagerControllerImpl = FgsManagerControllerImpl.this;
                Map map2 = linkedHashMap;
                Set set2 = set;
                boolean z2 = z;
                fgsManagerControllerImpl.getClass();
                if (z2) {
                    Iterator it = map2.entrySet().iterator();
                    while (it.hasNext()) {
                        ((FgsManagerControllerImpl.UserPackage) ((Map.Entry) it.next()).getKey()).updateUiControl();
                    }
                }
                Set keySet = map2.keySet();
                ArrayList<FgsManagerControllerImpl.UserPackage> arrayList = new ArrayList();
                for (Object obj : keySet) {
                    FgsManagerControllerImpl.UserPackage userPackage = (FgsManagerControllerImpl.UserPackage) obj;
                    if (set2.contains(Integer.valueOf(userPackage.userId)) && userPackage.getUiControl() != FgsManagerControllerImpl.UIControl.HIDE_ENTRY && ((runningApp = (FgsManagerControllerImpl.RunningApp) fgsManagerControllerImpl.runningApps.get(userPackage)) == null || !runningApp.stopped)) {
                        arrayList.add(obj);
                    }
                }
                Set keySet2 = fgsManagerControllerImpl.runningApps.keySet();
                ArrayList<FgsManagerControllerImpl.UserPackage> arrayList2 = new ArrayList();
                for (Object obj2 : keySet2) {
                    if (!map2.containsKey((FgsManagerControllerImpl.UserPackage) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                for (FgsManagerControllerImpl.UserPackage userPackage2 : arrayList) {
                    PackageManager packageManager = fgsManagerControllerImpl.packageManager;
                    String str = userPackage2.packageName;
                    int i = userPackage2.userId;
                    ApplicationInfo applicationInfoAsUser = packageManager.getApplicationInfoAsUser(str, 0, i);
                    ArrayMap arrayMap = fgsManagerControllerImpl.runningApps;
                    Object obj3 = map2.get(userPackage2);
                    Intrinsics.checkNotNull(obj3);
                    long longValue = ((Number) obj3).longValue();
                    FgsManagerControllerImpl.UIControl uiControl = userPackage2.getUiControl();
                    CharSequence applicationLabel = fgsManagerControllerImpl.packageManager.getApplicationLabel(applicationInfoAsUser);
                    PackageManager packageManager2 = fgsManagerControllerImpl.packageManager;
                    Drawable userBadgedIcon = packageManager2.getUserBadgedIcon(packageManager2.getApplicationIcon(applicationInfoAsUser), UserHandle.of(i));
                    FgsManagerControllerImpl.RunningApp runningApp2 = new FgsManagerControllerImpl.RunningApp(userPackage2.userId, userPackage2.packageName, longValue, uiControl);
                    runningApp2.appLabel = applicationLabel;
                    runningApp2.icon = userBadgedIcon;
                    arrayMap.put(userPackage2, runningApp2);
                    Object obj4 = fgsManagerControllerImpl.runningApps.get(userPackage2);
                    Intrinsics.checkNotNull(obj4);
                    ((SystemClockImpl) fgsManagerControllerImpl.systemClock).getClass();
                    fgsManagerControllerImpl.backgroundExecutor.execute(new FgsManagerControllerImpl$logEvent$1(fgsManagerControllerImpl, userPackage2.packageName, userPackage2.userId, 1, android.os.SystemClock.elapsedRealtime(), ((FgsManagerControllerImpl.RunningApp) obj4).timeStarted));
                }
                for (FgsManagerControllerImpl.UserPackage userPackage3 : arrayList2) {
                    Object obj5 = fgsManagerControllerImpl.runningApps.get(userPackage3);
                    Intrinsics.checkNotNull(obj5);
                    FgsManagerControllerImpl.RunningApp runningApp3 = (FgsManagerControllerImpl.RunningApp) obj5;
                    FgsManagerControllerImpl.RunningApp runningApp4 = new FgsManagerControllerImpl.RunningApp(runningApp3.userId, runningApp3.packageName, runningApp3.timeStarted, runningApp3.uiControl);
                    runningApp4.stopped = true;
                    runningApp4.appLabel = runningApp3.appLabel;
                    runningApp4.icon = runningApp3.icon;
                    fgsManagerControllerImpl.runningApps.put(userPackage3, runningApp4);
                }
                fgsManagerControllerImpl.mainExecutor.execute(new FgsManagerControllerImpl$updateAppItems$4(0, fgsManagerControllerImpl));
            }
        });
    }

    public final void updateNumberOfVisibleRunningPackagesLocked() {
        final int numVisiblePackagesLocked = getNumVisiblePackagesLocked();
        if (numVisiblePackagesLocked != this.lastNumberOfVisiblePackages) {
            this.lastNumberOfVisiblePackages = numVisiblePackagesLocked;
            this.newChangesSinceDialogWasDismissed = true;
            for (final ForegroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1 foregroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1 : this.onNumberOfPackagesChangedListeners) {
                this.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$updateNumberOfVisibleRunningPackagesLocked$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ForegroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1.this.onNumberOfPackagesChanged(numVisiblePackagesLocked);
                    }
                });
            }
        }
    }

    @Override // com.android.systemui.qs.FgsManagerController
    public final int visibleButtonsCount() {
        int numVisibleButtonsLocked;
        synchronized (this.lock) {
            numVisibleButtonsLocked = getNumVisibleButtonsLocked();
        }
        return numVisibleButtonsLocked;
    }
}
