package com.android.systemui.statusbar.phone;

import android.graphics.Color;
import android.os.Trace;
import com.android.systemui.dock.DockManager;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.scrim.ScrimView$$ExternalSyntheticLambda4;
import com.google.android.systemui.dreamliner.DockObserver;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ScrimState {
    public static final /* synthetic */ ScrimState[] $VALUES;
    public static final ScrimState AOD;
    public static final ScrimState AUTH_SCRIMMED_SHADE;
    public static final ScrimState BOUNCER;
    public static final ScrimState BOUNCER_SCRIMMED;
    public static final ScrimState BRIGHTNESS_MIRROR;
    public static final ScrimState DREAMING;
    public static final ScrimState GLANCEABLE_HUB;
    public static final ScrimState GLANCEABLE_HUB_OVER_DREAM;
    public static final ScrimState KEYGUARD;
    public static final ScrimState OFF;
    public static final ScrimState PULSING;
    public static final ScrimState SHADE_LOCKED;
    public static final ScrimState UNINITIALIZED;
    public static final ScrimState UNLOCKED;
    float mAodFrontScrimAlpha;
    int mBackgroundColor;
    float mBehindAlpha;
    boolean mClipQsScrim;
    float mDefaultScrimAlpha;
    boolean mDisplayRequiresBlanking;
    DockManager mDockManager;
    DozeParameters mDozeParameters;
    float mFrontAlpha;
    boolean mHasBackdrop;
    boolean mKeyguardFadingAway;
    long mKeyguardFadingAwayDuration;
    boolean mLaunchingAffordanceWithPreview;
    float mNotifAlpha;
    boolean mOccludeAnimationPlaying;
    ScrimView mScrimBehind;
    float mScrimBehindAlphaKeyguard;
    ScrimView mScrimInFront;
    boolean mWakeLockScreenSensorActive;
    boolean mWallpaperSupportsAmbientMode;
    boolean mBlankScreen = false;
    long mAnimationDuration = 220;
    int mFrontTint = 0;
    int mBehindTint = 0;
    int mNotifTint = 0;
    int mSurfaceColor = 0;
    boolean mAnimateChange = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$1, reason: invalid class name */
    enum AnonymousClass1 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            int i = this.mBackgroundColor;
            this.mFrontTint = i;
            this.mBehindTint = i;
            this.mFrontAlpha = 1.0f;
            this.mBehindAlpha = 1.0f;
            if (scrimState == ScrimState.AOD) {
                this.mAnimateChange = false;
            } else {
                this.mAnimationDuration = 1000L;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$10, reason: invalid class name */
    enum AnonymousClass10 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final float getMaxLightRevealScrimAlpha() {
            if (this.mWakeLockScreenSensorActive) {
                return 0.6f;
            }
            return ScrimState.AOD.getMaxLightRevealScrimAlpha();
        }

        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mFrontAlpha = this.mAodFrontScrimAlpha;
            int i = this.mBackgroundColor;
            this.mBehindTint = i;
            this.mFrontTint = i;
            this.mBlankScreen = this.mDisplayRequiresBlanking;
            this.mAnimationDuration = this.mWakeLockScreenSensorActive ? 1000L : 220L;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$11, reason: invalid class name */
    enum AnonymousClass11 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBehindAlpha = this.mClipQsScrim ? 1.0f : 0.0f;
            this.mNotifAlpha = 0.0f;
            this.mFrontAlpha = 0.0f;
            this.mAnimationDuration = this.mKeyguardFadingAway ? this.mKeyguardFadingAwayDuration : 300L;
            ScrimState scrimState2 = ScrimState.AOD;
            this.mAnimateChange = (this.mLaunchingAffordanceWithPreview || this.mOccludeAnimationPlaying || (scrimState == scrimState2 || scrimState == ScrimState.PULSING)) ? false : true;
            this.mFrontTint = 0;
            int i = this.mBackgroundColor;
            this.mBehindTint = i;
            this.mBlankScreen = false;
            if (this.mDisplayRequiresBlanking && scrimState == scrimState2) {
                updateScrimColor(this.mScrimInFront, i);
                updateScrimColor(this.mScrimBehind, this.mBackgroundColor);
                int i2 = this.mBackgroundColor;
                this.mFrontTint = i2;
                this.mBehindTint = i2;
                this.mBlankScreen = true;
            }
            if (this.mClipQsScrim) {
                updateScrimColor(this.mScrimBehind, this.mBackgroundColor);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$12, reason: invalid class name */
    enum AnonymousClass12 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mFrontTint = 0;
            int i = this.mBackgroundColor;
            this.mBehindTint = i;
            boolean z = this.mClipQsScrim;
            this.mNotifTint = z ? i : 0;
            this.mFrontAlpha = 0.0f;
            this.mBehindAlpha = z ? 1.0f : 0.0f;
            this.mNotifAlpha = 0.0f;
            this.mBlankScreen = false;
            if (z) {
                updateScrimColor(this.mScrimBehind, i);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$13, reason: invalid class name */
    enum AnonymousClass13 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBehindAlpha = 0.0f;
            this.mNotifAlpha = 0.0f;
            this.mFrontAlpha = 0.0f;
            this.mFrontTint = 0;
            int i = this.mBackgroundColor;
            this.mBehindTint = i;
            this.mNotifTint = this.mClipQsScrim ? i : 0;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$14, reason: invalid class name */
    enum AnonymousClass14 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBehindAlpha = 0.0f;
            this.mNotifAlpha = 0.0f;
            this.mFrontAlpha = 0.0f;
            this.mFrontTint = 0;
            int i = this.mBackgroundColor;
            this.mBehindTint = i;
            this.mNotifTint = this.mClipQsScrim ? i : 0;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$2, reason: invalid class name */
    enum AnonymousClass2 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBlankScreen = false;
            if (scrimState == ScrimState.AOD) {
                this.mAnimationDuration = 667L;
                if (this.mDisplayRequiresBlanking) {
                    this.mBlankScreen = true;
                }
            } else if (scrimState == ScrimState.KEYGUARD) {
                this.mAnimationDuration = 667L;
            } else {
                this.mAnimationDuration = 220L;
            }
            int i = this.mBackgroundColor;
            this.mFrontTint = i;
            this.mBehindTint = i;
            boolean z = this.mClipQsScrim;
            this.mNotifTint = z ? i : 0;
            this.mFrontAlpha = 0.0f;
            this.mBehindAlpha = z ? 1.0f : this.mScrimBehindAlphaKeyguard;
            this.mNotifAlpha = z ? this.mScrimBehindAlphaKeyguard : 0.0f;
            if (z) {
                updateScrimColor(this.mScrimBehind, i);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$3, reason: invalid class name */
    enum AnonymousClass3 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            int i = this.mBackgroundColor;
            this.mFrontTint = i;
            this.mFrontAlpha = 0.66f;
            this.mBehindTint = i;
            this.mBehindAlpha = 1.0f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$4, reason: invalid class name */
    enum AnonymousClass4 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mNotifTint = scrimState.mNotifTint;
            this.mNotifAlpha = scrimState.mNotifAlpha;
            this.mBehindTint = scrimState.mBehindTint;
            this.mBehindAlpha = scrimState.mBehindAlpha;
            this.mFrontTint = this.mBackgroundColor;
            this.mFrontAlpha = 0.66f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$5, reason: invalid class name */
    enum AnonymousClass5 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            boolean z = this.mClipQsScrim;
            this.mBehindAlpha = z ? 1.0f : this.mDefaultScrimAlpha;
            this.mBehindTint = z ? this.mBackgroundColor : this.mSurfaceColor;
            this.mNotifAlpha = z ? this.mDefaultScrimAlpha : 0.0f;
            this.mNotifTint = 0;
            this.mFrontAlpha = 0.0f;
        }

        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void setSurfaceColor(int i) {
            this.mSurfaceColor = i;
            if (this.mClipQsScrim) {
                return;
            }
            this.mBehindTint = i;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$6, reason: invalid class name */
    enum AnonymousClass6 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBehindAlpha = 0.0f;
            this.mFrontAlpha = this.mDefaultScrimAlpha;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$7, reason: invalid class name */
    enum AnonymousClass7 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            boolean z = this.mClipQsScrim;
            this.mBehindAlpha = z ? 1.0f : this.mDefaultScrimAlpha;
            this.mNotifAlpha = 1.0f;
            this.mFrontAlpha = 0.0f;
            this.mBehindTint = z ? 0 : this.mBackgroundColor;
            if (z) {
                updateScrimColor(this.mScrimBehind, this.mBackgroundColor);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$8, reason: invalid class name */
    enum AnonymousClass8 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            this.mBehindAlpha = 0.0f;
            this.mFrontAlpha = 0.0f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ScrimState$9, reason: invalid class name */
    enum AnonymousClass9 extends ScrimState {
        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final float getMaxLightRevealScrimAlpha() {
            return (!this.mWallpaperSupportsAmbientMode || this.mHasBackdrop) ? 1.0f : 0.0f;
        }

        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final boolean isLowPowerState() {
            return true;
        }

        @Override // com.android.systemui.statusbar.phone.ScrimState
        public final void prepare(ScrimState scrimState) {
            boolean alwaysOn = this.mDozeParameters.getAlwaysOn();
            boolean z = this.mDozeParameters.mIsQuickPickupEnabled;
            boolean isDocked = ((DockObserver) this.mDockManager).isDocked();
            this.mBlankScreen = this.mDisplayRequiresBlanking;
            int i = this.mBackgroundColor;
            this.mFrontTint = i;
            this.mFrontAlpha = (alwaysOn || isDocked || z) ? this.mAodFrontScrimAlpha : 1.0f;
            this.mBehindTint = i;
            this.mBehindAlpha = 0.0f;
            this.mAnimationDuration = 1000L;
            boolean z2 = false;
            if (scrimState == ScrimState.OFF) {
                this.mAnimateChange = false;
                return;
            }
            DozeParameters dozeParameters = this.mDozeParameters;
            if (dozeParameters.mControlScreenOffAnimation && !dozeParameters.shouldShowLightRevealScrim()) {
                z2 = true;
            }
            this.mAnimateChange = z2;
        }
    }

    static {
        ScrimState scrimState = new ScrimState("UNINITIALIZED", 0);
        UNINITIALIZED = scrimState;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1("OFF", 1);
        OFF = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2("KEYGUARD", 2);
        KEYGUARD = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3("AUTH_SCRIMMED_SHADE", 3);
        AUTH_SCRIMMED_SHADE = anonymousClass3;
        AnonymousClass4 anonymousClass4 = new AnonymousClass4("AUTH_SCRIMMED", 4);
        AnonymousClass5 anonymousClass5 = new AnonymousClass5("BOUNCER", 5);
        BOUNCER = anonymousClass5;
        AnonymousClass6 anonymousClass6 = new AnonymousClass6("BOUNCER_SCRIMMED", 6);
        BOUNCER_SCRIMMED = anonymousClass6;
        AnonymousClass7 anonymousClass7 = new AnonymousClass7("SHADE_LOCKED", 7);
        SHADE_LOCKED = anonymousClass7;
        AnonymousClass8 anonymousClass8 = new AnonymousClass8("BRIGHTNESS_MIRROR", 8);
        BRIGHTNESS_MIRROR = anonymousClass8;
        AnonymousClass9 anonymousClass9 = new AnonymousClass9("AOD", 9);
        AOD = anonymousClass9;
        AnonymousClass10 anonymousClass10 = new AnonymousClass10("PULSING", 10);
        PULSING = anonymousClass10;
        AnonymousClass11 anonymousClass11 = new AnonymousClass11("UNLOCKED", 11);
        UNLOCKED = anonymousClass11;
        AnonymousClass12 anonymousClass12 = new AnonymousClass12("DREAMING", 12);
        DREAMING = anonymousClass12;
        AnonymousClass13 anonymousClass13 = new AnonymousClass13("GLANCEABLE_HUB", 13);
        GLANCEABLE_HUB = anonymousClass13;
        AnonymousClass14 anonymousClass14 = new AnonymousClass14("GLANCEABLE_HUB_OVER_DREAM", 14);
        GLANCEABLE_HUB_OVER_DREAM = anonymousClass14;
        $VALUES = new ScrimState[]{scrimState, anonymousClass1, anonymousClass2, anonymousClass3, anonymousClass4, anonymousClass5, anonymousClass6, anonymousClass7, anonymousClass8, anonymousClass9, anonymousClass10, anonymousClass11, anonymousClass12, anonymousClass13, anonymousClass14};
    }

    public ScrimState(String str, int i) {
    }

    public static ScrimState valueOf(String str) {
        return (ScrimState) Enum.valueOf(ScrimState.class, str);
    }

    public static ScrimState[] values() {
        return (ScrimState[]) $VALUES.clone();
    }

    public float getMaxLightRevealScrimAlpha() {
        return 1.0f;
    }

    public boolean isLowPowerState() {
        return this instanceof AnonymousClass1;
    }

    public void setSurfaceColor(int i) {
        this.mSurfaceColor = i;
    }

    public final void updateScrimColor(ScrimView scrimView, int i) {
        Trace.traceCounter(4096L, scrimView == this.mScrimInFront ? "front_scrim_alpha" : "back_scrim_alpha", (int) 255.0f);
        Trace.traceCounter(4096L, scrimView == this.mScrimInFront ? "front_scrim_tint" : "back_scrim_tint", Color.alpha(i));
        scrimView.getClass();
        scrimView.executeOnExecutor(new ScrimView$$ExternalSyntheticLambda4(scrimView, i));
        scrimView.setViewAlpha(1.0f);
    }

    public void prepare(ScrimState scrimState) {
    }
}
