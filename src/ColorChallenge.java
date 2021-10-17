import java.util.ArrayList;
import java.util.Scanner;
/*
 * Chalana Wickramasinghe
 */
public class ColorChallenge {

    public static void main(String[] args) {
        int gridHeight = 0;
        int gridWidth = 0;
        
        Scanner input = new Scanner(System.in);
        System.out.print("Input Grid Height(Columns): ");
        gridHeight = input.nextInt();
        
        System.out.print("Input Grid Width(Rows): ");
        gridWidth = input.nextInt();
        
        System.out.println("Please enter the colors, type 'exit' for quit");
        int colorNo = 1;
        ArrayList<String> colorList = new ArrayList<>();
        while(true) {
        	System.out.print("Input ("+(colorNo++)+") Grid Color: ");
        	String i = input.next().toUpperCase();
        	if(i.equalsIgnoreCase("exit"))
        		break;
        	if(colorList.contains(i.substring(0,1)))
        		System.out.println("Cannot insert same color code");
        	else
        		colorList.add(i.substring(0, 1));
        }
        
        ColorGridProcess process = new ColorGridProcess();
        process.initialize(gridHeight, gridWidth, colorList);
        process.desplayColorGrids();

        ColorBlock colorBlock = process.getMaxColorBlock();

        if (colorBlock != null) {
            System.out.println();
            process.desplayColorGridWithBlocks(colorBlock);
        }
    }


}