package com.android.systemui.statusbar.policy;

import android.content.ClipData;
import com.android.systemui.statusbar.policy.RemoteInputView;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RemoteInputView$RemoteEditText$$ExternalSyntheticLambda2 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = RemoteInputView.RemoteEditText.$r8$clinit;
        return ((ClipData.Item) obj).getUri() != null;
    }
}
