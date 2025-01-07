package com.android.systemui.statusbar.notification.collection.render;

import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NodeSpecBuilderLogger {
    public NodeSpecBuilderLogger(final NotifPipelineFlags notifPipelineFlags) {
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.collection.render.NodeSpecBuilderLogger$devLoggingEnabled$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NotifPipelineFlags notifPipelineFlags2 = NotifPipelineFlags.this;
                notifPipelineFlags2.getClass();
                UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                notifPipelineFlags2.featureFlags.getClass();
                return Boolean.FALSE;
            }
        });
    }
}
