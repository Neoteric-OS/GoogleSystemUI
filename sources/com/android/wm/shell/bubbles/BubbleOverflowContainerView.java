package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ContrastColorUtil;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubbleOverflowContainerView extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public BubbleOverflowAdapter mAdapter;
    public final AnonymousClass1 mDataListener;
    public LinearLayout mEmptyState;
    public ImageView mEmptyStateImage;
    public TextView mEmptyStateSubtitle;
    public TextView mEmptyStateTitle;
    public BubbleExpandedViewManager mExpandedViewManager;
    public int mHorizontalMargin;
    public final BubbleOverflowContainerView$$ExternalSyntheticLambda0 mKeyListener;
    public final List mOverflowBubbles;
    public BubblePositioner mPositioner;
    public RecyclerView mRecyclerView;
    public int mVerticalMargin;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleOverflowContainerView$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OverflowGridLayoutManager extends GridLayoutManager {
        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
            int itemCount = state.getItemCount();
            int columnCountForAccessibility = super.getColumnCountForAccessibility(recycler, state);
            return itemCount < columnCountForAccessibility ? itemCount : columnCountForAccessibility;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OverflowItemDecoration extends RecyclerView.ItemDecoration {
        public OverflowItemDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            BubbleOverflowContainerView bubbleOverflowContainerView = BubbleOverflowContainerView.this;
            int i = bubbleOverflowContainerView.mHorizontalMargin;
            rect.left = i;
            int i2 = bubbleOverflowContainerView.mVerticalMargin;
            rect.top = i2;
            rect.right = i;
            rect.bottom = i2;
        }
    }

    public BubbleOverflowContainerView(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        BubbleExpandedViewManager bubbleExpandedViewManager = this.mExpandedViewManager;
        if (bubbleExpandedViewManager != null) {
            ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager).$controller.updateWindowFlagsForBackpress(true);
        }
        setOnKeyListener(this.mKeyListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BubbleExpandedViewManager bubbleExpandedViewManager = this.mExpandedViewManager;
        if (bubbleExpandedViewManager != null) {
            ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleExpandedViewManager).$controller.updateWindowFlagsForBackpress(false);
        }
        setOnKeyListener(null);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.bubble_overflow_recycler);
        this.mEmptyState = (LinearLayout) findViewById(R.id.bubble_overflow_empty_state);
        this.mEmptyStateTitle = (TextView) findViewById(R.id.bubble_overflow_empty_title);
        this.mEmptyStateSubtitle = (TextView) findViewById(R.id.bubble_overflow_empty_subtitle);
        this.mEmptyStateImage = (ImageView) findViewById(R.id.bubble_overflow_empty_state_image);
    }

    public final void show() {
        requestFocus();
        Resources resources = getResources();
        int round = Math.round(getWidth() / resources.getDimension(R.dimen.bubble_name_width));
        if (round <= 0) {
            round = resources.getInteger(R.integer.bubbles_overflow_columns);
        }
        RecyclerView recyclerView = this.mRecyclerView;
        getContext();
        recyclerView.setLayoutManager(new OverflowGridLayoutManager(round));
        if (this.mRecyclerView.mItemDecorations.size() == 0) {
            this.mRecyclerView.addItemDecoration(new OverflowItemDecoration());
        }
        Context context = getContext();
        List list = this.mOverflowBubbles;
        BubbleExpandedViewManager bubbleExpandedViewManager = this.mExpandedViewManager;
        Objects.requireNonNull(bubbleExpandedViewManager);
        BubbleOverflowContainerView$$ExternalSyntheticLambda1 bubbleOverflowContainerView$$ExternalSyntheticLambda1 = new BubbleOverflowContainerView$$ExternalSyntheticLambda1(bubbleExpandedViewManager);
        BubblePositioner bubblePositioner = this.mPositioner;
        BubbleOverflowAdapter bubbleOverflowAdapter = new BubbleOverflowAdapter();
        bubbleOverflowAdapter.mContext = context;
        bubbleOverflowAdapter.mBubbles = list;
        bubbleOverflowAdapter.mPromoteBubbleFromOverflow = bubbleOverflowContainerView$$ExternalSyntheticLambda1;
        bubbleOverflowAdapter.mPositioner = bubblePositioner;
        this.mAdapter = bubbleOverflowAdapter;
        this.mRecyclerView.setAdapter(bubbleOverflowAdapter);
        this.mOverflowBubbles.clear();
        this.mOverflowBubbles.addAll(((BubbleExpandedViewManager$Companion$fromBubbleController$1) this.mExpandedViewManager).$controller.mBubbleData.getOverflowBubbles());
        this.mAdapter.notifyDataSetChanged();
        ((BubbleExpandedViewManager$Companion$fromBubbleController$1) this.mExpandedViewManager).$controller.mOverflowListener = this.mDataListener;
        updateEmptyStateVisibility();
        Resources resources2 = getResources();
        boolean z = (resources2.getConfiguration().uiMode & 48) == 32;
        this.mHorizontalMargin = resources2.getDimensionPixelSize(R.dimen.bubble_overflow_item_padding_horizontal);
        this.mVerticalMargin = resources2.getDimensionPixelSize(R.dimen.bubble_overflow_item_padding_vertical);
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.invalidateItemDecorations();
        }
        this.mEmptyStateImage.setImageDrawable(z ? resources2.getDrawable(R.drawable.bubble_ic_empty_overflow_dark) : resources2.getDrawable(R.drawable.bubble_ic_empty_overflow_light));
        findViewById(R.id.bubble_overflow_container).setBackgroundColor(z ? resources2.getColor(R.color.bubbles_dark) : resources2.getColor(R.color.bubbles_light));
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorSurfaceBright, android.R.^attr-private.materialColorOnSurface});
        int color = obtainStyledAttributes.getColor(0, z ? -16777216 : -1);
        int ensureTextContrast = ContrastColorUtil.ensureTextContrast(obtainStyledAttributes.getColor(1, z ? -1 : -16777216), color, z);
        obtainStyledAttributes.recycle();
        setBackgroundColor(color);
        this.mEmptyStateTitle.setTextColor(ensureTextContrast);
        this.mEmptyStateSubtitle.setTextColor(ensureTextContrast);
    }

    public final void updateEmptyStateVisibility() {
        this.mEmptyState.setVisibility(this.mOverflowBubbles.isEmpty() ? 0 : 8);
        this.mRecyclerView.setVisibility(this.mOverflowBubbles.isEmpty() ? 8 : 0);
    }

    public final void updateFontSize() {
        float dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(android.R.dimen.text_size_menu_material);
        this.mEmptyStateTitle.setTextSize(0, dimensionPixelSize);
        this.mEmptyStateSubtitle.setTextSize(0, dimensionPixelSize);
    }

    public final void updateLocale() {
        this.mEmptyStateTitle.setText(((LinearLayout) this).mContext.getString(R.string.bubble_overflow_empty_title));
        this.mEmptyStateSubtitle.setText(((LinearLayout) this).mContext.getString(R.string.bubble_overflow_empty_subtitle));
    }

    public BubbleOverflowContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleOverflowContainerView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.wm.shell.bubbles.BubbleOverflowContainerView$$ExternalSyntheticLambda0] */
    public BubbleOverflowContainerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mOverflowBubbles = new ArrayList();
        this.mKeyListener = new View.OnKeyListener() { // from class: com.android.wm.shell.bubbles.BubbleOverflowContainerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i3, KeyEvent keyEvent) {
                BubbleOverflowContainerView bubbleOverflowContainerView = BubbleOverflowContainerView.this;
                int i4 = BubbleOverflowContainerView.$r8$clinit;
                if (keyEvent.getAction() != 1 || keyEvent.getKeyCode() != 4) {
                    return false;
                }
                ((BubbleExpandedViewManager$Companion$fromBubbleController$1) bubbleOverflowContainerView.mExpandedViewManager).$controller.collapseStack();
                return true;
            }
        };
        this.mDataListener = new AnonymousClass1();
        setFocusableInTouchMode(true);
    }
}
