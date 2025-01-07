package com.android.wm.shell.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.EventLog;
import android.util.SparseArray;
import android.view.IWindowManager;
import android.view.InsetsSource;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.ImeTracker;
import android.view.inputmethod.InputMethodManagerGlobal;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.sysui.ShellInit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayImeController implements DisplayController.OnDisplaysChangedListener {
    public static final Interpolator INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    public final DisplayController mDisplayController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final SparseArray mImePerDisplay = new SparseArray();
    public final ArrayList mPositionProcessors = new ArrayList();
    public final TransactionPool mTransactionPool;
    public final IWindowManager mWmService;

    public DisplayImeController(IWindowManager iWindowManager, ShellInit shellInit, DisplayController displayController, DisplayInsetsController displayInsetsController, TransactionPool transactionPool) {
        this.mWmService = iWindowManager;
        this.mDisplayController = displayController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mTransactionPool = transactionPool;
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.common.DisplayImeController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DisplayImeController displayImeController = DisplayImeController.this;
                displayImeController.mDisplayController.addDisplayWindowListener(displayImeController);
            }
        }, this);
    }

    public final void addPositionProcessor(ImePositionProcessor imePositionProcessor) {
        synchronized (this.mPositionProcessors) {
            try {
                if (this.mPositionProcessors.contains(imePositionProcessor)) {
                    return;
                }
                this.mPositionProcessors.add(imePositionProcessor);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        PerDisplay perDisplay = new PerDisplay(i, this.mDisplayController.getDisplayLayout(i).mRotation);
        this.mDisplayInsetsController.addInsetsChangedListener(perDisplay.mDisplayId, perDisplay);
        this.mImePerDisplay.put(i, perDisplay);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
        PerDisplay perDisplay;
        InsetsSource peekSource;
        PerDisplay perDisplay2 = (PerDisplay) this.mImePerDisplay.get(i);
        if (perDisplay2 == null || this.mDisplayController.getDisplayLayout(i).mRotation == perDisplay2.mRotation || (perDisplay = (PerDisplay) this.mImePerDisplay.get(i)) == null || (peekSource = perDisplay.mInsetsState.peekSource(InsetsSource.ID_IME)) == null || perDisplay.mImeSourceControl == null || !peekSource.isVisible()) {
            return;
        }
        perDisplay2.startAnimation(47, true, false);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayRemoved(int i) {
        PerDisplay perDisplay = (PerDisplay) this.mImePerDisplay.get(i);
        if (perDisplay == null) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) DisplayImeController.this.mDisplayInsetsController.mListeners.get(perDisplay.mDisplayId);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(perDisplay);
        }
        this.mImePerDisplay.remove(i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PerDisplay implements DisplayInsetsController.OnInsetsChangedListener {
        public boolean mAnimateAlpha;
        public ValueAnimator mAnimation;
        public int mAnimationDirection;
        public final int mDisplayId;
        public final Rect mImeFrame;
        public boolean mImeShowing;
        public InsetsSourceControl mImeSourceControl;
        public final InsetsState mInsetsState = new InsetsState();
        public int mRequestedVisibleTypes = WindowInsets.Type.defaultVisible();
        public final int mRotation;

        public PerDisplay(int i, int i2) {
            WindowInsets.Type.defaultVisible();
            WindowInsets.Type.ime();
            this.mImeSourceControl = null;
            this.mAnimationDirection = 0;
            this.mAnimation = null;
            this.mRotation = 0;
            this.mImeShowing = false;
            this.mImeFrame = new Rect();
            this.mAnimateAlpha = true;
            this.mDisplayId = i;
            this.mRotation = i2;
        }

        public InsetsSourceControl getImeSourceControl() {
            return this.mImeSourceControl;
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void hideInsets(int i, ImeTracker.Token token) {
            if ((i & WindowInsets.Type.ime()) == 0) {
                return;
            }
            startAnimation(false, false, token);
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsChanged(InsetsState insetsState) {
            if (this.mInsetsState.equals(insetsState)) {
                return;
            }
            int i = InsetsSource.ID_IME;
            updateImeVisibility(insetsState.isSourceOrDefaultVisible(i, WindowInsets.Type.ime()));
            InsetsSource peekSource = insetsState.peekSource(i);
            Rect frame = peekSource != null ? peekSource.getFrame() : null;
            boolean z = peekSource != null && peekSource.isVisible();
            InsetsSource peekSource2 = this.mInsetsState.peekSource(i);
            Rect frame2 = peekSource2 != null ? peekSource2.getFrame() : null;
            this.mInsetsState.set(insetsState, true);
            if (this.mImeShowing && !Objects.equals(frame2, frame) && z) {
                startAnimation(48, this.mImeShowing, true);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x00c9  */
        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void insetsControlChanged(android.view.InsetsState r9, android.view.InsetsSourceControl[] r10) {
            /*
                Method dump skipped, instructions count: 252
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.common.DisplayImeController.PerDisplay.insetsControlChanged(android.view.InsetsState, android.view.InsetsSourceControl[]):void");
        }

        public final void setVisibleDirectly(boolean z) {
            this.mInsetsState.setSourceVisible(InsetsSource.ID_IME, z);
            int ime = z ? this.mRequestedVisibleTypes | WindowInsets.Type.ime() : this.mRequestedVisibleTypes & (~WindowInsets.Type.ime());
            this.mRequestedVisibleTypes = ime;
            try {
                DisplayImeController.this.mWmService.updateDisplayWindowRequestedVisibleTypes(this.mDisplayId, ime);
            } catch (RemoteException unused) {
            }
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void showInsets(int i, ImeTracker.Token token) {
            if ((i & WindowInsets.Type.ime()) == 0) {
                return;
            }
            startAnimation(true, false, token);
        }

        public final void startAnimation(int i, boolean z, boolean z2) {
            if (this.mInsetsState.peekSource(InsetsSource.ID_IME) == null || this.mImeSourceControl == null) {
                return;
            }
            startAnimation(z, z2, ImeTracker.forLogging().onStart(z ? 1 : 2, 8, i, false));
        }

        public final void updateImeVisibility(boolean z) {
            if (this.mImeShowing != z) {
                this.mImeShowing = z;
                DisplayImeController displayImeController = DisplayImeController.this;
                int i = this.mDisplayId;
                synchronized (displayImeController.mPositionProcessors) {
                    try {
                        Iterator it = displayImeController.mPositionProcessors.iterator();
                        while (it.hasNext()) {
                            ((ImePositionProcessor) it.next()).onImeVisibilityChanged(i, z);
                        }
                    } finally {
                    }
                }
            }
        }

        public final void startAnimation(boolean z, boolean z2, ImeTracker.Token token) {
            boolean z3;
            InsetsSourceControl insetsSourceControl = this.mImeSourceControl;
            if (insetsSourceControl == null || insetsSourceControl.getLeash() == null) {
                return;
            }
            InsetsSource peekSource = this.mInsetsState.peekSource(InsetsSource.ID_IME);
            if (peekSource == null) {
                ImeTracker.forLogging().onFailed(token, 26);
                return;
            }
            Rect frame = peekSource.getFrame();
            Rect frame2 = peekSource.getFrame();
            int height = frame2.height();
            int i = this.mDisplayId;
            boolean z4 = (height == 0 || frame2.height() <= DisplayImeController.this.mDisplayController.getDisplayLayout(i).mNavBarFrameHeight) && z;
            if (z4) {
                this.mImeFrame.set(frame);
                this.mImeFrame.bottom -= (int) ((r5.mDisplayController.getDisplayLayout(i).mDensityDpi * 0.00625f) * (-80.0f));
            } else if (frame.height() != 0) {
                this.mImeFrame.set(frame);
            }
            if ((!z2 && this.mAnimationDirection == 1 && z) || (this.mAnimationDirection == 2 && !z)) {
                ImeTracker.forLogging().onCancelled(token, 26);
                return;
            }
            ValueAnimator valueAnimator = this.mAnimation;
            float f = 0.0f;
            if (valueAnimator != null) {
                if (valueAnimator.isRunning()) {
                    f = ((Float) this.mAnimation.getAnimatedValue()).floatValue();
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.mAnimation.cancel();
            } else {
                z3 = false;
            }
            InsetsSourceControl insetsSourceControl2 = new InsetsSourceControl(this.mImeSourceControl);
            final SurfaceControl leash = insetsSourceControl2.getLeash();
            final float f2 = insetsSourceControl2.getSurfacePosition().y;
            final float f3 = insetsSourceControl2.getSurfacePosition().x;
            final float height2 = f2 + this.mImeFrame.height();
            float f4 = z ? height2 : f2;
            float f5 = z ? f2 : height2;
            if (this.mAnimationDirection == 0 && this.mImeShowing && z) {
                z3 = true;
                f = f2;
            }
            this.mAnimationDirection = z ? 1 : 2;
            updateImeVisibility(z);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f4, f5);
            this.mAnimation = ofFloat;
            ofFloat.setDuration(z ? 275L : 340L);
            if (z3) {
                this.mAnimation.setCurrentFraction((f - f4) / (f5 - f4));
            }
            final boolean z5 = z4;
            this.mAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.common.DisplayImeController$PerDisplay$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    DisplayImeController.PerDisplay perDisplay = DisplayImeController.PerDisplay.this;
                    SurfaceControl surfaceControl = leash;
                    float f6 = f3;
                    boolean z6 = z5;
                    float f7 = height2;
                    float f8 = f2;
                    SurfaceControl.Transaction acquire = DisplayImeController.this.mTransactionPool.acquire();
                    float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                    acquire.setPosition(surfaceControl, f6, floatValue);
                    acquire.setAlpha(surfaceControl, (perDisplay.mAnimateAlpha || z6) ? (floatValue - f7) / (f8 - f7) : 1.0f);
                    DisplayImeController displayImeController = DisplayImeController.this;
                    int i2 = perDisplay.mDisplayId;
                    int i3 = perDisplay.mImeFrame.top + ((int) floatValue);
                    synchronized (displayImeController.mPositionProcessors) {
                        try {
                            Iterator it = displayImeController.mPositionProcessors.iterator();
                            while (it.hasNext()) {
                                ((DisplayImeController.ImePositionProcessor) it.next()).onImePositionChanged(i2, i3);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    acquire.apply();
                    DisplayImeController.this.mTransactionPool.release(acquire);
                }
            });
            this.mAnimation.setInterpolator(DisplayImeController.INTERPOLATOR);
            ImeTracker.forLogging().onProgress(token, 26);
            this.mAnimation.addListener(new AnimatorListenerAdapter(token, leash, f3, height2, f2, z4, f5, insetsSourceControl2) { // from class: com.android.wm.shell.common.DisplayImeController.PerDisplay.1
                public boolean mCancelled = false;
                public final ImeTracker.Token mStatsToken;
                public final /* synthetic */ InsetsSourceControl val$animatingControl;
                public final /* synthetic */ SurfaceControl val$animatingLeash;
                public final /* synthetic */ float val$endY;
                public final /* synthetic */ float val$hiddenY;
                public final /* synthetic */ boolean val$isFloating;
                public final /* synthetic */ float val$shownY;
                public final /* synthetic */ float val$x;

                {
                    this.val$animatingLeash = leash;
                    this.val$x = f3;
                    this.val$hiddenY = height2;
                    this.val$shownY = f2;
                    this.val$isFloating = z4;
                    this.val$endY = f5;
                    this.val$animatingControl = insetsSourceControl2;
                    this.mStatsToken = token;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.mCancelled = true;
                    if (ImeTracker.DEBUG_IME_VISIBILITY) {
                        ImeTracker.Token token2 = this.mStatsToken;
                        EventLog.writeEvent(32011, token2 != null ? token2.getTag() : "TOKEN_NONE", Integer.valueOf(PerDisplay.this.mDisplayId), Objects.toString(this.val$animatingControl.getInsetsHint()));
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    SurfaceControl.Transaction acquire = DisplayImeController.this.mTransactionPool.acquire();
                    if (!this.mCancelled) {
                        acquire.setPosition(this.val$animatingLeash, this.val$x, this.val$endY);
                        acquire.setAlpha(this.val$animatingLeash, 1.0f);
                    }
                    PerDisplay perDisplay = PerDisplay.this;
                    DisplayImeController displayImeController = DisplayImeController.this;
                    int i2 = perDisplay.mDisplayId;
                    boolean z6 = this.mCancelled;
                    synchronized (displayImeController.mPositionProcessors) {
                        try {
                            Iterator it = displayImeController.mPositionProcessors.iterator();
                            while (it.hasNext()) {
                                ((ImePositionProcessor) it.next()).onImeEndPositioning(i2, z6);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    int i3 = PerDisplay.this.mAnimationDirection;
                    if (i3 == 2 && !this.mCancelled) {
                        ImeTracker.forLogging().onProgress(this.mStatsToken, 27);
                        acquire.hide(this.val$animatingLeash);
                        PerDisplay perDisplay2 = PerDisplay.this;
                        DisplayImeController displayImeController2 = DisplayImeController.this;
                        int i4 = perDisplay2.mDisplayId;
                        displayImeController2.getClass();
                        InputMethodManagerGlobal.removeImeSurface(i4, new DisplayImeController$$ExternalSyntheticLambda1(0));
                        ImeTracker.forLogging().onHidden(this.mStatsToken);
                    } else if (i3 == 1 && !this.mCancelled) {
                        ImeTracker.forLogging().onShown(this.mStatsToken);
                    } else if (this.mCancelled) {
                        ImeTracker.forLogging().onCancelled(this.mStatsToken, 27);
                    }
                    if (ImeTracker.DEBUG_IME_VISIBILITY) {
                        ImeTracker.Token token2 = this.mStatsToken;
                        EventLog.writeEvent(32010, token2 != null ? token2.getTag() : "TOKEN_NONE", Integer.valueOf(PerDisplay.this.mDisplayId), Integer.valueOf(PerDisplay.this.mAnimationDirection), Float.valueOf(this.val$endY), Objects.toString(this.val$animatingLeash), Objects.toString(this.val$animatingControl.getInsetsHint()), Objects.toString(this.val$animatingControl.getSurfacePosition()), Objects.toString(PerDisplay.this.mImeFrame));
                    }
                    acquire.apply();
                    DisplayImeController.this.mTransactionPool.release(acquire);
                    PerDisplay perDisplay3 = PerDisplay.this;
                    perDisplay3.mAnimationDirection = 0;
                    perDisplay3.mAnimation = null;
                    this.val$animatingControl.release(new DisplayImeController$$ExternalSyntheticLambda1(1));
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    int i2;
                    float f6;
                    Float f7 = (Float) ((ValueAnimator) animator).getAnimatedValue();
                    float floatValue = f7.floatValue();
                    SurfaceControl.Transaction acquire = DisplayImeController.this.mTransactionPool.acquire();
                    acquire.setPosition(this.val$animatingLeash, this.val$x, floatValue);
                    PerDisplay perDisplay = PerDisplay.this;
                    DisplayImeController displayImeController = DisplayImeController.this;
                    int i3 = perDisplay.mDisplayId;
                    float f8 = this.val$hiddenY;
                    int i4 = perDisplay.mImeFrame.top;
                    int i5 = ((int) f8) + i4;
                    int i6 = i4 + ((int) this.val$shownY);
                    boolean z6 = perDisplay.mAnimationDirection == 1;
                    boolean z7 = this.val$isFloating;
                    synchronized (displayImeController.mPositionProcessors) {
                        try {
                            Iterator it = displayImeController.mPositionProcessors.iterator();
                            i2 = 0;
                            while (it.hasNext()) {
                                i2 |= ((ImePositionProcessor) it.next()).onImeStartPositioning(i3, i5, i6, z6, z7);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    PerDisplay perDisplay2 = PerDisplay.this;
                    boolean z8 = (i2 & 1) == 0;
                    perDisplay2.mAnimateAlpha = z8;
                    if (z8 || this.val$isFloating) {
                        float f9 = this.val$hiddenY;
                        f6 = (floatValue - f9) / (this.val$shownY - f9);
                    } else {
                        f6 = 1.0f;
                    }
                    acquire.setAlpha(this.val$animatingLeash, f6);
                    if (PerDisplay.this.mAnimationDirection == 1) {
                        ImeTracker.forLogging().onProgress(this.mStatsToken, 27);
                        acquire.show(this.val$animatingLeash);
                    }
                    if (ImeTracker.DEBUG_IME_VISIBILITY) {
                        ImeTracker.Token token2 = this.mStatsToken;
                        EventLog.writeEvent(32009, token2 != null ? token2.getTag() : "TOKEN_NONE", Integer.valueOf(PerDisplay.this.mDisplayId), Integer.valueOf(PerDisplay.this.mAnimationDirection), Float.valueOf(f6), f7, Float.valueOf(this.val$endY), Objects.toString(this.val$animatingLeash), Objects.toString(this.val$animatingControl.getInsetsHint()), Objects.toString(this.val$animatingControl.getSurfacePosition()), Objects.toString(PerDisplay.this.mImeFrame));
                    }
                    acquire.apply();
                    DisplayImeController.this.mTransactionPool.release(acquire);
                }
            });
            if (!z) {
                setVisibleDirectly(false);
            }
            this.mAnimation.start();
            if (z) {
                setVisibleDirectly(true);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ImePositionProcessor {
        default int onImeStartPositioning(int i, int i2, int i3, boolean z, boolean z2) {
            return 0;
        }

        default void onImeControlTargetChanged(int i, boolean z) {
        }

        default void onImeEndPositioning(int i, boolean z) {
        }

        default void onImePositionChanged(int i, int i2) {
        }

        default void onImeVisibilityChanged(int i, boolean z) {
        }
    }
}
