package com.android.systemui.volume;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeDialogSeekBarAccessibilityDelegate extends View.AccessibilityDelegate {
    public final int accessibilityStep = 100;

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (!(view instanceof SeekBar)) {
            throw new IllegalArgumentException("This class only works with the SeekBar");
        }
        SeekBar seekBar = (SeekBar) view;
        if (i != 4096 && i != 8192) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        int i2 = this.accessibilityStep;
        if (i == 8192) {
            i2 = -i2;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE", RangesKt.coerceIn(seekBar.getProgress() + i2, seekBar.getMin(), seekBar.getMax()));
        return super.performAccessibilityAction(view, R.id.accessibilityActionSetProgress, bundle2);
    }
}
