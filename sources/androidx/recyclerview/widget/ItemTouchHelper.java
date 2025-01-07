package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    public final Callback mCallback;
    public List mDistances;
    public long mDragScrollStartTimeInMs;
    public float mDx;
    public float mDy;
    public GestureDetector mGestureDetector;
    public float mInitialTouchX;
    public float mInitialTouchY;
    public ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    public float mMaxSwipeVelocity;
    public RecyclerView mRecyclerView;
    public int mSelectedFlags;
    public float mSelectedStartX;
    public float mSelectedStartY;
    public List mSwapTargets;
    public float mSwipeEscapeVelocity;
    public Rect mTmpRect;
    public VelocityTracker mVelocityTracker;
    public final List mPendingCleanup = new ArrayList();
    public final float[] mTmpPosition = new float[2];
    public RecyclerView.ViewHolder mSelected = null;
    public int mActivePointerId = -1;
    public int mActionState = 0;
    public final List mRecoverAnimations = new ArrayList();
    public final AnonymousClass1 mScrollRunnable = new AnonymousClass1();
    public View mOverdrawChild = null;
    public final AnonymousClass2 mOnItemTouchListener = new RecyclerView.OnItemTouchListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper.2
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public final boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int findPointerIndex;
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            itemTouchHelper.mGestureDetector.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            AnonymousClass3 anonymousClass3 = null;
            if (actionMasked == 0) {
                itemTouchHelper.mActivePointerId = motionEvent.getPointerId(0);
                itemTouchHelper.mInitialTouchX = motionEvent.getX();
                itemTouchHelper.mInitialTouchY = motionEvent.getY();
                VelocityTracker velocityTracker = itemTouchHelper.mVelocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                itemTouchHelper.mVelocityTracker = VelocityTracker.obtain();
                if (itemTouchHelper.mSelected == null) {
                    if (!itemTouchHelper.mRecoverAnimations.isEmpty()) {
                        View findChildView = itemTouchHelper.findChildView(motionEvent);
                        int size = ((ArrayList) itemTouchHelper.mRecoverAnimations).size() - 1;
                        while (true) {
                            if (size < 0) {
                                break;
                            }
                            AnonymousClass3 anonymousClass32 = (AnonymousClass3) ((ArrayList) itemTouchHelper.mRecoverAnimations).get(size);
                            if (anonymousClass32.mViewHolder.itemView == findChildView) {
                                anonymousClass3 = anonymousClass32;
                                break;
                            }
                            size--;
                        }
                    }
                    if (anonymousClass3 != null) {
                        itemTouchHelper.mInitialTouchX -= anonymousClass3.mX;
                        itemTouchHelper.mInitialTouchY -= anonymousClass3.mY;
                        itemTouchHelper.endRecoverAnimation(anonymousClass3.mViewHolder, true);
                        if (itemTouchHelper.mPendingCleanup.remove(anonymousClass3.mViewHolder.itemView)) {
                            itemTouchHelper.mCallback.clearView(itemTouchHelper.mRecyclerView, anonymousClass3.mViewHolder);
                        }
                        itemTouchHelper.select(anonymousClass3.mViewHolder, anonymousClass3.mActionState);
                        itemTouchHelper.updateDxDy(itemTouchHelper.mSelectedFlags, 0, motionEvent);
                    }
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                itemTouchHelper.mActivePointerId = -1;
                itemTouchHelper.select(null, 0);
            } else {
                int i = itemTouchHelper.mActivePointerId;
                if (i != -1 && (findPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                    itemTouchHelper.checkSelectForSwipe(actionMasked, findPointerIndex, motionEvent);
                }
            }
            VelocityTracker velocityTracker2 = itemTouchHelper.mVelocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            return itemTouchHelper.mSelected != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public final void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.select(null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public final void onTouchEvent(MotionEvent motionEvent) {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            itemTouchHelper.mGestureDetector.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = itemTouchHelper.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (itemTouchHelper.mActivePointerId == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(itemTouchHelper.mActivePointerId);
            if (findPointerIndex >= 0) {
                itemTouchHelper.checkSelectForSwipe(actionMasked, findPointerIndex, motionEvent);
            }
            RecyclerView.ViewHolder viewHolder = itemTouchHelper.mSelected;
            if (viewHolder == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        itemTouchHelper.updateDxDy(itemTouchHelper.mSelectedFlags, findPointerIndex, motionEvent);
                        itemTouchHelper.moveIfNecessary(viewHolder);
                        RecyclerView recyclerView = itemTouchHelper.mRecyclerView;
                        AnonymousClass1 anonymousClass1 = itemTouchHelper.mScrollRunnable;
                        recyclerView.removeCallbacks(anonymousClass1);
                        anonymousClass1.run();
                        itemTouchHelper.mRecyclerView.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == itemTouchHelper.mActivePointerId) {
                        itemTouchHelper.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        itemTouchHelper.updateDxDy(itemTouchHelper.mSelectedFlags, actionIndex, motionEvent);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = itemTouchHelper.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            itemTouchHelper.select(null, 0);
            itemTouchHelper.mActivePointerId = -1;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
        
            if (r11 < 0) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0073, code lost:
        
            if (r11 > 0) goto L23;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00da  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x010c  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 290
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.AnonymousClass1.run():void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean mShouldReactToLongPress = true;

        public ItemTouchHelperGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            View findChildView;
            RecyclerView.ViewHolder childViewHolder;
            if (!this.mShouldReactToLongPress || (findChildView = ItemTouchHelper.this.findChildView(motionEvent)) == null || (childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(findChildView)) == null) {
                return;
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if ((Callback.convertToAbsoluteDirection(itemTouchHelper.mCallback.getMovementFlags(childViewHolder), itemTouchHelper.mRecyclerView.getLayoutDirection()) & 16711680) != 0) {
                int pointerId = motionEvent.getPointerId(0);
                int i = ItemTouchHelper.this.mActivePointerId;
                if (pointerId == i) {
                    int findPointerIndex = motionEvent.findPointerIndex(i);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.mInitialTouchX = x;
                    itemTouchHelper2.mInitialTouchY = y;
                    itemTouchHelper2.mDy = 0.0f;
                    itemTouchHelper2.mDx = 0.0f;
                    itemTouchHelper2.mCallback.getClass();
                    ItemTouchHelper.this.select(childViewHolder, 2);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.recyclerview.widget.ItemTouchHelper$2] */
    public ItemTouchHelper(Callback callback) {
        this.mCallback = callback;
    }

    public static boolean hitTest(View view, float f, float f2, float f3, float f4) {
        return f >= f3 && f <= f3 + ((float) view.getWidth()) && f2 >= f4 && f2 <= f4 + ((float) view.getHeight());
    }

    public final void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        AnonymousClass2 anonymousClass2 = this.mOnItemTouchListener;
        if (recyclerView2 != null) {
            recyclerView2.removeItemDecoration(this);
            RecyclerView recyclerView3 = this.mRecyclerView;
            recyclerView3.mOnItemTouchListeners.remove(anonymousClass2);
            if (recyclerView3.mInterceptingOnItemTouchListener == anonymousClass2) {
                recyclerView3.mInterceptingOnItemTouchListener = null;
            }
            List list = this.mRecyclerView.mOnChildAttachStateListeners;
            if (list != null) {
                list.remove(this);
            }
            int size = ((ArrayList) this.mRecoverAnimations).size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                AnonymousClass3 anonymousClass3 = (AnonymousClass3) ((ArrayList) this.mRecoverAnimations).get(0);
                anonymousClass3.mValueAnimator.cancel();
                this.mCallback.clearView(this.mRecyclerView, anonymousClass3.mViewHolder);
            }
            this.mRecoverAnimations.clear();
            this.mOverdrawChild = null;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.mItemTouchHelperGestureListener;
            if (itemTouchHelperGestureListener != null) {
                itemTouchHelperGestureListener.mShouldReactToLongPress = false;
                this.mItemTouchHelperGestureListener = null;
            }
            if (this.mGestureDetector != null) {
                this.mGestureDetector = null;
            }
        }
        this.mRecyclerView = recyclerView;
        Resources resources = recyclerView.getResources();
        this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
        this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
        ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.mOnItemTouchListeners.add(anonymousClass2);
        RecyclerView recyclerView4 = this.mRecyclerView;
        if (recyclerView4.mOnChildAttachStateListeners == null) {
            recyclerView4.mOnChildAttachStateListeners = new ArrayList();
        }
        recyclerView4.mOnChildAttachStateListeners.add(this);
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
        this.mGestureDetector = new GestureDetector(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
    }

    public final int checkHorizontalSwipe(int i) {
        if ((i & 12) == 0) {
            return 0;
        }
        int i2 = this.mDx > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        Callback callback = this.mCallback;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            float f = this.mMaxSwipeVelocity;
            callback.getClass();
            velocityTracker.computeCurrentVelocity(1000, f);
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            int i3 = xVelocity > 0.0f ? 8 : 4;
            float abs = Math.abs(xVelocity);
            if ((i3 & i) != 0 && i2 == i3 && abs >= this.mSwipeEscapeVelocity && abs > Math.abs(yVelocity)) {
                return i3;
            }
        }
        float width = this.mRecyclerView.getWidth();
        callback.getClass();
        float f2 = width * 0.5f;
        if ((i & i2) == 0 || Math.abs(this.mDx) <= f2) {
            return 0;
        }
        return i2;
    }

    public final void checkSelectForSwipe(int i, int i2, MotionEvent motionEvent) {
        if (this.mSelected == null && i == 2 && this.mActionState != 2) {
            this.mCallback.getClass();
        }
    }

    public final int checkVerticalSwipe(int i) {
        if ((i & 3) == 0) {
            return 0;
        }
        int i2 = this.mDy > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        Callback callback = this.mCallback;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            float f = this.mMaxSwipeVelocity;
            callback.getClass();
            velocityTracker.computeCurrentVelocity(1000, f);
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            int i3 = yVelocity > 0.0f ? 2 : 1;
            float abs = Math.abs(yVelocity);
            if ((i3 & i) != 0 && i3 == i2 && abs >= this.mSwipeEscapeVelocity && abs > Math.abs(xVelocity)) {
                return i3;
            }
        }
        float height = this.mRecyclerView.getHeight();
        callback.getClass();
        float f2 = height * 0.5f;
        if ((i & i2) == 0 || Math.abs(this.mDy) <= f2) {
            return 0;
        }
        return i2;
    }

    public final void endRecoverAnimation(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = ((ArrayList) this.mRecoverAnimations).size() - 1; size >= 0; size--) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) ((ArrayList) this.mRecoverAnimations).get(size);
            if (anonymousClass3.mViewHolder == viewHolder) {
                anonymousClass3.mOverridden |= z;
                if (!anonymousClass3.mEnded) {
                    anonymousClass3.mValueAnimator.cancel();
                }
                this.mRecoverAnimations.remove(size);
                return;
            }
        }
    }

    public final View findChildView(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (hitTest(view, x, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return view;
            }
        }
        for (int size = ((ArrayList) this.mRecoverAnimations).size() - 1; size >= 0; size--) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) ((ArrayList) this.mRecoverAnimations).get(size);
            View view2 = anonymousClass3.mViewHolder.itemView;
            if (hitTest(view2, x, y, anonymousClass3.mX, anonymousClass3.mY)) {
                return view2;
            }
        }
        RecyclerView recyclerView = this.mRecyclerView;
        for (int childCount = recyclerView.mChildHelper.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = recyclerView.mChildHelper.getChildAt(childCount);
            float translationX = childAt.getTranslationX();
            float translationY = childAt.getTranslationY();
            if (x >= childAt.getLeft() + translationX && x <= childAt.getRight() + translationX && y >= childAt.getTop() + translationY && y <= childAt.getBottom() + translationY) {
                return childAt;
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    public final void getSelectedDxDy(float[] fArr) {
        if ((this.mSelectedFlags & 12) != 0) {
            fArr[0] = (this.mSelectedStartX + this.mDx) - this.mSelected.itemView.getLeft();
        } else {
            fArr[0] = this.mSelected.itemView.getTranslationX();
        }
        if ((this.mSelectedFlags & 3) != 0) {
            fArr[1] = (this.mSelectedStartY + this.mDy) - this.mSelected.itemView.getTop();
        } else {
            fArr[1] = this.mSelected.itemView.getTranslationY();
        }
    }

    public final void moveIfNecessary(RecyclerView.ViewHolder viewHolder) {
        ArrayList arrayList;
        int i;
        int bottom;
        int abs;
        int top;
        int abs2;
        int left;
        int abs3;
        int right;
        int abs4;
        int i2;
        int i3;
        int i4;
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            Callback callback = this.mCallback;
            callback.getClass();
            int i5 = (int) (this.mSelectedStartX + this.mDx);
            int i6 = (int) (this.mSelectedStartY + this.mDy);
            if (Math.abs(i6 - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * 0.5f || Math.abs(i5 - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * 0.5f) {
                List list = this.mSwapTargets;
                if (list == null) {
                    this.mSwapTargets = new ArrayList();
                    this.mDistances = new ArrayList();
                } else {
                    list.clear();
                    this.mDistances.clear();
                }
                int round = Math.round(this.mSelectedStartX + this.mDx);
                int round2 = Math.round(this.mSelectedStartY + this.mDy);
                int width = viewHolder.itemView.getWidth() + round;
                int height = viewHolder.itemView.getHeight() + round2;
                int i7 = (round + width) / 2;
                int i8 = (round2 + height) / 2;
                RecyclerView.LayoutManager layoutManager = this.mRecyclerView.mLayout;
                int childCount = layoutManager.getChildCount();
                int i9 = 0;
                while (i9 < childCount) {
                    View childAt = layoutManager.getChildAt(i9);
                    if (childAt != viewHolder.itemView && childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                        RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(childAt);
                        i2 = round;
                        if (callback.canDropOver(this.mSelected, childViewHolder)) {
                            int abs5 = Math.abs(i7 - ((childAt.getRight() + childAt.getLeft()) / 2));
                            int abs6 = Math.abs(i8 - ((childAt.getBottom() + childAt.getTop()) / 2));
                            int i10 = (abs6 * abs6) + (abs5 * abs5);
                            int size = ((ArrayList) this.mSwapTargets).size();
                            i3 = round2;
                            i4 = width;
                            int i11 = 0;
                            int i12 = 0;
                            while (i11 < size) {
                                int i13 = size;
                                if (i10 <= ((Integer) ((ArrayList) this.mDistances).get(i11)).intValue()) {
                                    break;
                                }
                                i12++;
                                i11++;
                                size = i13;
                            }
                            this.mSwapTargets.add(i12, childViewHolder);
                            this.mDistances.add(i12, Integer.valueOf(i10));
                            i9++;
                            round = i2;
                            round2 = i3;
                            width = i4;
                        }
                    } else {
                        i2 = round;
                    }
                    i3 = round2;
                    i4 = width;
                    i9++;
                    round = i2;
                    round2 = i3;
                    width = i4;
                }
                ArrayList arrayList2 = (ArrayList) this.mSwapTargets;
                if (arrayList2.size() == 0) {
                    return;
                }
                int width2 = viewHolder.itemView.getWidth() + i5;
                int height2 = viewHolder.itemView.getHeight() + i6;
                int left2 = i5 - viewHolder.itemView.getLeft();
                int top2 = i6 - viewHolder.itemView.getTop();
                int size2 = arrayList2.size();
                RecyclerView.ViewHolder viewHolder2 = null;
                int i14 = -1;
                int i15 = 0;
                while (i15 < size2) {
                    RecyclerView.ViewHolder viewHolder3 = (RecyclerView.ViewHolder) arrayList2.get(i15);
                    if (left2 <= 0 || (right = viewHolder3.itemView.getRight() - width2) >= 0) {
                        arrayList = arrayList2;
                        i = width2;
                    } else {
                        arrayList = arrayList2;
                        i = width2;
                        if (viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (abs4 = Math.abs(right)) > i14) {
                            i14 = abs4;
                            viewHolder2 = viewHolder3;
                        }
                    }
                    if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i5) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (abs3 = Math.abs(left)) > i14) {
                        i14 = abs3;
                        viewHolder2 = viewHolder3;
                    }
                    if (top2 < 0 && (top = viewHolder3.itemView.getTop() - i6) > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop() && (abs2 = Math.abs(top)) > i14) {
                        i14 = abs2;
                        viewHolder2 = viewHolder3;
                    }
                    if (top2 > 0 && (bottom = viewHolder3.itemView.getBottom() - height2) < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom() && (abs = Math.abs(bottom)) > i14) {
                        i14 = abs;
                        viewHolder2 = viewHolder3;
                    }
                    i15++;
                    arrayList2 = arrayList;
                    width2 = i;
                }
                if (viewHolder2 == null) {
                    this.mSwapTargets.clear();
                    this.mDistances.clear();
                    return;
                }
                int absoluteAdapterPosition = viewHolder2.getAbsoluteAdapterPosition();
                viewHolder.getAbsoluteAdapterPosition();
                if (callback.onMove(viewHolder, viewHolder2)) {
                    RecyclerView recyclerView = this.mRecyclerView;
                    RecyclerView.LayoutManager layoutManager2 = recyclerView.mLayout;
                    if (!(layoutManager2 instanceof LinearLayoutManager)) {
                        if (layoutManager2.canScrollHorizontally()) {
                            if (layoutManager2.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                                recyclerView.scrollToPosition(absoluteAdapterPosition);
                            }
                            if (layoutManager2.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                                recyclerView.scrollToPosition(absoluteAdapterPosition);
                            }
                        }
                        if (layoutManager2.canScrollVertically()) {
                            if (layoutManager2.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                                recyclerView.scrollToPosition(absoluteAdapterPosition);
                            }
                            if (layoutManager2.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                                recyclerView.scrollToPosition(absoluteAdapterPosition);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager2;
                    View view = viewHolder.itemView;
                    View view2 = viewHolder2.itemView;
                    linearLayoutManager.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
                    linearLayoutManager.ensureLayoutState();
                    linearLayoutManager.resolveShouldLayoutReverse();
                    int position = RecyclerView.LayoutManager.getPosition(view);
                    int position2 = RecyclerView.LayoutManager.getPosition(view2);
                    char c = position < position2 ? (char) 1 : (char) 65535;
                    if (linearLayoutManager.mShouldReverseLayout) {
                        if (c == 1) {
                            linearLayoutManager.scrollToPositionWithOffset(position2, linearLayoutManager.mOrientationHelper.getEndAfterPadding() - (linearLayoutManager.mOrientationHelper.getDecoratedMeasurement(view) + linearLayoutManager.mOrientationHelper.getDecoratedStart(view2)));
                            return;
                        } else {
                            linearLayoutManager.scrollToPositionWithOffset(position2, linearLayoutManager.mOrientationHelper.getEndAfterPadding() - linearLayoutManager.mOrientationHelper.getDecoratedEnd(view2));
                            return;
                        }
                    }
                    if (c == 65535) {
                        linearLayoutManager.scrollToPositionWithOffset(position2, linearLayoutManager.mOrientationHelper.getDecoratedStart(view2));
                    } else {
                        linearLayoutManager.scrollToPositionWithOffset(position2, linearLayoutManager.mOrientationHelper.getDecoratedEnd(view2) - linearLayoutManager.mOrientationHelper.getDecoratedMeasurement(view));
                    }
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public final void onChildViewDetachedFromWindow(View view) {
        removeChildDrawingOrderCallbackIfNecessary(view);
        RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null && childViewHolder == viewHolder) {
            select(null, 0);
            return;
        }
        endRecoverAnimation(childViewHolder, false);
        if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
            this.mCallback.clearView(this.mRecyclerView, childViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void onDraw(Canvas canvas, RecyclerView recyclerView) {
        float f;
        float f2;
        if (this.mSelected != null) {
            float[] fArr = this.mTmpPosition;
            getSelectedDxDy(fArr);
            f = fArr[0];
            f2 = fArr[1];
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        List list = this.mRecoverAnimations;
        this.mCallback.getClass();
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) arrayList.get(i);
            float f3 = anonymousClass3.mStartDx;
            float f4 = anonymousClass3.mTargetX;
            if (f3 == f4) {
                anonymousClass3.mX = anonymousClass3.mViewHolder.itemView.getTranslationX();
            } else {
                anonymousClass3.mX = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f4, f3, anonymousClass3.mFraction, f3);
            }
            float f5 = anonymousClass3.mStartDy;
            float f6 = anonymousClass3.mTargetY;
            if (f5 == f6) {
                anonymousClass3.mY = anonymousClass3.mViewHolder.itemView.getTranslationY();
            } else {
                anonymousClass3.mY = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f6, f5, anonymousClass3.mFraction, f5);
            }
            int save = canvas.save();
            Callback.onChildDraw(recyclerView, anonymousClass3.mViewHolder, anonymousClass3.mX, anonymousClass3.mY, false);
            canvas.restoreToCount(save);
        }
        if (viewHolder != null) {
            int save2 = canvas.save();
            Callback.onChildDraw(recyclerView, viewHolder, f, f2, true);
            canvas.restoreToCount(save2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        boolean z = false;
        if (this.mSelected != null) {
            float[] fArr = this.mTmpPosition;
            getSelectedDxDy(fArr);
            float f = fArr[0];
            float f2 = fArr[1];
        }
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        List list = this.mRecoverAnimations;
        this.mCallback.getClass();
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) arrayList.get(i);
            int save = canvas.save();
            View view = anonymousClass3.mViewHolder.itemView;
            canvas.restoreToCount(save);
        }
        if (viewHolder != null) {
            canvas.restoreToCount(canvas.save());
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            AnonymousClass3 anonymousClass32 = (AnonymousClass3) arrayList.get(i2);
            boolean z2 = anonymousClass32.mEnded;
            if (z2 && !anonymousClass32.mIsPendingCleanup) {
                arrayList.remove(i2);
            } else if (!z2) {
                z = true;
            }
        }
        if (z) {
            recyclerView.invalidate();
        }
    }

    public final void removeChildDrawingOrderCallbackIfNecessary(View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0090, code lost:
    
        if (r2 > 0) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void select(androidx.recyclerview.widget.RecyclerView.ViewHolder r22, int r23) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.select(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    public final void updateDxDy(int i, int i2, MotionEvent motionEvent) {
        float x = motionEvent.getX(i2);
        float y = motionEvent.getY(i2);
        float f = x - this.mInitialTouchX;
        this.mDx = f;
        this.mDy = y - this.mInitialTouchY;
        if ((i & 4) == 0) {
            this.mDx = Math.max(0.0f, f);
        }
        if ((i & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((i & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((i & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$3, reason: invalid class name */
    public final class AnonymousClass3 implements Animator.AnimatorListener {
        public final int mActionState;
        public float mFraction;
        public boolean mIsPendingCleanup;
        public final float mStartDx;
        public final float mStartDy;
        public final float mTargetX;
        public final float mTargetY;
        public final ValueAnimator mValueAnimator;
        public final RecyclerView.ViewHolder mViewHolder;
        public float mX;
        public float mY;
        public final /* synthetic */ RecyclerView.ViewHolder val$prevSelected;
        public final /* synthetic */ int val$swipeDir;
        public boolean mOverridden = false;
        public boolean mEnded = false;

        public AnonymousClass3(RecyclerView.ViewHolder viewHolder, int i, float f, float f2, float f3, float f4, int i2, RecyclerView.ViewHolder viewHolder2) {
            this.val$swipeDir = i2;
            this.val$prevSelected = viewHolder2;
            this.mActionState = i;
            this.mViewHolder = viewHolder;
            this.mStartDx = f;
            this.mStartDy = f2;
            this.mTargetX = f3;
            this.mTargetY = f4;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mValueAnimator = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.recyclerview.widget.ItemTouchHelper$RecoverAnimation$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ItemTouchHelper.AnonymousClass3.this.mFraction = valueAnimator.getAnimatedFraction();
                }
            });
            ofFloat.setTarget(viewHolder.itemView);
            ofFloat.addListener(this);
            this.mFraction = 0.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.mFraction = 1.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            onAnimationEnd$androidx$recyclerview$widget$ItemTouchHelper$RecoverAnimation(animator);
            if (this.mOverridden) {
                return;
            }
            if (this.val$swipeDir <= 0) {
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                itemTouchHelper.mCallback.clearView(itemTouchHelper.mRecyclerView, this.val$prevSelected);
            } else {
                ItemTouchHelper.this.mPendingCleanup.add(this.val$prevSelected.itemView);
                this.mIsPendingCleanup = true;
                int i = this.val$swipeDir;
                if (i > 0) {
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.mRecyclerView.post(new Runnable(this, i) { // from class: androidx.recyclerview.widget.ItemTouchHelper.4
                        public final /* synthetic */ AnonymousClass3 val$anim;

                        @Override // java.lang.Runnable
                        public final void run() {
                            RecyclerView recyclerView = ItemTouchHelper.this.mRecyclerView;
                            if (recyclerView == null || !recyclerView.mIsAttached) {
                                return;
                            }
                            AnonymousClass3 anonymousClass3 = this.val$anim;
                            if (anonymousClass3.mOverridden || anonymousClass3.mViewHolder.getAbsoluteAdapterPosition() == -1) {
                                return;
                            }
                            DefaultItemAnimator defaultItemAnimator = ItemTouchHelper.this.mRecyclerView.mItemAnimator;
                            if (defaultItemAnimator == null || !defaultItemAnimator.isRunning()) {
                                ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                                int size = ((ArrayList) itemTouchHelper3.mRecoverAnimations).size();
                                for (int i2 = 0; i2 < size; i2++) {
                                    if (((AnonymousClass3) ((ArrayList) itemTouchHelper3.mRecoverAnimations).get(i2)).mEnded) {
                                    }
                                }
                                ItemTouchHelper.this.mCallback.getClass();
                                return;
                            }
                            ItemTouchHelper.this.mRecyclerView.post(this);
                        }
                    });
                }
            }
            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
            View view = itemTouchHelper3.mOverdrawChild;
            View view2 = this.val$prevSelected.itemView;
            if (view == view2) {
                itemTouchHelper3.removeChildDrawingOrderCallbackIfNecessary(view2);
            }
        }

        public final void onAnimationEnd$androidx$recyclerview$widget$ItemTouchHelper$RecoverAnimation(Animator animator) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public final void onChildViewAttachedToWindow(View view) {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public static final AnonymousClass1 sDragScrollInterpolator = new AnonymousClass1(0);
        public static final AnonymousClass1 sDragViewScrollCapInterpolator = new AnonymousClass1(1);
        public int mCachedMaxScrollSpeed = -1;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.recyclerview.widget.ItemTouchHelper$Callback$1, reason: invalid class name */
        public final class AnonymousClass1 implements Interpolator {
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            @Override // android.animation.TimeInterpolator
            public final float getInterpolation(float f) {
                switch (this.$r8$classId) {
                    case 0:
                        return f * f * f * f * f;
                    default:
                        float f2 = f - 1.0f;
                        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
                }
            }
        }

        public static int convertToAbsoluteDirection(int i, int i2) {
            int i3;
            int i4 = i & 3158064;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (i6 & 3158064) >> 2;
            }
            return i5 | i3;
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3;
            int i4 = i & 789516;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & 789516) << 2;
            }
            return i5 | i3;
        }

        public static int makeMovementFlags(int i) {
            return i | (i << 16);
        }

        public static void onChildDraw(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, boolean z) {
            View view = viewHolder.itemView;
            if (z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                Float valueOf = Float.valueOf(ViewCompat.Api21Impl.getElevation(view));
                int childCount = recyclerView.getChildCount();
                float f3 = 0.0f;
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView.getChildAt(i);
                    if (childAt != view) {
                        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                        float elevation = ViewCompat.Api21Impl.getElevation(childAt);
                        if (elevation > f3) {
                            f3 = elevation;
                        }
                    }
                }
                ViewCompat.Api21Impl.setElevation(view, f3 + 1.0f);
                view.setTag(R.id.item_touch_helper_previous_elevation, valueOf);
            }
            view.setTranslationX(f);
            view.setTranslationY(f2);
        }

        public abstract boolean canDropOver(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
            if (tag instanceof Float) {
                float floatValue = ((Float) tag).floatValue();
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api21Impl.setElevation(view, floatValue);
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, null);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        public abstract int getMovementFlags(RecyclerView.ViewHolder viewHolder);

        public final int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, long j) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            int interpolation = (int) (sDragScrollInterpolator.getInterpolation(j <= 2000 ? j / 2000.0f : 1.0f) * ((int) (sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)) * ((int) Math.signum(i2)) * this.mCachedMaxScrollSpeed)));
            return interpolation == 0 ? i2 > 0 ? 1 : -1 : interpolation;
        }

        public abstract boolean onMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        }
    }
}
