package com.android.systemui.screenrecord;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1 implements View.OnTouchListener {
    public static final ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1 INSTANCE = new ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1(0);
    public static final ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1 INSTANCE$1 = new ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1(1);
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ScreenRecordPermissionDialogDelegate$initRecordOptionsView$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (this.$r8$classId) {
            case 0:
                if (motionEvent.getAction() == 2) {
                }
                break;
            default:
                if (motionEvent.getAction() == 2) {
                }
                break;
        }
        return false;
    }
}
