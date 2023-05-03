import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserManager {
    public static String chooseFile() {
        JFileChooser fileChooser = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Project Balas Controls Files", "pbc");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
}
