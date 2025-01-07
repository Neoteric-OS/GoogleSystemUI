package com.google.android.systemui.gesture;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Trace;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.Interpreter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BackGestureTfClassifierProviderGoogle {
    public static final Object sModelLoadingLock = new Object();
    public final String mModelFile;
    public final float[][] mOutput;
    public final Map mOutputMap;
    public Map mVocab;
    public final String mVocabFile;
    public Interpreter mInterpreter = null;
    public boolean mModelLoaded = false;

    public BackGestureTfClassifierProviderGoogle(String str) {
        HashMap hashMap = new HashMap();
        this.mOutputMap = hashMap;
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 1, 1);
        this.mOutput = fArr;
        this.mModelFile = str.concat(".tflite");
        this.mVocabFile = str.concat(".vocab");
        hashMap.put(0, fArr);
    }

    public final void loadModel(AssetManager assetManager) {
        try {
            try {
                Trace.beginSection("BackGestureTfClassifierProviderGoogle#modelLoading");
                AssetFileDescriptor openFd = assetManager.openFd(this.mModelFile);
                try {
                    this.mInterpreter = new Interpreter(openFd.createInputStream().getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength()));
                    this.mModelLoaded = true;
                    openFd.close();
                } catch (Throwable th) {
                    if (openFd != null) {
                        try {
                            openFd.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } finally {
                Trace.endSection();
            }
        } catch (Exception e) {
            Log.e("BackGestureTfClassifier", "Load TFLite file error:", e);
            this.mModelLoaded = false;
        }
    }

    public final Map readVocab(AssetManager assetManager) {
        HashMap hashMap = new HashMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(this.mVocabFile)));
            int i = 0;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    hashMap.put(readLine, Integer.valueOf(i));
                    i++;
                } finally {
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            Log.e("BackGestureTfClassifier", "Load vocab file error: ", e);
        }
        return hashMap;
    }
}
