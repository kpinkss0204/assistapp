package com.kakao.vectormap.label;

import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LabelTransition {
    public boolean enableTransitionEntrance = true;
    public boolean enableTransitionExit = true;
    public int entrance;
    public int exit;

    LabelTransition(Transition transition, Transition transition2) {
        this.entrance = transition.getValue();
        this.exit = transition2.getValue();
    }

    public static LabelTransition from(LabelTransition labelTransition) {
        return new LabelTransition(labelTransition.getEntrance(), labelTransition.getExit());
    }

    public static LabelTransition from(Transition transition, Transition transition2) {
        return new LabelTransition(transition, transition2);
    }

    public LabelTransition enableTransitionWhenChange(boolean z, boolean z2) {
        this.enableTransitionEntrance = z;
        this.enableTransitionExit = z2;
        return this;
    }

    public Transition getEntrance() {
        return Transition.getEnum(this.entrance);
    }

    public Transition getExit() {
        return Transition.getEnum(this.exit);
    }

    public boolean isEnableTransitionEntrance() {
        return this.enableTransitionEntrance;
    }

    public boolean isEnableTransitionExit() {
        return this.enableTransitionExit;
    }

    public String toString() {
        return "LabelIconTransition(" + this.entrance + ", " + this.exit + this.enableTransitionEntrance + this.enableTransitionExit + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            LabelTransition labelTransition = (LabelTransition) obj;
            if (this.entrance == labelTransition.entrance && this.exit == labelTransition.exit && this.enableTransitionEntrance == labelTransition.enableTransitionEntrance && this.enableTransitionExit == labelTransition.enableTransitionExit) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.entrance), Integer.valueOf(this.exit), Boolean.valueOf(this.enableTransitionEntrance), Boolean.valueOf(this.enableTransitionExit));
    }
}
