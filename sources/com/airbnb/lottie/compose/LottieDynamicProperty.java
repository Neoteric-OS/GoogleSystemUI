package com.airbnb.lottie.compose;

import com.airbnb.lottie.model.KeyPath;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieDynamicProperty {
    public final Function1 callback;
    public final KeyPath keyPath;
    public final Object property;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.airbnb.lottie.compose.LottieDynamicProperty$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function1 {
        final /* synthetic */ Object $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object obj) {
            super(1);
            this.$value = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return this.$value;
        }
    }

    public LottieDynamicProperty(Object obj, KeyPath keyPath, Object obj2) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(obj2);
        this.property = obj;
        this.keyPath = keyPath;
        this.callback = anonymousClass1;
    }
}
