package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenOccupiedCellExceptionInLogic()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            Logic logic = new Logic();
            Cell source = Cell.C1;
            Figure bishopBlack = new BishopBlack(source);
            logic.add(bishopBlack);
            Cell occupied = Cell.D2;
            Figure figureToOccupy = new BishopBlack(occupied);
            logic.add(figureToOccupy);
            Cell dest = Cell.G5;
            logic.move(source, dest);
        });
        assertThat(exception.getMessage()).isEqualTo("The cell is occupied");
    }

    @Test
    public void whenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Figure bishop = new BishopBlack(Cell.C1);
        logic.add(bishop);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(bishop.position(), Cell.G4);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from %s to %s",
                bishop.position(), Cell.G4);
    }

    @Test
    public void whenMoveIsCorrect()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Figure figure = new BishopBlack(Cell.C1);
        logic.add(figure);
        logic.move(Cell.C1, Cell.G5);
        figure = figure.copy(Cell.G5);
        assertThat(figure.position()).isEqualTo(Cell.G5);
    }
}