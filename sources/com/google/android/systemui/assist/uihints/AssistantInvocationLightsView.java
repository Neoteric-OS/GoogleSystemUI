package com.google.android.systemui.assist.uihints;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.MathUtils;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.assist.ui.DisplayUtils;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PathSpecCornerPathRenderer;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AssistantInvocationLightsView extends View implements NavigationBarTransitions.DarkIntensityListener {
    public final ArrayList mAssistInvocationLights;
    public final int mColorBlue;
    public final int mColorGreen;
    public final int mColorRed;
    public final int mColorYellow;
    public final int mDarkColor;
    public final PerimeterPathGuide mGuide;
    public final int mLightColor;
    public NavigationBarControllerImpl mNavigationBarController;
    public final Paint mPaint;
    public final Path mPath;
    public boolean mRegistered;
    public final int[] mScreenLocation;
    public boolean mUseNavBarColor;
    public final int mViewHeight;

    public AssistantInvocationLightsView(Context context) {
        this(context, null);
    }

    public final void hide() {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        setVisibility(8);
        Iterator it = this.mAssistInvocationLights.iterator();
        while (it.hasNext()) {
            ((EdgeLight) it.next()).setEndpoints(0.0f, 0.0f);
        }
        if (!this.mRegistered || (navigationBarControllerImpl = this.mNavigationBarController) == null || (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) == null) {
            return;
        }
        defaultNavigationBar.mNavigationBarTransitions.mDarkIntensityListeners.remove(this);
        this.mRegistered = false;
    }

    @Override // com.android.systemui.navigationbar.views.NavigationBarTransitions.DarkIntensityListener
    public final void onDarkIntensity(float f) {
        updateDarkness(f);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        getLocationOnScreen(this.mScreenLocation);
        int[] iArr = this.mScreenLocation;
        canvas.translate(-iArr[0], -iArr[1]);
        if (this.mUseNavBarColor) {
            Iterator it = this.mAssistInvocationLights.iterator();
            while (it.hasNext()) {
                renderLight((EdgeLight) it.next(), canvas);
            }
        } else {
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            renderLight((EdgeLight) this.mAssistInvocationLights.get(0), canvas);
            renderLight((EdgeLight) this.mAssistInvocationLights.get(3), canvas);
            this.mPaint.setStrokeCap(Paint.Cap.BUTT);
            renderLight((EdgeLight) this.mAssistInvocationLights.get(1), canvas);
            renderLight((EdgeLight) this.mAssistInvocationLights.get(2), canvas);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        getLayoutParams().height = this.mViewHeight;
        requestLayout();
    }

    public final void onInvocationProgress(float f) {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        if (f <= 1.0f) {
            if (f == 0.0f) {
                setVisibility(8);
            } else {
                if (!this.mRegistered && (navigationBarControllerImpl = this.mNavigationBarController) != null && (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) != null) {
                    NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
                    navigationBarTransitions.mDarkIntensityListeners.add(this);
                    updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
                    this.mRegistered = true;
                }
                PerimeterPathGuide.RegionAttributes[] regionAttributesArr = this.mGuide.mRegions;
                float f2 = regionAttributesArr[7].normalizedLength;
                float f3 = (f2 - (0.6f * f2)) / 2.0f;
                float lerp = MathUtils.lerp(0.0f, regionAttributesArr[0].normalizedLength / 4.0f, f);
                float f4 = 1.0f - f;
                float f5 = ((-f2) + f3) * f4;
                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f3, f4, this.mGuide.mRegions[0].normalizedLength);
                float f6 = f5 + lerp;
                setLight(0, f5, f6);
                float f7 = 2.0f * lerp;
                setLight(1, f6, f5 + f7);
                float f8 = m - lerp;
                setLight(2, m - f7, f8);
                setLight(3, f8, m);
                setVisibility(0);
            }
            invalidate();
        } else {
            PerimeterPathGuide.RegionAttributes[] regionAttributesArr2 = this.mGuide.mRegions;
            float f9 = (regionAttributesArr2[7].normalizedLength * 0.6f) / 2.0f;
            float f10 = regionAttributesArr2[0].normalizedLength / 4.0f;
            float lerp2 = MathUtils.lerp(f9, f10, 1.0f - (f - 1.0f));
            setLight(0, f10 - lerp2, f10);
            float f11 = 2.0f * f10;
            setLight(1, f10, f11);
            float f12 = f10 * 3.0f;
            setLight(2, f11, f12);
            setLight(3, f12, lerp2 + f12);
            setVisibility(0);
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int rotation = getContext().getDisplay().getRotation();
        PerimeterPathGuide perimeterPathGuide = this.mGuide;
        if (rotation != perimeterPathGuide.mRotation) {
            if (rotation != 0 && rotation != 1 && rotation != 2 && rotation != 3) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid rotation provided: ", "PerimeterPathGuide", rotation);
            } else {
                perimeterPathGuide.mRotation = rotation;
                perimeterPathGuide.computeRegions();
            }
        }
    }

    public final void renderLight(EdgeLight edgeLight, Canvas canvas) {
        float f = edgeLight.mLength;
        float f2 = 0.0f;
        if (f > 0.0f) {
            PerimeterPathGuide perimeterPathGuide = this.mGuide;
            Path path = this.mPath;
            float f3 = edgeLight.mStart;
            float f4 = f + f3;
            perimeterPathGuide.getClass();
            path.reset();
            float f5 = ((f3 % 1.0f) + 1.0f) % 1.0f;
            float f6 = ((f4 % 1.0f) + 1.0f) % 1.0f;
            if (f5 > f6) {
                perimeterPathGuide.strokeSegmentInternal(path, f5, 1.0f);
            } else {
                f2 = f5;
            }
            perimeterPathGuide.strokeSegmentInternal(path, f2, f6);
            this.mPaint.setColor(edgeLight.mColor);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    public final void setLight(int i, float f, float f2) {
        if (i < 0 || i >= 4) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("invalid invocation light index: ", "InvocationLightsView", i);
        }
        ((EdgeLight) this.mAssistInvocationLights.get(i)).setEndpoints(f, f2);
    }

    public final void updateDarkness(float f) {
        if (this.mUseNavBarColor) {
            int intValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mLightColor), Integer.valueOf(this.mDarkColor))).intValue();
            Iterator it = this.mAssistInvocationLights.iterator();
            boolean z = true;
            while (it.hasNext()) {
                z &= ((EdgeLight) it.next()).setColor(intValue);
            }
            if (z) {
                invalidate();
            }
        }
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        int i4;
        this.mAssistInvocationLights = new ArrayList();
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPath = new Path();
        this.mScreenLocation = new int[2];
        this.mRegistered = false;
        this.mUseNavBarColor = true;
        context.getDisplay().getRealMetrics(new DisplayMetrics());
        int ceil = (int) Math.ceil(r2.density * 3.0f);
        paint.setStrokeWidth(ceil);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setAntiAlias(true);
        Display display = context.getDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        int rotation = display.getRotation();
        if (rotation != 0 && rotation != 2) {
            i3 = displayMetrics.heightPixels;
        } else {
            i3 = displayMetrics.widthPixels;
        }
        int i5 = i3;
        Display display2 = context.getDisplay();
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        display2.getRealMetrics(displayMetrics2);
        int rotation2 = display2.getRotation();
        if (rotation2 != 0 && rotation2 != 2) {
            i4 = displayMetrics2.widthPixels;
        } else {
            i4 = displayMetrics2.heightPixels;
        }
        this.mGuide = new PerimeterPathGuide(context, new PathSpecCornerPathRenderer(context), ceil / 2, i5, i4);
        int max = Math.max(DisplayUtils.getCornerRadiusBottom(context), DisplayUtils.getCornerRadiusTop(context));
        context.getDisplay().getRealMetrics(new DisplayMetrics());
        this.mViewHeight = Math.max(max, (int) Math.ceil(3.0f * r13.density));
        int themeAttr = Utils.getThemeAttr(R.attr.darkIconTheme, ((View) this).mContext);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(((View) this).mContext, Utils.getThemeAttr(R.attr.lightIconTheme, ((View) this).mContext));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(((View) this).mContext, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper);
        this.mDarkColor = Utils.getColorAttrDefaultColor(R.attr.singleToneColor, 0, contextThemeWrapper2);
        for (int i6 = 0; i6 < 4; i6++) {
            ArrayList arrayList = this.mAssistInvocationLights;
            EdgeLight edgeLight = new EdgeLight();
            edgeLight.mColor = 0;
            edgeLight.mStart = 0.0f;
            edgeLight.mLength = 0.0f;
            arrayList.add(edgeLight);
        }
        Resources resources = context.getResources();
        this.mColorRed = resources.getColor(R.color.edge_light_red);
        this.mColorYellow = resources.getColor(R.color.edge_light_yellow);
        this.mColorBlue = resources.getColor(R.color.edge_light_blue);
        this.mColorGreen = resources.getColor(R.color.edge_light_green);
    }
}
