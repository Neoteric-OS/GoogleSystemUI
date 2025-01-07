package com.google.android.systemui.assist;

import android.content.Context;
import com.android.internal.app.AssistUtils;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.PhoneStateMonitor;
import com.android.systemui.settings.UserTracker;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GoogleAssistLogger extends AssistLogger {
    public final AssistantPresenceHandler assistantPresenceHandler;

    public GoogleAssistLogger(Context context, UiEventLogger uiEventLogger, AssistUtils assistUtils, PhoneStateMonitor phoneStateMonitor, UserTracker userTracker, AssistantPresenceHandler assistantPresenceHandler) {
        super(context, uiEventLogger, assistUtils, phoneStateMonitor, userTracker);
        this.assistantPresenceHandler = assistantPresenceHandler;
    }
}
