package transfer.data;

public class Configs {
    //默认保存地址为当前目录
    private static String fileDirectory=".";

    public static String getFileDiretory() {
        return fileDirectory;
    }

    public static void setFileDirectory(String fileDirectory) {
        Configs.fileDirectory = fileDirectory;
    }
    public static String toFile(){
        return fileDirectory;
    }
    public static void fromFile(String config){
        Configs.setFileDirectory(config);
    }
}
