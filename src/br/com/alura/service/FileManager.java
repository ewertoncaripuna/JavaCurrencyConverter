package br.com.alura.service;

import br.com.alura.model.Api;
import br.com.alura.model.ExchangeRate;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    public static String nameWithDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateToday = new Date();
        String date = sdf.format(dateToday);
        String filePath = "src/br/com/alura/files/ExchangeRates_" + date + ".json";

        return filePath;
    }

    public static void saveFile(Map<String, Double> map, String filePath){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){

            for (Map.Entry<String, Double> entry : map.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("*************************************");
            System.out.println("Outdated or missing file, generating a new file.");
            System.out.println("*************************************");
        }catch(IOException e){
            System.out.println("Error to save file: " + e.getMessage());
        }

    }


    public static Map<String, Double> openFile(String filePath){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateToday = new Date();
        String date = sdf.format(dateToday);

        File file = new File(filePath);
        String nameFile = filePath.substring(filePath.lastIndexOf("/") + 1);
        String getDateFromName = nameFile.substring(nameFile.indexOf("_") + 1, nameFile.lastIndexOf("."));


        if (file.exists() && getDateFromName.equals(date)) {

            Map<String, Double> exchangeRates = null;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                exchangeRates = new HashMap<>();
                String line = br.readLine();
                while (line != null) {
                    String[] item = line.split(",");
                    String baseCode = item[0];
                    Double rate = Double.parseDouble(item[1]);
                    exchangeRates.put(baseCode, rate);
                    line = br.readLine();
                }
                System.out.println("*************************************");
                System.out.println("Opening updated file to: " + filePath);
                System.out.println("*************************************");
            } catch (IOException e) {
                System.out.println("Error to read file: " + e.getMessage());
            }
            return exchangeRates;

        }else{
            Api api = new Api();
            ExchangeRate getCotationNow = api.getCode();
            Map<String,Double> maps = getCotationNow.getConversionRates();
            saveFile(maps, filePath);
            return openFile(filePath);
        }

    }
}
