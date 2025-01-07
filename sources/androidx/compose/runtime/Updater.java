package androidx.compose.runtime;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Updater {
    /* renamed from: init-impl, reason: not valid java name */
    public static final void m258initimpl(Composer composer, final Function1 function1) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        if (composerImpl.inserting) {
            composerImpl.apply(Unit.INSTANCE, new Function2() { // from class: androidx.compose.runtime.Updater$init$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Function1.this.invoke(obj);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* renamed from: set-impl, reason: not valid java name */
    public static final void m259setimpl(Composer composer, Object obj, Function2 function2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), obj)) {
            composerImpl.updateRememberedValue(obj);
            composerImpl.apply(obj, function2);
        }
    }
}
