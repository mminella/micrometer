/**
 * Copyright 2017 Pivotal Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument.composite;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;

public class CompositeCustomMeter implements CompositeMeter {
    private final Meter.Id id;
    private final Meter.Type type;
    private final Iterable<Measurement> measurements;

    public CompositeCustomMeter(Meter.Id id, Type type, Iterable<Measurement> measurements) {
        this.id = id;
        this.type = type;
        this.measurements = measurements;
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Iterable<Measurement> measure() {
        return measurements;
    }

    @Override
    public void add(MeterRegistry registry) {
        registry.register(id.getName(), id.getTags(), type, measurements);
    }

    @Override
    public void remove(MeterRegistry registry) {
        // do nothing
    }
}
