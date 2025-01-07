package com.android.systemui.qs.customize;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSEditEvent;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileAdapter extends RecyclerView.Adapter {
    public final TileAdapterDelegate mAccessibilityDelegate;
    public int mAccessibilityFromIndex;
    public List mAllTiles;
    public final AnonymousClass5 mCallbacks;
    public final Context mContext;
    public Holder mCurrentDrag;
    public List mCurrentSpecs;
    public final TileItemDecoration mDecoration;
    public int mEditIndex;
    public int mFocusIndex;
    public final QSHost mHost;
    public final ItemTouchHelper mItemTouchHelper;
    public final MarginTileDecoration mMarginDecoration;
    public final int mMinNumTiles;
    public int mMinTileViewHeight;
    public boolean mNeedsFocus;
    public int mNumColumns;
    public List mOtherTiles;
    public RecyclerView mRecyclerView;
    public final AnonymousClass4 mSizeLookup;
    public final TextView mTempTextView;
    public int mTileDividerIndex;
    public final UiEventLogger mUiEventLogger;
    public final Handler mHandler = new Handler();
    public final List mTiles = new ArrayList();
    public int mAccessibilityAction = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Holder extends RecyclerView.ViewHolder {
        public final QSTileViewImpl mTileView;

        public Holder(View view) {
            super(view);
            if (view instanceof FrameLayout) {
                QSTileViewImpl qSTileViewImpl = (QSTileViewImpl) ((FrameLayout) view).getChildAt(0);
                this.mTileView = qSTileViewImpl;
                qSTileViewImpl.icon.mAnimationEnabled = false;
                qSTileViewImpl.setTag(this);
                ViewCompat.setAccessibilityDelegate(qSTileViewImpl, TileAdapter.this.mAccessibilityDelegate);
            }
        }

        public final void stopDrag() {
            this.itemView.animate().setDuration(100L).scaleX(1.0f).scaleY(1.0f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MarginTileDecoration extends RecyclerView.ItemDecoration {
        public int mHalfMargin;

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView.getLayoutManager() == null) {
                return;
            }
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            int i = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).mSpanIndex;
            if (view instanceof TextView) {
                super.getItemOffsets(rect, view, recyclerView, state);
                return;
            }
            if (i != 0 && i != gridLayoutManager.mSpanCount - 1) {
                int i2 = this.mHalfMargin;
                rect.left = i2;
                rect.right = i2;
            } else {
                if (recyclerView.isLayoutRtl()) {
                    if (i == 0) {
                        rect.left = this.mHalfMargin;
                        rect.right = 0;
                        return;
                    } else {
                        rect.left = 0;
                        rect.right = this.mHalfMargin;
                        return;
                    }
                }
                if (i == 0) {
                    rect.left = 0;
                    rect.right = this.mHalfMargin;
                } else {
                    rect.left = this.mHalfMargin;
                    rect.right = 0;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileItemDecoration extends RecyclerView.ItemDecoration {
        public final Drawable mDrawable;

        public TileItemDecoration(Context context) {
            this.mDrawable = context.getDrawable(R.drawable.qs_customize_tile_decoration);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void onDraw(Canvas canvas, RecyclerView recyclerView) {
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            int bottom = recyclerView.getBottom();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
                TileAdapter tileAdapter = TileAdapter.this;
                if (childViewHolder != tileAdapter.mCurrentDrag && childViewHolder.getBindingAdapterPosition() != 0 && (childViewHolder.getBindingAdapterPosition() >= tileAdapter.mEditIndex || (childAt instanceof TextView))) {
                    int top = childAt.getTop();
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    this.mDrawable.setBounds(0, Math.round(childAt.getTranslationY()) + top, width, bottom);
                    this.mDrawable.draw(canvas);
                    return;
                }
            }
        }
    }

    /* renamed from: -$$Nest$mselectPosition, reason: not valid java name */
    public static void m841$$Nest$mselectPosition(TileAdapter tileAdapter, int i) {
        if (tileAdapter.mAccessibilityAction == 1) {
            List list = tileAdapter.mTiles;
            int i2 = tileAdapter.mEditIndex;
            tileAdapter.mEditIndex = i2 - 1;
            list.remove(i2);
        }
        tileAdapter.mAccessibilityAction = 0;
        tileAdapter.move(tileAdapter.mAccessibilityFromIndex, i, false);
        tileAdapter.mFocusIndex = i;
        tileAdapter.mNeedsFocus = true;
        tileAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.recyclerview.widget.GridLayoutManager$SpanSizeLookup, com.android.systemui.qs.customize.TileAdapter$4] */
    public TileAdapter(Context context, QSHost qSHost, UiEventLogger uiEventLogger, FeatureFlags featureFlags) {
        ?? r0 = new GridLayoutManager.SpanSizeLookup() { // from class: com.android.systemui.qs.customize.TileAdapter.4
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public final int getSpanSize(int i) {
                TileAdapter tileAdapter = TileAdapter.this;
                int itemViewType = tileAdapter.getItemViewType(i);
                if (itemViewType == 1 || itemViewType == 4 || itemViewType == 3) {
                    return tileAdapter.mNumColumns;
                }
                return 1;
            }
        };
        this.mSizeLookup = r0;
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() { // from class: com.android.systemui.qs.customize.TileAdapter.5
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final boolean canDropOver(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int bindingAdapterPosition = viewHolder2.getBindingAdapterPosition();
                if (bindingAdapterPosition == 0 || bindingAdapterPosition == -1) {
                    return false;
                }
                TileAdapter tileAdapter = TileAdapter.this;
                if (!(((ArrayList) tileAdapter.mCurrentSpecs).size() > tileAdapter.mMinNumTiles)) {
                    int bindingAdapterPosition2 = viewHolder.getBindingAdapterPosition();
                    int i = tileAdapter.mEditIndex;
                    if (bindingAdapterPosition2 < i) {
                        return bindingAdapterPosition < i;
                    }
                }
                return bindingAdapterPosition <= tileAdapter.mEditIndex + 1;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                ((Holder) viewHolder).stopDrag();
                super.clearView(recyclerView, viewHolder);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final int getMovementFlags(RecyclerView.ViewHolder viewHolder) {
                int i = viewHolder.mItemViewType;
                return (i == 1 || i == 3 || i == 4) ? ItemTouchHelper.Callback.makeMovementFlags(0) : ItemTouchHelper.Callback.makeMovementFlags(15);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final boolean onMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                int bindingAdapterPosition2 = viewHolder2.getBindingAdapterPosition();
                if (bindingAdapterPosition == 0 || bindingAdapterPosition == -1 || bindingAdapterPosition2 == 0 || bindingAdapterPosition2 == -1) {
                    return false;
                }
                TileAdapter.this.move(bindingAdapterPosition, bindingAdapterPosition2, true);
                return true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public final void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
                if (i != 2) {
                    viewHolder = null;
                }
                TileAdapter tileAdapter = TileAdapter.this;
                Holder holder = tileAdapter.mCurrentDrag;
                if (viewHolder == holder) {
                    return;
                }
                if (holder != null) {
                    int bindingAdapterPosition = holder.getBindingAdapterPosition();
                    if (bindingAdapterPosition == -1) {
                        return;
                    }
                    TileQueryHelper.TileInfo tileInfo = (TileQueryHelper.TileInfo) ((ArrayList) tileAdapter.mTiles).get(bindingAdapterPosition);
                    CustomizeTileView customizeTileView = (CustomizeTileView) tileAdapter.mCurrentDrag.mTileView;
                    customizeTileView.showAppLabel = bindingAdapterPosition > tileAdapter.mEditIndex && !tileInfo.isSystem;
                    TextView textView = customizeTileView.secondaryLabel;
                    TextView textView2 = textView != null ? textView : null;
                    if (textView == null) {
                        textView = null;
                    }
                    textView2.setVisibility((!customizeTileView.showAppLabel || TextUtils.isEmpty(textView.getText())) ? 8 : 0);
                    tileAdapter.mCurrentDrag.stopDrag();
                    tileAdapter.mCurrentDrag = null;
                }
                if (viewHolder != null) {
                    Holder holder2 = (Holder) viewHolder;
                    tileAdapter.mCurrentDrag = holder2;
                    holder2.itemView.animate().setDuration(100L).scaleX(1.2f).scaleY(1.2f);
                }
                tileAdapter.mHandler.post(new Runnable() { // from class: com.android.systemui.qs.customize.TileAdapter.5.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TileAdapter tileAdapter2 = TileAdapter.this;
                        tileAdapter2.notifyItemChanged(tileAdapter2.mEditIndex);
                    }
                });
            }
        };
        this.mContext = context;
        this.mHost = qSHost;
        this.mUiEventLogger = uiEventLogger;
        this.mItemTouchHelper = new ItemTouchHelper(callback);
        this.mDecoration = new TileItemDecoration(context);
        this.mMarginDecoration = new MarginTileDecoration();
        this.mMinNumTiles = context.getResources().getInteger(R.integer.quick_settings_min_num_tiles);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlags.getClass();
        this.mNumColumns = context.getResources().getInteger(R.integer.quick_settings_num_columns);
        this.mAccessibilityDelegate = new TileAdapterDelegate();
        r0.mCacheSpanIndices = true;
        this.mTempTextView = new TextView(context);
        this.mMinTileViewHeight = context.getResources().getDimensionPixelSize(R.dimen.qs_tile_height);
    }

    public static String strip(TileQueryHelper.TileInfo tileInfo) {
        String str = tileInfo.spec;
        if (!str.startsWith("custom(")) {
            return str;
        }
        String substring = str.substring(7, str.length() - 1);
        if (substring.isEmpty()) {
            throw new IllegalArgumentException("Empty custom tile spec action");
        }
        return ComponentName.unflattenFromString(substring).getPackageName();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return ((ArrayList) this.mTiles).size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (i == 0) {
            return 3;
        }
        if (this.mAccessibilityAction == 1 && i == this.mEditIndex - 1) {
            return 2;
        }
        if (i == this.mTileDividerIndex) {
            return 4;
        }
        return ((ArrayList) this.mTiles).get(i) == null ? 1 : 0;
    }

    public final void move(int i, int i2, boolean z) {
        if (i2 == i) {
            return;
        }
        List list = this.mTiles;
        list.add(i2, list.remove(i));
        if (z) {
            this.mObservable.notifyItemMoved(i, i2);
        }
        updateDividerLocations();
        int i3 = this.mEditIndex;
        if (i2 >= i3) {
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_REMOVE, 0, strip((TileQueryHelper.TileInfo) ((ArrayList) this.mTiles).get(i2)));
        } else if (i >= i3) {
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_ADD, 0, strip((TileQueryHelper.TileInfo) ((ArrayList) this.mTiles).get(i2)));
        } else {
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_MOVE, 0, strip((TileQueryHelper.TileInfo) ((ArrayList) this.mTiles).get(i2)));
        }
        saveSpecs(this.mHost);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        String string;
        final Holder holder = (Holder) viewHolder;
        QSTileViewImpl qSTileViewImpl = holder.mTileView;
        if (qSTileViewImpl != null) {
            qSTileViewImpl.setMinimumHeight(this.mMinTileViewHeight);
        }
        int i3 = holder.mItemViewType;
        if (i3 == 3) {
            View view = holder.itemView;
            boolean z = this.mAccessibilityAction == 0;
            view.setFocusable(z);
            view.setImportantForAccessibility(z ? 1 : 4);
            view.setFocusableInTouchMode(z);
            return;
        }
        if (i3 == 4) {
            holder.itemView.setVisibility(this.mTileDividerIndex >= ((ArrayList) this.mTiles).size() - 1 ? 4 : 0);
            return;
        }
        if (i3 == 1) {
            Resources resources = this.mContext.getResources();
            if (this.mCurrentDrag == null) {
                string = resources.getString(R.string.drag_to_add_tiles);
            } else {
                int size = ((ArrayList) this.mCurrentSpecs).size();
                int i4 = this.mMinNumTiles;
                string = (size <= i4 && this.mCurrentDrag.getBindingAdapterPosition() < this.mEditIndex) ? resources.getString(R.string.drag_to_remove_disabled, Integer.valueOf(i4)) : resources.getString(R.string.drag_to_remove_tiles);
            }
            ((TextView) holder.itemView.findViewById(android.R.id.title)).setText(string);
            View view2 = holder.itemView;
            r0 = this.mAccessibilityAction == 0;
            view2.setFocusable(r0);
            view2.setImportantForAccessibility(r0 ? 1 : 4);
            view2.setFocusableInTouchMode(r0);
            return;
        }
        QSTileViewImpl qSTileViewImpl2 = holder.mTileView;
        if (i3 == 2) {
            qSTileViewImpl2.setClickable(true);
            qSTileViewImpl2.setFocusable(true);
            qSTileViewImpl2.setFocusableInTouchMode(true);
            qSTileViewImpl2.setVisibility(0);
            qSTileViewImpl2.setImportantForAccessibility(1);
            qSTileViewImpl2.setContentDescription(this.mContext.getString(R.string.accessibility_qs_edit_tile_add_to_position, Integer.valueOf(i)));
            final int i5 = r0 ? 1 : 0;
            qSTileViewImpl2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.qs.customize.TileAdapter.1
                public final /* synthetic */ TileAdapter this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    switch (i5) {
                        case 0:
                            TileAdapter.m841$$Nest$mselectPosition(this.this$0, holder.getLayoutPosition());
                            break;
                        default:
                            int layoutPosition = holder.getLayoutPosition();
                            if (layoutPosition != -1) {
                                TileAdapter tileAdapter = this.this$0;
                                if (tileAdapter.mAccessibilityAction != 0) {
                                    TileAdapter.m841$$Nest$mselectPosition(tileAdapter, layoutPosition);
                                    break;
                                }
                            }
                            break;
                    }
                }
            });
            if (this.mNeedsFocus) {
                holder.mTileView.requestLayout();
                holder.mTileView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.qs.customize.TileAdapter.3
                    @Override // android.view.View.OnLayoutChangeListener
                    public final void onLayoutChange(View view3, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
                        Holder.this.mTileView.removeOnLayoutChangeListener(this);
                        Holder.this.mTileView.requestAccessibilityFocus();
                    }
                });
                this.mNeedsFocus = false;
                this.mFocusIndex = -1;
                return;
            }
            return;
        }
        TileQueryHelper.TileInfo tileInfo = (TileQueryHelper.TileInfo) ((ArrayList) this.mTiles).get(i);
        boolean z2 = i > 0 && i < this.mEditIndex;
        if (z2 && this.mAccessibilityAction == 1) {
            tileInfo.state.contentDescription = this.mContext.getString(R.string.accessibility_qs_edit_tile_add_to_position, Integer.valueOf(i));
        } else if (z2 && this.mAccessibilityAction == 2) {
            tileInfo.state.contentDescription = this.mContext.getString(R.string.accessibility_qs_edit_tile_move_to_position, Integer.valueOf(i));
        } else if (z2 || !((i2 = this.mAccessibilityAction) == 2 || i2 == 1)) {
            QSTile.State state = tileInfo.state;
            state.contentDescription = state.label;
        } else {
            tileInfo.state.contentDescription = this.mContext.getString(R.string.accessibilit_qs_edit_tile_add_move_invalid_position);
        }
        tileInfo.state.expandedAccessibilityClassName = "";
        CustomizeTileView customizeTileView = (CustomizeTileView) qSTileViewImpl2;
        Objects.requireNonNull(customizeTileView, "The holder must have a tileView");
        customizeTileView.handleStateChanged(tileInfo.state);
        int i6 = this.mEditIndex;
        boolean z3 = tileInfo.isSystem;
        customizeTileView.showAppLabel = i > i6 && !z3;
        TextView textView = customizeTileView.secondaryLabel;
        TextView textView2 = textView != null ? textView : null;
        if (textView == null) {
            textView = null;
        }
        textView2.setVisibility((!customizeTileView.showAppLabel || TextUtils.isEmpty(textView.getText())) ? 8 : 0);
        boolean z4 = i < this.mEditIndex || z3;
        customizeTileView.showSideView = z4;
        if (!z4) {
            ViewGroup viewGroup = customizeTileView.sideView;
            if (viewGroup == null) {
                viewGroup = null;
            }
            viewGroup.setVisibility(8);
        }
        qSTileViewImpl2.setSelected(true);
        qSTileViewImpl2.setImportantForAccessibility(1);
        qSTileViewImpl2.setClickable(true);
        qSTileViewImpl2.setOnClickListener(null);
        qSTileViewImpl2.setFocusable(true);
        qSTileViewImpl2.setFocusableInTouchMode(true);
        qSTileViewImpl2.setAccessibilityTraversalBefore(-1);
        if (this.mAccessibilityAction != 0) {
            qSTileViewImpl2.setClickable(z2);
            qSTileViewImpl2.setFocusable(z2);
            qSTileViewImpl2.setFocusableInTouchMode(z2);
            if (z2) {
                qSTileViewImpl2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.qs.customize.TileAdapter.1
                    public final /* synthetic */ TileAdapter this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        switch (r3) {
                            case 0:
                                TileAdapter.m841$$Nest$mselectPosition(this.this$0, holder.getLayoutPosition());
                                break;
                            default:
                                int layoutPosition = holder.getLayoutPosition();
                                if (layoutPosition != -1) {
                                    TileAdapter tileAdapter = this.this$0;
                                    if (tileAdapter.mAccessibilityAction != 0) {
                                        TileAdapter.m841$$Nest$mselectPosition(tileAdapter, layoutPosition);
                                        break;
                                    }
                                }
                                break;
                        }
                    }
                });
            }
        }
        if (i == this.mFocusIndex && this.mNeedsFocus) {
            holder.mTileView.requestLayout();
            holder.mTileView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.qs.customize.TileAdapter.3
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view3, int i62, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
                    Holder.this.mTileView.removeOnLayoutChangeListener(this);
                    Holder.this.mTileView.requestAccessibilityFocus();
                }
            });
            this.mNeedsFocus = false;
            this.mFocusIndex = -1;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        Holder holder;
        int i2 = 0;
        Context context = viewGroup.getContext();
        LayoutInflater from = LayoutInflater.from(context);
        if (i != 3) {
            if (i == 4) {
                holder = new Holder(from.inflate(R.layout.qs_customize_tile_divider, viewGroup, false));
            } else {
                if (i != 1) {
                    FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.qs_customize_tile_frame, viewGroup, false);
                    frameLayout.setClipChildren(false);
                    CustomizeTileView customizeTileView = new CustomizeTileView(context, false, null);
                    customizeTileView.showSideView = true;
                    frameLayout.addView(customizeTileView);
                    return new Holder(frameLayout);
                }
                holder = new Holder(from.inflate(R.layout.qs_customize_divider, viewGroup, false));
            }
            return holder;
        }
        View inflate = from.inflate(R.layout.qs_customize_header, viewGroup, false);
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.style.QSCustomizeToolbar, com.android.internal.R.styleable.Toolbar);
        int resourceId = obtainStyledAttributes.getResourceId(27, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, android.R.styleable.View);
            i2 = obtainStyledAttributes2.getDimensionPixelSize(36, 0);
            obtainStyledAttributes2.recycle();
        }
        inflate.setMinimumHeight(((resources.getDimensionPixelSize(R.dimen.qs_brightness_margin_bottom) + (resources.getDimensionPixelSize(R.dimen.qs_brightness_margin_top) + (resources.getDimensionPixelSize(R.dimen.brightness_mirror_height) + resources.getDimensionPixelSize(R.dimen.qs_panel_padding_top)))) - i2) - resources.getDimensionPixelSize(R.dimen.qs_tile_margin_top_bottom));
        return new Holder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onDetachedFromRecyclerView() {
        this.mRecyclerView = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        Holder holder = (Holder) viewHolder;
        holder.stopDrag();
        holder.itemView.clearAnimation();
        holder.itemView.setScaleX(1.0f);
        holder.itemView.setScaleY(1.0f);
        return true;
    }

    public final void recalcSpecs() {
        TileQueryHelper.TileInfo tileInfo;
        if (this.mCurrentSpecs == null || this.mAllTiles == null) {
            return;
        }
        this.mOtherTiles = new ArrayList(this.mAllTiles);
        this.mTiles.clear();
        this.mTiles.add(null);
        int i = 0;
        for (int i2 = 0; i2 < ((ArrayList) this.mCurrentSpecs).size(); i2++) {
            String str = (String) ((ArrayList) this.mCurrentSpecs).get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= ((ArrayList) this.mOtherTiles).size()) {
                    tileInfo = null;
                    break;
                } else {
                    if (((TileQueryHelper.TileInfo) ((ArrayList) this.mOtherTiles).get(i3)).spec.equals(str)) {
                        tileInfo = (TileQueryHelper.TileInfo) this.mOtherTiles.remove(i3);
                        break;
                    }
                    i3++;
                }
            }
            if (tileInfo != null) {
                this.mTiles.add(tileInfo);
            }
        }
        this.mTiles.add(null);
        while (i < ((ArrayList) this.mOtherTiles).size()) {
            TileQueryHelper.TileInfo tileInfo2 = (TileQueryHelper.TileInfo) ((ArrayList) this.mOtherTiles).get(i);
            if (tileInfo2.isSystem) {
                this.mOtherTiles.remove(i);
                this.mTiles.add(tileInfo2);
                i--;
            }
            i++;
        }
        this.mTileDividerIndex = ((ArrayList) this.mTiles).size();
        this.mTiles.add(null);
        this.mTiles.addAll(this.mOtherTiles);
        updateDividerLocations();
        notifyDataSetChanged();
    }

    public final void saveSpecs(QSHost qSHost) {
        ArrayList arrayList = new ArrayList();
        this.mNeedsFocus = false;
        if (this.mAccessibilityAction == 1) {
            List list = this.mTiles;
            int i = this.mEditIndex - 1;
            this.mEditIndex = i;
            list.remove(i);
            notifyDataSetChanged();
        }
        this.mAccessibilityAction = 0;
        for (int i2 = 1; i2 < ((ArrayList) this.mTiles).size() && ((ArrayList) this.mTiles).get(i2) != null; i2++) {
            arrayList.add(((TileQueryHelper.TileInfo) ((ArrayList) this.mTiles).get(i2)).spec);
        }
        ((QSHostAdapter) qSHost).changeTilesByUser(arrayList);
        this.mCurrentSpecs = arrayList;
    }

    public final void updateDividerLocations() {
        this.mEditIndex = -1;
        this.mTileDividerIndex = ((ArrayList) this.mTiles).size();
        for (int i = 1; i < ((ArrayList) this.mTiles).size(); i++) {
            if (((ArrayList) this.mTiles).get(i) == null) {
                if (this.mEditIndex == -1) {
                    this.mEditIndex = i;
                } else {
                    this.mTileDividerIndex = i;
                }
            }
        }
        int size = ((ArrayList) this.mTiles).size() - 1;
        int i2 = this.mTileDividerIndex;
        if (size == i2) {
            notifyItemChanged(i2);
        }
    }
}
