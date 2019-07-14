package gaode;

import java.io.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class CodeSearch {
    public static void main(String[] args) throws IOException {
        //读取gaode.xlsx
        FileInputStream in = new FileInputStream(new File("C:\\Users\\14807\\Downloads\\gaode.xlsx"));
        Workbook workbook = new XSSFWorkbook(in);

        Sheet sheet = workbook.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        //定义一个HashMap储存城市，key值：abcode，value值：city对象
        Map<String,City> hashmap=new HashMap<String, City>();

        for (int i = 2;i < rowNum; i++)
        {
            City city =new City();
            Row row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String abcode = row.getCell(1).getStringCellValue();
            city.setName(name);
            city.setAbcode(abcode);
            hashmap.put(city.getAbcode(),city);
        }
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
        String string = null;
        System.out.println("输入想查询城市的邮编：");
        System.out.println("输入'结束'退出程序");
        do {
            try {
                string=bufferedReader.readLine();
                if (hashmap.containsKey(string)){
                    City city = hashmap.get(string);
                    System.out.println("城市的邮编是："+city.getAbcode());
                    System.out.println("城市的名称是："+city.getName());
                }else if(string.equals("结束")){
                    continue;
                }else if(!hashmap.containsKey(string)){
                    System.out.println("未找到该邮编对应的城市名称，请输入正确邮编");
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }while (!string.equals("结束"));



    }
}
