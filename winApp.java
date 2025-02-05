/*
Adam Khan
*/
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class winApp extends JFrame implements ActionListener
  {
    private JLabel lblParagraph, lblRep, lblResult; //2 Labels && Result at the end
    private JTextField paragraphtxt, reptxt; //2 Text Fields
    private JButton actionBtn; //1 Button
    private JRadioButton analysis, repeat; //2 Radio Buttons

    //Constructor
    public winApp()
    {
      setTitle("Analysis or Repeat");
      setSize(400, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(8, 2));
      setVisible(true);

      //Setting the Radio Buttons:
      analysis = new JRadioButton("Analysis \tParagraph");
      add(analysis);
      repeat = new JRadioButton("Repeat \t# of Reps");
      add(repeat);

      //Setting the Labels:
      lblParagraph = new JLabel("Line 1, Enter paragraph!:\n");
      add(lblParagraph);
      lblRep = new JLabel("Line 2, Enter how many times to repeat Line 1!:");
      add(lblRep);

      //Grouping Radio Buttons:
      ButtonGroup group = new ButtonGroup();
      group.add(analysis);
      group.add(repeat);   

      //Setting the Text Fields:
      paragraphtxt = new JTextField(20);
      paragraphtxt.setDocument(new JTextFieldLimit(20));
      add(paragraphtxt);
      reptxt = new JTextField(20);
      reptxt.setDocument(new JTextFieldLimit(1));
      add(reptxt);

      //Setting the Button:
      actionBtn = new JButton("Action");
      add(actionBtn);
      actionBtn.addActionListener(this);

      //Setting the Result Label:
      lblResult = new JLabel("");
      add(lblResult);
    }

    //Limiter to enable the 20 character limit & 1 character limit for text fields "paragraphtxt" & "reptxt"
    class JTextFieldLimit extends PlainDocument
      {
        private int limit;

        public JTextFieldLimit(int limit)
        {
          super();
          this.limit = limit;
        }
        public void insertString(int offset, String string, AttributeSet attr) throws BadLocationException
          {
            if ((getLength() + string.length()) <= limit)
              super.insertString(offset, string, attr);
          }
      }

    //Action Listener
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == actionBtn)
      {
        //If-loop depending on button selected:
        if (analysis.isSelected())
        {
          String paragraph = paragraphtxt.getText();
          int length = paragraph.replaceAll("\\s", "").length();
          //Number of characters written will be presented
          lblResult.setText("Result: " + length + " (# of Characters)");
        }

        else if (repeat.isSelected())
        {
          String paragraph = paragraphtxt.getText();
          int repeating = Integer.parseInt(reptxt.getText());
          String repeatingP = "";
          //For-loop to repeat the paragraph:
          for (int i = 0; i < repeating; i++)
            {
              repeatingP += paragraph + "\n"; 
            }
          //The paragraph will be repeated the number of times the user entered
          lblResult.setText("Result: " + repeatingP);
        }
      }
    }

    public static void main(String[] args)
    {
      java.awt.EventQueue.invokeLater(new Runnable()
        {
          public void run()
          {
            new winApp();
          }
        });
    } 
  }
