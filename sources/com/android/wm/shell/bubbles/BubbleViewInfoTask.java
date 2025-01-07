package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleViewInfoTask {
    public final Executor mBgExecutor;
    public final Bubble mBubble;
    public final Callback mCallback;
    public final WeakReference mContext;
    public final WeakReference mExpandedViewManager;
    public final BubbleIconFactory mIconFactory;
    public final WeakReference mLayerView;
    public final Executor mMainExecutor;
    public final WeakReference mPositioner;
    public final boolean mSkipInflation;
    public final WeakReference mStackView;
    public final WeakReference mTaskViewFactory;
    public final AtomicBoolean mStarted = new AtomicBoolean();
    public final AtomicBoolean mCancelled = new AtomicBoolean();
    public final AtomicBoolean mFinished = new AtomicBoolean();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.bubbles.BubbleViewInfoTask$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class BubbleViewInfo {
        public String appName;
        public Bitmap badgeBitmap;
        public BubbleBarExpandedView bubbleBarExpandedView;
        public Bitmap bubbleBitmap;
        public int dotColor;
        public Path dotPath;
        public BubbleExpandedView expandedView;
        public Bubble.FlyoutMessage flyoutMessage;
        public BadgedImageView imageView;
        public Bitmap rawBadgeBitmap;
        public ShortcutInfo shortcutInfo;
        public BubbleTaskView taskView;

        public static BubbleViewInfo populate(Context context, BubbleTaskViewFactory bubbleTaskViewFactory, BubblePositioner bubblePositioner, BubbleStackView bubbleStackView, BubbleIconFactory bubbleIconFactory, Bubble bubble, boolean z) {
            BubbleViewInfo bubbleViewInfo = new BubbleViewInfo();
            if (!z && !bubble.isInflated()) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 8082017669105793175L, 0, String.valueOf(bubble.mKey));
                }
                LayoutInflater from = LayoutInflater.from(context);
                BadgedImageView badgedImageView = (BadgedImageView) from.inflate(R.layout.bubble_view, (ViewGroup) bubbleStackView, false);
                bubbleViewInfo.imageView = badgedImageView;
                badgedImageView.initialize(bubblePositioner);
                bubbleViewInfo.taskView = bubble.getOrCreateBubbleTaskView(bubbleTaskViewFactory);
                bubbleViewInfo.expandedView = (BubbleExpandedView) from.inflate(R.layout.bubble_expanded_view, (ViewGroup) bubbleStackView, false);
            }
            Drawable drawable = null;
            if (!BubbleViewInfoTask.m901$$Nest$smpopulateCommonInfo(bubbleViewInfo, context, bubble, bubbleIconFactory)) {
                return null;
            }
            Bubble.FlyoutMessage flyoutMessage = bubble.mFlyoutMessage;
            bubbleViewInfo.flyoutMessage = flyoutMessage;
            if (flyoutMessage != null) {
                Icon icon = flyoutMessage.senderIcon;
                Objects.requireNonNull(context);
                if (icon != null) {
                    try {
                        if (icon.getType() != 4) {
                            if (icon.getType() == 6) {
                            }
                            drawable = icon.loadDrawable(context);
                        }
                        context.grantUriPermission(context.getPackageName(), icon.getUri(), 1);
                        drawable = icon.loadDrawable(context);
                    } catch (Exception e) {
                        Log.w("Bubbles", "loadSenderAvatar failed: " + e.getMessage());
                    }
                }
                flyoutMessage.senderAvatar = drawable;
            }
            return bubbleViewInfo;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onBubbleViewsReady(Bubble bubble);
    }

    /* renamed from: -$$Nest$smpopulateCommonInfo, reason: not valid java name */
    public static boolean m901$$Nest$smpopulateCommonInfo(BubbleViewInfo bubbleViewInfo, Context context, Bubble bubble, BubbleIconFactory bubbleIconFactory) {
        Drawable drawable;
        ShortcutInfo shortcutInfo = bubble.mShortcutInfo;
        if (shortcutInfo != null) {
            bubbleViewInfo.shortcutInfo = shortcutInfo;
        }
        PackageManager packageManagerForUser = BubbleController.getPackageManagerForUser(bubble.mUser.getIdentifier(), context);
        try {
            ApplicationInfo applicationInfo = packageManagerForUser.getApplicationInfo(bubble.mPackageName, 795136);
            if (applicationInfo != null) {
                bubbleViewInfo.appName = String.valueOf(packageManagerForUser.getApplicationLabel(applicationInfo));
            }
            Drawable applicationIcon = packageManagerForUser.getApplicationIcon(bubble.mPackageName);
            Drawable userBadgedIcon = packageManagerForUser.getUserBadgedIcon(applicationIcon, bubble.mUser);
            try {
                ShortcutInfo shortcutInfo2 = bubbleViewInfo.shortcutInfo;
                Icon icon = bubble.mIcon;
                bubbleIconFactory.getClass();
                drawable = BubbleIconFactory.getBubbleDrawable(context, shortcutInfo2, icon);
            } catch (Exception unused) {
                Log.w("Bubbles", "Exception creating icon for the bubble: " + bubble.mKey);
                drawable = null;
            }
            if (drawable != null) {
                applicationIcon = drawable;
            }
            BitmapInfo badgeBitmap = bubbleIconFactory.getBadgeBitmap(userBadgedIcon, bubble.mIsImportantConversation);
            Bitmap bitmap = badgeBitmap.icon;
            bubbleViewInfo.badgeBitmap = bitmap;
            if (bubble.mIsImportantConversation) {
                bitmap = bubbleIconFactory.getBadgeBitmap(userBadgedIcon, false).icon;
            }
            bubbleViewInfo.rawBadgeBitmap = bitmap;
            float[] fArr = new float[1];
            bubbleViewInfo.bubbleBitmap = bubbleIconFactory.createIconBitmap(bubbleIconFactory.normalizeAndWrapToAdaptiveIcon(applicationIcon, null, fArr), fArr[0], 2);
            Path createPathFromPathData = PathParser.createPathFromPathData(context.getResources().getString(android.R.string.config_mainBuiltInDisplayCutout));
            Matrix matrix = new Matrix();
            float f = fArr[0];
            matrix.setScale(f, f, 50.0f, 50.0f);
            createPathFromPathData.transform(matrix);
            bubbleViewInfo.dotPath = createPathFromPathData;
            bubbleViewInfo.dotColor = ColorUtils.blendARGB(badgeBitmap.color, -1, 0.54f);
            return true;
        } catch (PackageManager.NameNotFoundException unused2) {
            Log.w("Bubbles", "Unable to find package: " + bubble.mPackageName);
            return false;
        }
    }

    public BubbleViewInfoTask(Bubble bubble, Context context, BubbleExpandedViewManager$Companion$fromBubbleController$1 bubbleExpandedViewManager$Companion$fromBubbleController$1, BubbleController.AnonymousClass1 anonymousClass1, BubblePositioner bubblePositioner, BubbleStackView bubbleStackView, BubbleBarLayerView bubbleBarLayerView, BubbleIconFactory bubbleIconFactory, boolean z, Callback callback, Executor executor, Executor executor2) {
        this.mBubble = bubble;
        this.mContext = new WeakReference(context);
        this.mExpandedViewManager = new WeakReference(bubbleExpandedViewManager$Companion$fromBubbleController$1);
        this.mTaskViewFactory = new WeakReference(anonymousClass1);
        this.mPositioner = new WeakReference(bubblePositioner);
        this.mStackView = new WeakReference(bubbleStackView);
        this.mLayerView = new WeakReference(bubbleBarLayerView);
        this.mIconFactory = bubbleIconFactory;
        this.mSkipInflation = z;
        this.mCallback = callback;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
    }

    public final BubbleViewInfo loadViewInfo() {
        if (!verifyState()) {
            return null;
        }
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1];
        Bubble bubble = this.mBubble;
        if (z) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 8956445472117109952L, 0, String.valueOf(bubble.mKey));
        }
        if (this.mLayerView.get() == null) {
            return BubbleViewInfo.populate((Context) this.mContext.get(), (BubbleTaskViewFactory) this.mTaskViewFactory.get(), (BubblePositioner) this.mPositioner.get(), (BubbleStackView) this.mStackView.get(), this.mIconFactory, this.mBubble, this.mSkipInflation);
        }
        Context context = (Context) this.mContext.get();
        BubbleTaskViewFactory bubbleTaskViewFactory = (BubbleTaskViewFactory) this.mTaskViewFactory.get();
        BubbleBarLayerView bubbleBarLayerView = (BubbleBarLayerView) this.mLayerView.get();
        BubbleViewInfo bubbleViewInfo = new BubbleViewInfo();
        if (!this.mSkipInflation && !bubble.isInflated()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, -4859492011243720687L, 0, String.valueOf(bubble.mKey));
            }
            bubbleViewInfo.taskView = bubble.getOrCreateBubbleTaskView(bubbleTaskViewFactory);
            bubbleViewInfo.bubbleBarExpandedView = (BubbleBarExpandedView) LayoutInflater.from(context).inflate(R.layout.bubble_bar_expanded_view, (ViewGroup) bubbleBarLayerView, false);
        }
        if (m901$$Nest$smpopulateCommonInfo(bubbleViewInfo, context, bubble, this.mIconFactory)) {
            return bubbleViewInfo;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0194  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateViewInfo(com.android.wm.shell.bubbles.BubbleViewInfoTask.BubbleViewInfo r19) {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleViewInfoTask.updateViewInfo(com.android.wm.shell.bubbles.BubbleViewInfoTask$BubbleViewInfo):void");
    }

    public final boolean verifyState() {
        return ((BubbleExpandedViewManager$Companion$fromBubbleController$1) ((BubbleExpandedViewManager) this.mExpandedViewManager.get())).$controller.isShowingAsBubbleBar() ? this.mLayerView.get() != null : this.mStackView.get() != null;
    }
}
