package com.android.systemui.ambient.statusbar.ui;

import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyType;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AmbientStatusBarViewController$$ExternalSyntheticLambda11 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((PrivacyItem) obj).privacyType == PrivacyType.TYPE_LOCATION;
    }
}
