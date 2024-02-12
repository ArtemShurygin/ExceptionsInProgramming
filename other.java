
// проверка на число
char[] chars = splitDataOne.toCharArray();
for (char charDataOne : chars) {
    if (Character.isDigit(charDataOne)){
        System.out.println("есть число");
        break;
    }
}

// попытка преобразовать строку в число (номер телефона)
try{
    long i = Long.parseLong(splitDataOne);
    System.out.println("long i = " + i);
    phoneNumber = i;
    }
    
    catch (NumberFormatException nfe)
    {
    //System.out.println("NumberFormatException: " + nfe.getMessage());
    }


    System.out.print("Введите следующие данные в произвольном порядке, разделенные пробелом:\n" + 
    "Фамилия Имя Отчество датарождения номертелефона пол\n" + "\n" + 
    "Форматы данных:\n" + 
    "фамилия, имя, отчество - строки\n" + 
    "датарождения - строка формата dd.mm.yyyy\n" + 
    "номертелефона - целое беззнаковое число без форматирования\n" + 
    "пол - символ латиницей f или m\n" + "\n" + 
    "Введите данные: \n"
    );