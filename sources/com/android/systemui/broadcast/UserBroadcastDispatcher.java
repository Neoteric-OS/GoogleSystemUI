package com.android.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IndentingPrintWriter;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.android.internal.util.Preconditions;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.logging.BroadcastDispatcherLogger;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserBroadcastDispatcher implements Dumpable {
    public final Context context;
    public final BroadcastDispatcherLogger logger;
    public final PendingRemovalStore removalPendingStore;
    public final int userId;
    public final Executor workerExecutor;
    public final Handler workerHandler;
    public final Looper workerLooper;
    public final ArrayMap actionsToActionsReceivers = new ArrayMap();
    public final ArrayMap receiverToActions = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ReceiverProperties {
        public final String action;
        public final int flags;
        public final String permission;

        public ReceiverProperties(String str, String str2, int i) {
            this.action = str;
            this.flags = i;
            this.permission = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ReceiverProperties)) {
                return false;
            }
            ReceiverProperties receiverProperties = (ReceiverProperties) obj;
            return Intrinsics.areEqual(this.action, receiverProperties.action) && this.flags == receiverProperties.flags && Intrinsics.areEqual(this.permission, receiverProperties.permission);
        }

        public final int hashCode() {
            int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.flags, this.action.hashCode() * 31, 31);
            String str = this.permission;
            return m + (str == null ? 0 : str.hashCode());
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ReceiverProperties(action=");
            sb.append(this.action);
            sb.append(", flags=");
            sb.append(this.flags);
            sb.append(", permission=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.permission, ")");
        }
    }

    static {
        new AtomicInteger(0);
    }

    public UserBroadcastDispatcher(Context context, int i, Looper looper, Executor executor, BroadcastDispatcherLogger broadcastDispatcherLogger, PendingRemovalStore pendingRemovalStore) {
        this.context = context;
        this.userId = i;
        this.workerLooper = looper;
        this.workerExecutor = executor;
        this.logger = broadcastDispatcherLogger;
        this.removalPendingStore = pendingRemovalStore;
        this.workerHandler = new Handler(looper);
    }

    public ActionReceiver createActionReceiver$frameworks__base__packages__SystemUI__android_common__SystemUI_core(String str, String str2, int i) {
        UserBroadcastDispatcher$createActionReceiver$1 userBroadcastDispatcher$createActionReceiver$1 = new UserBroadcastDispatcher$createActionReceiver$1(str, this, str2, i);
        UserBroadcastDispatcher$createActionReceiver$2 userBroadcastDispatcher$createActionReceiver$2 = new UserBroadcastDispatcher$createActionReceiver$2(str, this);
        Executor executor = this.workerExecutor;
        UserBroadcastDispatcher$createActionReceiver$3 userBroadcastDispatcher$createActionReceiver$3 = new UserBroadcastDispatcher$createActionReceiver$3(2, this.removalPendingStore, PendingRemovalStore.class, "isPendingRemoval", "isPendingRemoval(Landroid/content/BroadcastReceiver;I)Z", 0);
        return new ActionReceiver(str, this.userId, userBroadcastDispatcher$createActionReceiver$1, userBroadcastDispatcher$createActionReceiver$2, executor, this.logger, userBroadcastDispatcher$createActionReceiver$3);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        boolean z = printWriter instanceof IndentingPrintWriter;
        if (z) {
            ((IndentingPrintWriter) printWriter).increaseIndent();
        }
        for (Map.Entry entry : this.actionsToActionsReceivers.entrySet()) {
            ReceiverProperties receiverProperties = (ReceiverProperties) entry.getKey();
            ActionReceiver actionReceiver = (ActionReceiver) entry.getValue();
            String str = receiverProperties.action;
            StringBuilder sb = new StringBuilder("");
            int i = receiverProperties.flags;
            if ((i & 1) != 0) {
                sb.append("instant_apps,");
            }
            if ((i & 4) != 0) {
                sb.append("not_exported,");
            }
            if ((i & 2) != 0) {
                sb.append("exported");
            }
            if (sb.length() == 0) {
                sb.append(i);
            }
            String sb2 = sb.toString();
            String str2 = "):";
            String str3 = receiverProperties.permission;
            if (str3 != null) {
                str2 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(":", str3, "):");
            }
            printWriter.println(MotionLayout$$ExternalSyntheticOutline0.m("(", str, ": ", sb2, str2));
            actionReceiver.dump(printWriter, strArr);
        }
        if (z) {
            ((IndentingPrintWriter) printWriter).decreaseIndent();
        }
    }

    public final boolean isReceiverReferenceHeld$frameworks__base__packages__SystemUI__android_common__SystemUI_core(BroadcastReceiver broadcastReceiver) {
        Collection values = this.actionsToActionsReceivers.values();
        if (!values.isEmpty()) {
            Iterator it = values.iterator();
            loop0: while (it.hasNext()) {
                ArraySet arraySet = ((ActionReceiver) it.next()).receiverDatas;
                if (arraySet == null || !arraySet.isEmpty()) {
                    Iterator it2 = arraySet.iterator();
                    while (it2.hasNext()) {
                        if (Intrinsics.areEqual(((ReceiverData) it2.next()).receiver, broadcastReceiver)) {
                            break loop0;
                        }
                    }
                }
            }
        }
        return this.receiverToActions.containsKey(broadcastReceiver);
    }

    public final void unregisterReceiver(final BroadcastReceiver broadcastReceiver) {
        Preconditions.checkState(this.workerLooper.isCurrentThread(), "This method should only be called from the worker thread (which is expected to be the BroadcastRunning thread)");
        for (String str : (Iterable) this.receiverToActions.getOrDefault(broadcastReceiver, new LinkedHashSet())) {
            for (Map.Entry entry : this.actionsToActionsReceivers.entrySet()) {
                ReceiverProperties receiverProperties = (ReceiverProperties) entry.getKey();
                ActionReceiver actionReceiver = (ActionReceiver) entry.getValue();
                if (receiverProperties.action.equals(str) && CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(actionReceiver.receiverDatas, new Function1() { // from class: com.android.systemui.broadcast.ActionReceiver$removeReceiver$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(Intrinsics.areEqual(((ReceiverData) obj).receiver, broadcastReceiver));
                    }
                }, true) && actionReceiver.receiverDatas.isEmpty() && actionReceiver.registered) {
                    ((UserBroadcastDispatcher$createActionReceiver$2) actionReceiver.unregisterAction).invoke(actionReceiver);
                    actionReceiver.registered = false;
                    actionReceiver.activeCategories.clear();
                }
            }
        }
        this.receiverToActions.remove(broadcastReceiver);
        this.logger.logReceiverUnregistered(this.userId, broadcastReceiver);
    }

    public static /* synthetic */ void getActionsToActionsReceivers$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
