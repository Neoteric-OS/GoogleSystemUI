package androidx.room;

import android.content.Context;
import android.content.Intent;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InvalidationTracker {
    public final RoomDatabase database;
    public final TriggerBasedInvalidationTracker implementation;
    public MultiInstanceClientInitState multiInstanceClientInitState;
    public MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    public final Function0 onRefreshCompleted;
    public final Function0 onRefreshScheduled;
    public final String[] tableNames;
    public final Object trackerLock;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MultiInstanceClientInitState {
        public final Context context;
        public final String name;
        public final Intent serviceIntent;

        public MultiInstanceClientInitState(Context context, String str, Intent intent) {
            this.context = context;
            this.name = str;
            this.serviceIntent = intent;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MultiInstanceClientInitState)) {
                return false;
            }
            MultiInstanceClientInitState multiInstanceClientInitState = (MultiInstanceClientInitState) obj;
            return Intrinsics.areEqual(this.context, multiInstanceClientInitState.context) && Intrinsics.areEqual(this.name, multiInstanceClientInitState.name) && Intrinsics.areEqual(this.serviceIntent, multiInstanceClientInitState.serviceIntent);
        }

        public final int hashCode() {
            return this.serviceIntent.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, this.context.hashCode() * 31, 31);
        }

        public final String toString() {
            return "MultiInstanceClientInitState(context=" + this.context + ", name=" + this.name + ", serviceIntent=" + this.serviceIntent + ')';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Observer {
        public final String[] tables;

        public Observer(String[] strArr) {
            this.tables = strArr;
        }

        public abstract void onInvalidated(Set set);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.room.InvalidationTracker$1, kotlin.jvm.internal.Lambda] */
    public InvalidationTracker(RoomDatabase roomDatabase, Map map, Map map2, String... strArr) {
        this.database = roomDatabase;
        this.tableNames = strArr;
        TriggerBasedInvalidationTracker triggerBasedInvalidationTracker = new TriggerBasedInvalidationTracker(roomDatabase, map, map2, strArr);
        this.implementation = triggerBasedInvalidationTracker;
        this.onRefreshScheduled = new InvalidationTracker$onRefreshScheduled$1(this);
        this.onRefreshCompleted = new Function0() { // from class: androidx.room.InvalidationTracker$onRefreshCompleted$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                InvalidationTracker.this.getClass();
                return Unit.INSTANCE;
            }
        };
        Collections.newSetFromMap(new IdentityHashMap());
        this.trackerLock = new Object();
        triggerBasedInvalidationTracker.onAllowRefresh = new Function0() { // from class: androidx.room.InvalidationTracker.1
            /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
            
                if ((r1 != null ? r1.isOpen() : false) != false) goto L12;
             */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke() {
                /*
                    r1 = this;
                    androidx.room.InvalidationTracker r0 = androidx.room.InvalidationTracker.this
                    androidx.room.RoomDatabase r0 = r0.database
                    boolean r0 = r0.inCompatibilityMode$room_runtime_release()
                    if (r0 == 0) goto L20
                    androidx.room.InvalidationTracker r1 = androidx.room.InvalidationTracker.this
                    androidx.room.RoomDatabase r1 = r1.database
                    androidx.room.RoomConnectionManager r1 = r1.connectionManager
                    if (r1 != 0) goto L13
                    r1 = 0
                L13:
                    androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.supportDatabase
                    r0 = 0
                    if (r1 == 0) goto L1d
                    boolean r1 = r1.isOpen()
                    goto L1e
                L1d:
                    r1 = r0
                L1e:
                    if (r1 == 0) goto L21
                L20:
                    r0 = 1
                L21:
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker.AnonymousClass1.invoke():java.lang.Object");
            }
        };
    }

    public final void refreshAsync() {
        Function0 function0 = this.onRefreshScheduled;
        Function0 function02 = this.onRefreshCompleted;
        TriggerBasedInvalidationTracker triggerBasedInvalidationTracker = this.implementation;
        AtomicBoolean atomicBoolean = triggerBasedInvalidationTracker.pendingRefresh;
        atomicBoolean.getClass();
        if (AtomicBoolean.FU.compareAndSet(atomicBoolean, 0, 1)) {
            ((InvalidationTracker$onRefreshScheduled$1) function0).invoke();
            ContextScope contextScope = triggerBasedInvalidationTracker.database.coroutineScope;
            if (contextScope == null) {
                contextScope = null;
            }
            BuildersKt.launch$default(contextScope, null, null, new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(triggerBasedInvalidationTracker, function02, null), 3);
        }
    }

    public final void startMultiInstanceInvalidation() {
        MultiInstanceClientInitState multiInstanceClientInitState = this.multiInstanceClientInitState;
        if (multiInstanceClientInitState == null) {
            throw new IllegalStateException("Required value was null.");
        }
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(multiInstanceClientInitState.context, multiInstanceClientInitState.name, this);
        Intent intent = multiInstanceClientInitState.serviceIntent;
        if (multiInstanceInvalidationClient.stopped.compareAndSet(true, false)) {
            multiInstanceInvalidationClient.appContext.bindService(intent, multiInstanceInvalidationClient.serviceConnection, 1);
            MultiInstanceInvalidationClient$observer$1 multiInstanceInvalidationClient$observer$1 = multiInstanceInvalidationClient.observer;
            if (multiInstanceInvalidationClient$observer$1 == null) {
                throw new IllegalStateException("isRemote was false of observer argument");
            }
            this.implementation.addObserverOnly$room_runtime_release(multiInstanceInvalidationClient$observer$1);
        }
        this.multiInstanceInvalidationClient = multiInstanceInvalidationClient;
    }
}
