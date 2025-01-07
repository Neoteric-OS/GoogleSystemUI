package com.android.systemui.navigationbar.views;

import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBar$$ExternalSyntheticLambda3 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                if (((Long) obj).longValue() != 0) {
                }
                break;
            case 1:
                if (((Long) obj).longValue() > 0) {
                }
                break;
            case 2:
                if (((Float) obj).floatValue() > 0.0f) {
                }
                break;
            default:
                if (((Long) obj).longValue() != 0) {
                }
                break;
        }
        return false;
    }
}
