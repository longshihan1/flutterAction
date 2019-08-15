package leetcode.utils;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.notification.Notifications.Bus;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.Balloon.Position;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.awt.RelativePoint;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class MessageUtils {
    public static void showMsg(JComponent component, MessageType messageType, String title, String body)
    {
        JBPopupFactory factory = JBPopupFactory.getInstance();
        BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(body, messageType, null);
        builder.setTitle(title);
        builder.setFillColor(JBColor.background());
        Balloon b = builder.createBalloon();
        Rectangle r = component.getBounds();
        RelativePoint p = new RelativePoint(component, new Point(r.x + r.width, r.y + 30));
        b.show(p, Balloon.Position.atRight);
    }

    public static void showInfoMsg(String title, String body)
    {
        Notifications.Bus.notify(new Notification("leetcode editor", title, body, NotificationType.INFORMATION));
    }

    public static void showWarnMsg(String title, String body)
    {
        Notifications.Bus.notify(new Notification("leetcode editor", title, body, NotificationType.WARNING));
    }

    public static void showErrorMsg(String title, String body)
    {
        Notifications.Bus.notify(new Notification("leetcode editor", title, body, NotificationType.ERROR));
    }
}
