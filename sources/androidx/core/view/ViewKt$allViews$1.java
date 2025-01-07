package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ViewKt$allViews$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ View $this_allViews;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewKt$allViews$1(View view, Continuation continuation) {
        super(continuation);
        this.$this_allViews = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ViewKt$allViews$1 viewKt$allViews$1 = new ViewKt$allViews$1(this.$this_allViews, continuation);
        viewKt$allViews$1.L$0 = obj;
        return viewKt$allViews$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ViewKt$allViews$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceBuilderIterator sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            View view = this.$this_allViews;
            this.L$0 = sequenceBuilderIterator;
            this.label = 1;
            sequenceBuilderIterator.yield(view, this);
            return coroutineSingletons;
        }
        Unit unit = Unit.INSTANCE;
        if (i == 1) {
            SequenceBuilderIterator sequenceBuilderIterator2 = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
            View view2 = this.$this_allViews;
            if (view2 instanceof ViewGroup) {
                this.L$0 = null;
                this.label = 2;
                sequenceBuilderIterator2.getClass();
                Object yieldAll = sequenceBuilderIterator2.yieldAll(new TreeIterator(new ViewGroupKt$iterator$1((ViewGroup) view2), new Function1() { // from class: androidx.core.view.ViewGroupKt$descendants$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        View view3 = (View) obj2;
                        ViewGroup viewGroup = view3 instanceof ViewGroup ? (ViewGroup) view3 : null;
                        if (viewGroup != null) {
                            return new ViewGroupKt$iterator$1(viewGroup);
                        }
                        return null;
                    }
                }), this);
                if (yieldAll != coroutineSingletons) {
                    yieldAll = unit;
                }
                if (yieldAll == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
