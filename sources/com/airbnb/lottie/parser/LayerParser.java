package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.parser.moshi.JsonUtf8Reader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.android.app.viewcapture.data.ViewNode;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayerParser {
    public static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd", "ao", "bm");
    public static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");
    public static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("ty", "nm");

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0062. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v14 */
    public static Layer parse(JsonUtf8Reader jsonUtf8Reader, LottieComposition lottieComposition) {
        String str;
        AnimatableTransform animatableTransform;
        String str2;
        int i;
        char c;
        String str3;
        String str4;
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableFloatValue animatableFloatValue3;
        AnimatableFloatValue animatableFloatValue4;
        int i2;
        int i3 = 2;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        LBlendMode lBlendMode = LBlendMode.NORMAL;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        jsonUtf8Reader.beginObject();
        Float valueOf = Float.valueOf(0.0f);
        Float valueOf2 = Float.valueOf(1.0f);
        String str5 = "UNSET";
        long j = 0;
        boolean z = false;
        Layer.MatteType matteType2 = matteType;
        LBlendMode lBlendMode2 = lBlendMode;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        boolean z3 = false;
        long j2 = -1;
        String str6 = null;
        Layer.LayerType layerType = null;
        String str7 = null;
        float f6 = 1.0f;
        AnimatableTextFrame animatableTextFrame = null;
        AnimatableTextProperties animatableTextProperties = null;
        AnimatableFloatValue animatableFloatValue5 = null;
        BlurEffect blurEffect = null;
        DropShadowEffect dropShadowEffect = null;
        AnimatableTransform animatableTransform2 = null;
        while (jsonUtf8Reader.hasNext()) {
            switch (jsonUtf8Reader.selectName(NAMES)) {
                case 0:
                    str5 = jsonUtf8Reader.nextString();
                    i3 = 2;
                    break;
                case 1:
                    str2 = str6;
                    j = jsonUtf8Reader.nextInt();
                    str6 = str2;
                    i3 = 2;
                    break;
                case 2:
                    str7 = jsonUtf8Reader.nextString();
                    i3 = 2;
                    break;
                case 3:
                    str2 = str6;
                    int nextInt = jsonUtf8Reader.nextInt();
                    layerType = nextInt < 6 ? Layer.LayerType.values()[nextInt] : Layer.LayerType.UNKNOWN;
                    str6 = str2;
                    i3 = 2;
                    break;
                case 4:
                    str2 = str6;
                    j2 = jsonUtf8Reader.nextInt();
                    str6 = str2;
                    i3 = 2;
                    break;
                case 5:
                    i4 = (int) (Utils.dpScale() * jsonUtf8Reader.nextInt());
                    i3 = 2;
                    break;
                case 6:
                    i5 = (int) (Utils.dpScale() * jsonUtf8Reader.nextInt());
                    i3 = 2;
                    break;
                case 7:
                    i6 = Color.parseColor(jsonUtf8Reader.nextString());
                    i3 = 2;
                    break;
                case 8:
                    animatableTransform2 = AnimatableTransformParser.parse(jsonUtf8Reader, lottieComposition);
                    i3 = 2;
                    break;
                case 9:
                    str2 = str6;
                    int nextInt2 = jsonUtf8Reader.nextInt();
                    if (nextInt2 >= Layer.MatteType.values().length) {
                        lottieComposition.addWarning("Unsupported matte type: " + nextInt2);
                    } else {
                        matteType2 = Layer.MatteType.values()[nextInt2];
                        int ordinal = matteType2.ordinal();
                        if (ordinal == 3) {
                            lottieComposition.addWarning("Unsupported matte type: Luma");
                        } else if (ordinal == 4) {
                            lottieComposition.addWarning("Unsupported matte type: Luma Inverted");
                        }
                        lottieComposition.maskAndMatteCount++;
                    }
                    str6 = str2;
                    i3 = 2;
                    break;
                case 10:
                    str2 = str6;
                    Mask.MaskMode maskMode = null;
                    jsonUtf8Reader.beginArray();
                    while (jsonUtf8Reader.hasNext()) {
                        jsonUtf8Reader.beginObject();
                        Mask.MaskMode maskMode2 = maskMode;
                        Mask.MaskMode maskMode3 = maskMode2;
                        AnimatableIntegerValue animatableIntegerValue = maskMode3;
                        boolean z4 = false;
                        AnimatableShapeValue animatableShapeValue = maskMode3;
                        while (jsonUtf8Reader.hasNext()) {
                            String nextName = jsonUtf8Reader.nextName();
                            nextName.getClass();
                            switch (nextName.hashCode()) {
                                case 111:
                                    if (nextName.equals("o")) {
                                        i = 0;
                                        break;
                                    }
                                    i = -1;
                                    break;
                                case 3588:
                                    if (nextName.equals("pt")) {
                                        i = 1;
                                        break;
                                    }
                                    i = -1;
                                    break;
                                case 104433:
                                    if (nextName.equals("inv")) {
                                        i = i3;
                                        break;
                                    }
                                    i = -1;
                                    break;
                                case 3357091:
                                    if (nextName.equals("mode")) {
                                        i = 3;
                                        break;
                                    }
                                    i = -1;
                                    break;
                                default:
                                    i = -1;
                                    break;
                            }
                            switch (i) {
                                case 0:
                                    animatableIntegerValue = AnimatableValueParser.parseInteger(jsonUtf8Reader, lottieComposition);
                                    break;
                                case 1:
                                    animatableShapeValue = new AnimatableShapeValue(KeyframesParser.parse(jsonUtf8Reader, lottieComposition, Utils.dpScale(), ShapeDataParser.INSTANCE, false));
                                    break;
                                case 2:
                                    z4 = jsonUtf8Reader.nextBoolean();
                                    break;
                                case 3:
                                    String nextString = jsonUtf8Reader.nextString();
                                    nextString.getClass();
                                    Mask.MaskMode maskMode4 = Mask.MaskMode.MASK_MODE_ADD;
                                    switch (nextString.hashCode()) {
                                        case 97:
                                            if (nextString.equals("a")) {
                                                c = 0;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        case 105:
                                            if (nextString.equals("i")) {
                                                c = 1;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        case 110:
                                            if (nextString.equals("n")) {
                                                c = 2;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        case 115:
                                            if (nextString.equals("s")) {
                                                c = 3;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        default:
                                            c = 65535;
                                            break;
                                    }
                                    switch (c) {
                                        case 0:
                                            maskMode2 = maskMode4;
                                            break;
                                        case 1:
                                            lottieComposition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                                            maskMode2 = Mask.MaskMode.MASK_MODE_INTERSECT;
                                            break;
                                        case 2:
                                            maskMode2 = Mask.MaskMode.MASK_MODE_NONE;
                                            break;
                                        case 3:
                                            maskMode2 = Mask.MaskMode.MASK_MODE_SUBTRACT;
                                            break;
                                        default:
                                            Logger.warning("Unknown mask mode " + nextName + ". Defaulting to Add.");
                                            maskMode2 = maskMode4;
                                            break;
                                    }
                                    break;
                                default:
                                    jsonUtf8Reader.skipValue();
                                    break;
                            }
                            i3 = 2;
                            animatableShapeValue = animatableShapeValue;
                        }
                        jsonUtf8Reader.endObject();
                        arrayList.add(new Mask(maskMode2, animatableShapeValue, animatableIntegerValue, z4));
                        maskMode = null;
                        i3 = 2;
                    }
                    z = false;
                    lottieComposition.maskAndMatteCount += arrayList.size();
                    jsonUtf8Reader.endArray();
                    str6 = str2;
                    i3 = 2;
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    str2 = str6;
                    jsonUtf8Reader.beginArray();
                    while (jsonUtf8Reader.hasNext()) {
                        ContentModel parse = ContentModelParser.parse(jsonUtf8Reader, lottieComposition);
                        if (parse != null) {
                            arrayList2.add(parse);
                        }
                    }
                    jsonUtf8Reader.endArray();
                    z = false;
                    str6 = str2;
                    i3 = 2;
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    str4 = str6;
                    jsonUtf8Reader.beginObject();
                    while (jsonUtf8Reader.hasNext()) {
                        int selectName = jsonUtf8Reader.selectName(TEXT_NAMES);
                        if (selectName == 0) {
                            animatableTextFrame = new AnimatableTextFrame(KeyframesParser.parse(jsonUtf8Reader, lottieComposition, Utils.dpScale(), DocumentDataParser.INSTANCE, false));
                        } else if (selectName != 1) {
                            jsonUtf8Reader.skipName();
                            jsonUtf8Reader.skipValue();
                        } else {
                            jsonUtf8Reader.beginArray();
                            if (jsonUtf8Reader.hasNext()) {
                                JsonReader.Options options = AnimatableTextPropertiesParser.PROPERTIES_NAMES;
                                jsonUtf8Reader.beginObject();
                                AnimatableTextProperties animatableTextProperties2 = null;
                                while (jsonUtf8Reader.hasNext()) {
                                    if (jsonUtf8Reader.selectName(AnimatableTextPropertiesParser.PROPERTIES_NAMES) != 0) {
                                        jsonUtf8Reader.skipName();
                                        jsonUtf8Reader.skipValue();
                                    } else {
                                        jsonUtf8Reader.beginObject();
                                        AnimatableColorValue animatableColorValue = null;
                                        AnimatableColorValue animatableColorValue2 = null;
                                        AnimatableFloatValue animatableFloatValue6 = null;
                                        AnimatableFloatValue animatableFloatValue7 = null;
                                        while (jsonUtf8Reader.hasNext()) {
                                            int selectName2 = jsonUtf8Reader.selectName(AnimatableTextPropertiesParser.ANIMATABLE_PROPERTIES_NAMES);
                                            if (selectName2 == 0) {
                                                animatableColorValue = AnimatableValueParser.parseColor(jsonUtf8Reader, lottieComposition);
                                            } else if (selectName2 == 1) {
                                                animatableColorValue2 = AnimatableValueParser.parseColor(jsonUtf8Reader, lottieComposition);
                                            } else if (selectName2 == i3) {
                                                animatableFloatValue6 = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, true);
                                            } else if (selectName2 != 3) {
                                                jsonUtf8Reader.skipName();
                                                jsonUtf8Reader.skipValue();
                                            } else {
                                                animatableFloatValue7 = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, true);
                                            }
                                        }
                                        jsonUtf8Reader.endObject();
                                        animatableTextProperties2 = new AnimatableTextProperties(animatableColorValue, animatableColorValue2, animatableFloatValue6, animatableFloatValue7);
                                    }
                                }
                                jsonUtf8Reader.endObject();
                                if (animatableTextProperties2 == null) {
                                    animatableTextProperties2 = new AnimatableTextProperties(null, null, null, null);
                                }
                                animatableTextProperties = animatableTextProperties2;
                            }
                            while (jsonUtf8Reader.hasNext()) {
                                jsonUtf8Reader.skipValue();
                            }
                            jsonUtf8Reader.endArray();
                        }
                    }
                    jsonUtf8Reader.endObject();
                    str6 = str4;
                    z = false;
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    str4 = str6;
                    jsonUtf8Reader.beginArray();
                    ArrayList arrayList3 = new ArrayList();
                    while (jsonUtf8Reader.hasNext()) {
                        jsonUtf8Reader.beginObject();
                        while (jsonUtf8Reader.hasNext()) {
                            int selectName3 = jsonUtf8Reader.selectName(EFFECTS_NAMES);
                            if (selectName3 == 0) {
                                int nextInt3 = jsonUtf8Reader.nextInt();
                                if (nextInt3 == 29) {
                                    JsonReader.Options options2 = BlurEffectParser.BLUR_EFFECT_NAMES;
                                    blurEffect = null;
                                    while (jsonUtf8Reader.hasNext()) {
                                        if (jsonUtf8Reader.selectName(BlurEffectParser.BLUR_EFFECT_NAMES) != 0) {
                                            jsonUtf8Reader.skipName();
                                            jsonUtf8Reader.skipValue();
                                        } else {
                                            jsonUtf8Reader.beginArray();
                                            while (jsonUtf8Reader.hasNext()) {
                                                jsonUtf8Reader.beginObject();
                                                boolean z5 = z ? 1 : 0;
                                                BlurEffect blurEffect2 = null;
                                                while (jsonUtf8Reader.hasNext()) {
                                                    int selectName4 = jsonUtf8Reader.selectName(BlurEffectParser.INNER_BLUR_EFFECT_NAMES);
                                                    if (selectName4 == 0) {
                                                        z5 = jsonUtf8Reader.nextInt() == 0 ? true : z ? 1 : 0;
                                                    } else if (selectName4 != 1) {
                                                        jsonUtf8Reader.skipName();
                                                        jsonUtf8Reader.skipValue();
                                                    } else if (z5) {
                                                        blurEffect2 = new BlurEffect(AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, true));
                                                    } else {
                                                        jsonUtf8Reader.skipValue();
                                                    }
                                                }
                                                jsonUtf8Reader.endObject();
                                                if (blurEffect2 != null) {
                                                    blurEffect = blurEffect2;
                                                }
                                            }
                                            jsonUtf8Reader.endArray();
                                        }
                                    }
                                } else if (nextInt3 == 25) {
                                    DropShadowEffectParser dropShadowEffectParser = new DropShadowEffectParser();
                                    while (jsonUtf8Reader.hasNext()) {
                                        if (jsonUtf8Reader.selectName(DropShadowEffectParser.DROP_SHADOW_EFFECT_NAMES) != 0) {
                                            jsonUtf8Reader.skipName();
                                            jsonUtf8Reader.skipValue();
                                        } else {
                                            jsonUtf8Reader.beginArray();
                                            while (jsonUtf8Reader.hasNext()) {
                                                jsonUtf8Reader.beginObject();
                                                String str8 = "";
                                                while (jsonUtf8Reader.hasNext()) {
                                                    int selectName5 = jsonUtf8Reader.selectName(DropShadowEffectParser.INNER_EFFECT_NAMES);
                                                    if (selectName5 == 0) {
                                                        str8 = jsonUtf8Reader.nextString();
                                                    } else if (selectName5 == 1) {
                                                        str8.getClass();
                                                        switch (str8.hashCode()) {
                                                            case 353103893:
                                                                if (str8.equals("Distance")) {
                                                                    i2 = z ? 1 : 0;
                                                                    break;
                                                                }
                                                                i2 = -1;
                                                                break;
                                                            case 397447147:
                                                                if (str8.equals("Opacity")) {
                                                                    i2 = 1;
                                                                    break;
                                                                }
                                                                i2 = -1;
                                                                break;
                                                            case 1041377119:
                                                                if (str8.equals("Direction")) {
                                                                    i2 = i3;
                                                                    break;
                                                                }
                                                                i2 = -1;
                                                                break;
                                                            case 1379387491:
                                                                if (str8.equals("Shadow Color")) {
                                                                    i2 = 3;
                                                                    break;
                                                                }
                                                                i2 = -1;
                                                                break;
                                                            case 1383710113:
                                                                if (str8.equals("Softness")) {
                                                                    i2 = 4;
                                                                    break;
                                                                }
                                                                i2 = -1;
                                                                break;
                                                            default:
                                                                i2 = -1;
                                                                break;
                                                        }
                                                        switch (i2) {
                                                            case 0:
                                                                dropShadowEffectParser.distance = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, true);
                                                                break;
                                                            case 1:
                                                                dropShadowEffectParser.opacity = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, z);
                                                                break;
                                                            case 2:
                                                                dropShadowEffectParser.direction = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, z);
                                                                break;
                                                            case 3:
                                                                dropShadowEffectParser.color = AnimatableValueParser.parseColor(jsonUtf8Reader, lottieComposition);
                                                                break;
                                                            case 4:
                                                                dropShadowEffectParser.radius = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, true);
                                                                break;
                                                            default:
                                                                jsonUtf8Reader.skipValue();
                                                                break;
                                                        }
                                                    } else {
                                                        jsonUtf8Reader.skipName();
                                                        jsonUtf8Reader.skipValue();
                                                    }
                                                }
                                                jsonUtf8Reader.endObject();
                                            }
                                            jsonUtf8Reader.endArray();
                                        }
                                    }
                                    AnimatableColorValue animatableColorValue3 = dropShadowEffectParser.color;
                                    dropShadowEffect = (animatableColorValue3 == null || (animatableFloatValue = dropShadowEffectParser.opacity) == null || (animatableFloatValue2 = dropShadowEffectParser.direction) == null || (animatableFloatValue3 = dropShadowEffectParser.distance) == null || (animatableFloatValue4 = dropShadowEffectParser.radius) == null) ? null : new DropShadowEffect(animatableColorValue3, animatableFloatValue, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4);
                                }
                            } else if (selectName3 != 1) {
                                jsonUtf8Reader.skipName();
                                jsonUtf8Reader.skipValue();
                            } else {
                                arrayList3.add(jsonUtf8Reader.nextString());
                            }
                            z = false;
                        }
                        jsonUtf8Reader.endObject();
                        z = false;
                    }
                    jsonUtf8Reader.endArray();
                    lottieComposition.addWarning("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList3);
                    str6 = str4;
                    z = false;
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    f6 = (float) jsonUtf8Reader.nextDouble();
                    break;
                case 15:
                    f2 = (float) jsonUtf8Reader.nextDouble();
                    break;
                case 16:
                    str3 = str6;
                    f3 = (float) (jsonUtf8Reader.nextDouble() * Utils.dpScale());
                    str6 = str3;
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    str3 = str6;
                    f4 = (float) (jsonUtf8Reader.nextDouble() * Utils.dpScale());
                    str6 = str3;
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    f = (float) jsonUtf8Reader.nextDouble();
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    f5 = (float) jsonUtf8Reader.nextDouble();
                    break;
                case 20:
                    animatableFloatValue5 = AnimatableValueParser.parseFloat(jsonUtf8Reader, lottieComposition, z);
                    break;
                case 21:
                    str6 = jsonUtf8Reader.nextString();
                    break;
                case 22:
                    z2 = jsonUtf8Reader.nextBoolean();
                    break;
                case 23:
                    z3 = jsonUtf8Reader.nextInt() == 1 ? true : z ? 1 : 0;
                    break;
                case 24:
                    int nextInt4 = jsonUtf8Reader.nextInt();
                    if (nextInt4 >= LBlendMode.values().length) {
                        lottieComposition.addWarning("Unsupported Blend Mode: " + nextInt4);
                        lBlendMode2 = lBlendMode;
                    } else {
                        lBlendMode2 = LBlendMode.values()[nextInt4];
                    }
                    break;
                default:
                    jsonUtf8Reader.skipName();
                    jsonUtf8Reader.skipValue();
                    str2 = str6;
                    str6 = str2;
                    i3 = 2;
                    break;
            }
        }
        String str9 = str6;
        jsonUtf8Reader.endObject();
        ArrayList arrayList4 = new ArrayList();
        if (f > 0.0f) {
            str = str9;
            arrayList4.add(new Keyframe(lottieComposition, valueOf, valueOf, (Interpolator) null, 0.0f, Float.valueOf(f)));
        } else {
            str = str9;
        }
        if (f5 <= 0.0f) {
            f5 = lottieComposition.endFrame;
        }
        arrayList4.add(new Keyframe(lottieComposition, valueOf2, valueOf2, (Interpolator) null, f, Float.valueOf(f5)));
        arrayList4.add(new Keyframe(lottieComposition, valueOf, valueOf, (Interpolator) null, f5, Float.valueOf(Float.MAX_VALUE)));
        if (str5.endsWith(".ai") || "ai".equals(str)) {
            lottieComposition.addWarning("Convert your Illustrator layers to shape layers.");
        }
        boolean z6 = z3;
        if (z6) {
            if (animatableTransform2 == null) {
                animatableTransform2 = new AnimatableTransform();
            }
            AnimatableTransform animatableTransform3 = animatableTransform2;
            animatableTransform3.autoOrient = z6;
            animatableTransform = animatableTransform3;
        } else {
            animatableTransform = animatableTransform2;
        }
        return new Layer(arrayList2, lottieComposition, str5, j, layerType, j2, str7, arrayList, animatableTransform, i4, i5, i6, f6, f2, f3, f4, animatableTextFrame, animatableTextProperties, arrayList4, matteType2, animatableFloatValue5, z2, blurEffect, dropShadowEffect, lBlendMode2);
    }
}
