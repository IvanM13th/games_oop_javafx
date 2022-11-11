package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not way by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(position.getX() - dest.getX());
        Cell[] steps = new Cell[size];
        int deltaX = (dest.getX() - position.getX()) > 0 ? 1 : -1;
        int deltaY = (dest.getY() - position.getY()) > 0 ? 1 : -1;
        int x = position.getX();
        int y = position.getY();
        for (int index = 0; index < size; index++) {
            x = x + deltaX;
            y = y + deltaY;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;

    }

    public boolean isDiagonal(Cell source, Cell dest) {
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = dest.getX();
        int y2 = dest.getY();
        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);
        return deltaX == deltaY;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
