package com.android.wm.shell.activityembedding;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.window.TransitionInfo;
import com.android.wm.shell.activityembedding.ActivityEmbeddingAnimationAdapter;
import com.android.wm.shell.common.ScreenshotUtils;
import com.android.wm.shell.shared.TransitionUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityEmbeddingAnimationRunner {
    public Animator mActiveAnimator;
    final ActivityEmbeddingAnimationSpec mAnimationSpec;
    public final ActivityEmbeddingController mController;

    public ActivityEmbeddingAnimationRunner(Context context, ActivityEmbeddingController activityEmbeddingController) {
        this.mController = activityEmbeddingController;
        this.mAnimationSpec = new ActivityEmbeddingAnimationSpec(context);
    }

    public static void calculateParentBounds(TransitionInfo.Change change, TransitionInfo.Change change2, Rect rect) {
        Point endParentSize = change.getEndParentSize();
        if (endParentSize.equals(0, 0)) {
            return;
        }
        Point endRelOffset = change.getEndRelOffset();
        Point point = new Point(change.getEndAbsBounds().left, change.getEndAbsBounds().top);
        Point point2 = new Point(point.x - endRelOffset.x, point.y - endRelOffset.y);
        int i = point2.x;
        int i2 = point2.y;
        rect.set(i, i2, endParentSize.x + i, endParentSize.y + i2);
    }

    public static List createOpenCloseAnimationAdapters(TransitionInfo transitionInfo, boolean z, ActivityEmbeddingAnimationRunner$$ExternalSyntheticLambda2 activityEmbeddingAnimationRunner$$ExternalSyntheticLambda2, SurfaceControl.Transaction transaction) {
        SurfaceControl orCreateScreenshot;
        ArrayList<TransitionInfo.Change> arrayList = new ArrayList();
        ArrayList<TransitionInfo.Change> arrayList2 = new ArrayList();
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (TransitionUtil.isOpeningType(change.getMode())) {
                arrayList.add(change);
                rect.union(change.getEndAbsBounds());
            } else {
                arrayList2.add(change);
                rect2.union(change.getStartAbsBounds());
                rect2.union(change.getEndAbsBounds());
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int i = 1000;
        for (TransitionInfo.Change change2 : arrayList) {
            Animation animation = activityEmbeddingAnimationRunner$$ExternalSyntheticLambda2.get(transitionInfo, change2, rect);
            if (shouldUseJumpCutForAnimation(animation)) {
                return new ArrayList();
            }
            ActivityEmbeddingAnimationAdapter activityEmbeddingAnimationAdapter = new ActivityEmbeddingAnimationAdapter(animation, change2, change2.getLeash(), rect, transitionInfo.getRoot(TransitionUtil.rootIndexFor(change2, transitionInfo)));
            if (z) {
                activityEmbeddingAnimationAdapter.mOverrideLayer = i;
                i++;
            }
            arrayList3.add(activityEmbeddingAnimationAdapter);
        }
        for (TransitionInfo.Change change3 : arrayList2) {
            if ((!TransitionUtil.isClosingType(change3.getMode()) ? false : !change3.getStartAbsBounds().equals(change3.getEndAbsBounds())) && (orCreateScreenshot = getOrCreateScreenshot(change3, change3, transaction)) != null) {
                ActivityEmbeddingAnimationAdapter.SnapshotAdapter snapshotAdapter = new ActivityEmbeddingAnimationAdapter.SnapshotAdapter(new AlphaAnimation(1.0f, 1.0f), change3, orCreateScreenshot, change3.getEndAbsBounds(), transitionInfo.getRoot(TransitionUtil.rootIndexFor(change3, transitionInfo)));
                if (!z) {
                    snapshotAdapter.mOverrideLayer = i;
                    i++;
                }
                arrayList3.add(snapshotAdapter);
            }
            Animation animation2 = activityEmbeddingAnimationRunner$$ExternalSyntheticLambda2.get(transitionInfo, change3, rect2);
            if (shouldUseJumpCutForAnimation(animation2)) {
                return new ArrayList();
            }
            ActivityEmbeddingAnimationAdapter activityEmbeddingAnimationAdapter2 = new ActivityEmbeddingAnimationAdapter(animation2, change3, change3.getLeash(), rect2, transitionInfo.getRoot(TransitionUtil.rootIndexFor(change3, transitionInfo)));
            if (!z) {
                activityEmbeddingAnimationAdapter2.mOverrideLayer = i;
                i++;
            }
            arrayList3.add(activityEmbeddingAnimationAdapter2);
        }
        return arrayList3;
    }

    public static SurfaceControl getOrCreateScreenshot(TransitionInfo.Change change, TransitionInfo.Change change2, SurfaceControl.Transaction transaction) {
        SurfaceControl snapshot = change.getSnapshot();
        if (snapshot != null) {
            transaction.reparent(snapshot, change2.getLeash());
            return snapshot;
        }
        Rect rect = new Rect(change.getStartAbsBounds());
        rect.offsetTo(0, 0);
        return ScreenshotUtils.takeScreenshot(transaction, change.getLeash(), change2.getLeash(), rect, Integer.MAX_VALUE);
    }

    public static boolean shouldUseJumpCutForAnimation(Animation animation) {
        return animation.getDuration() == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:181:0x0154, code lost:
    
        if (r7.getEndAbsBounds().equals(r11.getStartAbsBounds()) != false) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x061b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[LOOP:2: B:52:0x05eb->B:78:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator createAnimator(android.window.TransitionInfo r29, android.view.SurfaceControl.Transaction r30, final android.view.SurfaceControl.Transaction r31, final java.lang.Runnable r32, java.util.List r33) {
        /*
            Method dump skipped, instructions count: 1758
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.activityembedding.ActivityEmbeddingAnimationRunner.createAnimator(android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, java.lang.Runnable, java.util.List):android.animation.Animator");
    }
}
