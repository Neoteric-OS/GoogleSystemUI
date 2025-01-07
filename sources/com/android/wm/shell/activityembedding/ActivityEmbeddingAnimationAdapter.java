package com.android.wm.shell.activityembedding;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.window.TransitionInfo;
import com.android.wm.shell.shared.TransitionUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ActivityEmbeddingAnimationAdapter {
    public final Animation mAnimation;
    public final TransitionInfo.Change mChange;
    public final Rect mContentBounds;
    public final Point mContentRelOffset;
    public final SurfaceControl mLeash;
    public final float[] mMatrix;
    public int mOverrideLayer;
    public final Transformation mTransformation;
    public final Rect mWholeAnimationBounds;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SnapshotAdapter extends ActivityEmbeddingAnimationAdapter {
        public final /* synthetic */ int $r8$classId = 0;

        public /* synthetic */ SnapshotAdapter(Animation animation, TransitionInfo.Change change, SurfaceControl surfaceControl, Rect rect, TransitionInfo.Root root) {
            super(animation, change, surfaceControl, rect, root);
        }

        @Override // com.android.wm.shell.activityembedding.ActivityEmbeddingAnimationAdapter
        public void onAnimationEnd(SurfaceControl.Transaction transaction) {
            switch (this.$r8$classId) {
                case 0:
                    super.onAnimationEnd(transaction);
                    if (this.mLeash.isValid()) {
                        transaction.remove(this.mLeash);
                        break;
                    }
                    break;
                default:
                    super.onAnimationEnd(transaction);
                    break;
            }
        }

        @Override // com.android.wm.shell.activityembedding.ActivityEmbeddingAnimationAdapter
        public final void onAnimationUpdateInner(SurfaceControl.Transaction transaction) {
            switch (this.$r8$classId) {
                case 0:
                    this.mTransformation.getMatrix().postTranslate(0.0f, 0.0f);
                    transaction.setMatrix(this.mLeash, this.mTransformation.getMatrix(), this.mMatrix);
                    transaction.setAlpha(this.mLeash, this.mTransformation.getAlpha());
                    break;
                default:
                    Matrix matrix = this.mTransformation.getMatrix();
                    Point point = this.mContentRelOffset;
                    matrix.postTranslate(point.x, point.y);
                    transaction.setMatrix(this.mLeash, this.mTransformation.getMatrix(), this.mMatrix);
                    transaction.setAlpha(this.mLeash, this.mTransformation.getAlpha());
                    transaction.setWindowCrop(this.mLeash, this.mWholeAnimationBounds.width(), this.mWholeAnimationBounds.height());
                    break;
            }
        }

        public /* synthetic */ SnapshotAdapter(Animation animation, TransitionInfo.Change change, TransitionInfo.Root root) {
            super(animation, change, root);
        }
    }

    public ActivityEmbeddingAnimationAdapter(Animation animation, TransitionInfo.Change change, TransitionInfo.Root root) {
        this(animation, change, change.getLeash(), change.getEndAbsBounds(), root);
    }

    public void onAnimationEnd(SurfaceControl.Transaction transaction) {
        long duration = this.mAnimation.getDuration();
        this.mTransformation.clear();
        Animation animation = this.mAnimation;
        animation.getTransformation(Math.min(duration, animation.getDuration()), this.mTransformation);
        onAnimationUpdateInner(transaction);
    }

    public void onAnimationUpdateInner(SurfaceControl.Transaction transaction) {
        Matrix matrix = this.mTransformation.getMatrix();
        Point point = this.mContentRelOffset;
        matrix.postTranslate(point.x, point.y);
        SurfaceControl surfaceControl = this.mLeash;
        Matrix matrix2 = this.mTransformation.getMatrix();
        float[] fArr = this.mMatrix;
        transaction.setMatrix(surfaceControl, matrix2, fArr);
        transaction.setAlpha(this.mLeash, this.mTransformation.getAlpha());
        int round = Math.round(fArr[2]);
        int round2 = Math.round(fArr[5]);
        Rect rect = new Rect(this.mContentBounds);
        Point point2 = this.mContentRelOffset;
        rect.offset(round - point2.x, round2 - point2.y);
        int i = rect.left;
        int i2 = rect.top;
        if (!rect.intersect(this.mWholeAnimationBounds)) {
            transaction.setAlpha(this.mLeash, 0.0f);
        } else if (this.mAnimation.getExtensionEdges() != 0) {
            rect.union(this.mContentBounds);
        }
        rect.offset(-i, -i2);
        transaction.setCrop(this.mLeash, rect);
    }

    public ActivityEmbeddingAnimationAdapter(Animation animation, TransitionInfo.Change change, SurfaceControl surfaceControl, Rect rect, TransitionInfo.Root root) {
        Rect rect2 = new Rect();
        this.mWholeAnimationBounds = rect2;
        Rect rect3 = new Rect();
        this.mContentBounds = rect3;
        Point point = new Point();
        this.mContentRelOffset = point;
        this.mTransformation = new Transformation();
        this.mMatrix = new float[9];
        new Rect();
        this.mOverrideLayer = -1;
        this.mAnimation = animation;
        this.mChange = change;
        this.mLeash = surfaceControl;
        rect2.set(rect);
        Rect startAbsBounds = change.getStartAbsBounds();
        Rect endAbsBounds = change.getEndAbsBounds();
        if (change.getParent() != null) {
            point.set(change.getEndRelOffset());
        } else {
            Point offset = root.getOffset();
            point.set(endAbsBounds.left - offset.x, endAbsBounds.top - offset.y);
        }
        if (!TransitionUtil.isClosingType(change.getMode())) {
            rect3.set(change.getEndAbsBounds());
        } else {
            rect3.set(startAbsBounds);
            point.offset(startAbsBounds.left - endAbsBounds.left, startAbsBounds.top - endAbsBounds.top);
        }
    }
}
