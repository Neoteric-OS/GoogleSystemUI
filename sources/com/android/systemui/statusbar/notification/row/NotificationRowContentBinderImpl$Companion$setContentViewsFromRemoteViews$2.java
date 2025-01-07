package com.android.systemui.statusbar.notification.row;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$2 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((NotificationContentView) this.receiver).setExpandedChild((View) obj);
        return Unit.INSTANCE;
    }
}
