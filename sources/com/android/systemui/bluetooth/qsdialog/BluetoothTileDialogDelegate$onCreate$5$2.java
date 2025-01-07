package com.android.systemui.bluetooth.qsdialog;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogDelegate$onCreate$5$2 extends View.AccessibilityDelegate {
    public final /* synthetic */ Object $context;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BluetoothTileDialogDelegate$onCreate$5$2(int i, Object obj) {
        this.$r8$classId = i;
        this.$context = obj;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        switch (this.$r8$classId) {
            case 0:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), ((Context) this.$context).getString(R.string.quick_settings_bluetooth_audio_sharing_button_accessibility)));
                break;
            default:
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), ((DeviceItem) this.$context).actionAccessibilityLabel));
                break;
        }
    }
}
