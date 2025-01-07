package com.google.android.systemui.columbus.fetchers;

import android.content.Context;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BroadcastFetcher {
    public final BroadcastDispatcher broadcastDispatcher;
    public final Map cachedFlows = new LinkedHashMap();
    public final Context context;
    public final CoroutineScope coroutineScope;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BroadcastKey {
        public final IntentFilter intentFilter;
        public final boolean usingDispatcher;

        public BroadcastKey(IntentFilter intentFilter, boolean z) {
            this.intentFilter = intentFilter;
            this.usingDispatcher = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BroadcastKey)) {
                return false;
            }
            BroadcastKey broadcastKey = (BroadcastKey) obj;
            return this.intentFilter.equals(broadcastKey.intentFilter) && this.usingDispatcher == broadcastKey.usingDispatcher && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null);
        }

        public final int hashCode() {
            return TransitionData$$ExternalSyntheticOutline0.m(this.intentFilter.hashCode() * 31, 29791, this.usingDispatcher);
        }

        public final String toString() {
            IntentFilter intentFilter = this.intentFilter;
            StringBuilder sb = new StringBuilder("BroadcastKey(intentFilter=");
            sb.append(intentFilter);
            sb.append(", usingDispatcher=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.usingDispatcher, ", userHandle=null, permission=null, flags=null)");
        }
    }

    public BroadcastFetcher(CoroutineScope coroutineScope, Context context, BroadcastDispatcher broadcastDispatcher) {
        this.coroutineScope = coroutineScope;
        this.context = context;
        this.broadcastDispatcher = broadcastDispatcher;
    }
}
