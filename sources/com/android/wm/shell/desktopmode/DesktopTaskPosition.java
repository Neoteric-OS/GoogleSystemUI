package com.android.wm.shell.desktopmode;

import android.graphics.Point;
import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DesktopTaskPosition {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BottomLeft extends DesktopTaskPosition {
        public static final BottomLeft INSTANCE = new BottomLeft();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof BottomLeft);
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final Point getTopLeftCoordinates(Rect rect, Rect rect2) {
            return new Point(rect.left, rect.bottom - rect2.height());
        }

        public final int hashCode() {
            return -1215550673;
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final DesktopTaskPosition next() {
            return TopRight.INSTANCE;
        }

        public final String toString() {
            return "BottomLeft";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BottomRight extends DesktopTaskPosition {
        public static final BottomRight INSTANCE = new BottomRight();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof BottomRight);
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final Point getTopLeftCoordinates(Rect rect, Rect rect2) {
            return new Point(rect.right - rect2.width(), rect.bottom - rect2.height());
        }

        public final int hashCode() {
            return 978295796;
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final DesktopTaskPosition next() {
            return TopLeft.INSTANCE;
        }

        public final String toString() {
            return "BottomRight";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Center extends DesktopTaskPosition {
        public static final Center INSTANCE = new Center();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Center);
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final Point getTopLeftCoordinates(Rect rect, Rect rect2) {
            return new Point((rect.width() - rect2.width()) / 2, (int) (((rect.height() - rect2.height()) * 0.375d) + rect.top));
        }

        public final int hashCode() {
            return 2013400370;
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final DesktopTaskPosition next() {
            return BottomRight.INSTANCE;
        }

        public final String toString() {
            return "Center";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TopLeft extends DesktopTaskPosition {
        public static final TopLeft INSTANCE = new TopLeft();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TopLeft);
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final Point getTopLeftCoordinates(Rect rect, Rect rect2) {
            return new Point(rect.left, rect.top);
        }

        public final int hashCode() {
            return 480509375;
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final DesktopTaskPosition next() {
            return BottomLeft.INSTANCE;
        }

        public final String toString() {
            return "TopLeft";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TopRight extends DesktopTaskPosition {
        public static final TopRight INSTANCE = new TopRight();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TopRight);
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final Point getTopLeftCoordinates(Rect rect, Rect rect2) {
            return new Point(rect.right - rect2.width(), rect.top);
        }

        public final int hashCode() {
            return 2016549732;
        }

        @Override // com.android.wm.shell.desktopmode.DesktopTaskPosition
        public final DesktopTaskPosition next() {
            return Center.INSTANCE;
        }

        public final String toString() {
            return "TopRight";
        }
    }

    public abstract Point getTopLeftCoordinates(Rect rect, Rect rect2);

    public abstract DesktopTaskPosition next();
}
