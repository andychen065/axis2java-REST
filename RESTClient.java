package cn.edu.xidian;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class RESTClient {

  private static String toEpr = "http://localhost:8080/axis2/services/SalaryService";

        public static void main(String[] args) throws AxisFault {

                Options options = new Options();
                options.setTo(new EndpointReference(toEpr));
                options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

                options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

                ServiceClient sender = new ServiceClient();

                sender.setOptions(options);
                OMElement result = sender.sendReceive(getPayload());

                try {
                        XMLStreamWriter writer = XMLOutputFactory.newInstance()
                                        .createXMLStreamWriter(System.out);
                        result.serialize(writer);
                        writer.flush();
                } catch (XMLStreamException e) {
                        e.printStackTrace();
                } catch (FactoryConfigurationError e) {
                        e.printStackTrace();
                }
        }


        private static OMElement getPayload() {
                OMFactory fac = OMAbstractFactory.getOMFactory();
                OMNamespace omNs = fac.createOMNamespace(
                                "http://xidian.edu.cn", "xsd");
                OMElement method = fac.createOMElement("getSalary", omNs);
                OMElement value = fac.createOMElement("name", omNs);
                value.addChild(fac.createOMText(value, "lisi"));
                method.addChild(value);

                return method;
        }
}