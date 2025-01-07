package com.google.android.systemui.dreamliner;

import android.view.View;
import com.google.common.base.Preconditions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DreamlinerA11yAction {
    public int mActionId = -1;
    public final String mActionLabel;
    public final AnonymousClass1 mActionWrapper;
    public final DockGestureController$$ExternalSyntheticLambda0 mCustomAction;
    public final View mViewForAction;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.dreamliner.DreamlinerA11yAction$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    public DreamlinerA11yAction(String str, View view, DockGestureController$$ExternalSyntheticLambda0 dockGestureController$$ExternalSyntheticLambda0) {
        Preconditions.checkNotNull((str == null || str.isEmpty()) ? null : str, "action label cannot be null or empty");
        Preconditions.checkNotNull(view, "view cannot be null");
        this.mActionLabel = str;
        this.mViewForAction = view;
        this.mCustomAction = dockGestureController$$ExternalSyntheticLambda0;
        this.mActionWrapper = new AnonymousClass1();
    }
}
