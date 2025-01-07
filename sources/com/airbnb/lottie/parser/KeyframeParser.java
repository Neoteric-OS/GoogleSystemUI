package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyframeParser {
    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", "to", "ti");
    public static final JsonReader.Options INTERPOLATOR_NAMES = JsonReader.Options.of("x", "y");

    public static Interpolator interpolatorFor(PointF pointF, PointF pointF2) {
        pointF.x = MiscUtils.clamp(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.clamp(pointF.y, -100.0f, 100.0f);
        pointF2.x = MiscUtils.clamp(pointF2.x, -1.0f, 1.0f);
        float clamp = MiscUtils.clamp(pointF2.y, -100.0f, 100.0f);
        pointF2.y = clamp;
        Utils.AnonymousClass1 anonymousClass1 = Utils.threadLocalPathMeasure;
        try {
            return new PathInterpolator(pointF.x, pointF.y, pointF2.x, clamp);
        } catch (IllegalArgumentException e) {
            return "The Path cannot loop back on itself.".equals(e.getMessage()) ? new PathInterpolator(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
        }
    }

    public static Keyframe parse(JsonReader jsonReader, LottieComposition lottieComposition, float f, ValueParser valueParser, boolean z, boolean z2) {
        Interpolator interpolatorFor;
        Object obj;
        Interpolator interpolator;
        Interpolator interpolatorFor2;
        Interpolator interpolatorFor3;
        Object obj2;
        Object obj3;
        PointF pointF;
        JsonReader.Options options = NAMES;
        if (!z || !z2) {
            JsonReader.Options options2 = options;
            if (!z) {
                return new Keyframe(valueParser.parse(jsonReader, f));
            }
            jsonReader.beginObject();
            PointF pointF2 = null;
            PointF pointF3 = null;
            PointF pointF4 = null;
            boolean z3 = false;
            PointF pointF5 = null;
            Object obj4 = null;
            float f2 = 0.0f;
            Object obj5 = null;
            while (jsonReader.hasNext()) {
                JsonReader.Options options3 = options2;
                switch (jsonReader.selectName(options3)) {
                    case 0:
                        f2 = (float) jsonReader.nextDouble();
                        break;
                    case 1:
                        obj4 = valueParser.parse(jsonReader, f);
                        break;
                    case 2:
                        obj5 = valueParser.parse(jsonReader, f);
                        break;
                    case 3:
                        pointF5 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                        break;
                    case 4:
                        pointF2 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                        break;
                    case 5:
                        if (jsonReader.nextInt() != 1) {
                            z3 = false;
                            break;
                        } else {
                            z3 = true;
                            break;
                        }
                    case 6:
                        pointF3 = JsonUtils.jsonToPoint(jsonReader, f);
                        break;
                    case 7:
                        pointF4 = JsonUtils.jsonToPoint(jsonReader, f);
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
                options2 = options3;
            }
            jsonReader.endObject();
            if (z3) {
                interpolatorFor = LINEAR_INTERPOLATOR;
                obj = obj4;
            } else {
                interpolatorFor = (pointF5 == null || pointF2 == null) ? LINEAR_INTERPOLATOR : interpolatorFor(pointF5, pointF2);
                obj = obj5;
            }
            Keyframe keyframe = new Keyframe(lottieComposition, obj4, obj, interpolatorFor, f2, (Float) null);
            keyframe.pathCp1 = pointF3;
            keyframe.pathCp2 = pointF4;
            return keyframe;
        }
        jsonReader.beginObject();
        PointF pointF6 = null;
        PointF pointF7 = null;
        boolean z4 = false;
        PointF pointF8 = null;
        PointF pointF9 = null;
        PointF pointF10 = null;
        Object obj6 = null;
        PointF pointF11 = null;
        PointF pointF12 = null;
        PointF pointF13 = null;
        float f3 = 0.0f;
        Object obj7 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(options);
            JsonReader.Token token = JsonReader.Token.BEGIN_OBJECT;
            JsonReader.Token token2 = JsonReader.Token.NUMBER;
            JsonReader.Options options4 = options;
            JsonReader.Options options5 = INTERPOLATOR_NAMES;
            switch (selectName) {
                case 0:
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    obj6 = valueParser.parse(jsonReader, f);
                    break;
                case 2:
                    obj7 = valueParser.parse(jsonReader, f);
                    break;
                case 3:
                    PointF pointF14 = pointF6;
                    PointF pointF15 = pointF7;
                    Object obj8 = obj6;
                    PointF pointF16 = pointF11;
                    if (jsonReader.peek() == token) {
                        jsonReader.beginObject();
                        float f4 = 0.0f;
                        float f5 = 0.0f;
                        float f6 = 0.0f;
                        float f7 = 0.0f;
                        while (jsonReader.hasNext()) {
                            int selectName2 = jsonReader.selectName(options5);
                            if (selectName2 != 0) {
                                if (selectName2 != 1) {
                                    jsonReader.skipValue();
                                } else if (jsonReader.peek() == token2) {
                                    f7 = (float) jsonReader.nextDouble();
                                    f5 = f7;
                                } else {
                                    jsonReader.beginArray();
                                    f5 = (float) jsonReader.nextDouble();
                                    f7 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f5;
                                    jsonReader.endArray();
                                }
                            } else if (jsonReader.peek() == token2) {
                                f6 = (float) jsonReader.nextDouble();
                                f4 = f6;
                            } else {
                                jsonReader.beginArray();
                                f4 = (float) jsonReader.nextDouble();
                                f6 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f4;
                                jsonReader.endArray();
                            }
                        }
                        PointF pointF17 = new PointF(f4, f5);
                        pointF11 = new PointF(f6, f7);
                        jsonReader.endObject();
                        obj6 = obj8;
                        pointF10 = pointF17;
                    } else {
                        pointF8 = JsonUtils.jsonToPoint(jsonReader, f);
                        obj6 = obj8;
                        pointF11 = pointF16;
                    }
                    pointF7 = pointF15;
                    options = options4;
                    pointF6 = pointF14;
                    continue;
                case 4:
                    PointF pointF18 = pointF7;
                    if (jsonReader.peek() == token) {
                        jsonReader.beginObject();
                        float f8 = 0.0f;
                        float f9 = 0.0f;
                        float f10 = 0.0f;
                        float f11 = 0.0f;
                        while (jsonReader.hasNext()) {
                            PointF pointF19 = pointF6;
                            int selectName3 = jsonReader.selectName(options5);
                            if (selectName3 != 0) {
                                obj3 = obj6;
                                if (selectName3 != 1) {
                                    jsonReader.skipValue();
                                    obj6 = obj3;
                                } else if (jsonReader.peek() == token2) {
                                    f11 = (float) jsonReader.nextDouble();
                                    obj6 = obj3;
                                    pointF11 = pointF11;
                                    f9 = f11;
                                } else {
                                    pointF = pointF11;
                                    jsonReader.beginArray();
                                    f9 = (float) jsonReader.nextDouble();
                                    f11 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f9;
                                    jsonReader.endArray();
                                    obj6 = obj3;
                                    pointF11 = pointF;
                                }
                            } else {
                                obj3 = obj6;
                                pointF = pointF11;
                                if (jsonReader.peek() == token2) {
                                    f10 = (float) jsonReader.nextDouble();
                                    obj6 = obj3;
                                    pointF11 = pointF;
                                    f8 = f10;
                                } else {
                                    jsonReader.beginArray();
                                    f8 = (float) jsonReader.nextDouble();
                                    f10 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f8;
                                    jsonReader.endArray();
                                    obj6 = obj3;
                                    pointF11 = pointF;
                                }
                            }
                            pointF6 = pointF19;
                        }
                        PointF pointF20 = new PointF(f8, f9);
                        PointF pointF21 = new PointF(f10, f11);
                        jsonReader.endObject();
                        pointF12 = pointF20;
                        pointF13 = pointF21;
                    } else {
                        pointF9 = JsonUtils.jsonToPoint(jsonReader, f);
                    }
                    pointF7 = pointF18;
                    break;
                case 5:
                    if (jsonReader.nextInt() != 1) {
                        z4 = false;
                        break;
                    } else {
                        z4 = true;
                        break;
                    }
                case 6:
                    pointF6 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                case 7:
                    pointF7 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
            options = options4;
        }
        PointF pointF22 = pointF6;
        PointF pointF23 = pointF7;
        Object obj9 = obj6;
        PointF pointF24 = pointF11;
        jsonReader.endObject();
        if (z4) {
            interpolator = LINEAR_INTERPOLATOR;
            obj2 = obj9;
        } else {
            if (pointF8 != null && pointF9 != null) {
                interpolator = interpolatorFor(pointF8, pointF9);
            } else {
                if (pointF10 != null && pointF24 != null && pointF12 != null && pointF13 != null) {
                    interpolatorFor2 = interpolatorFor(pointF10, pointF12);
                    interpolatorFor3 = interpolatorFor(pointF24, pointF13);
                    obj2 = obj7;
                    interpolator = null;
                    Keyframe keyframe2 = (interpolatorFor2 != null || interpolatorFor3 == null) ? new Keyframe(lottieComposition, obj9, obj2, interpolator, f3, (Float) null) : new Keyframe(lottieComposition, obj9, obj2, interpolatorFor2, interpolatorFor3, f3);
                    keyframe2.pathCp1 = pointF22;
                    keyframe2.pathCp2 = pointF23;
                    return keyframe2;
                }
                interpolator = LINEAR_INTERPOLATOR;
            }
            obj2 = obj7;
        }
        interpolatorFor2 = null;
        interpolatorFor3 = null;
        if (interpolatorFor2 != null) {
        }
        keyframe2.pathCp1 = pointF22;
        keyframe2.pathCp2 = pointF23;
        return keyframe2;
    }
}
