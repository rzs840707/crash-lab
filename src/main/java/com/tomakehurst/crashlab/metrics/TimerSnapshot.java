package com.tomakehurst.crashlab.metrics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.tomakehurst.crashlab.TimeInterval;

import java.util.concurrent.TimeUnit;

public class TimerSnapshot extends MeterSnapshot {

    private final double max;
    private final double mean;
    private final double min;
    private final double p50;
    private final double p75;
    private final double p95;
    private final double p98;
    private final double p99;
    private final double p999;
    private final double stddev;
    private final TimeUnit durationUnit;

    public TimerSnapshot(@JsonProperty("count") long count,
                         @JsonProperty("max") double max,
                         @JsonProperty("min") double min,
                         @JsonProperty("mean") double mean,
                         @JsonProperty("p50") double p50,
                         @JsonProperty("p75") double p75,
                         @JsonProperty("p95") double p95,
                         @JsonProperty("p98") double p98,
                         @JsonProperty("p99") double p99,
                         @JsonProperty("p999") double p999,
                         @JsonProperty("stddev") double stddev,
                         @JsonProperty("m15_rate") double m15Rate,
                         @JsonProperty("m1_rate") double m1Rate,
                         @JsonProperty("m5_rate") double m5Rate,
                         @JsonProperty("mean_rate") double meanRate,
                         @JsonProperty("duration_units") String durationUnit,
                         @JsonProperty("rate_units") String rateUnit) {

        super(count, m1Rate, m5Rate, m15Rate, meanRate, rateUnit);

        this.max = max;
        this.mean = mean;
        this.min = min;
        this.p50 = p50;
        this.p75 = p75;
        this.p95 = p95;
        this.p98 = p98;
        this.p99 = p99;
        this.p999 = p999;
        this.stddev = stddev;
        this.durationUnit = TimeUnit.valueOf(Optional.fromNullable(durationUnit).or("seconds").toUpperCase());
    }

    public TimeInterval max() {
        return new TimeInterval(max, durationUnit);
    }

    public TimeInterval min() {
        return new TimeInterval(min, durationUnit);
    }

    public TimeInterval mean() {
        return new TimeInterval(mean, durationUnit);
    }

    public TimeInterval stddev() {
        return new TimeInterval(stddev, durationUnit);
    }

    public TimeInterval median() {
        return new TimeInterval(p50, durationUnit);
    }

    public TimeInterval percentile75() {
        return new TimeInterval(p75, durationUnit);
    }

    public TimeInterval percentile95() {
        return new TimeInterval(p95, durationUnit);
    }

    public TimeInterval percentile98() {
        return new TimeInterval(p98, durationUnit);
    }

    public TimeInterval percentile99() {
        return new TimeInterval(p99, durationUnit);
    }

    public TimeInterval percentile999() {
        return new TimeInterval(p999, durationUnit);
    }
}
