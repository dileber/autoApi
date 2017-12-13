package com.drcosu.autoapi.main;

import com.drcosu.autoapi.config.Config;
import com.drcosu.autoapi.config.Constants;
import com.drcosu.autoapi.utils.*;
import org.dom4j.DocumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws DocumentException {
        Map<String,String> properties = UProperties.readProperties();
        Config.config(properties.get("src"));
        UXml.readXml();
        UXmlTemplate.readXml();

        JFrame frame = new JFrame("AutoApi");
        AutoApi autoApi = new AutoApi();
        frame.setContentPane(autoApi.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        autoApi.Button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                String urlpost = autoApi.textField1.getText();
                String title =  autoApi.textField2.getText();
                String params = UString.captureName(title + "Params");
                String now = UTime.getBeijingNowTime(UTime.Pattern.y_m_d_h_m_s);
                String name = title;
                String url = urlpost;
                String response = UString.captureName(title+"Response");
                Map<String,Object> map = new HashMap<>();
                map.put("params",params);
                map.put("title",title);
                map.put("now",now);
                map.put("name",name);
                map.put("url",url);
                map.put("response",response);



                Map<String,String> instantiate = UXml.xml.get("instantiate");
                for(Map.Entry<String,String> entry:instantiate.entrySet()){
                    String from = entry.getKey();
                    String to = UString.templetStr(entry.getValue(), map);

                    String scrouce = Config.productSrc+"//" + to;

                    String file = Constants.TEMPLATE +  from;
                    String fileTemp = Constants.TEMP + from + ".temp";
                    AttachmentStore.create(fileTemp);

                    AttachmentStore.replace(file, fileTemp, new AttachmentStore.Oo() {
                        @Override
                        public void o(StringBuffer sb, String s) {
                            sb.append(UString.templetStr(s, map) + "\r\n");
                        }
                    });

                    AttachmentStore.save2(fileTemp, scrouce);
//            AttachmentStore.delete(temp);

                }


                Map<String,String> merge = UXml.xml.get("merge");
                for(Map.Entry<String,String> entry:merge.entrySet()){
                    String from = entry.getKey();
                    String to = UString.templetStr(entry.getValue(), map);

                    String scrouce = Config.productSrc+"//" + to;
                    String file = Constants.TEMPLATE +  from;
                    String fileTemp = Constants.TEMP + from + ".temp";

                    AttachmentStore.create(fileTemp);

                    AttachmentStore.replace(scrouce, fileTemp, new AttachmentStore.Oo() {
                        @Override
                        public void o(StringBuffer sb, String s) {
                            int index = s.indexOf("//#06#");

                            if (index != -1) {

                                sb.append("\r\n");
                                sb.append(UString.templetStr(AttachmentStore.loadAsString(file), map) + "\r\n");
                                sb.append("//#06#\r\n");
                            } else {
                                sb.append(s + "\r\n");

                            }
                        }
                    });

                    AttachmentStore.save2(fileTemp, scrouce);
//            AttachmentStore.delete(temp);

                }

                frame.dispose();



            }
        });




    }
}
