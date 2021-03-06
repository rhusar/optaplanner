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

package org.optaplanner.core.score.holder;

import java.io.Serializable;

import org.drools.common.AgendaItem;
import org.kie.event.rule.ActivationUnMatchListener;
import org.kie.runtime.rule.RuleContext;
import org.kie.runtime.rule.Session;

/**
 * Abstract superclass for {@link ScoreHolder}.
 */
public abstract class AbstractScoreHolder implements ScoreHolder, Serializable {


    protected void registerUndoListener(RuleContext kcontext, ActivationUnMatchListener undoListener) {
        AgendaItem agendaItem = (AgendaItem) kcontext.getMatch();
        if (agendaItem.getActivationUnMatchListener() != null) {
            Session workingMemory = null; // Should not be used by the undoListener anyway
            agendaItem.getActivationUnMatchListener().unMatch(workingMemory, agendaItem);
        }
        agendaItem.setActivationUnMatchListener(undoListener);
    }

}
