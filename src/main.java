/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол

Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
 */


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dateStr = "-1";
        String iStr = "-1";
        String gender = "-1";
        int dateOk = -1;
        int iOk = -1;


        String f = "f";


        // System.out.print("Введите данные: ");
        // Иванов Иван Иванович 01.01.1991 87777777777
        // Ivanov Ivan Ivanovich 01.01.1991 87777777777
        // String str = sc.nextLine();
        String data = "Иванов Иван Иванович 01.05.1991 f 99999999999";
        System.out.println(data);
        String [] splitData = data.split(" ");

        if (dateOk == -1 | iOk == -1){
            
        }


        for (String splitDataOne : splitData){
            System.out.println(splitDataOne);

            if (splitDataOne.equalsIgnoreCase("m") | splitDataOne.equalsIgnoreCase("f")){
                gender = splitDataOne;
                System.out.print("ОК ");
            }


            char[] chars = splitDataOne.toCharArray();
            for (char charDataOne : chars) {
                if (Character.isDigit(charDataOne)){
                    System.out.println("есть число");
                    break;
                }
            }



            if (dateOk == -1 | iOk == -1){
                try{
                // именно здесь String преобразуется в int
                long i = Long.parseLong(splitDataOne);


                // выведем на экран значение после конвертации
                System.out.println("long i = " + i);
                iStr = splitDataOne;
                }
                
                catch (NumberFormatException nfe)
                {
                //System.out.println("NumberFormatException: " + nfe.getMessage());
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    java.util.Date date = dateFormat.parse(splitDataOne);
                    System.out.println("date = " + date);
                    dateStr = splitDataOne;
                    dateOk = 1;
                } catch (Exception e) {

                }
            }






            /* 
            if (splitDataOne instanceof String) {
                System.out.print("Строка ");
            } 
            */ 
            /* 
            if (splitDataOne instanceof Integer) {
                System.out.println("123 ");
            }
            */

            

            
        }
        System.out.println();
        System.out.println("long i = " + iStr);
        System.out.println("date = " + dateStr);
        System.out.println("Gender = " + gender);

    }
}
//////