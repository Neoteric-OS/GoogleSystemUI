package com.android.systemui.wifi;

import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiDebuggingActivity$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i = WifiDebuggingActivity.$r8$clinit;
        if ((motionEvent.getFlags() & 1) == 0 && (motionEvent.getFlags() & 2) == 0) {
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return true;
        }
        EventLog.writeEvent(1397638484, "62187985");
        Toast.makeText(view.getContext(), R.string.touch_filtered_warning, 0).show();
        return true;
    }
}
