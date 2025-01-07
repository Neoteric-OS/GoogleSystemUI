package com.android.systemui.user.ui.binder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.helper.widget.Flow;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.user.UserSwitcherRootView;
import com.android.systemui.user.ui.viewmodel.UserActionViewModel;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UserSwitcherViewBinder {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuAdapter extends BaseAdapter {
        public final LayoutInflater layoutInflater;
        public List sections = EmptyList.INSTANCE;

        public MenuAdapter(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return this.sections.size();
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return (List) this.sections.get(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 2;
            List list = (List) this.sections.get(i);
            Context context = viewGroup.getContext();
            LinearLayout linearLayout = view instanceof LinearLayout ? (LinearLayout) view : null;
            if (linearLayout == null) {
                linearLayout = new LinearLayout(context, null);
                linearLayout.setOrientation(1);
                linearLayout.setBackground(viewGroup.getResources().getDrawable(R.drawable.bouncer_user_switcher_popup_bg, context.getTheme()));
                linearLayout.setShowDividers(2);
                linearLayout.setDividerDrawable(context.getDrawable(R.drawable.fullscreen_userswitcher_menu_item_divider));
            }
            linearLayout.removeAllViewsInLayout();
            int i3 = 0;
            for (Object obj : list) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                UserActionViewModel userActionViewModel = (UserActionViewModel) obj;
                final View inflate = this.layoutInflater.inflate(R.layout.user_switcher_fullscreen_popup_item, (ViewGroup) null);
                ((ImageView) inflate.requireViewById(R.id.icon)).setImageResource(userActionViewModel.iconResourceId);
                ((TextView) inflate.requireViewById(R.id.text)).setText(inflate.getResources().getString(userActionViewModel.textResourceId));
                inflate.setOnClickListener(new UserSwitcherViewBinder$bind$2(i2, userActionViewModel));
                linearLayout.addView(inflate);
                if (i3 == 0 && i == 0) {
                    inflate.postDelayed(new Runnable() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$MenuAdapter$getView$1$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            inflate.requestAccessibilityFocus();
                        }
                    }, 200L);
                }
                i3 = i4;
            }
            return linearLayout;
        }
    }

    public static void bind(ViewGroup viewGroup, UserSwitcherViewModel userSwitcherViewModel, LayoutInflater layoutInflater, FalsingCollector falsingCollector, Function0 function0) {
        UserSwitcherRootView userSwitcherRootView = (UserSwitcherRootView) viewGroup.requireViewById(R.id.user_switcher_grid_container);
        Flow flow = (Flow) userSwitcherRootView.requireViewById(R.id.flow);
        View requireViewById = viewGroup.requireViewById(R.id.add);
        View requireViewById2 = viewGroup.requireViewById(R.id.cancel);
        MenuAdapter menuAdapter = new MenuAdapter(layoutInflater);
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        userSwitcherRootView.touchHandler = new UserSwitcherViewBinder$bind$1(falsingCollector);
        requireViewById.setOnClickListener(new UserSwitcherViewBinder$bind$2(0, userSwitcherViewModel));
        requireViewById2.setOnClickListener(new UserSwitcherViewBinder$bind$2(1, userSwitcherViewModel));
        RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new UserSwitcherViewBinder$bind$4(userSwitcherViewModel, ref$ObjectRef, function0, requireViewById, viewGroup, menuAdapter, flow, userSwitcherRootView, layoutInflater, null));
    }
}
