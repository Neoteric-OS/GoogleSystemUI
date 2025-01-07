package com.android.systemui.navigationbar.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable;
import com.android.systemui.navigationbar.views.buttons.KeyButtonView;
import com.android.systemui.navigationbar.views.buttons.ReverseLinearLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NavigationBarInflaterView extends FrameLayout {
    public boolean mAlternativeOrder;
    SparseArray mButtonDispatchers;
    public String mCurrentLayout;
    public FrameLayout mHorizontal;
    public boolean mIsVertical;
    public LayoutInflater mLandscapeInflater;
    public View mLastLandscape;
    public View mLastPortrait;
    public LayoutInflater mLayoutInflater;
    public final Listener mListener;
    public int mNavBarMode;
    public final OverviewProxyService mOverviewProxyService;
    public FrameLayout mVertical;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Listener implements NavigationModeController.ModeChangedListener {
        public final WeakReference mSelf;

        public Listener(NavigationBarInflaterView navigationBarInflaterView) {
            this.mSelf = new WeakReference(navigationBarInflaterView);
        }

        @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
        public final void onNavigationModeChanged(int i) {
            NavigationBarInflaterView navigationBarInflaterView = (NavigationBarInflaterView) this.mSelf.get();
            if (navigationBarInflaterView != null) {
                navigationBarInflaterView.mNavBarMode = i;
            }
        }
    }

    public NavigationBarInflaterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNavBarMode = 0;
        createInflaters();
        this.mOverviewProxyService = (OverviewProxyService) Dependency.sDependency.getDependencyInner(OverviewProxyService.class);
        Listener listener = new Listener(this);
        this.mListener = listener;
        this.mNavBarMode = ((NavigationModeController) Dependency.sDependency.getDependencyInner(NavigationModeController.class)).addListener(listener);
    }

    public static void addAll(ButtonDispatcher buttonDispatcher, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (viewGroup.getChildAt(i).getId() == buttonDispatcher.mId) {
                buttonDispatcher.addView(viewGroup.getChildAt(i));
            }
            if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                addAll(buttonDispatcher, (ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    public static String extractButton(String str) {
        return !str.contains("[") ? str : str.substring(0, str.indexOf("["));
    }

    public final void addToDispatchers(View view) {
        SparseArray sparseArray = this.mButtonDispatchers;
        if (sparseArray != null) {
            int indexOfKey = sparseArray.indexOfKey(view.getId());
            if (indexOfKey >= 0) {
                ((ButtonDispatcher) this.mButtonDispatchers.valueAt(indexOfKey)).addView(view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    addToDispatchers(viewGroup.getChildAt(i));
                }
            }
        }
    }

    public final void clearDispatcherViews() {
        if (this.mButtonDispatchers != null) {
            for (int i = 0; i < this.mButtonDispatchers.size(); i++) {
                ((ButtonDispatcher) this.mButtonDispatchers.valueAt(i)).mViews.clear();
            }
        }
    }

    public void createInflaters() {
        this.mLayoutInflater = LayoutInflater.from(((FrameLayout) this).mContext);
        Configuration configuration = new Configuration();
        configuration.setTo(((FrameLayout) this).mContext.getResources().getConfiguration());
        configuration.orientation = 2;
        this.mLandscapeInflater = LayoutInflater.from(((FrameLayout) this).mContext.createConfigurationContext(configuration));
    }

    public final String getDefaultLayout() {
        return getContext().getString(QuickStepContract.isGesturalMode(this.mNavBarMode) ? R.string.config_navBarLayoutHandle : this.mOverviewProxyService.shouldShowSwipeUpUI() ? R.string.config_navBarLayoutQuickstep : R.string.config_navBarLayout);
    }

    public final void inflateButtons(String[] strArr, ViewGroup viewGroup, boolean z, boolean z2) {
        View view;
        for (String str : strArr) {
            LayoutInflater layoutInflater = z ? this.mLandscapeInflater : this.mLayoutInflater;
            String extractButton = extractButton(str);
            if ("left".equals(extractButton)) {
                extractButton = extractButton("space");
            } else if ("right".equals(extractButton)) {
                extractButton = extractButton("menu_ime");
            }
            if (BcSmartspaceDataPlugin.UI_SURFACE_HOME_SCREEN.equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.home, viewGroup, false);
            } else if ("back".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.back, viewGroup, false);
            } else if ("recent".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.recent_apps, viewGroup, false);
            } else if ("menu_ime".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.menu_ime, viewGroup, false);
            } else if ("space".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.nav_key_space, viewGroup, false);
            } else if ("clipboard".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.clipboard, viewGroup, false);
            } else if ("contextual".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.contextual, viewGroup, false);
            } else if ("home_handle".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.home_handle, viewGroup, false);
            } else if ("ime_switcher".equals(extractButton)) {
                view = layoutInflater.inflate(R.layout.ime_switcher, viewGroup, false);
            } else if (extractButton.startsWith("key")) {
                String substring = !extractButton.contains(":") ? null : extractButton.substring(extractButton.indexOf(":") + 1, extractButton.indexOf(")"));
                int parseInt = !extractButton.contains("(") ? 1 : Integer.parseInt(extractButton.substring(extractButton.indexOf("(") + 1, extractButton.indexOf(":")));
                view = layoutInflater.inflate(R.layout.custom_key, viewGroup, false);
                final KeyButtonView keyButtonView = (KeyButtonView) view;
                keyButtonView.mCode = parseInt;
                if (substring != null) {
                    if (substring.contains(":")) {
                        new AsyncTask() { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonView.2
                            public AnonymousClass2() {
                            }

                            @Override // android.os.AsyncTask
                            public final Object doInBackground(Object[] objArr) {
                                return ((Icon[]) objArr)[0].loadDrawable(((ImageView) KeyButtonView.this).mContext);
                            }

                            @Override // android.os.AsyncTask
                            public final void onPostExecute(Object obj) {
                                KeyButtonView.this.setImageDrawable((Drawable) obj);
                            }
                        }.execute(Icon.createWithContentUri(substring));
                    } else if (substring.contains("/")) {
                        int indexOf = substring.indexOf(47);
                        new AsyncTask() { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonView.2
                            public AnonymousClass2() {
                            }

                            @Override // android.os.AsyncTask
                            public final Object doInBackground(Object[] objArr) {
                                return ((Icon[]) objArr)[0].loadDrawable(((ImageView) KeyButtonView.this).mContext);
                            }

                            @Override // android.os.AsyncTask
                            public final void onPostExecute(Object obj) {
                                KeyButtonView.this.setImageDrawable((Drawable) obj);
                            }
                        }.execute(Icon.createWithResource(substring.substring(0, indexOf), Integer.parseInt(substring.substring(indexOf + 1))));
                    }
                }
            } else {
                view = null;
            }
            if (view != null) {
                String substring2 = str.contains("[") ? str.substring(str.indexOf("[") + 1, str.indexOf("]")) : null;
                if (substring2 != null) {
                    if (substring2.contains("W") || substring2.contains("A")) {
                        ReverseLinearLayout.ReverseRelativeLayout reverseRelativeLayout = new ReverseLinearLayout.ReverseRelativeLayout(((FrameLayout) this).mContext);
                        reverseRelativeLayout.mDefaultGravity = 0;
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getLayoutParams());
                        int i = z ? z2 ? 48 : 80 : z2 ? 8388611 : 8388613;
                        if (substring2.endsWith("WC")) {
                            i = 17;
                        } else if (substring2.endsWith("C")) {
                            i = 16;
                        }
                        reverseRelativeLayout.mDefaultGravity = i;
                        reverseRelativeLayout.setGravity(i);
                        reverseRelativeLayout.addView(view, layoutParams);
                        if (substring2.contains("W")) {
                            reverseRelativeLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -1, Float.parseFloat(substring2.substring(0, substring2.indexOf("W")))));
                        } else {
                            reverseRelativeLayout.setLayoutParams(new LinearLayout.LayoutParams((int) (Float.parseFloat(substring2.substring(0, substring2.indexOf("A"))) * ((FrameLayout) this).mContext.getResources().getDisplayMetrics().density), -1));
                        }
                        reverseRelativeLayout.setClipChildren(false);
                        reverseRelativeLayout.setClipToPadding(false);
                        view = reverseRelativeLayout;
                    } else {
                        view.getLayoutParams().width = (int) (r5.width * Float.parseFloat(substring2));
                    }
                }
                viewGroup.addView(view);
                addToDispatchers(view);
                View view2 = z ? this.mLastLandscape : this.mLastPortrait;
                if (view instanceof ReverseLinearLayout.ReverseRelativeLayout) {
                    view = ((ReverseLinearLayout.ReverseRelativeLayout) view).getChildAt(0);
                }
                if (view2 != null) {
                    view.setAccessibilityTraversalAfter(view2.getId());
                }
                if (z) {
                    this.mLastLandscape = view;
                } else {
                    this.mLastPortrait = view;
                }
            }
        }
    }

    public final void inflateLayout(String str) {
        this.mCurrentLayout = str;
        if (str == null) {
            str = getDefaultLayout();
        }
        String[] split = str.split(";", 3);
        if (split.length != 3) {
            Log.d("NavBarInflater", "Invalid layout.");
            split = getDefaultLayout().split(";", 3);
        }
        String[] split2 = split[0].split(",");
        String[] split3 = split[1].split(",");
        String[] split4 = split[2].split(",");
        inflateButtons(split2, (ViewGroup) this.mHorizontal.findViewById(R.id.ends_group), false, true);
        inflateButtons(split2, (ViewGroup) this.mVertical.findViewById(R.id.ends_group), true, true);
        inflateButtons(split3, (ViewGroup) this.mHorizontal.findViewById(R.id.center_group), false, false);
        inflateButtons(split3, (ViewGroup) this.mVertical.findViewById(R.id.center_group), true, false);
        ((LinearLayout) this.mHorizontal.findViewById(R.id.ends_group)).addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 1.0f));
        ((LinearLayout) this.mVertical.findViewById(R.id.ends_group)).addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 1.0f));
        inflateButtons(split4, (ViewGroup) this.mHorizontal.findViewById(R.id.ends_group), false, false);
        inflateButtons(split4, (ViewGroup) this.mVertical.findViewById(R.id.ends_group), true, false);
        updateButtonDispatchersCurrentView();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ((NavigationModeController) Dependency.sDependency.getDependencyInner(NavigationModeController.class)).removeListener(this.mListener);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        removeAllViews();
        FrameLayout frameLayout = (FrameLayout) this.mLayoutInflater.inflate(R.layout.navigation_layout, (ViewGroup) this, false);
        this.mHorizontal = frameLayout;
        addView(frameLayout);
        FrameLayout frameLayout2 = (FrameLayout) this.mLayoutInflater.inflate(R.layout.navigation_layout_vertical, (ViewGroup) this, false);
        this.mVertical = frameLayout2;
        addView(frameLayout2);
        updateAlternativeOrder();
        clearDispatcherViews();
        ViewGroup viewGroup = (ViewGroup) this.mHorizontal.findViewById(R.id.nav_buttons);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ((ViewGroup) viewGroup.getChildAt(i)).removeAllViews();
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mVertical.findViewById(R.id.nav_buttons);
        for (int i2 = 0; i2 < viewGroup2.getChildCount(); i2++) {
            ((ViewGroup) viewGroup2.getChildAt(i2)).removeAllViews();
        }
        inflateLayout(getDefaultLayout());
    }

    public final void updateAlternativeOrder() {
        updateAlternativeOrder(this.mHorizontal.findViewById(R.id.ends_group));
        updateAlternativeOrder(this.mHorizontal.findViewById(R.id.center_group));
        updateAlternativeOrder(this.mVertical.findViewById(R.id.ends_group));
        updateAlternativeOrder(this.mVertical.findViewById(R.id.center_group));
    }

    public final void updateButtonDispatchersCurrentView() {
        if (this.mButtonDispatchers != null) {
            FrameLayout frameLayout = this.mIsVertical ? this.mVertical : this.mHorizontal;
            for (int i = 0; i < this.mButtonDispatchers.size(); i++) {
                ButtonDispatcher buttonDispatcher = (ButtonDispatcher) this.mButtonDispatchers.valueAt(i);
                View findViewById = frameLayout.findViewById(buttonDispatcher.mId);
                buttonDispatcher.mCurrentView = findViewById;
                KeyButtonDrawable keyButtonDrawable = buttonDispatcher.mImageDrawable;
                if (keyButtonDrawable != null) {
                    keyButtonDrawable.setCallback(findViewById);
                }
                View view = buttonDispatcher.mCurrentView;
                if (view != null) {
                    view.setTranslationX(0.0f);
                    buttonDispatcher.mCurrentView.setTranslationY(0.0f);
                    buttonDispatcher.mCurrentView.setTranslationZ(0.0f);
                }
            }
        }
    }

    public final void updateAlternativeOrder(View view) {
        if (view instanceof ReverseLinearLayout) {
            ReverseLinearLayout reverseLinearLayout = (ReverseLinearLayout) view;
            reverseLinearLayout.mIsAlternativeOrder = this.mAlternativeOrder;
            reverseLinearLayout.updateOrder();
        }
    }
}
