import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        float x2,y2;
        float x1 = 0;
        float y1 = 0;
        float R = 0;
        String lineCircle;
        String linePoint;
        int lineNumber = 0;
        boolean isSecondLine = true;
        String pathCircle=args[0];
        String pathPoint=args[1];

        BufferedReader brCircleFile = new BufferedReader(new FileReader(pathCircle));

        while((lineCircle=brCircleFile.readLine())!=null) {
            String [] points = lineCircle.split(" ");
            if(isSecondLine){
                x1 = Float.parseFloat(points[0].trim());
                y1 = Float.parseFloat(points[1].trim());
                isSecondLine = false;
            }
            else{
                R = Float.parseFloat(points[0].trim());
            }
        }

        BufferedReader brPointFile = new BufferedReader(new FileReader(pathPoint));

        while((linePoint=brPointFile.readLine())!=null && !isSecondLine) {
            String [] points = linePoint.split(" ");
            x2 = Float.parseFloat(points[0].trim());
            y2 = Float.parseFloat(points[1].trim());

            if(pow(x2-x1)+pow(y2 - y1) < pow(R)){
                System.out.println(lineNumber + " - точка внутри");
            }
            if(pow(x2-x1)+pow(y2 - y1) == pow(R)){
                System.out.println(lineNumber + " - точка лежит на окружности");
            }
            if(pow(x2-x1)+pow(y2 - y1) > pow(R)){
                System.out.println(lineNumber + " - точка снаружи");
            }
            lineNumber++;
            if (lineNumber == 100){
                break;
            }
        }
        brPointFile.close();
        brCircleFile.close();
    }
    public static float pow(float value){
        return value*value;
    }
}

