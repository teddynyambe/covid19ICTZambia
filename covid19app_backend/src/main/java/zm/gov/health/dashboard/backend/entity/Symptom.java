package zm.gov.health.dashboard.backend.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Symptom extends AbstractEntity {
    @NotNull
    private Boolean fever;
    @NotNull
    private Boolean soreThroat;
    @NotNull
    private Boolean muscleJointPain;
    @NotNull
    private Boolean fatigue;
    @NotNull
    private Boolean dryCough;
    @NotNull
    private Boolean headache;
    @NotNull
    private Boolean shortBreath;
    @NotNull
    private Boolean runnyNose;


    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }

    public Boolean getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(Boolean soreThroat) {
        this.soreThroat = soreThroat;
    }

    public Boolean getMuscleJointPain() {
        return muscleJointPain;
    }

    public void setMuscleJointPain(Boolean muscleJointPain) {
        this.muscleJointPain = muscleJointPain;
    }

    public Boolean getFatigue() {
        return fatigue;
    }

    public void setFatigue(Boolean fatigue) {
        this.fatigue = fatigue;
    }

    public Boolean getDryCough() {
        return dryCough;
    }

    public void setDryCough(Boolean dryCough) {
        this.dryCough = dryCough;
    }

    public Boolean getHeadache() {
        return headache;
    }

    public void setHeadache(Boolean headache) {
        this.headache = headache;
    }

    public Boolean getShortBreath() {
        return shortBreath;
    }

    public void setShortBreath(Boolean shortBreath) {
        this.shortBreath = shortBreath;
    }

    public Boolean getRunnyNose() {
        return runnyNose;
    }

    public void setRunnyNose(Boolean runnyNose) {
        this.runnyNose = runnyNose;
    }
}
