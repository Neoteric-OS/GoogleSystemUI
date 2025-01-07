package com.android.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IndentingPrintWriter;
import android.util.SparseArray;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.internal.util.Preconditions;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.UserBroadcastDispatcher;
import com.android.systemui.broadcast.logging.BroadcastDispatcherLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BroadcastDispatcher implements Dumpable {
    public final Executor broadcastExecutor;
    public final Looper broadcastLooper;
    public final Context context;
    public final DumpManager dumpManager;
    public final BroadcastDispatcher$handler$1 handler;
    public final BroadcastDispatcherLogger logger;
    public final Executor mainExecutor;
    public final SparseArray receiversByUser = new SparseArray(20);
    public final PendingRemovalStore removalPendingStore;
    public final UserTracker userTracker;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.broadcast.BroadcastDispatcher$handler$1] */
    public BroadcastDispatcher(Context context, Executor executor, final Looper looper, Executor executor2, DumpManager dumpManager, BroadcastDispatcherLogger broadcastDispatcherLogger, UserTracker userTracker, PendingRemovalStore pendingRemovalStore) {
        this.context = context;
        this.mainExecutor = executor;
        this.broadcastLooper = looper;
        this.broadcastExecutor = executor2;
        this.dumpManager = dumpManager;
        this.logger = broadcastDispatcherLogger;
        this.userTracker = userTracker;
        this.removalPendingStore = pendingRemovalStore;
        this.handler = new Handler(looper) { // from class: com.android.systemui.broadcast.BroadcastDispatcher$handler$1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                BroadcastDispatcher broadcastDispatcher = BroadcastDispatcher.this;
                if (i != 0) {
                    if (i == 1) {
                        int size = broadcastDispatcher.receiversByUser.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            ((UserBroadcastDispatcher) broadcastDispatcher.receiversByUser.valueAt(i2)).unregisterReceiver((BroadcastReceiver) message.obj);
                        }
                        PendingRemovalStore pendingRemovalStore2 = broadcastDispatcher.removalPendingStore;
                        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) message.obj;
                        synchronized (pendingRemovalStore2.pendingRemoval) {
                            pendingRemovalStore2.pendingRemoval.remove(-1, broadcastReceiver);
                        }
                        pendingRemovalStore2.logger.logClearedAfterRemoval(-1, broadcastReceiver);
                        return;
                    }
                    if (i != 2) {
                        super.handleMessage(message);
                        return;
                    }
                    int i3 = message.arg1;
                    if (i3 == -2) {
                        i3 = ((UserTrackerImpl) broadcastDispatcher.userTracker).getUserId();
                    }
                    UserBroadcastDispatcher userBroadcastDispatcher = (UserBroadcastDispatcher) broadcastDispatcher.receiversByUser.get(i3);
                    if (userBroadcastDispatcher != null) {
                        userBroadcastDispatcher.unregisterReceiver((BroadcastReceiver) message.obj);
                    }
                    PendingRemovalStore pendingRemovalStore3 = broadcastDispatcher.removalPendingStore;
                    BroadcastReceiver broadcastReceiver2 = (BroadcastReceiver) message.obj;
                    synchronized (pendingRemovalStore3.pendingRemoval) {
                        pendingRemovalStore3.pendingRemoval.remove(i3, broadcastReceiver2);
                    }
                    pendingRemovalStore3.logger.logClearedAfterRemoval(i3, broadcastReceiver2);
                    return;
                }
                ReceiverData receiverData = (ReceiverData) message.obj;
                int i4 = message.arg1;
                int userId = receiverData.user.getIdentifier() == -2 ? ((UserTrackerImpl) broadcastDispatcher.userTracker).getUserId() : receiverData.user.getIdentifier();
                if (userId < -1) {
                    throw new IllegalStateException(GenericDocument$$ExternalSyntheticOutline0.m("Attempting to register receiver for invalid user {", "}", userId));
                }
                UserBroadcastDispatcher userBroadcastDispatcher2 = (UserBroadcastDispatcher) broadcastDispatcher.receiversByUser.get(userId, broadcastDispatcher.createUBRForUser(userId));
                broadcastDispatcher.receiversByUser.put(userId, userBroadcastDispatcher2);
                Preconditions.checkState(userBroadcastDispatcher2.workerLooper.isCurrentThread(), "This method should only be called from the worker thread (which is expected to be the BroadcastRunning thread)");
                ArrayMap arrayMap = userBroadcastDispatcher2.receiverToActions;
                BroadcastReceiver broadcastReceiver3 = receiverData.receiver;
                Object obj = arrayMap.get(broadcastReceiver3);
                if (obj == null) {
                    obj = new ArraySet();
                    arrayMap.put(broadcastReceiver3, obj);
                }
                Collection collection = (Collection) obj;
                Iterator<String> actionsIterator = receiverData.filter.actionsIterator();
                EmptySequence emptySequence = EmptySequence.INSTANCE;
                CollectionsKt__MutableCollectionsKt.addAll(collection, actionsIterator != null ? SequencesKt.asSequence(actionsIterator) : emptySequence);
                Iterator<String> actionsIterator2 = receiverData.filter.actionsIterator();
                while (actionsIterator2.hasNext()) {
                    String next = actionsIterator2.next();
                    ArrayMap arrayMap2 = userBroadcastDispatcher2.actionsToActionsReceivers;
                    Intrinsics.checkNotNull(next);
                    String str = receiverData.permission;
                    UserBroadcastDispatcher.ReceiverProperties receiverProperties = new UserBroadcastDispatcher.ReceiverProperties(next, str, i4);
                    Object obj2 = arrayMap2.get(receiverProperties);
                    if (obj2 == null) {
                        obj2 = userBroadcastDispatcher2.createActionReceiver$frameworks__base__packages__SystemUI__android_common__SystemUI_core(next, str, i4);
                        arrayMap2.put(receiverProperties, obj2);
                    }
                    ActionReceiver actionReceiver = (ActionReceiver) obj2;
                    actionReceiver.getClass();
                    if (!receiverData.filter.hasAction(actionReceiver.action)) {
                        throw new IllegalArgumentException("Trying to attach to " + actionReceiver.action + " without correct action,receiver: " + receiverData.receiver);
                    }
                    ArraySet arraySet = actionReceiver.activeCategories;
                    Iterator<String> categoriesIterator = receiverData.filter.categoriesIterator();
                    boolean addAll = CollectionsKt__MutableCollectionsKt.addAll(arraySet, categoriesIterator != null ? SequencesKt.asSequence(categoriesIterator) : emptySequence);
                    if (actionReceiver.receiverDatas.add(receiverData) && actionReceiver.receiverDatas.size() == 1) {
                        Function2 function2 = actionReceiver.registerAction;
                        IntentFilter intentFilter = new IntentFilter(actionReceiver.action);
                        Iterator it = actionReceiver.activeCategories.iterator();
                        while (it.hasNext()) {
                            intentFilter.addCategory((String) it.next());
                        }
                        ((UserBroadcastDispatcher$createActionReceiver$1) function2).invoke(actionReceiver, intentFilter);
                        actionReceiver.registered = true;
                    } else if (addAll) {
                        ((UserBroadcastDispatcher$createActionReceiver$2) actionReceiver.unregisterAction).invoke(actionReceiver);
                        Function2 function22 = actionReceiver.registerAction;
                        IntentFilter intentFilter2 = new IntentFilter(actionReceiver.action);
                        Iterator it2 = actionReceiver.activeCategories.iterator();
                        while (it2.hasNext()) {
                            intentFilter2.addCategory((String) it2.next());
                        }
                        ((UserBroadcastDispatcher$createActionReceiver$1) function22).invoke(actionReceiver, intentFilter2);
                    }
                }
                userBroadcastDispatcher2.logger.logReceiverRegistered(userBroadcastDispatcher2.userId, receiverData.receiver, i4);
            }
        };
    }

    public static Flow broadcastFlow$default(BroadcastDispatcher broadcastDispatcher, IntentFilter intentFilter, UserHandle userHandle, Function2 function2, int i) {
        if ((i & 2) != 0) {
            userHandle = null;
        }
        broadcastDispatcher.getClass();
        return FlowConflatedKt.conflatedCallbackFlow(new BroadcastDispatcher$broadcastFlow$1(broadcastDispatcher, intentFilter, userHandle, 2, null, function2, null));
    }

    public static /* synthetic */ void registerReceiver$default(BroadcastDispatcher broadcastDispatcher, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle userHandle, int i, int i2) {
        Executor executor2 = (i2 & 4) != 0 ? null : executor;
        UserHandle userHandle2 = (i2 & 8) != 0 ? null : userHandle;
        if ((i2 & 16) != 0) {
            i = 2;
        }
        broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter, executor2, userHandle2, i, (i2 & 32) == 0 ? "android.permission.DUMP" : null);
    }

    public static void registerReceiverWithHandler$default(BroadcastDispatcher broadcastDispatcher, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler, UserHandle userHandle, int i) {
        if ((i & 8) != 0) {
            userHandle = broadcastDispatcher.context.getUser();
        }
        broadcastDispatcher.getClass();
        broadcastDispatcher.registerReceiver(broadcastReceiver, intentFilter, new HandlerExecutor(handler), userHandle, 2, null);
    }

    public final Flow broadcastFlow(IntentFilter intentFilter, UserHandle userHandle) {
        return FlowConflatedKt.conflatedCallbackFlow(new BroadcastDispatcher$broadcastFlow$1(this, intentFilter, userHandle, 2, null, BroadcastDispatcher$broadcastFlow$2.INSTANCE, null));
    }

    public UserBroadcastDispatcher createUBRForUser(int i) {
        return new UserBroadcastDispatcher(this.context, i, this.broadcastLooper, this.broadcastExecutor, this.logger, this.removalPendingStore);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Broadcast dispatcher:");
        PrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.increaseIndent();
        int size = this.receiversByUser.size();
        for (int i = 0; i < size; i++) {
            indentingPrintWriter.println("User " + this.receiversByUser.keyAt(i));
            ((UserBroadcastDispatcher) this.receiversByUser.valueAt(i)).dump(indentingPrintWriter, strArr);
        }
        indentingPrintWriter.println("Pending removal:");
        this.removalPendingStore.dump(indentingPrintWriter, strArr);
        indentingPrintWriter.decreaseIndent();
    }

    public final void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        registerReceiver$default(this, broadcastReceiver, intentFilter, null, null, 0, 60);
    }

    public final void registerReceiverWithHandler(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        registerReceiverWithHandler$default(this, broadcastReceiver, intentFilter, handler, null, 56);
    }

    public final void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        PendingRemovalStore pendingRemovalStore = this.removalPendingStore;
        pendingRemovalStore.logger.logTagForRemoval(-1, broadcastReceiver);
        synchronized (pendingRemovalStore.pendingRemoval) {
            pendingRemovalStore.pendingRemoval.add(-1, broadcastReceiver);
        }
        obtainMessage(1, broadcastReceiver).sendToTarget();
    }

    public final void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle userHandle) {
        registerReceiver$default(this, broadcastReceiver, intentFilter, executor, userHandle, 0, 48);
    }

    public final void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Executor executor, UserHandle userHandle, int i, String str) {
        StringBuilder sb = new StringBuilder();
        if (intentFilter.countActions() == 0) {
            sb.append("Filter must contain at least one action. ");
        }
        if (intentFilter.countDataAuthorities() != 0) {
            sb.append("Filter cannot contain DataAuthorities. ");
        }
        if (intentFilter.countDataPaths() != 0) {
            sb.append("Filter cannot contain DataPaths. ");
        }
        if (intentFilter.countDataSchemes() != 0) {
            sb.append("Filter cannot contain DataSchemes. ");
        }
        if (intentFilter.countDataTypes() != 0) {
            sb.append("Filter cannot contain DataTypes. ");
        }
        if (intentFilter.getPriority() != 0) {
            sb.append("Filter cannot modify priority. ");
        }
        if (TextUtils.isEmpty(sb)) {
            if (executor == null) {
                executor = this.mainExecutor;
            }
            Executor executor2 = executor;
            if (userHandle == null) {
                userHandle = this.context.getUser();
            }
            obtainMessage(0, i, 0, new ReceiverData(broadcastReceiver, intentFilter, executor2, userHandle, str)).sendToTarget();
            return;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public static Flow broadcastFlow$default(BroadcastDispatcher broadcastDispatcher, IntentFilter intentFilter, UserHandle userHandle, int i) {
        UserHandle userHandle2 = (i & 2) != 0 ? null : userHandle;
        int i2 = (i & 4) != 0 ? 2 : 4;
        String str = (i & 8) == 0 ? "com.android.systemui.permission.SELF" : null;
        BroadcastDispatcher$broadcastFlow$2 broadcastDispatcher$broadcastFlow$2 = BroadcastDispatcher$broadcastFlow$2.INSTANCE;
        broadcastDispatcher.getClass();
        return FlowConflatedKt.conflatedCallbackFlow(new BroadcastDispatcher$broadcastFlow$1(broadcastDispatcher, intentFilter, userHandle2, i2, str, broadcastDispatcher$broadcastFlow$2, null));
    }
}
