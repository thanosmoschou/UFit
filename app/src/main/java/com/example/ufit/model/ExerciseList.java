/*
Author: Thanos Moschou
Description: This is a simple fitness app.
 */

package com.example.ufit.model;

import android.content.res.AssetManager;

import com.example.ufit.model.Exercise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExerciseList
{
    private ArrayList<Exercise> availableExercises;

    public ExerciseList(AssetManager assets)
    {
        availableExercises = new ArrayList<>();

        //TODO: MAKE API CALL IN ORDER TO FILL THE LIST INSTEAD OF PARSING XML
        //KEEP IN MIND THAT YOU HAVE TO UPDATE THE EXERCISE OBJECT TOO.
        try
        {
            //parse the xml
            InputStream input = assets.open("exercises.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(input);

            NodeList nodeList = document.getElementsByTagName("exercise");
            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) //check if is a node that we can manipulate
                {
                    Element elementNode = (Element) node;

                    String exerciseName = elementNode.getElementsByTagName("name").item(0).getTextContent();
                    int sets = Integer.parseInt(elementNode.getElementsByTagName("sets").item(0).getTextContent());
                    String description = elementNode.getElementsByTagName("description").item(0).getTextContent();
                    String imageUrl = elementNode.getElementsByTagName("image_url").item(0).getTextContent();

                    availableExercises.add(new Exercise(exerciseName, sets, description, imageUrl));
                }
            }

        }
        catch (ParserConfigurationException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (SAXException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Exercise getExerciseByName(String name)
    {
        for(Exercise ex : availableExercises)
            if(ex.hasName(name))
                return ex;

        return null;
    }

    public ArrayList<Exercise> getAvailableExercises()
    {
        return availableExercises;
    }

}
