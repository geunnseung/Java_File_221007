package week3.day4.java;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PopulationStatistics {

    public void readByChar(String filename) throws IOException {


        FileReader fileReader = new FileReader(filename);
        // 파일을 읽지 않는다

        //한 바이트씩 읽기
        //BufferedReader 보다 비효율적
        String fileContents = "";
        while (fileContents.length() < 1_000_000) {
            char c = (char)fileReader.read();
            fileContents += c;
            System.out.println(fileContents);
        }

        /*
        char c = (char)fileReader.read(); //한 바이트씩 읽음
        System.out.println(c);
         */

    }

    public List<PopulationMove> readByLine(String filename) throws IOException {
        //삽
        List<PopulationMove> pml = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
            PopulationMove pm = parse(str);
            pml.add(pm);

        }
        reader.close();
        return pml;
    }

    /*
    public void readAllLines(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        List<PopulationMove> pms = lines.parallelStream()
                .map(item -> {
                    String[] splittedLine = item.split(",");
                    return new PopulationMove(splittedLine[0], splittedLine[6]);
                }).collect(Collectors.toList());
        for (PopulationMove pm : pms) {
            System.out.println(pm.getFromSido());
        }


    }
     */
    public void readByLine2(String filename) {
        try(BufferedReader br = Files.newBufferedReader(
                Paths.get(filename), StandardCharsets.UTF_8)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAFile(String filename) {
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // List<String>을 지정한 파일에 wirte
    public void write(List<String> strs, String filename) {
        File file = new File(filename);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String str : strs) {
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public PopulationMove parse(String data) {

        String[] splittedLine = data.split(",");

        return new PopulationMove(splittedLine[6],splittedLine[1]); // 전입 전출
    }

    public String fromToString(PopulationMove populationMove) {
        return populationMove.getFromSido() + "," + populationMove.getToSido() + "\n";
    }

    public Map<Integer, Integer> heatmapIdxMap
    public Map<String, Integer> getMoveCntMap(List<PopulationMove> pml) {
        Map<String, Integer> moveCntMap = new HashMap<>();
        //A~Z
        for (PopulationMove pm : pml) {
            String key = pm.getFromSido() + "," + pm.getToSido();
            if(moveCntMap.get(key) == null) {
                moveCntMap.put(key,1);
            }
            moveCntMap.put(key, moveCntMap.get(key) +1);
        }

        return moveCntMap;

    }
    public static void main(String[] args) throws IOException {

        String address = "./from_to.txt";
        PopulationStatistics ps = new PopulationStatistics();
        List<PopulationMove> pml = ps.readByLine(address);
        //A~Z

        Map<String, Integer> map = ps.getMoveCntMap(pml);
        String targetFilename = "for_heatmap.txt";
        ps.createAFile(targetFilename);
        List<String> cntResult = new ArrayList<>();
        for (String key : map.keySet()) {
            String[] fromto = key.split(",");
            String s = String.format("[%s, %s, %d]\n",fromto[0], fromto[1], map.get(key));

            cntResult.add(s);
        }

        ps.write(cntResult, targetFilename);






        }

}
