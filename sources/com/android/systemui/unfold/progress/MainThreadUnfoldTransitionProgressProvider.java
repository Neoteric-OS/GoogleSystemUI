package com.android.systemui.unfold.progress;

import android.os.Handler;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.progress.MainThreadUnfoldTransitionProgressProvider;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MainThreadUnfoldTransitionProgressProvider implements UnfoldTransitionProgressProvider {
    public final Map listenerMap = Collections.synchronizedMap(new LinkedHashMap());
    public final Handler mainHandler;
    public final UnfoldTransitionProgressProvider rootProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionProgressListerProxy implements UnfoldTransitionProgressProvider.TransitionProgressListener {
        public final UnfoldTransitionProgressProvider.TransitionProgressListener listener;

        public TransitionProgressListerProxy(UnfoldTransitionProgressProvider.TransitionProgressListener transitionProgressListener) {
            this.listener = transitionProgressListener;
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinished() {
            MainThreadUnfoldTransitionProgressProvider.this.mainHandler.post(new MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionStarted$1(this, 1));
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinishing() {
            MainThreadUnfoldTransitionProgressProvider.this.mainHandler.post(new MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionStarted$1(this, 2));
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionProgress(final float f) {
            MainThreadUnfoldTransitionProgressProvider.this.mainHandler.post(new Runnable() { // from class: com.android.systemui.unfold.progress.MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionProgress$1
                @Override // java.lang.Runnable
                public final void run() {
                    MainThreadUnfoldTransitionProgressProvider.TransitionProgressListerProxy.this.listener.onTransitionProgress(f);
                }
            });
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionStarted() {
            MainThreadUnfoldTransitionProgressProvider.this.mainHandler.post(new MainThreadUnfoldTransitionProgressProvider$TransitionProgressListerProxy$onTransitionStarted$1(this, 0));
        }
    }

    public MainThreadUnfoldTransitionProgressProvider(Handler handler, UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.mainHandler = handler;
        this.rootProvider = unfoldTransitionProgressProvider;
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        UnfoldTransitionProgressProvider.TransitionProgressListener transitionProgressListener = (UnfoldTransitionProgressProvider.TransitionProgressListener) obj;
        TransitionProgressListerProxy transitionProgressListerProxy = new TransitionProgressListerProxy(transitionProgressListener);
        this.rootProvider.addCallback(transitionProgressListerProxy);
        this.listenerMap.put(transitionProgressListener, transitionProgressListerProxy);
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        UnfoldTransitionProgressProvider.TransitionProgressListener transitionProgressListener = (UnfoldTransitionProgressProvider.TransitionProgressListener) this.listenerMap.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
        if (transitionProgressListener == null) {
            return;
        }
        this.rootProvider.removeCallback(transitionProgressListener);
    }
}
