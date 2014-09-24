package com.phoenixjcam;

//package application.XMLreaderV2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RandomLearner extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel lblQuestion;
	private JButton btnNext;
	private JButton btnAnswer;
	private JButton btnDelete;
	private JTextArea txtaOptions;
	private JTextField txtfAnswer;
	private JTextField txtfCounter;
	private static int counter;

	private static final String RERUN_APP = "no more question rerun app";

	private RandomQuestions randomQuestions;

	public RandomLearner()
	{
		setLayout(null);

		randomQuestions = new RandomQuestions();

		add(getLblQuestion());
		add(getBtnNext());

		add(getBtnDelete());
		add(getTxtOptions());
		add(getBtnAnswer());

		add(getTxtfCounter());
		add(getTxtfAnswer());
	}

	private JLabel getLblQuestion()
	{
		lblQuestion = new JLabel("click Next question button :)");
		lblQuestion.setBackground(Color.GREEN);
		lblQuestion.setSize(1400, 30);
		lblQuestion.setLocation(40, 40);
		lblQuestion.setFont(new Font("Arial", Font.BOLD, 18));

		return lblQuestion;
	}

	private JButton getBtnNext()
	{
		btnNext = new JButton("Next question");
		btnNext.setLocation(1000, 600);
		btnNext.setSize(200, 50);
		btnNext.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (randomQuestions.isListEmpty())
				{
					lblQuestion.setText(RERUN_APP);
					txtaOptions.setText(RERUN_APP);
					txtfAnswer.setText(RERUN_APP);

					return;
				}
				else
				{
					randomQuestions.randIndex();
					txtaOptions.setText("");
					txtfAnswer.setText("");
					txtfCounter.setText("");

					String question = randomQuestions.getQuestion();
					lblQuestion.setText(question);

					String options = randomQuestions.getAnswer();
					txtaOptions.setText(options);

					counter++;

				}
			}
		});
		return btnNext;
	}

	private JButton getBtnAnswer()
	{
		btnAnswer = new JButton("Show Answer");
		btnAnswer.setLocation(1000, 680);
		btnAnswer.setSize(200, 50);
		btnAnswer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (randomQuestions.isListEmpty())
				{
					lblQuestion.setText(RERUN_APP);
					txtaOptions.setText(RERUN_APP);
					txtfAnswer.setText(RERUN_APP);

					return;
				}
				else
				{
					String strCounter = Integer.toString(counter);
					txtfCounter.setText(strCounter);

					String answer = randomQuestions.getProfession();
					txtfAnswer.setText(answer);
				}
			}
		});
		return btnAnswer;
	}

	private JButton getBtnDelete()
	{
		btnDelete = new JButton("Delete Question");
		btnDelete.setLocation(1000, 520);
		btnDelete.setSize(200, 50);
		btnDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (randomQuestions.isListEmpty())
					return;

				randomQuestions.deleteListElement();
			}
		});
		return btnDelete;
	}

	private JTextArea getTxtOptions()
	{
		txtaOptions = new JTextArea("click Next question button :)");
		txtaOptions.setBounds(40, 100, 1200, 400);
		txtaOptions.setEditable(false);
		txtaOptions.setLineWrap(true);
		txtaOptions.setFont(new Font("Arial", Font.PLAIN, 18));

		return txtaOptions;
	}

	private JTextField getTxtfAnswer()
	{
		txtfAnswer = new JTextField();
		txtfAnswer.setBounds(40, 600, 500, 30);
		txtfAnswer.setEditable(false);
		return txtfAnswer;
	}

	private JTextField getTxtfCounter()
	{
		txtfCounter = new JTextField();
		txtfCounter.setBounds(40, 550, 50, 30);
		txtfCounter.setEditable(false);
		return txtfCounter;
	}

	protected static void createGUI()
	{
		JFrame frame = new JFrame("Random Lerner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new RandomLearner());
		frame.setSize(1300, 800);
		frame.setLocation(100, 50);
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createGUI();
			}
		});
	}
}
