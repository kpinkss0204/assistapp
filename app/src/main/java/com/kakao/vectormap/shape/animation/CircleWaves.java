package com.kakao.vectormap.shape.animation;

import com.google.logging.type.LogSeverity;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.animation.Interpolation;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CircleWaves implements Animation {
    private List<CircleWave> circleWaves;
    private String id;
    private int repeatCount = 3;
    private int duration = LogSeverity.ERROR_VALUE;
    private boolean hideShapeAtStop = true;
    private Interpolation interpolation = Interpolation.Linear;

    CircleWaves(String str, CircleWave... circleWaveArr) {
        this.id = MapUtils.getUniqueId(str);
        this.circleWaves = new ArrayList();
        if (circleWaveArr != null) {
            this.circleWaves = new ArrayList(Arrays.asList(circleWaveArr));
        }
    }

    public static CircleWaves from(String str) {
        return new CircleWaves(str, null);
    }

    public static CircleWaves from(CircleWave... circleWaveArr) {
        return new CircleWaves("", circleWaveArr);
    }

    public static CircleWaves from(String str, CircleWave... circleWaveArr) {
        return new CircleWaves(str, circleWaveArr);
    }

    public CircleWaves setHideShapeAtStop(boolean z) {
        this.hideShapeAtStop = z;
        return this;
    }

    public boolean isHideShapeAtStop() {
        return this.hideShapeAtStop;
    }

    public CircleWaves setInterpolation(Interpolation interpolation) {
        if (interpolation != null) {
            this.interpolation = interpolation;
        }
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public CircleWaves setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public CircleWaves setRepeatCount(int i) {
        this.repeatCount = i;
        return this;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public CircleWaves addCircleWave(CircleWave... circleWaveArr) {
        if (circleWaveArr != null) {
            this.circleWaves.addAll(Arrays.asList(circleWaveArr));
        }
        return this;
    }

    public List<CircleWave> getCircleWaves() {
        return new ArrayList(this.circleWaves);
    }

    public int getCircleWaveCount() {
        List<CircleWave> list = this.circleWaves;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }
}
