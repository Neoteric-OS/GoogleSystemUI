package com.google.android.systemui.columbus.legacy.gates;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SetupWizard$isSetupComplete$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SetupWizard this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupWizard$isSetupComplete$1(SetupWizard setupWizard, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = setupWizard;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SetupWizard.access$isSetupComplete(this.this$0, this);
    }
}
