package com.android.wm.shell.startingsurface;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Trace;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Slog;
import android.view.Choreographer;
import android.view.Display;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.window.SplashScreenView;
import android.window.StartingWindowInfo;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.internal.policy.PhoneWindow;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.IconProvider;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.startingsurface.SplashScreenExitAnimationUtils;
import java.util.Objects;
import java.util.function.IntSupplier;
import java.util.function.UnaryOperator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplashscreenContentDrawer {
    public int mBrandingImageHeight;
    public int mBrandingImageWidth;
    public final boolean mCanUseAppIconForSplashScreen;
    final ColorCache mColorCache;
    public final Context mContext;
    public int mDefaultIconSize;
    public float mEnlargeForegroundIconThreshold;
    public final HighResIconProvider mHighResIconProvider;
    public int mIconSize;
    public int mLastPackageContextConfigHash;
    public int mMainWindowShiftLength;
    public float mNoBackgroundScale;
    public final Handler mSplashscreenWorkerHandler;
    public final SplashScreenWindowAttrs mTmpAttrs = new SplashScreenWindowAttrs();
    public final TransactionPool mTransactionPool;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class ColorCache extends BroadcastReceiver {
        public final ArrayMap mColorMap = new ArrayMap();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Cache {
            public final int mHash;
            public int mReuseCount;

            public Cache(int i) {
                this.mHash = i;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Colors {
            public final WindowColor[] mWindowColors = new WindowColor[2];
            public final IconColor[] mIconColors = new IconColor[2];
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class IconColor extends Cache {
            public final int mBgColor;
            public final int mFgColor;
            public final float mFgNonTranslucentRatio;
            public final boolean mIsBgComplex;
            public final boolean mIsBgGrayscale;

            public IconColor(int i, int i2, int i3, boolean z, boolean z2, float f) {
                super(i);
                this.mFgColor = i2;
                this.mBgColor = i3;
                this.mIsBgComplex = z;
                this.mIsBgGrayscale = z2;
                this.mFgNonTranslucentRatio = f;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class WindowColor extends Cache {
            public final int mBgColor;

            public WindowColor(int i, int i2) {
                super(i);
                this.mBgColor = i2;
            }
        }

        public ColorCache(Context context, Handler handler) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            context.registerReceiverAsUser(this, UserHandle.ALL, intentFilter, null, handler);
        }

        public static Cache getCache(Cache[] cacheArr, int i, int[] iArr) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < 2; i3++) {
                Cache cache = cacheArr[i3];
                if (cache == null) {
                    iArr[0] = i3;
                    i2 = -1;
                } else {
                    if (cache.mHash == i) {
                        cache.mReuseCount++;
                        return cache;
                    }
                    int i4 = cache.mReuseCount;
                    if (i4 < i2) {
                        iArr[0] = i3;
                        i2 = i4;
                    }
                }
            }
            return null;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            if (data != null) {
                this.mColorMap.remove(data.getEncodedSchemeSpecificPart());
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HighResIconProvider {
        public boolean mLoadInDetail;
        public final Context mSharedContext;
        public final IconProvider mSharedIconProvider;
        public Context mStandaloneContext;
        public IconProvider mStandaloneIconProvider;

        public HighResIconProvider(Context context, IconProvider iconProvider) {
            this.mSharedContext = context;
            this.mSharedIconProvider = iconProvider;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplashScreenWindowAttrs {
        public int mWindowBgResId = 0;
        public int mWindowBgColor = 0;
        public Drawable mSplashScreenIcon = null;
        public Drawable mBrandingImage = null;
        public int mIconBgColor = 0;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplashViewBuilder {
        public final ActivityInfo mActivityInfo;
        public boolean mAllowHandleSolidColor;
        public final Context mContext;
        public Drawable[] mFinalIconDrawables;
        public int mFinalIconSize;
        public Drawable mOverlayDrawable;
        public int mSuggestType;
        public int mThemeColor;
        public SplashscreenWindowCreator$$ExternalSyntheticLambda2 mUiThreadInitTask;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ShapeIconFactory extends BaseIconFactory {
        }

        public SplashViewBuilder(Context context, ActivityInfo activityInfo) {
            this.mFinalIconSize = SplashscreenContentDrawer.this.mIconSize;
            this.mContext = context;
            this.mActivityInfo = activityInfo;
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x0134, code lost:
        
            if (r12 != null) goto L78;
         */
        /* JADX WARN: Removed duplicated region for block: B:120:0x028f  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x029b  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0192  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x01ad  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x01bc  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0176  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01fe  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final android.window.SplashScreenView build() {
            /*
                Method dump skipped, instructions count: 796
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.startingsurface.SplashscreenContentDrawer.SplashViewBuilder.build():android.window.SplashScreenView");
        }

        public final void createIconDrawable(Drawable drawable, boolean z, boolean z2) {
            SplashscreenIconDrawableFactory$ImmobileIconDrawable splashscreenIconDrawableFactory$ImmobileIconDrawable;
            Drawable drawable2;
            SplashscreenContentDrawer splashscreenContentDrawer = SplashscreenContentDrawer.this;
            if (z) {
                this.mFinalIconDrawables = new Drawable[]{new SplashscreenIconDrawableFactory$ImmobileIconDrawable(drawable, splashscreenContentDrawer.mDefaultIconSize, this.mFinalIconSize, z2, splashscreenContentDrawer.mSplashscreenWorkerHandler)};
                return;
            }
            int i = splashscreenContentDrawer.mTmpAttrs.mIconBgColor;
            int i2 = this.mThemeColor;
            int i3 = splashscreenContentDrawer.mDefaultIconSize;
            int i4 = this.mFinalIconSize;
            boolean z3 = (i == 0 || i == i2) ? false : true;
            if (drawable instanceof Animatable) {
                drawable2 = new SplashscreenIconDrawableFactory$AnimatableIconAnimateListener(drawable);
            } else {
                boolean z4 = drawable instanceof AdaptiveIconDrawable;
                Handler handler = splashscreenContentDrawer.mSplashscreenWorkerHandler;
                if (z4) {
                    splashscreenIconDrawableFactory$ImmobileIconDrawable = new SplashscreenIconDrawableFactory$ImmobileIconDrawable(drawable, i3, i4, z2, handler);
                    z3 = false;
                } else {
                    splashscreenIconDrawableFactory$ImmobileIconDrawable = new SplashscreenIconDrawableFactory$ImmobileIconDrawable(new SplashscreenIconDrawableFactory$AdaptiveForegroundDrawable(drawable), i3, i4, z2, handler);
                }
                drawable2 = splashscreenIconDrawableFactory$ImmobileIconDrawable;
            }
            this.mFinalIconDrawables = new Drawable[]{drawable2, z3 ? new SplashscreenIconDrawableFactory$MaskBackgroundDrawable(i) : null};
        }
    }

    /* renamed from: -$$Nest$smisRgbSimilarInHsv, reason: not valid java name */
    public static boolean m907$$Nest$smisRgbSimilarInHsv(int i, int i2) {
        double d;
        if (i == i2) {
            return true;
        }
        float luminance = Color.luminance(i);
        float luminance2 = Color.luminance(i2);
        float f = luminance > luminance2 ? (luminance + 0.05f) / (luminance2 + 0.05f) : (luminance2 + 0.05f) / (luminance + 0.05f);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -985106566387254744L, 32, String.valueOf(Integer.toHexString(i)), String.valueOf(Integer.toHexString(i2)), Double.valueOf(f));
        }
        if (f < 2.0f) {
            return true;
        }
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        Color.colorToHSV(i, fArr);
        Color.colorToHSV(i2, fArr2);
        int abs = ((((int) Math.abs(fArr[0] - fArr2[0])) + 180) % 360) - 180;
        double pow = Math.pow(abs / 180.0f, 2.0d);
        double pow2 = Math.pow(fArr[1] - fArr2[1], 2.0d);
        double pow3 = Math.pow(fArr[2] - fArr2[2], 2.0d);
        double sqrt = Math.sqrt(((pow + pow2) + pow3) / 3.0d);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            d = sqrt;
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 449850539114510075L, 2796201, Long.valueOf(abs), Double.valueOf(fArr[0]), Double.valueOf(fArr2[0]), Double.valueOf(fArr[1]), Double.valueOf(fArr2[1]), Double.valueOf(fArr[2]), Double.valueOf(fArr2[2]), Double.valueOf(pow), Double.valueOf(pow2), Double.valueOf(pow3), Double.valueOf(d));
        } else {
            d = sqrt;
        }
        return d < 0.1d;
    }

    public SplashscreenContentDrawer(Context context, IconProvider iconProvider, TransactionPool transactionPool) {
        this.mContext = context;
        this.mHighResIconProvider = new HighResIconProvider(context, iconProvider);
        this.mTransactionPool = transactionPool;
        HandlerThread handlerThread = new HandlerThread("wmshell.splashworker", -10);
        handlerThread.start();
        Handler threadHandler = handlerThread.getThreadHandler();
        this.mSplashscreenWorkerHandler = threadHandler;
        this.mColorCache = new ColorCache(context, threadHandler);
        this.mCanUseAppIconForSplashScreen = context.getResources().getBoolean(R.bool.config_canUseAppIconForSplashScreen);
    }

    public static Context createContext(Context context, StartingWindowInfo startingWindowInfo, int i, int i2, DisplayManager displayManager) {
        String str;
        ActivityManager.RunningTaskInfo runningTaskInfo = startingWindowInfo.taskInfo;
        ActivityInfo activityInfo = startingWindowInfo.targetActivityInfo;
        if (activityInfo == null) {
            activityInfo = runningTaskInfo.topActivityInfo;
        }
        if (activityInfo == null || (str = activityInfo.packageName) == null) {
            return null;
        }
        int i3 = runningTaskInfo.displayId;
        int i4 = runningTaskInfo.taskId;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -3363243702063253373L, 80, str, String.valueOf(Integer.toHexString(i)), Long.valueOf(i4), Long.valueOf(i2));
        }
        Display display = displayManager.getDisplay(i3);
        if (display == null) {
            return null;
        }
        if (i3 != 0) {
            context = context.createDisplayContext(display);
        }
        if (context == null) {
            return null;
        }
        if (i != context.getThemeResId()) {
            try {
                context = context.createPackageContextAsUser(activityInfo.packageName, 4, UserHandle.of(runningTaskInfo.userId));
                context.setTheme(i);
            } catch (PackageManager.NameNotFoundException e) {
                Slog.w("ShellStartingWindow", "Failed creating package context with package name " + activityInfo.packageName + " for user " + runningTaskInfo.userId, e);
                return null;
            }
        }
        Configuration configuration = runningTaskInfo.getConfiguration();
        if ((configuration.uiMode & 48) == (context.getResources().getConfiguration().uiMode & 48)) {
            return context;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 6600937306632370785L, 0, String.valueOf(configuration));
        }
        Context createConfigurationContext = context.createConfigurationContext(configuration);
        createConfigurationContext.setTheme(i);
        return createConfigurationContext;
    }

    public static WindowManager.LayoutParams createLayoutParameters(Context context, StartingWindowInfo startingWindowInfo, int i, CharSequence charSequence, int i2, IBinder iBinder) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(3);
        layoutParams.setFitInsetsSides(0);
        layoutParams.setFitInsetsTypes(0);
        layoutParams.format = i2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(com.android.internal.R.styleable.Window);
        int i3 = obtainStyledAttributes.getBoolean(14, false) ? android.R.bool.config_cecSetMenuLanguageEnabled_default : android.R.attr.transcriptMode;
        if (i != 4 || obtainStyledAttributes.getBoolean(33, false)) {
            i3 |= Integer.MIN_VALUE;
        }
        ActivityManager.RunningTaskInfo runningTaskInfo = startingWindowInfo.taskInfo;
        ActivityInfo activityInfo = startingWindowInfo.targetActivityInfo;
        if (activityInfo == null) {
            activityInfo = runningTaskInfo.topActivityInfo;
        }
        boolean isEdgeToEdgeEnforced = PhoneWindow.isEdgeToEdgeEnforced(activityInfo.applicationInfo, false, obtainStyledAttributes);
        if (isEdgeToEdgeEnforced) {
            layoutParams.privateFlags |= 2048;
        }
        layoutParams.layoutInDisplayCutoutMode = obtainStyledAttributes.getInt(50, isEdgeToEdgeEnforced ? 3 : layoutParams.layoutInDisplayCutoutMode);
        layoutParams.windowAnimations = obtainStyledAttributes.getResourceId(8, 0);
        obtainStyledAttributes.recycle();
        if (runningTaskInfo.displayId == 0 && startingWindowInfo.isKeyguardOccluded) {
            i3 |= 524288;
        }
        layoutParams.flags = 131096 | i3;
        layoutParams.token = iBinder;
        layoutParams.packageName = activityInfo.packageName;
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle("Splash Screen " + ((Object) charSequence));
        return layoutParams;
    }

    public static int estimateWindowBGColor(Drawable drawable) {
        SplashscreenContentDrawer$DrawableColorTester$ColorTester splashscreenContentDrawer$DrawableColorTester$SingleColorTester;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            if (layerDrawable.getNumberOfLayers() > 0) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -2298264882754273930L, 0, null);
                }
                drawable = layerDrawable.getDrawable(0);
            }
        }
        if (drawable == null) {
            splashscreenContentDrawer$DrawableColorTester$SingleColorTester = new SplashscreenContentDrawer$DrawableColorTester$SingleColorTester(new ColorDrawable(getSystemBGColor()));
        } else {
            splashscreenContentDrawer$DrawableColorTester$SingleColorTester = drawable instanceof ColorDrawable ? new SplashscreenContentDrawer$DrawableColorTester$SingleColorTester((ColorDrawable) drawable) : new SplashscreenContentDrawer$DrawableColorTester$ComplexDrawableTester(2, drawable);
        }
        if (splashscreenContentDrawer$DrawableColorTester$SingleColorTester.passFilterRatio() >= 0.5f) {
            return splashscreenContentDrawer$DrawableColorTester$SingleColorTester.getDominantColor();
        }
        Slog.w("ShellStartingWindow", "Window background is translucent, fill background with black color");
        return getSystemBGColor();
    }

    public static long getShowingDuration(long j, long j2) {
        return j <= j2 ? j2 : j2 < 500 ? (j > 500 || j2 < 400) ? 400L : 500L : j2;
    }

    public static int getSystemBGColor() {
        Application currentApplication = ActivityThread.currentApplication();
        if (currentApplication != null) {
            return currentApplication.getResources().getColor(R.color.splash_window_background_default);
        }
        Slog.e("ShellStartingWindow", "System context does not exist!");
        return DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    }

    public static void getWindowAttrs(Context context, SplashScreenWindowAttrs splashScreenWindowAttrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(com.android.internal.R.styleable.Window);
        splashScreenWindowAttrs.mWindowBgResId = obtainStyledAttributes.getResourceId(1, 0);
        splashScreenWindowAttrs.mWindowBgColor = ((Integer) safeReturnAttrDefault(new SplashscreenContentDrawer$$ExternalSyntheticLambda0(obtainStyledAttributes, 0), 0)).intValue();
        splashScreenWindowAttrs.mSplashScreenIcon = (Drawable) safeReturnAttrDefault(new SplashscreenContentDrawer$$ExternalSyntheticLambda0(obtainStyledAttributes, 1), null);
        splashScreenWindowAttrs.mBrandingImage = (Drawable) safeReturnAttrDefault(new SplashscreenContentDrawer$$ExternalSyntheticLambda0(obtainStyledAttributes, 2), null);
        splashScreenWindowAttrs.mIconBgColor = ((Integer) safeReturnAttrDefault(new SplashscreenContentDrawer$$ExternalSyntheticLambda0(obtainStyledAttributes, 3), 0)).intValue();
        obtainStyledAttributes.recycle();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -2397845537672908446L, 12, String.valueOf(Integer.toHexString(splashScreenWindowAttrs.mWindowBgColor)), Boolean.valueOf(splashScreenWindowAttrs.mSplashScreenIcon != null));
        }
    }

    public static int peekWindowBGColor(Context context, SplashScreenWindowAttrs splashScreenWindowAttrs) {
        Drawable drawable;
        Trace.traceBegin(32L, "peekWindowBGColor");
        if (splashScreenWindowAttrs.mWindowBgColor != 0) {
            drawable = new ColorDrawable(splashScreenWindowAttrs.mWindowBgColor);
        } else {
            int i = splashScreenWindowAttrs.mWindowBgResId;
            if (i != 0) {
                try {
                    drawable = context.getDrawable(i);
                } catch (Resources.NotFoundException e) {
                    Slog.w("ShellStartingWindow", "Unable get drawable from resource", e);
                }
            }
            drawable = null;
        }
        if (drawable == null) {
            drawable = new ColorDrawable(getSystemBGColor());
            Slog.w("ShellStartingWindow", "Window background does not exist, using " + drawable);
        }
        int estimateWindowBGColor = estimateWindowBGColor(drawable);
        Trace.traceEnd(32L);
        return estimateWindowBGColor;
    }

    public static Object safeReturnAttrDefault(UnaryOperator unaryOperator, Object obj) {
        try {
            return unaryOperator.apply(obj);
        } catch (RuntimeException e) {
            Slog.w("ShellStartingWindow", "Get attribute fail, return default: " + e.getMessage());
            return obj;
        }
    }

    public final void applyExitAnimation(final SplashScreenView splashScreenView, final SurfaceControl surfaceControl, final Rect rect, final Runnable runnable, long j, final float f) {
        Runnable runnable2 = new Runnable() { // from class: com.android.wm.shell.startingsurface.SplashscreenContentDrawer$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                float f2;
                View view;
                SplashScreenExitAnimationUtils.ShiftUpAnimation shiftUpAnimation;
                ValueAnimator valueAnimator;
                SplashscreenContentDrawer splashscreenContentDrawer = SplashscreenContentDrawer.this;
                SplashScreenView splashScreenView2 = splashScreenView;
                SurfaceControl surfaceControl2 = surfaceControl;
                Rect rect2 = rect;
                Runnable runnable3 = runnable;
                float f3 = f;
                splashscreenContentDrawer.getClass();
                SplashScreenExitAnimation splashScreenExitAnimation = new SplashScreenExitAnimation(splashscreenContentDrawer.mContext, splashScreenView2, surfaceControl2, rect2, splashscreenContentDrawer.mMainWindowShiftLength, splashscreenContentDrawer.mTransactionPool, runnable3, f3);
                int i = splashScreenExitAnimation.mAnimationType;
                final SplashScreenView splashScreenView3 = splashScreenExitAnimation.mSplashScreenView;
                SurfaceControl surfaceControl3 = splashScreenExitAnimation.mFirstWindowSurface;
                int i2 = splashScreenExitAnimation.mMainWindowShiftLength;
                TransactionPool transactionPool = splashScreenExitAnimation.mTransactionPool;
                Rect rect3 = splashScreenExitAnimation.mFirstWindowFrame;
                final int i3 = splashScreenExitAnimation.mAnimationDuration;
                final int i4 = splashScreenExitAnimation.mIconFadeOutDuration;
                final float f4 = splashScreenExitAnimation.mIconStartAlpha;
                final float f5 = splashScreenExitAnimation.mBrandingStartAlpha;
                final int i5 = splashScreenExitAnimation.mAppRevealDelay;
                final int i6 = splashScreenExitAnimation.mAppRevealDuration;
                float f6 = splashScreenExitAnimation.mRoundedCornerRadius;
                Interpolator interpolator = SplashScreenExitAnimationUtils.ICON_INTERPOLATOR;
                if (i == 1) {
                    valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                    valueAnimator.setDuration(i3);
                    valueAnimator.setInterpolator(Interpolators.LINEAR);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.shared.startingsurface.SplashScreenExitAnimationUtils$$ExternalSyntheticLambda0
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            View view2;
                            View view3;
                            int i7 = i4;
                            int i8 = i3;
                            ViewGroup viewGroup = splashScreenView3;
                            float f7 = f4;
                            float f8 = f5;
                            int i9 = i5;
                            int i10 = i6;
                            float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                            float interpolation = ((PathInterpolator) SplashScreenExitAnimationUtils.ICON_INTERPOLATOR).getInterpolation(SplashScreenExitAnimationUtils.getProgress(floatValue, i8, 0L, i7));
                            if (viewGroup instanceof SplashScreenView) {
                                SplashScreenView splashScreenView4 = (SplashScreenView) viewGroup;
                                view2 = splashScreenView4.getIconView();
                                view3 = splashScreenView4.getBrandingView();
                            } else {
                                view2 = null;
                                view3 = null;
                            }
                            if (view2 != null) {
                                view2.setAlpha((1.0f - interpolation) * f7);
                            }
                            if (view3 != null) {
                                view3.setAlpha((1.0f - interpolation) * f8);
                            }
                            viewGroup.setAlpha(1.0f - ((PathInterpolator) Interpolators.ALPHA_OUT).getInterpolation(SplashScreenExitAnimationUtils.getProgress(floatValue, i8, i9, i10)));
                        }
                    });
                    valueAnimator.addListener(splashScreenExitAnimation);
                } else {
                    int height = splashScreenView3.getHeight();
                    int width = splashScreenView3.getWidth() / 2;
                    int sqrt = (int) ((((int) Math.sqrt((width * width) + (height * height))) * 1.25f) + 0.5d);
                    final SplashScreenExitAnimationUtils.RadialVanishAnimation radialVanishAnimation = new SplashScreenExitAnimationUtils.RadialVanishAnimation(splashScreenView3);
                    radialVanishAnimation.mCircleCenter.set(width, 0);
                    radialVanishAnimation.mFinishRadius = sqrt;
                    radialVanishAnimation.mVanishPaint.setShader(new RadialGradient(0.0f, 0.0f, 1.0f, new int[]{-1, -1, 0}, new float[]{0.0f, 0.8f, 1.0f}, Shader.TileMode.CLAMP));
                    radialVanishAnimation.mVanishPaint.setBlendMode(BlendMode.DST_OUT);
                    if (surfaceControl3 == null || !surfaceControl3.isValid()) {
                        f2 = f5;
                        view = null;
                        shiftUpAnimation = null;
                    } else {
                        view = new View(splashScreenView3.getContext());
                        view.setBackgroundColor(splashScreenView3.getInitBackgroundColor());
                        splashScreenView3.addView(view, new ViewGroup.LayoutParams(-1, i2));
                        f2 = f5;
                        shiftUpAnimation = new SplashScreenExitAnimationUtils.ShiftUpAnimation(-i2, view, surfaceControl3, splashScreenView3, transactionPool, rect3, i2, f6);
                    }
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    ofFloat.setDuration(i3);
                    ofFloat.setInterpolator(Interpolators.LINEAR);
                    ofFloat.addListener(splashScreenExitAnimation);
                    ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.shared.startingsurface.SplashScreenExitAnimationUtils.1
                        public final /* synthetic */ View val$finalOccludeHoleView;
                        public final /* synthetic */ RadialVanishAnimation val$radialVanishAnimation;
                        public final /* synthetic */ ViewGroup val$splashScreenView;

                        public AnonymousClass1(final ViewGroup splashScreenView32, final RadialVanishAnimation radialVanishAnimation2, View view2) {
                            r2 = splashScreenView32;
                            r3 = radialVanishAnimation2;
                            r4 = view2;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            SurfaceControl surfaceControl4;
                            super.onAnimationEnd(animator);
                            ShiftUpAnimation shiftUpAnimation2 = ShiftUpAnimation.this;
                            if (shiftUpAnimation2 != null && (surfaceControl4 = shiftUpAnimation2.mFirstWindowSurface) != null && surfaceControl4.isValid()) {
                                TransactionPool transactionPool2 = shiftUpAnimation2.mTransactionPool;
                                SurfaceControl.Transaction acquire = transactionPool2.acquire();
                                if (shiftUpAnimation2.mSplashScreenView.isAttachedToWindow()) {
                                    acquire.setFrameTimelineVsync(Choreographer.getSfInstance().getVsyncId());
                                    shiftUpAnimation2.mApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(shiftUpAnimation2.mFirstWindowSurface).withWindowCrop((Rect) null).withMergeTransaction(acquire).build()});
                                } else {
                                    acquire.setWindowCrop(shiftUpAnimation2.mFirstWindowSurface, null);
                                    acquire.apply();
                                }
                                transactionPool2.release(acquire);
                                Choreographer sfInstance = Choreographer.getSfInstance();
                                final SurfaceControl surfaceControl5 = shiftUpAnimation2.mFirstWindowSurface;
                                Objects.requireNonNull(surfaceControl5);
                                sfInstance.postCallback(4, new Runnable() { // from class: com.android.wm.shell.shared.startingsurface.SplashScreenExitAnimationUtils$ShiftUpAnimation$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        surfaceControl5.release();
                                    }
                                }, null);
                            }
                            r2.removeView(r3);
                            r2.removeView(r4);
                        }
                    });
                    final float f7 = f2;
                    valueAnimator = ofFloat;
                    final SplashScreenExitAnimationUtils.ShiftUpAnimation shiftUpAnimation2 = shiftUpAnimation;
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.shared.startingsurface.SplashScreenExitAnimationUtils$$ExternalSyntheticLambda1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            View view2;
                            View view3;
                            SurfaceControl surfaceControl4;
                            int i7 = i4;
                            int i8 = i3;
                            ViewGroup viewGroup = splashScreenView32;
                            float f8 = f4;
                            float f9 = f7;
                            int i9 = i5;
                            int i10 = i6;
                            SplashScreenExitAnimationUtils.RadialVanishAnimation radialVanishAnimation2 = radialVanishAnimation2;
                            SplashScreenExitAnimationUtils.ShiftUpAnimation shiftUpAnimation3 = shiftUpAnimation2;
                            float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                            float interpolation = ((PathInterpolator) SplashScreenExitAnimationUtils.ICON_INTERPOLATOR).getInterpolation(SplashScreenExitAnimationUtils.getProgress(floatValue, i8, 0L, i7));
                            if (viewGroup instanceof SplashScreenView) {
                                SplashScreenView splashScreenView4 = (SplashScreenView) viewGroup;
                                view2 = splashScreenView4.getIconView();
                                view3 = splashScreenView4.getBrandingView();
                            } else {
                                view2 = null;
                                view3 = null;
                            }
                            if (view2 != null) {
                                view2.setAlpha((1.0f - interpolation) * f8);
                            }
                            if (view3 != null) {
                                view3.setAlpha((1.0f - interpolation) * f9);
                            }
                            float progress = SplashScreenExitAnimationUtils.getProgress(floatValue, i8, i9, i10);
                            if (radialVanishAnimation2.mVanishPaint.getShader() != null) {
                                float interpolation2 = ((PathInterpolator) SplashScreenExitAnimationUtils.MASK_RADIUS_INTERPOLATOR).getInterpolation(progress);
                                float interpolation3 = ((PathInterpolator) Interpolators.ALPHA_OUT).getInterpolation(progress);
                                float f10 = (radialVanishAnimation2.mFinishRadius * interpolation2) + 0;
                                radialVanishAnimation2.mVanishMatrix.setScale(f10, f10);
                                Matrix matrix = radialVanishAnimation2.mVanishMatrix;
                                Point point = radialVanishAnimation2.mCircleCenter;
                                matrix.postTranslate(point.x, point.y);
                                radialVanishAnimation2.mVanishPaint.getShader().setLocalMatrix(radialVanishAnimation2.mVanishMatrix);
                                radialVanishAnimation2.mVanishPaint.setAlpha(Math.round(interpolation3 * 255.0f));
                                radialVanishAnimation2.postInvalidate();
                            }
                            if (shiftUpAnimation3 == null || (surfaceControl4 = shiftUpAnimation3.mFirstWindowSurface) == null || !surfaceControl4.isValid() || !shiftUpAnimation3.mSplashScreenView.isAttachedToWindow()) {
                                return;
                            }
                            float interpolation4 = ((PathInterpolator) SplashScreenExitAnimationUtils.SHIFT_UP_INTERPOLATOR).getInterpolation(progress);
                            float f11 = shiftUpAnimation3.mToYDelta;
                            float f12 = shiftUpAnimation3.mFromYDelta;
                            float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f11, f12, interpolation4, f12);
                            shiftUpAnimation3.mOccludeHoleView.setTranslationY(m);
                            shiftUpAnimation3.mTmpTransform.setTranslate(0.0f, m);
                            TransactionPool transactionPool2 = shiftUpAnimation3.mTransactionPool;
                            SurfaceControl.Transaction acquire = transactionPool2.acquire();
                            acquire.setFrameTimelineVsync(Choreographer.getSfInstance().getVsyncId());
                            Matrix matrix2 = shiftUpAnimation3.mTmpTransform;
                            Rect rect4 = shiftUpAnimation3.mFirstWindowFrame;
                            matrix2.postTranslate(rect4.left, rect4.top + shiftUpAnimation3.mMainWindowShiftLength);
                            shiftUpAnimation3.mApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(shiftUpAnimation3.mFirstWindowSurface).withMatrix(shiftUpAnimation3.mTmpTransform).withMergeTransaction(acquire).build()});
                            transactionPool2.release(acquire);
                        }
                    });
                }
                valueAnimator.start();
            }
        };
        if (splashScreenView.getIconView() == null) {
            runnable2.run();
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis() - j;
        long showingDuration = getShowingDuration(splashScreenView.getIconAnimationDuration() != null ? splashScreenView.getIconAnimationDuration().toMillis() : 0L, uptimeMillis) - uptimeMillis;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -5778344489450129331L, 0, String.valueOf(showingDuration));
        }
        if (showingDuration > 0) {
            splashScreenView.postDelayed(runnable2, showingDuration);
        } else {
            runnable2.run();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0029, code lost:
    
        if (r5 != null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int getBGColorFromCache(android.content.pm.ActivityInfo r5, java.util.function.IntSupplier r6) {
        /*
            r4 = this;
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache r0 = r4.mColorCache
            java.lang.String r5 = r5.packageName
            int r1 = r4.mLastPackageContextConfigHash
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$SplashScreenWindowAttrs r4 = r4.mTmpAttrs
            int r2 = r4.mWindowBgColor
            int r4 = r4.mWindowBgResId
            android.util.ArrayMap r3 = r0.mColorMap
            java.lang.Object r3 = r3.get(r5)
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$Colors r3 = (com.android.wm.shell.startingsurface.SplashscreenContentDrawer.ColorCache.Colors) r3
            int r1 = r1 * 31
            int r1 = r1 + r2
            int r1 = r1 * 31
            int r1 = r1 + r4
            r4 = 0
            int[] r2 = new int[]{r4}
            if (r3 == 0) goto L2c
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$WindowColor[] r5 = r3.mWindowColors
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$Cache r5 = com.android.wm.shell.startingsurface.SplashscreenContentDrawer.ColorCache.getCache(r5, r1, r2)
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$WindowColor r5 = (com.android.wm.shell.startingsurface.SplashscreenContentDrawer.ColorCache.WindowColor) r5
            if (r5 == 0) goto L36
            goto L45
        L2c:
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$Colors r3 = new com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$Colors
            r3.<init>()
            android.util.ArrayMap r0 = r0.mColorMap
            r0.put(r5, r3)
        L36:
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$WindowColor r5 = new com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$WindowColor
            int r6 = r6.getAsInt()
            r5.<init>(r1, r6)
            com.android.wm.shell.startingsurface.SplashscreenContentDrawer$ColorCache$WindowColor[] r6 = r3.mWindowColors
            r4 = r2[r4]
            r6[r4] = r5
        L45:
            int r4 = r5.mBgColor
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.startingsurface.SplashscreenContentDrawer.getBGColorFromCache(android.content.pm.ActivityInfo, java.util.function.IntSupplier):int");
    }

    public final SplashScreenView makeSplashScreenContentView(final Context context, StartingWindowInfo startingWindowInfo, int i, SplashscreenWindowCreator$$ExternalSyntheticLambda2 splashscreenWindowCreator$$ExternalSyntheticLambda2) {
        updateDensity();
        SplashScreenWindowAttrs splashScreenWindowAttrs = this.mTmpAttrs;
        getWindowAttrs(context, splashScreenWindowAttrs);
        this.mLastPackageContextConfigHash = context.getResources().getConfiguration().hashCode();
        if (i == 1 && !this.mCanUseAppIconForSplashScreen && splashScreenWindowAttrs.mSplashScreenIcon == null && (startingWindowInfo.startingWindowTypeParameter & 512) == 0) {
            i = 3;
        }
        final Drawable drawable = null;
        if (i == 4) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(com.android.internal.R.styleable.Window);
            int intValue = ((Integer) safeReturnAttrDefault(new SplashscreenContentDrawer$$ExternalSyntheticLambda0(obtainStyledAttributes, 4), 0)).intValue();
            obtainStyledAttributes.recycle();
            if (intValue != 0) {
                drawable = context.getDrawable(intValue);
            } else {
                int i2 = splashScreenWindowAttrs.mWindowBgResId;
                if (i2 != 0) {
                    drawable = context.getDrawable(i2);
                }
            }
        }
        ActivityInfo activityInfo = startingWindowInfo.targetActivityInfo;
        if (activityInfo == null) {
            activityInfo = startingWindowInfo.taskInfo.topActivityInfo;
        }
        int bGColorFromCache = drawable != null ? getBGColorFromCache(activityInfo, new IntSupplier() { // from class: com.android.wm.shell.startingsurface.SplashscreenContentDrawer$$ExternalSyntheticLambda6
            @Override // java.util.function.IntSupplier
            public final int getAsInt() {
                return SplashscreenContentDrawer.estimateWindowBGColor(drawable);
            }
        }) : getBGColorFromCache(activityInfo, new IntSupplier() { // from class: com.android.wm.shell.startingsurface.SplashscreenContentDrawer$$ExternalSyntheticLambda7
            @Override // java.util.function.IntSupplier
            public final int getAsInt() {
                return SplashscreenContentDrawer.peekWindowBGColor(context, SplashscreenContentDrawer.this.mTmpAttrs);
            }
        });
        SplashViewBuilder splashViewBuilder = new SplashViewBuilder(context, activityInfo);
        splashViewBuilder.mThemeColor = bGColorFromCache;
        splashViewBuilder.mOverlayDrawable = drawable;
        splashViewBuilder.mSuggestType = i;
        splashViewBuilder.mUiThreadInitTask = splashscreenWindowCreator$$ExternalSyntheticLambda2;
        splashViewBuilder.mAllowHandleSolidColor = startingWindowInfo.allowHandleSolidColorSplashScreen();
        return splashViewBuilder.build();
    }

    public final void updateDensity() {
        this.mIconSize = this.mContext.getResources().getDimensionPixelSize(android.R.dimen.status_bar_system_icon_size);
        this.mDefaultIconSize = this.mContext.getResources().getDimensionPixelSize(android.R.dimen.status_bar_system_icon_intrinsic_size);
        this.mBrandingImageWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.starting_surface_brand_image_width);
        this.mBrandingImageHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.starting_surface_brand_image_height);
        this.mMainWindowShiftLength = this.mContext.getResources().getDimensionPixelSize(R.dimen.starting_surface_exit_animation_window_shift_length);
        this.mEnlargeForegroundIconThreshold = this.mContext.getResources().getFloat(R.dimen.splash_icon_enlarge_foreground_threshold);
        this.mNoBackgroundScale = this.mContext.getResources().getFloat(R.dimen.splash_icon_no_background_scale_factor);
    }
}
