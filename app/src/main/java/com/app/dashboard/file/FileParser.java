package com.app.dashboard.file;

import android.content.Context;

import com.app.dashboard.beans.CaseBean;
import com.app.dashboard.beans.ComponentBean;
import com.app.dashboard.listener.FileCallback;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.app.dashboard.util.Constants.REPORT_XML;

/**
 * Created by khushalb on 23/10/19.
 */

public class FileParser {

    private List<ComponentBean> c1 = new ArrayList<>();
    private List<CaseBean> c2 = new ArrayList<>();

    private FileCallback fileCallback;

    private Context context;

    public FileParser(Context context) {
        this.context = context;
        fileCallback = (FileCallback) context;
        parseXml();
    }

    private void parseXml() {
        fileCallback.onLoading();
        try {
            InputStream is = context.getAssets().open(REPORT_XML);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();
            System.out.println("Root element :" + element.getNodeName());
            NodeList componentList = doc.getElementsByTagName("ComponentBean");

            for (int i = 0; i < componentList.getLength(); i++) {
                Node node1 = componentList.item(i);
                if (node1.getNodeType() == Node.ELEMENT_NODE) {
                    Element componentElement = (Element) node1;

                    ComponentBean component = new ComponentBean();
                    component.setName(componentElement.getAttribute("Name").trim());
                    component.setFunctionalArea(componentElement.getAttribute("FunctionalArea").trim());
                    component.setElapsedTime(Long.parseLong(componentElement.getAttribute("ElapsedTime").trim()));
                    component.setNbTests(Integer.parseInt(componentElement.getAttribute("nbTests").trim()));
                    component.setNbFailures(Integer.parseInt(componentElement.getAttribute("nbFailures").trim()));
                    component.setNbErrors(Integer.parseInt(componentElement.getAttribute("nbErrors").trim()));
                    component.setNbSkipped(Integer.parseInt(componentElement.getAttribute("nbSkipped").trim()));
                    component.setNbNotRun(Integer.parseInt(componentElement.getAttribute("nbNotRun").trim()));
                    component.setNbReRun(Integer.parseInt(componentElement.getAttribute("nbReRun").trim()));
                    component.setNbPerfFailures(Integer.parseInt(componentElement.getAttribute("nbPerfFailures").trim()));
                    component.setEmail(componentElement.getAttribute("Email").trim());
                    component.setTotalElapsedTime(Long.parseLong(componentElement.getAttribute("TotalElapsedTime").replace("&#xA;", "").trim()));
//                    c1.add(component);
                    System.out.println(component.toString());


/*                    NodeList executionList = ((Element) componentList.item(i)).getElementsByTagName("Execution");

                    for (int j = 0; j < executionList.getLength(); j++) {

                        NodeList suite1List = ((Element) executionList.item(j)).getElementsByTagName("Suite");

                        for (int k = 0; k < suite1List.getLength(); k++) {

                            NodeList suite2List = ((Element) suite1List.item(k)).getElementsByTagName("Suite");

                            for (int l = 0; l < suite2List.getLength(); l++) {

                                NodeList caseList = ((Element) suite2List.item(l)).getElementsByTagName("CaseBean");

                                for (int m = 0; m < caseList.getLength(); m++) {

                                    Node node2 = caseList.item(m);
                                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                                        Element caseElement = (Element) node2;

                                        CaseBean c = new CaseBean();
                                        c.setComponentName(componentElement.getAttribute("Name").trim());
                                        c.setName(caseElement.getAttribute("Name").trim());
                                        c.setCaseId(caseElement.getAttribute("Id"));
                                        c.setShortId(caseElement.getAttribute("ShortId"));
                                        c.setCaseStatus(caseElement.getAttribute("Status"));
                                        c.setStartTime(caseElement.getAttribute("StartTime"));
                                        c.setKeywords(caseElement.getAttribute("Keywords"));
                                        c.setElapsedTime(Long.parseLong(caseElement.getAttribute("ElapsedTime")));
                                        c.setReRun(caseElement.getAttribute("Rerun") == "FALSE" ? false : true);

//                                        c2.add(c);
                                        System.out.println(c.toString());
                                    }
                                }
                            }
                        }
                    }*/
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        fileCallback.onCompleted(c1, c2);
    }


    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
