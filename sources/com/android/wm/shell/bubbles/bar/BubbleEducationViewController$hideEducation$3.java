package com.android.wm.shell.bubbles.bar;

import android.view.View;
import android.view.ViewGroup;
import com.android.wm.shell.taskview.TaskView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BubbleEducationViewController$hideEducation$3 extends Lambda implements Function0 {
    final /* synthetic */ Function0 $endActions;
    final /* synthetic */ BubbleEducationViewController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BubbleEducationViewController$hideEducation$3(BubbleEducationViewController bubbleEducationViewController, Function0 function0) {
        super(0);
        this.this$0 = bubbleEducationViewController;
        this.$endActions = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        TaskView taskView;
        BubbleEducationViewController bubbleEducationViewController = this.this$0;
        ViewGroup viewGroup = bubbleEducationViewController.rootView;
        if (viewGroup != null) {
            viewGroup.removeView(bubbleEducationViewController.educationView);
        }
        ViewGroup viewGroup2 = bubbleEducationViewController.rootView;
        if (viewGroup2 != null) {
            viewGroup2.removeView((View) bubbleEducationViewController.scrimView$delegate.getValue());
        }
        bubbleEducationViewController.educationView = null;
        bubbleEducationViewController.rootView = null;
        bubbleEducationViewController.animator = null;
        this.$endActions.invoke();
        BubbleBarExpandedView bubbleBarExpandedView = this.this$0.listener.f$0.mExpandedView;
        if (bubbleBarExpandedView != null && (taskView = bubbleBarExpandedView.mTaskView) != null && bubbleBarExpandedView.mLayerBoundsSupplier != null) {
            taskView.mObscuredTouchRegion = null;
        }
        return Unit.INSTANCE;
    }
}
