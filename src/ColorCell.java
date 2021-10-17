/*
 * Chalana Wickramasinghe
 */

class ColorCell {
    private int x;
    private int y;
    private char color;
    private int cellId;

    public ColorCell(int cellId, int x, int y, char color) {
        this.color = color;
        this.y = y;
        this.x =x;
        this.cellId = cellId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    }

    public int getCellId() {
        return cellId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorCell cell = (ColorCell) o;

        if (color != cell.color) return false;
        return cell.x == this.x && cell.y == cell.y;
    }

    @Override
    public int hashCode() {
        return 27 * (x + y + (int) color);
    }

    @Override
    public String toString() {
        return " [" + x + ", " + y + ", " + color + "] ";
    }
}