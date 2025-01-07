package com.android.systemui.communal.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RemoteViews;
import androidx.core.os.BuildCompat;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.communal.util.DensityUtils;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalAppWidgetHostView extends AppWidgetHostView implements LaunchableView {
    public final CommunalAppWidgetHostView$cornerRadiusEnforcementOutline$1 cornerRadiusEnforcementOutline;
    public final float enforcedCornerRadius;
    public final Rect enforcedRectangle;
    public final RemoteViews.InteractionHandler interactionHandler;
    public final LaunchableViewDelegate launchableViewDelegate;

    /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.communal.widgets.CommunalAppWidgetHostView$cornerRadiusEnforcementOutline$1] */
    public CommunalAppWidgetHostView(Context context, RemoteViews.InteractionHandler interactionHandler) {
        super(context, interactionHandler);
        this.interactionHandler = interactionHandler;
        this.launchableViewDelegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.communal.widgets.CommunalAppWidgetHostView$launchableViewDelegate$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.appwidget.AppWidgetHostView*/.setVisibility(((Number) obj).intValue());
                return Unit.INSTANCE;
            }
        });
        int i = BuildCompat.$r8$clinit;
        this.enforcedCornerRadius = Math.min((DensityUtils.windowManagerService != null ? r0.getInitialDisplayDensity(0) / r0.getBaseDisplayDensity(0) : 1.0f) * context.getResources().getDimension(R.dimen.communal_enforced_rounded_corner_max_radius), context.getResources().getDimension(android.R.dimen.system_app_widget_background_radius));
        this.enforcedRectangle = new Rect();
        this.cornerRadiusEnforcementOutline = new ViewOutlineProvider() { // from class: com.android.systemui.communal.widgets.CommunalAppWidgetHostView$cornerRadiusEnforcementOutline$1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (!CommunalAppWidgetHostView.this.enforcedRectangle.isEmpty()) {
                    CommunalAppWidgetHostView communalAppWidgetHostView = CommunalAppWidgetHostView.this;
                    float f = communalAppWidgetHostView.enforcedCornerRadius;
                    if (f > 0.0f) {
                        outline.setRoundRect(communalAppWidgetHostView.enforcedRectangle, f);
                        return;
                    }
                }
                outline.setEmpty();
            }
        };
    }

    public final void onDefaultViewClicked(View view) {
        LauncherApps launcherApps;
        List<LauncherActivityInfo> activityList;
        LauncherActivityInfo launcherActivityInfo;
        PendingIntent mainActivityLaunchIntent;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        if (appWidgetManager != null) {
            appWidgetManager.noteAppWidgetTapped(getAppWidgetId());
        }
        if (getAppWidgetInfo() == null || (activityList = (launcherApps = (LauncherApps) getContext().getSystemService(LauncherApps.class)).getActivityList(getAppWidgetInfo().provider.getPackageName(), getAppWidgetInfo().getProfile())) == null || (launcherActivityInfo = (LauncherActivityInfo) CollectionsKt.getOrNull(0, activityList)) == null || (mainActivityLaunchIntent = launcherApps.getMainActivityLaunchIntent(launcherActivityInfo.getComponentName(), null, launcherActivityInfo.getUser())) == null) {
            return;
        }
        this.interactionHandler.onInteraction(view, mainActivityLaunchIntent, RemoteViews.RemoteResponse.fromPendingIntent(mainActivityLaunchIntent));
    }

    @Override // android.appwidget.AppWidgetHostView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.enforcedCornerRadius <= 0.0f) {
            setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            setClipToOutline(false);
            return;
        }
        ArrayList arrayList = new ArrayList();
        RoundedCornerEnforcement.accumulateViewsWithId(this, arrayList);
        View findUndefinedBackground = arrayList.size() == 1 ? (View) arrayList.get(0) : getChildCount() > 0 ? RoundedCornerEnforcement.findUndefinedBackground(getChildAt(0)) : this;
        if (findUndefinedBackground == null || (findUndefinedBackground.getId() == R.id.background && findUndefinedBackground.getClipToOutline())) {
            setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            setClipToOutline(false);
            return;
        }
        Rect rect = this.enforcedRectangle;
        rect.left = 0;
        rect.right = findUndefinedBackground.getWidth();
        rect.top = 0;
        rect.bottom = findUndefinedBackground.getHeight();
        while (findUndefinedBackground != this) {
            rect.offset(findUndefinedBackground.getLeft(), findUndefinedBackground.getTop());
            findUndefinedBackground = (View) findUndefinedBackground.getParent();
        }
        setOutlineProvider(this.cornerRadiusEnforcementOutline);
        setClipToOutline(true);
        invalidateOutline();
    }

    @Override // android.appwidget.AppWidgetHostView
    public final void setAppWidget(int i, AppWidgetProviderInfo appWidgetProviderInfo) {
        super.setAppWidget(i, appWidgetProviderInfo);
        setPadding(0, 0, 0, 0);
    }

    @Override // com.android.systemui.animation.LaunchableView
    public final void setShouldBlockVisibilityChanges(boolean z) {
        this.launchableViewDelegate.setShouldBlockVisibilityChanges(z);
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        this.launchableViewDelegate.setVisibility(i);
    }
}
