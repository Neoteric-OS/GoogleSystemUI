package com.airbnb.lottie.animation.content;

import android.graphics.Matrix;
import android.graphics.Path;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MergePathsContent implements PathContent, GreedyContent {
    public final MergePaths mergePaths;
    public final Path firstPath = new Path();
    public final Path remainderPath = new Path();
    public final Path path = new Path();
    public final List pathContents = new ArrayList();

    public MergePathsContent(MergePaths mergePaths) {
        mergePaths.getClass();
        this.mergePaths = mergePaths;
    }

    @Override // com.airbnb.lottie.animation.content.GreedyContent
    public final void absorbContent(ListIterator listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            Content content = (Content) listIterator.previous();
            if (content instanceof PathContent) {
                this.pathContents.add((PathContent) content);
                listIterator.remove();
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public final Path getPath() {
        this.path.reset();
        MergePaths mergePaths = this.mergePaths;
        if (mergePaths.hidden) {
            return this.path;
        }
        int ordinal = mergePaths.mode.ordinal();
        if (ordinal == 0) {
            for (int i = 0; i < ((ArrayList) this.pathContents).size(); i++) {
                this.path.addPath(((PathContent) ((ArrayList) this.pathContents).get(i)).getPath());
            }
        } else if (ordinal == 1) {
            opFirstPathWithRest(Path.Op.UNION);
        } else if (ordinal == 2) {
            opFirstPathWithRest(Path.Op.REVERSE_DIFFERENCE);
        } else if (ordinal == 3) {
            opFirstPathWithRest(Path.Op.INTERSECT);
        } else if (ordinal == 4) {
            opFirstPathWithRest(Path.Op.XOR);
        }
        return this.path;
    }

    public final void opFirstPathWithRest(Path.Op op) {
        Matrix matrix;
        Matrix matrix2;
        this.remainderPath.reset();
        this.firstPath.reset();
        for (int size = ((ArrayList) this.pathContents).size() - 1; size >= 1; size--) {
            PathContent pathContent = (PathContent) ((ArrayList) this.pathContents).get(size);
            if (pathContent instanceof ContentGroup) {
                ContentGroup contentGroup = (ContentGroup) pathContent;
                List pathList = contentGroup.getPathList();
                for (int size2 = pathList.size() - 1; size2 >= 0; size2--) {
                    Path path = ((PathContent) pathList.get(size2)).getPath();
                    TransformKeyframeAnimation transformKeyframeAnimation = contentGroup.transformAnimation;
                    if (transformKeyframeAnimation != null) {
                        matrix2 = transformKeyframeAnimation.getMatrix();
                    } else {
                        contentGroup.matrix.reset();
                        matrix2 = contentGroup.matrix;
                    }
                    path.transform(matrix2);
                    this.remainderPath.addPath(path);
                }
            } else {
                this.remainderPath.addPath(pathContent.getPath());
            }
        }
        PathContent pathContent2 = (PathContent) ((ArrayList) this.pathContents).get(0);
        if (pathContent2 instanceof ContentGroup) {
            ContentGroup contentGroup2 = (ContentGroup) pathContent2;
            List pathList2 = contentGroup2.getPathList();
            for (int i = 0; i < pathList2.size(); i++) {
                Path path2 = ((PathContent) pathList2.get(i)).getPath();
                TransformKeyframeAnimation transformKeyframeAnimation2 = contentGroup2.transformAnimation;
                if (transformKeyframeAnimation2 != null) {
                    matrix = transformKeyframeAnimation2.getMatrix();
                } else {
                    contentGroup2.matrix.reset();
                    matrix = contentGroup2.matrix;
                }
                path2.transform(matrix);
                this.firstPath.addPath(path2);
            }
        } else {
            this.firstPath.set(pathContent2.getPath());
        }
        this.path.op(this.firstPath, this.remainderPath, op);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final void setContents(List list, List list2) {
        for (int i = 0; i < ((ArrayList) this.pathContents).size(); i++) {
            ((PathContent) ((ArrayList) this.pathContents).get(i)).setContents(list, list2);
        }
    }
}
