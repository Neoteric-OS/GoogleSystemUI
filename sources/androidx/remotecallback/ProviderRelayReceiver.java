package androidx.remotecallback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ProviderRelayReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("androidx.remotecallback.action.PROVIDER_RELAY".equals(intent.getAction())) {
            context.getContentResolver().call(new Uri.Builder().scheme("content").authority(intent.getStringExtra("androidx.remotecallback.extra.AUTHORITY")).build(), "androidx.remotecallback.method.PROVIDER_CALLBACK", (String) null, intent.getExtras());
        }
    }
}
