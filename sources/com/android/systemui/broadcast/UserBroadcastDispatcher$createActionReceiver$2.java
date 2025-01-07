package com.android.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.os.Trace;
import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UserBroadcastDispatcher$createActionReceiver$2 extends Lambda implements Function1 {
    final /* synthetic */ String $action;
    final /* synthetic */ UserBroadcastDispatcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserBroadcastDispatcher$createActionReceiver$2(String str, UserBroadcastDispatcher userBroadcastDispatcher) {
        super(1);
        this.$action = str;
        this.this$0 = userBroadcastDispatcher;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) obj;
        try {
            if (Trace.isEnabled()) {
                Trace.traceBegin(4096L, "unregisterReceiver act=" + this.$action + " user=" + this.this$0.userId);
            }
            this.this$0.context.unregisterReceiver(broadcastReceiver);
            Trace.endSection();
            UserBroadcastDispatcher userBroadcastDispatcher = this.this$0;
            userBroadcastDispatcher.logger.logContextReceiverUnregistered(userBroadcastDispatcher.userId, this.$action);
        } catch (IllegalArgumentException e) {
            Log.e("UserBroadcastDispatcher", "Trying to unregister unregistered receiver for user " + this.this$0.userId + ", action " + this.$action, new IllegalStateException(e));
        }
        return Unit.INSTANCE;
    }
}
