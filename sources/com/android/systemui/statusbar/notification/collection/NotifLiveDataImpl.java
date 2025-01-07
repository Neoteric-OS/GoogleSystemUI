package com.android.systemui.statusbar.notification.collection;

import android.os.Trace;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.lifecycle.Observer;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.util.ListenerSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifLiveDataImpl implements PipelineDumpable {
    public final AtomicReference atomicValue;
    public Object lastAsyncValue;
    public final Executor mainExecutor;
    public final String name;
    public final ListenerSet syncObservers = new ListenerSet();
    public final ListenerSet asyncObservers = new ListenerSet();

    public NotifLiveDataImpl(String str, Object obj, Executor executor) {
        this.name = str;
        this.mainExecutor = executor;
        this.atomicValue = new AtomicReference(obj);
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.syncObservers, "syncObservers");
        pipelineDumper.dump(this.asyncObservers, "asyncObservers");
    }

    public final Function0 setValueAndProvideDispatcher(final Object obj) {
        return !Intrinsics.areEqual(this.atomicValue.getAndSet(obj), obj) ? new Function0() { // from class: com.android.systemui.statusbar.notification.collection.NotifLiveDataImpl$setValueAndProvideDispatcher$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (!NotifLiveDataImpl.this.syncObservers.listeners.isEmpty()) {
                    String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("NotifLiveData(", NotifLiveDataImpl.this.name, ").dispatchToSyncObservers");
                    NotifLiveDataImpl notifLiveDataImpl = NotifLiveDataImpl.this;
                    Object obj2 = obj;
                    boolean isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice(m);
                    }
                    try {
                        Iterator it = notifLiveDataImpl.syncObservers.listeners.iterator();
                        while (it.hasNext()) {
                            ((Observer) it.next()).onChanged(obj2);
                        }
                    } finally {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                }
                if (!NotifLiveDataImpl.this.asyncObservers.listeners.isEmpty()) {
                    final NotifLiveDataImpl notifLiveDataImpl2 = NotifLiveDataImpl.this;
                    notifLiveDataImpl2.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.NotifLiveDataImpl$setValueAndProvideDispatcher$1.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            NotifLiveDataImpl notifLiveDataImpl3 = NotifLiveDataImpl.this;
                            Object obj3 = notifLiveDataImpl3.atomicValue.get();
                            if (Intrinsics.areEqual(notifLiveDataImpl3.lastAsyncValue, obj3)) {
                                return;
                            }
                            notifLiveDataImpl3.lastAsyncValue = obj3;
                            String m2 = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("NotifLiveData("), notifLiveDataImpl3.name, ").dispatchToAsyncObservers");
                            boolean isEnabled2 = Trace.isEnabled();
                            if (isEnabled2) {
                                TraceUtilsKt.beginSlice(m2);
                            }
                            try {
                                Iterator it2 = notifLiveDataImpl3.asyncObservers.iterator();
                                while (it2.hasNext()) {
                                    ((Observer) it2.next()).onChanged(obj3);
                                }
                            } finally {
                                if (isEnabled2) {
                                    TraceUtilsKt.endSlice();
                                }
                            }
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        } : new Function0() { // from class: com.android.systemui.statusbar.notification.collection.NotifLiveDataImpl$setValueAndProvideDispatcher$2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Unit.INSTANCE;
            }
        };
    }
}
