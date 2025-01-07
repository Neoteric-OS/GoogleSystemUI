package com.android.app.viewcapture;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.permission.SafeCloseable;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.android.app.viewcapture.ViewCapture;
import com.android.app.viewcapture.ViewCapture.WindowListener.AnonymousClass1;
import com.android.app.viewcapture.data.ExportedData;
import com.android.app.viewcapture.data.ViewNode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewCapture {
    public static final long MAGIC_NUMBER_FOR_WINSCOPE = (ExportedData.MagicNumber.MAGIC_NUMBER_H.getNumber() << 32) | ExportedData.MagicNumber.MAGIC_NUMBER_L.getNumber();
    public static final LooperExecutor MAIN_EXECUTOR = new LooperExecutor(Looper.getMainLooper());
    public final LooperExecutor mBgExecutor;
    public final int mMemorySize;
    public final List mListeners = new ArrayList();
    public ViewPropertyRef mPool = new ViewPropertyRef();
    public boolean mIsEnabled = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewIdProvider {
        public final SparseArray mNames = new SparseArray();
        public final Resources mRes;

        public ViewIdProvider(Resources resources) {
            this.mRes = resources;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewPropertyRef implements Runnable {
        public float alpha;
        public int bottom;
        public Class clazz;
        public float elevation;
        public int hashCode;
        public int id;
        public int left;
        public ViewPropertyRef next;
        public int right;
        public float scaleX;
        public float scaleY;
        public int scrollX;
        public int scrollY;
        public int top;
        public float translateX;
        public float translateY;
        public View view;
        public int visibility;
        public boolean willNotDraw;
        public int childCount = 0;
        public ViewCapture$$ExternalSyntheticLambda4 callback = null;
        public long elapsedRealtimeNanos = 0;

        @Override // java.lang.Runnable
        public final void run() {
            ViewCapture$$ExternalSyntheticLambda4 viewCapture$$ExternalSyntheticLambda4 = this.callback;
            this.callback = null;
            if (viewCapture$$ExternalSyntheticLambda4 != null) {
                viewCapture$$ExternalSyntheticLambda4.accept(this);
            }
        }

        public final ViewPropertyRef toProto(ViewIdProvider viewIdProvider, ArrayList arrayList, ViewNode.Builder builder) {
            int indexOf = arrayList.indexOf(this.clazz);
            if (indexOf < 0) {
                indexOf = arrayList.size();
                arrayList.add(this.clazz);
            }
            builder.copyOnWrite();
            ViewNode.access$100((ViewNode) builder.instance, indexOf);
            int i = this.hashCode;
            builder.copyOnWrite();
            ViewNode.access$300((ViewNode) builder.instance, i);
            int i2 = this.id;
            String str = (String) viewIdProvider.mNames.get(i2);
            if (str == null) {
                if (i2 >= 0) {
                    try {
                        str = viewIdProvider.mRes.getResourceTypeName(i2) + '/' + viewIdProvider.mRes.getResourceEntryName(i2);
                    } catch (Resources.NotFoundException unused) {
                        str = "id/0x" + Integer.toHexString(i2).toUpperCase();
                    }
                } else {
                    str = "NO_ID";
                }
                viewIdProvider.mNames.put(i2, str);
            }
            builder.copyOnWrite();
            ViewNode.access$1100((ViewNode) builder.instance, str);
            int i3 = this.left;
            builder.copyOnWrite();
            ViewNode.access$1400((ViewNode) builder.instance, i3);
            int i4 = this.top;
            builder.copyOnWrite();
            ViewNode.access$1600((ViewNode) builder.instance, i4);
            int i5 = this.right - this.left;
            builder.copyOnWrite();
            ViewNode.access$1800((ViewNode) builder.instance, i5);
            int i6 = this.bottom - this.top;
            builder.copyOnWrite();
            ViewNode.access$2000((ViewNode) builder.instance, i6);
            float f = this.translateX;
            builder.copyOnWrite();
            ViewNode.access$2600((ViewNode) builder.instance, f);
            float f2 = this.translateY;
            builder.copyOnWrite();
            ViewNode.access$2800((ViewNode) builder.instance, f2);
            int i7 = this.scrollX;
            builder.copyOnWrite();
            ViewNode.access$2200((ViewNode) builder.instance, i7);
            int i8 = this.scrollY;
            builder.copyOnWrite();
            ViewNode.access$2400((ViewNode) builder.instance, i8);
            float f3 = this.scaleX;
            builder.copyOnWrite();
            ViewNode.access$3000((ViewNode) builder.instance, f3);
            float f4 = this.scaleY;
            builder.copyOnWrite();
            ViewNode.access$3200((ViewNode) builder.instance, f4);
            float f5 = this.alpha;
            builder.copyOnWrite();
            ViewNode.access$3400((ViewNode) builder.instance, f5);
            int i9 = this.visibility;
            builder.copyOnWrite();
            ViewNode.access$4000((ViewNode) builder.instance, i9);
            boolean z = this.willNotDraw;
            builder.copyOnWrite();
            ViewNode.access$3600((ViewNode) builder.instance, z);
            float f6 = this.elevation;
            builder.copyOnWrite();
            ViewNode.access$4200((ViewNode) builder.instance, f6);
            builder.copyOnWrite();
            ViewNode.access$3800((ViewNode) builder.instance);
            ViewPropertyRef viewPropertyRef = this.next;
            for (int i10 = 0; i10 < this.childCount && viewPropertyRef != null; i10++) {
                ViewNode.Builder newBuilder = ViewNode.newBuilder();
                viewPropertyRef = viewPropertyRef.toProto(viewIdProvider, arrayList, newBuilder);
                builder.copyOnWrite();
                ViewNode.access$600((ViewNode) builder.instance, (ViewNode) newBuilder.build());
            }
            return viewPropertyRef;
        }

        public final void transferFrom(View view) {
            this.view = view;
            this.left = view.getLeft();
            this.top = view.getTop();
            this.right = view.getRight();
            this.bottom = view.getBottom();
            this.scrollX = view.getScrollX();
            this.scrollY = view.getScrollY();
            this.translateX = view.getTranslationX();
            this.translateY = view.getTranslationY();
            this.scaleX = view.getScaleX();
            this.scaleY = view.getScaleY();
            this.alpha = view.getAlpha();
            this.elevation = view.getElevation();
            this.visibility = view.getVisibility();
            this.willNotDraw = view.willNotDraw();
        }

        public final void transferTo(ViewPropertyRef viewPropertyRef) {
            viewPropertyRef.clazz = this.clazz;
            viewPropertyRef.hashCode = this.hashCode;
            viewPropertyRef.childCount = this.childCount;
            viewPropertyRef.id = this.id;
            viewPropertyRef.left = this.left;
            viewPropertyRef.top = this.top;
            viewPropertyRef.right = this.right;
            viewPropertyRef.bottom = this.bottom;
            viewPropertyRef.scrollX = this.scrollX;
            viewPropertyRef.scrollY = this.scrollY;
            viewPropertyRef.scaleX = this.scaleX;
            viewPropertyRef.scaleY = this.scaleY;
            viewPropertyRef.translateX = this.translateX;
            viewPropertyRef.translateY = this.translateY;
            viewPropertyRef.alpha = this.alpha;
            viewPropertyRef.visibility = this.visibility;
            viewPropertyRef.willNotDraw = this.willNotDraw;
            viewPropertyRef.elevation = this.elevation;
        }
    }

    public ViewCapture(int i, final int i2, LooperExecutor looperExecutor) {
        this.mMemorySize = i;
        this.mBgExecutor = looperExecutor;
        looperExecutor.execute(new Runnable() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ViewCapture viewCapture = ViewCapture.this;
                int i3 = i2;
                viewCapture.getClass();
                ViewCapture.ViewPropertyRef viewPropertyRef = new ViewCapture.ViewPropertyRef();
                int i4 = 0;
                ViewCapture.ViewPropertyRef viewPropertyRef2 = viewPropertyRef;
                while (i4 < i3) {
                    ViewCapture.ViewPropertyRef viewPropertyRef3 = new ViewCapture.ViewPropertyRef();
                    viewPropertyRef2.next = viewPropertyRef3;
                    i4++;
                    viewPropertyRef2 = viewPropertyRef3;
                }
                ViewCapture.MAIN_EXECUTOR.execute(new ViewCapture$$ExternalSyntheticLambda7(viewCapture, viewPropertyRef, viewPropertyRef2, 0));
            }
        });
    }

    public static LooperExecutor createAndStartNewLooperExecutor(int i, String str) {
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        return new LooperExecutor(handlerThread.getLooper());
    }

    public ExportedData getExportedData(Context context) throws InterruptedException, ExecutionException {
        ArrayList arrayList = new ArrayList();
        ExportedData.Builder newBuilder = ExportedData.newBuilder();
        newBuilder.copyOnWrite();
        ExportedData.access$100((ExportedData) newBuilder.instance, MAGIC_NUMBER_FOR_WINSCOPE);
        String packageName = context.getPackageName();
        newBuilder.copyOnWrite();
        ExportedData.access$900((ExportedData) newBuilder.instance, packageName);
        Iterable iterable = (Iterable) getWindowData(context, arrayList, new ViewCapture$$ExternalSyntheticLambda0()).get();
        newBuilder.copyOnWrite();
        ExportedData.access$600((ExportedData) newBuilder.instance, iterable);
        List list = arrayList.stream().map(new ViewCapture$$ExternalSyntheticLambda10()).toList();
        newBuilder.copyOnWrite();
        ExportedData.access$1400((ExportedData) newBuilder.instance, list);
        long nanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - SystemClock.elapsedRealtimeNanos();
        newBuilder.copyOnWrite();
        ExportedData.access$1700((ExportedData) newBuilder.instance, nanos);
        return (ExportedData) newBuilder.build();
    }

    public final CompletableFuture getWindowData(Context context, ArrayList arrayList, final Predicate predicate) {
        return CompletableFuture.supplyAsync(new Supplier() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                ViewCapture viewCapture = ViewCapture.this;
                return viewCapture.mListeners.stream().filter(predicate).toList();
            }
        }, MAIN_EXECUTOR).thenApplyAsync((Function) new ViewCapture$$ExternalSyntheticLambda12(new ViewIdProvider(context.getResources()), arrayList, 0), (Executor) this.mBgExecutor);
    }

    public SafeCloseable startCapture(View view, String str) {
        final WindowListener windowListener = new WindowListener(view, str);
        if (this.mIsEnabled) {
            MAIN_EXECUTOR.execute(new Runnable() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    ViewCapture.WindowListener windowListener2 = ViewCapture.WindowListener.this;
                    View view2 = windowListener2.mRoot;
                    if (view2 == null) {
                        return;
                    }
                    windowListener2.mIsActive = true;
                    if (!view2.isAttachedToWindow()) {
                        windowListener2.mRoot.addOnAttachStateChangeListener(windowListener2.new AnonymousClass1());
                        return;
                    }
                    View view3 = windowListener2.mRoot;
                    if (view3 != null) {
                        view3.getViewTreeObserver().removeOnDrawListener(windowListener2);
                        windowListener2.mRoot.getViewTreeObserver().addOnDrawListener(windowListener2);
                    }
                }
            });
        }
        this.mListeners.add(windowListener);
        view.getContext().registerComponentCallbacks(windowListener);
        return new SafeCloseable() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda6
            public final void close() {
                ViewCapture viewCapture = ViewCapture.this;
                ViewCapture.WindowListener windowListener2 = windowListener;
                viewCapture.getClass();
                View view2 = windowListener2.mRoot;
                if (view2 != null && view2.getContext() != null) {
                    windowListener2.mRoot.getContext().unregisterComponentCallbacks(windowListener2);
                }
                viewCapture.mListeners.remove(windowListener2);
                windowListener2.mIsActive = false;
                View view3 = windowListener2.mRoot;
                if (view3 != null) {
                    view3.getViewTreeObserver().removeOnDrawListener(windowListener2);
                }
            }
        };
    }

    public void stopCapture(View view) {
        ((ArrayList) this.mListeners).forEach(new ViewCapture$$ExternalSyntheticLambda4(0, view));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WindowListener implements ViewTreeObserver.OnDrawListener, ComponentCallbacks2 {
        public final ViewCapture$$ExternalSyntheticLambda4 mCaptureCallback;
        public long[] mFrameTimesNanosBg;
        public boolean mIsActive;
        public ViewPropertyRef[] mNodesBg;
        public View mRoot;
        public final String name;
        public final ViewPropertyRef mViewPropertyRef = new ViewPropertyRef();
        public int mFrameIndexBg = -1;
        public boolean mIsFirstFrame = true;

        public WindowListener(View view, String str) {
            int i = ViewCapture.this.mMemorySize;
            this.mFrameTimesNanosBg = new long[i];
            this.mNodesBg = new ViewPropertyRef[i];
            this.mIsActive = true;
            this.mCaptureCallback = new ViewCapture$$ExternalSyntheticLambda4(1, this);
            this.mRoot = view;
            this.name = str;
        }

        public final ViewPropertyRef captureViewTree(View view, ViewPropertyRef viewPropertyRef) {
            ViewCapture viewCapture = ViewCapture.this;
            ViewPropertyRef viewPropertyRef2 = viewCapture.mPool;
            if (viewPropertyRef2 != null) {
                viewCapture.mPool = viewPropertyRef2.next;
                viewPropertyRef2.next = null;
            } else {
                viewPropertyRef2 = new ViewPropertyRef();
            }
            viewPropertyRef.next = viewPropertyRef2;
            if (!(view instanceof ViewGroup)) {
                viewPropertyRef2.childCount = 0;
                viewPropertyRef2.transferFrom(view);
                return viewPropertyRef2;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if ((view.mPrivateFlags & (-2145386496)) == 0 && !this.mIsFirstFrame) {
                viewPropertyRef2.childCount = -1;
                viewPropertyRef2.view = view;
                return viewPropertyRef2;
            }
            int childCount = viewGroup.getChildCount();
            viewPropertyRef2.childCount = childCount;
            viewPropertyRef2.transferFrom(view);
            for (int i = 0; i < childCount; i++) {
                viewPropertyRef2 = captureViewTree(viewGroup.getChildAt(i), viewPropertyRef2);
            }
            return viewPropertyRef2;
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            Trace.beginSection("vc#onDraw");
            captureViewTree(this.mRoot, this.mViewPropertyRef);
            ViewPropertyRef viewPropertyRef = this.mViewPropertyRef.next;
            if (viewPropertyRef != null) {
                viewPropertyRef.callback = this.mCaptureCallback;
                viewPropertyRef.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                ViewCapture.this.mBgExecutor.execute(viewPropertyRef);
            }
            this.mIsFirstFrame = false;
            Trace.endSection();
        }

        @Override // android.content.ComponentCallbacks
        public final void onLowMemory() {
            onTrimMemory(40);
        }

        @Override // android.content.ComponentCallbacks2
        public final void onTrimMemory(int i) {
            if (i >= 40) {
                this.mNodesBg = new ViewPropertyRef[0];
                this.mFrameTimesNanosBg = new long[0];
                View view = this.mRoot;
                if (view != null && view.getContext() != null) {
                    this.mRoot.getContext().unregisterComponentCallbacks(this);
                }
                this.mIsActive = false;
                View view2 = this.mRoot;
                if (view2 != null) {
                    view2.getViewTreeObserver().removeOnDrawListener(this);
                }
                this.mRoot = null;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.app.viewcapture.ViewCapture$WindowListener$1, reason: invalid class name */
        public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
            public AnonymousClass1() {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                View view2;
                WindowListener windowListener = WindowListener.this;
                if (windowListener.mIsActive && (view2 = windowListener.mRoot) != null) {
                    view2.getViewTreeObserver().removeOnDrawListener(windowListener);
                    windowListener.mRoot.getViewTreeObserver().addOnDrawListener(windowListener);
                }
                WindowListener.this.mRoot.removeOnAttachStateChangeListener(this);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
            }
        }

        @Override // android.content.ComponentCallbacks
        public final void onConfigurationChanged(Configuration configuration) {
        }
    }
}
