package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultItemAnimator {
    public static TimeInterpolator sDefaultInterpolator;
    public RecyclerView.AnonymousClass5 mListener = null;
    public ArrayList mFinishedListeners = new ArrayList();
    public long mAddDuration = 120;
    public long mRemoveDuration = 120;
    public long mMoveDuration = 250;
    public long mChangeDuration = 250;
    public final boolean mSupportsChangeAnimations = true;
    public final ArrayList mPendingRemovals = new ArrayList();
    public final ArrayList mPendingAdditions = new ArrayList();
    public final ArrayList mPendingMoves = new ArrayList();
    public final ArrayList mPendingChanges = new ArrayList();
    public final ArrayList mAdditionsList = new ArrayList();
    public final ArrayList mMovesList = new ArrayList();
    public final ArrayList mChangesList = new ArrayList();
    public final ArrayList mAddAnimations = new ArrayList();
    public final ArrayList mMoveAnimations = new ArrayList();
    public final ArrayList mRemoveAnimations = new ArrayList();
    public final ArrayList mChangeAnimations = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$4, reason: invalid class name */
    public final class AnonymousClass4 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId = 1;
        public final /* synthetic */ ViewPropertyAnimator val$animation;
        public final /* synthetic */ RecyclerView.ViewHolder val$holder;
        public final /* synthetic */ View val$view;

        public AnonymousClass4(RecyclerView.ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.val$holder = viewHolder;
            this.val$animation = viewPropertyAnimator;
            this.val$view = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    this.val$view.setAlpha(1.0f);
                    break;
                default:
                    super.onAnimationCancel(animator);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.val$animation.setListener(null);
                    this.val$view.setAlpha(1.0f);
                    DefaultItemAnimator.this.dispatchAnimationFinished(this.val$holder);
                    DefaultItemAnimator.this.mRemoveAnimations.remove(this.val$holder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                    break;
                default:
                    this.val$animation.setListener(null);
                    DefaultItemAnimator.this.dispatchAnimationFinished(this.val$holder);
                    DefaultItemAnimator.this.mAddAnimations.remove(this.val$holder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    DefaultItemAnimator.this.getClass();
                    break;
                default:
                    DefaultItemAnimator.this.getClass();
                    break;
            }
        }

        public AnonymousClass4(RecyclerView.ViewHolder viewHolder, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.val$holder = viewHolder;
            this.val$view = view;
            this.val$animation = viewPropertyAnimator;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        public final String toString() {
            StringBuilder sb = new StringBuilder("ChangeInfo{oldHolder=");
            sb.append(this.oldHolder);
            sb.append(", newHolder=");
            sb.append(this.newHolder);
            sb.append(", fromX=");
            sb.append(this.fromX);
            sb.append(", fromY=");
            sb.append(this.fromY);
            sb.append(", toX=");
            sb.append(this.toX);
            sb.append(", toY=");
            return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.toY, '}');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;
    }

    public static void buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder viewHolder) {
        int i = viewHolder.mFlags;
        if (!viewHolder.isInvalid() && (i & 4) == 0) {
            viewHolder.getAbsoluteAdapterPosition();
        }
    }

    public static void cancelAll(List list) {
        ArrayList arrayList = (ArrayList) list;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((RecyclerView.ViewHolder) arrayList.get(size)).itemView.animate().cancel();
        }
    }

    public final boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        int i;
        int i2;
        int i3 = recyclerView$ItemAnimator$ItemHolderInfo.left;
        int i4 = recyclerView$ItemAnimator$ItemHolderInfo.top;
        if (viewHolder2.shouldIgnore()) {
            int i5 = recyclerView$ItemAnimator$ItemHolderInfo.left;
            i2 = recyclerView$ItemAnimator$ItemHolderInfo.top;
            i = i5;
        } else {
            i = recyclerView$ItemAnimator$ItemHolderInfo2.left;
            i2 = recyclerView$ItemAnimator$ItemHolderInfo2.top;
        }
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i3, i4, i, i2);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation(viewHolder);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        resetAnimation(viewHolder2);
        viewHolder2.itemView.setTranslationX(-((int) ((i - i3) - translationX)));
        viewHolder2.itemView.setTranslationY(-((int) ((i2 - i4) - translationY)));
        viewHolder2.itemView.setAlpha(0.0f);
        ArrayList arrayList = this.mPendingChanges;
        ChangeInfo changeInfo = new ChangeInfo();
        changeInfo.oldHolder = viewHolder;
        changeInfo.newHolder = viewHolder2;
        changeInfo.fromX = i3;
        changeInfo.fromY = i4;
        changeInfo.toX = i;
        changeInfo.toY = i2;
        arrayList.add(changeInfo);
        return true;
    }

    public final boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        ArrayList arrayList = this.mPendingMoves;
        MoveInfo moveInfo = new MoveInfo();
        moveInfo.holder = viewHolder;
        moveInfo.fromX = translationX;
        moveInfo.fromY = translationY;
        moveInfo.toX = i3;
        moveInfo.toY = i4;
        arrayList.add(moveInfo);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchAnimationFinished(androidx.recyclerview.widget.RecyclerView.ViewHolder r9) {
        /*
            r8 = this;
            androidx.recyclerview.widget.RecyclerView$5 r8 = r8.mListener
            if (r8 == 0) goto L94
            r0 = 1
            r9.setIsRecyclable(r0)
            androidx.recyclerview.widget.RecyclerView$ViewHolder r1 = r9.mShadowedHolder
            r2 = 0
            if (r1 == 0) goto L13
            androidx.recyclerview.widget.RecyclerView$ViewHolder r1 = r9.mShadowingHolder
            if (r1 != 0) goto L13
            r9.mShadowedHolder = r2
        L13:
            r9.mShadowingHolder = r2
            int r1 = r9.mFlags
            r1 = r1 & 16
            if (r1 == 0) goto L1d
            goto L94
        L1d:
            android.view.View r1 = r9.itemView
            androidx.recyclerview.widget.RecyclerView r8 = androidx.recyclerview.widget.RecyclerView.this
            r8.startInterceptRequestLayout()
            androidx.recyclerview.widget.ChildHelper r2 = r8.mChildHelper
            androidx.recyclerview.widget.ChildHelper$Bucket r3 = r2.mBucket
            androidx.recyclerview.widget.RecyclerView$5 r4 = r2.mCallback
            int r5 = r2.mRemoveStatus
            r6 = 0
            if (r5 != r0) goto L3d
            android.view.View r0 = r2.mViewInRemoveView
            if (r0 != r1) goto L35
        L33:
            r0 = r6
            goto L66
        L35:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Cannot call removeViewIfHidden within removeView(At) for a different view"
            r8.<init>(r9)
            throw r8
        L3d:
            r7 = 2
            if (r5 == r7) goto L8c
            r2.mRemoveStatus = r7     // Catch: java.lang.Throwable -> L51
            androidx.recyclerview.widget.RecyclerView r5 = androidx.recyclerview.widget.RecyclerView.this     // Catch: java.lang.Throwable -> L51
            int r5 = r5.indexOfChild(r1)     // Catch: java.lang.Throwable -> L51
            r7 = -1
            if (r5 != r7) goto L53
            r2.unhideViewInternal(r1)     // Catch: java.lang.Throwable -> L51
        L4e:
            r2.mRemoveStatus = r6
            goto L66
        L51:
            r8 = move-exception
            goto L89
        L53:
            boolean r7 = r3.get(r5)     // Catch: java.lang.Throwable -> L51
            if (r7 == 0) goto L63
            r3.remove(r5)     // Catch: java.lang.Throwable -> L51
            r2.unhideViewInternal(r1)     // Catch: java.lang.Throwable -> L51
            r4.removeViewAt(r5)     // Catch: java.lang.Throwable -> L51
            goto L4e
        L63:
            r2.mRemoveStatus = r6
            goto L33
        L66:
            if (r0 == 0) goto L76
            androidx.recyclerview.widget.RecyclerView$ViewHolder r1 = androidx.recyclerview.widget.RecyclerView.getChildViewHolderInt(r1)
            androidx.recyclerview.widget.RecyclerView$Recycler r2 = r8.mRecycler
            r2.unscrapView(r1)
            androidx.recyclerview.widget.RecyclerView$Recycler r2 = r8.mRecycler
            r2.recycleViewHolderInternal(r1)
        L76:
            r1 = r0 ^ 1
            r8.stopInterceptRequestLayout(r1)
            if (r0 != 0) goto L94
            boolean r0 = r9.isTmpDetached()
            if (r0 == 0) goto L94
            android.view.View r9 = r9.itemView
            r8.removeDetachedView(r9, r6)
            goto L94
        L89:
            r2.mRemoveStatus = r6
            throw r8
        L8c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Cannot call removeViewIfHidden within removeViewIfHidden"
            r8.<init>(r9)
            throw r8
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.DefaultItemAnimator.dispatchAnimationFinished(androidx.recyclerview.widget.RecyclerView$ViewHolder):void");
    }

    public final void dispatchFinishedWhenDone() {
        if (isRunning()) {
            return;
        }
        if (this.mFinishedListeners.size() <= 0) {
            this.mFinishedListeners.clear();
        } else {
            this.mFinishedListeners.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((MoveInfo) this.mPendingMoves.get(size)).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchAnimationFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.mChangesList.get(size2);
            endChangeAnimation(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAnimationFinished(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(viewHolder);
        this.mAddAnimations.remove(viewHolder);
        this.mChangeAnimations.remove(viewHolder);
        this.mMoveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    public final void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = (MoveInfo) this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        for (int size2 = this.mPendingRemovals.size() - 1; size2 >= 0; size2--) {
            dispatchAnimationFinished((RecyclerView.ViewHolder) this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        for (int size4 = this.mPendingChanges.size() - 1; size4 >= 0; size4--) {
            ChangeInfo changeInfo = (ChangeInfo) this.mPendingChanges.get(size4);
            RecyclerView.ViewHolder viewHolder2 = changeInfo.oldHolder;
            if (viewHolder2 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder2);
            }
            RecyclerView.ViewHolder viewHolder3 = changeInfo.newHolder;
            if (viewHolder3 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder3);
            }
        }
        this.mPendingChanges.clear();
        if (isRunning()) {
            for (int size5 = this.mMovesList.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.mMovesList.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchAnimationFinished(moveInfo2.holder);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.mMovesList.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.mAdditionsList.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.mAdditionsList.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder4 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder4.itemView.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder4);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.mAdditionsList.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.mChangesList.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.mChangesList.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    ChangeInfo changeInfo2 = (ChangeInfo) arrayList3.get(size10);
                    RecyclerView.ViewHolder viewHolder5 = changeInfo2.oldHolder;
                    if (viewHolder5 != null) {
                        endChangeAnimationIfNecessary(changeInfo2, viewHolder5);
                    }
                    RecyclerView.ViewHolder viewHolder6 = changeInfo2.newHolder;
                    if (viewHolder6 != null) {
                        endChangeAnimationIfNecessary(changeInfo2, viewHolder6);
                    }
                    if (arrayList3.isEmpty()) {
                        this.mChangesList.remove(arrayList3);
                    }
                }
            }
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            if (this.mFinishedListeners.size() > 0) {
                this.mFinishedListeners.get(0).getClass();
                throw new ClassCastException();
            }
            this.mFinishedListeners.clear();
        }
    }

    public final void endChangeAnimation(List list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }

    public final boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else {
            if (changeInfo.oldHolder != viewHolder) {
                return false;
            }
            changeInfo.oldHolder = null;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    public final boolean isRunning() {
        return (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) ? false : true;
    }

    public final void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }
}
