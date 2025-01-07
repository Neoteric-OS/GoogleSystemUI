package com.android.systemui.controls.management;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Icon;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.systemui.controls.ControlInterface;
import com.android.systemui.controls.ui.CanUseIconPredicate;
import com.android.systemui.controls.ui.RenderInfo;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlHolder extends Holder {
    public final ControlHolderAccessibilityDelegate accessibilityDelegate;
    public final CanUseIconPredicate canUseIconPredicate;
    public final CheckBox favorite;
    public final Function2 favoriteCallback;
    public final String favoriteStateDescription;
    public final ImageView icon;
    public final FavoritesModel$moveHelper$1 moveHelper;
    public final String notFavoriteStateDescription;
    public final TextView removed;
    public final TextView subtitle;
    public final TextView title;

    public ControlHolder(View view, int i, FavoritesModel$moveHelper$1 favoritesModel$moveHelper$1, Function2 function2) {
        super(view);
        this.moveHelper = favoritesModel$moveHelper$1;
        this.favoriteCallback = function2;
        this.favoriteStateDescription = view.getContext().getString(R.string.accessibility_control_favorite);
        this.notFavoriteStateDescription = view.getContext().getString(R.string.accessibility_control_not_favorite);
        this.icon = (ImageView) view.requireViewById(R.id.icon);
        this.title = (TextView) view.requireViewById(R.id.title);
        this.subtitle = (TextView) view.requireViewById(R.id.subtitle);
        this.removed = (TextView) view.requireViewById(R.id.status);
        CheckBox checkBox = (CheckBox) view.requireViewById(R.id.favorite);
        checkBox.setVisibility(0);
        this.favorite = checkBox;
        this.canUseIconPredicate = new CanUseIconPredicate(i);
        ControlHolderAccessibilityDelegate controlHolderAccessibilityDelegate = new ControlHolderAccessibilityDelegate(new ControlHolder$accessibilityDelegate$1(1, this, ControlHolder.class, "stateDescription", "stateDescription(Z)Ljava/lang/CharSequence;", 0), new ControlHolder$accessibilityDelegate$2(0, this, ControlHolder.class, "getLayoutPosition", "getLayoutPosition()I", 0), favoritesModel$moveHelper$1);
        this.accessibilityDelegate = controlHolderAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(view, controlHolderAccessibilityDelegate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.controls.management.Holder
    public final void bindData(final ElementWrapper elementWrapper) {
        ControlInterface controlInterface = (ControlInterface) elementWrapper;
        ComponentName component = controlInterface.getComponent();
        int deviceType = controlInterface.getDeviceType();
        RenderInfo.Companion companion = RenderInfo.Companion;
        RenderInfo lookup = RenderInfo.Companion.lookup(this.itemView.getContext(), component, deviceType, 0);
        this.title.setText(controlInterface.getTitle());
        this.subtitle.setText(controlInterface.getSubtitle());
        updateFavorite(controlInterface.getFavorite());
        this.removed.setText(controlInterface.getRemoved() ? this.itemView.getContext().getText(R.string.controls_removed) : "");
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.management.ControlHolder$bindData$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlHolder.this.updateFavorite(!r2.favorite.isChecked());
                ControlHolder.this.favoriteCallback.invoke(((ControlInterface) elementWrapper).getControlId(), Boolean.valueOf(ControlHolder.this.favorite.isChecked()));
            }
        });
        Context context = this.itemView.getContext();
        ColorStateList colorStateList = context.getResources().getColorStateList(lookup.foreground, context.getTheme());
        Unit unit = null;
        this.icon.setImageTintList(null);
        Icon customIcon = controlInterface.getCustomIcon();
        if (customIcon != null) {
            if (!((Boolean) this.canUseIconPredicate.invoke(customIcon)).booleanValue()) {
                customIcon = null;
            }
            if (customIcon != null) {
                this.icon.setImageIcon(customIcon);
                unit = Unit.INSTANCE;
            }
        }
        if (unit == null) {
            this.icon.setImageDrawable(lookup.icon);
            if (controlInterface.getDeviceType() != 52) {
                this.icon.setImageTintList(colorStateList);
            }
        }
    }

    public final CharSequence stateDescription(boolean z) {
        if (!z) {
            return this.notFavoriteStateDescription;
        }
        if (this.moveHelper == null) {
            return this.favoriteStateDescription;
        }
        return this.itemView.getContext().getString(R.string.accessibility_control_favorite_position, Integer.valueOf(getLayoutPosition() + 1));
    }

    @Override // com.android.systemui.controls.management.Holder
    public final void updateFavorite(boolean z) {
        this.favorite.setChecked(z);
        this.accessibilityDelegate.isFavorite = z;
        this.itemView.setStateDescription(stateDescription(z));
    }
}
