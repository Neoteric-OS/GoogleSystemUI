package com.android.systemui.statusbar.notification.row;

import android.os.Build;
import android.util.Log;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RichOngoingNotificationViewInflaterImpl {
    public RichOngoingNotificationViewInflaterImpl(DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory daggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory) {
    }

    public final void canKeepView() {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects android.app.api_rich_ongoing to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects android.app.api_rich_ongoing to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects android.app.api_rich_ongoing to be enabled.");
        }
    }

    public final void inflateView() {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects android.app.api_rich_ongoing to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects android.app.api_rich_ongoing to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects android.app.api_rich_ongoing to be enabled.");
        }
    }
}
