package com.android.systemui.navigationbar.views.buttons;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.FloatProperty;
import android.view.MotionEvent;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeadZone {
    public static final AnonymousClass1 FLASH_PROPERTY = new AnonymousClass1("DeadZoneFlash");
    public int mDecay;
    public final int mDisplayId;
    public int mDisplayRotation;
    public int mHold;
    public long mLastPokeTime;
    public final NavigationBarView mNavigationBarView;
    public boolean mShouldFlash;
    public int mSizeMax;
    public int mSizeMin;
    public final boolean mUseDeadZone;
    public boolean mVertical;
    public float mFlashFrac = 0.0f;
    public final AnonymousClass2 mDebugFlash = new Runnable() { // from class: com.android.systemui.navigationbar.views.buttons.DeadZone.2
        @Override // java.lang.Runnable
        public final void run() {
            ObjectAnimator.ofFloat(DeadZone.this, DeadZone.FLASH_PROPERTY, 1.0f, 0.0f).setDuration(150L).start();
        }
    };
    public final NavigationBarControllerImpl mNavBarController = (NavigationBarControllerImpl) Dependency.sDependency.getDependencyInner(NavigationBarControllerImpl.class);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.views.buttons.DeadZone$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((DeadZone) obj).mFlashFrac);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            DeadZone deadZone = (DeadZone) obj;
            deadZone.mFlashFrac = f;
            deadZone.mNavigationBarView.postInvalidate();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.navigationbar.views.buttons.DeadZone$2] */
    public DeadZone(NavigationBarView navigationBarView) {
        this.mUseDeadZone = navigationBarView.getResources().getBoolean(R.bool.config_useDeadZone);
        this.mNavigationBarView = navigationBarView;
        this.mDisplayId = navigationBarView.getContext().getDisplayId();
        onConfigurationChanged(0);
    }

    public final float getSize(long j) {
        int m;
        int i = this.mSizeMax;
        if (i == 0) {
            return 0.0f;
        }
        long j2 = j - this.mLastPokeTime;
        int i2 = this.mHold;
        int i3 = this.mDecay;
        if (j2 > i2 + i3) {
            m = this.mSizeMin;
        } else {
            if (j2 < i2) {
                return i;
            }
            float f = i;
            m = (int) AndroidFlingSpline$$ExternalSyntheticOutline0.m(this.mSizeMin, f, (j2 - i2) / i3, f);
        }
        return m;
    }

    public final void onConfigurationChanged(int i) {
        boolean z = this.mUseDeadZone;
        if (z) {
            this.mDisplayRotation = i;
            NavigationBarView navigationBarView = this.mNavigationBarView;
            Resources resources = navigationBarView.getResources();
            this.mHold = resources.getInteger(R.integer.navigation_bar_deadzone_hold);
            this.mDecay = resources.getInteger(R.integer.navigation_bar_deadzone_decay);
            this.mSizeMin = resources.getDimensionPixelSize(R.dimen.navigation_bar_deadzone_size);
            this.mSizeMax = resources.getDimensionPixelSize(R.dimen.navigation_bar_deadzone_size_max);
            this.mVertical = resources.getInteger(R.integer.navigation_bar_deadzone_orientation) == 1;
            boolean z2 = resources.getBoolean(R.bool.config_dead_zone_flash);
            if (z) {
                this.mShouldFlash = z2;
                this.mFlashFrac = 0.0f;
                navigationBarView.postInvalidate();
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mUseDeadZone || motionEvent.getToolType(0) == 3) {
            return false;
        }
        int action = motionEvent.getAction();
        NavigationBarView navigationBarView = this.mNavigationBarView;
        if (action == 4) {
            this.mLastPokeTime = motionEvent.getEventTime();
            if (this.mShouldFlash) {
                navigationBarView.postInvalidate();
            }
            return true;
        }
        if (action == 0) {
            this.mNavBarController.touchAutoDim(this.mDisplayId);
            int size = (int) getSize(motionEvent.getEventTime());
            if (!this.mVertical ? motionEvent.getY() < size : !(this.mDisplayRotation != 3 ? motionEvent.getX() >= size : motionEvent.getX() <= navigationBarView.getWidth() - size)) {
                motionEvent.getX();
                motionEvent.getY();
                if (this.mShouldFlash) {
                    navigationBarView.post(this.mDebugFlash);
                    navigationBarView.postInvalidate();
                }
                return true;
            }
        }
        return false;
    }
}
