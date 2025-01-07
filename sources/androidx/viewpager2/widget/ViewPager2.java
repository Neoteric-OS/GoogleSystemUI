package androidx.viewpager2.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.R$styleable;
import androidx.viewpager2.widget.ScrollEventAdapter;
import com.android.systemui.controls.management.StructureAdapter;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {
    public PageAwareAccessibilityProvider mAccessibilityProvider;
    public int mCurrentItem;
    public final AnonymousClass1 mCurrentItemDataSetChangeObserver;
    public boolean mCurrentItemDirty;
    public final CompositeOnPageChangeCallback mExternalPageChangeCallbacks;
    public FakeDrag mFakeDragger;
    public LinearLayoutManagerImpl mLayoutManager;
    public final int mOffscreenPageLimit;
    public CompositeOnPageChangeCallback mPageChangeEventDispatcher;
    public PagerSnapHelperImpl mPagerSnapHelper;
    public Parcelable mPendingAdapterState;
    public int mPendingCurrentItem;
    public RecyclerViewImpl mRecyclerView;
    public ScrollEventAdapter mScrollEventAdapter;
    public final Rect mTmpChildRect;
    public final Rect mTmpContainerRect;
    public final boolean mUserInputEnabled;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.viewpager2.widget.ViewPager2$1, reason: invalid class name */
    public final class AnonymousClass1 extends RecyclerView.AdapterDataObserver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onChanged() {
            switch (this.$r8$classId) {
                case 0:
                    ViewPager2 viewPager2 = (ViewPager2) this.this$0;
                    viewPager2.mCurrentItemDirty = true;
                    viewPager2.mScrollEventAdapter.mDataSetChangeHappened = true;
                    break;
                default:
                    ((PageAwareAccessibilityProvider) this.this$0).updatePageAccessibilityActions();
                    break;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged() {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            onChanged();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LinearLayoutManagerImpl extends LinearLayoutManager {
        public LinearLayoutManagerImpl() {
            super(1);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public final void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
            int height;
            int paddingBottom;
            ViewPager2 viewPager2 = ViewPager2.this;
            int i = viewPager2.mOffscreenPageLimit;
            if (i == -1) {
                super.calculateExtraLayoutSpace(state, iArr);
                return;
            }
            RecyclerViewImpl recyclerViewImpl = viewPager2.mRecyclerView;
            if (viewPager2.getOrientation() == 0) {
                height = recyclerViewImpl.getWidth() - recyclerViewImpl.getPaddingLeft();
                paddingBottom = recyclerViewImpl.getPaddingRight();
            } else {
                height = recyclerViewImpl.getHeight() - recyclerViewImpl.getPaddingTop();
                paddingBottom = recyclerViewImpl.getPaddingBottom();
            }
            int i2 = (height - paddingBottom) * i;
            iArr[0] = i2;
            iArr[1] = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
            ViewPager2.this.mAccessibilityProvider.getClass();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            int i2;
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.getOrientation() == 1) {
                viewPager2.mLayoutManager.getClass();
                i = RecyclerView.LayoutManager.getPosition(view);
            } else {
                i = 0;
            }
            if (viewPager2.getOrientation() == 0) {
                viewPager2.mLayoutManager.getClass();
                i2 = RecyclerView.LayoutManager.getPosition(view);
            } else {
                i2 = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(false, i, 1, i2, 1));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int i, Bundle bundle) {
            ViewPager2.this.mAccessibilityProvider.getClass();
            return super.performAccessibilityAction(recycler, state, i, bundle);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PageAwareAccessibilityProvider {
        public final AnonymousClass1 mActionPageBackward;
        public final AnonymousClass1 mActionPageForward;
        public AnonymousClass1 mAdapterDataObserver;

        /* JADX WARN: Type inference failed for: r2v1, types: [androidx.viewpager2.widget.ViewPager2$PageAwareAccessibilityProvider$1] */
        /* JADX WARN: Type inference failed for: r2v2, types: [androidx.viewpager2.widget.ViewPager2$PageAwareAccessibilityProvider$1] */
        public PageAwareAccessibilityProvider() {
            final int i = 0;
            this.mActionPageForward = new AccessibilityViewCommand(this) { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.1
                public final /* synthetic */ PageAwareAccessibilityProvider this$1;

                {
                    this.this$1 = this;
                }

                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public final boolean perform(View view) {
                    switch (i) {
                        case 0:
                            int i2 = ((ViewPager2) view).mCurrentItem + 1;
                            ViewPager2 viewPager2 = ViewPager2.this;
                            if (viewPager2.mUserInputEnabled) {
                                viewPager2.setCurrentItemInternal(i2);
                                break;
                            }
                            break;
                        default:
                            int i3 = ((ViewPager2) view).mCurrentItem - 1;
                            ViewPager2 viewPager22 = ViewPager2.this;
                            if (viewPager22.mUserInputEnabled) {
                                viewPager22.setCurrentItemInternal(i3);
                                break;
                            }
                            break;
                    }
                    return true;
                }
            };
            final int i2 = 1;
            this.mActionPageBackward = new AccessibilityViewCommand(this) { // from class: androidx.viewpager2.widget.ViewPager2.PageAwareAccessibilityProvider.1
                public final /* synthetic */ PageAwareAccessibilityProvider this$1;

                {
                    this.this$1 = this;
                }

                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public final boolean perform(View view) {
                    switch (i2) {
                        case 0:
                            int i22 = ((ViewPager2) view).mCurrentItem + 1;
                            ViewPager2 viewPager2 = ViewPager2.this;
                            if (viewPager2.mUserInputEnabled) {
                                viewPager2.setCurrentItemInternal(i22);
                                break;
                            }
                            break;
                        default:
                            int i3 = ((ViewPager2) view).mCurrentItem - 1;
                            ViewPager2 viewPager22 = ViewPager2.this;
                            if (viewPager22.mUserInputEnabled) {
                                viewPager22.setCurrentItemInternal(i3);
                                break;
                            }
                            break;
                    }
                    return true;
                }
            };
        }

        public final void updatePageAccessibilityActions() {
            int itemCount;
            int i = R.id.accessibilityActionPageLeft;
            ViewPager2 viewPager2 = ViewPager2.this;
            ViewCompat.removeActionWithId(viewPager2, R.id.accessibilityActionPageLeft);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(viewPager2, 0);
            ViewCompat.removeActionWithId(viewPager2, R.id.accessibilityActionPageRight);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(viewPager2, 0);
            ViewCompat.removeActionWithId(viewPager2, R.id.accessibilityActionPageUp);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(viewPager2, 0);
            ViewCompat.removeActionWithId(viewPager2, R.id.accessibilityActionPageDown);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(viewPager2, 0);
            RecyclerView.Adapter adapter = viewPager2.mRecyclerView.mAdapter;
            if (adapter == null || (itemCount = adapter.getItemCount()) == 0 || !viewPager2.mUserInputEnabled) {
                return;
            }
            int orientation = viewPager2.getOrientation();
            AnonymousClass1 anonymousClass1 = this.mActionPageBackward;
            AnonymousClass1 anonymousClass12 = this.mActionPageForward;
            if (orientation != 0) {
                if (viewPager2.mCurrentItem < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageDown, (CharSequence) null), null, anonymousClass12);
                }
                if (viewPager2.mCurrentItem > 0) {
                    ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.accessibilityActionPageUp, (CharSequence) null), null, anonymousClass1);
                    return;
                }
                return;
            }
            boolean z = viewPager2.mLayoutManager.mRecyclerView.getLayoutDirection() == 1;
            int i2 = z ? 16908360 : 16908361;
            if (z) {
                i = 16908361;
            }
            if (viewPager2.mCurrentItem < itemCount - 1) {
                ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, (CharSequence) null), null, anonymousClass12);
            }
            if (viewPager2.mCurrentItem > 0) {
                ViewCompat.replaceAccessibilityAction(viewPager2, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i, (CharSequence) null), null, anonymousClass1);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PagerSnapHelperImpl extends PagerSnapHelper {
        public PagerSnapHelperImpl() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper
        public final View findSnapView(RecyclerView.LayoutManager layoutManager) {
            ScrollEventAdapter scrollEventAdapter = ViewPager2.this.mFakeDragger.mScrollEventAdapter;
            if (layoutManager.canScrollVertically()) {
                return PagerSnapHelper.findCenterView(layoutManager, getVerticalHelper(layoutManager));
            }
            if (layoutManager.canScrollHorizontally()) {
                return PagerSnapHelper.findCenterView(layoutManager, getHorizontalHelper(layoutManager));
            }
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RecyclerViewImpl extends RecyclerView {
        public RecyclerViewImpl(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public final CharSequence getAccessibilityClassName() {
            ViewPager2.this.mAccessibilityProvider.getClass();
            return "androidx.recyclerview.widget.RecyclerView";
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setFromIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setToIndex(ViewPager2.this.mCurrentItem);
            accessibilityEvent.setSource(ViewPager2.this);
            accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.mUserInputEnabled && super.onInterceptTouchEvent(motionEvent);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return ViewPager2.this.mUserInputEnabled && super.onTouchEvent(motionEvent);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmoothScrollToPosition implements Runnable {
        public final int mPosition;
        public final RecyclerViewImpl mRecyclerView;

        public SmoothScrollToPosition(int i, RecyclerViewImpl recyclerViewImpl) {
            this.mPosition = i;
            this.mRecyclerView = recyclerViewImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback();
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new AnonymousClass1(0, this);
        this.mPendingCurrentItem = -1;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, null);
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return this.mRecyclerView.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        return this.mRecyclerView.canScrollVertically(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i = ((SavedState) parcelable).mRecyclerViewId;
            sparseArray.put(this.mRecyclerView.getId(), (Parcelable) sparseArray.get(i));
            sparseArray.remove(i);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        restorePendingState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        this.mAccessibilityProvider.getClass();
        this.mAccessibilityProvider.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public final int getOrientation() {
        return this.mLayoutManager.mOrientation == 1 ? 1 : 0;
    }

    public final void initialize(Context context, AttributeSet attributeSet) {
        final int i = 1;
        final int i2 = 0;
        this.mAccessibilityProvider = new PageAwareAccessibilityProvider();
        RecyclerViewImpl recyclerViewImpl = new RecyclerViewImpl(context);
        this.mRecyclerView = recyclerViewImpl;
        recyclerViewImpl.setId(View.generateViewId());
        this.mRecyclerView.setDescendantFocusability(131072);
        LinearLayoutManagerImpl linearLayoutManagerImpl = new LinearLayoutManagerImpl();
        this.mLayoutManager = linearLayoutManagerImpl;
        this.mRecyclerView.setLayoutManager(linearLayoutManagerImpl);
        RecyclerViewImpl recyclerViewImpl2 = this.mRecyclerView;
        recyclerViewImpl2.mTouchSlop = ViewConfiguration.get(recyclerViewImpl2.getContext()).getScaledPagingTouchSlop();
        int[] iArr = R$styleable.ViewPager2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api29Impl.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, 0, 0);
        try {
            this.mLayoutManager.setOrientation(obtainStyledAttributes.getInt(0, 0));
            this.mAccessibilityProvider.updatePageAccessibilityActions();
            obtainStyledAttributes.recycle();
            this.mRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            RecyclerViewImpl recyclerViewImpl3 = this.mRecyclerView;
            AnonymousClass4 anonymousClass4 = new AnonymousClass4();
            if (recyclerViewImpl3.mOnChildAttachStateListeners == null) {
                recyclerViewImpl3.mOnChildAttachStateListeners = new ArrayList();
            }
            recyclerViewImpl3.mOnChildAttachStateListeners.add(anonymousClass4);
            ScrollEventAdapter scrollEventAdapter = new ScrollEventAdapter(this);
            this.mScrollEventAdapter = scrollEventAdapter;
            this.mFakeDragger = new FakeDrag(scrollEventAdapter);
            PagerSnapHelperImpl pagerSnapHelperImpl = new PagerSnapHelperImpl();
            this.mPagerSnapHelper = pagerSnapHelperImpl;
            pagerSnapHelperImpl.attachToRecyclerView(this.mRecyclerView);
            this.mRecyclerView.addOnScrollListener(this.mScrollEventAdapter);
            this.mRecyclerView.setOverScrollMode(getOverScrollMode());
            CompositeOnPageChangeCallback compositeOnPageChangeCallback = new CompositeOnPageChangeCallback();
            this.mPageChangeEventDispatcher = compositeOnPageChangeCallback;
            this.mScrollEventAdapter.mCallback = compositeOnPageChangeCallback;
            OnPageChangeCallback onPageChangeCallback = new OnPageChangeCallback(this) { // from class: androidx.viewpager2.widget.ViewPager2.2
                public final /* synthetic */ ViewPager2 this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageScrollStateChanged(int i3) {
                    switch (i2) {
                        case 0:
                            if (i3 == 0) {
                                this.this$0.updateCurrentItem();
                                break;
                            }
                            break;
                    }
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public final void onPageSelected(int i3) {
                    switch (i2) {
                        case 0:
                            ViewPager2 viewPager2 = this.this$0;
                            if (viewPager2.mCurrentItem != i3) {
                                viewPager2.mCurrentItem = i3;
                                viewPager2.mAccessibilityProvider.updatePageAccessibilityActions();
                                break;
                            }
                            break;
                        default:
                            ViewPager2 viewPager22 = this.this$0;
                            viewPager22.clearFocus();
                            if (viewPager22.hasFocus()) {
                                viewPager22.mRecyclerView.requestFocus(2);
                                break;
                            }
                            break;
                    }
                }
            };
            OnPageChangeCallback onPageChangeCallback2 = new OnPageChangeCallback(this) { // from class: androidx.viewpager2.widget.ViewPager2.2
                public final /* synthetic */ ViewPager2 this$0;

                {
                    this.this$0 = this;
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageScrollStateChanged(int i3) {
                    switch (i) {
                        case 0:
                            if (i3 == 0) {
                                this.this$0.updateCurrentItem();
                                break;
                            }
                            break;
                    }
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public final void onPageSelected(int i3) {
                    switch (i) {
                        case 0:
                            ViewPager2 viewPager2 = this.this$0;
                            if (viewPager2.mCurrentItem != i3) {
                                viewPager2.mCurrentItem = i3;
                                viewPager2.mAccessibilityProvider.updatePageAccessibilityActions();
                                break;
                            }
                            break;
                        default:
                            ViewPager2 viewPager22 = this.this$0;
                            viewPager22.clearFocus();
                            if (viewPager22.hasFocus()) {
                                viewPager22.mRecyclerView.requestFocus(2);
                                break;
                            }
                            break;
                    }
                }
            };
            compositeOnPageChangeCallback.mCallbacks.add(onPageChangeCallback);
            this.mPageChangeEventDispatcher.mCallbacks.add(onPageChangeCallback2);
            PageAwareAccessibilityProvider pageAwareAccessibilityProvider = this.mAccessibilityProvider;
            RecyclerViewImpl recyclerViewImpl4 = this.mRecyclerView;
            pageAwareAccessibilityProvider.getClass();
            recyclerViewImpl4.setImportantForAccessibility(2);
            pageAwareAccessibilityProvider.mAdapterDataObserver = new AnonymousClass1(i, pageAwareAccessibilityProvider);
            ViewPager2 viewPager2 = ViewPager2.this;
            if (viewPager2.getImportantForAccessibility() == 0) {
                viewPager2.setImportantForAccessibility(1);
            }
            CompositeOnPageChangeCallback compositeOnPageChangeCallback2 = this.mPageChangeEventDispatcher;
            compositeOnPageChangeCallback2.mCallbacks.add(this.mExternalPageChangeCallbacks);
            this.mPageChangeEventDispatcher.mCallbacks.add(new PageTransformerAdapter(this.mLayoutManager));
            RecyclerViewImpl recyclerViewImpl5 = this.mRecyclerView;
            attachViewToParent(recyclerViewImpl5, 0, recyclerViewImpl5.getLayoutParams());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        int i2;
        int itemCount;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        ViewPager2 viewPager2 = ViewPager2.this;
        if (viewPager2.mRecyclerView.mAdapter == null) {
            i = 0;
            i2 = 0;
        } else if (viewPager2.getOrientation() == 1) {
            i = viewPager2.mRecyclerView.mAdapter.getItemCount();
            i2 = 1;
        } else {
            i2 = viewPager2.mRecyclerView.mAdapter.getItemCount();
            i = 1;
        }
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, false, 0));
        RecyclerView.Adapter adapter = viewPager2.mRecyclerView.mAdapter;
        if (adapter == null || (itemCount = adapter.getItemCount()) == 0 || !viewPager2.mUserInputEnabled) {
            return;
        }
        if (viewPager2.mCurrentItem > 0) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (viewPager2.mCurrentItem < itemCount - 1) {
            accessibilityNodeInfo.addAction(4096);
        }
        accessibilityNodeInfo.setScrollable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        this.mTmpContainerRect.left = getPaddingLeft();
        this.mTmpContainerRect.right = (i3 - i) - getPaddingRight();
        this.mTmpContainerRect.top = getPaddingTop();
        this.mTmpContainerRect.bottom = (i4 - i2) - getPaddingBottom();
        Gravity.apply(8388659, measuredWidth, measuredHeight, this.mTmpContainerRect, this.mTmpChildRect);
        RecyclerViewImpl recyclerViewImpl = this.mRecyclerView;
        Rect rect = this.mTmpChildRect;
        recyclerViewImpl.layout(rect.left, rect.top, rect.right, rect.bottom);
        if (this.mCurrentItemDirty) {
            updateCurrentItem();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        measureChild(this.mRecyclerView, i, i2);
        int measuredWidth = this.mRecyclerView.getMeasuredWidth();
        int measuredHeight = this.mRecyclerView.getMeasuredHeight();
        int measuredState = this.mRecyclerView.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + measuredHeight;
        setMeasuredDimension(ViewGroup.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i, measuredState), ViewGroup.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, measuredState << 16));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mPendingCurrentItem = savedState.mCurrentItem;
        this.mPendingAdapterState = savedState.mAdapterState;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mRecyclerViewId = this.mRecyclerView.getId();
        int i = this.mPendingCurrentItem;
        if (i == -1) {
            i = this.mCurrentItem;
        }
        savedState.mCurrentItem = i;
        Parcelable parcelable = this.mPendingAdapterState;
        if (parcelable != null) {
            savedState.mAdapterState = parcelable;
        } else {
            RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        this.mAccessibilityProvider.getClass();
        if (i != 8192 && i != 4096) {
            return super.performAccessibilityAction(i, bundle);
        }
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider = this.mAccessibilityProvider;
        pageAwareAccessibilityProvider.getClass();
        if (i != 8192 && i != 4096) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = ViewPager2.this;
        int i2 = i == 8192 ? viewPager2.mCurrentItem - 1 : viewPager2.mCurrentItem + 1;
        if (viewPager2.mUserInputEnabled) {
            viewPager2.setCurrentItemInternal(i2);
        }
        return true;
    }

    public final void restorePendingState() {
        RecyclerView.Adapter adapter;
        int i = this.mPendingCurrentItem;
        if (i == -1 || (adapter = this.mRecyclerView.mAdapter) == null) {
            return;
        }
        if (this.mPendingAdapterState != null) {
            this.mPendingAdapterState = null;
        }
        int max = Math.max(0, Math.min(i, adapter.getItemCount() - 1));
        this.mCurrentItem = max;
        this.mPendingCurrentItem = -1;
        this.mRecyclerView.scrollToPosition(max);
        this.mAccessibilityProvider.updatePageAccessibilityActions();
    }

    public final void setAdapter(StructureAdapter structureAdapter) {
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider = this.mAccessibilityProvider;
        if (adapter != null) {
            adapter.mObservable.unregisterObserver(pageAwareAccessibilityProvider.mAdapterDataObserver);
        } else {
            pageAwareAccessibilityProvider.getClass();
        }
        if (adapter != null) {
            adapter.mObservable.unregisterObserver(this.mCurrentItemDataSetChangeObserver);
        }
        this.mRecyclerView.setAdapter(structureAdapter);
        this.mCurrentItem = 0;
        restorePendingState();
        PageAwareAccessibilityProvider pageAwareAccessibilityProvider2 = this.mAccessibilityProvider;
        pageAwareAccessibilityProvider2.updatePageAccessibilityActions();
        AnonymousClass1 anonymousClass1 = pageAwareAccessibilityProvider2.mAdapterDataObserver;
        RecyclerView.AdapterDataObservable adapterDataObservable = structureAdapter.mObservable;
        adapterDataObservable.registerObserver(anonymousClass1);
        adapterDataObservable.registerObserver(this.mCurrentItemDataSetChangeObserver);
    }

    public final void setCurrentItemInternal(int i) {
        CompositeOnPageChangeCallback compositeOnPageChangeCallback;
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        if (adapter == null) {
            if (this.mPendingCurrentItem != -1) {
                this.mPendingCurrentItem = Math.max(i, 0);
                return;
            }
            return;
        }
        if (adapter.getItemCount() <= 0) {
            return;
        }
        int min = Math.min(Math.max(i, 0), adapter.getItemCount() - 1);
        int i2 = this.mCurrentItem;
        if ((min == i2 && this.mScrollEventAdapter.mScrollState == 0) || min == i2) {
            return;
        }
        double d = i2;
        this.mCurrentItem = min;
        this.mAccessibilityProvider.updatePageAccessibilityActions();
        ScrollEventAdapter scrollEventAdapter = this.mScrollEventAdapter;
        if (scrollEventAdapter.mScrollState != 0) {
            scrollEventAdapter.updateScrollEventValues();
            ScrollEventAdapter.ScrollEventValues scrollEventValues = scrollEventAdapter.mScrollValues;
            d = scrollEventValues.mPosition + scrollEventValues.mOffset;
        }
        ScrollEventAdapter scrollEventAdapter2 = this.mScrollEventAdapter;
        scrollEventAdapter2.getClass();
        scrollEventAdapter2.mAdapterState = 2;
        boolean z = scrollEventAdapter2.mTarget != min;
        scrollEventAdapter2.mTarget = min;
        scrollEventAdapter2.dispatchStateChanged(2);
        if (z && (compositeOnPageChangeCallback = scrollEventAdapter2.mCallback) != null) {
            compositeOnPageChangeCallback.onPageSelected(min);
        }
        double d2 = min;
        if (Math.abs(d2 - d) <= 3.0d) {
            this.mRecyclerView.smoothScrollToPosition(min);
            return;
        }
        this.mRecyclerView.scrollToPosition(d2 > d ? min - 3 : min + 3);
        RecyclerViewImpl recyclerViewImpl = this.mRecyclerView;
        recyclerViewImpl.post(new SmoothScrollToPosition(min, recyclerViewImpl));
    }

    @Override // android.view.View
    public final void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        this.mAccessibilityProvider.updatePageAccessibilityActions();
    }

    @Override // android.view.View
    public final void setOverScrollMode(int i) {
        RecyclerViewImpl recyclerViewImpl = this.mRecyclerView;
        if (recyclerViewImpl != null) {
            recyclerViewImpl.setOverScrollMode(i);
        }
        super.setOverScrollMode(i);
    }

    public final void updateCurrentItem() {
        PagerSnapHelperImpl pagerSnapHelperImpl = this.mPagerSnapHelper;
        if (pagerSnapHelperImpl == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View findSnapView = pagerSnapHelperImpl.findSnapView(this.mLayoutManager);
        if (findSnapView == null) {
            return;
        }
        this.mLayoutManager.getClass();
        int position = RecyclerView.LayoutManager.getPosition(findSnapView);
        if (position != this.mCurrentItem && this.mScrollEventAdapter.mScrollState == 0) {
            this.mPageChangeEventDispatcher.onPageSelected(position);
        }
        this.mCurrentItemDirty = false;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public Parcelable mAdapterState;
        public int mCurrentItem;
        public int mRecyclerViewId;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mRecyclerViewId);
            parcel.writeInt(this.mCurrentItem);
            parcel.writeParcelable(this.mAdapterState, i);
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.viewpager2.widget.ViewPager2$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState(parcel, null);
                savedState.mRecyclerViewId = parcel.readInt();
                savedState.mCurrentItem = parcel.readInt();
                savedState.mAdapterState = parcel.readParcelable(null);
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState(parcel, classLoader);
                savedState.mRecyclerViewId = parcel.readInt();
                savedState.mCurrentItem = parcel.readInt();
                savedState.mAdapterState = parcel.readParcelable(classLoader);
                return savedState;
            }
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback();
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new AnonymousClass1(0, this);
        this.mPendingCurrentItem = -1;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback();
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new AnonymousClass1(0, this);
        this.mPendingCurrentItem = -1;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.viewpager2.widget.ViewPager2$4, reason: invalid class name */
    public final class AnonymousClass4 implements RecyclerView.OnChildAttachStateChangeListener {
        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public final void onChildViewAttachedToWindow(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (((ViewGroup.MarginLayoutParams) layoutParams).width != -1 || ((ViewGroup.MarginLayoutParams) layoutParams).height != -1) {
                throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public final void onChildViewDetachedFromWindow(View view) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class OnPageChangeCallback {
        public abstract void onPageSelected(int i);

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTmpContainerRect = new Rect();
        this.mTmpChildRect = new Rect();
        this.mExternalPageChangeCallbacks = new CompositeOnPageChangeCallback();
        this.mCurrentItemDirty = false;
        this.mCurrentItemDataSetChangeObserver = new AnonymousClass1(0, this);
        this.mPendingCurrentItem = -1;
        this.mUserInputEnabled = true;
        this.mOffscreenPageLimit = -1;
        initialize(context, attributeSet);
    }
}
