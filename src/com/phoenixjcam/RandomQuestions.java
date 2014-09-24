package com.phoenixjcam;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class RandomQuestions
{
	private LinkedList<String> answerLinkedList;
	private LinkedList<String> questionLinkedList;
	private LinkedList<String> optionsListLinkedList;

	private int randInt;
	private int lastRandInt;

	public RandomQuestions()
	{
		randInt = 0;
		answerLinkedList = new LinkedList<String>();
		questionLinkedList = new LinkedList<String>();
		optionsListLinkedList = new LinkedList<String>();

		String momoryTest2Str = "res/memoryTest-570mb.xml";
		String momoryTestStr = "res/memoryTest-170mb.xml";
		String questionsStr = "res/questions.xml";

		try
		{
			File fXmlFile = new File(momoryTest2Str);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("examSet");

			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;

					String question = eElement.getElementsByTagName("question").item(0).getTextContent();
					questionLinkedList.add(question);

					String options = eElement.getElementsByTagName("options").item(0).getTextContent();
					optionsListLinkedList.add(options);

					String answer = eElement.getElementsByTagName("answer").item(0).getTextContent();
					answerLinkedList.add(answer);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getRandInt(int min, int max)
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public void randIndex()
	{
		// if last element in list always randInt = 0;
		if (answerLinkedList.size() == 1)
		{
			randInt = 0;
			return;
		}

		lastRandInt = randInt;

		int min = 0;
		int max = answerLinkedList.size();

		randInt = getRandInt(min, max - 1);

		if (lastRandInt == randInt)
		{
			randIndex();
		}
	}

	public int getRandIndex()
	{
		return randInt;
	}

	public String getProfession()
	{
		return answerLinkedList.get(randInt);
	}

	public String getQuestion()
	{
		return questionLinkedList.get(randInt);
	}

	public String getAnswer()
	{
		return optionsListLinkedList.get(randInt);
	}

	/** delete entire list element - proffesion, question, answer */
	public void deleteListElement()
	{
		answerLinkedList.remove(randInt);
		questionLinkedList.remove(randInt);
		optionsListLinkedList.remove(randInt);
	}

	public int getListSize()
	{
		return answerLinkedList.size();
	}

	public boolean isListEmpty()
	{
		return answerLinkedList.isEmpty();
	}
}
