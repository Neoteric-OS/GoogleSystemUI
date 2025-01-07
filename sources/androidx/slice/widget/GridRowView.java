package androidx.slice.widget;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.ArrayUtils;
import androidx.slice.CornerDrawable;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import androidx.slice.widget.GridContent;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda1;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class GridRowView extends SliceChildView implements View.OnClickListener, View.OnTouchListener {
    public final View mForeground;
    public GridContent mGridContent;
    public final int mGutter;
    public final int mIconSize;
    public final int mLargeImageHeight;
    public final int[] mLoc;
    public boolean mMaxCellUpdateScheduled;
    public int mMaxCells;
    public final AnonymousClass2 mMaxCellsUpdater;
    public int mRowCount;
    public int mRowIndex;
    public final int mSmallImageMinWidth;
    public final int mSmallImageSize;
    public final int mTextPadding;
    public final LinearLayout mViewContainer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DateSetListener implements DatePickerDialog.OnDateSetListener {
        public final SliceItem mActionItem;

        public DateSetListener(SliceItem sliceItem, int i) {
            this.mActionItem = sliceItem;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(i, i2, i3);
            Date time = calendar.getTime();
            SliceItem sliceItem = this.mActionItem;
            if (sliceItem != null) {
                try {
                    sliceItem.fireActionInternal(GridRowView.this.getContext(), new Intent().addFlags(268435456).putExtra("android.app.slice.extra.RANGE_VALUE", time.getTime()));
                    VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1 = GridRowView.this.mObserver;
                } catch (PendingIntent.CanceledException e) {
                    Log.e("GridRowView", "PendingIntent for slice cannot be sent", e);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TimeSetListener implements TimePickerDialog.OnTimeSetListener {
        public final SliceItem mActionItem;

        public TimeSetListener(SliceItem sliceItem, int i) {
            this.mActionItem = sliceItem;
        }

        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public final void onTimeSet(TimePicker timePicker, int i, int i2) {
            Date time = Calendar.getInstance().getTime();
            time.setHours(i);
            time.setMinutes(i2);
            SliceItem sliceItem = this.mActionItem;
            if (sliceItem != null) {
                try {
                    sliceItem.fireActionInternal(GridRowView.this.getContext(), new Intent().addFlags(268435456).putExtra("android.app.slice.extra.RANGE_VALUE", time.getTime()));
                    VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1 = GridRowView.this.mObserver;
                } catch (PendingIntent.CanceledException e) {
                    Log.e("GridRowView", "PendingIntent for slice cannot be sent", e);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.slice.widget.GridRowView$2] */
    public GridRowView(Context context, AttributeSet attributeSet) {
        super(context);
        this.mLoc = new int[2];
        this.mMaxCells = -1;
        this.mMaxCellsUpdater = new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.slice.widget.GridRowView.2
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                GridRowView gridRowView = GridRowView.this;
                gridRowView.mMaxCells = gridRowView.getMaxCells();
                GridRowView.this.populateViews();
                GridRowView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                GridRowView.this.mMaxCellUpdateScheduled = false;
                return true;
            }
        };
        Resources resources = getContext().getResources();
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.mViewContainer = linearLayout;
        linearLayout.setOrientation(0);
        addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        linearLayout.setGravity(16);
        this.mIconSize = resources.getDimensionPixelSize(R.dimen.abc_slice_icon_size);
        this.mSmallImageSize = resources.getDimensionPixelSize(R.dimen.abc_slice_small_image_size);
        this.mLargeImageHeight = resources.getDimensionPixelSize(R.dimen.abc_slice_grid_image_only_height);
        this.mSmallImageMinWidth = resources.getDimensionPixelSize(R.dimen.abc_slice_grid_image_min_width);
        this.mGutter = resources.getDimensionPixelSize(R.dimen.abc_slice_grid_gutter);
        this.mTextPadding = resources.getDimensionPixelSize(R.dimen.abc_slice_grid_text_padding);
        View view = new View(getContext());
        this.mForeground = view;
        addView(view, new FrameLayout.LayoutParams(-1, -1));
    }

    public final void addCell(GridContent.CellContent cellContent, int i, int i2) {
        SliceActionView sliceActionView;
        SliceItem sliceItem;
        int i3;
        int i4;
        int i5;
        int i6;
        IconCompat iconCompat;
        Drawable loadDrawable;
        LinearLayout.LayoutParams layoutParams;
        int i7;
        CharSequence sanitizedText;
        int subtitleColor;
        GridContent.CellContent cellContent2 = cellContent;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        ArrayList arrayList = cellContent2.mCellItems;
        SliceItem sliceItem2 = cellContent2.mContentIntent;
        SliceItem sliceItem3 = cellContent2.mPicker;
        SliceItem sliceItem4 = cellContent2.mToggleItem;
        boolean z = arrayList.size() == 1;
        int i8 = 0;
        SliceItem sliceItem5 = null;
        int i9 = 0;
        boolean z2 = false;
        int i10 = 0;
        while (i8 < arrayList.size()) {
            SliceItem sliceItem6 = (SliceItem) arrayList.get(i8);
            String str = sliceItem6.mFormat;
            ArrayList arrayList2 = arrayList;
            int determinePadding = determinePadding(sliceItem5);
            SliceItem sliceItem7 = sliceItem2;
            if (i9 >= 2) {
                sliceItem = sliceItem3;
                i3 = 1;
                i4 = i10;
            } else if ("text".equals(str) || "long".equals(str)) {
                String str2 = sliceItem6.mFormat;
                if ("text".equals(str2) || "long".equals(str2)) {
                    boolean hasAnyHints = SliceQuery.hasAnyHints(sliceItem6, "large", "title");
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(hasAnyHints ? R.layout.abc_slice_title : R.layout.abc_slice_secondary_text, (ViewGroup) null);
                    if (this.mSliceStyle != null && this.mRowStyle != null) {
                        textView.setTextSize(0, hasAnyHints ? r14.mGridTitleSize : r14.mGridSubtitleSize);
                        if (hasAnyHints) {
                            RowStyle rowStyle = this.mRowStyle;
                            Integer num = rowStyle.mTitleColor;
                            subtitleColor = num != null ? num.intValue() : rowStyle.mSliceStyle.mTitleColor;
                        } else {
                            subtitleColor = this.mRowStyle.getSubtitleColor();
                        }
                        textView.setTextColor(subtitleColor);
                    }
                    if ("long".equals(str2)) {
                        sliceItem = sliceItem3;
                        sanitizedText = SliceViewUtil.getTimestampString(getContext(), sliceItem6.getLong());
                    } else {
                        sliceItem = sliceItem3;
                        sanitizedText = sliceItem6.getSanitizedText();
                    }
                    textView.setText(sanitizedText);
                    linearLayout.addView(textView);
                    textView.setPadding(0, determinePadding, 0, 0);
                    i9++;
                    i5 = 1;
                    z2 = true;
                    sliceItem5 = sliceItem6;
                    i8 += i5;
                    cellContent2 = cellContent;
                    sliceItem3 = sliceItem;
                    arrayList = arrayList2;
                    sliceItem2 = sliceItem7;
                } else {
                    sliceItem = sliceItem3;
                    i4 = i10;
                    i5 = 1;
                    i6 = i9;
                    i9 = i6;
                    i10 = i4;
                    i8 += i5;
                    cellContent2 = cellContent;
                    sliceItem3 = sliceItem;
                    arrayList = arrayList2;
                    sliceItem2 = sliceItem7;
                }
            } else {
                sliceItem = sliceItem3;
                i4 = i10;
                i3 = 1;
            }
            if (i4 < i3) {
                if ("image".equals(sliceItem6.mFormat)) {
                    SliceItem sliceItem8 = cellContent2.mOverlayItem;
                    int i11 = this.mTintColor;
                    String str3 = sliceItem6.mFormat;
                    i6 = i9;
                    SliceStyle sliceStyle = this.mSliceStyle;
                    boolean z3 = sliceStyle != null && sliceStyle.mImageCornerRadius > 0.0f;
                    if ("image".equals(str3) && (iconCompat = (IconCompat) sliceItem6.mObj) != null && (loadDrawable = iconCompat.loadDrawable(getContext())) != null) {
                        ImageView imageView = new ImageView(getContext());
                        if (z3) {
                            imageView.setImageDrawable(new CornerDrawable(loadDrawable, this.mSliceStyle.mImageCornerRadius));
                        } else {
                            imageView.setImageDrawable(loadDrawable);
                        }
                        if (ArrayUtils.contains(sliceItem6.mHints, "raw")) {
                            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            layoutParams = new LinearLayout.LayoutParams(this.mGridContent.getFirstImageSize(getContext()).x, this.mGridContent.getFirstImageSize(getContext()).y);
                        } else if (ArrayUtils.contains(sliceItem6.mHints, "large")) {
                            imageView.setScaleType(z3 ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.CENTER_CROP);
                            layoutParams = new LinearLayout.LayoutParams(-1, z ? -1 : this.mLargeImageHeight);
                            i7 = -1;
                            if (i11 != i7 && !ArrayUtils.contains(sliceItem6.mHints, "no_tint")) {
                                imageView.setColorFilter(i11);
                            }
                            if (sliceItem8 != null || this.mViewContainer.getChildCount() == this.mMaxCells - 1) {
                                linearLayout.addView(imageView, layoutParams);
                            } else {
                                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.abc_slice_grid_text_overlay_image, (ViewGroup) linearLayout, false);
                                frameLayout.addView(imageView, 0, new FrameLayout.LayoutParams(-2, -2));
                                ((TextView) frameLayout.findViewById(R.id.text_overlay)).setText((CharSequence) sliceItem8.mObj);
                                frameLayout.findViewById(R.id.tint_overlay).setBackground(new CornerDrawable(getContext().getDrawable(R.drawable.abc_slice_gradient), this.mSliceStyle.mImageCornerRadius));
                                linearLayout.addView(frameLayout, layoutParams);
                            }
                            i5 = 1;
                            z2 = true;
                            sliceItem5 = sliceItem6;
                            i9 = i6;
                            i10 = i4 + 1;
                            i8 += i5;
                            cellContent2 = cellContent;
                            sliceItem3 = sliceItem;
                            arrayList = arrayList2;
                            sliceItem2 = sliceItem7;
                        } else {
                            boolean contains = ArrayUtils.contains(sliceItem6.mHints, "no_tint");
                            int i12 = contains ? this.mSmallImageSize : this.mIconSize;
                            imageView.setScaleType(!contains ? ImageView.ScaleType.CENTER_INSIDE : ImageView.ScaleType.CENTER_CROP);
                            layoutParams = new LinearLayout.LayoutParams(i12, i12);
                        }
                        i7 = -1;
                        if (i11 != i7) {
                            imageView.setColorFilter(i11);
                        }
                        if (sliceItem8 != null) {
                        }
                        linearLayout.addView(imageView, layoutParams);
                        i5 = 1;
                        z2 = true;
                        sliceItem5 = sliceItem6;
                        i9 = i6;
                        i10 = i4 + 1;
                        i8 += i5;
                        cellContent2 = cellContent;
                        sliceItem3 = sliceItem;
                        arrayList = arrayList2;
                        sliceItem2 = sliceItem7;
                    }
                } else {
                    i6 = i9;
                }
                i5 = 1;
                i9 = i6;
                i10 = i4;
                i8 += i5;
                cellContent2 = cellContent;
                sliceItem3 = sliceItem;
                arrayList = arrayList2;
                sliceItem2 = sliceItem7;
            } else {
                i5 = i3;
                i6 = i9;
                i9 = i6;
                i10 = i4;
                i8 += i5;
                cellContent2 = cellContent;
                sliceItem3 = sliceItem;
                arrayList = arrayList2;
                sliceItem2 = sliceItem7;
            }
        }
        SliceItem sliceItem9 = sliceItem2;
        SliceItem sliceItem10 = sliceItem3;
        if (sliceItem10 != null) {
            if ("date_picker".equals(sliceItem10.mSubType)) {
                z2 = addPickerItem(sliceItem10, linearLayout, determinePadding(sliceItem5), true);
            } else if ("time_picker".equals(sliceItem10.mSubType)) {
                z2 = addPickerItem(sliceItem10, linearLayout, determinePadding(sliceItem5), false);
            }
        }
        if (sliceItem4 != null) {
            SliceActionView sliceActionView2 = new SliceActionView(getContext(), this.mRowStyle);
            linearLayout.addView(sliceActionView2);
            sliceActionView = sliceActionView2;
            z2 = true;
        } else {
            sliceActionView = null;
        }
        if (z2) {
            SliceItem sliceItem11 = cellContent.mContentDescr;
            CharSequence charSequence = sliceItem11 != null ? (CharSequence) sliceItem11.mObj : null;
            if (charSequence != null) {
                linearLayout.setContentDescription(charSequence);
            }
            this.mViewContainer.addView(linearLayout, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (i != i2 - 1) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
                marginLayoutParams.setMarginEnd(this.mGutter);
                linearLayout.setLayoutParams(marginLayoutParams);
            }
            if (sliceItem9 != null) {
                EventInfo eventInfo = new EventInfo(2, 1, 1, this.mRowIndex);
                eventInfo.actionPosition = 2;
                eventInfo.actionIndex = i;
                eventInfo.actionCount = i2;
                linearLayout.setTag(new Pair(sliceItem9, eventInfo));
                linearLayout.setOnClickListener(this);
                linearLayout.setBackground(SliceViewUtil.getDrawable(android.R.attr.selectableItemBackgroundBorderless, getContext()));
                linearLayout.setClickable(true);
            }
            if (sliceItem4 != null) {
                EventInfo eventInfo2 = new EventInfo(2, 0, 3, this.mRowIndex);
                sliceActionView.setAction(new SliceActionImpl(sliceItem4), eventInfo2, this.mObserver, this.mTintColor, this.mLoadingListener);
                eventInfo2.actionPosition = 2;
                eventInfo2.actionIndex = i;
                eventInfo2.actionCount = i2;
            }
        }
    }

    public final boolean addPickerItem(final SliceItem sliceItem, ViewGroup viewGroup, int i, final boolean z) {
        SliceItem findSubtype = SliceQuery.findSubtype(sliceItem, "long", "millis");
        if (findSubtype == null) {
            return false;
        }
        long j = findSubtype.getLong();
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.abc_slice_title, (ViewGroup) null);
        if (this.mSliceStyle != null) {
            textView.setTextSize(0, r4.mGridTitleSize);
            textView.setTextColor(this.mSliceStyle.mTitleColor);
        }
        final Date date = new Date(j);
        SliceItem find = SliceQuery.find(sliceItem, "text", "title");
        if (find != null) {
            textView.setText((CharSequence) find.mObj);
        }
        final int i2 = this.mRowIndex;
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: androidx.slice.widget.GridRowView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if (z) {
                    new DatePickerDialog(GridRowView.this.getContext(), R.style.DialogTheme, GridRowView.this.new DateSetListener(sliceItem, i2), calendar.get(1), calendar.get(2), calendar.get(5)).show();
                } else {
                    new TimePickerDialog(GridRowView.this.getContext(), R.style.DialogTheme, GridRowView.this.new TimeSetListener(sliceItem, i2), calendar.get(11), calendar.get(12), false).show();
                }
            }
        });
        viewGroup.setClickable(true);
        viewGroup.setBackground(SliceViewUtil.getDrawable(android.R.attr.selectableItemBackgroundBorderless, getContext()));
        viewGroup.addView(textView);
        textView.setPadding(0, i, 0, 0);
        return true;
    }

    public final int determinePadding(SliceItem sliceItem) {
        SliceStyle sliceStyle;
        if (sliceItem == null) {
            return 0;
        }
        if ("image".equals(sliceItem.mFormat)) {
            return this.mTextPadding;
        }
        if (("text".equals(sliceItem.mFormat) || "long".equals(sliceItem.mFormat)) && (sliceStyle = this.mSliceStyle) != null) {
            return sliceStyle.mVerticalGridTextPadding;
        }
        return 0;
    }

    public final int getExtraBottomPadding() {
        SliceStyle sliceStyle;
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.mAllImages || this.mRowIndex != this.mRowCount - 1 || (sliceStyle = this.mSliceStyle) == null) {
            return 0;
        }
        return sliceStyle.mGridBottomPadding;
    }

    public final int getMaxCells() {
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid() || getWidth() == 0) {
            return -1;
        }
        if (this.mGridContent.mGridContent.size() <= 1) {
            return 1;
        }
        GridContent gridContent2 = this.mGridContent;
        int i = gridContent2.mLargestImageMode;
        return getWidth() / ((i != 2 ? i != 4 ? this.mSmallImageMinWidth : gridContent2.getFirstImageSize(getContext()).x : this.mLargeImageHeight) + this.mGutter);
    }

    public final void makeEntireGridClickable(boolean z) {
        this.mViewContainer.setOnTouchListener(z ? this : null);
        this.mViewContainer.setOnClickListener(z ? this : null);
        this.mForeground.setBackground(z ? SliceViewUtil.getDrawable(android.R.attr.selectableItemBackground, getContext()) : null);
        this.mViewContainer.setClickable(z);
        setClickable(z);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        SliceItem find;
        Pair pair = (Pair) view.getTag();
        SliceItem sliceItem = (SliceItem) pair.first;
        if (sliceItem == null || (find = SliceQuery.find(sliceItem, "action", (String) null)) == null) {
            return;
        }
        try {
            find.fireActionInternal(null, null);
        } catch (PendingIntent.CanceledException e) {
            Log.e("GridRowView", "PendingIntent for slice cannot be sent", e);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int height = this.mGridContent.getHeight(this.mSliceStyle, this.mViewPolicy) + this.mInsetTop + this.mInsetBottom;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
        this.mViewContainer.getLayoutParams().height = height;
        super.onMeasure(i, makeMeasureSpec);
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.mForeground.getLocationOnScreen(this.mLoc);
        this.mForeground.getBackground().setHotspot((int) (motionEvent.getRawX() - this.mLoc[0]), (int) (motionEvent.getRawY() - this.mLoc[1]));
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mForeground.setPressed(true);
        } else if (actionMasked == 3 || actionMasked == 1 || actionMasked == 2) {
            this.mForeground.setPressed(false);
        }
        return false;
    }

    public final void populateViews() {
        ViewGroup viewGroup;
        TextView textView;
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid()) {
            resetView();
            return;
        }
        if (scheduleMaxCellsUpdate()) {
            return;
        }
        if (this.mGridContent.getLayoutDir() != -1) {
            setLayoutDirection(this.mGridContent.getLayoutDir());
        }
        if (this.mGridContent.mPrimaryAction != null) {
            this.mViewContainer.setTag(new Pair(this.mGridContent.mPrimaryAction, new EventInfo(2, 3, 1, this.mRowIndex)));
            makeEntireGridClickable(true);
        }
        SliceItem sliceItem = this.mGridContent.mContentDescr;
        CharSequence charSequence = sliceItem != null ? (CharSequence) sliceItem.mObj : null;
        if (charSequence != null) {
            this.mViewContainer.setContentDescription(charSequence);
        }
        GridContent gridContent2 = this.mGridContent;
        ArrayList arrayList = gridContent2.mGridContent;
        int i = gridContent2.mLargestImageMode;
        if (i == 2 || i == 4) {
            this.mViewContainer.setGravity(48);
        } else {
            this.mViewContainer.setGravity(16);
        }
        int i2 = this.mMaxCells;
        boolean z = this.mGridContent.mSeeMoreItem != null;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (this.mViewContainer.getChildCount() >= i2) {
                int size = arrayList.size() - i2;
                if (z) {
                    LinearLayout linearLayout = this.mViewContainer;
                    View childAt = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
                    this.mViewContainer.removeView(childAt);
                    SliceItem sliceItem2 = this.mGridContent.mSeeMoreItem;
                    int childCount = this.mViewContainer.getChildCount();
                    int i4 = this.mMaxCells;
                    if (("slice".equals(sliceItem2.mFormat) || "action".equals(sliceItem2.mFormat)) && Arrays.asList(sliceItem2.getSlice().mItems).size() > 0) {
                        addCell(new GridContent.CellContent(sliceItem2), childCount, i4);
                        return;
                    }
                    LayoutInflater from = LayoutInflater.from(getContext());
                    if (this.mGridContent.mAllImages) {
                        viewGroup = (FrameLayout) from.inflate(R.layout.abc_slice_grid_see_more_overlay, (ViewGroup) this.mViewContainer, false);
                        viewGroup.addView(childAt, 0, new FrameLayout.LayoutParams(-1, -1));
                        textView = (TextView) viewGroup.findViewById(R.id.text_see_more_count);
                        viewGroup.findViewById(R.id.overlay_see_more).setBackground(new CornerDrawable(SliceViewUtil.getDrawable(android.R.attr.colorForeground, getContext()), this.mSliceStyle.mImageCornerRadius));
                    } else {
                        viewGroup = (LinearLayout) from.inflate(R.layout.abc_slice_grid_see_more, (ViewGroup) this.mViewContainer, false);
                        textView = (TextView) viewGroup.findViewById(R.id.text_see_more_count);
                        TextView textView2 = (TextView) viewGroup.findViewById(R.id.text_see_more);
                        if (this.mSliceStyle != null && this.mRowStyle != null) {
                            textView2.setTextSize(0, r12.mGridTitleSize);
                            RowStyle rowStyle = this.mRowStyle;
                            Integer num = rowStyle.mTitleColor;
                            textView2.setTextColor(num != null ? num.intValue() : rowStyle.mSliceStyle.mTitleColor);
                        }
                    }
                    this.mViewContainer.addView(viewGroup, new LinearLayout.LayoutParams(0, -1, 1.0f));
                    textView.setText(getResources().getString(R.string.abc_slice_more_content, Integer.valueOf(size)));
                    EventInfo eventInfo = new EventInfo(2, 4, 1, this.mRowIndex);
                    eventInfo.actionPosition = 2;
                    eventInfo.actionIndex = childCount;
                    eventInfo.actionCount = i4;
                    viewGroup.setTag(new Pair(sliceItem2, eventInfo));
                    viewGroup.setOnClickListener(this);
                    viewGroup.setBackground(SliceViewUtil.getDrawable(android.R.attr.selectableItemBackgroundBorderless, getContext()));
                    viewGroup.setClickable(true);
                    return;
                }
                return;
            }
            addCell((GridContent.CellContent) arrayList.get(i3), i3, Math.min(arrayList.size(), i2));
        }
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void resetView() {
        if (this.mMaxCellUpdateScheduled) {
            this.mMaxCellUpdateScheduled = false;
            getViewTreeObserver().removeOnPreDrawListener(this.mMaxCellsUpdater);
        }
        this.mViewContainer.removeAllViews();
        setLayoutDirection(2);
        makeEntireGridClickable(false);
    }

    public final boolean scheduleMaxCellsUpdate() {
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid()) {
            return true;
        }
        if (getWidth() != 0) {
            this.mMaxCells = getMaxCells();
            return false;
        }
        this.mMaxCellUpdateScheduled = true;
        getViewTreeObserver().addOnPreDrawListener(this.mMaxCellsUpdater);
        return true;
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setInsets(int i, int i2, int i3, int i4) {
        SliceStyle sliceStyle;
        super.setInsets(i, i2, i3, i4);
        LinearLayout linearLayout = this.mViewContainer;
        GridContent gridContent = this.mGridContent;
        int i5 = 0;
        if (gridContent != null && gridContent.mAllImages && this.mRowIndex == 0 && (sliceStyle = this.mSliceStyle) != null) {
            i5 = sliceStyle.mGridTopPadding;
        }
        linearLayout.setPadding(i, i5 + i2, i3, getExtraBottomPadding() + i4);
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setSliceItem(SliceContent sliceContent, boolean z, int i, int i2, VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1) {
        SliceStyle sliceStyle;
        resetView();
        this.mRowIndex = i;
        this.mRowCount = i2;
        this.mGridContent = (GridContent) sliceContent;
        if (!scheduleMaxCellsUpdate()) {
            populateViews();
        }
        LinearLayout linearLayout = this.mViewContainer;
        int i3 = this.mInsetStart;
        int i4 = this.mInsetTop;
        GridContent gridContent = this.mGridContent;
        int i5 = 0;
        if (gridContent != null && gridContent.mAllImages && this.mRowIndex == 0 && (sliceStyle = this.mSliceStyle) != null) {
            i5 = sliceStyle.mGridTopPadding;
        }
        linearLayout.setPadding(i3, i5 + i4, this.mInsetEnd, getExtraBottomPadding() + this.mInsetBottom);
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setTint(int i) {
        this.mTintColor = i;
        if (this.mGridContent != null) {
            resetView();
            populateViews();
        }
    }

    public GridRowView(Context context) {
        this(context, null);
    }
}
