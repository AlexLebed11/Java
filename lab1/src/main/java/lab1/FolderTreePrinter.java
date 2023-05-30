package lab1;
import java.io.File;

public class FolderTreePrinter {

    public static void main(String[] args) {
        File currentDirectory = new File(".");
        printFolderTree(currentDirectory, 0);
    }

    private static void printFolderTree(File folder, int LevelIndent) {
        if (!folder.isDirectory()) {
            return;
        }

        String indent = getIndent(LevelIndent);
        System.out.println(indent + folder.getName());

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    printFolderTree(file, LevelIndent + 1);
                } else {
                    System.out.println(getIndent(LevelIndent + 1) + file.getName());
                }
            }
        }
    }

    private static String getIndent(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("\t");
        }
        return indent.toString();
    }
}