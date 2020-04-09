package zm.gov.health.dashboard.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Result extends AbstractEntity {
  @NotNull
  private LocalDate reportDate;
  @NotNull
  private Integer confirmedZambia;
  @NotNull
  private Integer confirmedWorld;
  @NotNull
  private Integer deathsZambia;
  @NotNull
  private Integer deathsWorld;
  @NotNull
  private Integer recoveredZambia;
  @NotNull
  private Integer recoveredWorld;
  @NotNull
  private Integer testedPositive;
  @NotNull
  private Integer testedNegative;


  public Result() {
  }

  public LocalDate getReportDate() {
    return reportDate;
  }

  public void setReportDate(LocalDate reportDate) {
    this.reportDate = reportDate;
  }

  public Integer getConfirmedZambia() {
    return confirmedZambia;
  }

  public void setConfirmedZambia(Integer confirmed_zm) {
    this.confirmedZambia = confirmed_zm;
  }

  public Integer getConfirmedWorld() {
    return confirmedWorld;
  }

  public void setConfirmedWorld(Integer confirmed_word) {
    this.confirmedWorld = confirmed_word;
  }

  public Integer getDeathsZambia() {
    return deathsZambia;
  }

  public void setDeathsZambia(Integer deaths_zm) {
    this.deathsZambia = deaths_zm;
  }

  public Integer getDeathsWorld() {
    return deathsWorld;
  }

  public void setDeathsWorld(Integer deaths_world) {
    this.deathsWorld = deaths_world;
  }

  public Integer getTestedPositive() {
    return testedPositive;
  }

  public void setTestedPositive(Integer tested_pos) {
    this.testedPositive = tested_pos;
  }

  public Integer getTestedNegative() {
    return testedNegative;
  }

  public void setTestedNegative(Integer tested_neg) {
    this.testedNegative = tested_neg;
  }

  public Integer getRecoveredZambia() {
    return recoveredZambia;
  }

  public void setRecoveredZambia(Integer recovered_zm) {
    this.recoveredZambia = recovered_zm;
  }

  public Integer getRecoveredWorld() {
    return recoveredWorld;
  }

  public void setRecoveredWorld(Integer recovered_world) {
    this.recoveredWorld = recovered_world;
  }

}