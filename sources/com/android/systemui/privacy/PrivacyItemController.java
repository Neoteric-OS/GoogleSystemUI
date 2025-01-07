package com.android.systemui.privacy;

import android.content.pm.UserInfo;
import android.os.UserHandle;
import com.android.systemui.Dumpable;
import com.android.systemui.appops.AppOpItem;
import com.android.systemui.appops.AppOpsControllerImpl;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyItemController implements Dumpable {
    public final DelayableExecutor bgExecutor;
    public ExecutorImpl.ExecutionToken holdingRunnableCanceler;
    public final MyExecutor internalUiExecutor;
    public boolean listening;
    public final PrivacyLogger logger;
    public final PrivacyItemController$optionsCallback$1 optionsCallback;
    public final PrivacyConfig privacyConfig;
    public final PrivacyItemController$privacyItemMonitorCallback$1 privacyItemMonitorCallback;
    public final Set privacyItemMonitors;
    public final SystemClock systemClock;
    public final NotifyChangesToCallback updateListAndNotifyChanges;
    public List privacyList = EmptyList.INSTANCE;
    public final List callbacks = new ArrayList();
    public final PrivacyItemController$update$1 notifyChanges = new PrivacyItemController$update$1(this, 2);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback extends PrivacyConfig.Callback {
        void onPrivacyItemsChanged(List list);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public /* synthetic */ Companion(int i) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void getTIME_TO_HOLD_INDICATORS$annotations() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyExecutor implements Executor {
        public final DelayableExecutor delegate;
        public ExecutorImpl.ExecutionToken listeningCanceller;

        public MyExecutor(DelayableExecutor delayableExecutor) {
            this.delegate = delayableExecutor;
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            ((ExecutorImpl) this.delegate).execute(runnable);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotifyChangesToCallback implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final Object callback;
        public final Object list;

        public /* synthetic */ NotifyChangesToCallback(int i, Object obj, Object obj2) {
            this.$r8$classId = i;
            this.callback = obj;
            this.list = obj2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList arrayList;
            switch (this.$r8$classId) {
                case 0:
                    Callback callback = (Callback) this.callback;
                    if (callback != null) {
                        callback.onPrivacyItemsChanged((List) this.list);
                        return;
                    }
                    return;
                default:
                    PrivacyItemController privacyItemController = (PrivacyItemController) this.callback;
                    ExecutorImpl.ExecutionToken executionToken = privacyItemController.holdingRunnableCanceler;
                    Object obj = null;
                    if (executionToken != null) {
                        executionToken.run();
                        privacyItemController.holdingRunnableCanceler = null;
                    }
                    if (privacyItemController.listening) {
                        Set set = privacyItemController.privacyItemMonitors;
                        ArrayList arrayList2 = new ArrayList();
                        Iterator it = set.iterator();
                        while (it.hasNext()) {
                            AppOpsPrivacyItemMonitor appOpsPrivacyItemMonitor = (AppOpsPrivacyItemMonitor) ((PrivacyItemMonitor) it.next());
                            List activeAppOps = ((AppOpsControllerImpl) appOpsPrivacyItemMonitor.appOpsController).getActiveAppOps(true);
                            List userProfiles = ((UserTrackerImpl) appOpsPrivacyItemMonitor.userTracker).getUserProfiles();
                            synchronized (appOpsPrivacyItemMonitor.lock) {
                                try {
                                    ArrayList<AppOpItem> arrayList3 = new ArrayList();
                                    for (Object obj2 : activeAppOps) {
                                        AppOpItem appOpItem = (AppOpItem) obj2;
                                        if (userProfiles == null || !userProfiles.isEmpty()) {
                                            Iterator it2 = userProfiles.iterator();
                                            while (it2.hasNext()) {
                                                if (((UserInfo) it2.next()).id == UserHandle.getUserId(appOpItem.mUid)) {
                                                    arrayList3.add(obj2);
                                                }
                                            }
                                        }
                                        if (ArraysKt.contains(AppOpsPrivacyItemMonitor.USER_INDEPENDENT_OPS, appOpItem.mCode)) {
                                            arrayList3.add(obj2);
                                        }
                                    }
                                    arrayList = new ArrayList();
                                    for (AppOpItem appOpItem2 : arrayList3) {
                                        Intrinsics.checkNotNull(appOpItem2);
                                        PrivacyItem privacyItemLocked = appOpsPrivacyItemMonitor.toPrivacyItemLocked(appOpItem2);
                                        if (privacyItemLocked != null) {
                                            arrayList.add(privacyItemLocked);
                                        }
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            CollectionsKt__MutableCollectionsKt.addAll(CollectionsKt.distinct(arrayList), arrayList2);
                        }
                        List<PrivacyItem> distinct = CollectionsKt.distinct(arrayList2);
                        PrivacyLogger privacyLogger = privacyItemController.logger;
                        privacyLogger.logRetrievedPrivacyItemsList(distinct);
                        ((SystemClockImpl) privacyItemController.systemClock).getClass();
                        long elapsedRealtime = android.os.SystemClock.elapsedRealtime() - 5000;
                        List privacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core = privacyItemController.getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj3 : privacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core) {
                            PrivacyItem privacyItem = (PrivacyItem) obj3;
                            if (privacyItem.timeStampElapsed > elapsedRealtime) {
                                if (!distinct.isEmpty()) {
                                    for (PrivacyItem privacyItem2 : distinct) {
                                        if (privacyItem2.privacyType != privacyItem.privacyType || !privacyItem2.application.equals(privacyItem.application) || privacyItem2.timeStampElapsed != privacyItem.timeStampElapsed) {
                                        }
                                    }
                                }
                                arrayList4.add(obj3);
                            }
                        }
                        if (!arrayList4.isEmpty()) {
                            privacyLogger.logPrivacyItemsToHold(arrayList4);
                            Iterator it3 = arrayList4.iterator();
                            if (it3.hasNext()) {
                                obj = it3.next();
                                if (it3.hasNext()) {
                                    long j = ((PrivacyItem) obj).timeStampElapsed;
                                    do {
                                        Object next = it3.next();
                                        long j2 = ((PrivacyItem) next).timeStampElapsed;
                                        if (j > j2) {
                                            obj = next;
                                            j = j2;
                                        }
                                    } while (it3.hasNext());
                                }
                            }
                            Intrinsics.checkNotNull(obj);
                            long j3 = ((PrivacyItem) obj).timeStampElapsed - elapsedRealtime;
                            privacyLogger.logPrivacyItemsUpdateScheduled(j3);
                            privacyItemController.holdingRunnableCanceler = privacyItemController.bgExecutor.executeDelayed(privacyItemController.updateListAndNotifyChanges, j3);
                        }
                        ArrayList arrayList5 = new ArrayList();
                        for (Object obj4 : distinct) {
                            if (!((PrivacyItem) obj4).paused) {
                                arrayList5.add(obj4);
                            }
                        }
                        privacyItemController.privacyList = CollectionsKt.plus((Iterable) arrayList4, (Collection) arrayList5);
                    } else {
                        privacyItemController.privacyList = EmptyList.INSTANCE;
                    }
                    ((ExecutorImpl) ((DelayableExecutor) this.list)).execute(((PrivacyItemController) this.callback).notifyChanges);
                    return;
            }
        }
    }

    static {
        new Companion(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.privacy.PrivacyConfig$Callback, com.android.systemui.privacy.PrivacyItemController$optionsCallback$1] */
    public PrivacyItemController(DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, PrivacyConfig privacyConfig, Set set, PrivacyLogger privacyLogger, SystemClock systemClock, DumpManager dumpManager) {
        this.bgExecutor = delayableExecutor2;
        this.privacyConfig = privacyConfig;
        this.privacyItemMonitors = set;
        this.logger = privacyLogger;
        this.systemClock = systemClock;
        this.internalUiExecutor = new MyExecutor(delayableExecutor);
        this.updateListAndNotifyChanges = new NotifyChangesToCallback(1, this, delayableExecutor);
        ?? r1 = new PrivacyConfig.Callback() { // from class: com.android.systemui.privacy.PrivacyItemController$optionsCallback$1
            @Override // com.android.systemui.privacy.PrivacyConfig.Callback
            public final void onFlagLocationChanged(boolean z) {
                Iterator it = PrivacyItemController.this.callbacks.iterator();
                while (it.hasNext()) {
                    PrivacyItemController.Callback callback = (PrivacyItemController.Callback) ((WeakReference) it.next()).get();
                    if (callback != null) {
                        callback.onFlagLocationChanged(z);
                    }
                }
            }

            @Override // com.android.systemui.privacy.PrivacyConfig.Callback
            public final void onFlagMediaProjectionChanged() {
                Iterator it = PrivacyItemController.this.callbacks.iterator();
                while (it.hasNext()) {
                }
            }

            @Override // com.android.systemui.privacy.PrivacyConfig.Callback
            public final void onFlagMicCameraChanged(boolean z) {
                Iterator it = PrivacyItemController.this.callbacks.iterator();
                while (it.hasNext()) {
                    PrivacyItemController.Callback callback = (PrivacyItemController.Callback) ((WeakReference) it.next()).get();
                    if (callback != null) {
                        callback.onFlagMicCameraChanged(z);
                    }
                }
            }
        };
        this.optionsCallback = r1;
        this.privacyItemMonitorCallback = new PrivacyItemController$privacyItemMonitorCallback$1(this);
        DumpManager.registerDumpable$default(dumpManager, "PrivacyItemController", this);
        privacyConfig.addCallback(r1);
    }

    public final void addCallback(Callback callback) {
        WeakReference weakReference = new WeakReference(callback);
        this.callbacks.add(weakReference);
        boolean isEmpty = this.callbacks.isEmpty();
        MyExecutor myExecutor = this.internalUiExecutor;
        if (isEmpty || this.listening) {
            if (this.listening) {
                myExecutor.execute(new NotifyChangesToCallback(0, (Callback) weakReference.get(), getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core()));
                return;
            }
            return;
        }
        ExecutorImpl.ExecutionToken executionToken = myExecutor.listeningCanceller;
        if (executionToken != null) {
            executionToken.run();
        }
        myExecutor.listeningCanceller = myExecutor.delegate.executeDelayed(new PrivacyItemController$update$1(PrivacyItemController.this, 1), 0L);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("PrivacyItemController state:");
        asIndenting.increaseIndent();
        try {
            asIndenting.println("Listening: " + this.listening);
            asIndenting.println("Privacy Items:");
            asIndenting.increaseIndent();
            try {
                Iterator it = getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core().iterator();
                while (it.hasNext()) {
                    asIndenting.println(((PrivacyItem) it.next()).toString());
                }
                asIndenting.decreaseIndent();
                asIndenting.println("Callbacks:");
                asIndenting.increaseIndent();
                try {
                    Iterator it2 = this.callbacks.iterator();
                    while (it2.hasNext()) {
                        Callback callback = (Callback) ((WeakReference) it2.next()).get();
                        if (callback != null) {
                            asIndenting.println(callback.toString());
                        }
                    }
                    asIndenting.decreaseIndent();
                    asIndenting.println("PrivacyItemMonitors:");
                    asIndenting.increaseIndent();
                    try {
                        Iterator it3 = this.privacyItemMonitors.iterator();
                        while (it3.hasNext()) {
                            ((AppOpsPrivacyItemMonitor) ((PrivacyItemMonitor) it3.next())).dump(asIndenting, strArr);
                        }
                        asIndenting.decreaseIndent();
                        asIndenting.flush();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized List getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return CollectionsKt.toList(this.privacyList);
    }

    public final void removeCallback(Callback callback) {
        final WeakReference weakReference = new WeakReference(callback);
        ((ArrayList) this.callbacks).removeIf(new Predicate() { // from class: com.android.systemui.privacy.PrivacyItemController$removeCallback$1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                PrivacyItemController.Callback callback2 = (PrivacyItemController.Callback) ((WeakReference) obj).get();
                if (callback2 != null) {
                    return callback2.equals(weakReference.get());
                }
                return true;
            }
        });
        if (this.callbacks.isEmpty()) {
            MyExecutor myExecutor = this.internalUiExecutor;
            ExecutorImpl.ExecutionToken executionToken = myExecutor.listeningCanceller;
            if (executionToken != null) {
                executionToken.run();
            }
            myExecutor.listeningCanceller = myExecutor.delegate.executeDelayed(new PrivacyItemController$update$1(PrivacyItemController.this, 1), 0L);
        }
    }

    public static /* synthetic */ void getPrivacyList$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
