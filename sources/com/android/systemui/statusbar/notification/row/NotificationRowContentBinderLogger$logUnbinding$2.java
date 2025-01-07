package com.android.systemui.statusbar.notification.row;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationRowContentBinderLogger$logUnbinding$2 extends Lambda implements Function1 {
    public static final NotificationRowContentBinderLogger$logUnbinding$2 INSTANCE = new NotificationRowContentBinderLogger$logUnbinding$2();

    public NotificationRowContentBinderLogger$logUnbinding$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("unbinding views ", NotificationRowContentBinderLogger.Companion.flagToString(logMessage.getInt1()), " for ", logMessage.getStr1());
    }
}
