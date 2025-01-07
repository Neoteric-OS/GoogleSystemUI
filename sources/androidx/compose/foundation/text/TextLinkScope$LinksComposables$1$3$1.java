package androidx.compose.foundation.text;

import androidx.collection.MutableObjectList;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.foundation.interaction.PressInteraction$Release;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextLinkScope$LinksComposables$1$3$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ LinkStateInteractionSourceObserver $linkStateObserver;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextLinkScope$LinksComposables$1$3$1(LinkStateInteractionSourceObserver linkStateInteractionSourceObserver, Continuation continuation) {
        super(2, continuation);
        this.$linkStateObserver = linkStateInteractionSourceObserver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TextLinkScope$LinksComposables$1$3$1(this.$linkStateObserver, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TextLinkScope$LinksComposables$1$3$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        final LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = this.$linkStateObserver;
        this.label = 1;
        linkStateInteractionSourceObserver.getClass();
        final MutableObjectList mutableObjectList = new MutableObjectList();
        linkStateInteractionSourceObserver.interactionSource.getInteractions().collect(new FlowCollector() { // from class: androidx.compose.foundation.text.LinkStateInteractionSourceObserver$collectInteractionsForLinks$2
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                int indexOf;
                Interaction interaction = (Interaction) obj2;
                boolean z = interaction instanceof HoverInteraction$Enter ? true : interaction instanceof FocusInteraction$Focus ? true : interaction instanceof PressInteraction$Press;
                MutableObjectList mutableObjectList2 = MutableObjectList.this;
                if (z) {
                    mutableObjectList2.add(interaction);
                } else if (interaction instanceof HoverInteraction$Exit) {
                    int indexOf2 = mutableObjectList2.indexOf(((HoverInteraction$Exit) interaction).enter);
                    if (indexOf2 >= 0) {
                        mutableObjectList2.removeAt(indexOf2);
                    }
                } else if (interaction instanceof FocusInteraction$Unfocus) {
                    int indexOf3 = mutableObjectList2.indexOf(((FocusInteraction$Unfocus) interaction).focus);
                    if (indexOf3 >= 0) {
                        mutableObjectList2.removeAt(indexOf3);
                    }
                } else if (interaction instanceof PressInteraction$Release) {
                    int indexOf4 = mutableObjectList2.indexOf(((PressInteraction$Release) interaction).press);
                    if (indexOf4 >= 0) {
                        mutableObjectList2.removeAt(indexOf4);
                    }
                } else if ((interaction instanceof PressInteraction$Cancel) && (indexOf = mutableObjectList2.indexOf(((PressInteraction$Cancel) interaction).press)) >= 0) {
                    mutableObjectList2.removeAt(indexOf);
                }
                Object[] objArr = mutableObjectList2.content;
                int i2 = mutableObjectList2._size;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    LinkStateInteractionSourceObserver linkStateInteractionSourceObserver2 = linkStateInteractionSourceObserver;
                    if (i3 >= i2) {
                        ((SnapshotMutableIntStateImpl) linkStateInteractionSourceObserver2.interactionState).setIntValue(i4);
                        return Unit.INSTANCE;
                    }
                    Interaction interaction2 = (Interaction) objArr[i3];
                    if (interaction2 instanceof HoverInteraction$Enter) {
                        linkStateInteractionSourceObserver2.getClass();
                        i4 |= 2;
                    } else if (interaction2 instanceof FocusInteraction$Focus) {
                        linkStateInteractionSourceObserver2.getClass();
                        i4 |= 1;
                    } else if (interaction2 instanceof PressInteraction$Press) {
                        linkStateInteractionSourceObserver2.getClass();
                        i4 |= 4;
                    }
                    i3++;
                }
            }
        }, this);
        return coroutineSingletons;
    }
}
