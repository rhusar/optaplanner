/*
 * Copyright 2012 JBoss Inc
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

package org.optaplanner.core.score.director.simple;

import org.optaplanner.core.score.Score;
import org.optaplanner.core.score.director.AbstractScoreDirector;
import org.optaplanner.core.score.director.ScoreDirector;
import org.optaplanner.core.solution.Solution;

/**
 * Simple java implementation of {@link ScoreDirector}, which recalculates the {@link Score}
 * of the {@link Solution} workingSolution every time. This is non-incremental calculation, which is slow.
 * @see ScoreDirector
 */
public class SimpleScoreDirector extends AbstractScoreDirector<SimpleScoreDirectorFactory> {

    private final SimpleScoreCalculator simpleScoreCalculator;

    public SimpleScoreDirector(SimpleScoreDirectorFactory scoreDirectorFactory,
            SimpleScoreCalculator simpleScoreCalculator) {
        super(scoreDirectorFactory);
        this.simpleScoreCalculator = simpleScoreCalculator;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Score calculateScore() {
        Score score = simpleScoreCalculator.calculateScore(workingSolution);
        setCalculatedScore(score);
        return score;
    }

}
