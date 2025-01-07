package com.android.wm.shell.sysui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.systemui.input.TouchContextService;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShellInterface {
    default boolean handleCommand(PrintWriter printWriter, String[] strArr) {
        return false;
    }

    default void createExternalInterfaces(Bundle bundle) {
    }

    default void dump(PrintWriter printWriter) {
    }

    default void onConfigurationChanged(Configuration configuration) {
    }

    default void onInit() {
    }

    default void onKeyguardDismissAnimationFinished() {
    }

    default void onUserProfilesChanged(List list) {
    }

    default void removeDisplayImeChangeListener(TouchContextService.AnonymousClass4 anonymousClass4) {
    }

    default void addDisplayImeChangeListener(TouchContextService.AnonymousClass4 anonymousClass4, Executor executor) {
    }

    default void onUserChanged(int i, Context context) {
    }

    default void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
    }
}
