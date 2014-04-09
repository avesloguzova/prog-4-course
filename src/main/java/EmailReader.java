import javax.mail.*;
import java.util.Properties;

/**
 * Created by avesloguzova on 16.03.14.
 */
public class EmailReader {
    Session session;
    Properties props = new Properties();
    String email;
    String password;

    public EmailReader(final String email, final String password) {
        props.put("mail.pop3.host", "pop.gmail.com");

        props.put("mail.pop3.user", email);

        props.put("mail.pop3.socketFactory", 995);

        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.put("mail.pop3.port", 995);

        this.email = email;
        this.password = password;

        session = Session.getDefaultInstance(props);

    }

    public static void main(String[] args) {
        new EmailReader("sashik.himik@gmail.com", "gjhfljrc").readInputEmails();
    }

    public void readInputEmails() {


        try {
            Store store = session.getStore("pop3");

            store.connect("pop.gmail.com", email, password);

            Folder fldr = store.getFolder("INBOX");

            fldr.open(Folder.READ_ONLY);

            Message[] msg = fldr.getMessages();

            for(Message message: msg){
                System.out.println(message.getSubject());
                System.out.println(message.getSentDate());
            }
            fldr.close(true);

            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
