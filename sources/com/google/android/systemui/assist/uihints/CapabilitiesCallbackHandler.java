package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CapabilitiesCallbackHandler implements NgaMessageHandler.ConfigInfoListener {
    public final Context mContext;

    public CapabilitiesCallbackHandler(Context context) {
        this.mContext = context;
    }

    @Override // com.google.android.systemui.assist.uihints.NgaMessageHandler.ConfigInfoListener
    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        if (configInfo.capabilitiesCallback == null) {
            return;
        }
        Intent intent = new Intent();
        ArrayList<CharSequence> arrayList = new ArrayList<>();
        arrayList.add("config/nga_is_assistant");
        arrayList.add("config/capabilities_callback");
        arrayList.add("go_back");
        arrayList.add("swipe/swipe_action");
        arrayList.add("gesture_nav_bar_visible/visible");
        intent.putCharSequenceArrayListExtra("capabilities", arrayList);
        try {
            configInfo.capabilitiesCallback.send(this.mContext, 0, intent);
        } catch (PendingIntent.CanceledException e) {
            Log.e("CapabilitiesCallbackHandler", "Pending intent canceled", e);
        }
    }
}
