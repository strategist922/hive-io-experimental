/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.api.impl.input.benchmark;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.MetricsRegistry;
import com.yammer.metrics.util.RatioGauge;

/**
 * Ratio Gauge of two Counters.
 */
public class CounterRatioGauge extends RatioGauge {
  /** Counter for Numerator */
  private final Counter numerator;
  /** Counter for Denominator */
  private final Counter denominator;

  /**
   * Constructor which creates Counters
   *
   * @param numeratorName String name of numerator
   * @param denominatorName String name of denominator
   */
  public CounterRatioGauge(String numeratorName, String denominatorName) {
    this(Metrics.defaultRegistry(), numeratorName, denominatorName);
  }

  /**
   * Constructor which creates Counters
   *
   * @param metricsRegistry MetricsRegistry to use
   * @param numeratorName String name of numerator
   * @param denominatorName String name of denominator
   */
  public CounterRatioGauge(MetricsRegistry metricsRegistry,
                           String numeratorName, String denominatorName) {
    this(metricsRegistry.newCounter(CounterRatioGauge.class, numeratorName),
         metricsRegistry.newCounter(CounterRatioGauge.class, denominatorName));
  }

  /**
   * Constructor using existing counters
   *
   * @param numerator Numerator counter
   * @param denominator Denominator counter
   */
  public CounterRatioGauge(Counter numerator, Counter denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public Counter getNumeratorCounter() {
    return numerator;
  }

  public Counter getDenominatorCounter() {
    return denominator;
  }

  @Override protected double getNumerator() {
    return numerator.count();
  }

  @Override protected double getDenominator() {
    return denominator.count();
  }
}
