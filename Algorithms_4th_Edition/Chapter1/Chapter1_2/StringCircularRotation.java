package Chapter1.Chapter1_2;

public class StringCircularRotation {
    public static void main(String[] args) {
        String a = "ACTGACG";
        String b = "TGACGAC";

        System.out.println(isRotatedString(a, b));
    }

    public static boolean isRotatedString(String s, String t) {
        if (s == null || t == null) return false;
        return s.length() == t.length() && (s + s).contains(t);
    }
}
