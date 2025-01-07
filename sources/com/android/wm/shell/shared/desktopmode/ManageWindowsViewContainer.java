package com.android.wm.shell.shared.desktopmode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.hardware.HardwareBuffer;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.window.TaskSnapshot;
import com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ManageWindowsViewContainer {
    public final Context context;
    public final int menuBackgroundColor;
    public ManageWindowsView menuView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ManageWindowsView {
        public final Context context;
        public int menuHeight;
        public int menuWidth;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 onIconClickListener;
        public Function0 onOutsideClickListener;
        public final LinearLayout rootView;

        public ManageWindowsView(int i, Context context) {
            this.context = context;
            LinearLayout linearLayout = new LinearLayout(context);
            this.rootView = linearLayout;
            linearLayout.setOrientation(1);
            ShapeDrawable shapeDrawable = new ShapeDrawable();
            float dimensionPixelSize = getDimensionPixelSize(26.0f);
            float[] fArr = new float[8];
            for (int i2 = 0; i2 < 8; i2++) {
                fArr[i2] = dimensionPixelSize;
            }
            shapeDrawable.setShape(new RoundRectShape(fArr, null, null));
            shapeDrawable.getPaint().setColor(i);
            this.rootView.setBackground(shapeDrawable);
            this.rootView.setElevation(getDimensionPixelSize(1.0f));
            this.rootView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer.ManageWindowsView.2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    Function0 function0;
                    if (motionEvent.getActionMasked() != 4 || (function0 = ManageWindowsView.this.onOutsideClickListener) == null) {
                        return true;
                    }
                    function0.invoke();
                    return true;
                }
            });
        }

        public final float getDimensionPixelSize(float f) {
            return TypedValue.applyDimension(1, f, this.context.getResources().getDisplayMetrics());
        }
    }

    public ManageWindowsViewContainer(int i, Context context) {
        this.context = context;
        this.menuBackgroundColor = i;
    }

    public abstract void addToContainer(ManageWindowsView manageWindowsView);

    public abstract void close();

    public final void show(List list, DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, Function0 function0) {
        final ManageWindowsView manageWindowsView = new ManageWindowsView(this.menuBackgroundColor, this.context);
        manageWindowsView.onOutsideClickListener = function0;
        manageWindowsView.onIconClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13;
        int i = 0;
        manageWindowsView.menuWidth = 0;
        manageWindowsView.menuHeight = 0;
        manageWindowsView.rootView.removeAllViews();
        float dimensionPixelSize = manageWindowsView.getDimensionPixelSize(127.5f);
        float dimensionPixelSize2 = manageWindowsView.getDimensionPixelSize(204.0f);
        float dimensionPixelSize3 = manageWindowsView.getDimensionPixelSize(16.0f);
        float dimensionPixelSize4 = manageWindowsView.getDimensionPixelSize(16.0f);
        Iterator it = list.iterator();
        int i2 = 0;
        LinearLayout linearLayout = null;
        while (it.hasNext()) {
            int i3 = i2 + 1;
            Pair pair = (Pair) it.next();
            final int intValue = ((Number) pair.getFirst()).intValue();
            TaskSnapshot taskSnapshot = (TaskSnapshot) pair.getSecond();
            if (i2 % 3 == 0) {
                linearLayout = new LinearLayout(manageWindowsView.context);
                linearLayout.setOrientation(i);
                manageWindowsView.rootView.addView(linearLayout);
                manageWindowsView.menuHeight += (int) (dimensionPixelSize + dimensionPixelSize4);
            }
            Bitmap wrapHardwareBuffer = Bitmap.wrapHardwareBuffer(taskSnapshot.getHardwareBuffer(), taskSnapshot.getColorSpace());
            final Bitmap createScaledBitmap = wrapHardwareBuffer != null ? Bitmap.createScaledBitmap(wrapHardwareBuffer, (int) dimensionPixelSize2, (int) dimensionPixelSize, true) : null;
            final SurfaceView surfaceView = new SurfaceView(manageWindowsView.context);
            surfaceView.setCornerRadius(dimensionPixelSize3);
            surfaceView.setZOrderOnTop(true);
            surfaceView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer$ManageWindowsView$generateIconViews$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda132 = ManageWindowsViewContainer.ManageWindowsView.this.onIconClickListener;
                    if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda132 != null) {
                        desktopModeWindowDecorViewModel$$ExternalSyntheticLambda132.invoke(Integer.valueOf(intValue));
                    }
                }
            });
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams((int) dimensionPixelSize2, (int) dimensionPixelSize);
            int i4 = (int) dimensionPixelSize4;
            marginLayoutParams.setMarginStart(i4);
            marginLayoutParams.topMargin = i4;
            surfaceView.setLayoutParams(marginLayoutParams);
            if (i2 < 3) {
                manageWindowsView.menuWidth += (int) (dimensionPixelSize2 + dimensionPixelSize4);
            }
            if (linearLayout != null) {
                linearLayout.addView(surfaceView);
            }
            surfaceView.requestLayout();
            if (linearLayout != null) {
                linearLayout.post(new Runnable() { // from class: com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer$ManageWindowsView$generateIconViews$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Surface surface = surfaceView.getHolder().getSurface();
                        Bitmap bitmap = createScaledBitmap;
                        HardwareBuffer hardwareBuffer = bitmap != null ? bitmap.getHardwareBuffer() : null;
                        Bitmap bitmap2 = createScaledBitmap;
                        surface.attachAndQueueBufferWithColorSpace(hardwareBuffer, bitmap2 != null ? bitmap2.getColorSpace() : null);
                    }
                });
            }
            i2 = i3;
            i = 0;
        }
        int i5 = (int) dimensionPixelSize4;
        manageWindowsView.menuWidth += i5;
        manageWindowsView.menuHeight += i5;
        this.menuView = manageWindowsView;
        addToContainer(manageWindowsView);
    }
}
