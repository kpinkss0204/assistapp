package com.kakao.vectormap.internal;

import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelAnimator;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelLayerOptions;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LodLabel;
import com.kakao.vectormap.label.LodLabelLayer;
import com.kakao.vectormap.label.PolylineLabel;
import com.kakao.vectormap.label.PolylineLabelOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface ILabelFactory {
    LabelAnimator newAnimator(String str, ILabelDelegate iLabelDelegate, boolean z, boolean z2);

    Label newLabel(ILabelDelegate iLabelDelegate, String str, LabelOptions labelOptions);

    LabelLayer newLayer(ILabelDelegate iLabelDelegate, String str, LabelLayerOptions labelLayerOptions, ILabelFactory iLabelFactory);

    LodLabel newLodLabel(ILabelDelegate iLabelDelegate, String str, LabelOptions labelOptions);

    LodLabelLayer newLodLayer(ILabelDelegate iLabelDelegate, String str, LabelLayerOptions labelLayerOptions, ILabelFactory iLabelFactory);

    PolylineLabel newPolylineLabel(ILabelDelegate iLabelDelegate, String str, PolylineLabelOptions polylineLabelOptions);
}
