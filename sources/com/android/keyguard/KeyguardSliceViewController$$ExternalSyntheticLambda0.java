package com.android.keyguard;

import android.net.Uri;
import androidx.slice.widget.SliceContent;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSliceViewController$$ExternalSyntheticLambda0 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return !"content://com.android.systemui.keyguard/action".equals(Uri.parse(((SliceContent) obj).mSliceItem.getSlice().mUri).toString());
    }
}
