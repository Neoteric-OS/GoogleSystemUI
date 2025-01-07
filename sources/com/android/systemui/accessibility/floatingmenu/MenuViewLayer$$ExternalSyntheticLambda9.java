package com.android.systemui.accessibility.floatingmenu;

import android.content.Intent;
import android.view.View;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.systemui.accessibility.floatingmenu.AnnotationLinkSpan;
import com.android.wm.shell.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MenuViewLayer$$ExternalSyntheticLambda9 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MenuViewLayer f$0;

    public /* synthetic */ MenuViewLayer$$ExternalSyntheticLambda9(MenuViewLayer menuViewLayer, int i) {
        this.$r8$classId = i;
        this.f$0 = menuViewLayer;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = 1;
        int i2 = this.$r8$classId;
        final MenuViewLayer menuViewLayer = this.f$0;
        MenuEduTooltipView menuEduTooltipView = (MenuEduTooltipView) obj;
        switch (i2) {
            case 0:
                MenuViewLayer.m780$r8$lambda$EoXQAmcH59afcqT97RTQWdI8g(menuViewLayer, menuEduTooltipView);
                break;
            case 1:
                CharSequence text = menuViewLayer.getContext().getText(R.string.accessibility_floating_button_docking_tooltip);
                menuViewLayer.addView(menuEduTooltipView, 3);
                menuEduTooltipView.mMessageView.setText(text);
                menuEduTooltipView.updateLocationAndVisibility();
                menuEduTooltipView.setTag("dock");
                MenuListViewTouchHandler menuListViewTouchHandler = menuViewLayer.mMenuListViewTouchHandler;
                MenuViewLayer$$ExternalSyntheticLambda0 menuViewLayer$$ExternalSyntheticLambda0 = new MenuViewLayer$$ExternalSyntheticLambda0(menuViewLayer, i);
                menuListViewTouchHandler.getClass();
                menuListViewTouchHandler.mOnActionDownEnd = Optional.ofNullable(menuViewLayer$$ExternalSyntheticLambda0);
                break;
            default:
                menuViewLayer.getClass();
                final Intent intent = new Intent("android.settings.ACCESSIBILITY_DETAILS_SETTINGS");
                intent.addFlags(268435456);
                intent.putExtra("android.intent.extra.COMPONENT_NAME", AccessibilityShortcutController.ACCESSIBILITY_BUTTON_COMPONENT_NAME.flattenToShortString());
                CharSequence linkify = AnnotationLinkSpan.linkify(menuViewLayer.getContext().getText(R.string.accessibility_floating_button_migration_tooltip), new AnnotationLinkSpan.LinkInfo(new View.OnClickListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuViewLayer$$ExternalSyntheticLambda12
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MenuViewLayer menuViewLayer2 = MenuViewLayer.this;
                        menuViewLayer2.getContext().startActivity(intent);
                        menuViewLayer2.mEduTooltipView.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(menuViewLayer2, 0));
                    }
                }));
                menuViewLayer.addView(menuEduTooltipView, 3);
                menuEduTooltipView.mMessageView.setText(linkify);
                menuEduTooltipView.updateLocationAndVisibility();
                menuEduTooltipView.setTag("migration");
                MenuListViewTouchHandler menuListViewTouchHandler2 = menuViewLayer.mMenuListViewTouchHandler;
                MenuViewLayer$$ExternalSyntheticLambda0 menuViewLayer$$ExternalSyntheticLambda02 = new MenuViewLayer$$ExternalSyntheticLambda0(menuViewLayer, i);
                menuListViewTouchHandler2.getClass();
                menuListViewTouchHandler2.mOnActionDownEnd = Optional.ofNullable(menuViewLayer$$ExternalSyntheticLambda02);
                break;
        }
    }
}
