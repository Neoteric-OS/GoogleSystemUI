package com.android.wm.shell.windowdecor.viewhost;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.window.InputTransferToken;
import androidx.tracing.Trace;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultWindowDecorViewHost {
    public final Context context;
    public StandaloneCoroutine currentUpdateJob;
    public final Display display;
    public final CoroutineScope mainScope;
    public final SurfaceControl rootSurface;
    public final Function4 surfaceControlViewHostFactory;
    public SurfaceControlViewHost viewHost;
    public WindowlessWindowManager wwm;

    public DefaultWindowDecorViewHost(Context context, CoroutineScope coroutineScope, Display display) {
        AnonymousClass1 anonymousClass1 = new Function4() { // from class: com.android.wm.shell.windowdecor.viewhost.DefaultWindowDecorViewHost.1
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return new SurfaceControlViewHost((Context) obj, (Display) obj2, (WindowlessWindowManager) obj3, (String) obj4);
            }
        };
        this.context = context;
        this.mainScope = coroutineScope;
        this.display = display;
        this.surfaceControlViewHostFactory = anonymousClass1;
        this.rootSurface = new SurfaceControl.Builder().setName("DefaultWindowDecorViewHost surface").setContainerLayer().setCallsite("DefaultWindowDecorViewHost#init").build();
    }

    public final SurfaceControlViewHost requireViewHost() {
        SurfaceControlViewHost surfaceControlViewHost = this.viewHost;
        if (surfaceControlViewHost != null) {
            return surfaceControlViewHost;
        }
        throw new IllegalStateException("Expected non-null view host");
    }

    public final void updateViewAsync(View view, WindowManager.LayoutParams layoutParams, Configuration configuration) {
        Trace.beginSection("DefaultWindowDecorViewHost#updateViewAsync");
        StandaloneCoroutine standaloneCoroutine = this.currentUpdateJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.currentUpdateJob = null;
        this.currentUpdateJob = BuildersKt.launch$default(this.mainScope, null, null, new DefaultWindowDecorViewHost$updateViewAsync$1(this, view, layoutParams, configuration, null), 3);
        android.os.Trace.endSection();
    }

    public final void updateViewHost(View view, WindowManager.LayoutParams layoutParams, Configuration configuration, SurfaceControl.Transaction transaction) {
        Trace.beginSection("DefaultWindowDecorViewHost#updateViewHost");
        if (this.wwm == null) {
            this.wwm = new WindowlessWindowManager(configuration, this.rootSurface, (InputTransferToken) null);
        }
        WindowlessWindowManager windowlessWindowManager = this.wwm;
        if (windowlessWindowManager == null) {
            throw new IllegalStateException("Expected non-null windowless window manager");
        }
        windowlessWindowManager.setConfiguration(configuration);
        if (this.viewHost == null) {
            Context context = this.context;
            Display display = this.display;
            WindowlessWindowManager windowlessWindowManager2 = this.wwm;
            if (windowlessWindowManager2 == null) {
                throw new IllegalStateException("Expected non-null windowless window manager");
            }
            this.viewHost = (SurfaceControlViewHost) this.surfaceControlViewHostFactory.invoke(context, display, windowlessWindowManager2, "DefaultWindowDecorViewHost#updateViewHost");
        }
        if (transaction != null) {
            requireViewHost().getRootSurfaceControl().applyTransactionOnDraw(transaction);
        }
        if (requireViewHost().getView() == null) {
            Trace.beginSection("DefaultWindowDecorViewHost#updateViewHost-setView");
            requireViewHost().setView(view, layoutParams);
            android.os.Trace.endSection();
        } else {
            if (!Intrinsics.areEqual(requireViewHost().getView(), view)) {
                throw new IllegalStateException("Changing view is not allowed");
            }
            Trace.beginSection("DefaultWindowDecorViewHost#updateViewHost-relayout");
            requireViewHost().relayout(layoutParams);
            android.os.Trace.endSection();
        }
        android.os.Trace.endSection();
    }

    public static /* synthetic */ void getViewHost$annotations() {
    }
}
