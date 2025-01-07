package com.android.systemui.media.controls.ui.controller;

import com.android.systemui.media.controls.shared.model.MediaButton;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda10 implements Predicate {
    public final /* synthetic */ MediaButton f$0;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.f$0.getActionById(((Integer) obj).intValue()) != null;
    }
}
