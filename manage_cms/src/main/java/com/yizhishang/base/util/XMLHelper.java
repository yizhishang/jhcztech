package com.yizhishang.base.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.util.Properties;

public final class XMLHelper
{

    public static Document getDocument(File xmlFile)
    {
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(xmlFile);
        } catch (Exception ex) {
        }
        return null;
    }

    public static Document getDocument(String xmlFile) throws Exception
    {
        if (StringHelper.isEmpty(xmlFile)) {
            return null;
        }

        File file = null;
        SAXReader saxReader = new SAXReader();

        file = new File(xmlFile);
        return saxReader.read(file);
    }

    public static Document getDocumentFromString(String xmlString)
    {
        if (StringHelper.isEmpty(xmlString)) {
            return null;
        }
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(new StringReader(xmlString));
        } catch (Exception ex) {
        }
        return null;
    }

    public static String getString(String name, Element element)
    {
        return element.valueOf(name) == null ? "" : element.valueOf(name);
    }

    public static boolean savaToFile(Document doc, String filePathName, OutputFormat format)
    {
        try {
            String filePath = FileHelper.getFullPath(filePathName);

            if (!FileHelper.exists(filePath)) {
                if (!FileHelper.createDirectory(filePath)) {
                    return false;
                }
            }

            XMLWriter writer = new XMLWriter(new FileWriter(new File(filePathName)), format);
            writer.write(doc);
            writer.close();
            return true;
        } catch (IOException ex) {
        }

        return false;
    }

    public static boolean writeToXml(String filePathName, Document doc)
    {
        OutputFormat format = OutputFormat.createCompactFormat();
        format.setEncoding("GBK");
        return savaToFile(doc, filePathName, format);
    }

    public static String xml2html(String xmlDoc, String xslFile, String encoding) throws Exception
    {
        if (StringHelper.isEmpty(xmlDoc)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xslFile)) {
            throw new Exception("xslt file is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;
        String html = null;
        try {
            xmlSource = new StreamSource(new StringReader(xmlDoc));
            xslSource = new StreamSource(new File(xslFile));

            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.put("method", "html");
            properties.setProperty("encoding", encoding);
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);

            html = writer.toString();
            writer.close();

            return html;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    public static String xml2xsl(String xmlFilePath, String xsl) throws Exception
    {
        if (StringHelper.isEmpty(xmlFilePath)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xsl)) {
            throw new Exception("xsl string is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = new StreamSource(new File(xmlFilePath));
        Source xslSource = new StreamSource(new File(xsl));
        Result result = new StreamResult(writer);
        try {
            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.setProperty("encoding", "gb2312");
            properties.put("method", "html");
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);
            return writer.toString();
        } catch (Exception ex) {
            System.out.println("xml2xsl:" + ex);
            throw new Exception(ex);
        } finally {
            writer.close();
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    public static String xml2xsl(String xml, URL xsl) throws Exception
    {
        if (StringHelper.isEmpty(xml)) {
            throw new Exception("xml string is empty");
        }
        if (xsl == null) {
            throw new Exception("xsl string is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;
        try {
            xmlSource = new StreamSource(new StringReader(xml));
            xslSource = new StreamSource(xsl.openStream());
            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            trans.transform(xmlSource, result);
            return writer.toString();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer.close();
            writer = null;
            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }

    public static String xmlFile2html(String xmlFile, String xslFile, String encoding) throws Exception
    {
        if (StringHelper.isEmpty(xmlFile)) {
            throw new Exception("xml string is empty");
        }
        if (StringHelper.isEmpty(xslFile)) {
            throw new Exception("xslt file is empty");
        }

        StringWriter writer = new StringWriter();
        Source xmlSource = null;
        Source xslSource = null;
        Result result = null;
        String html = null;
        try {
            xmlSource = new StreamSource(new File(xmlFile));
            xslSource = new StreamSource(new File(xslFile));

            result = new StreamResult(writer);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer(xslSource);
            Properties properties = trans.getOutputProperties();
            properties.put("method", "html");
            properties.setProperty("encoding", encoding);
            trans.setOutputProperties(properties);

            trans.transform(xmlSource, result);

            html = writer.toString();
            writer.close();

            return html;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            writer = null;

            xmlSource = null;
            xslSource = null;
            result = null;
        }
    }
}
