package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PrimaryBouncerToGoneTransitionViewModel$lockscreenAlpha$2 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Boolean.valueOf(((PrimaryBouncerInteractor) this.receiver).willRunDismissFromKeyguard());
    }
}
