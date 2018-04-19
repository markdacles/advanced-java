import java.util.*;
import static java.util.stream.Collectors.*;

public class Matrix {

	List<List<StringPair>> matrix = new ArrayList<List<StringPair>>();
	private int row, col;
	private String table[][] = null;

	public Matrix() {
		matrix = Helper.fileToMatrix();
		System.out.println(" ");
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(i).size(); j++) {
				System.out.print(matrix.get(i).get(j).key() + "," + matrix.get(i).get(j).value());
				if( j < matrix.get(i).size() - 1) {
					System.out.print(" █ ");
				}
			}	
			System.out.println();
		}
	}

	private void getDimensions() {
		System.out.print("Input row: ");
		row = Helper.scanInt("row");
		System.out.print("Input column: ");
		col = Helper.scanInt("column");
	}

	public void displayTable() {
		System.out.println(" ");
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(i).size(); j++) {
				System.out.print(matrix.get(i).get(j).key() + "," + matrix.get(i).get(j).value());
				if( j < matrix.get(i).size() - 1) {
					System.out.print(" █ ");
				}
			}	
			System.out.println();
		}
	}

	public String keyOrValue() {
		System.out.print("Edit Key or Value or Both [K/V/B]: ");
		return Helper.scanStr();
	}

	public void editTable() {
		boolean redo = true;
		String newStr = "";
		String kov = "";
		System.out.println();
		while(redo) {
			getDimensions();
			kov = keyOrValue();
			if(row < matrix.size() && row >= 0 && col < matrix.get(row).size() && col >=0) {
				redo = false;
			} 	
			else {
				System.out.println("Error! Invalid index.");
			}
		}
		Helper.scanNxtLine();
		if(kov.equalsIgnoreCase("k")) {
			System.out.print("Input new key string: ");
			newStr = Helper.scanStr();
			matrix.get(row).get(col).setKey(newStr);
		}
		else if(kov.equalsIgnoreCase("v")) {
			System.out.print("Input new value string: ");
			newStr = Helper.scanStr();
			matrix.get(row).get(col).setValue(newStr);
		}
		else if(kov.equalsIgnoreCase("b")) {
			System.out.print("Input new key string: ");
			newStr = Helper.scanStr();
			matrix.get(row).get(col).setKey(newStr);
			System.out.print("Input new value string: ");
			newStr = Helper.scanStr();
			matrix.get(row).get(col).setValue(newStr);
		}
	}

	public int searcher(int i, int j, String str) {
		int iFrom = 0;
		//int count = 0;
		int total = 0;
		while(iFrom != -1) {
			// if(kov == "key") {
			// 	iFrom = matrix.get(i).get(j).key().indexOf(str, iFrom);
			// }
			// else if(kov == "value") {
			// 	iFrom = matrix.get(i).get(j).value().indexOf(str, iFrom);
			// }

			iFrom = matrix.get(i).get(j).merged().indexOf(str, iFrom);

			if(iFrom != -1) {
				//count++;
				total++;
				iFrom += str.length();
			}
		}
		// if(count > 0 && kov == "key") {
		// 	System.out.println(count + " KEY occurence(s) at index (" + i + "," + j + ").");
		// }
		// else if(count > 0 && kov == "value") {
		// 	System.out.println(count + " VALUE occurence(s) at index (" + i + "," + j + ").");
		// }

		if(total > 0) {
			System.out.println(total + " occurence(s) at index (" + i + "," + j + ") for search string \"" + str + "\".");
		}
		return total;
	}

	public void searchTable() {
		System.out.println();
		System.out.print("Input search string: ");
		String str = Helper.scanStr();
		int total = 0;
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0;j < matrix.get(i).size(); j++) {
				total += searcher(i,j, str);
				//total += searcher(i,j, str, "value");
			}
		}
		if(total == 0) {
			System.out.println("No occurences found.");
		} 
		else {
			System.out.println(total + " total occurence(s) in the table.");
		}
	}

	public void resetTable() {
		matrix.clear();
		Random rn = new Random();
		StringPair sp;
		String[] sPair = new String[2];
		Arrays.fill( sPair, "");
		for(int i = (rn.nextInt(5) + 1); i > 0; i--) {
			ArrayList<StringPair> column = new ArrayList<StringPair>();
			for(int j = (rn.nextInt(5) + 1); j > 0; j--) {
				sp = new StringPair("","");
				sp.setRandomKey();
				sp.setRandomValue();
				column.add(sp);
			}
			matrix.add(column);
		}
	}

	public void addRow() {
		ArrayList<StringPair> column = new ArrayList<StringPair>();
		StringPair sp;
		String[] pairTokens;
		System.out.println();
		System.out.print("Input number of columns: ");
		int addCol = Helper.scanInt("");
		String[] tokens = new String[addCol];
		for( int i = 0; i < addCol ; i++) {
			System.out.print("Enter pair for index (" + (matrix.size()) + "," + i + "): ");
			tokens[i] = Helper.scanStr();
			pairTokens = tokens[i].split(",",2);
			if ( (pairTokens.length & 1) != 0 ) {
				System.out.println("Invalid input! Please try again.");
				i--;
			}
		}
		for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            pairTokens = token.split(",",2);
            for(int i =0; i < pairTokens.length; i+=2) {
                sp = new StringPair(pairTokens[i], pairTokens[i+1]);
                column.add(sp);
            }
        }
        matrix.add(column);
       // Helper.addRow(col,matrix);
	}

	public void sortTable() {
		System.out.println();
		System.out.print("Ascending or Descending Sort [A/D]: ");
		String aod = Helper.scanStr();
		// for(int i = 0; i < matrix.size(); i++) {
		// 	for(int j = 0;j < matrix.get(i).size(); j++) {
		// 		for(int k = j+1; k<matrix.get(i).size(); k++) {
		// 			if(aod.equalsIgnoreCase("a")) {
		// 				if((Helper.mergeString(matrix, i, j).compareTo(Helper.mergeString(matrix, i , k))) > 0) {
		// 					Collections.swap(matrix.get(i), j, k);
		// 				}
		// 			}
		// 			else if(aod.equalsIgnoreCase("d")) {
		// 				if((Helper.mergeString(matrix, i, j).compareTo(Helper.mergeString(matrix, i , k))) < 0) {
		// 					Collections.swap(matrix.get(i), j, k);
		// 				}
		// 			}
		// 		}
		// 	}
		// }
		if(aod.equalsIgnoreCase("a")) {
			// Collections.sort(matrix, Helper.colComp(0, Comparator.<StringPair>naturalOrder()));
		   //	for(int i = 0; i < matrix.size(); i++) {

		   	for(int i = 0; i < matrix.size(); i++) {
				matrix.get(i).sort();
			}
			//}	
			// Collections.sort(matrix, new Comparator<ArrayList<StringPair>>() {    
	  	//       	@Override
		 //        public int compare(ArrayList<StringPair> o1, ArrayList<StringPair> o2) {
		 //            return o1.get(0).compareTo(o2.get(0));
		 //        }               
			// });
		}
		else if(aod.equalsIgnoreCase("d")) {
			// Collections.sort(matrix, new Comparator<ArrayList<StringPair>>() {    
	  //       	@Override
		 //        public int compare(ArrayList<StringPair> o1, ArrayList<StringPair> o2) {
		 //            return o2.get(0).compareTo(o1.get(0));
		 //        }               
			// });
		}
	}

	public void writeToFile() {
		Helper.writeToFile(matrix);
	}
}