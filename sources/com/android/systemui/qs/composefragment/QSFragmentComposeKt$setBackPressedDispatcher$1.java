package com.android.systemui.qs.composefragment;

import android.view.View;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSFragmentComposeKt$setBackPressedDispatcher$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $this_setBackPressedDispatcher;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.composefragment.QSFragmentComposeKt$setBackPressedDispatcher$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ View $it;
        final /* synthetic */ View $this_setBackPressedDispatcher;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, LifecycleOwner lifecycleOwner, View view2, Continuation continuation) {
            super(2, continuation);
            this.$this_setBackPressedDispatcher = view;
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$it = view2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$this_setBackPressedDispatcher, this.$$this$repeatWhenAttached, this.$it, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$this_setBackPressedDispatcher.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, new OnBackPressedDispatcherOwner(this.$$this$repeatWhenAttached, this.$it) { // from class: com.android.systemui.qs.composefragment.QSFragmentComposeKt.setBackPressedDispatcher.1.1.1
                public final Lifecycle lifecycle;
                public final OnBackPressedDispatcher onBackPressedDispatcher;

                {
                    OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(null);
                    onBackPressedDispatcher.setOnBackInvokedDispatcher(r4.getViewRootImpl().getOnBackInvokedDispatcher());
                    this.onBackPressedDispatcher = onBackPressedDispatcher;
                    this.lifecycle = r3.getLifecycle();
                }

                @Override // androidx.lifecycle.LifecycleOwner
                public final Lifecycle getLifecycle() {
                    return this.lifecycle;
                }

                @Override // androidx.activity.OnBackPressedDispatcherOwner
                public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
                    return this.onBackPressedDispatcher;
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSFragmentComposeKt$setBackPressedDispatcher$1(View view, Continuation continuation) {
        super(3, continuation);
        this.$this_setBackPressedDispatcher = view;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        QSFragmentComposeKt$setBackPressedDispatcher$1 qSFragmentComposeKt$setBackPressedDispatcher$1 = new QSFragmentComposeKt$setBackPressedDispatcher$1(this.$this_setBackPressedDispatcher, (Continuation) obj3);
        qSFragmentComposeKt$setBackPressedDispatcher$1.L$0 = (LifecycleOwner) obj;
        qSFragmentComposeKt$setBackPressedDispatcher$1.L$1 = (View) obj2;
        return qSFragmentComposeKt$setBackPressedDispatcher$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            View view = (View) this.L$1;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_setBackPressedDispatcher, lifecycleOwner, view, null);
            this.L$0 = null;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
