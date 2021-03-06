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

package org.optaplanner.examples.examination.solver.move.factory;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.heuristic.selector.move.factory.MoveListFactory;
import org.optaplanner.core.move.Move;
import org.optaplanner.core.solution.Solution;
import org.optaplanner.examples.examination.domain.Exam;
import org.optaplanner.examples.examination.domain.Examination;
import org.optaplanner.examples.examination.domain.Room;
import org.optaplanner.examples.examination.solver.move.RoomChangeMove;

public class RoomChangeMoveFactory implements MoveListFactory {

    public List<Move> createMoveList(Solution solution) {
        Examination examination = (Examination) solution;
        List<Room> roomList = examination.getRoomList();
        List<Move> moveList = new ArrayList<Move>();
        for (Exam exam : examination.getExamList()) {
            for (Room room : roomList) {
                moveList.add(new RoomChangeMove(exam, room));
            }
        }
        return moveList;
    }

}
