package com.airbnb.lottie.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontCharacter {
    public final char character;
    public final String fontFamily;
    public final List shapes;
    public final String style;
    public final double width;

    public FontCharacter(List list, char c, double d, String str, String str2) {
        this.shapes = list;
        this.character = c;
        this.width = d;
        this.style = str;
        this.fontFamily = str2;
    }

    public static int hashFor(char c, String str, String str2) {
        return str2.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(str, c * 31, 31);
    }

    public final int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}
