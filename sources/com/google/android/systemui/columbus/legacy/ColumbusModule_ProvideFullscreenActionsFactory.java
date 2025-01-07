package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.DismissTimer;
import com.google.android.systemui.columbus.legacy.actions.SettingsAction;
import com.google.android.systemui.columbus.legacy.actions.SilenceCall;
import com.google.android.systemui.columbus.legacy.actions.SnoozeAlarm;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ColumbusModule_ProvideFullscreenActionsFactory implements Provider {
    public static List provideFullscreenActions(DismissTimer dismissTimer, SnoozeAlarm snoozeAlarm, SilenceCall silenceCall, SettingsAction settingsAction) {
        List listOf = CollectionsKt__CollectionsKt.listOf(dismissTimer, snoozeAlarm, silenceCall, settingsAction);
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}
