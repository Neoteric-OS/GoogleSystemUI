package com.android.systemui.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Trace;
import android.os.UserHandle;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UserBroadcastDispatcher$createActionReceiver$1 extends Lambda implements Function2 {
    final /* synthetic */ String $action;
    final /* synthetic */ int $flags;
    final /* synthetic */ String $permission;
    final /* synthetic */ UserBroadcastDispatcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserBroadcastDispatcher$createActionReceiver$1(String str, UserBroadcastDispatcher userBroadcastDispatcher, String str2, int i) {
        super(2);
        this.$action = str;
        this.this$0 = userBroadcastDispatcher;
        this.$permission = str2;
        this.$flags = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) obj;
        IntentFilter intentFilter = (IntentFilter) obj2;
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096L, CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("registerReceiver act=", this.$action, " user=", this.this$0.userId));
        }
        UserBroadcastDispatcher userBroadcastDispatcher = this.this$0;
        userBroadcastDispatcher.context.registerReceiverAsUser(broadcastReceiver, UserHandle.of(userBroadcastDispatcher.userId), intentFilter, this.$permission, this.this$0.workerHandler, this.$flags);
        Trace.endSection();
        UserBroadcastDispatcher userBroadcastDispatcher2 = this.this$0;
        userBroadcastDispatcher2.logger.logContextReceiverRegistered(userBroadcastDispatcher2.userId, this.$flags, intentFilter);
        return Unit.INSTANCE;
    }
}
