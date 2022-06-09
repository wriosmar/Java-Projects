import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class OccurrenceSum {
    public static void main(String args[]) throws IOException {
        // I couldn't figure out how to write my command line arguments in console so i just used a String array 
        // Change all testCmdLine to args
        String[] testCmdLine = {"data_1.csv"};


        List<String> data = new ArrayList<>();

        for(String comLine : testCmdLine) {
            List<String> line = Files.readAllLines(Paths.get(comLine));

            data.addAll(line);
        }

        String print = "";
        for(String addPrint : data) {
            print += addPrint + " ";
        }

        // System.out.println(print);

        Map<String, Integer> wordCount = new HashMap<>();

        for(String line : data) {
            // These three lines is what I added
            String[] keyValue = line.split(",");
            String key = keyValue[0];
            int value = Integer.parseInt(keyValue[1]);

            Integer current = wordCount.get(key);
            if(current == null) {
                wordCount.put(key, value);
            }
            else {
                wordCount.put(key, current + value);
            }
        }

        // Takes ties into account
        int largestCountTrack = 0;
        List<String> largestList = new ArrayList<>();
        List<Integer> largestCountList = new ArrayList<>();
        for(String key : wordCount.keySet()) {
            if(wordCount.get(key) > largestCountTrack) {
                largestList.clear();
                largestCountList.clear();

                largestList.add(key);
                largestCountList.add(wordCount.get(key));

                largestCountTrack = wordCount.get(key);
            }
            else if(wordCount.get(key) == largestCountTrack) {
                largestList.add(key);
                largestCountList.add(wordCount.get(key));
            }
        }

        // Prints out the results 
        for(int i = 0; i < largestList.size(); i++) {
            System.out.println(largestList.get(i) + ", " + largestCountList.get(i));
        }
    }
}