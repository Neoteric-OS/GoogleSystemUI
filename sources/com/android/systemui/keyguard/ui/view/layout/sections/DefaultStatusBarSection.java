package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultStatusBarSection extends KeyguardSection {
    public final Context context;

    public DefaultStatusBarSection(Context context, NotificationPanelView notificationPanelView, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory) {
        this.context = context;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        constraintSet.constrainHeight(R.id.keyguard_header, Utils.getStatusBarHeaderHeightKeyguard(this.context));
        constraintSet.connect(R.id.keyguard_header, 3, 0, 3);
        constraintSet.connect(R.id.keyguard_header, 6, 0, 6);
        constraintSet.connect(R.id.keyguard_header, 7, 0, 7);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.keyguard_header);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
    }
}
