import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Chalana Wickramasinghe
 */
public class ColorGridProcess {

    private HashMap<String, ColorCell> game;

    private int col;
    private int row;

    public void initialize(int cols, int rows, ArrayList<String> colors) {
        this.col = cols;
        this.row = rows;

        Random random = new Random();
        this.game = new HashMap<>();

        int count = 0;
        for (int x = 0; x< row; x++) {
            for (int y = 0; y< col; y++) {
            	this.game.put(x+"_"+y, new ColorCell(count++, x, y, colors.get(random.nextInt(colors.size())).charAt(0)));
            }
        }
    }

    public ColorCell getCell(int x, int y) {
        return this.game.get(x+"_"+y);
    }

    public void desplayColorGrids() {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                if(x == this.col - 1 )
                    System.out.println(getCell(y, x).getColor());
                else
                    System.out.print(getCell(y, x).getColor() + " ");
            }
        }
    }

    public void desplayColorGridWithBlocks(ColorBlock block) {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < col; x++) {
                ColorCell n = getCell(y, x);
                String color = block.hasColorCell(n) ? "*" : String.valueOf(n.getColor());
                if(x == this.col - 1 ) {
                    System.out.println(color);
                } else {
                    System.out.print(color + " ");
                }
            }
        }
    }

    private List<ColorCell> findAdjecentCell(ColorCell cell, ColorBlock block) {
        List<ColorCell> cells = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        return Arrays.asList(
                this.game.get(createKey(x,y+1)),
                this.game.get(createKey(x+1,y)),
                this.game.get(createKey(x,y-1)),
                this.game.get(createKey(x-1,y)))
            .stream()
            .filter(c -> c != null && c.getColor() == cell.getColor() && !block.hasColorCell(c))
            .collect(Collectors.toList());
    }
    
    private String createKey (int x, int y) {
        return x+"_"+y;
    }

    public ColorBlock getContinousColorBlock(int x, int y) {
        ColorCell startCell = this.game.get(createKey(x,y));
        ColorBlock colorBlock = new ColorBlock(startCell.getColor());
        colorBlock.addCell(startCell);

        LinkedList<ColorCell> processingCells = new LinkedList<>();
        processingCells.addAll(findAdjecentCell(startCell, colorBlock));

        while(!processingCells.isEmpty()) {
            ColorCell nextCell = processingCells.remove();
            colorBlock.addCell(nextCell);
            processingCells.addAll(findAdjecentCell(nextCell, colorBlock));
        }

        return colorBlock;
    }

    public ColorBlock getMaxColorBlock() {
        Set<String> keys = new HashSet<>(this.game.keySet());
        List<ColorBlock> blocks = new ArrayList<>();
        while(!keys.isEmpty()) {
            String key = keys.iterator().next();
            ColorCell cell = this.game.get(key);
            ColorBlock newBlock = getContinousColorBlock(cell.getX(), cell.getY());
            blocks.add(newBlock);
            keys.removeAll(newBlock.getAllPoints());
        }
        blocks.sort((ColorBlock cb1, ColorBlock cb2) -> cb2.size() - cb1.size());
        return blocks.size() > 0 ? blocks.get(0) : null;
    }
}
