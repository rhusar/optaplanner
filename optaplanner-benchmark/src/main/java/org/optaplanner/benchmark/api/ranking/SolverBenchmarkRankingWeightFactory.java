/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.benchmark.api.ranking;

import java.util.List;

import org.optaplanner.benchmark.core.SolverBenchmark;

/**
 * Defines an interface for classes that will be used to rank solver benchmarks 
 * in order of their respective performance.
 */
public interface SolverBenchmarkRankingWeightFactory {

    /**
     * The ranking function. Takes the provided solverBenchmarkList and ranks them.
     * @param solverBenchmarkList never null
     * @param solverBenchmark never null
     * @return never null
     */
    Comparable createRankingWeight(List<SolverBenchmark> solverBenchmarkList, SolverBenchmark solverBenchmark);

}
