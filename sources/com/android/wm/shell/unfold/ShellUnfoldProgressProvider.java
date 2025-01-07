package com.android.wm.shell.unfold;

import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShellUnfoldProgressProvider {
    public static final AnonymousClass1 NO_PROVIDER = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.unfold.ShellUnfoldProgressProvider$1, reason: invalid class name */
    public final class AnonymousClass1 implements ShellUnfoldProgressProvider {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface UnfoldListener {
        void onStateChangeFinished();

        void onStateChangeProgress(float f);

        default void onFoldStateChanged(boolean z) {
        }

        default void onStateChangeStarted() {
        }
    }

    default void addListener(Executor executor, UnfoldListener unfoldListener) {
    }
}
