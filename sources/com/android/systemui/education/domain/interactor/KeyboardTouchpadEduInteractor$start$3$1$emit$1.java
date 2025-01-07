package com.android.systemui.education.domain.interactor;

import com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$3;
import com.android.systemui.inputdevice.data.model.UserDeviceConnectionStatus;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyboardTouchpadEduInteractor$start$3$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ KeyboardTouchpadEduInteractor$start$3.AnonymousClass1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardTouchpadEduInteractor$start$3$1$emit$1(KeyboardTouchpadEduInteractor$start$3.AnonymousClass1 anonymousClass1, Continuation continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((UserDeviceConnectionStatus) null, (Continuation) this);
    }
}
