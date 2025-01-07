package com.android.systemui.unfold.util;

import android.os.Handler;
import android.os.Looper;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScopedUnfoldTransitionProgressProvider implements UnfoldTransitionProgressProvider, UnfoldTransitionProgressProvider.TransitionProgressListener {
    public boolean isReadyToHandleTransition;
    public boolean isTransitionRunning;
    public Handler progressHandler;
    public final UnfoldTransitionProgressProvider source;
    public final CopyOnWriteArrayList listeners = new CopyOnWriteArrayList();
    public final Object lock = new Object();
    public float lastTransitionProgress = -1.0f;

    public ScopedUnfoldTransitionProgressProvider(UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2 = this.source;
        if (unfoldTransitionProgressProvider2 != null) {
            unfoldTransitionProgressProvider2.removeCallback(this);
        }
        if (unfoldTransitionProgressProvider == null) {
            this.source = null;
        } else {
            this.source = unfoldTransitionProgressProvider;
            unfoldTransitionProgressProvider.addCallback(this);
        }
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.listeners.add((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    public final void assertInProgressThread$1() {
        Handler handler = this.progressHandler;
        if (handler == null) {
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                throw new IllegalStateException("This thread is expected to have a looper.");
            }
            this.progressHandler = new Handler(myLooper);
            return;
        }
        if (handler.getLooper().isCurrentThread()) {
            return;
        }
        throw new IllegalStateException(StringsKt__IndentKt.trimMargin$default("Receiving unfold transition callback from different threads.\n                    |Current: " + Thread.currentThread() + "\n                    |expected: " + handler.getLooper().getThread()).toString());
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinished() {
        assertInProgressThread$1();
        synchronized (this.lock) {
            try {
                if (this.isReadyToHandleTransition) {
                    Iterator it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionFinished();
                    }
                }
                this.isTransitionRunning = false;
                this.lastTransitionProgress = -1.0f;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionFinishing() {
        assertInProgressThread$1();
        synchronized (this.lock) {
            if (this.isReadyToHandleTransition) {
                Iterator it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionFinishing();
                }
            }
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionProgress(float f) {
        assertInProgressThread$1();
        synchronized (this.lock) {
            try {
                if (this.isReadyToHandleTransition) {
                    Iterator it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionProgress(f);
                    }
                }
                this.lastTransitionProgress = f;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        assertInProgressThread$1();
        synchronized (this.lock) {
            this.isTransitionRunning = true;
            if (this.isReadyToHandleTransition) {
                Iterator it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionStarted();
                }
            }
        }
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.listeners.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    public final void setReadyToHandleTransition(boolean z) {
        synchronized (this.lock) {
            this.isReadyToHandleTransition = z;
            Handler handler = this.progressHandler;
            if (handler != null) {
                final Function0 function0 = new Function0() { // from class: com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider$setReadyToHandleTransition$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider = ScopedUnfoldTransitionProgressProvider.this;
                        scopedUnfoldTransitionProgressProvider.assertInProgressThread$1();
                        synchronized (scopedUnfoldTransitionProgressProvider.lock) {
                            try {
                                if (scopedUnfoldTransitionProgressProvider.isTransitionRunning) {
                                    if (scopedUnfoldTransitionProgressProvider.isReadyToHandleTransition) {
                                        Iterator it = scopedUnfoldTransitionProgressProvider.listeners.iterator();
                                        while (it.hasNext()) {
                                            ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionStarted();
                                        }
                                        if (scopedUnfoldTransitionProgressProvider.lastTransitionProgress != -1.0f) {
                                            Iterator it2 = scopedUnfoldTransitionProgressProvider.listeners.iterator();
                                            while (it2.hasNext()) {
                                                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it2.next()).onTransitionProgress(scopedUnfoldTransitionProgressProvider.lastTransitionProgress);
                                            }
                                        }
                                    } else {
                                        scopedUnfoldTransitionProgressProvider.isTransitionRunning = false;
                                        Iterator it3 = scopedUnfoldTransitionProgressProvider.listeners.iterator();
                                        while (it3.hasNext()) {
                                            ((UnfoldTransitionProgressProvider.TransitionProgressListener) it3.next()).onTransitionFinished();
                                        }
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (handler.getLooper().isCurrentThread()) {
                    function0.invoke();
                } else {
                    handler.post(new Runnable() { // from class: com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider$sam$java_lang_Runnable$0
                        @Override // java.lang.Runnable
                        public final /* synthetic */ void run() {
                            Function0.this.invoke();
                        }
                    });
                }
            }
        }
    }
}
