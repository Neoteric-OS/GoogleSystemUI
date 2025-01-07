package com.android.wm.shell.transition;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.TransactionPool;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultTransitionHandler$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ ValueAnimator f$0;
    public final /* synthetic */ SurfaceControl.Transaction f$1;
    public final /* synthetic */ TransactionPool f$10;
    public final /* synthetic */ ShellExecutor f$11;
    public final /* synthetic */ ArrayList f$12;
    public final /* synthetic */ Runnable f$13;
    public final /* synthetic */ SurfaceControl f$2;
    public final /* synthetic */ Animation f$3;
    public final /* synthetic */ Transformation f$4;
    public final /* synthetic */ float[] f$5;
    public final /* synthetic */ Point f$6;
    public final /* synthetic */ float f$7;
    public final /* synthetic */ Rect f$8;

    public /* synthetic */ DefaultTransitionHandler$$ExternalSyntheticLambda9(ValueAnimator valueAnimator, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Animation animation, Transformation transformation, float[] fArr, Point point, float f, Rect rect, boolean z, TransactionPool transactionPool, ShellExecutor shellExecutor, ArrayList arrayList, Runnable runnable) {
        this.f$0 = valueAnimator;
        this.f$1 = transaction;
        this.f$2 = surfaceControl;
        this.f$3 = animation;
        this.f$4 = transformation;
        this.f$5 = fArr;
        this.f$6 = point;
        this.f$7 = f;
        this.f$8 = rect;
        this.f$10 = transactionPool;
        this.f$11 = shellExecutor;
        this.f$12 = arrayList;
        this.f$13 = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final ValueAnimator valueAnimator = this.f$0;
        SurfaceControl.Transaction transaction = this.f$1;
        SurfaceControl surfaceControl = this.f$2;
        Animation animation = this.f$3;
        Transformation transformation = this.f$4;
        float[] fArr = this.f$5;
        Point point = this.f$6;
        float f = this.f$7;
        Rect rect = this.f$8;
        TransactionPool transactionPool = this.f$10;
        ShellExecutor shellExecutor = this.f$11;
        final ArrayList arrayList = this.f$12;
        final Runnable runnable = this.f$13;
        DefaultTransitionHandler.applyTransformation(valueAnimator.getDuration(), transaction, surfaceControl, animation, transformation, fArr, point, f, rect);
        transactionPool.release(transaction);
        ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.DefaultTransitionHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ArrayList arrayList2 = arrayList;
                ValueAnimator valueAnimator2 = valueAnimator;
                Runnable runnable2 = runnable;
                arrayList2.remove(valueAnimator2);
                runnable2.run();
            }
        });
    }
}
