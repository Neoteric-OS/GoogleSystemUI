package com.android.systemui.temporarydisplay;

import android.graphics.Rect;
import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class TemporaryViewDisplayController$inflateAndUpdateView$newViewController$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TemporaryViewDisplayController temporaryViewDisplayController = (TemporaryViewDisplayController) this.receiver;
        temporaryViewDisplayController.getTouchableRegion((Rect) obj2, (View) obj);
        return Unit.INSTANCE;
    }
}
