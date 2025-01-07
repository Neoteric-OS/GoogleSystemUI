package com.android.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.policy.InflatedSmartReplyViewHolder;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder = (InflatedSmartReplyViewHolder) obj;
        NotificationContentView notificationContentView = (NotificationContentView) this.receiver;
        notificationContentView.mExpandedInflatedSmartReplies = inflatedSmartReplyViewHolder;
        if (inflatedSmartReplyViewHolder == null) {
            notificationContentView.mExpandedSmartReplyView = null;
        }
        return Unit.INSTANCE;
    }
}
