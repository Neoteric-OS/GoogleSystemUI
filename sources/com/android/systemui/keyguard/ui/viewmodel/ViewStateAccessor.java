package com.android.systemui.keyguard.ui.viewmodel;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewStateAccessor {
    public final Lambda alpha;
    public final Function0 translationX;
    public final Function0 translationY;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewStateAccessor(Function0 function0) {
        AnonymousClass2 anonymousClass2 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor.2
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return 0;
            }
        };
        AnonymousClass3 anonymousClass3 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor.3
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return 0;
            }
        };
        this.alpha = (Lambda) function0;
        this.translationY = anonymousClass2;
        this.translationX = anonymousClass3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewStateAccessor)) {
            return false;
        }
        ViewStateAccessor viewStateAccessor = (ViewStateAccessor) obj;
        return this.alpha.equals(viewStateAccessor.alpha) && Intrinsics.areEqual(this.translationY, viewStateAccessor.translationY) && Intrinsics.areEqual(this.translationX, viewStateAccessor.translationX);
    }

    public final int hashCode() {
        return this.translationX.hashCode() + ((this.translationY.hashCode() + (this.alpha.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ViewStateAccessor(alpha=" + this.alpha + ", translationY=" + this.translationY + ", translationX=" + this.translationX + ")";
    }
}
