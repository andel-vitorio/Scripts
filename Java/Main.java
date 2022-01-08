package Java;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String description = "Esta é a Descrição.";
        String title = "Este é o título.";
        char sep = 255;

        String content = title + sep + description;
        System.out.println(content);

        String[] str = content.split(String.valueOf(sep));
        System.out.println(sep);
        System.out.println(Arrays.toString(str));
    }
}