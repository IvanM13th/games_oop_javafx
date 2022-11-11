package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    public void figureTakesTheSamePos() {
        Cell initialPos = Cell.A3;
        BishopBlack bishopBlack = new BishopBlack(initialPos);
        Cell curPos = bishopBlack.position();
        assertThat(initialPos).isEqualTo(curPos);
    }

    @Test
    public void copyHasCorrectPos() {
        Cell initialPos = Cell.A3;
        BishopBlack bishopBlack = new BishopBlack(initialPos);
        Cell destPos = Cell.C1;
        bishopBlack = (BishopBlack) bishopBlack.copy(destPos);
        assertThat(destPos).isEqualTo(bishopBlack.position());
    }

    @Test
    public void correctWayCheck() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell dest = Cell.G5;
        bishopBlack.way(dest);
        bishopBlack = (BishopBlack) bishopBlack.copy(dest);
        assertThat(bishopBlack.position()).isEqualTo(dest);
    }

    @Test
    public void whenImpossibleMoveException() {
        Cell dest = Cell.G4;
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            bishopBlack.way(dest);
        });
        assertThat(exception.getMessage()).isEqualTo(
                "Could not way by diagonal from %s to %s",
                bishopBlack.position(), dest);
    }
}