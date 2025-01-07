package com.android.systemui.keyguard;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Lifecycle {
    public final ArrayList mObservers = new ArrayList();

    public final void addObserver(Object obj) {
        ArrayList arrayList = this.mObservers;
        Objects.requireNonNull(obj);
        arrayList.add(obj);
    }

    public final void dispatch(Consumer consumer) {
        for (int i = 0; i < this.mObservers.size(); i++) {
            Object obj = this.mObservers.get(i);
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("dispatch#" + consumer.toString());
            }
            try {
                consumer.accept(obj);
            } finally {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        }
    }

    public final void removeObserver(Object obj) {
        this.mObservers.remove(obj);
    }
}
