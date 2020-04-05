/**
 * CS 1027 Assignment 4
 * @author Edward Cheng
 * Student Number: 251024979
 * ShortestPath.java finds the shortest path
 */



public class ShortestPath {
	CityMap cityMap;

	
/**
 * This is the constructor for the class. It receives as input a reference to a CityMap object representing the city map
 * @param theMap
 */
	public ShortestPath(CityMap theMap) {
			cityMap = theMap;
	}
	
// This method will look for a path with the minimum number of map cells from the starting cell to the destination cell
	public void findShortestPath() {
		
		boolean destinationCell = false; // Set destination cell variable 
		
				OrderedCircularArray<MapCell> list = new OrderedCircularArray<MapCell>(); // Create an empty ordered list
				MapCell begin = cityMap.getStart(); // Get Starting Cell 
				int distance = 0;
				list.insert(begin, distance); // Insert the starting cell in the ordered list with a distance value of zero
				begin.markInList(); // Mark cell in list
				MapCell smallestDistance = null; 

				
				while (!list.isEmpty() && !destinationCell) { // While the list is not empty, and the destination cell has not been reached
					smallestDistance = list.getSmallest();	// Remove from the ordered list the map cell S with smallest distance value and mark it as out of the ordered list
					smallestDistance.markOutList();
					
					if(smallestDistance.isDestination()) { // If S is the destination cell, then the algorithm exits the while loop
						destinationCell = true;
						break;
					}
					
					else {
						for(int i = 0; i < 4; i ++) { // iterate
							MapCell current = nextCell(smallestDistance); // Current represents "C"
							distance = smallestDistance.getDistanceToStart() + 1; // Distance represents "D", set D = 1 + distance from S to the starting cell 
							
							if(current == null) {
								break;
							}
							
							if (current.getDistanceToStart() > distance) {
								current.setDistanceToStart(distance); // set the distance of C to the starting cell to D
								current.setPredecessor(smallestDistance); // set S as the predecessor of C in the path to the starting cell
							}
							
							int initialDistance; // this variable represents "P"
							initialDistance = current.getDistanceToStart(); // Set P = distance from C to the starting cell.


							if (current.isMarkedInList() && initialDistance < list.getValue(current)) // If C is marked as in the ordered list and P is smaller than the distance value stored in the entry of the circular array storing C (
								list.changeValue(current, initialDistance);

							if (!current.isMarked()) { // If C is not marked as in the ordered list, then add it to the ordered list with distance value equal to P
								list.insert(current, initialDistance);
								current.markInList(); // Mark C in list
							}

						}
					}
				}
			}
/**
 * This method returns the first unmarked neighboring map cell that can be used to continue the path from the current one	
 * @param cell
 * @return
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

