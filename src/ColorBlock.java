import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/*
 * Chalana Wickramasinghe
 */
class ColorBlock{

    private char color;

    private Set<ColorCell> cells;

    public ColorBlock(char color) {
        this.color = color;
        cells = new HashSet<>();
    }

    public Set<ColorCell> getAllCells() {
        return cells;
    }

    public Set<String> getAllPoints() {
        return cells.stream().map(n -> n.getX() + "_" + n.getY()).collect(Collectors.toSet());
    }

    public boolean addCell(ColorCell cell) {
        if (cell != null && !cells.contains(cell) && cell.getColor() == this.color)
            return cells.add(cell);
        return false;
    }

    public boolean hasColorCell(ColorCell cell) {
        if(cell == null)
            return false;
        return cells.stream().anyMatch(n -> n.getCellId() == cell.getCellId());
    }

    public int size() {
        return cells.size();
    }

}