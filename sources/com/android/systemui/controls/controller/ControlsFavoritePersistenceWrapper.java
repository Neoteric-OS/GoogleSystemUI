package com.android.systemui.controls.controller;

import android.app.backup.BackupManager;
import android.content.ComponentName;
import android.util.AtomicFile;
import android.util.Log;
import android.util.Xml;
import com.android.systemui.backup.BackupHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsFavoritePersistenceWrapper {
    public BackupManager backupManager;
    public final Executor executor;
    public File file;

    public ControlsFavoritePersistenceWrapper(File file, Executor executor, BackupManager backupManager) {
        this.file = file;
        this.executor = executor;
        this.backupManager = backupManager;
    }

    public static List parseXml(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ComponentName componentName = null;
        String str = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return arrayList;
            }
            String name = xmlPullParser.getName();
            if (name == null) {
                name = "";
            }
            if (next == 2 && name.equals("structure")) {
                componentName = ComponentName.unflattenFromString(xmlPullParser.getAttributeValue(null, "component"));
                str = xmlPullParser.getAttributeValue(null, "structure");
                if (str == null) {
                    str = "";
                }
            } else if (next == 2 && name.equals("control")) {
                String attributeValue = xmlPullParser.getAttributeValue(null, "id");
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "title");
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "subtitle");
                String str2 = attributeValue3 != null ? attributeValue3 : "";
                String attributeValue4 = xmlPullParser.getAttributeValue(null, "type");
                Integer valueOf = attributeValue4 != null ? Integer.valueOf(Integer.parseInt(attributeValue4)) : null;
                if (attributeValue != null && attributeValue2 != null && valueOf != null) {
                    arrayList2.add(new ControlInfo(attributeValue, attributeValue2, str2, valueOf.intValue()));
                }
            } else if (next == 3 && name.equals("structure")) {
                Intrinsics.checkNotNull(componentName);
                Intrinsics.checkNotNull(str);
                arrayList.add(new StructureInfo(componentName, str, CollectionsKt.toList(arrayList2)));
                arrayList2.clear();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.BufferedInputStream, java.io.InputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v5, types: [org.xmlpull.v1.XmlPullParser] */
    public final List readFavorites() {
        List parseXml;
        ?? exists = this.file.exists();
        if (exists == 0) {
            Log.d("ControlsFavoritePersistenceWrapper", "No favorites, returning empty list");
            return EmptyList.INSTANCE;
        }
        try {
            try {
                exists = new BufferedInputStream(new FileInputStream(this.file));
                try {
                    Log.d("ControlsFavoritePersistenceWrapper", "Reading data from file: " + this.file);
                    synchronized (BackupHelper.controlsDataLock) {
                        ?? newPullParser = Xml.newPullParser();
                        newPullParser.setInput(exists, null);
                        parseXml = parseXml(newPullParser);
                    }
                    return parseXml;
                } catch (IOException e) {
                    throw new IllegalStateException("Failed parsing favorites file: " + this.file, e);
                } catch (XmlPullParserException e2) {
                    throw new IllegalStateException("Failed parsing favorites file: " + this.file, e2);
                }
            } catch (FileNotFoundException unused) {
                Log.i("ControlsFavoritePersistenceWrapper", "No file found");
                return EmptyList.INSTANCE;
            }
        } finally {
            IoUtils.closeQuietly((AutoCloseable) exists);
        }
    }

    public final void storeFavorites(final List list) {
        if (!list.isEmpty() || this.file.exists()) {
            this.executor.execute(new Runnable() { // from class: com.android.systemui.controls.controller.ControlsFavoritePersistenceWrapper$storeFavorites$1
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z;
                    BackupManager backupManager;
                    Log.d("ControlsFavoritePersistenceWrapper", "Saving data to file: " + ControlsFavoritePersistenceWrapper.this.file);
                    AtomicFile atomicFile = new AtomicFile(ControlsFavoritePersistenceWrapper.this.file);
                    Object obj = BackupHelper.controlsDataLock;
                    Object obj2 = BackupHelper.controlsDataLock;
                    List<StructureInfo> list2 = list;
                    synchronized (obj2) {
                        try {
                            FileOutputStream startWrite = atomicFile.startWrite();
                            try {
                                try {
                                    XmlSerializer newSerializer = Xml.newSerializer();
                                    newSerializer.setOutput(startWrite, "utf-8");
                                    z = true;
                                    newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                                    newSerializer.startDocument(null, Boolean.TRUE);
                                    newSerializer.startTag(null, "version");
                                    newSerializer.text("1");
                                    newSerializer.endTag(null, "version");
                                    newSerializer.startTag(null, "structures");
                                    for (StructureInfo structureInfo : list2) {
                                        newSerializer.startTag(null, "structure");
                                        newSerializer.attribute(null, "component", structureInfo.componentName.flattenToString());
                                        newSerializer.attribute(null, "structure", structureInfo.structure.toString());
                                        newSerializer.startTag(null, "controls");
                                        for (ControlInfo controlInfo : structureInfo.controls) {
                                            newSerializer.startTag(null, "control");
                                            newSerializer.attribute(null, "id", controlInfo.controlId);
                                            newSerializer.attribute(null, "title", controlInfo.controlTitle.toString());
                                            newSerializer.attribute(null, "subtitle", controlInfo.controlSubtitle.toString());
                                            newSerializer.attribute(null, "type", String.valueOf(controlInfo.deviceType));
                                            newSerializer.endTag(null, "control");
                                        }
                                        newSerializer.endTag(null, "controls");
                                        newSerializer.endTag(null, "structure");
                                    }
                                    newSerializer.endTag(null, "structures");
                                    newSerializer.endDocument();
                                    atomicFile.finishWrite(startWrite);
                                } finally {
                                    IoUtils.closeQuietly(startWrite);
                                }
                            } catch (Throwable unused) {
                                Log.e("ControlsFavoritePersistenceWrapper", "Failed to write file, reverting to previous version");
                                atomicFile.failWrite(startWrite);
                                z = false;
                            }
                        } catch (IOException e) {
                            Log.e("ControlsFavoritePersistenceWrapper", "Failed to start write file", e);
                            return;
                        }
                    }
                    if (!z || (backupManager = ControlsFavoritePersistenceWrapper.this.backupManager) == null) {
                        return;
                    }
                    backupManager.dataChanged();
                }
            });
        }
    }
}
