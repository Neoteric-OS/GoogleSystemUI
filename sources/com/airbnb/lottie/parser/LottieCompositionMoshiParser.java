package com.airbnb.lottie.parser;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.parser.moshi.JsonUtf8Reader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LottieCompositionMoshiParser {
    public static final JsonReader.Options NAMES = JsonReader.Options.of("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");
    public static final JsonReader.Options ASSETS_NAMES = JsonReader.Options.of("id", "layers", "w", "h", "p", "u");
    public static final JsonReader.Options FONT_NAMES = JsonReader.Options.of("list");
    public static final JsonReader.Options MARKER_NAMES = JsonReader.Options.of("cm", "tm", "dr");

    public static LottieComposition parse(JsonUtf8Reader jsonUtf8Reader) {
        ArrayList arrayList;
        HashMap hashMap;
        float f;
        int i;
        float f2;
        ArrayList arrayList2;
        float f3;
        int i2;
        ArrayList arrayList3;
        float f4;
        int i3 = 1;
        float dpScale = Utils.dpScale();
        LongSparseArray longSparseArray = new LongSparseArray((Object) null);
        ArrayList arrayList4 = new ArrayList();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        ArrayList arrayList5 = new ArrayList();
        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat(0);
        LottieComposition lottieComposition = new LottieComposition();
        jsonUtf8Reader.beginObject();
        int i4 = 0;
        int i5 = 0;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (jsonUtf8Reader.hasNext()) {
            switch (jsonUtf8Reader.selectName(NAMES)) {
                case 0:
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i4 = jsonUtf8Reader.nextInt();
                    break;
                case 1:
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i5 = jsonUtf8Reader.nextInt();
                    break;
                case 2:
                    f = dpScale;
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i = i3;
                    f5 = (float) jsonUtf8Reader.nextDouble();
                    i3 = i;
                    dpScale = f;
                    break;
                case 3:
                    f = dpScale;
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i = i3;
                    f6 = ((float) jsonUtf8Reader.nextDouble()) - 0.01f;
                    i3 = i;
                    dpScale = f;
                    break;
                case 4:
                    f = dpScale;
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i = i3;
                    f7 = (float) jsonUtf8Reader.nextDouble();
                    i3 = i;
                    dpScale = f;
                    break;
                case 5:
                    f2 = dpScale;
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    arrayList2 = arrayList5;
                    f3 = f5;
                    String[] split = jsonUtf8Reader.nextString().split("\\.");
                    int parseInt = Integer.parseInt(split[0]);
                    i2 = 1;
                    int parseInt2 = Integer.parseInt(split[1]);
                    int parseInt3 = Integer.parseInt(split[2]);
                    if (parseInt < 4 || (parseInt <= 4 && (parseInt2 < 4 || (parseInt2 <= 4 && parseInt3 < 0)))) {
                        lottieComposition.addWarning("Lottie only supports bodymovin >= 4.4.0");
                    }
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                case 6:
                    f2 = dpScale;
                    ArrayList arrayList6 = arrayList4;
                    arrayList2 = arrayList5;
                    f3 = f5;
                    jsonUtf8Reader.beginArray();
                    int i6 = 0;
                    while (jsonUtf8Reader.hasNext()) {
                        Layer parse = LayerParser.parse(jsonUtf8Reader, lottieComposition);
                        if (parse.layerType == Layer.LayerType.IMAGE) {
                            i6++;
                        }
                        ArrayList arrayList7 = arrayList6;
                        arrayList7.add(parse);
                        HashMap hashMap5 = hashMap4;
                        longSparseArray.put(parse.layerId, parse);
                        if (i6 > 4) {
                            Logger.warning("You have " + i6 + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
                        }
                        arrayList6 = arrayList7;
                        hashMap4 = hashMap5;
                    }
                    hashMap = hashMap4;
                    arrayList = arrayList6;
                    jsonUtf8Reader.endArray();
                    i2 = 1;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                case 7:
                    f2 = dpScale;
                    arrayList2 = arrayList5;
                    f3 = f5;
                    jsonUtf8Reader.beginArray();
                    while (jsonUtf8Reader.hasNext()) {
                        ArrayList arrayList8 = new ArrayList();
                        LongSparseArray longSparseArray2 = new LongSparseArray((Object) null);
                        jsonUtf8Reader.beginObject();
                        String str = null;
                        String str2 = null;
                        String str3 = null;
                        int i7 = 0;
                        int i8 = 0;
                        while (jsonUtf8Reader.hasNext()) {
                            int selectName = jsonUtf8Reader.selectName(ASSETS_NAMES);
                            if (selectName != 0) {
                                if (selectName == 1) {
                                    jsonUtf8Reader.beginArray();
                                    while (jsonUtf8Reader.hasNext()) {
                                        Layer parse2 = LayerParser.parse(jsonUtf8Reader, lottieComposition);
                                        longSparseArray2.put(parse2.layerId, parse2);
                                        arrayList8.add(parse2);
                                        arrayList4 = arrayList4;
                                    }
                                    arrayList3 = arrayList4;
                                    jsonUtf8Reader.endArray();
                                } else if (selectName == 2) {
                                    i7 = jsonUtf8Reader.nextInt();
                                } else if (selectName == 3) {
                                    i8 = jsonUtf8Reader.nextInt();
                                } else if (selectName == 4) {
                                    str2 = jsonUtf8Reader.nextString();
                                } else if (selectName != 5) {
                                    jsonUtf8Reader.skipName();
                                    jsonUtf8Reader.skipValue();
                                    arrayList3 = arrayList4;
                                } else {
                                    str3 = jsonUtf8Reader.nextString();
                                }
                                arrayList4 = arrayList3;
                            } else {
                                str = jsonUtf8Reader.nextString();
                            }
                        }
                        ArrayList arrayList9 = arrayList4;
                        jsonUtf8Reader.endObject();
                        if (str2 != null) {
                            hashMap3.put(str, new LottieImageAsset(i7, i8, str, str2, str3));
                        } else {
                            hashMap2.put(str, arrayList8);
                        }
                        arrayList4 = arrayList9;
                    }
                    ArrayList arrayList10 = arrayList4;
                    jsonUtf8Reader.endArray();
                    hashMap = hashMap4;
                    arrayList = arrayList10;
                    i2 = 1;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                case 8:
                    f2 = dpScale;
                    f3 = f5;
                    jsonUtf8Reader.beginObject();
                    while (jsonUtf8Reader.hasNext()) {
                        if (jsonUtf8Reader.selectName(FONT_NAMES) != 0) {
                            jsonUtf8Reader.skipName();
                            jsonUtf8Reader.skipValue();
                        } else {
                            jsonUtf8Reader.beginArray();
                            while (jsonUtf8Reader.hasNext()) {
                                JsonReader.Options options = FontParser.NAMES;
                                jsonUtf8Reader.beginObject();
                                String str4 = null;
                                String str5 = null;
                                String str6 = null;
                                while (jsonUtf8Reader.hasNext()) {
                                    int selectName2 = jsonUtf8Reader.selectName(FontParser.NAMES);
                                    if (selectName2 != 0) {
                                        ArrayList arrayList11 = arrayList5;
                                        if (selectName2 == 1) {
                                            str5 = jsonUtf8Reader.nextString();
                                        } else if (selectName2 == 2) {
                                            str6 = jsonUtf8Reader.nextString();
                                        } else if (selectName2 != 3) {
                                            jsonUtf8Reader.skipName();
                                            jsonUtf8Reader.skipValue();
                                        } else {
                                            jsonUtf8Reader.nextDouble();
                                        }
                                        arrayList5 = arrayList11;
                                    } else {
                                        str4 = jsonUtf8Reader.nextString();
                                    }
                                }
                                jsonUtf8Reader.endObject();
                                hashMap4.put(str5, new Font(str4, str5, str6));
                                arrayList5 = arrayList5;
                            }
                            jsonUtf8Reader.endArray();
                        }
                    }
                    arrayList2 = arrayList5;
                    jsonUtf8Reader.endObject();
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    i2 = 1;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                case 9:
                    f2 = dpScale;
                    f3 = f5;
                    jsonUtf8Reader.beginArray();
                    while (jsonUtf8Reader.hasNext()) {
                        JsonReader.Options options2 = FontCharacterParser.NAMES;
                        ArrayList arrayList12 = new ArrayList();
                        jsonUtf8Reader.beginObject();
                        double d = 0.0d;
                        char c = 0;
                        String str7 = null;
                        String str8 = null;
                        while (jsonUtf8Reader.hasNext()) {
                            int selectName3 = jsonUtf8Reader.selectName(FontCharacterParser.NAMES);
                            if (selectName3 == 0) {
                                c = jsonUtf8Reader.nextString().charAt(0);
                            } else if (selectName3 == 1) {
                                jsonUtf8Reader.nextDouble();
                            } else if (selectName3 == 2) {
                                d = jsonUtf8Reader.nextDouble();
                            } else if (selectName3 == 3) {
                                str7 = jsonUtf8Reader.nextString();
                            } else if (selectName3 == 4) {
                                str8 = jsonUtf8Reader.nextString();
                            } else if (selectName3 != 5) {
                                jsonUtf8Reader.skipName();
                                jsonUtf8Reader.skipValue();
                            } else {
                                jsonUtf8Reader.beginObject();
                                while (jsonUtf8Reader.hasNext()) {
                                    if (jsonUtf8Reader.selectName(FontCharacterParser.DATA_NAMES) != 0) {
                                        jsonUtf8Reader.skipName();
                                        jsonUtf8Reader.skipValue();
                                    } else {
                                        jsonUtf8Reader.beginArray();
                                        while (jsonUtf8Reader.hasNext()) {
                                            arrayList12.add((ShapeGroup) ContentModelParser.parse(jsonUtf8Reader, lottieComposition));
                                        }
                                        jsonUtf8Reader.endArray();
                                    }
                                }
                                jsonUtf8Reader.endObject();
                            }
                        }
                        jsonUtf8Reader.endObject();
                        FontCharacter fontCharacter = new FontCharacter(arrayList12, c, d, str7, str8);
                        sparseArrayCompat.put(fontCharacter.hashCode(), fontCharacter);
                    }
                    jsonUtf8Reader.endArray();
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    arrayList2 = arrayList5;
                    i2 = 1;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                case 10:
                    jsonUtf8Reader.beginArray();
                    while (jsonUtf8Reader.hasNext()) {
                        jsonUtf8Reader.beginObject();
                        String str9 = null;
                        float f8 = 0.0f;
                        float f9 = 0.0f;
                        while (jsonUtf8Reader.hasNext()) {
                            int selectName4 = jsonUtf8Reader.selectName(MARKER_NAMES);
                            if (selectName4 != 0) {
                                if (selectName4 == i3) {
                                    f4 = dpScale;
                                    f8 = (float) jsonUtf8Reader.nextDouble();
                                } else if (selectName4 != 2) {
                                    jsonUtf8Reader.skipName();
                                    jsonUtf8Reader.skipValue();
                                } else {
                                    f4 = dpScale;
                                    f9 = (float) jsonUtf8Reader.nextDouble();
                                }
                                dpScale = f4;
                            } else {
                                str9 = jsonUtf8Reader.nextString();
                            }
                            i3 = 1;
                        }
                        float f10 = dpScale;
                        jsonUtf8Reader.endObject();
                        arrayList5.add(new Marker(str9, f8, f9));
                        dpScale = f10;
                        f5 = f5;
                        i3 = 1;
                    }
                    f2 = dpScale;
                    f3 = f5;
                    jsonUtf8Reader.endArray();
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    arrayList2 = arrayList5;
                    i2 = 1;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
                default:
                    jsonUtf8Reader.skipName();
                    jsonUtf8Reader.skipValue();
                    f2 = dpScale;
                    arrayList = arrayList4;
                    hashMap = hashMap4;
                    arrayList2 = arrayList5;
                    f3 = f5;
                    i2 = i3;
                    i3 = i2;
                    dpScale = f2;
                    f5 = f3;
                    arrayList5 = arrayList2;
                    break;
            }
            hashMap4 = hashMap;
            arrayList4 = arrayList;
        }
        float f11 = dpScale;
        Rect rect = new Rect(0, 0, (int) (i4 * f11), (int) (i5 * f11));
        float dpScale2 = Utils.dpScale();
        lottieComposition.bounds = rect;
        lottieComposition.startFrame = f5;
        lottieComposition.endFrame = f6;
        lottieComposition.frameRate = f7;
        lottieComposition.layers = arrayList4;
        lottieComposition.layerMap = longSparseArray;
        lottieComposition.precomps = hashMap2;
        lottieComposition.images = hashMap3;
        lottieComposition.imagesDpScale = dpScale2;
        lottieComposition.characters = sparseArrayCompat;
        lottieComposition.fonts = hashMap4;
        lottieComposition.markers = arrayList5;
        return lottieComposition;
    }
}
