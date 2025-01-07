package com.android.settingslib.widget;

import android.R;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.util.PathParser;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AdaptiveIconShapeDrawable extends ShapeDrawable {
    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        setShape(new PathShape(new Path(PathParser.createPathFromPathData(resources.getString(R.string.config_mainBuiltInDisplayCutout))), 100.0f, 100.0f));
    }
}
