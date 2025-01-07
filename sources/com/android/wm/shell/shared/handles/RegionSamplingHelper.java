package com.android.wm.shell.shared.handles;

import android.graphics.Rect;
import android.os.Handler;
import android.view.CompositionSamplingListener;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class RegionSamplingHelper implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
    public final Executor mBackgroundExecutor;
    public final SamplingCallback mCallback;
    public final SysuiCompositionSamplingListener mCompositionSamplingListener;
    public float mCurrentMedianLuma;
    public boolean mFirstSamplingAfterStart;
    public final Handler mHandler;
    public boolean mIsDestroyed;
    public float mLastMedianLuma;
    public final Rect mRegisteredSamplingBounds;
    public SurfaceControl mRegisteredStopLayer;
    public final AnonymousClass2 mRemoveDrawRunnable;
    public final View mSampledView;
    public boolean mSamplingEnabled;
    public final AnonymousClass3 mSamplingListener;
    public boolean mSamplingListenerRegistered;
    public final Rect mSamplingRequestBounds;
    public final AnonymousClass1 mUpdateOnDraw;
    public boolean mWaitingOnDraw;
    public boolean mWindowHasBlurs;
    public boolean mWindowVisible;
    public SurfaceControl mWrappedStopLayer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SamplingCallback {
        Rect getSampledRegion();

        boolean isSamplingEnabled();

        void onRegionDarknessChanged(boolean z);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class SysuiCompositionSamplingListener {
    }

    public RegionSamplingHelper(View view, SamplingCallback samplingCallback, Executor executor, Executor executor2) {
        this(view, samplingCallback, executor, executor2, new SysuiCompositionSamplingListener());
    }

    public SamplingCallback getCallback() {
        return this.mCallback;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Rect sampledRegion = this.mCallback.getSampledRegion();
        if (this.mSamplingRequestBounds.equals(sampledRegion)) {
            return;
        }
        this.mSamplingRequestBounds.set(sampledRegion);
        updateSamplingListener();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        updateSamplingListener();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        stopAndDestroy();
    }

    public final void start(Rect rect) {
        if (this.mCallback.isSamplingEnabled()) {
            if (rect != null) {
                this.mSamplingRequestBounds.set(rect);
            }
            this.mSamplingEnabled = true;
            this.mLastMedianLuma = -1.0f;
            this.mFirstSamplingAfterStart = true;
            updateSamplingListener();
        }
    }

    public final void stop() {
        this.mSamplingEnabled = false;
        updateSamplingListener();
    }

    public final void stopAndDestroy() {
        stop();
        Executor executor = this.mBackgroundExecutor;
        final AnonymousClass3 anonymousClass3 = this.mSamplingListener;
        Objects.requireNonNull(anonymousClass3);
        executor.execute(new Runnable() { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                destroy();
            }
        });
        this.mIsDestroyed = true;
    }

    public final void unregisterSamplingListener() {
        if (this.mSamplingListenerRegistered) {
            this.mSamplingListenerRegistered = false;
            final SurfaceControl surfaceControl = this.mWrappedStopLayer;
            this.mRegisteredStopLayer = null;
            this.mWrappedStopLayer = null;
            this.mRegisteredSamplingBounds.setEmpty();
            this.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    RegionSamplingHelper regionSamplingHelper = RegionSamplingHelper.this;
                    SurfaceControl surfaceControl2 = surfaceControl;
                    RegionSamplingHelper.SysuiCompositionSamplingListener sysuiCompositionSamplingListener = regionSamplingHelper.mCompositionSamplingListener;
                    RegionSamplingHelper.AnonymousClass3 anonymousClass3 = regionSamplingHelper.mSamplingListener;
                    sysuiCompositionSamplingListener.getClass();
                    CompositionSamplingListener.unregister(anonymousClass3);
                    if (surfaceControl2 == null || !surfaceControl2.isValid()) {
                        return;
                    }
                    surfaceControl2.release();
                }
            });
        }
    }

    public final void updateSamplingListener() {
        if (!this.mSamplingEnabled || this.mSamplingRequestBounds.isEmpty() || !this.mWindowVisible || this.mWindowHasBlurs || (!this.mSampledView.isAttachedToWindow() && !this.mFirstSamplingAfterStart)) {
            unregisterSamplingListener();
            return;
        }
        ViewRootImpl viewRootImpl = this.mSampledView.getViewRootImpl();
        SurfaceControl surfaceControl = null;
        SurfaceControl surfaceControl2 = viewRootImpl != null ? viewRootImpl.getSurfaceControl() : null;
        if (surfaceControl2 != null && surfaceControl2.isValid()) {
            surfaceControl = surfaceControl2;
        } else if (!this.mWaitingOnDraw) {
            this.mWaitingOnDraw = true;
            if (this.mHandler.hasCallbacks(this.mRemoveDrawRunnable)) {
                this.mHandler.removeCallbacks(this.mRemoveDrawRunnable);
            } else {
                this.mSampledView.getViewTreeObserver().addOnDrawListener(this.mUpdateOnDraw);
            }
        }
        if (!this.mSamplingRequestBounds.equals(this.mRegisteredSamplingBounds) || this.mRegisteredStopLayer != surfaceControl) {
            unregisterSamplingListener();
            this.mSamplingListenerRegistered = true;
            final SurfaceControl wrap = wrap(surfaceControl);
            final Rect rect = new Rect(this.mSamplingRequestBounds);
            this.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RegionSamplingHelper regionSamplingHelper = RegionSamplingHelper.this;
                    SurfaceControl surfaceControl3 = wrap;
                    Rect rect2 = rect;
                    if (surfaceControl3 != null) {
                        regionSamplingHelper.getClass();
                        if (!surfaceControl3.isValid()) {
                            return;
                        }
                    }
                    RegionSamplingHelper.SysuiCompositionSamplingListener sysuiCompositionSamplingListener = regionSamplingHelper.mCompositionSamplingListener;
                    RegionSamplingHelper.AnonymousClass3 anonymousClass3 = regionSamplingHelper.mSamplingListener;
                    sysuiCompositionSamplingListener.getClass();
                    CompositionSamplingListener.register(anonymousClass3, 0, surfaceControl3, rect2);
                }
            });
            this.mRegisteredSamplingBounds.set(this.mSamplingRequestBounds);
            this.mRegisteredStopLayer = surfaceControl;
            this.mWrappedStopLayer = wrap;
        }
        this.mFirstSamplingAfterStart = false;
    }

    public SurfaceControl wrap(SurfaceControl surfaceControl) {
        if (surfaceControl == null) {
            return null;
        }
        return new SurfaceControl(surfaceControl, "regionSampling");
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.wm.shell.shared.handles.RegionSamplingHelper$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.android.wm.shell.shared.handles.RegionSamplingHelper$2] */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.android.wm.shell.shared.handles.RegionSamplingHelper$3] */
    public RegionSamplingHelper(View view, SamplingCallback samplingCallback, Executor executor, Executor executor2, SysuiCompositionSamplingListener sysuiCompositionSamplingListener) {
        this.mHandler = new Handler();
        this.mSamplingRequestBounds = new Rect();
        this.mRegisteredSamplingBounds = new Rect();
        this.mSamplingEnabled = false;
        this.mSamplingListenerRegistered = false;
        this.mRegisteredStopLayer = null;
        this.mWrappedStopLayer = null;
        this.mUpdateOnDraw = new ViewTreeObserver.OnDrawListener() { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper.1
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public final void onDraw() {
                RegionSamplingHelper regionSamplingHelper = RegionSamplingHelper.this;
                regionSamplingHelper.mHandler.post(regionSamplingHelper.mRemoveDrawRunnable);
                RegionSamplingHelper regionSamplingHelper2 = RegionSamplingHelper.this;
                if (regionSamplingHelper2.mWaitingOnDraw) {
                    regionSamplingHelper2.mWaitingOnDraw = false;
                    regionSamplingHelper2.updateSamplingListener();
                }
            }
        };
        this.mRemoveDrawRunnable = new Runnable() { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper.2
            @Override // java.lang.Runnable
            public final void run() {
                RegionSamplingHelper.this.mSampledView.getViewTreeObserver().removeOnDrawListener(RegionSamplingHelper.this.mUpdateOnDraw);
            }
        };
        this.mBackgroundExecutor = executor2;
        this.mCompositionSamplingListener = sysuiCompositionSamplingListener;
        this.mSamplingListener = new CompositionSamplingListener(executor) { // from class: com.android.wm.shell.shared.handles.RegionSamplingHelper.3
            public final void onSampleCollected(float f) {
                RegionSamplingHelper regionSamplingHelper = RegionSamplingHelper.this;
                if (regionSamplingHelper.mSamplingEnabled) {
                    regionSamplingHelper.mCurrentMedianLuma = f;
                    if (Math.abs(f - regionSamplingHelper.mLastMedianLuma) > 0.05f) {
                        regionSamplingHelper.mCallback.onRegionDarknessChanged(f < 0.5f);
                        regionSamplingHelper.mLastMedianLuma = f;
                    }
                }
            }
        };
        this.mSampledView = view;
        view.addOnAttachStateChangeListener(this);
        view.addOnLayoutChangeListener(this);
        this.mCallback = samplingCallback;
    }
}
