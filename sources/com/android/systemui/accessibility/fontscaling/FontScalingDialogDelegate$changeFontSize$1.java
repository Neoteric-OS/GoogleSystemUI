package com.android.systemui.accessibility.fontscaling;

import android.widget.Button;
import android.widget.TextView;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontScalingDialogDelegate$changeFontSize$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FontScalingDialogDelegate this$0;

    public /* synthetic */ FontScalingDialogDelegate$changeFontSize$1(FontScalingDialogDelegate fontScalingDialogDelegate, int i) {
        this.$r8$classId = i;
        this.this$0 = fontScalingDialogDelegate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.widget.TextView] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                FontScalingDialogDelegate fontScalingDialogDelegate = this.this$0;
                UserTrackerImpl userTrackerImpl = (UserTrackerImpl) fontScalingDialogDelegate.userTracker;
                int userId = userTrackerImpl.getUserId();
                SecureSettingsImpl secureSettingsImpl = (SecureSettingsImpl) fontScalingDialogDelegate.secureSettings;
                if (!Intrinsics.areEqual(secureSettingsImpl.getStringForUser(userId, "accessibility_font_scaling_has_been_changed"), "1")) {
                    secureSettingsImpl.putStringForUser("accessibility_font_scaling_has_been_changed", "1", userTrackerImpl.getUserId());
                    break;
                }
                break;
            case 1:
                TextView textView = this.this$0.title;
                if (textView == null) {
                    textView = null;
                }
                textView.setTextAppearance(R.style.TextAppearance_Dialog_Title);
                Button button = this.this$0.doneButton;
                if (button == null) {
                    button = null;
                }
                button.setTextAppearance(R.style.Widget_Dialog_Button);
                Button button2 = this.this$0.doneButton;
                (button2 != null ? button2 : null).setEnabled(true);
                break;
            case 2:
                Button button3 = this.this$0.doneButton;
                (button3 != null ? button3 : null).setEnabled(true);
                break;
            default:
                FontScalingDialogDelegate fontScalingDialogDelegate2 = this.this$0;
                if (!fontScalingDialogDelegate2.systemSettings.putStringForUser("font_scale", fontScalingDialogDelegate2.strEntryValues[fontScalingDialogDelegate2.lastProgress.get()], ((UserTrackerImpl) fontScalingDialogDelegate2.userTracker).getUserId())) {
                    ?? r0 = fontScalingDialogDelegate2.title;
                    (r0 != 0 ? r0 : null).post(new FontScalingDialogDelegate$changeFontSize$1(fontScalingDialogDelegate2, 2));
                    break;
                }
                break;
        }
    }
}
