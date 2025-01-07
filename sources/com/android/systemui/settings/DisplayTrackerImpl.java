package com.android.systemui.settings;

import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.util.Assert;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayTrackerImpl implements DisplayTracker {
    public final Handler backgroundHandler;
    public final DisplayTrackerImpl$displayChangedListener$1 displayBrightnessChangedListener;
    public final DisplayTrackerImpl$displayChangedListener$1 displayChangedListener;
    public final DisplayManager displayManager;
    public final List displayCallbacks = new ArrayList();
    public final List brightnessCallbacks = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayTrackerDataItem {
        public final WeakReference callback;
        public final Executor executor;

        public DisplayTrackerDataItem(WeakReference weakReference, Executor executor) {
            this.callback = weakReference;
            this.executor = executor;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DisplayTrackerDataItem)) {
                return false;
            }
            DisplayTrackerDataItem displayTrackerDataItem = (DisplayTrackerDataItem) obj;
            return Intrinsics.areEqual(this.callback, displayTrackerDataItem.callback) && Intrinsics.areEqual(this.executor, displayTrackerDataItem.executor);
        }

        public final int hashCode() {
            return this.executor.hashCode() + (this.callback.hashCode() * 31);
        }

        public final String toString() {
            return "DisplayTrackerDataItem(callback=" + this.callback + ", executor=" + this.executor + ")";
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.settings.DisplayTrackerImpl$displayChangedListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.settings.DisplayTrackerImpl$displayChangedListener$1] */
    public DisplayTrackerImpl(DisplayManager displayManager, Handler handler) {
        this.displayManager = displayManager;
        this.backgroundHandler = handler;
        final int i = 0;
        this.displayChangedListener = new DisplayManager.DisplayListener(this) { // from class: com.android.systemui.settings.DisplayTrackerImpl$displayChangedListener$1
            public final /* synthetic */ DisplayTrackerImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayAdded(int i2) {
                List<DisplayTrackerImpl.DisplayTrackerDataItem> list;
                switch (i) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayAdded");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            Assert.isNotMainThread();
                            for (DisplayTrackerImpl.DisplayTrackerDataItem displayTrackerDataItem : list) {
                                if (displayTrackerDataItem.callback.get() != null) {
                                    displayTrackerDataItem.executor.execute(new DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1(displayTrackerDataItem, i2, 0));
                                }
                            }
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        return;
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayChanged(int i2) {
                boolean isEnabled;
                List list;
                List list2;
                switch (i) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayChanged");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            DisplayTrackerImpl.access$onDisplayChanged(displayTrackerImpl, i2, list);
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                                return;
                            }
                            return;
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        DisplayTrackerImpl displayTrackerImpl2 = this.this$0;
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayBrightnessChangedDisplayListener#onDisplayChanged");
                        }
                        try {
                            synchronized (displayTrackerImpl2.brightnessCallbacks) {
                                list2 = CollectionsKt.toList(displayTrackerImpl2.brightnessCallbacks);
                            }
                            DisplayTrackerImpl.access$onDisplayChanged(displayTrackerImpl2, i2, list2);
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayRemoved(int i2) {
                List<DisplayTrackerImpl.DisplayTrackerDataItem> list;
                switch (i) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayRemoved");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            Assert.isNotMainThread();
                            for (DisplayTrackerImpl.DisplayTrackerDataItem displayTrackerDataItem : list) {
                                if (displayTrackerDataItem.callback.get() != null) {
                                    displayTrackerDataItem.executor.execute(new DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1(displayTrackerDataItem, i2, 1));
                                }
                            }
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        return;
                }
            }

            private final void onDisplayAdded$com$android$systemui$settings$DisplayTrackerImpl$displayBrightnessChangedListener$1(int i2) {
            }

            private final void onDisplayRemoved$com$android$systemui$settings$DisplayTrackerImpl$displayBrightnessChangedListener$1(int i2) {
            }
        };
        final int i2 = 1;
        this.displayBrightnessChangedListener = new DisplayManager.DisplayListener(this) { // from class: com.android.systemui.settings.DisplayTrackerImpl$displayChangedListener$1
            public final /* synthetic */ DisplayTrackerImpl this$0;

            {
                this.this$0 = this;
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayAdded(int i22) {
                List<DisplayTrackerImpl.DisplayTrackerDataItem> list;
                switch (i2) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayAdded");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            Assert.isNotMainThread();
                            for (DisplayTrackerImpl.DisplayTrackerDataItem displayTrackerDataItem : list) {
                                if (displayTrackerDataItem.callback.get() != null) {
                                    displayTrackerDataItem.executor.execute(new DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1(displayTrackerDataItem, i22, 0));
                                }
                            }
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        return;
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayChanged(int i22) {
                boolean isEnabled;
                List list;
                List list2;
                switch (i2) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayChanged");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            DisplayTrackerImpl.access$onDisplayChanged(displayTrackerImpl, i22, list);
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                                return;
                            }
                            return;
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        DisplayTrackerImpl displayTrackerImpl2 = this.this$0;
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayBrightnessChangedDisplayListener#onDisplayChanged");
                        }
                        try {
                            synchronized (displayTrackerImpl2.brightnessCallbacks) {
                                list2 = CollectionsKt.toList(displayTrackerImpl2.brightnessCallbacks);
                            }
                            DisplayTrackerImpl.access$onDisplayChanged(displayTrackerImpl2, i22, list2);
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public final void onDisplayRemoved(int i22) {
                List<DisplayTrackerImpl.DisplayTrackerDataItem> list;
                switch (i2) {
                    case 0:
                        DisplayTrackerImpl displayTrackerImpl = this.this$0;
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("DisplayTrackerImpl.displayChangedDisplayListener#onDisplayRemoved");
                        }
                        try {
                            synchronized (displayTrackerImpl.displayCallbacks) {
                                list = CollectionsKt.toList(displayTrackerImpl.displayCallbacks);
                            }
                            Assert.isNotMainThread();
                            for (DisplayTrackerImpl.DisplayTrackerDataItem displayTrackerDataItem : list) {
                                if (displayTrackerDataItem.callback.get() != null) {
                                    displayTrackerDataItem.executor.execute(new DisplayTrackerImpl$onDisplayAdded$$inlined$notifySubscribers$1(displayTrackerDataItem, i22, 1));
                                }
                            }
                            if (isEnabled) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    default:
                        return;
                }
            }

            private final void onDisplayAdded$com$android$systemui$settings$DisplayTrackerImpl$displayBrightnessChangedListener$1(int i22) {
            }

            private final void onDisplayRemoved$com$android$systemui$settings$DisplayTrackerImpl$displayBrightnessChangedListener$1(int i22) {
            }
        };
    }

    public static final void access$onDisplayChanged(DisplayTrackerImpl displayTrackerImpl, final int i, List list) {
        displayTrackerImpl.getClass();
        Assert.isNotMainThread();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final DisplayTrackerDataItem displayTrackerDataItem = (DisplayTrackerDataItem) it.next();
            if (displayTrackerDataItem.callback.get() != null) {
                displayTrackerDataItem.executor.execute(new Runnable(i) { // from class: com.android.systemui.settings.DisplayTrackerImpl$onDisplayChanged$$inlined$notifySubscribers$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DisplayTracker.Callback callback = (DisplayTracker.Callback) DisplayTrackerImpl.DisplayTrackerDataItem.this.callback.get();
                        if (callback != null) {
                            callback.onDisplayChanged();
                        }
                    }
                });
            }
        }
    }

    public final void addDisplayChangeCallback(DisplayTracker.Callback callback, Executor executor) {
        synchronized (this.displayCallbacks) {
            try {
                if (this.displayCallbacks.isEmpty()) {
                    this.displayManager.registerDisplayListener(this.displayChangedListener, this.backgroundHandler);
                }
                this.displayCallbacks.add(new DisplayTrackerDataItem(new WeakReference(callback), executor));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void getDisplayBrightnessChangedListener$annotations() {
    }

    public static /* synthetic */ void getDisplayChangedListener$annotations() {
    }
}
