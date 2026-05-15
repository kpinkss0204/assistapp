package com.kakao.vectormap.label;

import com.kakao.vectormap.internal.ILabelDelegate;
import com.kakao.vectormap.internal.ILabelFactory;

/* JADX INFO: loaded from: classes4.dex */
class LabelFactory implements ILabelFactory {
    LabelFactory() {
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public Label newLabel(ILabelDelegate iLabelDelegate, String str, LabelOptions labelOptions) {
        return new Label(iLabelDelegate, str, labelOptions.getLabelId(), false, labelOptions.getRank(), labelOptions.isVisible(), labelOptions.isClickable(), labelOptions.getTag(), labelOptions.getPosition(), labelOptions.getStyles(), labelOptions.getLabelTextBuilder());
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public LodLabel newLodLabel(ILabelDelegate iLabelDelegate, String str, LabelOptions labelOptions) {
        return new LodLabel(iLabelDelegate, str, labelOptions.getLabelId(), true, labelOptions.getRank(), labelOptions.isVisible(), labelOptions.isClickable(), labelOptions.getTag(), labelOptions.getPosition(), labelOptions.getStyles(), labelOptions.getLabelTextBuilder());
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public PolylineLabel newPolylineLabel(ILabelDelegate iLabelDelegate, String str, PolylineLabelOptions polylineLabelOptions) {
        return new PolylineLabel(iLabelDelegate, str, polylineLabelOptions.getLabelId(), polylineLabelOptions.getRank(), polylineLabelOptions.getTag(), polylineLabelOptions.getText(), polylineLabelOptions.getPoints(), polylineLabelOptions.getStyles(), polylineLabelOptions.isVisible());
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public LabelLayer newLayer(ILabelDelegate iLabelDelegate, String str, LabelLayerOptions labelLayerOptions, ILabelFactory iLabelFactory) {
        return new LabelLayer(iLabelDelegate, str, labelLayerOptions.getZOrder(), labelLayerOptions.getCompetitionType(), labelLayerOptions.getCompetitionUnit(), labelLayerOptions.getOrderingType(), false, labelLayerOptions.isVisible(), labelLayerOptions.isClickable(), labelLayerOptions.getTag(), iLabelFactory);
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public LodLabelLayer newLodLayer(ILabelDelegate iLabelDelegate, String str, LabelLayerOptions labelLayerOptions, ILabelFactory iLabelFactory) {
        return new LodLabelLayer(iLabelDelegate, str, labelLayerOptions.getZOrder(), labelLayerOptions.getCompetitionType(), labelLayerOptions.getCompetitionUnit(), labelLayerOptions.getOrderingType(), labelLayerOptions.getLodRadius(), true, labelLayerOptions.isVisible(), labelLayerOptions.isClickable(), labelLayerOptions.getTag(), iLabelFactory);
    }

    @Override // com.kakao.vectormap.internal.ILabelFactory
    public LabelAnimator newAnimator(String str, ILabelDelegate iLabelDelegate, boolean z, boolean z2) {
        return new LabelAnimator(str, iLabelDelegate, z, z2);
    }
}
