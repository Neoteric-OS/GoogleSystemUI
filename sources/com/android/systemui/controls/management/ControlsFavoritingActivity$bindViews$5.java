package com.android.systemui.controls.management;

import android.text.TextUtils;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.android.systemui.controls.TooltipManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsFavoritingActivity$bindViews$5 extends ViewPager2.OnPageChangeCallback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsFavoritingActivity this$0;

    public /* synthetic */ ControlsFavoritingActivity$bindViews$5(ControlsFavoritingActivity controlsFavoritingActivity, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsFavoritingActivity;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i, float f, int i2) {
        switch (this.$r8$classId) {
            case 1:
                ManagementPageIndicator managementPageIndicator = this.this$0.pageIndicator;
                if (managementPageIndicator == null) {
                    managementPageIndicator = null;
                }
                managementPageIndicator.setLocation(i + f);
                break;
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public final void onPageSelected(int i) {
        switch (this.$r8$classId) {
            case 0:
                TooltipManager tooltipManager = this.this$0.mTooltipManager;
                if (tooltipManager != null) {
                    tooltipManager.hide(true);
                    break;
                }
                break;
            default:
                ControlsFavoritingActivity controlsFavoritingActivity = this.this$0;
                CharSequence charSequence = ((StructureContainer) controlsFavoritingActivity.listOfStructures.get(i)).structureName;
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = controlsFavoritingActivity.appName;
                }
                TextView textView = controlsFavoritingActivity.titleView;
                if (textView == null) {
                    textView = null;
                }
                textView.setText(charSequence);
                TextView textView2 = controlsFavoritingActivity.titleView;
                (textView2 != null ? textView2 : null).requestFocus();
                break;
        }
    }
}
