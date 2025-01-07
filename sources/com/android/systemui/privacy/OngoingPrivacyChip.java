package com.android.systemui.privacy;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OngoingPrivacyChip extends FrameLayout implements BackgroundAnimatableView {
    public final Configuration configuration;
    public int iconColor;
    public int iconMargin;
    public int iconSize;
    public final LinearLayout iconsContainer;
    public List privacyList;

    public OngoingPrivacyChip(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration != null) {
            int diff = configuration.diff(this.configuration);
            this.configuration.setTo(configuration);
            if ((1073745920 & diff) != 0) {
                updateResources();
            }
        }
    }

    @Override // com.android.systemui.statusbar.events.BackgroundAnimatableView
    public final void setBoundsForAnimation(int i, int i2, int i3, int i4) {
        this.iconsContainer.setLeftTopRightBottom(i - getLeft(), i2 - getTop(), i3 - getLeft(), i4 - getTop());
    }

    public final void setPrivacyList(List list) {
        this.privacyList = list;
        PrivacyChipBuilder privacyChipBuilder = new PrivacyChipBuilder(getContext(), this.privacyList);
        if (this.privacyList.isEmpty()) {
            this.iconsContainer.removeAllViews();
        } else {
            setContentDescription(getContext().getString(R.string.ongoing_privacy_chip_content_multiple_apps, privacyChipBuilder.joinTypes()));
            LinearLayout linearLayout = this.iconsContainer;
            linearLayout.removeAllViews();
            List list2 = privacyChipBuilder.types;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(((PrivacyType) it.next()).getIcon(privacyChipBuilder.context));
            }
            int i = 0;
            for (Object obj : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                Drawable drawable = (Drawable) obj;
                drawable.mutate();
                drawable.setTint(this.iconColor);
                ImageView imageView = new ImageView(getContext());
                imageView.setImageDrawable(drawable);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                int i3 = this.iconSize;
                linearLayout.addView(imageView, i3, i3);
                if (i != 0) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                    marginLayoutParams.setMarginStart(this.iconMargin);
                    imageView.setLayoutParams(marginLayoutParams);
                }
                i = i2;
            }
        }
        requestLayout();
    }

    public final void updateResources() {
        this.iconMargin = getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_appops_chip_icon_margin);
        this.iconSize = getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_appops_chip_icon_size);
        this.iconColor = Utils.getColorAttrDefaultColor(android.R.attr.colorPrimary, 0, getContext());
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_appops_chip_height);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_appops_chip_side_padding);
        this.iconsContainer.getLayoutParams().height = dimensionPixelSize;
        this.iconsContainer.setPaddingRelative(dimensionPixelSize2, 0, dimensionPixelSize2, 0);
        this.iconsContainer.setBackground(getContext().getDrawable(R.drawable.statusbar_privacy_chip_bg));
    }

    public OngoingPrivacyChip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public /* synthetic */ OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public OngoingPrivacyChip(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.privacyList = EmptyList.INSTANCE;
        FrameLayout.inflate(context, R.layout.ongoing_privacy_chip, this);
        setId(R.id.privacy_chip);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388629));
        setClipChildren(true);
        setClipToPadding(true);
        this.iconsContainer = (LinearLayout) requireViewById(R.id.icons_container);
        this.configuration = new Configuration(context.getResources().getConfiguration());
        updateResources();
    }
}
