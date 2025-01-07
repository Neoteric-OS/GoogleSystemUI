package androidx.compose.foundation;

import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class AbstractClickableNode$focusableNode$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        AbstractClickableNode abstractClickableNode = (AbstractClickableNode) this.receiver;
        if (z) {
            abstractClickableNode.initializeIndicationAndInteractionSourceIfNeeded();
            return;
        }
        MutableInteractionSource mutableInteractionSource = abstractClickableNode.interactionSource;
        MutableLongObjectMap mutableLongObjectMap = abstractClickableNode.currentKeyPressInteractions;
        if (mutableInteractionSource != null) {
            Object[] objArr = mutableLongObjectMap.values;
            long[] jArr = mutableLongObjectMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                BuildersKt.launch$default(abstractClickableNode.getCoroutineScope(), null, null, new AbstractClickableNode$onFocusChange$1$1(abstractClickableNode, (PressInteraction$Press) objArr[(i << 3) + i3], null), 3);
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        }
                    }
                    if (i == length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        mutableLongObjectMap.clear();
        abstractClickableNode.onCancelKeyInput();
    }
}
