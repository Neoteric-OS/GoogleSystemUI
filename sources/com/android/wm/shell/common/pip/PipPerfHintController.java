package com.android.wm.shell.common.pip;

import android.window.SystemPerformanceHinter;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipPerfHintController {
    public final ShellExecutor mMainExecutor;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public final SystemPerformanceHinter mSystemPerformanceHinter;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipHighPerfSession implements AutoCloseable {
        public static final Map sActiveSessions = new WeakHashMap();
        public final String mReason;
        public final SystemPerformanceHinter.HighPerfSession mSession;

        public PipHighPerfSession(SystemPerformanceHinter.HighPerfSession highPerfSession, String str) {
            this.mSession = highPerfSession;
            this.mReason = str;
            sActiveSessions.put(this, Boolean.TRUE);
        }

        @Override // java.lang.AutoCloseable
        public final void close() {
            sActiveSessions.remove(this);
            this.mSession.close();
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 7222560070951514468L, 0, "PipPerfHintController", String.valueOf(toString()));
            }
        }

        public final void finalize() {
            this.mSession.close();
        }

        public final String toString() {
            return "[" + super.toString() + "] initially started due to: " + this.mReason;
        }
    }

    public PipPerfHintController(PipDisplayLayoutState pipDisplayLayoutState, ShellExecutor shellExecutor, SystemPerformanceHinter systemPerformanceHinter) {
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mMainExecutor = shellExecutor;
        this.mSystemPerformanceHinter = systemPerformanceHinter;
    }

    public final PipHighPerfSession startSession(final Consumer consumer, String str) {
        if (PipHighPerfSession.sActiveSessions.size() == 20) {
            return null;
        }
        final PipHighPerfSession pipHighPerfSession = new PipHighPerfSession(this.mSystemPerformanceHinter.startSession(3, this.mPipDisplayLayoutState.mDisplayId, "pip-high-perf-session"), str);
        ((HandlerExecutor) this.mMainExecutor).executeDelayed(new Runnable() { // from class: com.android.wm.shell.common.pip.PipPerfHintController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PipPerfHintController.PipHighPerfSession pipHighPerfSession2 = PipPerfHintController.PipHighPerfSession.this;
                Consumer consumer2 = consumer;
                if (PipPerfHintController.PipHighPerfSession.sActiveSessions.containsKey(pipHighPerfSession2)) {
                    pipHighPerfSession2.close();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3814674303351573385L, 0, "PipPerfHintController", String.valueOf(pipHighPerfSession2.toString()));
                    }
                    consumer2.accept(pipHighPerfSession2);
                }
            }
        }, 20000L);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 2113210064412554747L, 0, "PipPerfHintController", String.valueOf(pipHighPerfSession.toString()));
        }
        return pipHighPerfSession;
    }
}
