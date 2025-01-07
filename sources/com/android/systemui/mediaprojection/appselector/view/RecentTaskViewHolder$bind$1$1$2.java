package com.android.systemui.mediaprojection.appselector.view;

import android.content.ComponentName;
import android.view.ViewGroup;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader;
import com.android.systemui.mediaprojection.appselector.data.RecentTask;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RecentTaskViewHolder$bind$1$1$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ComponentName $component;
    final /* synthetic */ RecentTask $task;
    int label;
    final /* synthetic */ RecentTaskViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecentTaskViewHolder$bind$1$1$2(RecentTaskViewHolder recentTaskViewHolder, RecentTask recentTask, ComponentName componentName, Continuation continuation) {
        super(2, continuation);
        this.this$0 = recentTaskViewHolder;
        this.$task = recentTask;
        this.$component = componentName;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new RecentTaskViewHolder$bind$1$1$2(this.this$0, this.$task, this.$component, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((RecentTaskViewHolder$bind$1$1$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ActivityTaskManagerLabelLoader activityTaskManagerLabelLoader = this.this$0.labelLoader;
            int i2 = this.$task.userId;
            ComponentName componentName = this.$component;
            this.label = 1;
            obj = activityTaskManagerLabelLoader.loadLabel(i2, componentName, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        CharSequence charSequence = (CharSequence) obj;
        ViewGroup viewGroup = this.this$0.root;
        if (charSequence == null) {
            charSequence = viewGroup.getContext().getString(R.string.unknown);
        }
        viewGroup.setContentDescription(charSequence);
        return Unit.INSTANCE;
    }
}
