package androidx.slice.widget;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TemplateView extends SliceChildView {
    public final SliceAdapter mAdapter;
    public List mDisplayedItems;
    public int mDisplayedItemsHeight;
    public final View mForeground;
    public ListContent mListContent;
    public final int[] mLoc;
    public SliceView mParent;
    public final RecyclerView mRecyclerView;

    public TemplateView(Context context) {
        super(context);
        this.mDisplayedItems = new ArrayList();
        this.mDisplayedItemsHeight = 0;
        this.mLoc = new int[2];
        RecyclerView recyclerView = new RecyclerView(getContext());
        this.mRecyclerView = recyclerView;
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        SliceAdapter sliceAdapter = new SliceAdapter(context);
        this.mAdapter = sliceAdapter;
        recyclerView.setAdapter(sliceAdapter);
        SliceAdapter sliceAdapter2 = new SliceAdapter(context);
        this.mAdapter = sliceAdapter2;
        recyclerView.setAdapter(sliceAdapter2);
        addView(recyclerView);
        View view = new View(getContext());
        this.mForeground = view;
        view.setBackground(SliceViewUtil.getDrawable(R.attr.selectableItemBackground, getContext()));
        addView(view);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        view.setLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        SliceView sliceView = (SliceView) getParent();
        this.mParent = sliceView;
        SliceAdapter sliceAdapter = this.mAdapter;
        sliceAdapter.mParent = sliceView;
        sliceAdapter.mTemplateView = this;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (!this.mViewPolicy.mScrollable && this.mDisplayedItems.size() > 0 && this.mDisplayedItemsHeight != size) {
            updateDisplayedItems(size);
        }
        super.onMeasure(i, i2);
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void resetView() {
        this.mDisplayedItemsHeight = 0;
        this.mDisplayedItems.clear();
        this.mAdapter.setSliceItems(null);
        this.mListContent = null;
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setAllowTwoLines(boolean z) {
        SliceAdapter sliceAdapter = this.mAdapter;
        sliceAdapter.mAllowTwoLines = z;
        sliceAdapter.notifyHeaderChanged();
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setInsets(int i, int i2, int i3, int i4) {
        super.setInsets(i, i2, i3, i4);
        SliceAdapter sliceAdapter = this.mAdapter;
        sliceAdapter.mInsetStart = i;
        sliceAdapter.mInsetTop = i2;
        sliceAdapter.mInsetEnd = i3;
        sliceAdapter.mInsetBottom = i4;
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setLastUpdated(long j) {
        this.mLastUpdated = j;
        SliceAdapter sliceAdapter = this.mAdapter;
        if (sliceAdapter.mLastUpdated != j) {
            sliceAdapter.mLastUpdated = j;
            sliceAdapter.notifyHeaderChanged();
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setLoadingActions(Set set) {
        SliceAdapter sliceAdapter = this.mAdapter;
        if (set == null) {
            sliceAdapter.mLoadingActions.clear();
        } else {
            sliceAdapter.mLoadingActions = set;
        }
        sliceAdapter.notifyDataSetChanged();
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setPolicy(SliceViewPolicy sliceViewPolicy) {
        this.mViewPolicy = sliceViewPolicy;
        this.mAdapter.mPolicy = sliceViewPolicy;
        sliceViewPolicy.mListener = this;
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setShowLastUpdated(boolean z) {
        this.mShowLastUpdated = z;
        SliceAdapter sliceAdapter = this.mAdapter;
        if (sliceAdapter.mShowLastUpdated != z) {
            sliceAdapter.mShowLastUpdated = z;
            sliceAdapter.notifyHeaderChanged();
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setSliceActionListener(VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1) {
        SliceAdapter sliceAdapter = this.mAdapter;
        if (sliceAdapter != null) {
            sliceAdapter.mSliceObserver = volumePanelDialog$$ExternalSyntheticLambda1;
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setSliceActions(List list) {
        SliceAdapter sliceAdapter = this.mAdapter;
        sliceAdapter.mSliceActions = list;
        sliceAdapter.notifyHeaderChanged();
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setSliceContent(ListContent listContent) {
        this.mListContent = listContent;
        updateDisplayedItems(listContent.getHeight(this.mSliceStyle, this.mViewPolicy));
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setStyle(SliceStyle sliceStyle, RowStyle rowStyle) {
        this.mSliceStyle = sliceStyle;
        this.mRowStyle = rowStyle;
        SliceAdapter sliceAdapter = this.mAdapter;
        sliceAdapter.mSliceStyle = sliceStyle;
        sliceAdapter.notifyDataSetChanged();
        if (rowStyle.mDisableRecyclerViewItemAnimator) {
            RecyclerView recyclerView = this.mRecyclerView;
            DefaultItemAnimator defaultItemAnimator = recyclerView.mItemAnimator;
            if (defaultItemAnimator != null) {
                defaultItemAnimator.endAnimations();
                recyclerView.mItemAnimator.mListener = null;
            }
            recyclerView.mItemAnimator = null;
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setTint(int i) {
        this.mTintColor = i;
        updateDisplayedItems(getMeasuredHeight());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List] */
    public final void updateDisplayedItems(int i) {
        DisplayedListItems displayedListItems;
        ListContent listContent = this.mListContent;
        if (listContent == null || !listContent.isValid()) {
            resetView();
            return;
        }
        ListContent listContent2 = this.mListContent;
        SliceStyle sliceStyle = this.mSliceStyle;
        SliceViewPolicy sliceViewPolicy = this.mViewPolicy;
        listContent2.getClass();
        sliceViewPolicy.getClass();
        if (sliceViewPolicy.mScrollable || i <= 0) {
            sliceStyle.getClass();
            ArrayList arrayList = listContent2.mRowItems;
            int size = arrayList.size();
            ArrayList arrayList2 = arrayList;
            if (size > 0) {
                boolean shouldSkipFirstListItem = sliceStyle.shouldSkipFirstListItem(arrayList);
                arrayList2 = arrayList;
                if (shouldSkipFirstListItem) {
                    arrayList2 = arrayList.subList(1, arrayList.size());
                }
            }
            displayedListItems = new DisplayedListItems(0, arrayList2);
        } else {
            displayedListItems = sliceStyle.getListItemsForNonScrollingList(listContent2, i, sliceViewPolicy);
        }
        List list = displayedListItems.mDisplayedItems;
        this.mDisplayedItems = list;
        this.mDisplayedItemsHeight = this.mSliceStyle.getListItemsHeight(list, this.mViewPolicy);
        SliceAdapter sliceAdapter = this.mAdapter;
        List list2 = this.mDisplayedItems;
        this.mViewPolicy.getClass();
        sliceAdapter.setSliceItems(list2);
        this.mRecyclerView.setOverScrollMode((this.mViewPolicy.mScrollable && (this.mDisplayedItemsHeight > getMeasuredHeight())) ? 1 : 2);
    }
}
