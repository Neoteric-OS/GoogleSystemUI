package com.android.systemui.shade;

import com.android.systemui.common.buffer.RingBuffer;
import com.android.systemui.statusbar.StatusBarState;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationShadeWindowState {
    public static final List TABLE_HEADERS = CollectionsKt__CollectionsKt.listOf("keyguardShowing", "keyguardOccluded", "keyguardNeedsInput", "panelVisible", "panelExpanded", "notificationShadeFocusable", "glanceableHubShowing", "bouncerShowing", "keyguardFadingAway", "keyguardGoingAway", "qsExpanded", "headsUpShowing", "lightRevealScrimOpaque", "isSwitchingUsers", "forceCollapsed", "forceDozeBrightness", "forceUserActivity", "launchingActivity", "backdropShowing", "notTouchable", "componentsForcingTopUi", "forceOpenTokens", "statusBarState", "remoteInputActive", "forcePluginOpen", "dozing", "scrimsVisibility", "backgroundBlurRadius", "communalVisible");
    public final Lazy asStringList$delegate;
    public int backgroundBlurRadius;
    public boolean bouncerShowing;
    public boolean communalVisible;
    public final Set componentsForcingTopUi;
    public boolean dozing;
    public boolean dreaming;
    public boolean forceDozeBrightness;
    public final Set forceOpenTokens;
    public boolean forcePluginOpen;
    public boolean forceWindowCollapsed;
    public boolean glanceableHubShowing;
    public boolean headsUpNotificationShowing;
    public boolean isSwitchingUsers;
    public boolean keyguardFadingAway;
    public boolean keyguardGoingAway;
    public boolean keyguardNeedsInput;
    public boolean keyguardOccluded;
    public boolean keyguardShowing;
    public boolean launchingActivityFromNotification;
    public boolean lightRevealScrimOpaque;
    public boolean notificationShadeFocusable;
    public boolean panelVisible;
    public boolean qsExpanded;
    public boolean remoteInputActive;
    public int scrimsVisibility;
    public boolean shadeOrQsExpanded;
    public int statusBarState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Buffer {
        public final RingBuffer buffer = new RingBuffer(100, new Function0() { // from class: com.android.systemui.shade.NotificationShadeWindowState$Buffer$buffer$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new NotificationShadeWindowState();
            }
        });
    }

    public NotificationShadeWindowState() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        this.keyguardShowing = false;
        this.keyguardOccluded = false;
        this.keyguardNeedsInput = false;
        this.panelVisible = false;
        this.shadeOrQsExpanded = false;
        this.notificationShadeFocusable = false;
        this.bouncerShowing = false;
        this.glanceableHubShowing = false;
        this.keyguardFadingAway = false;
        this.keyguardGoingAway = false;
        this.qsExpanded = false;
        this.headsUpNotificationShowing = false;
        this.lightRevealScrimOpaque = false;
        this.isSwitchingUsers = false;
        this.forceWindowCollapsed = false;
        this.forceDozeBrightness = false;
        this.launchingActivityFromNotification = false;
        this.componentsForcingTopUi = linkedHashSet;
        this.forceOpenTokens = linkedHashSet2;
        this.statusBarState = 0;
        this.remoteInputActive = false;
        this.forcePluginOpen = false;
        this.dozing = false;
        this.dreaming = false;
        this.scrimsVisibility = 0;
        this.backgroundBlurRadius = 0;
        this.communalVisible = false;
        this.asStringList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.shade.NotificationShadeWindowState$asStringList$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf = String.valueOf(NotificationShadeWindowState.this.keyguardShowing);
                String valueOf2 = String.valueOf(NotificationShadeWindowState.this.keyguardOccluded);
                String valueOf3 = String.valueOf(NotificationShadeWindowState.this.keyguardNeedsInput);
                String valueOf4 = String.valueOf(NotificationShadeWindowState.this.panelVisible);
                String valueOf5 = String.valueOf(NotificationShadeWindowState.this.shadeOrQsExpanded);
                String valueOf6 = String.valueOf(NotificationShadeWindowState.this.notificationShadeFocusable);
                String valueOf7 = String.valueOf(NotificationShadeWindowState.this.bouncerShowing);
                String valueOf8 = String.valueOf(NotificationShadeWindowState.this.glanceableHubShowing);
                String valueOf9 = String.valueOf(NotificationShadeWindowState.this.keyguardFadingAway);
                String valueOf10 = String.valueOf(NotificationShadeWindowState.this.keyguardGoingAway);
                String valueOf11 = String.valueOf(NotificationShadeWindowState.this.qsExpanded);
                String valueOf12 = String.valueOf(NotificationShadeWindowState.this.headsUpNotificationShowing);
                String valueOf13 = String.valueOf(NotificationShadeWindowState.this.lightRevealScrimOpaque);
                String valueOf14 = String.valueOf(NotificationShadeWindowState.this.isSwitchingUsers);
                String valueOf15 = String.valueOf(NotificationShadeWindowState.this.forceWindowCollapsed);
                String valueOf16 = String.valueOf(NotificationShadeWindowState.this.forceDozeBrightness);
                NotificationShadeWindowState.this.getClass();
                String valueOf17 = String.valueOf(false);
                String valueOf18 = String.valueOf(NotificationShadeWindowState.this.launchingActivityFromNotification);
                NotificationShadeWindowState.this.getClass();
                String valueOf19 = String.valueOf(false);
                NotificationShadeWindowState.this.getClass();
                return CollectionsKt__CollectionsKt.listOf(valueOf, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, valueOf15, valueOf16, valueOf17, valueOf18, valueOf19, String.valueOf(false), NotificationShadeWindowState.this.componentsForcingTopUi.toString(), NotificationShadeWindowState.this.forceOpenTokens.toString(), StatusBarState.toString(NotificationShadeWindowState.this.statusBarState), String.valueOf(NotificationShadeWindowState.this.remoteInputActive), String.valueOf(NotificationShadeWindowState.this.forcePluginOpen), String.valueOf(NotificationShadeWindowState.this.dozing), String.valueOf(NotificationShadeWindowState.this.scrimsVisibility), String.valueOf(NotificationShadeWindowState.this.backgroundBlurRadius), String.valueOf(NotificationShadeWindowState.this.communalVisible));
            }
        });
    }

    public final boolean isKeyguardShowingAndNotOccluded() {
        return this.keyguardShowing && !this.keyguardOccluded;
    }
}
