/*
 * Copyright 2013 JBoss Inc
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

package org.optaplanner.core.score.buildin.bendable;

import java.util.Arrays;

import org.optaplanner.core.score.Score;
import org.optaplanner.core.score.holder.AbstractScoreHolder;
import org.kie.event.rule.ActivationUnMatchListener;
import org.kie.runtime.rule.Match;
import org.kie.runtime.rule.RuleContext;
import org.kie.runtime.rule.Session;

public class BendableScoreHolder extends AbstractScoreHolder {

    private int[] hardScores;
    private int[] softScores;

    public BendableScoreHolder(int hardLevelCount, int softLevelCount) {
        hardScores = new int[hardLevelCount];
        softScores = new int[softLevelCount];
    }

    public int getHardLevelCount() {
        return hardScores.length;
    }

    public int getHardScore(int hardLevel) {
        return hardScores[hardLevel];
    }

    public void setHardScore(int hardLevel, int hardScore) {
        hardScores[hardLevel] = hardScore;
    }

    public int getSoftLevelCount() {
        return softScores.length;
    }

    public int getSoftScore(int softLevel) {
        return softScores[softLevel];
    }

    public void setSoftScore(int softLevel, int softScore) {
        softScores[softLevel] = softScore;
    }

    // ************************************************************************
    // Worker methods
    // ************************************************************************

    public void addHardConstraintMatch(RuleContext kcontext, final int hardLevel, final int weight) {
        hardScores[hardLevel] += weight;
        registerUndoListener(kcontext, new ActivationUnMatchListener() {
            public void unMatch(Session workingMemory, Match activation) {
                hardScores[hardLevel] -= weight;
            }
        });
    }

    public void addSoftConstraintMatch(RuleContext kcontext, final int softLevel, final int weight) {
        softScores[softLevel] += weight;
        registerUndoListener(kcontext, new ActivationUnMatchListener() {
            public void unMatch(Session workingMemory, Match activation) {
                softScores[softLevel] -= weight;
            }
        });
    }

    public Score extractScore() {
        return new BendableScore(Arrays.copyOf(hardScores, hardScores.length),
                Arrays.copyOf(softScores, softScores.length));
    }

}
