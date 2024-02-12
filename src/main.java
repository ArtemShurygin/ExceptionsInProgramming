import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dateStr = "-1";
        String phoneNumber = "-1";
        String gender = "-1";
        String data = "-1";
        int genderOk = -1;
        int dateOk = -1;
        int phoneOk = -1;
        int fioOk = -3;
        int i = 0;
        ArrayList<String> FIO = new ArrayList<String>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        // запрещает смещать дату, если введено большее количество дней/месяцев, чем возможно
        dateFormat.setLenient(false);
        String dateMax = "01.01.2024";
        String dateMin = "01.01.1900";

        System.out.print("Введите следующие данные в произвольном порядке, разделенные пробелом:\n" + 
        "Фамилия Имя Отчество датарождения номертелефона пол\n" + "\n" + 
        "Форматы данных:\n" + 
        "фамилия, имя, отчество - строки\n" + 
        "датарождения - строка формата dd.mm.yyyy\n" + 
        "номертелефона - целое беззнаковое число без форматирования\n" + 
        "пол - символ латиницей f или m\n" + "\n" + 
        "Введите данные: \n");
        // String data = "Ivanov Ivan Ivanovich 23.10.1991 f +79112293344";
        // String data = "Иванов Иван Иванович 23.10.1991 f +79112293344";  

        try {
            data = sc.nextLine();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.out.println(data);

        // разделили строку на массив строк, где разднлителем является пробел
        String [] splitData = data.split(" ");

        for (String splitDataOne : splitData){
            //System.out.println(splitDataOne);

            // проверка на наличе только букв
            if (Pattern.matches("[ЁёА-яa-zA-Z]+",splitDataOne)) { 
                //System.out.println("Только буквы");

                // определили гендер
                if (splitDataOne.equalsIgnoreCase("m") | splitDataOne.equalsIgnoreCase("f")){
                gender = splitDataOne;
                genderOk = 0;
                }

                else {
                    FIO.add(splitDataOne);
                    fioOk++;
                }
            }
            else{
                //System.out.println("Не только буквы");
 
                // проверка на номер телефона
                if (phoneOk == -1){
                    if (Pattern.matches("[0-9+]+",splitDataOne)) {
                        if (splitDataOne.charAt(0) == '+'){
                            //System.out.println("Есть +");
                            i--;
                        }
                        // проверка на количество символов в номере
                        if(splitDataOne.length() + i == 11){
                            //System.out.println("Ок");
                            phoneNumber = splitDataOne;
                            phoneOk = 0;
                        }
                        else{
                            throw new NumberFormatException("Неверный формат телефона, телефон должен начинаться с +7 или 8 и иметь 11 цифр (89112223344)");
                        }
                    }
                }

                // проверка на дату
                if (dateOk == -1){
                    try {
                        if(splitDataOne.length() == 10){
                            java.util.Date date = dateFormat.parse(splitDataOne);
                            //System.out.println("date = " + date);
                            if(date.after(dateFormat.parse(dateMin)) && date.before(dateFormat.parse(dateMax))) {
                                dateStr = splitDataOne;
                                dateOk = 0;
                            }
                            else{
                                throw new NumberFormatException("Дата рождения не попала во временные рамки 01.01.1900-01.01.2024");
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        /* 
        // Вывод кодов ошибки
        System.out.println();
        System.out.println("fioOk = " + fioOk);
        System.out.println("dateOk = " + dateOk);
        System.out.println("phoneOk = " + phoneOk);
        System.out.println("genderOk = " + genderOk);

        // Вывод данных
        System.out.println();
        System.out.println("FIO = " + FIO);
        System.out.println("date = " + dateStr);
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("Gender = " + gender);
        System.out.println();
        */

    if(fioOk != 0){
        throw new RuntimeException("Не введены или неверно введены ФИО! ФИО включает 3 слова, разделенных пробелом, без цифр и символов");
    }
    if(dateOk != 0){
        throw new RuntimeException("Не введена или неверно введена дата рождения! Формат дыты: dd.mm.yyyy");
    }
    if(phoneOk != 0){
        throw new RuntimeException("Не введен или неверно введен номер телефона! Номер должен начинаться с +7 или 8 и иметь 11 цифр (89112223344)");
    }
    if(genderOk != 0){
        throw new RuntimeException("Не введен или неверно введены пол! Пол - символ латиницей f или m");
    }

        if(fioOk + dateOk + phoneOk + genderOk == 0){
            //System.out.println("allOk");
            String info = FIO.get(0) + " " + FIO.get(1) + " " +FIO.get(2) +
            " " + dateStr + " " + phoneNumber + " " + gender;
            //System.out.println(info);

            //проверка наличия файла относительно открытого проекта
            String thisDirectory = System.getProperty("user.dir");
            //File file = new File(thisDirectory + "\\Data\\testFile.txt");

            try{

                File file = new File(thisDirectory + "\\Data\\" + FIO.get(0) + ".txt");

                // Создаёт файл, если он отсутствует
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file,true); 
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(info);
                bw.newLine();
                bw.close();
                System.out.println("Success");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}