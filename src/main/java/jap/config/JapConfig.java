package jap.config;

public class JapConfig {
    static ConfigIO io = new ConfigIO();

    public static String getOpenOfficeHome() {
        return io.readContent("open-office.home");
    }

    public static void setOpenOfficeHome(String openOfficeHomeString) {
        io.writeContent("open-office.home", openOfficeHomeString);
    }

    public static boolean isFirstCome() {
        return Boolean.valueOf(io.readContent("first-come"));
    }

    public static void setFirstCome(boolean isFirstCome) {
        io.writeContent("first-come", isFirstCome + "");
    }
}
