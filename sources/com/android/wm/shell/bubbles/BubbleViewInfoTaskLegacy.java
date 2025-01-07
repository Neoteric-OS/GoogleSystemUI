package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.util.Log;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.internal.graphics.ColorUtils;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.Bubble;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BubbleViewInfoTaskLegacy extends AsyncTask {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class BubbleViewInfo {
        public ShortcutInfo shortcutInfo;

        public static BubbleViewInfo populate(Context context, BubbleExpandedViewManager bubbleExpandedViewManager, BubbleTaskViewFactory bubbleTaskViewFactory, BubblePositioner bubblePositioner, BubbleStackView bubbleStackView, BubbleIconFactory bubbleIconFactory, Bubble bubble, boolean z) {
            Drawable drawable;
            BubbleViewInfo bubbleViewInfo = new BubbleViewInfo();
            boolean z2 = false;
            if (!z && !bubble.isInflated()) {
                LayoutInflater from = LayoutInflater.from(context);
                ((BadgedImageView) from.inflate(R.layout.bubble_view, (ViewGroup) bubbleStackView, false)).initialize(bubblePositioner);
                ((BubbleExpandedView) from.inflate(R.layout.bubble_expanded_view, (ViewGroup) bubbleStackView, false)).initialize(bubbleExpandedViewManager, bubbleStackView, bubblePositioner, false, bubble.getOrCreateBubbleTaskView(bubbleTaskViewFactory));
            }
            int i = BubbleViewInfoTaskLegacy.$r8$clinit;
            ShortcutInfo shortcutInfo = bubble.mShortcutInfo;
            if (shortcutInfo != null) {
                bubbleViewInfo.shortcutInfo = shortcutInfo;
            }
            PackageManager packageManagerForUser = BubbleController.getPackageManagerForUser(bubble.mUser.getIdentifier(), context);
            Drawable drawable2 = null;
            try {
                ApplicationInfo applicationInfo = packageManagerForUser.getApplicationInfo(bubble.mPackageName, 795136);
                if (applicationInfo != null) {
                    String.valueOf(packageManagerForUser.getApplicationLabel(applicationInfo));
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
                if (bubble.mIsImportantConversation) {
                    bubbleIconFactory.getBadgeBitmap(userBadgedIcon, false);
                }
                float[] fArr = new float[1];
                bubbleIconFactory.createIconBitmap(bubbleIconFactory.normalizeAndWrapToAdaptiveIcon(applicationIcon, null, fArr), fArr[0], 2);
                Path createPathFromPathData = PathParser.createPathFromPathData(context.getResources().getString(android.R.string.config_mainBuiltInDisplayCutout));
                Matrix matrix = new Matrix();
                float f = fArr[0];
                matrix.setScale(f, f, 50.0f, 50.0f);
                createPathFromPathData.transform(matrix);
                ColorUtils.blendARGB(badgeBitmap.color, -1, 0.54f);
                z2 = true;
            } catch (PackageManager.NameNotFoundException unused2) {
                Log.w("Bubbles", "Unable to find package: " + bubble.mPackageName);
            }
            if (!z2) {
                return null;
            }
            Bubble.FlyoutMessage flyoutMessage = bubble.mFlyoutMessage;
            if (flyoutMessage != null) {
                Icon icon2 = flyoutMessage.senderIcon;
                Objects.requireNonNull(context);
                if (icon2 != null) {
                    try {
                        if (icon2.getType() != 4) {
                            if (icon2.getType() == 6) {
                            }
                            drawable2 = icon2.loadDrawable(context);
                        }
                        context.grantUriPermission(context.getPackageName(), icon2.getUri(), 1);
                        drawable2 = icon2.loadDrawable(context);
                    } catch (Exception e) {
                        Log.w("Bubbles", "loadSenderAvatar failed: " + e.getMessage());
                    }
                }
                flyoutMessage.senderAvatar = drawable2;
            }
            return bubbleViewInfo;
        }
    }
}
