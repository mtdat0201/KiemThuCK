
package Action;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import toeicapp.TrangChuController;
/**
 *
 * @author Angel
 */
public class ForwardForm {
    public FXMLLoader transForm (String tn, String title) throws IOException
    {
        Stage sta = new Stage();
        URL url = getClass().getClassLoader().getResource(tn);
        FXMLLoader load = new FXMLLoader(url);
        Parent root = load.load();
        Scene sce = new Scene(root);
        sta.setScene(sce);
        sta.setResizable(false);
        sta.setTitle(title);
        sta.show();
        return load;
    }   
    
    public static Object loadStage (URL u, String title, Stage paStage)
    {
        Object controler = null;
        try
        {
            FXMLLoader load = new FXMLLoader(u);
            Parent parent = load.load();
            controler = load.getController();
            Stage stage = null;
            if (paStage != null){
                stage = paStage;
            }
            else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch(IOException ex)
        {
            Logger.getLogger(TrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controler;
    }
}
