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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dateStr = "-1";
        String phoneNumber = "-1";
        String gender = "-1";
        int dateOk = -1;
        int phoneOk = -1;
        int i = 0;


        String f = "f";
        ArrayList<String> FIO = new ArrayList<String>();


        // System.out.print("Введите данные: ");
        // Иванов Иван Иванович 01.01.1991 87777777777
        // Ivanov Ivan Ivanovich 01.01.1991 87777777777
        // String str = sc.nextLine();
        
        String data = "Иванов Иван Иванович 23.10.1991 f +79112293344";
        System.out.println(data);


        // разделили строку на массив строк, где разднлителем является пробел
        String [] splitData = data.split(" ");





        for (String splitDataOne : splitData){
            System.out.println(splitDataOne);

            // проверка на наличе только букв
            if (Pattern.matches("[ЁёА-яa-zA-Z]+",splitDataOne)) { 
                System.out.println("Только буквы");

                // определили гендер
                if (splitDataOne.equalsIgnoreCase("m") | splitDataOne.equalsIgnoreCase("f")){
                gender = splitDataOne;
                }

                else {
                    FIO.add(splitDataOne);
                }
            }
            else{
                System.out.println("Не только буквы");
 
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
                            System.out.println("Неверный формат номера");
                        }
                    }
                }

                // проверка на дату
                if (dateOk == -1){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        java.util.Date date = dateFormat.parse(splitDataOne);
                        System.out.println("date = " + date);
                        dateStr = splitDataOne;
                        dateOk = 1;
                    }
                    catch (Exception e) {

                    }
                }
            }
        }

        // Вывод данных
        System.out.println();
        System.out.println("FIO = " + FIO);
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("date = " + dateStr);
        System.out.println("Gender = " + gender);
    }
}
