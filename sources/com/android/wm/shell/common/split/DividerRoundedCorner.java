package com.android.wm.shell.common.split;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.RoundedCorner;
import android.view.View;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class DividerRoundedCorner extends View {
    public InvertedRoundedCornerDrawInfo mBottomLeftCorner;
    public InvertedRoundedCornerDrawInfo mBottomRightCorner;
    public final Paint mDividerBarBackground;
    public final int mDividerWidth;
    public boolean mIsLeftRightSplit;
    public final Point mStartPos;
    public InvertedRoundedCornerDrawInfo mTopLeftCorner;
    public InvertedRoundedCornerDrawInfo mTopRightCorner;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InvertedRoundedCornerDrawInfo {
        public final int mCornerPosition;
        public final Path mPath;
        public final int mRadius;

        /* renamed from: -$$Nest$mcalculateStartPos, reason: not valid java name */
        public static void m902$$Nest$mcalculateStartPos(InvertedRoundedCornerDrawInfo invertedRoundedCornerDrawInfo, Point point) {
            DividerRoundedCorner dividerRoundedCorner = DividerRoundedCorner.this;
            boolean z = dividerRoundedCorner.mIsLeftRightSplit;
            int i = invertedRoundedCornerDrawInfo.mCornerPosition;
            int i2 = 0;
            int i3 = invertedRoundedCornerDrawInfo.mRadius;
            if (z) {
                point.x = (i == 0 || i == 3) ? (dividerRoundedCorner.getWidth() / 2) + (dividerRoundedCorner.mDividerWidth / 2) : ((dividerRoundedCorner.getWidth() / 2) - (dividerRoundedCorner.mDividerWidth / 2)) - i3;
                if (i != 0 && i != 1) {
                    i2 = dividerRoundedCorner.getHeight() - i3;
                }
                point.y = i2;
                return;
            }
            if (i != 0 && i != 3) {
                i2 = dividerRoundedCorner.getWidth() - i3;
            }
            point.x = i2;
            point.y = (i == 0 || i == 1) ? (dividerRoundedCorner.mDividerWidth / 2) + (dividerRoundedCorner.getHeight() / 2) : ((dividerRoundedCorner.getHeight() / 2) - (dividerRoundedCorner.mDividerWidth / 2)) - i3;
        }

        public InvertedRoundedCornerDrawInfo(int i) {
            Path path = new Path();
            this.mPath = path;
            this.mCornerPosition = i;
            RoundedCorner roundedCorner = DividerRoundedCorner.this.getDisplay().getRoundedCorner(i);
            int radius = roundedCorner == null ? 0 : roundedCorner.getRadius();
            this.mRadius = radius;
            Path path2 = new Path();
            float f = radius;
            Path.Direction direction = Path.Direction.CW;
            path2.addRect(0.0f, 0.0f, f, f, direction);
            Path path3 = new Path();
            path3.addCircle(i == 0 || i == 3 ? f : 0.0f, (i == 0 || i == 1) ? f : 0.0f, f, direction);
            path.op(path2, path3, Path.Op.DIFFERENCE);
        }
    }

    public DividerRoundedCorner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStartPos = new Point();
        this.mDividerWidth = getResources().getDimensionPixelSize(R.dimen.split_divider_bar_width);
        Paint paint = new Paint();
        this.mDividerBarBackground = paint;
        paint.setColor(getResources().getColor(R.color.split_divider_background, null));
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mTopLeftCorner = new InvertedRoundedCornerDrawInfo(0);
        this.mTopRightCorner = new InvertedRoundedCornerDrawInfo(1);
        this.mBottomLeftCorner = new InvertedRoundedCornerDrawInfo(3);
        this.mBottomRightCorner = new InvertedRoundedCornerDrawInfo(2);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.save();
        InvertedRoundedCornerDrawInfo.m902$$Nest$mcalculateStartPos(this.mTopLeftCorner, this.mStartPos);
        Point point = this.mStartPos;
        canvas.translate(point.x, point.y);
        canvas.drawPath(this.mTopLeftCorner.mPath, this.mDividerBarBackground);
        Point point2 = this.mStartPos;
        canvas.translate(-point2.x, -point2.y);
        InvertedRoundedCornerDrawInfo.m902$$Nest$mcalculateStartPos(this.mTopRightCorner, this.mStartPos);
        Point point3 = this.mStartPos;
        canvas.translate(point3.x, point3.y);
        canvas.drawPath(this.mTopRightCorner.mPath, this.mDividerBarBackground);
        Point point4 = this.mStartPos;
        canvas.translate(-point4.x, -point4.y);
        InvertedRoundedCornerDrawInfo.m902$$Nest$mcalculateStartPos(this.mBottomLeftCorner, this.mStartPos);
        Point point5 = this.mStartPos;
        canvas.translate(point5.x, point5.y);
        canvas.drawPath(this.mBottomLeftCorner.mPath, this.mDividerBarBackground);
        Point point6 = this.mStartPos;
        canvas.translate(-point6.x, -point6.y);
        InvertedRoundedCornerDrawInfo.m902$$Nest$mcalculateStartPos(this.mBottomRightCorner, this.mStartPos);
        Point point7 = this.mStartPos;
        canvas.translate(point7.x, point7.y);
        canvas.drawPath(this.mBottomRightCorner.mPath, this.mDividerBarBackground);
        canvas.restore();
    }
}
