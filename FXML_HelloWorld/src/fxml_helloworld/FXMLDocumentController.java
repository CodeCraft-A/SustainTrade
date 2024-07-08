package fxml_helloworld;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField phoneField;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        
        if (registerNewUser(firstName, lastName, email, phone)) {
            label.setText("User registered successfully!");
        } else {
            label.setText("Error registering user.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private boolean registerNewUser(String firstName, String lastName, String email, String phone) {
        try {
            File file = new File("users.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;

            // Jika file tidak ada, buat dokumen XML baru
            if (!file.exists()) {
                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Users");
                doc.appendChild(rootElement);
            } else {
                doc = docBuilder.parse(file);
            }

            // Tambahkan elemen user baru
            Element root = doc.getDocumentElement();
            Element user = doc.createElement("User");
            
            Element firstNameElement = doc.createElement("FirstName");
            firstNameElement.appendChild(doc.createTextNode(firstName));
            user.appendChild(firstNameElement);

            Element lastNameElement = doc.createElement("LastName");
            lastNameElement.appendChild(doc.createTextNode(lastName));
            user.appendChild(lastNameElement);

            Element emailElement = doc.createElement("EmailAddress");
            emailElement.appendChild(doc.createTextNode(email));
            user.appendChild(emailElement);

            Element phoneElement = doc.createElement("PhoneNumber");
            phoneElement.appendChild(doc.createTextNode(phone));
            user.appendChild(phoneElement);

            root.appendChild(user);

            // Tulis konten ke file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

            return true;
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            e.printStackTrace();
            return false;
        }
    }    
}