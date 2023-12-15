package org.example;
import java.io.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        try {
            
            String testsJsonPath = args[0];
            String valuesJsonPath = args[1];

            String testsJson = readFile(testsJsonPath);
            String valuesJson = readFile(valuesJsonPath);

            JSONObject testsJsonObject = new JSONObject(testsJson);
            JSONObject valuesJsonObject = new JSONObject(valuesJson);

            JSONArray testsArray = testsJsonObject.getJSONArray("tests");
            JSONArray valuesArray = valuesJsonObject.getJSONArray("values");

            fillValues(testsArray, valuesArray);

            String reportJsonPath = "report.json";
            writeFile(reportJsonPath, testsJsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fillValues(JSONArray testsArray, JSONArray valuesArray) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject testJsonObject = testsArray.getJSONObject(i);
            int testId = testJsonObject.getInt("id");

            for (int j = 0; j < valuesArray.length(); j++) {
                JSONObject valueJsonObject = valuesArray.getJSONObject(j);
                int valueId = valueJsonObject.getInt("id");
                String testValue = valueJsonObject.getString("value");

                if (testId == valueId) {
                    testJsonObject.put("value", testValue);
                    if (testJsonObject.has("values")) {
                        JSONArray nestedTestsArray = testJsonObject.getJSONArray("values");
                        //System.out.println(nestedTestsArray);
                        fillValues(nestedTestsArray, valuesArray);
                    }
                    break;
                }
            }
        }
    }

    public static String readFile(String filePath) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            contentBuilder.append(line);
        }
        reader.close();
        return contentBuilder.toString();
    }

    public static void writeFile(String filePath, String content) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }
}