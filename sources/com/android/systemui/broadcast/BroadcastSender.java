package com.android.systemui.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.util.wakelock.WakeLock;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BroadcastSender {
    public final Executor bgExecutor;
    public final Context context;
    public final WakeLock.Builder wakeLockBuilder;

    public BroadcastSender(Context context, WakeLock.Builder builder, Executor executor) {
        this.context = context;
        this.wakeLockBuilder = builder;
        this.bgExecutor = executor;
    }

    public final void closeSystemDialogs() {
        sendInBackground("closeSystemDialogs", new Function0() { // from class: com.android.systemui.broadcast.BroadcastSender$closeSystemDialogs$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BroadcastSender.this.context.closeSystemDialogs();
                return Unit.INSTANCE;
            }
        });
    }

    public final void sendBroadcast(final Intent intent) {
        sendInBackground(String.valueOf(intent), new Function0() { // from class: com.android.systemui.broadcast.BroadcastSender$sendBroadcast$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BroadcastSender.this.context.sendBroadcast(intent);
                return Unit.INSTANCE;
            }
        });
    }

    public final void sendBroadcastAsUser(final Intent intent, final UserHandle userHandle) {
        sendInBackground(String.valueOf(intent), new Function0() { // from class: com.android.systemui.broadcast.BroadcastSender$sendBroadcastAsUser$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BroadcastSender.this.context.sendBroadcastAsUser(intent, userHandle);
                return Unit.INSTANCE;
            }
        });
    }

    public final void sendInBackground(final String str, final Function0 function0) {
        WakeLock.Builder builder = this.wakeLockBuilder;
        builder.mTag = "SysUI:BroadcastSender";
        builder.mMaxTimeout = 5000L;
        final WakeLock build = builder.build();
        build.acquire(str);
        this.bgExecutor.execute(new Runnable(function0, build, str) { // from class: com.android.systemui.broadcast.BroadcastSender$sendInBackground$1
            public final /* synthetic */ WakeLock $broadcastWakelock;
            public final /* synthetic */ Lambda $callable;
            public final /* synthetic */ String $reason;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$callable = (Lambda) function0;
                this.$broadcastWakelock = build;
                this.$reason = str;
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    this.$callable.invoke();
                } finally {
                    this.$broadcastWakelock.release(this.$reason);
                }
            }
        });
    }

    public final void sendBroadcast(final Intent intent, final String str) {
        sendInBackground(String.valueOf(intent), new Function0() { // from class: com.android.systemui.broadcast.BroadcastSender$sendBroadcast$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BroadcastSender.this.context.sendBroadcast(intent, str);
                return Unit.INSTANCE;
            }
        });
    }
}
