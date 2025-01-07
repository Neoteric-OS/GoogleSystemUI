package com.android.systemui.qs.composefragment;

import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import java.util.Collections;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSFragmentCompose$collapseExpandSemanticAction$1$1 extends Lambda implements Function1 {
    final /* synthetic */ Runnable $it;
    final /* synthetic */ String $label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSFragmentCompose$collapseExpandSemanticAction$1$1(String str, Runnable runnable) {
        super(1);
        this.$label = str;
        this.$it = runnable;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        String str = this.$label;
        final Runnable runnable = this.$it;
        SemanticsPropertiesKt.setCustomActions((SemanticsPropertyReceiver) obj, Collections.singletonList(new CustomAccessibilityAction(str, new Function0() { // from class: com.android.systemui.qs.composefragment.QSFragmentCompose$collapseExpandSemanticAction$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                runnable.run();
                return Boolean.TRUE;
            }
        })));
        return Unit.INSTANCE;
    }
}
