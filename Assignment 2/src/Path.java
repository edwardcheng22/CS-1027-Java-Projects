/** CS 1027 Assignment 2
 *  @author Edward Cheng
 *  251024979
 */

public class Path {

    // Instance variable that represents the city map
    private Map cityMap;

    // Constructor that assigns the input map to the instance variable cityMap
    public Path(Map theMap){
        cityMap = theMap;
    }

    /*
    Looks for a path from the starting cell to the destination cell
    Prints how many cells were in the path if one was found
    OR
    It will print no path was found

    This is done through the algorithm given for the assignment
     */
    public void findPath(){
        // Create an empty stack that will store the objects of the class MapCell
        ArrayStack<MapCell> stack = new ArrayStack<MapCell>(5,5,5);
        // Gets the starting cell and calls markInStack() on it
        cityMap.getStart().markInStack();
        // Pushes the starting cell into the stack
        stack.push(cityMap.getStart());

        // WHILE the stack is not empty perform the following steps
        while(!stack.isEmpty()){
            // Peek at the top of the stack to get the current cell
            MapCell curr = stack.peek();

            // If the current cell is the destination, then exit the algorithm
            if(curr.isDestination()) {
                curr.markCustomer();
                break;
            } else{
                // Otherwise find the best unmarked neighbouring cell using nextCell()
                MapCell next = nextCell(curr);
                if(next == null){
                    // If no cell is returned then pop the top cell from the stack and mark it outOfStack
                    curr.markOutStack();
                    stack.pop();
                } else{
                    // If a cell is returned then mark it as inStack and push it into the stack
                    next.markInStack();
                    stack.push(next);
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("No path found.");
        } else{
            System.out.println("Path found containing " + stack.size() + " cells.");
        }
    }

    /*
    Returns the best map cell to continue the path from the current one
    The param is the current map cell
    Returns null if there is no next possible cell
    The method returns the cells with the priority stated in the assignment
    1. Destination Cell
    2. Intersection cell with the lowest index
    3. Road cell with the lowest index
     */
    private MapCell nextCell(MapCell cell){
        // Make the bestMove null by default
        MapCell bestMove = null;
        // Loop through from 0-3 to go through the adjacent cells of the current cell
        for (int i = 0; i <= 3; i++) {
            // Get the adjacent cell of the current index
            MapCell adjacent = cell.getNeighbour(i);
            // If the adjacent cell isn't null or hasn't been marked
            if (adjacent != null && !adjacent.isMarked()) {
                // If the adjacent cell is the destination and the current cell points towards the direction of the destination
                if (((cell.isStart() || cell.isIntersection()) && adjacent.isDestination()) || (i == 0 && adjacent.isDestination() && cell.isNorthRoad()) || (i == 1 && adjacent.isDestination() && cell.isEastRoad()) || (i == 2 && adjacent.isDestination() && cell.isSouthRoad()) || (i == 3 && adjacent.isDestination() && cell.isWestRoad())) {
                    // Return the destination cell;
                    return adjacent;
                } else if (((cell.isStart() || cell.isIntersection()) && adjacent.isIntersection()) || (i == 0 && adjacent.isIntersection() && cell.isNorthRoad()) || (i == 1 && adjacent.isIntersection() && cell.isEastRoad()) || (i == 2 && adjacent.isIntersection() && cell.isSouthRoad()) || (i == 3 && adjacent.isIntersection() && cell.isWestRoad())) {
                    // Return the intersection cell if the current cell points towards the direction of the intersection
                    return adjacent;
                } else if (i == 0 && adjacent.isNorthRoad() && (cell.isNorthRoad() || cell.isIntersection())) {
                    // If the best move hasnt been changed then make it the North Cell to ensure the lowest index is returned
                    if (bestMove == null) {
                        bestMove = adjacent;
                    }
                } else if (i == 1 && adjacent.isEastRoad() && (cell.isEastRoad() || cell.isIntersection())) {
                    // If the best move hasnt been changed then make it the East Cell to ensure the lowest index is returned
                    if (bestMove == null) {
                        bestMove = adjacent;
                    }
                } else if (i == 2 && adjacent.isSouthRoad() && (cell.isSouthRoad() || cell.isIntersection())) {
                    // If the best move hasnt been changed then make it the South Cell to ensure the lowest index is returned
                    if (bestMove == null) {
                        bestMove = adjacent;
                    }
                } else if (i == 3 && adjacent.isWestRoad() && (cell.isWestRoad() || cell.isIntersection())) {
                    // If the best move hasnt been changed then make it the West Cell to ensure the lowest index is returned
                    if (bestMove == null) {
                        bestMove = adjacent;
                    }
                }
            }
        }
        return bestMove;
    }



}
