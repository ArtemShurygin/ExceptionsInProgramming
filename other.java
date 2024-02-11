
// проверка на число
char[] chars = splitDataOne.toCharArray();
for (char charDataOne : chars) {
    if (Character.isDigit(charDataOne)){
        System.out.println("есть число");
        break;
    }
}