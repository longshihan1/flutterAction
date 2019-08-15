import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBColor;
import model.FANYIINFO;
import model.TranslationInfo;
import org.apache.http.util.TextUtils;

import java.awt.*;

public class OriginAction extends AnAction {
    Gson gson = new Gson();

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        String classPath = psiFile.getVirtualFile().getPath();
        String title = "Hello Worldxsxs!";
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }
        showEnglishPop(mEditor,selectedText);
    }

    public void showEnglishPop(Editor mEditor, String msg1) {
        String baseUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
        FANYIINFO fanyiinfo = HttpUtils.doGet(baseUrl + msg1);
        System.out.println("" + fanyiinfo.toString());
        if (fanyiinfo.isSuccess() && !TextUtils.isEmpty(fanyiinfo.getData())) {
            TranslationInfo translationInfo = gson.fromJson(fanyiinfo.getData(), TranslationInfo.class);
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    JBPopupFactory factory = JBPopupFactory.getInstance();
                    factory.createHtmlTextBalloonBuilder(translationInfo.getTranslation().toString(), null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                            .setFadeoutTime(5000)
                            .createBalloon()
                            .show(factory.guessBestPopupLocation(mEditor), Balloon.Position.below);
                }
            });
        } else {
            System.out.println("请求失败");
        }
    }


}
